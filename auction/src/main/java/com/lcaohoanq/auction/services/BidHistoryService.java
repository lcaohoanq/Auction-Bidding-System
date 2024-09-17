package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.BidHistoryDTO;
import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionStatus;
import com.lcaohoanq.auction.models.AuctionStatus.Status;
import com.lcaohoanq.auction.models.BidHistory;
import com.lcaohoanq.auction.models.User;
import com.lcaohoanq.auction.repositories.AuctionRepository;
import com.lcaohoanq.auction.repositories.AuctionStatusRepository;
import com.lcaohoanq.auction.repositories.BidHistoryRepository;
import com.lcaohoanq.auction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BidHistoryService implements IBidHistoryService {

    private final BidHistoryRepository bidHistoryRepository;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    @Override
    public BidHistory placeBid(Long auctionId, Long userId, double bidAmount) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("Auction not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if(auction.getStatus().getName() == Status.INCOMING) {
            throw new IllegalArgumentException("Auction is not started yet");
        }

        if(auction.getStatus().getName() == Status.CLOSED) {
            throw new IllegalArgumentException("Auction is closed");
        }

        if (bidAmount % auction.getBidStep() != 0) {
            throw new IllegalArgumentException("Bid amount must be a multiple of the bid step, "
                                                   + "current bid step is: " + auction.getBidStep() + " Max bid amount is: " + auction.getHighestBid());
        }

        if (bidAmount <= auction.getHighestBid()) {
            throw new IllegalArgumentException("Bid amount must be higher than the current highest bid: " + " Max bid amount is: " + auction.getHighestBid());
        }

        auction.setHighestBid((int) bidAmount);
        auctionRepository.save(auction);

        BidHistory bidHistory = new BidHistory();
        bidHistory.setAuction(auction);
        bidHistory.setUser(user);
        bidHistory.setBidAmount(bidAmount);
        bidHistory.setBidTime(LocalDateTime.now());

        return bidHistoryRepository.save(bidHistory);
    }

    @Override
    public List<BidHistory> getBidHistoryForAuction(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("Auction not found"));

        if(auction.getStatus().getName() == Status.INCOMING) {
            throw new IllegalArgumentException("Auction is not started yet");
        }

        return bidHistoryRepository.findByAuctionOrderByBidTimeDesc(auction);
    }
}

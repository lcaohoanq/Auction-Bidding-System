package com.lcaohoanq.auction.controllers;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.BidHistory;
import com.lcaohoanq.auction.models.User;
import com.lcaohoanq.auction.repositories.AuctionRepository;
import com.lcaohoanq.auction.repositories.BidHistoryRepository;
import com.lcaohoanq.auction.repositories.UserRepository;
import com.lcaohoanq.auction.responses.BidMessage;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final BidHistoryRepository bidHistoryRepository;

    @MessageMapping("/placeBid")
    @SendTo("/topic/auction")
    public BidMessage bid(BidMessage message) throws Exception {
        System.out.println("New bid received: " + message);
        Auction auction = auctionRepository.findById(message.getAuctionId())
            .orElseThrow(() -> new RuntimeException("Auction not found with id: " + message.getAuctionId()));
        User user = userRepository.findById(message.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + message.getUserId()));

        if (message.getAmount() > auction.getHighestBid()) {
            auction.setHighestBid(message.getAmount());
            auctionRepository.save(auction);

            BidHistory bidHistory = new BidHistory();
            bidHistory.setAuction(auction);
            bidHistory.setUser(user);
            bidHistory.setBidAmount(message.getAmount());
            bidHistory.setBidTime(LocalDateTime.now());
            bidHistoryRepository.save(bidHistory);
        }
        return message;
    }

    @GetMapping("")
    public String index() {
        return "Welcome to the auction!";
    }
}


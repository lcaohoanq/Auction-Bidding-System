package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.AuctionDTO;
import com.lcaohoanq.auction.models.*;
import com.lcaohoanq.auction.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuctionService implements IAuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionStatusRepository auctionStatusRepository;
    private final IBidHistoryService bidHistoryService;
    private final IAuctionParticipantService auctionParticipantService;

    @Override
    public Auction createAuction(AuctionDTO auctionDTO) {
        Auction auction = Auction.builder()
            .title(auctionDTO.getTitle())
            .itemName(auctionDTO.getItemName())
            .bidStep(auctionDTO.getBidStep())
            .currentBid(auctionDTO.getCurrentBid())
            .highestBid(auctionDTO.getHighestBid())
            .startTime(LocalDateTime.parse(auctionDTO.getStartTime()))
            .endTime(LocalDateTime.parse(auctionDTO.getEndTime()))
            .status(auctionStatusRepository.findById(auctionDTO.getStatusId()).orElseThrow())
            .build();

        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public Optional<Auction> getAuctionById(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public Auction updateAuction(Long id, AuctionDTO auctionDTO) {
        Auction auction = auctionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Auction not found"));

        auction.setTitle(auctionDTO.getTitle());
        auction.setItemName(auctionDTO.getItemName());
        auction.setBidStep(auctionDTO.getBidStep());
        auction.setCurrentBid(auctionDTO.getCurrentBid());
        auction.setHighestBid(auctionDTO.getHighestBid());
        auction.setStartTime(LocalDateTime.parse(auctionDTO.getStartTime()));
        auction.setEndTime(LocalDateTime.parse(auctionDTO.getEndTime()));
        auction.setStatus(auctionStatusRepository.findById(auctionDTO.getStatusId()).orElseThrow());

        return auctionRepository.save(auction);
    }

    @Override
    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }

    @Override
    public void endAuction(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("Auction not found"));
        AuctionStatus endedStatus = auctionStatusRepository.findById(3)
            .orElseThrow(() -> new RuntimeException("Ended status not found"));
        auction.setStatus(endedStatus);
        auction.setEndTime(LocalDateTime.now());
        auctionRepository.save(auction);
    }

    @Override
    public List<Auction> getActiveAuctions() {
        AuctionStatus activeStatus = auctionStatusRepository.findById(2)
            .orElseThrow(() -> new RuntimeException("Active status not found"));
        return auctionRepository.findByStatus(activeStatus);
    }
}
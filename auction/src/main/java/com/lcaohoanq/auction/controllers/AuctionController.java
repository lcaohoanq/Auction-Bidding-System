package com.lcaohoanq.auction.controllers;

import com.lcaohoanq.auction.dtos.AuctionDTO;
import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionParticipant;
import com.lcaohoanq.auction.models.BidHistory;
import com.lcaohoanq.auction.responses.BidMessage;
import com.lcaohoanq.auction.services.AuctionService;
import com.lcaohoanq.auction.services.BidHistoryService;
import com.lcaohoanq.auction.services.IAuctionParticipantService;
import com.lcaohoanq.auction.services.IAuctionService;
import com.lcaohoanq.auction.services.IBidHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final IAuctionService auctionService;
    private final IBidHistoryService bidHistoryService;
    private final IAuctionParticipantService auctionParticipantService;

    @MessageMapping("/placeBid")
    @SendTo("/topic/auction")
    public BidMessage bid(BidMessage message) {
        BidHistory bidHistory = bidHistoryService.placeBid(message.getAuctionId(), message.getUserId(), message.getAmount());
        message.setBidTime(bidHistory.getBidTime());
        return message;
    }

    @PostMapping
    public ResponseEntity<?> createAuction(@RequestBody AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionService.createAuction(auctionDTO));
    }

    @GetMapping
    public ResponseEntity<List<Auction>> getAllAuctions() {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        return auctionService.getAuctionById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@PathVariable Long id,
                                                 @RequestBody AuctionDTO auctionDTO) {
        return ResponseEntity.ok(auctionService.updateAuction(id, auctionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity<List<BidHistory>> getBidHistory(@PathVariable Long id) {
        return ResponseEntity.ok(bidHistoryService.getBidHistoryForAuction(id));
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Void> joinAuction(@PathVariable Long id, @RequestParam Long userId) {
        auctionParticipantService.joinAuction(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<AuctionParticipant>> getAuctionParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(auctionParticipantService.getAuctionParticipants(id));
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Void> endAuction(@PathVariable Long id) {
        auctionService.endAuction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<Auction>> getActiveAuctions() {
        return ResponseEntity.ok(auctionService.getActiveAuctions());
    }

    @PostMapping("/{id}/bids")
    public ResponseEntity<?> placeBid(@PathVariable Long id, @RequestBody BidMessage bidMessage) {
        try{
            BidHistory bidHistory = bidHistoryService.placeBid(id, bidMessage.getUserId(), bidMessage.getAmount());
            return ResponseEntity.ok(bidHistory);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
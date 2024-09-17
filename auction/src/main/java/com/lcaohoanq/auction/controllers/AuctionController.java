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
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getBidHistory(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(bidHistoryService.getBidHistoryForAuction(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<?> joinAuction(@PathVariable Long id, @RequestParam Long userId) {
        try{
            auctionParticipantService.joinAuction(id, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<?> getAuctionParticipants(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(auctionParticipantService.getAuctionParticipants(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<?> endAuction(@PathVariable Long id) {
        try{
            auctionService.endAuction(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Auction>> getActiveAuctions() {
        return ResponseEntity.ok(auctionService.getActiveAuctions());
    }

    @PostMapping("/{id}/bids")
    public ResponseEntity<?> placeBid(@PathVariable Long id, @RequestBody BidMessage bidMessage, @RequestHeader("Authorization") String token) {
        try {
            // Validate the token (you'll need to implement this method)
            Long userId = validateToken(token);
            if (userId == null) {
                return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
            }

            BidHistory bidHistory = bidHistoryService.placeBid(id, userId, bidMessage.getAmount());
            return ResponseEntity.ok(bidHistory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Long validateToken(String token) {
        // Implement token validation logic here
        // This is a placeholder implementation
        String bearerToken = token.split(" ")[1];
        if ("12345".equals(bearerToken)) {
            return 1L; // Return the user ID associated with the token
        }
        return null;
    }
}
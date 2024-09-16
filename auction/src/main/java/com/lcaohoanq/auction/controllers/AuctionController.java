package com.lcaohoanq.auction.controllers;

import com.lcaohoanq.auction.Auction;
import com.lcaohoanq.auction.AuctionRepository;
import com.lcaohoanq.auction.responses.BidMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionRepository auctionRepository;

    @MessageMapping("/placeBid")
    @SendTo("/topic/auction")
    public BidMessage bid(BidMessage message) throws Exception {
        System.out.println("New bid received: " + message);
        // Handle and process bid logic
        // Update the auction in the database
        Auction auction = auctionRepository.findById(message.getAuctionId())
    .orElseThrow(() -> new RuntimeException("Auction not found with id: " + message.getAuctionId()));
        if (message.getAmount() > auction.getHighestBid()) {
            auction.setHighestBid(message.getAmount());
            auction.setHighestBidder(message.getBidder());
            auctionRepository.save(auction);
        }
        return message;
    }

    @GetMapping("")
    public String index() {
        return "Welcome to the auction!";
    }
}


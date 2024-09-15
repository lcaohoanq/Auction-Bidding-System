package com.lcaohoanq.auction.controllers;

import com.lcaohoanq.auction.responses.BidMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuctionController {

    @MessageMapping("/placeBid")
    @SendTo("/topic/auction")
    public BidMessage bid(BidMessage message) throws Exception {
        System.out.println("New bid received: " + message);
        // Handle and process bid logic
        return message;
    }

    @GetMapping("")
    public String index() {
        return "Welcome to the auction!";
    }
}


package com.lcaohoanq.auction;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auctions")
public class Auction {
    @Id
    private String id;
    private String itemName;
    private double highestBid;
    private String highestBidder;
    // getters and setters
}


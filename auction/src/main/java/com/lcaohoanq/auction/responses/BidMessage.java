package com.lcaohoanq.auction.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidMessage {
    private Long auctionId;
    private String bidder;
    private double amount;
    private String bidTime;
}


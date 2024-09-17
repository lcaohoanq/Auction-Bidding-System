package com.lcaohoanq.auction.responses;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidMessage {
    private Long auctionId;
    private Long userId;
    private int amount;
    private LocalDateTime bidTime;
}


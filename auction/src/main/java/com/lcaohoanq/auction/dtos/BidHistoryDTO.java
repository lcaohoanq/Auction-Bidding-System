package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BidHistoryDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("bid_amount")
    @NotNull(message = "Bid amount is required")
    @Min(value = 0, message = "Bid amount must be greater than or equal to 0")
    private Double bidAmount;

    @JsonProperty("bid_time")
    @NotNull(message = "Bid time is required")
    private LocalDateTime bidTime;

    @JsonProperty("auction_id")
    @NotNull(message = "Auction ID is required")
    private Long auctionId;

    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    private Long userId;
}
package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuctionDTO {

    @JsonProperty("title")
    @NotBlank(message = "Auction Title is required")
    private String title; //Atumn asfasdfsdaf Auction

    @JsonProperty("item_name")
    @NotBlank(message = "Item Name is required")
    private String itemName;

    @JsonProperty("bid_step")
    @Min(value = 10, message = "Bid Step must be greater than 10")
    @NotBlank(message = "Bid Step is required")
    private int bidStep;

    @JsonProperty("current_bid")
    @Min(value = 0, message = "Current Bid must be greater than 0")
    private int currentBid;

    @JsonProperty("highest_bid")
    @Min(value = 0, message = "Highest Bid must be greater than 0")
    private int highestBid;

    @JsonProperty("start_time")
    @NotBlank(message = "Start Time is required")
    private String startTime;

    @JsonProperty("end_time")
    @NotBlank(message = "End Time is required")
    private String endTime;

    @JsonProperty("status_id")
    @NotBlank(message = "Auction Status Id is required")
    private int statusId; //status id of the auction (plus one coresponding to the enum)

}

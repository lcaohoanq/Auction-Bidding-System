package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AuctionParticipantDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("join_time")
    @NotNull(message = "Join time is required")
    private LocalDateTime joinTime;

    @JsonProperty("auction_id")
    @NotNull(message = "Auction ID is required")
    private Long auctionId;

    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    private Long userId;
}
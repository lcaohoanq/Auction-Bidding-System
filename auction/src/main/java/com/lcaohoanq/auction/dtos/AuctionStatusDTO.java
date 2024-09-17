package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuctionStatusDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @NotNull(message = "Status name is required")
    private String name;
}
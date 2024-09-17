package com.lcaohoanq.auction.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuctionParticipantResponse {
    private Long auctionId;
    private String auctionTitle;
    private List<UserResponse> participants;

}

package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.AuctionParticipantDTO;
import com.lcaohoanq.auction.models.AuctionParticipant;
import java.util.List;

public interface IAuctionParticipantService {
    AuctionParticipant joinAuction(Long auctionId, Long userId);
    List<AuctionParticipant> getAuctionParticipants(Long auctionId);
}
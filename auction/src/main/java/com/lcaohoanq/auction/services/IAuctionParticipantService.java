package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.models.AuctionParticipant;
import com.lcaohoanq.auction.responses.AuctionParticipantResponse;

public interface IAuctionParticipantService {
    AuctionParticipant joinAuction(Long auctionId, Long userId);
    AuctionParticipantResponse getAuctionParticipants(Long auctionId);
}
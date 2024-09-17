package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.BidHistoryDTO;
import com.lcaohoanq.auction.models.BidHistory;
import java.util.List;

public interface IBidHistoryService {
    BidHistory placeBid(Long auctionId, Long userId, double bidAmount);
    List<BidHistory> getBidHistoryForAuction(Long auctionId);
}

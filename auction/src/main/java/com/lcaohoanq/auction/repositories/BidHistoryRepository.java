package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidHistoryRepository extends JpaRepository<BidHistory, Long> {
}


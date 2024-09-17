package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}


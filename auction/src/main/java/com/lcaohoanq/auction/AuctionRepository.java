package com.lcaohoanq.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}


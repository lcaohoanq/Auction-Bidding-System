package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionProductRepository extends JpaRepository<AuctionProduct, Long> {
}


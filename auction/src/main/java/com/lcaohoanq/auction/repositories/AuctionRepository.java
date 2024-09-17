package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findByStatus(AuctionStatus status);

}


package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.AuctionStatus;
import com.lcaohoanq.auction.models.BidMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionStatusRepository extends JpaRepository<AuctionStatus, Long> {

}

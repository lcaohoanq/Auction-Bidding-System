package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionParticipant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionParticipantRepository extends JpaRepository<AuctionParticipant, Long> {
}
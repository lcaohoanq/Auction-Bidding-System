package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.AuctionDTO;
import com.lcaohoanq.auction.models.Auction;
import java.util.List;
import java.util.Optional;

public interface IAuctionService {

    Auction createAuction(AuctionDTO auctionDTO);
    List<Auction> getAllAuctions();
    Optional<Auction> getAuctionById(Long id);
    Auction updateAuction(Long id, AuctionDTO auctionDTO);
    void deleteAuction(Long id);
    List<Auction> getActiveAuctions();
    void endAuction(Long id);
}

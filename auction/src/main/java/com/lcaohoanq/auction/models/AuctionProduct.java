package com.lcaohoanq.auction.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auction_products")
@Data
public class AuctionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "bid_method_id")
    private BidMethod bidMethod;

}

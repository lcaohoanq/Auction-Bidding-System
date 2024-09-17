package com.lcaohoanq.auction.models;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "base_price")
    private double basePrice;

    // One-to-Many relationship with AuctionProduct
    @OneToMany(mappedBy = "product")
    private Set<AuctionProduct> auctionProducts;
}
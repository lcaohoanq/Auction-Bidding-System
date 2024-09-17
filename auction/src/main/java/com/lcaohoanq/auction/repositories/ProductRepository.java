package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}


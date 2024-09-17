package com.lcaohoanq.auction.repositories;

import com.lcaohoanq.auction.models.Product;
import com.lcaohoanq.auction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);

}


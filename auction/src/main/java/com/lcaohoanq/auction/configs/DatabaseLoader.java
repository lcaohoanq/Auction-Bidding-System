package com.lcaohoanq.auction.configs;

import com.lcaohoanq.auction.Auction;
import com.lcaohoanq.auction.AuctionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(AuctionRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Auction(null, "Default Item", 0.0, ""));
            }
        };
    }
}

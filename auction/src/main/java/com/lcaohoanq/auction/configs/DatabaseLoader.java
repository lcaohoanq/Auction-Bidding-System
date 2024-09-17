package com.lcaohoanq.auction.configs;

import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionStatus;
import com.lcaohoanq.auction.models.AuctionStatus.Status;
import com.lcaohoanq.auction.repositories.AuctionRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

//    @Bean
//    CommandLineRunner initDatabase(AuctionRepository repository) {
//        return args -> {
//            if (repository.count() == 0) {
//                repository.save(new Auction(null,
//                                            "Winter Auction", "Metallica Vintage T-Shirt "
//                    , 10
//                    , LocalDateTime.of(2024, 9, 25, 14, 30, 45, 123456789)
//                    , LocalDateTime.of(2024, 9, 29, 14, 30, 45, 123456789)
//                    ,AuctionStatus.builder().status(Status.INCOMING).build()));
//            }
//        };
//    }
}

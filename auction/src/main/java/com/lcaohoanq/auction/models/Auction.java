package com.lcaohoanq.auction.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //Atumn asfasdfsdaf Auction

    @Column(name="item_name")
    private String itemName;

    @Column(name="bid_step")
    private int bidStep;

    @Column(name="current_bid", columnDefinition = "int default 0")
    private int currentBid;

    @Column(name="highest_bid", columnDefinition = "int default 0")
    private int highestBid;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name="status_id")
    private AuctionStatus status;

    // Many-to-Many relationship with User through AuctionParticipant
    @OneToMany(mappedBy = "auction")
    private Set<AuctionParticipant> auctionParticipants;

    // One-to-Many relationship with AuctionProduct
    @OneToMany(mappedBy = "auction")
    private Set<AuctionProduct> auctionProducts;

    public Auction(Long id, String title, String itemName, int bidStep, LocalDateTime startTime,
                   LocalDateTime endTime, AuctionStatus status) {
        this.id = id;
        this.title = title;
        this.itemName = itemName;
        this.bidStep = bidStep;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

}


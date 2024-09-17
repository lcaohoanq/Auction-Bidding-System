package com.lcaohoanq.auction.models;

import jakarta.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(name="email", unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<AuctionParticipant> auctionParticipants;
}

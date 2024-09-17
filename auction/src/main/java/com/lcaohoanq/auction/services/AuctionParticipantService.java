package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.AuctionParticipantDTO;
import com.lcaohoanq.auction.models.Auction;
import com.lcaohoanq.auction.models.AuctionParticipant;
import com.lcaohoanq.auction.models.AuctionStatus.Status;
import com.lcaohoanq.auction.models.User;
import com.lcaohoanq.auction.repositories.AuctionParticipantRepository;
import com.lcaohoanq.auction.repositories.AuctionRepository;
import com.lcaohoanq.auction.repositories.UserRepository;
import com.lcaohoanq.auction.responses.AuctionParticipantResponse;
import com.lcaohoanq.auction.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuctionParticipantService implements IAuctionParticipantService {

    private final AuctionParticipantRepository auctionParticipantRepository;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    @Override
    public AuctionParticipant joinAuction(Long auctionId, Long userId) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("Auction not found"));

        if(auction.getStatus().getName() == Status.INCOMING){
            throw new IllegalArgumentException("Auction is not started yet");
        }

        if(auction.getStatus().getName() == Status.CLOSED) {
            throw new IllegalArgumentException("Auction is closed");
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        AuctionParticipant participant = new AuctionParticipant();
        participant.setAuction(auction);
        participant.setUser(user);
        participant.setJoinTime(LocalDateTime.now());

        participant = auctionParticipantRepository.save(participant);
        return participant;
    }

    @Override
    public AuctionParticipantResponse getAuctionParticipants(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("Auction not found"));

        // Directly retrieve unique participants from the Many-to-Many relationship
        List<UserResponse> participants = auction.getAuctionParticipants().stream()
            .map(user -> new UserResponse(user.getId(), user.getUser().getUsername(), user.getUser().getEmail()))
            .collect(Collectors.toList());

        return new AuctionParticipantResponse(auction.getId(), auction.getTitle(), participants);
    }

    private AuctionParticipantDTO convertToAuctionParticipantDTO(AuctionParticipant participant) {
        return AuctionParticipantDTO.builder()
            .id(participant.getId())
            .joinTime(participant.getJoinTime())
            .auctionId(participant.getAuction().getId())
            .userId(participant.getUser().getId())
            .build();
    }
}
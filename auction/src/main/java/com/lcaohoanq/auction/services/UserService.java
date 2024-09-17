package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.UserDTO;
import com.lcaohoanq.auction.models.User;
import com.lcaohoanq.auction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {

        if(userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
            .build();

        return userRepository.save(user);

    }

}

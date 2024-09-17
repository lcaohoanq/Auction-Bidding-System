package com.lcaohoanq.auction.services;

import com.lcaohoanq.auction.dtos.UserDTO;
import com.lcaohoanq.auction.models.User;

public interface IUserService {

    User createUser(UserDTO userDTO);

}

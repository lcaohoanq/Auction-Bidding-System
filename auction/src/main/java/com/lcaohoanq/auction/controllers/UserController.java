package com.lcaohoanq.auction.controllers;

import com.lcaohoanq.auction.dtos.UserDTO;
import com.lcaohoanq.auction.dtos.UserLoginDTO;
import com.lcaohoanq.auction.models.User;
import com.lcaohoanq.auction.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        User newUser;
        try{
            newUser = userService.createUser(userDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO){
        User user;
        try{
            user = userService.login(userLoginDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Login successfully");
    }
}

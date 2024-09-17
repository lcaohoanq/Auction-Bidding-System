package com.lcaohoanq.auction.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    private String username;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;
}
package com.example.DigitalWallet.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
  
    @Email()
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min=6,max=8)
    private String password;
}

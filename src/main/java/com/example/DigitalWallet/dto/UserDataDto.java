package com.example.DigitalWallet.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDataDto {
     @Column(nullable = false)
    @NotNull()
    private String name;

    @Column(nullable = false)
    private String phone;


    @Column(nullable = false,unique = true)
    @NotNull
    @Size(min=9,max=9)
    private String panCardNumber;
}

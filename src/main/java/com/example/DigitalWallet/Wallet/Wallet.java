package com.example.DigitalWallet.Wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long walletId;

    

    private double walletAmount;

    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus;

}

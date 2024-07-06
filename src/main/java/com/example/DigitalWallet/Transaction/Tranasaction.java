package com.example.DigitalWallet.Transaction;

import java.util.Date;

import org.springframework.boot.context.annotation.UserConfigurations;

import com.example.DigitalWallet.UserData.UserData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tranasaction {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private double amount;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    UserData userData;

    @ManyToOne
    @JoinColumn(name = "transferFrom",referencedColumnName = "userId")
    UserData transferFrom;

    @ManyToOne
    @JoinColumn(name = "transferTo",referencedColumnName = "userId")
    UserData transferTo;
}

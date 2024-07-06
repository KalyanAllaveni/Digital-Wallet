package com.example.DigitalWallet.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Tranasaction,Long>{
    
}

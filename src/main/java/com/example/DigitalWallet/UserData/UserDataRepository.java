package com.example.DigitalWallet.UserData;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDataRepository extends JpaRepository<UserData,Long>{
    // @Query("select u from User u where u.username=:userName")
    // <Optional>UserData  findByUsername(String userName);

    @Query("select u from UserData u where u.phone=:phone")
    Optional<UserData> findByPhone(String phone);
}

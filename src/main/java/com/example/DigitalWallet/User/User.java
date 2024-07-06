package com.example.DigitalWallet.User;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.DigitalWallet.UserData.UserData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.Data;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Setter
@Getter
@Entity
public class User implements UserDetails {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(unique = true)
    private String username;
  
    private String password;

    @OneToOne
    UserData userData;

     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}

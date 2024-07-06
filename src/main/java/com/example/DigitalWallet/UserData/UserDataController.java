package com.example.DigitalWallet.UserData;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DigitalWallet.User.User;
import com.example.DigitalWallet.dto.UserDataDto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class UserDataController {
    
    @Autowired 
    UserDataService userDetailsService;


   @PostMapping("user/createuser")
   public UserData createUser(@RequestPart ("data") UserDataDto userData,
                            @RequestPart ("file") MultipartFile filename)throws IOException{
       
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return userDetailsService.registerUser(userData,filename,user);
   }
   
    @GetMapping("user/getuser/{userId}")
    public UserData getuser(@PathVariable Long userId) {
        return userDetailsService.getUser(userId);
    }

    @DeleteMapping("user/deleteuser/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userDetailsService.deleteUser(userId);
    }
    
}

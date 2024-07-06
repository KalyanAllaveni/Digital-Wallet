package com.example.DigitalWallet.UserData;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.DigitalWallet.Email.Emailsender;
import com.example.DigitalWallet.User.User;
import com.example.DigitalWallet.User.UserRepository;
import com.example.DigitalWallet.Wallet.Wallet;
import com.example.DigitalWallet.Wallet.WalletRepository;
import com.example.DigitalWallet.Wallet.WalletStatus;
import com.example.DigitalWallet.dto.UserDataDto;



@Service
public class UserDataService {
    
    
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    Emailsender email;



    public UserData registerUser(UserDataDto userData, MultipartFile filename, User user)throws IOException {
       String[] extension=filename.getOriginalFilename().split("[.]");
        String path="C:\\Users\\Dell\\Downloads\\"+user.getUserid()+"."+extension[extension.length-1];
        File f=new File(path);
        filename.transferTo(f);
        Wallet wallet=Wallet.builder()
                        
                    .walletAmount(0)
                    .walletStatus(WalletStatus.INACTIVE)
                    .build();
       
         wallet=this.walletRepository.save(wallet);
        UserData userData1=UserData.builder().email(user.getUsername()).imagePath(path)
                    .panCardNumber(userData.getPanCardNumber()).phone(userData.getPhone()).wallet(wallet).name(userData.getName()).build();
      
        
        userData1=this.userDataRepository.save(userData1);
        
        user.setUserData(userData1);
        
        email.emaisender(user.getUsername(),"Your Wallet is Created", 
        "Your wallet is created whose status is Inactive");
       
        user=this.userRepository.save(user);
        return userData1;
    } 

    public UserData getUser(Long userid){
        return (UserData)userDataRepository.findById(userid)
        .orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404),"Inavalid user"));
    }
    
    public String deleteUser(Long userid){
        userRepository.deleteById(userid);
        return "user deleted";
    }

    
}

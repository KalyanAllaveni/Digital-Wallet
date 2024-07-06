package com.example.DigitalWallet.Wallet;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.DigitalWallet.Email.Emailsender;
import com.example.DigitalWallet.Transaction.Tranasaction;
import com.example.DigitalWallet.Transaction.TransactionRepository;
import com.example.DigitalWallet.User.User;
import com.example.DigitalWallet.User.UserRepository;
import com.example.DigitalWallet.UserData.UserData;
import com.example.DigitalWallet.UserData.UserDataRepository;
import com.example.DigitalWallet.dto.TransferDto;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired 
    UserDataRepository userDataRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Emailsender emailsender;

    @Autowired 
    TransactionRepository transactionRepository;
    
    public Wallet getWallet(Long userId) {
        User user =userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Wallet wallet=user.getUserData().getWallet();
        return wallet;
    }

    public String deleteWallet(long userId) {
        User user =userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Wallet wallet=user.getUserData().getWallet();
        walletRepository.delete(wallet);
        return "Your wallet is deleted";
    }

    public Wallet updateWalletStatus(User user, WalletStatus walletStatus) {
        Wallet wallet=user.getUserData().getWallet();
        wallet.setWalletStatus(walletStatus);
        wallet=this.walletRepository.save(wallet);
        emailsender.emaisender(user.getUserData().getEmail(), 
        "Wallet Status Updated", "Wallet Status is Active");
        return wallet;
    }

    public Wallet addMoneyToWallet(User user, double amount) {
        Wallet wallet=user.getUserData().getWallet();
        if(wallet.getWalletStatus().equals(WalletStatus.INACTIVE)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Your wallet Status is INACTIVE");
        }
        wallet.setWalletAmount(wallet.getWalletAmount()+amount);
        emailsender.emaisender(user.getUserData().getEmail(), 
        "Money added to Wallet", "You have added money to to your wallet total of"+amount);
        wallet=walletRepository.save(wallet);
        return wallet;
    }

    public Wallet transferMoney(TransferDto transferDto,User user){
        Wallet wallet=user.getUserData().getWallet();
        if(wallet.getWalletStatus().equals(WalletStatus.INACTIVE)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Your wallet Status is INACTIVE");
        }

        if(wallet.getWalletAmount()<transferDto.getAmount()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Your wallet has not enough money");
        }

        UserData tranferData=userDataRepository.findByPhone(transferDto.getPhone()).
                    orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404),"Your wallet is INACTIVE"));

            Wallet tranferWallet=tranferData.getWallet();
        if(tranferWallet.getWalletStatus().equals(WalletStatus.INACTIVE)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Your wallet Status is INACTIVE");
        }

        wallet.setWalletAmount(wallet.getWalletAmount()-transferDto.getAmount());
        emailsender.emaisender(user.getUserData().getEmail(), "Money Transferred",
        "You have transfered money from wallet  amount: " + transferDto.getAmount());

        this.walletRepository.save(wallet);

        tranferWallet.setWalletAmount(tranferWallet.getWalletAmount()+transferDto.getAmount());
        emailsender.emaisender(user.getUserData().getEmail(), "Money Transferred",
                                "money has transffered to your wallet  amount: " + transferDto.getAmount());

        this.walletRepository.save(tranferWallet);

        Tranasaction transaction =Tranasaction.builder()
        .amount(transferDto.getAmount()).date(new Date()).transferFrom(user.getUserData()).transferTo(tranferData)
        .build();

        transactionRepository.save(transaction);
        

        return wallet;



    }





}

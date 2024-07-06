package com.example.DigitalWallet.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalWallet.User.User;
import com.example.DigitalWallet.UserData.UserData;
import com.example.DigitalWallet.dto.TransferDto;

@RestController
@RequestMapping("wallet")
public class WalletController {
    
    @Autowired
    WalletService walletService;

    @GetMapping("/getwallet/{userId}")
    public Wallet getWallet(@PathVariable Long userId ){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.getWallet(user.getUserid());
    }

    @DeleteMapping("deletewallet/{userId}")
    public String deleteWallet(){
        UserData user=(UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.deleteWallet(user.getUserId());
        
    }

    @PostMapping("/updatewallet")
    public Wallet updateWalletStatus(@RequestParam("status")WalletStatus walletStatus){
        User user =(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.updateWalletStatus(user,walletStatus);
    }

    @PostMapping("/addMoney")
    public Wallet addMoneyToWallet(@RequestParam("amount") double amount){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.addMoneyToWallet(user,amount);
    }

    @PostMapping("/sendMoney")
    public Wallet sendMoney(@RequestBody TransferDto transferDto){
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.transferMoney(transferDto, user);
    }
}

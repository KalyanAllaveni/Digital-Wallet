package com.example.DigitalWallet.Email;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Component;



@Component
public class Emailsender {
    
    public void emaisender(String receiverAdd,String subject,String context){
        Email email=EmailBuilder.startingBlank()
                    .from("kalyanallaveni003@gmail.com")
                    .to(receiverAdd)
                    .withSubject(subject)
                    .withPlainText(context)
                    .buildEmail();
            
        Mailer mailer=MailerBuilder
        .withSMTPServer("smtp.gmail.com", 587,"kalyanallaveni003@gmail.com" ,"azvi utah okef obyv" ).buildMailer();
                    
        mailer.sendMail(email, true);
    }

}

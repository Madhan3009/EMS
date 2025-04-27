package com.EMS.demo.Service;

import com.EMS.demo.Entities.ConfirmationToken;
import com.EMS.demo.Repositories.ConfirmationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenGeneration {

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepo;



    public TokenGeneration(ConfirmationTokenRepo confirmationTokenRepo){

        this.confirmationTokenRepo = confirmationTokenRepo;

    }

    public ConfirmationToken generateToken(){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        StringBuilder token = generateRandomToken();
        confirmationToken.setConfirmationToken(token);
        return confirmationTokenRepo.save(confirmationToken);
    }
    public StringBuilder generateRandomToken(){
        StringBuilder token = new StringBuilder();
        for(int i=0;i<6;i++){
            token.append((int) (Math.random() * 10));
        }
        return token;

}}


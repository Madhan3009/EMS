package com.EMS.demo.Service;

import com.EMS.demo.Entities.ConfirmationToken;
import com.EMS.demo.Repositories.ConfirmationTokenRepo;

public class TokenGeneration {

    private ConfirmationTokenRepo confirmationTokenRepo;


    private ConfirmationToken confirmationToken;

    TokenGeneration(ConfirmationTokenRepo confirmationTokenRepo,ConfirmationToken confirmationToken){
        this.confirmationToken = confirmationToken;
        this.confirmationTokenRepo = confirmationTokenRepo;

    }

    public ConfirmationToken generateToken(){
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


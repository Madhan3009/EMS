package com.EMS.demo.Service;

public class TokenGeneration {
    public static String generateToken(){
        StringBuilder token = new StringBuilder();
        for(int i=0;i<6;i++){
            token.append((int) (Math.random() * 10));
        }
        return token.toString();

}}


package com.nh7.ecommerce.util;

import org.springframework.stereotype.Component;

@Component
public class RandomPasswordUtil {
    public String rand(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int num= 0;
        do{
            num=(int) (Math.random()*256);
        }while (num<8);
        StringBuilder password = new StringBuilder(num);
        password.append("$");
        for(int i=0;i<num;i++){
            int index = (int) (Math.random()*AlphaNumericString.length());
            password.append(AlphaNumericString.charAt(index));
        }
        return new String(password);
    }
}

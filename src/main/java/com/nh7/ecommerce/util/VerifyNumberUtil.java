package com.nh7.ecommerce.util;
import org.springframework.stereotype.Component;

@Component
public class VerifyNumberUtil {
    public Integer generate(int n){
        return (int)(Math.random()*Math.pow(10.0,n));
    }
}

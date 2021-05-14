package com.nh7.ecommerce.security.authorizer;

import org.springframework.core.ResolvableType;
import org.springframework.security.core.Authentication;

public class AppAuthorizerImpl implements AppAuthorizer{
    @Override
    public boolean authorize(Authentication auth, String action, Object callerObj) {


        return false;
    }
    private String extractSecurityPath(Object callerObject){
        Class<?> clazz = ResolvableType.forClass(callerObject.getClass()).getRawClass();
            return null;
    }
}

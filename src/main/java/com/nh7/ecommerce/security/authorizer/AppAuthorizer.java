package com.nh7.ecommerce.security.authorizer;

import org.springframework.security.core.Authentication;

public interface AppAuthorizer {
    boolean authorize(Authentication auth,String action,Object callerObj);
}

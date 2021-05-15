package com.nh7.ecommerce.security.authorizer;

import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.service.AuthorizationService;
import com.nh7.ecommerce.service.JwtUserDetailsService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

@Service("appAuthorizer")
public class AppAuthorizerImpl implements AppAuthorizer{
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authService;
    @Override
    public boolean authorize(Authentication auth, String action, Object callerObj) {
        String securedPath = extractSecurityPath(callerObj);
        System.out.println("SECURED-PATH: "+ securedPath);
        if(securedPath == null || "".equals(securedPath.trim())) return true; // chua login
        if(auth== null) return  true;
        String info = auth.getPrincipal().toString();
        String username="";
        if(info.startsWith("org.springframework.security.core.userdetails.User")){
            username = info.substring(info.indexOf('=')+1,info.indexOf(','));
        }else {
            username = info;
        }
        Long id = userService.findIdByUsername(username);
        return authService.checkPermission(id,action,securedPath);
    }
    private String extractSecurityPath(Object callerObject){
        Class<?> clazz = ResolvableType.forClass(callerObject.getClass()).getRawClass();
        Optional<Annotation> annotation = Arrays.asList(clazz.getAnnotations()).stream().filter((ann)->{
            return ann instanceof RequestMapping;
        }).findFirst();
        if(annotation.isPresent()){
            return ((RequestMapping) annotation.get()).value()[0];
        }
        return null;
    }
}

package com.nh7.ecommerce.controller.api.social;

import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.dto.social.SocialLogin;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.service.UserDetailsService;
import com.nh7.ecommerce.service.UserService;
import com.nh7.ecommerce.util.RandomPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/login/facebook")
public class SocialFacebookApi {
    private String clientId="304496104650841";

    private String clientSecret="bd2ac4a237cbda8fe4101f2b8835afd6";

    private String redirect="https://shopee-ecommerce-nh7.herokuapp.com/api/login/facebook/redirect";

    @Autowired
    private UserService userService;

    @Autowired
    private RandomPasswordUtil randomPasswordUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    private FacebookConnectionFactory factory = new FacebookConnectionFactory(clientId,clientSecret);
    @CrossOrigin
    @GetMapping
    public RedirectView toFacebook(){
        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("email,public_profile");
        parameters.setRedirectUri(redirect);
        String url=operations.buildAuthenticateUrl(parameters);
        return new RedirectView(url);
    }
    @CrossOrigin
    @GetMapping("/redirect")
    public SocialLogin producer(@RequestParam("code") String authorizationCode){
        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode,redirect,null);
        Connection<Facebook> connection =factory.createConnection(accessToken);
        System.out.println("FACEBOOK "+authorizationCode);
        Facebook facebook = connection.getApi();
        String[] fields = {"id","email","name","birthday","gender"};
        User userProfile = facebook.fetchObject("me",User.class,fields);
        Long idOfEmail = userService.findIdByEmailAddressAndAuthProvider(userProfile.getEmail(),AuthProviderEnum.FACEBOOK_USER);
        String password =randomPasswordUtil.rand();
        if(idOfEmail == null){
            // USER
            com.nh7.ecommerce.entity.User userEntity = new com.nh7.ecommerce.entity.User();
            userEntity.setEmailAddress(userProfile.getEmail());
            userEntity.setPassword(password);
            userEntity.setAuthProvider(AuthProviderEnum.FACEBOOK_USER);
            com.nh7.ecommerce.entity.User user =userService.save(userEntity);
            idOfEmail = user.getId();
            //USER DETAIL
            UserDetails userDetails = new UserDetails();
            userDetails.setUser(user);
            userDetails.setDayOfBird(userProfile.getBirthday());
            userDetails.setGender(userProfile.getGender());
            userDetails.setFullName(userProfile.getName());
            userDetailsService.save(userDetails);
        }
        SocialLogin socialLogin = new SocialLogin();
        com.nh7.ecommerce.entity.User u = new com.nh7.ecommerce.entity.User();
        u.setPassword(password);
        userService.savePassword(idOfEmail,u);
        UserDto user = userService.findById(idOfEmail);
        socialLogin.setId(user.getId());
        socialLogin.setUsername(user.getUsername());
        socialLogin.setPassword(password);
        socialLogin.setEmail(user.getEmailAddress());
        return socialLogin;
    }


}

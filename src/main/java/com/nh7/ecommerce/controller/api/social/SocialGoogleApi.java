package com.nh7.ecommerce.controller.api.social;

import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.dto.social.SocialLogin;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.model.GoogleDataModel;
import com.nh7.ecommerce.service.UserDetailsService;
import com.nh7.ecommerce.service.UserService;
import com.nh7.ecommerce.util.RandomPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.google.connect.GoogleServiceProvider;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.DocFlavor;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/login/google")

public class SocialGoogleApi {
    private String clientId="860742485333-su2uj1cs46lq74014htl0amd7arc6gcs.apps.googleusercontent.com";

    private String clientSecret="xPYxvPzwqf-zhlnPI1hXKNLP";

    private String redirect="https://shopee-ecommerce-nh7.herokuapp.com/api/login/google/redirect";

    @Autowired
    private UserService userService;
    @Autowired
    private RandomPasswordUtil randomPasswordUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    private GoogleConnectionFactory factory = new GoogleConnectionFactory(clientId,clientSecret);
    private GoogleServiceProvider provider = new GoogleServiceProvider(clientId,clientSecret);
    @GetMapping
    public RedirectView toGoogle(){
        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("openid profile email");
        parameters.setRedirectUri(redirect);
        String url=operations.buildAuthenticateUrl(parameters);
        return new RedirectView(url);
    }
    @GetMapping("/redirect")
    public RedirectView producer(@RequestParam("code") String authorizationCode,@RequestParam("scope") String scope){
        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode,redirect,null);
        String getData = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken.getAccessToken();
        return new RedirectView(getData);
    }
    @PostMapping
    public SocialLogin saveGoogleData(@RequestBody GoogleDataModel googleDataModel){
        Long idOfEmail = userService.findIdByEmailAddressAndAuthProvider(googleDataModel.getEmail(),AuthProviderEnum.GOOGLE_USER);
        String password =randomPasswordUtil.rand();
        if(idOfEmail == null){
            // USER
            User userEntity = new User();
            userEntity.setEmailAddress(googleDataModel.getEmail());
            userEntity.setPassword(password);
            userEntity.setAuthProvider(AuthProviderEnum.GOOGLE_USER);
            userEntity.setAvatar(googleDataModel.getPicture());
            User user = userService.save(userEntity);
            idOfEmail = user.getId();

            //USER DETAIL
            UserDetails userDetails = new UserDetails();
            userDetails.setUser(user);
            userDetails.setFullName(googleDataModel.getFamily_name()+" "+googleDataModel.getGiven_name());
            userDetailsService.save(userDetails);
        }
        SocialLogin socialLogin = new SocialLogin();
        UserDto user = userService.findById(idOfEmail);
        User u = new User();
        u.setPassword(password);
        userService.savePassword(idOfEmail,u);
        socialLogin.setId(idOfEmail);
        socialLogin.setUsername(user.getUsername());
        socialLogin.setPassword(password);
        socialLogin.setEmail(user.getEmailAddress());
        return socialLogin;
    }


}

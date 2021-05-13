package com.nh7.ecommerce.dto.social;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialLogin {
    private Long id;
    private String username;
    private String password;
    private String email;
}

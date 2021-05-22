package com.nh7.ecommerce.dto;


import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String emailAddress;
    private AuthProviderEnum authProvider;
    private List<String> roleNames;
}

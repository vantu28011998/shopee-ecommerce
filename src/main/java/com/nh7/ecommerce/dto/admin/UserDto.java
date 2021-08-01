package com.nh7.ecommerce.dto.admin;

import com.nh7.ecommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String avatarUrl;
    private List<Role> roles;
    private boolean status;
}

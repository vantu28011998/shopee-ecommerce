package com.nh7.ecommerce.dto.admin;

import com.nh7.ecommerce.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String shopName;
    private String dayOfBirth;
    private List<Map<String, Object>> roles = new ArrayList<>();
    private boolean status;
}

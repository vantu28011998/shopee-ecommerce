package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private long id;
    private String avatar;
    private LocalDateTime createdAt;
    private String userName;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String gender;
    private String dateofbirth;
}

package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRoleDto {
    private Long userId;
    private List<Long> roleIds;
    private List<String> roleNames;
}

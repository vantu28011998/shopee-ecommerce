package com.nh7.ecommerce.dto;

import com.nh7.ecommerce.dto.developer.PermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDto {
    private Long id;
    private String roleName;
    private List<PermissionDto> permissions;
}

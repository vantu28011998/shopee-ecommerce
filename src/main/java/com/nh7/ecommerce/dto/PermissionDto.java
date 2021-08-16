package com.nh7.ecommerce.dto;

import com.nh7.ecommerce.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto{
    private Long id;
    private String permissionName;
    private String description;
    private Long actionId;
    private String actionName;
    private Long functionId;
    private String functionName;
}

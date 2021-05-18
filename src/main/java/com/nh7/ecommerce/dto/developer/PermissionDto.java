package com.nh7.ecommerce.dto.developer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDto{
    private Long id;
    private String permissionName;
    private String description;
    private Long actionId;
    private String actionName;
    private Long functionId;
    private String functionName;
}

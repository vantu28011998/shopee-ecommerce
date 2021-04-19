package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="permission")
@Getter
@Setter
public class Permission extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String permissionName;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles=new ArrayList<>();

    public String getPermissionName() {
        return permissionName;
    }
}

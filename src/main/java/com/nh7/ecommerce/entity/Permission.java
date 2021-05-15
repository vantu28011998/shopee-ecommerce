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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String permissionName;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles=new ArrayList<>();
    @Column
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private Action action;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "func_id")
    private Func func;
    public String getPermissionName() {
        return permissionName;
    }
}

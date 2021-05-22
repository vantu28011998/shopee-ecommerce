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
    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    private List<Role> roles=new ArrayList<>()  ;
    @Column
    private String description;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private Action action;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_id")
    private Func func;
}

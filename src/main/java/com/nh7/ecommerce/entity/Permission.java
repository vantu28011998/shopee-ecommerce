package com.nh7.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    private List<Role> roles=new ArrayList<>();
    @Column
    private String description;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "action_id")
    private Action action;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "function_id")
    private Func func;
}

package com.nh7.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
@Getter
@Setter
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String roleName;
    @ManyToMany
    @JoinTable(name="role_permission",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id")
    )
    private List<Permission> permissions=new ArrayList<>();
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users=new ArrayList<>();
}

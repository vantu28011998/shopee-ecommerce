package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="action")
@Getter
@Setter
public class Action{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToOne(mappedBy = "action", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Permission permission;
}

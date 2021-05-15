package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="func")
@Getter
@Setter
public class Func {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "func", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Permission permission;
    @Column
    private String name;
    @Column
    private String url;
}

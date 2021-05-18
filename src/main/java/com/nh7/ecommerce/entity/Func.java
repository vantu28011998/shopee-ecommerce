package com.nh7.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "id")
    private Long id;
    @OneToOne(mappedBy = "func",cascade = CascadeType.ALL)
    private Permission permission;
    @Column
    private String name;
    @Column
    private String url;
}

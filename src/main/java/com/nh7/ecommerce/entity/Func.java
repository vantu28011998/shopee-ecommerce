package com.nh7.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonBackReference
    @OneToOne(mappedBy = "func",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Permission permission;
    @Column
    private String name;
    @Column
    private String url;
}

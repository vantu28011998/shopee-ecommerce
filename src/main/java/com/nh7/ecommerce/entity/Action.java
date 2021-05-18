package com.nh7.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="action")
@Getter
@Setter
public class Action extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String name;
    @OneToOne(mappedBy = "action",cascade = CascadeType.ALL)
    @JsonBackReference
    private Permission permission;
}

package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="shop")
@Getter
@Setter
public class Shop extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userId;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String logo;
    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private User user;
}

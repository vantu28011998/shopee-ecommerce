package com.nh7.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class BillingInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private String shipAddress;

    @OneToOne
    @JoinColumn(name = "order_id")
    private UserOrder order;
}

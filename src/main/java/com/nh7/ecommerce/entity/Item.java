package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(name = "item_quantity")
    private int quantity;
    @Column(name = "item_price")
    private Double itemPrice;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

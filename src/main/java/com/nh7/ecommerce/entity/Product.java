package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name="product_name")
    private String productName;
    @Column(name="product_thumbnail")
    private String productThumbnail;
    @Column(name = "product_Price")
    private Double productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    private Category category;
    @OneToOne(mappedBy = "product")
    private Item item;

}

package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "avg_evalute")
    private Double avgRating;

    @Column(name="product_thumbnail")
    private String productThumbnail;

    @Column(name = "product_Price",precision=10, scale=2)
    private Double productPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Item> itemList;

    @OneToOne(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Post post;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @Column
    private int discount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    private List<Rating> ratings;
}

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
    @Column(name="product_thumbnail")
    private String productThumbnail;
    @Column(name = "product_Price")
    private Double productPrice;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Item> itemList;
    @OneToOne(mappedBy = "product")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @Setter
    private Category category;
}

package com.nh7.ecommerce.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_thumbnail")
    private String categoryThumbnail;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}

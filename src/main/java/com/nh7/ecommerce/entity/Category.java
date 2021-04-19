package com.nh7.ecommerce.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_thumbnail  ")
    private String categoryThumbnail;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> productList;
}

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_thumbnail  ")
    private String categoryThumbnail;
<<<<<<< HEAD
    @OneToMany(cascade = CascadeType.ALL)
=======
    @OneToMany(mappedBy = "category")
>>>>>>> 736d7b8ec0d885ce77570eeff41e7733b8d014fb
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<Product> productList;
}

package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class SubCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column
    private String subCategoryName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
    private List<Product> productList;
}

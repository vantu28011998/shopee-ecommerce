package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Service
public class SubCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column
    private String subCategoryName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne(mappedBy = "subCategory")
    private Product product;
}

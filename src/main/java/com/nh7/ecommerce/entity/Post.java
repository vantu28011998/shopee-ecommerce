package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "post_title")
    private String postTitle;
    @Lob
    @Column(name = "post_decription")
    private String postDecription;
    @Column(name = "sold_quantity", columnDefinition = "integer default 0")
    private int soldQuantity;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_decription")
    private String postDecription;
    @Column(name = "avg_evalute")
    private Double avgEvalute;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private List<Comment> commentList;
}

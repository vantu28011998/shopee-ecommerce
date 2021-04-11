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
    @Column(name = "post_decription")
    private String postDecription;
    @Column(name = "avg_evalute")
    private Double avgEvalute;
    @OneToOne
    @JoinColumn(name = "produdct_id")
    private Product product;
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

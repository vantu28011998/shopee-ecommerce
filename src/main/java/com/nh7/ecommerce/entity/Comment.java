package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="comment")
@Getter
@Setter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Column
    private Integer evalute;
<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
=======
>>>>>>> 736d7b8ec0d885ce77570eeff41e7733b8d014fb
}

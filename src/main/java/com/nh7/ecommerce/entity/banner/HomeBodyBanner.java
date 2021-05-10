package com.nh7.ecommerce.entity.banner;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class HomeBodyBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column
    private String thumbnail;
    @Column
    private String title;
}

package com.nh7.ecommerce.entity;

import com.nh7.ecommerce.enums.BannerPosition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="banner")
@Getter
@Setter
public class Banner extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private BannerPosition position;
}

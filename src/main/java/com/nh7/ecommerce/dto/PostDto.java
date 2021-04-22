package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String postDecription;
    private Double avgEvalute;
    @Column(name = "sold_quantity", columnDefinition = "integer default 0")
    private int soldQuantity;
}

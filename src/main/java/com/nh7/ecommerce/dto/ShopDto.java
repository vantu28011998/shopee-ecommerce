package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ShopDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String logo;
}

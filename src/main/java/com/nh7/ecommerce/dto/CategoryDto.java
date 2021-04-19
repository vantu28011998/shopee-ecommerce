package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String categoryThumbnail;
}

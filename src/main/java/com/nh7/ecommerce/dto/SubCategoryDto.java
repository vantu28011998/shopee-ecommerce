package com.nh7.ecommerce.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryDto {
    private Long id;
    private String subCategoryName;
    private Long categoryId;
}

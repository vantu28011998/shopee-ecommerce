package com.nh7.ecommerce.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String postDescription;
    private int soldQuantity;
    private int status;
    private ProductDto productDto;
    private String createBy;
    private LocalDateTime createAt;
}

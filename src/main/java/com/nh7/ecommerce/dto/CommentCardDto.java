package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCardDto {
    private String content;
    private int evalute;
    private String userName;
    private String userAvatar;
}

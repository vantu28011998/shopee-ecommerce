package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUDto {
    private Double orderPrice;
    private List<ItemUDto> itemList;
    private String receiverName;
    private String phoneNumber;
    private String shipAddress;
}

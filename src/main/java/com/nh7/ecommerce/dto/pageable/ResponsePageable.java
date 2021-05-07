package com.nh7.ecommerce.dto.pageable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePageable<T>{
    private Pagination pagination;
    private List<T> items;
}

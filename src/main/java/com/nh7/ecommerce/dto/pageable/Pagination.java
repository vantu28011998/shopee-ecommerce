package com.nh7.ecommerce.dto.pageable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int page;
    private int limit;
    private int totalRow;
}

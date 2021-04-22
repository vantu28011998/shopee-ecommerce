package com.nh7.ecommerce.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGetBySuperApi<D>{
    ResponseEntity<List<D>> getAllBySuper(Long superId);
    ResponseEntity<D> getBySuper(Long superId,Long id);
}

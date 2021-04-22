package com.nh7.ecommerce.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPostBySuperApi<E> {
    ResponseEntity<Object> postBySuper(Long id,E item);
    ResponseEntity<Object> postAllBySuper(Long id,List<E> items);
}

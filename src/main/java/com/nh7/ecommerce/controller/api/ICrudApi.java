package com.nh7.ecommerce.controller.api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface ICrudApi<D,E> {

    //----------GET METHOD---------//

    ResponseEntity<List<D>> getAll();
    ResponseEntity<D> get(Long id);

    //----------POST METHOD---------//

    ResponseEntity<Object> createAll(List<E> items);
    ResponseEntity<Object> create(E item);

    //----------PUT METHOD---------//

    ResponseEntity<Object> update(Long id,E item);

    //----------DELETE METHOD---------//

    ResponseEntity<Object> deleteAll();
    ResponseEntity<Object> delete(Long id);

}

package com.nh7.ecommerce.util;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@NoArgsConstructor
public class ModelMapperUtil<E,D> extends ModelMapper {

    public List<D> mapList(List<E> entityList, Class<D> dtoClassType){
        List<D> dtoList=new ArrayList<>();
        for(E entity : entityList){
            dtoList.add(map(entity,dtoClassType));
        }
        return dtoList;
    }
}

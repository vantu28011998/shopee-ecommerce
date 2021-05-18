package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.developer.FunctionDto;
import com.nh7.ecommerce.entity.Action;
import com.nh7.ecommerce.entity.Func;
import com.nh7.ecommerce.repository.ActionRepository;
import com.nh7.ecommerce.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionService {
    @Autowired
    private FunctionRepository functionRepository;
    public void saveAll(List<FunctionDto> functionDtos){
        List<Func> funcs = new ArrayList<>();
        for(FunctionDto functionDto : functionDtos){
            Func func = new Func();
            func.setName(functionDto.getName());
            func.setUrl(functionDto.getUrl());
            funcs.add(func);
        }
        functionRepository.saveAll(funcs);
    }
    public List<Func> findAll(){
        return  (List<Func>)functionRepository.findAll();
    }
}

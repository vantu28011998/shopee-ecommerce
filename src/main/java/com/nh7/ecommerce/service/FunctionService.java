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
    public void saveAll(List<Func> funcs){
        functionRepository.saveAll(funcs);
    }
    public List<FunctionDto> findAll(){
        List<FunctionDto> functionDtos = new ArrayList<>();
        List<Func> funcs = (List<Func>)functionRepository.findAll();
        for (Func func : funcs){
            FunctionDto functionDto = new FunctionDto();
            functionDto.setId(func.getId());
            functionDto.setName(func.getName());
            functionDto.setUrl(func.getUrl());
            functionDtos.add(functionDto);
        }
        return  functionDtos;
    }
}

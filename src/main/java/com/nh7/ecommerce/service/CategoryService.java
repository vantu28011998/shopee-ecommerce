package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.repository.CategoryRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<CategoryDto> findAll(){
        List<Category> categoryList= categoryRepository.findAll();
        return modelMapperUtil.mapList(categoryList,CategoryDto.class);
    }
    public List<Category> findByName(String var){return categoryRepository.findByCategoryName(var);}
}

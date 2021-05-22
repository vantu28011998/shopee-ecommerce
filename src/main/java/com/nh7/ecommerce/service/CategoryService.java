package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.repository.CategoryRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CategoryDto getById(long id) {
        Category category = categoryRepository.findById(id);
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setId(id);
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setCategoryThumbnail(category.getCategoryThumbnail());
        return categoryDTO;
    }

    public List<Category> findByName(String var){return categoryRepository.findByCategoryName(var);}
    //
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> saveAll(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories(){
        categoryRepository.deleteAll();
    }
}

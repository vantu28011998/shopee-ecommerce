package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.repository.CategoryRepository;
import com.nh7.ecommerce.repository.SubCategoryRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public List<SubCategoryDto> findAll(){
        List<SubCategory> subcategories = (List<SubCategory>)subCategoryRepository.findAll();
        List<SubCategoryDto> subcategoryDtos = new ArrayList<>();
        for(SubCategory subCategory : subcategories){
            SubCategoryDto subcategoryDto = new SubCategoryDto();
            subcategoryDto.setCategoryId(subCategory.getId());
            subcategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
            subcategoryDto.setCategoryId(subCategory.getCategory().getId());
            subcategoryDto.setCategoryName(subCategory.getCategory().getCategoryName());
            subcategoryDtos.add(subcategoryDto);
        }
        return  subcategoryDtos;
    }

    public SubCategoryDto findById(long id){
        SubCategory subCategory = subCategoryRepository.findById(id);
        return modelMapperUtil.map(subCategory,SubCategoryDto.class);
    }

    public boolean saveAll(List<SubCategory> subCategories){
        try {
            subCategoryRepository.saveAll(subCategories);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public SubCategory save(SubCategory subCategory){
        return subCategoryRepository.save(subCategory);
    }

    public void deleteAll(){
        subCategoryRepository.deleteAll();
    }

    public void delete(Long id){
        subCategoryRepository.deleteById(id);
    }

    public List<SubCategoryDto> findAllByCategoryId(long id) {

        List<SubCategoryDto> subCategoryDtos =new ArrayList<>();
        List<SubCategory> subCategories= subCategoryRepository.findAllByCategoryId(id);

        if(subCategories!=null){
            for(SubCategory subCategory :subCategories){
                SubCategoryDto subCategoryDto =new SubCategoryDto();
                subCategoryDto.setId(subCategory.getId());
                subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
                subCategoryDto.setCategoryId(subCategory.getCategory().getId());
                subCategoryDto.setCategoryName(subCategory.getCategory().getCategoryName());
                subCategoryDtos.add(subCategoryDto);
            }
        }
        return subCategoryDtos;
    }
}

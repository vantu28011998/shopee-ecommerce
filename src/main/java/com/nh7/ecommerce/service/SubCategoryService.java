package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.repository.SubCategoryRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public List<SubCategoryDto> findAll(){
        List<SubCategory> categories = (List<SubCategory>)subCategoryRepository.findAll();
        return modelMapperUtil.mapList(categories,SubCategoryDto.class);
    }
    public SubCategoryDto findById(long id){
        SubCategory subCategory = subCategoryRepository.findById(id);
        return modelMapperUtil.map(subCategory,SubCategoryDto.class);
    }

    public List<SubCategory> saveAll(List<SubCategory> subCategories){
        return (List<SubCategory>) subCategoryRepository.saveAll(subCategories);
    }

    public SubCategory save(SubCategory subCategory){
        return subCategoryRepository.save(subCategory);
    }

    public void saveBySuper(Long id,SubCategory subCategory){
        subCategoryRepository.saveBySuper(id,subCategory.getSubCategoryName());
    }

    public void saveAllBySuper(Long id,List<SubCategory> subCategories){
        for(SubCategory subCategory: subCategories){
           saveBySuper(id,subCategory);
        }
    }

    public void deleteAll(){
        subCategoryRepository.deleteAll();
    }

    public void delete(Long id){
        subCategoryRepository.deleteById(id);
    }

}

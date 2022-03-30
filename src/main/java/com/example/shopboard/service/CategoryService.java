package com.example.shopboard.service;

import com.example.shopboard.dto.CategoryDto;
import com.example.shopboard.entity.CategoryEntity;
import com.example.shopboard.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CategoryDto dto){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(dto.getName());
        categoryEntity = this.categoryRepository.save(categoryEntity);
        return new CategoryDto(categoryEntity);
    }

    public CategoryDto readCategory(Long id){
        Optional<CategoryEntity> categoryDtoOptional = this.categoryRepository.findById(id);
        if (categoryDtoOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return new CategoryDto(categoryDtoOptional.get());
    }

    public List<CategoryDto> readAllCategory(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        this.categoryRepository.findAll().forEach(
                categoryEntity -> categoryDtoList.add(
                        new CategoryDto(categoryEntity)));
        return categoryDtoList;
    }




}

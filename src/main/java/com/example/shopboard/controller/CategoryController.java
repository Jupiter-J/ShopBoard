package com.example.shopboard.controller;

import com.example.shopboard.dto.CategoryDto;
import com.example.shopboard.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(this.categoryService.createCategory(categoryDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> readCategory(@PathParam("id") Long id){
        return ResponseEntity.ok(this.categoryService.readCategory(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> readAllCategory(){
        return ResponseEntity.ok(this.categoryService.readAllCategory());
    }



}

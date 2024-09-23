package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
@Autowired
    private CategoryService categoryService;

    //CREATE
    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto createCategory= this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    //UPDATE

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")Integer CategoryId)
    {
        CategoryDto updatedCategory =this.categoryService.updateCategory(categoryDto,CategoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
    }



    //DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category is deleted successfully !!!", false), HttpStatus.OK);
    }


    //GET Single

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllCategory(){
        List<CategoryDto>categoryDtoList=this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtoList);
    }


    //GET BY ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getSingleUser(@PathVariable Integer categoryId) {
CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);

    }


}

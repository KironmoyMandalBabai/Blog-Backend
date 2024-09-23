package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //  CREATE
    CategoryDto createCategory (CategoryDto categoryDto);

    //UPDATE
    CategoryDto updateCategory (CategoryDto categoryDto,Integer categoryId);

    //DELETE
   void deleteCategory (Integer CategoryId);

    //GET
    CategoryDto getCategory (Integer categoryId);

    //GETALL
    List<CategoryDto > getAllCategory();

}

package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService {
@Autowired
    private CategoryRepo categoryRepo;
@Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
             Category cat =this.modelMapper.map(categoryDto,Category.class);
            Category addcat =this.categoryRepo.save(cat);
            return this.modelMapper.map(addcat,CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

            Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
            cat.setCategoryTitle(categoryDto.getCategoryTitle());
            cat.setCategoryDescription(categoryDto.getCategoryDescription());
            Category updatedcat =this.categoryRepo.save(cat);

            return this.modelMapper.map(updatedcat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        this.categoryRepo.delete(cat);


    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
return this.modelMapper.map(cat,CategoryDto.class);


    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category>categories =this.categoryRepo.findAll();
        List<CategoryDto>catDtos = categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(java.util.stream.Collectors.toList());

        return catDtos;
    }
}

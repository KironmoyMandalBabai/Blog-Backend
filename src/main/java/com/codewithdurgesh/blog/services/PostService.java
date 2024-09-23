package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto ,Integer postId);

    void deletePost(Integer postId);


    Post getPostBy(Integer postId);

    List<PostDto>getAllPost(Integer pageNumber,Integer pageSize );

    PostDto getPostById(Integer postId);

    List<PostDto>getPostByCategory(Integer categoryId);


    List<PostDto>getPostByUser(Integer userId);
    List<Post>searchPosts(String keyword);


}

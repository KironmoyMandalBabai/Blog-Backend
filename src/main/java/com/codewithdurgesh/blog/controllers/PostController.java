package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
//CREATE
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto>createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){
        PostDto createPost= this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }
    //Get By User
    @GetMapping("/user/{userId}/posts")

    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);  // Wrap the list with ResponseEntity
    }

    //Get By Category--------
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto>posts =this.postService.getPostByCategory(categoryId);
        return  new ResponseEntity<>(posts,HttpStatus.OK);

    }

    //Get By Post id-----
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId) {
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);

    }


    // Get All Posts------
    @GetMapping("/posts/")
    public ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "13",required = false) Integer pageSize

    ) {
        List<PostDto> allPosts = this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
    }

//Delete -------
@DeleteMapping("/posts/{postId}")
public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("postId") Integer postId) {
    this.postService.deletePost(postId);
    return new ResponseEntity<>(new ApiResponse("Post is deleted successfully !!!", false), HttpStatus.OK);
}

//Update Post
@PutMapping("/posts/{postId}")
public ResponseEntity<PostDto>updateCategory(@Valid @RequestBody PostDto postDto, @PathVariable("postId")Integer postId)
    {
        PostDto updatedPost =this.postService.updatePost(postDto,postId);
         return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }




}

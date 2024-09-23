package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;





import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

@Autowired
private PostRepo postRepo;
@Autowired
private UserRepo userRepo;
@Autowired
private CategoryRepo categoryRepo;
@Autowired
private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto ,Integer userId, Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User" ,"user Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));

        Post post =this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost=this.postRepo.save(post);
        // Map the saved Post entity back to PostDto
        PostDto newPostDto = this.modelMapper.map(newPost, PostDto.class);
        newPostDto.setUser(this.modelMapper.map(user, UserDto.class));
        newPostDto.setCategory(this.modelMapper.map(category, CategoryDto.class));
        //populate the UserDto and CategoryDto fields in PostDto
        return newPostDto;


    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        post.setImageName(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        Post updatedPost =this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);


    }

    @Override
    public void deletePost(Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        this.postRepo.delete(post);

    }

    @Override
    public Post getPostBy(Integer postId) {
        return null;
    }


    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {

        Pageable pageable=  PageRequest.of(pageNumber,pageSize);

        Page<Post> pagePost=this.postRepo.findAll(pageable);
        List<Post>allPosts=pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }


    @Override
    public PostDto getPostById(Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        return this.modelMapper.map(post,PostDto.class);


    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);

        List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));
        List<Post>posts =this.postRepo.findByUser(user);
        List<PostDto>postDtos=posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}

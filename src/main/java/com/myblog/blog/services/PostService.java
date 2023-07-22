package com.myblog.blog.services;

import com.myblog.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto, Integer postId);

    //delete

    void deletePost(Integer postId);

    //get all posts

    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);

    //get single post

    PostDto getPostById(Integer postId);
    //get all posts by category

    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user

    List<PostDto> getPostsByuser(Integer userId);
}

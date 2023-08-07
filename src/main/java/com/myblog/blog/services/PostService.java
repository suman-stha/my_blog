package com.myblog.blog.services;

import com.myblog.blog.payloads.PostDto;
import com.myblog.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    //update

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //delete

    PostDto updatePost(PostDto postDto, Integer postId);

    //get all posts

    void deletePost(Integer postId);

    //get single post

    PostDto getPostById(Integer postId);
    //get all posts by category

    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user

    List<PostDto> getPostsByuser(Integer userId);
}

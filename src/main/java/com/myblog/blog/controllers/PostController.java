package com.myblog.blog.controllers;

import com.myblog.blog.payloads.PostDto;
import com.myblog.blog.payloads.PostResponse;
import com.myblog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;

    // create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostsByuser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPOstById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    // delete posts
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);

    }
}

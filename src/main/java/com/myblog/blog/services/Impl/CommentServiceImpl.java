package com.myblog.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.blog.entities.Comment;
import com.myblog.blog.entities.Post;
import com.myblog.blog.exceptions.ResourceNotFoundException;
import com.myblog.blog.payloads.CommentDto;
import com.myblog.blog.repositories.CommentRepo;
import com.myblog.blog.repositories.PostRepo;
import com.myblog.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer commentId) {
        Post post = this.postRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", commentId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(com);
    }

}

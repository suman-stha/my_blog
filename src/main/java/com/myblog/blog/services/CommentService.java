package com.myblog.blog.services;

import com.myblog.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer commentId);

    void deleteComment(Integer commentId);

}

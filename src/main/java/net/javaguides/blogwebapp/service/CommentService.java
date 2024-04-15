package net.javaguides.blogwebapp.service;

import net.javaguides.blogwebapp.dto.CommentDto;

import java.util.List;

/**
 * @className: CommentService
 * @author: ChelsyLan
 * @description: TODO
 */
public interface CommentService {
    List<CommentDto> findAllComments();

    void createComment(String postUrl,CommentDto commentDto);

    void deleteComment(Long commentId);

    List<CommentDto> findCommentsByPost();



}

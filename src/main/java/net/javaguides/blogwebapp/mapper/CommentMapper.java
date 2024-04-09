package net.javaguides.blogwebapp.mapper;

import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.entity.Comment;
import net.javaguides.blogwebapp.entity.Post;

public class CommentMapper {

    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder().
                id(comment.getId()).
                email(comment.getEmail()).
                name(comment.getName()).
                content(comment.getContent()).
                createdOn(comment.getCreatedOn()).
                updatedOn(comment.getUpdatedOn()).build();
    }

    public static Comment mapToComment(CommentDto comment){
        return Comment.builder().
                id(comment.getId()).
                email(comment.getEmail()).
                name(comment.getName()).
                content(comment.getContent()).
                createdOn(comment.getCreatedOn()).
                updatedOn(comment.getUpdatedOn()).build();
    }


}

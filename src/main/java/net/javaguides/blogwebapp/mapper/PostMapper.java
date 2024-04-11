package net.javaguides.blogwebapp.mapper;

//map between Post entity and PostDto


import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.entity.*;

import java.util.stream.Collectors;

public class PostMapper {

    /*
     * @param post:
     * @return PostDto
            * @description ： for data mapping class, we don't need instance, just need its method,
            * therefore it's better to use static method，like return PostMapper.mapToPostDto(post);
     */
    public static PostDto mapToPostDto(Post post){
        return PostDto.builder().
                id(post.getId()).
                title(post.getTitle()).
                url(post.getUrl()).
                content(post.getContent()).
                shortDescription(post.getShortDescription()).
                createdOn(post.getCreatedOn()).
                comments(post.getComments().stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toSet())).
                updatedOn(post.getUpdatedOn()).build();
    }


    public static Post mapToPostEntity(PostDto postDto){
        return Post.builder().
                id(postDto.getId()).
                url(postDto.getUrl()).
                content(postDto.getContent()).
                shortDescription(postDto.getShortDescription()).
                title(postDto.getTitle()).
                comments(postDto.getComments().stream().map(CommentMapper::mapToComment).collect(Collectors.toSet())).
                createdOn(postDto.getCreatedOn()).
                updatedOn(postDto.getUpdatedOn()).build();
    }

}

package net.javaguides.blogwebapp.service;

import net.javaguides.blogwebapp.dto.PostDto;

import java.util.List;

/**
 * @className: PostService
 * @author: ChelsyLan
 * @description: TODO
 */
public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPosyById(long postId);

    void updatePost(PostDto postDto);

    void deletePost(long postId);

    PostDto findPostByUrl(String url);

    List<PostDto> searchPosts(String query);



}

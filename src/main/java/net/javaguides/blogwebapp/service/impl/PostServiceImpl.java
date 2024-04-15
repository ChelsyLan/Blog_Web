package net.javaguides.blogwebapp.service.impl;

import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.entity.Post;
import net.javaguides.blogwebapp.entity.User;
import net.javaguides.blogwebapp.mapper.PostMapper;
import net.javaguides.blogwebapp.repository.PostRepository;
import net.javaguides.blogwebapp.repository.UserRepository;
import net.javaguides.blogwebapp.service.PostService;
import net.javaguides.blogwebapp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts =  postRepository.findAll();
        return posts.stream().map((PostMapper::mapToPostDto)).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPostEntity(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPosyById(long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post=PostMapper.mapToPostEntity(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);

    }

    @Override
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String url) {
        return PostMapper.mapToPostDto(postRepository.findByUrl(url).get());
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        return postRepository.searchPosts(query).
                stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostsByUser(userId);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }


}

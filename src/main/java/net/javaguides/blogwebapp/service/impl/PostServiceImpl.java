package net.javaguides.blogwebapp.service.impl;

import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.entity.Post;
import net.javaguides.blogwebapp.mapper.PostMapper;
import net.javaguides.blogwebapp.repository.PostRepository;
import net.javaguides.blogwebapp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts =  postRepository.findAll();
        return posts.stream().map((PostMapper::mapToPostDto)).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPostEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPosyById(long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post=PostMapper.mapToPostEntity(postDto);
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


}

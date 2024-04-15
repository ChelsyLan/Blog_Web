package net.javaguides.blogwebapp.service.impl;

import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.entity.Comment;
import net.javaguides.blogwebapp.entity.Post;
import net.javaguides.blogwebapp.entity.User;
import net.javaguides.blogwebapp.mapper.CommentMapper;
import net.javaguides.blogwebapp.repository.CommentRepository;
import net.javaguides.blogwebapp.repository.PostRepository;
import net.javaguides.blogwebapp.repository.UserRepository;
import net.javaguides.blogwebapp.service.CommentService;
import net.javaguides.blogwebapp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);

    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        return commentRepository.findCommentsByPost(user.getId()).stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());

    }
}

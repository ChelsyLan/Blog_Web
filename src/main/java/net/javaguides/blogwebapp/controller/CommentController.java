package net.javaguides.blogwebapp.controller;

import jakarta.validation.Valid;
import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.service.CommentService;
import net.javaguides.blogwebapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;
//    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid @ModelAttribute("comment")CommentDto commentDto,
                                BindingResult result,
                                Model model){
        // for debugging
//        log.info("Received comment: Name: {}, Email: {}, Content: {}", commentDto.getName(), commentDto.getEmail(), commentDto.getContent());

        PostDto postDto = postService.findPostByUrl(postUrl);
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            model.addAttribute("comment",commentDto);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl,commentDto);
        return "redirect:/post/"+postUrl;
    }
}

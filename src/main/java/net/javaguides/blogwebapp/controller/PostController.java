package net.javaguides.blogwebapp.controller;

import jakarta.validation.Valid;
import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.service.CommentService;
import net.javaguides.blogwebapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;//use interface for injecting dependency for loose coupling
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;this.commentService= commentService;
    }

    //private static final Logger logger = LoggerFactory.getLogger(PostController.class); for debug


    //create handler method,handle GET request,return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts",posts);//use the key to get value in thymeleaf template
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPost(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post",postDto);
        return "/admin/create_post";
    }

    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments",comments);
        return "/admin/comments";

    }

    //handler to handle form submit request
    @PostMapping("/admin/posts")
    // annotation to read form data and set values to the fields of model object
    //validation: When using the @Valid (or @Validated) annotation to trigger Spring's verification mechanism, the parameter that follows must be a BindingResult。
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result,Model model){
        postDto.setUrl(getUrl(postDto.getTitle()));
        if(result.hasErrors()){
            //preserve the data when some columns are empty so that users don't need to enter again
            model.addAttribute("post",postDto);
            return "/admin/create_post";
        }
        postService.createPost(postDto);

        return "redirect:/admin/posts";

    }

    //handler to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId")long postId,Model model){
        PostDto postDto = postService.findPosyById(postId);
        model.addAttribute("post",postDto);
        return "/admin/edit_post";

    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post",post);
            return "/admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    //handler to handle DELETE post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";

    }

    //view post
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl")String postUrl,Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "/admin/view_post";
    }

    //handler to handle search blog posts request
    //localhost:8080/admin/post/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query")String query,Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }

    //delete comment request
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId")Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";


    }


    private String getUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+","-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }

}

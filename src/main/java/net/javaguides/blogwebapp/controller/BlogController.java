package net.javaguides.blogwebapp.controller;

import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.dto.PostDto;
import net.javaguides.blogwebapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //handler to handle http://localhost:8080
    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("postResponse",posts);
        return "blog/view_posts";
    }

    //handler to handle "Read more"
    @GetMapping("/post/{postUrl}")
    ///@PathVariable catches postUrl in post/{postUrl} as parameter
    private String showPost(@PathVariable("postUrl") String postUrl,Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        CommentDto comment = new CommentDto();
        model.addAttribute("post",post);
        model.addAttribute("comment",comment);
        return "blog/blog_post";
    }

    // //localhost:8080/admin/post/search?query=java->@RequestParam(value = "query")String query
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query")String query,Model model){
        List<PostDto> postResponse = postService.searchPosts(query);
        model.addAttribute("postResponse",postResponse);
        return "blog/view_posts";

    }




}

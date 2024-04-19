package com.dprsnn.UtilPlast.controllers;

import com.dprsnn.UtilPlast.models.BlogPost;
import com.dprsnn.UtilPlast.services.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogPostService blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @GetMapping("")
    public String getBlogPage(Model model){
        model.addAttribute("blog_post", blogPostService.list());
        return "blog";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Long id, Model model){
        BlogPost post = blogPostService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("images", post.otherImages());
        return "post";
    }

}

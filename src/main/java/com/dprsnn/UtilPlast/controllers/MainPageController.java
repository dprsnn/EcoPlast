package com.dprsnn.UtilPlast.controllers;

import com.dprsnn.UtilPlast.services.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final BlogPostService blogPostService;

    public MainPageController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("posts", blogPostService.getThreePosts());
        return "index";
    }
}

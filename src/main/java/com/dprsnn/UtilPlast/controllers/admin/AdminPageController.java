package com.dprsnn.UtilPlast.controllers.admin;

import com.dprsnn.UtilPlast.models.BlogPost;
import com.dprsnn.UtilPlast.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminPageController {

    private final BlogPostService blogPostService;


    @GetMapping()
    public String adminPage(Model model){
        model.addAttribute("post", new BlogPost());
        model.addAttribute("blog_post", blogPostService.list());
        return "admin/admin";
    }
}

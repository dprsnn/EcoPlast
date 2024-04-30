package com.dprsnn.UtilPlast.controllers.admin;

import com.dprsnn.UtilPlast.models.BlogPost;
import com.dprsnn.UtilPlast.models.PlasticCategory;
import com.dprsnn.UtilPlast.models.PointAddress;
import com.dprsnn.UtilPlast.services.AddressService;
import com.dprsnn.UtilPlast.services.BlogPostService;
import com.dprsnn.UtilPlast.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

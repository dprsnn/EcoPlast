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
    private final AddressService addressService;
    private final CategoryService categoryService;


    @GetMapping()
    public String adminPage(Model model){
        model.addAttribute("post", new BlogPost());
        model.addAttribute("blog_post", blogPostService.list());
        return "admin/admin";
    }

    @GetMapping("/plastic")
    public String plasticPage(Model model){
        model.addAttribute("addresses", addressService.adressList());
        model.addAttribute("categories", categoryService.categoryList());
        return "admin/plastic";
    }

    @PostMapping("/plastic/add_address")
    public String addAddress(@RequestParam("address") String address){
        addressService.addAddress(address);
        return "redirect:/admin/plastic";
    }

    @PostMapping("/plastic/add_category")
    public String addCategory(@RequestParam("category") String category){
        categoryService.addCategory(category);
        return "redirect:/admin/plastic";
    }

    @GetMapping("/plastic/delete_category/{id}")
    public String deleteCategoty(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/plastic";
    }

    @GetMapping("/plastic/delete_address/{id}")
    public String deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return "redirect:/admin/plastic";
    }

    @GetMapping("/plastic/edit_category/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/edit_category";
    }
    @PostMapping("/plastic/save_category/{id}")
    public String saveCategory(@PathVariable("id") Long id, @RequestParam("category") String category){
        categoryService.saveCategory(id, category);
        return "redirect:/admin/plastic";
    }
    @GetMapping("/plastic/edit_address/{id}")
    public String editAddress(@PathVariable("id") Long id, Model model){
        model.addAttribute("address", addressService.getAddressById(id));
        return "admin/edit_address";
    }
    @PostMapping("/plastic/save_address/{id}")
    public String saveAddress(@PathVariable("id") Long id, @RequestParam("address") String address){
        addressService.saveAddress(id, address);
        return "redirect:/admin/plastic";
    }
}

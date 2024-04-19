package com.dprsnn.UtilPlast.controllers.admin;

import com.dprsnn.UtilPlast.models.BlogPost;
import com.dprsnn.UtilPlast.models.BlogPostSubtheme;
import com.dprsnn.UtilPlast.models.BlogPostVideo;
import com.dprsnn.UtilPlast.repositories.BlogPostSubthemeRepository;
import com.dprsnn.UtilPlast.repositories.BlogPostVideoRepository;
import com.dprsnn.UtilPlast.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/blog")
public class AdminBlogController {

    private final BlogPostService blogPostService;
    private final BlogPostSubthemeRepository subthemeRepository;
    private final BlogPostVideoRepository videoRepository;

    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return "redirect:/admin";
    }
    @PostMapping("/post/add")
    public String addPost(@RequestParam(name = "previewImage") MultipartFile preview,
                          @ModelAttribute(name = "post") BlogPost blogPost) throws IOException {
        blogPostService.savePost(preview, blogPost);
        return "redirect:/admin";
    }
    @GetMapping("/post/{id}/edit")
    public String editPost(Model model, @PathVariable Long id) {
        BlogPost blogPost = blogPostService.getPostById(id);
        model.addAttribute("post", blogPost);
        model.addAttribute("subtheme", blogPost.getSubthemes());
        model.addAttribute("images", blogPost.otherImages());
        model.addAttribute("videos", blogPost.getVideos());
        model.addAttribute("video1", new BlogPostVideo());
        model.addAttribute("subtheme1", new BlogPostSubtheme());
        return "admin/post_edit";
    }
    @PostMapping("/post/{id}/edit")
    public String saveEditedPost(@PathVariable Long id,
                                 @RequestParam(name = "previewImage", required = false) MultipartFile preview,
                                 @RequestParam(name = "files", required = false) MultipartFile[] multipartFiles,
                                 @ModelAttribute(name = "post") BlogPost blogPost
    ) throws IOException {
        blogPostService.saveEditedPost(id, preview, multipartFiles, blogPost);
        return "redirect:/admin";
    }

    @PostMapping("/post/{id}/edit/add-subtheme")
    public String saveNewSubtheme(@ModelAttribute("subtheme") BlogPostSubtheme subtheme, @PathVariable Long id) {
        BlogPostSubtheme blogPostSubtheme = new BlogPostSubtheme();
        blogPostSubtheme.setBlogPostSubthemeTitle(subtheme.getBlogPostSubthemeTitle());
        blogPostSubtheme.setBlogPostSubthemeText(subtheme.getBlogPostSubthemeText());

        blogPostService.addSubtheme(blogPostSubtheme, id);
        return "redirect:/admin/blog/post/{id}/edit";
    }

    @GetMapping("/post/{id}/edit/subtheme/{sid}/delete")
    public String deleteSubtheme(@PathVariable Long id, @PathVariable Long sid) {
        blogPostService.deleteSubtheme(sid);
        return "redirect:/admin/blog/post/{id}/edit";

    }

    @GetMapping("/post/{id}/edit/subtheme/{sid}/edit")
    public String editSubtheme(Model model, @PathVariable("sid") Long id) {
        BlogPostSubtheme subtheme = subthemeRepository.findBlogPostSubthemeById(id);
        model.addAttribute("subtheme", subtheme);
        return "admin/admin_post-subtheme";
    }

    @PostMapping("/post/{id}/edit/subtheme/{sid}/edit-subtheme")
    public String saveSubtheme(@PathVariable("sid") Long id, @ModelAttribute("subtheme") BlogPostSubtheme subtheme) {
        blogPostService.editSubtheme(id, subtheme);
        return "redirect:/admin/blog/post/{id}/edit";
    }

    // IMAGE *******************************************************

    @GetMapping("/post/{id}/edit/image/{sid}/delete")
    public String deleteImage(@PathVariable Long id, @PathVariable Long sid) {
        blogPostService.deleteImage(sid);
        return "redirect:/admin/blog/post/{id}/edit";
    }

    // VIDEO *******************************************************
    @PostMapping("/post/{id}/edit/add-video")
    public String saveNewVideo(@ModelAttribute("video") BlogPostVideo video, @PathVariable Long id) {
        BlogPostVideo blogPostVideo = new BlogPostVideo();
        blogPostVideo.setBlogPostVideoLink(video.getBlogPostVideoLink());

        blogPostService.addVideo(blogPostVideo, id);
        return "redirect:/admin/blog/post/{id}/edit";
    }

    @GetMapping("/post/{id}/edit/video/{sid}/delete")
    public String deleteVideo(@PathVariable Long id, @PathVariable Long sid) {
        blogPostService.deleteVideo(sid);
        return "redirect:/admin/blog/post/{id}/edit";
    }

    @GetMapping("/post/{id}/edit/video/{sid}/edit")
    public String editVideo(Model model, @PathVariable("sid") Long id) {
        BlogPostVideo video = videoRepository.findBlogPostVideoById(id);
        model.addAttribute("video", video);
        return "admin/admin_post-video";
    }

    @PostMapping("/post/{id}/edit/video/{sid}/edit-video")
    public String saveVideo(@PathVariable("sid") Long id, @ModelAttribute("video") BlogPostVideo video) {
        System.out.println(video.getBlogPostVideoLink());
        blogPostService.editVideo(id, video);
        return "redirect:/admin/blog/post/{id}/edit";
    }
}

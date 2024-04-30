package com.dprsnn.UtilPlast.controllers.admin;

import com.dprsnn.UtilPlast.services.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminRequestController {

    private final RequestService requestService;

    public AdminRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/requests")
    public String getRequests(Model model, @RequestParam(name = "keywords", required = false) String keyword,
                              @RequestParam(name = "status", required = false) String status){

        model.addAttribute("requests", requestService.getAdminRequests(keyword, status));
        return "admin/request";
    }

    @PostMapping("/request/save/{id}")
    public String saveRequest(@PathVariable(name = "id") Long id, @RequestParam (name = "new_status") String status){
        requestService.saveEditedStatus(id, status);
        return "redirect:/admin/requests";
    }
}

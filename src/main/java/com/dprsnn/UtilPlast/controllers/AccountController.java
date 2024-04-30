package com.dprsnn.UtilPlast.controllers;

import com.dprsnn.UtilPlast.repositories.PeopleRepository;
import com.dprsnn.UtilPlast.services.AddressService;
import com.dprsnn.UtilPlast.services.CategoryService;
import com.dprsnn.UtilPlast.services.PersonService;
import com.dprsnn.UtilPlast.models.Person;
import com.dprsnn.UtilPlast.services.RequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myaccount")
public class AccountController {

    private final PeopleRepository peopleRepository;
    private final PersonService personService;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final RequestService requestService;

    public AccountController(PeopleRepository peopleRepository, PersonService personService, CategoryService categoryService, AddressService addressService, RequestService requestService) {
        this.peopleRepository = peopleRepository;
        this.personService = personService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.requestService = requestService;
    }

    @GetMapping("")
    public String accountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = peopleRepository.findPersonByEmail(authentication.getName());
        personService.setLastActivity(person);
        model.addAttribute("user", person);

        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("addresses", addressService.adressList());

        model.addAttribute("requests", person.getRequestList());

        return "myaccount";
    }
    @PostMapping("/create_request")
    public String createRequest (@RequestParam("category") Long category,
    @RequestParam("address") Long address, @RequestParam("amount") String amount, @RequestParam("comment") String comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = peopleRepository.findPersonByEmail(authentication.getName());

        requestService.createRequest(category, address, amount, comment, person);
        return "redirect:/myaccount";
    }

    @GetMapping("/request/edit/{id}")
    public String editRequest(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("request", requestService.getRequestById(id));
        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("addresses", addressService.adressList());


        return "request";
    }

    @PostMapping("/request/{id}/save")
    public String saveRequest(@PathVariable(name = "id") Long id, @RequestParam("category") Long category,  @RequestParam("address") Long address, @RequestParam("amount") String amount, @RequestParam("comment") String comment ){
        requestService.saveEditedRequest(id, category, address, amount, comment);
        return "redirect:/myaccount";
    }
    @GetMapping("/request/delete/{id}")
    public String deleteRequest(@PathVariable(name = "id") Long id){
        requestService.deleteRequest(id);
        return "redirect:/myaccount";
    }

}

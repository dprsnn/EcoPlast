package com.dprsnn.UtilPlast.controllers;

import com.dprsnn.UtilPlast.repositories.PeopleRepository;
import com.dprsnn.UtilPlast.services.PersonService;
import com.dprsnn.UtilPlast.models.Person;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final PeopleRepository peopleRepository;
    private final PersonService personService;

    public AccountController(PeopleRepository peopleRepository, PersonService personService) {
        this.peopleRepository = peopleRepository;
        this.personService = personService;
    }

    @GetMapping("/myaccount")
    public String accountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = peopleRepository.findPersonByEmail(authentication.getName());
        personService.setLastActivity(person);
        model.addAttribute("user", person);
        return "myaccount";
    }
}

package com.example.demosbthymeleaf.controller;

import com.example.demosbthymeleaf.entities.Person;
import com.example.demosbthymeleaf.form.PersonForm;
import com.example.demosbthymeleaf.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class PersonController {
    private final PersonService service;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/", "/index"})
    public String showHomePage(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping(value = {"/person-list", "/all"})
    public String showPersonList(Model model) {
        List<Person> list = service.getAll()
                .stream().sorted(Comparator.comparing(person -> person.getFirstName()))
                .collect(Collectors.toList());
        model.addAttribute("persons", list);
        return "person_list";
    }

    @GetMapping(value = "/add-person")
    public String showAddNewPerson(Model model) {
        // create form object
        PersonForm personForm = new PersonForm();
        model.addAttribute("form", personForm);
        return "add_person";
    }

    @PostMapping(value = "/add-person")
    public String savePerson(Model model, @ModelAttribute("form") PersonForm personForm) {
        // create form object
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        Integer age = personForm.getAge();

        if (firstName != null && firstName.length() > 0
                && lastName != null && lastName.length() > 0) {
            Person person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAge(age);
            service.save(person);
            return "redirect:/person-list";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "add_person";
    }
}

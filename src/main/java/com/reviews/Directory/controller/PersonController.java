package com.reviews.Directory.controller;

import com.reviews.Directory.entity.Business;
import com.reviews.Directory.service.BusinessService;
import com.reviews.Directory.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PersonController {
    private final BusinessService service;
    private final ReviewService reviewService;

// CREATE - POST
    @PostMapping("/addPerson")
    public Business addPerson(@RequestBody Business business) {
        return service.savePerson(business);
    }

    @PostMapping("/addPeople")
    public List<Business> addPeople(@RequestBody List<Business> people) {
        return service.savePeople(people);
    }

// READ - GET
    /* @GetMapping("/people")
    public List<Person> findAllPeople(){
        return service.getPeople();
    }*/
    @GetMapping("/people")
    public String findAllPeople(Model model){
        model.addAttribute("people",service.getPeople());
        model.addAttribute("test", "Testn=");
        return  "people_list";
 }

    @GetMapping("/person/{id}")
    public Business findPersonById(@PathVariable long id){
        return service.getPersonById(id);
    }

    @GetMapping("/name/{firstName}")  // Works only for unique names
    public Business findPersonByFirstName(@PathVariable String firstName){
        return service.getPersonByFirstName(firstName);
    }

// UPDATE - PUT
    @PutMapping("/update")
    public Business updatePerson(@RequestBody Business business) {
        return service.updatePerson(business);
    }
// DELETE
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable long id){
        return service.deletePerson(id);
    }

// GET / READ WITH MODELS ATTRIBUTES

    @GetMapping("/personForm")
    public String personForm(Model theModel) {
    // Create PERSON object.
    Business theBusiness = new Business();

    // Add PERSON object as a model attribute.
    theModel.addAttribute("person", theBusiness);  // Name & value of attribute.
    return "person-form";
}

    @PostMapping("/submitForm")
    public String personSubmit(@ModelAttribute Business theBusiness, Model theModel) {
        theModel.addAttribute("thePerson", theBusiness);  // Name & value of attribute.
//        System.out.println(thePerson);
        service.savePerson(theBusiness);
        return "submit-person";
    }


}

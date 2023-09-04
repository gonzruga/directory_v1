package com.reviews.Directory.controller;

import com.reviews.Directory.entity.Business;
import com.reviews.Directory.service.BusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService service;

// CREATE - POST
    @PostMapping("/addBiz")
    public Business addBusiness(@RequestBody Business business) {
        return service.saveBusiness(business);
    }

    @PostMapping("/addBiz2")
    public List<Business> addBusinesses(@RequestBody List<Business> businesses) {
        return service.saveBusinesses(businesses);
    }

// READ - GET
    @GetMapping("/businesses")
    public String findAllBusinesses(Model model){
        model.addAttribute("businesses",service.getBusinesses());
        model.addAttribute("test", "Test");
        return "business-list";
 }

    @GetMapping("/business/{id}")
    public Business findPersonById(@PathVariable long id){
        return service.getBusinessById(id);
    }

    @GetMapping("/name/{businessName}")  // Works only for unique names
    public Business findBusinessByBusinessName(@PathVariable String businessName){
        return service.getBusinessByBusinessName(businessName);
    }

// UPDATE - PUT
    @PutMapping("/update")
    public Business updateBusiness(@RequestBody Business business) {
        return service.updateBusiness(business);
    }
// DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteBusiness(@PathVariable long id){
        return service.deleteBusiness(id);
    }


// ADD BUSINESS FORM & SUBMISSION: GET - READ WITH MODELS ATTRIBUTES

    @GetMapping("/businessForm")
    public String businessForm(Model theModel) {
    // Create BUSINESS object.
    Business theBusiness = new Business();

    // Add BUSINESS object as a model attribute.
    theModel.addAttribute("business", theBusiness);  // Name & value of attribute.
    return "form-business";
}

    @PostMapping("/businessSubmit")
    public String businessSubmit(@ModelAttribute Business theBusiness, Model theModel) {
        theModel.addAttribute("theBusiness", theBusiness);  // Name & value of attribute.
//        System.out.println(thePerson);
        service.saveBusiness(theBusiness);
        return "submit-business";
    }


}

/*

{
    "id": "2",
    "businessName":"ABC Limited",
    "email":"office@gmail.com",
    "mobile":"0712345678",
    "physicalAddress":"12 Faru Road",
    "location":"Mwenge",
    "category":"Technology",
    "tinNumber":"123456",
    "link":"www.instagram.com/abcltd",
    "created_at":"2023-07-24 10:10:20",
    "updated_at":"2023-07-27 10:10:20"
}

*/
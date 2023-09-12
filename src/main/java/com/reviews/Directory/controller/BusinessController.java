package com.reviews.Directory.controller;

import com.reviews.Directory.entity.Business;
import com.reviews.Directory.service.BusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
//@RestController // For returning data, not template
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService service;

// CREATE - POST
    @PostMapping("/addBiz")
    public Business addBusiness(@RequestBody Business business) {
        return service.saveBusiness(business);
    }

    @PostMapping("/addBusinesses")
    public List<Business> addBusinesses(@RequestBody List<Business> businesses) {
        return service.saveBusinesses(businesses);
    }

// READ - GET
    @GetMapping("/businesses")
    public String findAllBusinesses(Model model){
        model.addAttribute("business",service.getBusinesses());
        return "business-list";
    }

    @GetMapping("/biz1")
    public ResponseEntity<List<Business>> findAllBusinesses1(){
        return  ResponseEntity.ok(service.getBusinesses());
    }
    @GetMapping("/biz2") //Rest
    public List<Business> findAllBusinesses2(){
       return  service.getBusinesses();
    }


    @GetMapping("/business/{id}")
    public Business findBusinessById(@PathVariable long id){
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
        service.deleteBusiness(id);
        return "redirect:/businesses";
    }


// FORMS & PAGES

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
        service.saveBusiness(theBusiness);
        return "submit-business";
    }

    @GetMapping("/businessPage/{id}")
    public String businessPage(@PathVariable long id, Model model) {
        model.addAttribute("business", service.getBusinessById(id));  // Name & value of attribute.
        return"page-business";
    }

//@GetMapping("/editBusiness")
//public String editBusiness(Long id, Model theModel){
//        try {
//            Optional<Business>business=BusinessService.findById(id);
//            business.ifPresent(value -> theModel.addAttribute("business", value));
//        } catch (Exception e) {
//            log.error(e);
//        }
//        return "edit-business";
//}

@GetMapping("/editBusiness/{id}")
public String editBusiness(@PathVariable long id, Model theModel){
        Business theBusiness = new Business();
            theBusiness = service.getBusinessById(id);
            theModel.addAttribute("business", theBusiness);
        return "edit-business";
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
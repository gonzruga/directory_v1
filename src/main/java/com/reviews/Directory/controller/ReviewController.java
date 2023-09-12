package com.reviews.Directory.controller;

import com.reviews.Directory.entity.Review;
import com.reviews.Directory.entity.ReviewDto;
import com.reviews.Directory.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController  // Data
@Controller // For attaching FORM since 'RestController' does not.
public class ReviewController {
    @Autowired
    private ReviewService service;

// CREATE - POST
    @PostMapping("/addRevRE")
    public ResponseEntity<Review> addReview1(@RequestBody Review review) {
        return new ResponseEntity<>(service.saveReview(review), HttpStatus.OK) ;
    }
    @PostMapping("/addRevData") // Data
    public Review addReview2(@RequestBody Review review) {
        return service.saveReview(review);   }

    @PostMapping("/addReviewDto")
    public ResponseEntity<Review> addReviewDto(@RequestBody ReviewDto review) {
        return new ResponseEntity<>(service.saveReviewDto(review), HttpStatus.OK) ;
    }

    @PostMapping("/addReviews")
    public List<Review> addReviews(@RequestBody List<Review> reviews) {
        return service.saveReviews(reviews);
    }

// READ - GET*
    @GetMapping("/reviews1")
    public ResponseEntity<List<Review>> findAllByReviews(){
        return ResponseEntity.ok(service.getReviews());
    }
    @GetMapping("/reviews2")
    public List<Review> findAllReviews2(){return service.getReviews(); }

    @GetMapping("/review/{id}")
    public  Review findReviewById(@PathVariable long id){
        return service.getReviewById(id);
    }

    @GetMapping("/rwname/{reviewWriterName}")
    public  Review findReviewByReviewWriterName(@PathVariable String reviewWriterName){
        return service.getReviewByReviewWriterName(reviewWriterName);
    }

    @GetMapping("/singleBizReviews/{id}")
    public ResponseEntity<List<Review>> findAllByReviewSubject_Id(@PathVariable Long id){
        return ResponseEntity.ok(service.getReviews(id));
    }

// UPDATE - PUT
    @PutMapping("/updateR")
    public Review updateReview(@RequestBody Review review) {
        return service.updateReview(review);
    }

// DELETE
    @DeleteMapping("/deleteR/{id}")
    public String deleteReview(@PathVariable long id){
        return service.deleteReview(id);
    }



// 'ADD REVIEW' FORM & SUBMISSION: GET - READ WITH MODELS ATTRIBUTES

    @GetMapping("/reviewForm1")
    public String reviewForm1(Model theModel) {
        Review theReview = new Review();
//        theModel.addAttribute("businessId",businessId);
        theModel.addAttribute("review", theReview);  // Name & value of attribute.
        return "form-review";
    }

    @GetMapping("/reviewForm2/{businessId}")
    public String reviewForm2(@PathVariable long businessId, Model model) {
        Review theReview = new Review();
        model.addAttribute("reviewSubjectId", businessId);
        model.addAttribute("review", theReview);  // Name & value of attribute.
//        model.setReviewSubjectId(id)
        return "form-review";
    }

    /*
    @GetMapping("/rev/{businessId}")
    public List<Review> findAllReviews(@PathVariable Long personId, Model model){
        model.addAttribute("personId",personId);
        return "";
    }
*/
    @GetMapping("/reviewParam/{id}/{businessName}")
    public String reviewFormParam(
            @PathVariable(name="businessId") Long businessId,
            @PathVariable(name="businessName") String businessName,
            Model theModel
    ) {
    // Create REVIEW object as a model attribute.
        Review theReview = new Review();
        theModel.addAttribute("businessId",businessId);
        theModel.addAttribute("businessName",businessName);
        theModel.addAttribute("review", theReview);  // Name & value of attribute.
        return "form-review";
    }



    @PostMapping("/reviewSubmit") // /{id}
    public String reviewSubmit(@ModelAttribute Review theReview, Model theModel) {
        theModel.addAttribute("theReview", theReview);  // Name & value of attribute.
        service.saveReview(theReview);
        return "submit-review";
    }


}

/*
{
    "reviewContent" : "ABC Limited do great work",
    "reviewWriterName" : "John2",

    "review_subject_id" : "1"
}
*/
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

@Controller // For attaching FORM since 'RestController' does not.
public class ReviewController {
    @Autowired
    private ReviewService service;

// CREATE - POST
    @PostMapping("/addReview1")
    public Review addReview1(@RequestBody Review review) {
        return service.saveReview(review);   }

    @PostMapping("/addReviews")
    public List<Review> addReviews(@RequestBody List<Review> reviews) {
    return service.saveReviews(reviews);
}


    @PostMapping("/addReview2")
    public ResponseEntity<?> addReview2(@RequestBody Review review) {
        return new ResponseEntity<>(service.saveReview(review), HttpStatus.OK) ;
    }

    @PostMapping("/addReviewDto")
    public ResponseEntity<Review> addReviewDto(@RequestBody ReviewDto review) {
        return new ResponseEntity<>(service.saveReviewDto(review), HttpStatus.OK) ;
    }

/*
    @GetMapping("/reviewForm/{personId}")
    public List<Review> findAllReviews(@PathVariable Long personId, Model model){
        model.addAttribute("personId",personId);
        return "";
    }
*/

// READ - GET*
    @GetMapping("/reviews")
    public List<Review> findAllReviews(){return service.getReviews(); }

    @GetMapping("/review/{id}")
    public  Review findReviewById(@PathVariable long id){
        return service.getReviewById(id);
    }

    @GetMapping("/rwname/{reviewWriterName}")
    public  Review findReviewByReviewWriterName(@PathVariable String reviewWriterName){
        return service.getReviewByReviewWriterName(reviewWriterName);
    }

    //BY ID
    @GetMapping("/people/{id}")
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




// ADD REVIEW FORM & SUBMISSION: GET - READ WITH MODELS ATTRIBUTES

    @GetMapping("/reviewForm/{personId}/{personName}")
    public String reviewForm(
            @PathVariable(name="personId") Long personId,
            @PathVariable(name="personName") String personName,
            Model theModel
    ) {

    // Create REVIEW object as a model attribute.
        Review theReview = new Review();
        theModel.addAttribute("personId",personId);
        theModel.addAttribute("review", theReview);  // Name & value of attribute.
        return "form-review";
    }

    @PostMapping("/reviewSubmit/{id}")
    public String reviewSubmit(@ModelAttribute Review theReview, Model theModel) {
        theModel.addAttribute("theReview", theReview);  // Name & value of attribute.
        service.saveReview(theReview);
        return "submit-review";
    }

    @RequestMapping("/reviewParam")
    public String reviewId(
            @RequestParam("personName") String thePersonName, Model model
    ) {
        String testSentence = "Combine with name: " + thePersonName;
        model.addAttribute("review", testSentence);
        return "submit-review";
    }

}

/*

{
    "id" : "1"
    "reviewContent" : "ABC Limited do great work",
    "reviewWriterName" : "John Doe"

    "review_subject_id" : "1"

    "created_at":"2023-07-24 10:10:20",
    "updated_at":"2023-07-27 10:10:20"
}

*/
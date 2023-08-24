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
//    @PostMapping("/addReview")
//    public Review addReview1(@RequestBody Review review) {
//        return service.saveReview(review);   }  // Version

    @PostMapping("/addReview")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        return new ResponseEntity<>(service.saveReview(review), HttpStatus.OK) ;
    }
    @PostMapping("/addReviewDto")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto review) {
        return new ResponseEntity<>(service.saveReviewDto(review), HttpStatus.OK) ;
    }

    //<a th:href="@{/reviewsForm/{gonzaId}(gonzaId = ${person.id})}"></a>
/*
    @GetMapping("/reviewForm/{personId}")
    public List<Review> findAllReviews(@PathVariable Long personId, Model model){
        model.addAttribute("personId",personId);
        return "";
    }
*/
    @PostMapping("/addReviews")
    public List<Review> addReviews(@RequestBody List<Review> reviews) {
        return service.saveReviews(reviews);
    }

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

    //BY iD
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




// GET / READ WITH MODELS ATTRIBUTES

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
        return "review-form";
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
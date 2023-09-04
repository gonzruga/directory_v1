package com.reviews.Directory.service;


import com.reviews.Directory.entity.Business;
import com.reviews.Directory.entity.Review;
import com.reviews.Directory.entity.ReviewDto;
import com.reviews.Directory.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final BusinessService personService;

// CREATE - POST
    public Review saveReview(Review review) {
        return repository.save(review);
    }
    public Review saveReviewDto(ReviewDto review) {
        try {
            Business business = personService.getBusinessById(review.getReviewSubjectId());
            if (business != null){
                Review review1 = new Review();
                review1.setReviewSubject(business);
                review1.setReviewContent(review.getReviewContent());
                review1.setReviewWriterName(review.getReviewWriterName());
                return repository.save(review1);
            }else {
                throw new RuntimeException("Person Does Not Exist");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed To Save Review");
        }
    }

    public List<Review>  saveReviews(List<Review> reviews) {
        return repository.saveAll(reviews);
    }

// READ - GET

    public List<Review> getReviews() {
        return repository.findAll();
    }

    public List<Review> getReviews(Long person) {
        return repository.findAllByReviewSubject_Id(person);
    }

    public Review getReviewById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Review getReviewByReviewWriterName(String reviewWriterName) {
        return repository.findByReviewWriterName(reviewWriterName);
        // This is a custom method. It has to be made in repository.
    }

// DELETE
    public String deleteReview(long id){
        repository.deleteAllById(Collections.singleton(id));
        return "Review removed "+ id;
    }

// UPDATE - PUT
    public Review updateReview(Review review){
        Review existingReview = repository.findById(review.getId()).orElse(null);

        // There is no UPDATE method in Spring Data JPA
        existingReview.setReviewContent(review.getReviewContent());
        existingReview.setReviewWriterName(review.getReviewWriterName());
        // You can't change person being reviewed
        existingReview.setUpdatedAt( new Date() );

        return repository.save(existingReview);

}


}

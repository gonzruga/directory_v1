package com.reviews.Directory.repository;

import com.reviews.Directory.entity.Business;
import com.reviews.Directory.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewWriterName(String reviewWriterName);
    List<Review> findAllByReviewSubject_Id(Long id);

    List<Review> findAllByReviewSubject(Business business);
}

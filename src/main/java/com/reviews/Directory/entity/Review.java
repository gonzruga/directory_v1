package com.reviews.Directory.entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String reviewContent;
    private String reviewWriterName;

    @ManyToOne
    @JoinColumn(name = "review_subject_id")
    private Business reviewSubject;

    @CreationTimestamp
    private Date createdAt  = new Date();
    @UpdateTimestamp
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Review review = (Review) o;
        return id != null && Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

/*

{
"id" : "1"
"reviewContent" : "Bob is a great guy",
"reviewWriterName" : "John Doe"

"review_subject_id" : "1"

 "created_at":"2023-07-24 10:10:20",
 "updated_at":"2023-07-27 10:10:20"
        }

 */

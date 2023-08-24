package com.reviews.Directory.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String reviewContent;
    private String reviewWriterName;
    private Long reviewSubjectId;
    private Date createdAt = new Date();
    private Date updatedAt = null;
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

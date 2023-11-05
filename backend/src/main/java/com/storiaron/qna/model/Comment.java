package com.storiaron.qna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    private int upVotes;
    private int downVotes;
    private LocalDateTime timeOfWriting;
    @ManyToOne
    @JsonBackReference(value = "post-comment")
    private Post parentPost;
    @ManyToOne
    @JsonBackReference(value = "user-comment")
    private QnAUser postedBy;
}

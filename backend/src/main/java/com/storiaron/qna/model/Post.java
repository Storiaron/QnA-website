package com.storiaron.qna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    @JsonManagedReference
    private Set<Comment> comments;
    private String title;
    private LocalDateTime timeOfWriting;
    private int upVotes;
    private int downVotes;
    @ManyToOne
    @JsonBackReference
    private QnAUser qnAUser;
}

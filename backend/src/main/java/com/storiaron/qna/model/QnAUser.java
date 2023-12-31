package com.storiaron.qna.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QnAUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    @JsonManagedReference(value = "user-comment")
    private Set<Comment> comments;
    @OneToMany
    @JsonManagedReference(value = "user-post")
    private Set<Post> posts;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    private Set<Comment> votedComments;
    @ManyToMany
    private Set<Post> votedPosts;
}

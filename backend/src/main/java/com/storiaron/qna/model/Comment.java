package com.storiaron.qna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
    @ManyToMany
    @JsonBackReference(value = "comment-upvoted-by")
    private Set<QnAUser> votedBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

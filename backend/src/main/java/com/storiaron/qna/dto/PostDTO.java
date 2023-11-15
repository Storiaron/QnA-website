package com.storiaron.qna.dto;

import com.storiaron.qna.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Long id;
    private Set<Comment> comments;
    private String title;
    private String body;
    private LocalDateTime timeOfWriting;
    private int upVotes;
    private int downVotes;
    private int commentCount;
    private String username;
    private boolean isLastPost;
}

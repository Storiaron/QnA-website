package com.storiaron.qna.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String body;
    private int upVotes;
    private int downVotes;
    private LocalDateTime timeOfWriting;
    private String username;
}
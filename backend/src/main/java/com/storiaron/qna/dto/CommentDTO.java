package com.storiaron.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String body;
    private String username;
    private Long idOfParentPost;
}

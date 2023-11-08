package com.storiaron.qna.dto.newdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewCommentDTO {
    private String body;
    private String username;
    private Long parentPostId;
}

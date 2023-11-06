package com.storiaron.qna.dto.newdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewPostDTO {
    private String title;
    private String body;
    private String username;
}

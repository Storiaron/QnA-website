package com.storiaron.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentAutoLoadDTO {
    private LocalDateTime startingFrom;
    private boolean isInDataSavingMode;
    private Long parentPostId;
}

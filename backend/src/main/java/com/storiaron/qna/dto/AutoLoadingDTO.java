package com.storiaron.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AutoLoadingDTO {
    private LocalDateTime startingFrom;
    private boolean isInDataSavingMode;
}

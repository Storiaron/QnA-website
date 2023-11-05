package com.storiaron.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostAutoLoadDTO {
    private LocalDateTime startingFrom;
    private boolean isInDataSavingMode;

    @Override
    public String toString() {
        return "AutoLoadingDTO{" +
                "startingFrom=" + startingFrom +
                ", isInDataSavingMode=" + isInDataSavingMode +
                '}';
    }
}

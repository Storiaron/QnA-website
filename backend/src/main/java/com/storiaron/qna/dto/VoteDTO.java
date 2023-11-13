package com.storiaron.qna.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoteDTO {
    private Long id;
    private String username;
    private boolean isUpVote;

    @Override
    public String toString() {
        return "VoteDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isUpvote=" + isUpVote +
                '}';
    }
}

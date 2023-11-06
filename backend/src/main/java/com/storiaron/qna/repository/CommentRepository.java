package com.storiaron.qna.repository;

import com.storiaron.qna.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findTop20ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(Long parentPostId, LocalDateTime queryStart);
    Set<Comment> findTop5ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(Long parentPostId, LocalDateTime queryStart);
}

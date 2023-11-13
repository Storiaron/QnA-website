package com.storiaron.qna.repository;

import com.storiaron.qna.model.Comment;
import com.storiaron.qna.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findTop20ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(Long parentPostId, LocalDateTime queryStart);
    List<Comment> findTop5ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(Long parentPostId, LocalDateTime queryStart);
    int countByParentPost(Post post);
    Comment findTopByParentPostIdOrderByTimeOfWritingAsc(Long parentPostId);
}

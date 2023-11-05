package com.storiaron.qna.repository;

import com.storiaron.qna.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findTop20ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(LocalDateTime queryStart);
    Set<Post> findTop5ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(LocalDateTime queryStart);
}

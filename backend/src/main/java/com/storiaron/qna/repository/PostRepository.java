package com.storiaron.qna.repository;

import com.storiaron.qna.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

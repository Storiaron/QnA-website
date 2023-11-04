package com.storiaron.qna.service;

import com.storiaron.qna.repository.CommentRepository;
import com.storiaron.qna.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public ContentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
}

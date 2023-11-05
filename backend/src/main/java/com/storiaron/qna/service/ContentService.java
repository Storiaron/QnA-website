package com.storiaron.qna.service;

import com.storiaron.qna.dto.AutoLoadingDTO;
import com.storiaron.qna.dto.PostDTO;
import com.storiaron.qna.model.Comment;
import com.storiaron.qna.model.Post;
import com.storiaron.qna.model.QnAUser;
import com.storiaron.qna.repository.CommentRepository;
import com.storiaron.qna.repository.PostRepository;
import com.storiaron.qna.repository.QnAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ContentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final QnAUserRepository qnAUserRepository;
    @Autowired
    public ContentService(PostRepository postRepository, CommentRepository commentRepository, QnAUserRepository qnAUserRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.qnAUserRepository = qnAUserRepository;
    }

    public Set<Post> getNewestPosts(AutoLoadingDTO autoLoadingDTO){
        if(autoLoadingDTO.isInDataSavingMode()){
            return postRepository.findTop5ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(autoLoadingDTO.getStartingFrom());
        }
        else {
            return postRepository.findTop20ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(autoLoadingDTO.getStartingFrom());
        }
    }
    public Set<Comment> getNewestComments(AutoLoadingDTO autoLoadingDTO){
        if(autoLoadingDTO.isInDataSavingMode()){
            return commentRepository.findTop5ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(autoLoadingDTO.getStartingFrom());
        }
        else {
            return commentRepository.findTop20ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(autoLoadingDTO.getStartingFrom());
        }
    }
    @Transactional
    public void addPost(PostDTO postDTO){
        QnAUser qnAUser = qnAUserRepository.findByUsername(postDTO.getUsername());
        if(qnAUser != null){
            Post post = new Post();
            post.setBody(postDTO.getBody());
            post.setTitle(postDTO.getTitle());
            post.setQnAUser(qnAUser);
            post.setTimeOfWriting(LocalDateTime.now());
            postRepository.save(post);
            qnAUser.getPosts().add(post);
            qnAUserRepository.save(qnAUser);
        }
    }
}

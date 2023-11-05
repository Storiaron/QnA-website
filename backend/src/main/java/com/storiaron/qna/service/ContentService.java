package com.storiaron.qna.service;

import com.storiaron.qna.dto.CommentAutoLoadDTO;
import com.storiaron.qna.dto.PostAutoLoadDTO;
import com.storiaron.qna.dto.CommentDTO;
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

    public Set<Post> getNewestPosts(PostAutoLoadDTO postAutoLoadDTO){
        if(postAutoLoadDTO.isInDataSavingMode()){
            return postRepository.findTop5ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    postAutoLoadDTO.getStartingFrom());
        }
        else {
            return postRepository.findTop20ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    postAutoLoadDTO.getStartingFrom());
        }
    }
    public Set<Comment> getNewestComments(CommentAutoLoadDTO commentAutoLoadDTO){
        if(commentAutoLoadDTO.isInDataSavingMode()){
            return commentRepository.findTop5ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    commentAutoLoadDTO.getParentPostId(), commentAutoLoadDTO.getStartingFrom());
        }
        else {
            return commentRepository.findTop20ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    commentAutoLoadDTO.getParentPostId(), commentAutoLoadDTO.getStartingFrom());
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
    @Transactional
    public void addComment(CommentDTO commentDTO){
        Post post = postRepository.getReferenceById(commentDTO.getIdOfParentPost());
        QnAUser qnAUser = qnAUserRepository.findByUsername(commentDTO.getUsername());
        if(post != null && qnAUser != null){
            Comment comment = new Comment();
            comment.setBody(commentDTO.getBody());
            comment.setTimeOfWriting(LocalDateTime.now());
            comment.setPostedBy(qnAUser);
            comment.setParentPost(post);
            commentRepository.save(comment);
            post.getComments().add(comment);
            postRepository.save(post);
            qnAUserRepository.save(qnAUser);
        }
    }
}

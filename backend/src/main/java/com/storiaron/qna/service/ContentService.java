package com.storiaron.qna.service;

import com.storiaron.qna.dto.CommentAutoLoadDTO;
import com.storiaron.qna.dto.CommentDTO;
import com.storiaron.qna.dto.PostAutoLoadDTO;
import com.storiaron.qna.dto.PostDTO;
import com.storiaron.qna.dto.newdto.NewCommentDTO;
import com.storiaron.qna.dto.newdto.NewPostDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Post> getNewestPosts(PostAutoLoadDTO postAutoLoadDTO){
        if(postAutoLoadDTO.isInDataSavingMode()){
            return postRepository.findTop5ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    postAutoLoadDTO.getStartingFrom());
        }
        else {
            return postRepository.findTop20ByTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    postAutoLoadDTO.getStartingFrom());
        }
    }
    public List<CommentDTO> getNewestComments(CommentAutoLoadDTO commentAutoLoadDTO){
        if(commentAutoLoadDTO.isInDataSavingMode()){
            List<Comment> comments = commentRepository.findTop5ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    commentAutoLoadDTO.getParentPostId(), commentAutoLoadDTO.getStartingFrom());
            return commentCommentDTOMapper(comments);
        }
        else {
            List<Comment> comments = commentRepository.findTop20ByParentPostIdAndTimeOfWritingBeforeOrderByTimeOfWritingDesc(
                    commentAutoLoadDTO.getParentPostId(), commentAutoLoadDTO.getStartingFrom());
            return commentCommentDTOMapper(comments);
        }
    }
    private List<CommentDTO> commentCommentDTOMapper(List<Comment> comments){
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for(Comment comment : comments){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setBody(comment.getBody());
            commentDTO.setId(comment.getId());
            commentDTO.setUpVotes(comment.getUpVotes());
            commentDTO.setDownVotes(comment.getDownVotes());
            QnAUser qnAUser = qnAUserRepository.findByUsername(comment.getPostedBy().getUsername());
            commentDTO.setUsername(qnAUser.getUsername());
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }
    @Transactional
    public Long addPost(NewPostDTO newPostDTO){
        QnAUser qnAUser = qnAUserRepository.findByUsername(newPostDTO.getUsername());
        if(qnAUser != null){
            Post post = new Post();
            post.setBody(newPostDTO.getBody());
            post.setTitle(newPostDTO.getTitle());
            post.setQnAUser(qnAUser);
            post.setTimeOfWriting(LocalDateTime.now());
            Post newPost = postRepository.save(post);
            qnAUser.getPosts().add(post);
            qnAUserRepository.save(qnAUser);
            return newPost.getId();
        }
        return (long)0;
    }
    @Transactional
    public void addComment(NewCommentDTO newCommentDTO){
        Post post = postRepository.getReferenceById(newCommentDTO.getParentPostId());
        QnAUser qnAUser = qnAUserRepository.findByUsername(newCommentDTO.getUsername());
        if(post != null && qnAUser != null){
            Comment comment = new Comment();
            comment.setBody(newCommentDTO.getBody());
            comment.setTimeOfWriting(LocalDateTime.now());
            comment.setPostedBy(qnAUser);
            comment.setParentPost(post);
            commentRepository.save(comment);
            post.getComments().add(comment);
            postRepository.save(post);
            qnAUserRepository.save(qnAUser);
        }
    }
    @Transactional
    public PostDTO getPost(Long id){
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isPresent()){
            Post post = optional.get();
            PostDTO postDTO = new PostDTO();
            postDTO.setBody(post.getBody());
            postDTO.setTitle(post.getTitle());
            postDTO.setUsername(post.getQnAUser().getUsername());
            postDTO.setTimeOfWriting(post.getTimeOfWriting());
            int commentCount = commentRepository.countByParentPost(post);
            postDTO.setCommentCount(commentCount);
            postDTO.setId(id);
            return postDTO;
        }
        return null;
    }
}

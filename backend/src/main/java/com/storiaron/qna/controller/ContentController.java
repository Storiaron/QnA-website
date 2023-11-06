package com.storiaron.qna.controller;

import com.storiaron.qna.dto.CommentAutoLoadDTO;
import com.storiaron.qna.dto.PostAutoLoadDTO;
import com.storiaron.qna.dto.PostDTO;
import com.storiaron.qna.dto.newdto.NewCommentDTO;
import com.storiaron.qna.dto.newdto.NewPostDTO;
import com.storiaron.qna.model.Comment;
import com.storiaron.qna.model.Post;
import com.storiaron.qna.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;
    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @GetMapping("/post/{id}")
    public PostDTO getPost(@PathVariable Long id){
        return contentService.getPost(id);
    }
    @PutMapping("/post/newest")
    public Set<Post> getNewestPosts(@RequestBody PostAutoLoadDTO postAutoLoadDTO){
        return contentService.getNewestPosts(postAutoLoadDTO);
    }
    @PostMapping("/post")
    public void addPost(@RequestBody NewPostDTO newPostDTO){
        contentService.addPost(newPostDTO);
    }
    @PutMapping("/comment/newest")
    public Set<Comment> getNewestComments(@RequestBody CommentAutoLoadDTO commentAutoLoadDTO){
        return contentService.getNewestComments(commentAutoLoadDTO);
    }
    @PostMapping("/comment")
    public void addComment(@RequestBody NewCommentDTO newCommentDTO){
        contentService.addComment(newCommentDTO);
    }
}

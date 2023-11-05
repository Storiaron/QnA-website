package com.storiaron.qna.controller;

import com.storiaron.qna.dto.AutoLoadingDTO;
import com.storiaron.qna.dto.PostDTO;
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

    @GetMapping("/post/newest")
    public Set<Post> getNewestPosts(@RequestBody AutoLoadingDTO autoLoadingDTO){
        return contentService.getNewestPosts(autoLoadingDTO);
    }
    @PostMapping("/post")
    public void addPost(@RequestBody PostDTO postDTO){
        contentService.addPost(postDTO);
    }
    @GetMapping("/comment/newest")
    public Set<Comment> getNewestComments(@RequestBody AutoLoadingDTO autoLoadingDTO){
        return contentService.getNewestComments(autoLoadingDTO);
    }
   /* @PostMapping("/comment")
    public void addComment(@RequestBody Comment comment){
        contentService.addComment(comment);
    } */
}

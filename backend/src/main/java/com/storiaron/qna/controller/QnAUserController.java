package com.storiaron.qna.controller;

import com.storiaron.qna.model.QnAUser;
import com.storiaron.qna.service.QnAUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class QnAUserController {
    private final QnAUserService qnAUserService;
    @Autowired
    public QnAUserController(QnAUserService qnAUserService) {
        this.qnAUserService = qnAUserService;
    }
    @PostMapping("/register")
    public boolean registerUser(@RequestBody QnAUser qnAUser){
        return qnAUserService.registerUser(qnAUser);
    }
}

package com.storiaron.qna.service;

import com.storiaron.qna.model.QnAUser;
import com.storiaron.qna.model.Role;
import com.storiaron.qna.repository.QnAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class QnAUserService implements UserDetailsService {
    private final QnAUserRepository qnAUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public QnAUserService(QnAUserRepository qnAUserRepository) {
        this.qnAUserRepository = qnAUserRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        QnAUser qnAUser = qnAUserRepository.findByUsername(username);
        return qnAUser != null ? User.withUsername(qnAUser.getUsername())
                .password(qnAUser.getPassword())
                .roles(String.valueOf(qnAUser.getRole()))
                .build() : null;
    }

    public boolean registerUser(QnAUser qnAUser){
        if(qnAUserRepository.findByUsername(qnAUser.getUsername()) != null){
            return false;
        }
        else {
            qnAUser.setRole(Role.User);
            qnAUser.setPassword(passwordEncoder.encode(qnAUser.getPassword()));
            qnAUserRepository.save(qnAUser);
            return true;
        }
    }
}

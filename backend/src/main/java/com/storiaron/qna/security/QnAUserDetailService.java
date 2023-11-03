package com.storiaron.qna.security;

import com.storiaron.qna.model.QnAUser;
import com.storiaron.qna.repository.QnAUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QnAUserDetailService implements UserDetailsService {
    private final QnAUserRepository qnAUserRepository;

    public QnAUserDetailService(QnAUserRepository qnAUserRepository) {
        this.qnAUserRepository = qnAUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QnAUser qnAUser = qnAUserRepository.findByUsername(username);
        return User.withUsername(qnAUser.getUsername())
                .password(qnAUser.getPassword())
                .roles(String.valueOf(qnAUser.getRole()))
                .build();
    }
}

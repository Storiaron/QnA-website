package com.storiaron.qna.repository;

import com.storiaron.qna.model.QnAUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnAUserRepository extends JpaRepository<QnAUser, Long> {
    QnAUser findByUsername(String username);
}

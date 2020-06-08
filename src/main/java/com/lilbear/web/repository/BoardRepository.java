package com.lilbear.web.repository;

import com.lilbear.web.domain.Board;
import com.lilbear.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}

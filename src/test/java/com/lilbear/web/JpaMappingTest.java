package com.lilbear.web;

import com.lilbear.web.domain.Board;
import com.lilbear.web.domain.BoardType;
import com.lilbear.web.domain.User;
import com.lilbear.web.repository.BoardRepository;
import com.lilbear.web.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        User user = userRepository.save(User.builder()
                .name("lilbear")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브 타이틀")
                .content("콘텐츠")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user)
                .build());
    }

    @Test
    public void 제대로_생성됐는가() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName()).isEqualTo("lilbear");
        assertThat(user.getPassword()).isEqualTo("test");
        assertThat(user.getEmail()).isEqualTo(email);

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle()).isEqualTo(boardTestTitle);
        assertThat(board.getSubTitle()).isEqualTo("서브 타이틀");
        assertThat(board.getContent()).isEqualTo("콘텐츠");
        assertThat(board.getBoardType()).isEqualTo(BoardType.free);
    }
}

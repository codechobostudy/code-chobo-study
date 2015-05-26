package io.codechobostudy.board.service;

import io.codechobostudy.Application;
import io.codechobostudy.board.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jinovice on 15. 5. 14..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @Rollback
    public void test_board_creatd() throws Exception {

        Board board = new Board();
        board.setRegId("urosaria");
        board.setRegName("Yu rosaria");
        board.setSubject("title");
        board.setContent("content");

        //when
        Long boardId = boardService.boardCreate(board);

        System.out.println(boardId);

    }

    @Test
    @Rollback
    public void test_board_list() throws Exception {

        Board board = new Board();
        board.setRegId("urosaria");
        board.setRegName("Yu rosaria");
        board.setSubject("title");
        board.setContent("content");

        //when
        List<Board> list = boardService.boardList();

        for(int i=0; i<list.size(); i++){
            System.out.println("test: " + list.get(i).getSubject());
        }
    }

}

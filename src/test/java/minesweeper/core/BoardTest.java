package minesweeper.core;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BoardTest {

    @Test
    public void testMakeBoard() {
        Board board = new Board(5);
        System.out.println(board);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(0, 0);
        });
    }
}
package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TicTacToeImpl implements TicTacToe{
	private char[][] board;   
    private static final char SPACE = ' ';
    private char lastMark;
    
    public TicTacToeImpl() {
		this.board = new char[3][3];
		IntStream.range(0, 3).forEach(i -> Arrays.fill(board[i], SPACE));		
		this.lastMark = 'O';
	}

	@Override
	public void setMark(int x, int y, char mark) {
		if(board[x][y] == SPACE) {
			board[x][y] = mark;
			lastMark = mark;						
		}else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public char[][] table() {
		return Arrays.stream(board).map(t -> Arrays.copyOf(t, t.length)).toArray(char[][]::new);
//		return Arrays.stream(board).map(t -> t.clone()).toArray(char[][]::new);
	}

	@Override
	public char lastMark() {	
		return lastMark;
	}
	
}

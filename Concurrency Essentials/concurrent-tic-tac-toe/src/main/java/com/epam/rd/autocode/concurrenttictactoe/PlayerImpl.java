package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player{
	private TicTacToe ticTacToe;  
    private PlayerStrategy strategy; 
    private char mark;
    private static final char SPACE = ' ';
	
    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
		this.ticTacToe = ticTacToe;
		this.mark = mark;
		this.strategy = strategy;		
	}

	@Override
	public void run() {
		while(!isWin()) {
			synchronized (ticTacToe) {
				if(!isWin() && ticTacToe.lastMark() != mark) {
					Move move = strategy.computeMove(mark, ticTacToe);
					ticTacToe.setMark(move.row, move.column, mark);
				}				
			}						
		}		
	}
	
	public boolean isWin() {
		char[][] table = ticTacToe.table();
		for (int i = 0; i < table.length; i++) {
			//проверка равны ли символы
			if(table[i][0] == table[i][1] && table[i][1] == table[i][2]) {
				return table[i][0] != SPACE;
			}else if(table[0][i] == table[1][i] && table[1][i] == table[2][i]) {
				return table[0][i] != SPACE; //проверка являются ли они пробелами
			}
		}
		//проверка равны ли символы по диагонали
		if(table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
			return table[0][0] != SPACE;
		}else if(table[2][0] == table[1][1] && table[1][1] == table[0][2]) {
			return table[2][0] != SPACE;
		}		
		return false;
	}
}

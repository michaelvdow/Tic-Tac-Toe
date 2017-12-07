
public class Board {
	
	private int[][] board = new int[3][3];
	
	public Board() {
		
	}
	
	public boolean makeMove(int player, int x, int y) {
		if(board[x][y] == 0) {
			board[x][y] = player;
			return true;
		}
		return false;
	}
	
	public int winner() {
		for(int i = 0; i < board.length; i++) {
			int value = checkRow(i);
			if(value != 0) {
				return value;
			}
		}
		
		for(int i = 0; i < board.length; i++) {
			int value = checkColumn(i);
			if(value != 0) {
				return value;
			}
		}
		
		return checkDiagonals();
	}
	
	private int checkRow(int row) {
		if(board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
			return board[row][0];
		}
		return 0;
	}
	
	private int checkColumn(int column) {
		if(board[0][column] == board[1][column] && board[1][column] == board[2][column]) {
			return board[0][column];
		}
		return 0;
	}
	
	private int checkDiagonals() {
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return board[0][0];
		} else if(board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
			return board[2][0];
		}
		return 0;
	}

}

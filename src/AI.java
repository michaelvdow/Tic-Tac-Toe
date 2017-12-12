
public class AI {

	int level;

	public AI(int level) {
		this.level = level;
		System.out.println(level);
	}

	public int[] getMove(Board board) {
		int[] point = new int[2];

		// Win
		point = win(board);
		if (point != null) {
			System.out.println("Win");
			return point;
		}

		// Block
		point = block(board);
		if (point != null) {
			System.out.println("Block");
			return point;
		}

		if (level == 2) {
			// Fork
			point = countForks(board, 2);
			if (point[0] != -1) {
				System.out.println("Fork");
				return point;
			}

			if ((board.getBoard()[0][0] == 1 && board.getBoard()[2][2] == 1)
					|| board.getBoard()[2][0] == 1 && board.getBoard()[0][2] == 1) {
				if (board.getBoard()[0][1] == 0) {
					point[0] = 0;
					point[1] = 1;
					return point;
				} else if (board.getBoard()[1][0] == 0) {
					point[0] = 1;
					point[1] = 0;
					return point;
				} else if (board.getBoard()[1][2] == 0) {
					point[0] = 1;
					point[1] = 2;
					return point;
				} else if (board.getBoard()[2][1] == 0) {
					point[0] = 2;
					point[1] = 1;
					return point;
				}
			}

			// Block forks
			point = countForks(board, 1);
			if (point[0] != -1) {

				System.out.println("Block Fork");
				return point;
			}

			// Center
			if (board.getBoard()[1][1] == 0) {
				point[0] = 1;
				point[1] = 1;
				System.out.println("Center");
				return point;
			}

			// Opposite Corner
			if (board.getBoard()[0][0] == 1 && board.getBoard()[2][2] == 0) {
				point[0] = 2;
				point[1] = 2;
				return point;
			} else if (board.getBoard()[0][0] == 0 && board.getBoard()[2][2] == 1) {
				point[0] = 0;
				point[1] = 0;
				return point;
			} else if (board.getBoard()[2][0] == 1 && board.getBoard()[0][2] == 0) {
				point[0] = 0;
				point[1] = 2;
				return point;
			} else if (board.getBoard()[2][0] == 0 && board.getBoard()[0][2] == 1) {
				point[0] = 2;
				point[1] = 0;
				return point;
			}
		}
		point = new int[2];

		// Empty corner
		if (board.getBoard()[0][0] == 0) {
			point[0] = 0;
			point[1] = 0;
			return point;
		} else if (board.getBoard()[2][0] == 0) {
			point[0] = 2;
			point[1] = 0;
			return point;
		} else if (board.getBoard()[0][2] == 0) {
			point[0] = 0;
			point[1] = 2;
			return point;
		} else if (board.getBoard()[2][2] == 0) {
			point[0] = 2;
			point[1] = 2;
			return point;
		}

		// Empty side
		if (board.getBoard()[0][1] == 0) {
			point[0] = 0;
			point[1] = 1;
			return point;
		} else if (board.getBoard()[1][0] == 0) {
			point[0] = 1;
			point[1] = 0;
			return point;
		} else if (board.getBoard()[1][2] == 0) {
			point[0] = 1;
			point[1] = 2;
			return point;
		} else if (board.getBoard()[2][1] == 0) {
			point[0] = 2;
			point[1] = 1;
			return point;
		}

		return point;
	}

	public int[] block(Board b) {
		int player = 0;
		int empty = 0;
		int[] index = new int[2];
		int[][] board = b.getBoard();
		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 1) {
					player++;
				} else if (board[i][j] == 0) {
					empty++;
					index[0] = i;
					index[1] = j;
				}
			}

			if (player == 2 && empty == 1) {
				return index;
			}
			player = 0;
			empty = 0;
		}

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[j][i] == 1) {
					player++;
				} else if (board[j][i] == 0) {
					empty++;
					index[0] = j;
					index[1] = i;
				}
			}

			if (player == 2 && empty == 1) {
				return index;
			}
			player = 0;
			empty = 0;
		}

		for (int a = 0; a < board.length; a++) {
			if (board[a][a] == 1) {
				player++;
			} else if (board[a][a] == 0) {
				empty++;
				index[0] = a;
				index[1] = a;
			}
		}
		if (player == 2 && empty == 1) {
			return index;
		}

		player = 0;
		empty = 0;
		for (int a = 0; a < board.length; a++) {
			if (board[a][2 - a] == 1) {
				player++;
			} else if (board[a][2 - a] == 0) {
				empty++;
				index[0] = a;
				index[1] = 2 - a;
			}
		}
		if (player == 2 && empty == 1) {
			return index;
		}
		return null;
	}

	public int[] win(Board b) {
		int AI = 0;
		int empty = 0;
		int[] index = new int[2];
		int[][] board = b.getBoard();
		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 2) {
					AI++;
				} else if (board[i][j] == 0) {
					empty++;
					index[0] = i;
					index[1] = j;
				}
			}

			if (AI == 2 && empty == 1) {
				return index;
			}
			AI = 0;
			empty = 0;
		}

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[j][i] == 2) {
					AI++;
				} else if (board[j][i] == 0) {
					empty++;
					index[0] = j;
					index[1] = i;
				}
			}

			if (AI == 2 && empty == 1) {
				return index;
			}
			AI = 0;
			empty = 0;
		}

		for (int a = 0; a < board.length; a++) {
			if (board[a][a] == 2) {
				AI++;
			} else if (board[a][a] == 0) {
				empty++;
				index[0] = a;
				index[1] = a;
			}
		}
		if (AI == 2 && empty == 1) {
			return index;
		}

		AI = 0;
		empty = 0;
		for (int a = 0; a < board.length; a++) {
			if (board[a][2 - a] == 2) {
				AI++;
			} else if (board[a][2 - a] == 0) {
				empty++;
				index[0] = a;
				index[1] = 2 - a;
			}
		}
		if (AI == 2 && empty == 1) {
			return index;
		}
		return null;
	}

	public int wins(Board b, int player) {
		int AI = 0;
		int empty = 0;
		int[] index = new int[2];
		int[][] board = b.getBoard();
		int numberOfWins = 0;
		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == player) {
					AI++;
				} else if (board[i][j] == 0) {
					empty++;
					index[0] = i;
					index[1] = j;
				}
			}

			if (AI == 2 && empty == 1) {
				numberOfWins++;
			}
			AI = 0;
			empty = 0;
		}

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				if (board[j][i] == 2) {
					AI++;
				} else if (board[j][i] == 0) {
					empty++;
					index[0] = j;
					index[1] = i;
				}
			}

			if (AI == 2 && empty == 1) {
				numberOfWins++;
			}
			AI = 0;
			empty = 0;
		}

		for (int a = 0; a < board.length; a++) {
			if (board[a][a] == player) {
				AI++;
			} else if (board[a][a] == 0) {
				empty++;
				index[0] = a;
				index[1] = a;
			}
		}
		if (AI == 2 && empty == 1) {
			numberOfWins++;
		}

		AI = 0;
		empty = 0;
		for (int a = 0; a < board.length; a++) {
			if (board[a][2 - a] == player) {
				AI++;
			} else if (board[a][2 - a] == 0) {
				empty++;
				index[0] = a;
				index[1] = 2 - a;
			}
		}
		if (AI == 2 && empty == 1) {
			numberOfWins++;
		}
		System.out.println("Number of wins:" + numberOfWins);
		return numberOfWins;
	}

	public int[] countForks(Board board, int player) {

		int bestFork = 0;
		int bestX = -1;
		int bestY = -1;

		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard().length; j++) {
				Board copyBoard = copyBoard(board);
				if (copyBoard.getBoard()[i][j] == 0) {
					copyBoard.makeMove(player, i, j);
					int forks = wins(copyBoard, player);
					System.out.println(forks);
					if (forks == 2) {
						bestFork = forks;
						bestX = i;
						bestY = j;
					}
				}
			}
		}
		int[] point = { bestX, bestY };
		return point;
	}

	public Board copyBoard(Board board) {
		Board copyBoard = new Board();
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard().length; j++) {
				copyBoard.getBoard()[i][j] = board.getBoard()[i][j];
			}
		}
		return copyBoard;
	}

}

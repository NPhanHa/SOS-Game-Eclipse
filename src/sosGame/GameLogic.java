package sosGame;

import java.util.ArrayList;

import sosGame.GameLogic.Cell;


public abstract class GameLogic {
	public enum Cell {EMPTY, BLUE_S, BLUE_O, RED_S, RED_O}

	protected int boardSize;
	protected int gameMode;		//1 - simple, 2- general
	protected String turn;		//"Blue" or "Red" player
	protected char move;			// 'S' or 'O' Move

	protected Cell[][] grid;
	protected int plays = 0;			//Number of possible plays in 
	protected int bScore = 0;
	protected int rScore = 0;
	protected String winner = "";

	/*
	public GameLogic(int boardSize) {
		this.boardSize = boardSize;
		grid = new Cell[boardSize][boardSize];
		initBoard();
	}*/

	//initialize the board
	public void initBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				grid[row][column] = Cell.EMPTY;
			}
		}
		
		turn = "Blue";
		move = 'S';
	}
	
	//function to set the game mode
	public void setGameMode(int gameMode) {this.gameMode = gameMode;}
	
	public void setTurn(String turn)	{this.turn = turn;}
	
	protected void setWinner(String winner) {this.winner = winner;}
	
	//function to return the board size
	public int getBoardSize() {
		return boardSize;
	}
	
	//function to return info of a specific cell
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
			return grid[column][row];
		} else {
			return null;
		}
	}
	
	public int getGameMode() {return gameMode;}		//function to return game mode

	public String getTurn() {return turn;}			//function to return current player's turn
	
	public char getMove() {return move;}			//function to return current player's move
	
	public int getBlueScore() {return bScore;}
	
	public int getRedScore() {return rScore;}
	
	public String getWinner() {return winner;}
	
	public ArrayList<int[]> makeMove(int row, int column, char move) {
		ArrayList<int[]> drawQueue = new ArrayList<>();
		return drawQueue;
	}
	
	
	protected ArrayList<int[]> checkLineS(int x, int y){
		ArrayList<int[]> lines = new ArrayList<>();
		for(int i = -1; i <= 1; ++i) {
			for(int j = -1; j <= 1; ++j) {
				if(i == 0 && j == 0)
					continue;
				if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
					continue;
				if(x + i + i >= boardSize || x + i + i < 0 || y + j + j >= boardSize || y + j + j < 0)
					continue;
				if(grid[x + i][y + j] == Cell.EMPTY || grid[x + i + i][y + j + j] == Cell.EMPTY)
					continue;
				if((grid[x + i][y + j] == Cell.BLUE_O         || grid[x + i][y + j] == Cell.RED_O)
				&& (grid[x + i + i][y + j + j] == Cell.BLUE_S || grid[x + i + i][y + j + j] == Cell.RED_S)){
					lines.add(new int[] {x, y, x + i + i, y + j + j});
				}
			}
		}
		return lines;
	}
	
	protected ArrayList<int[]> checkLineO(int x, int y){
		ArrayList<int[]> lines = new ArrayList<>();
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0)
					continue;
				if((i == -1 && j == 0) || (i == -1 && j == -1)
				|| (i == 0 && j == -1) || (i == 1 && j == -1))
					continue;
				if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
					continue;
				if(x - i >= boardSize || x - i < 0 || y - j >= boardSize || y - j < 0)
					continue;
				if(grid[x + i][y + j] == Cell.EMPTY || grid[x - i][y - j] == Cell.EMPTY)
					continue;
				if((grid[x + i][y + j] == Cell.BLUE_S || grid[x + i][y + j] == Cell.RED_S)
				&& (grid[x - i][y - j] == Cell.BLUE_S || grid[x - i][y - j] == Cell.RED_S)){
					lines.add(new int[] {x + i, y + j, x - i, y - j});
				}
			}
		}
		return lines;
	}
	
}
package sosGame;


public class GameLogic {
	public enum Cell {EMPTY, BLUE_S, BLUE_O, RED_S, RED_O}

	private int boardSize;
	private int gameMode;		//1 - simple, 2- general
	private String turn;		//"Blue" or "Red" player
	private char move;			// 'S' or 'O' Move

	private Cell[][] grid;

	public GameLogic(int boardSize) {
		this.boardSize = boardSize;
		grid = new Cell[boardSize][boardSize];
		initBoard();
	}

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
	
	//function to return the board size
	public int getBoardSize() {
		return boardSize;
	}
	
	//function to return info of a specific cell
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
			return grid[row][column];
		} else {
			return null;
		}
	}
	
	public int getGameMode() {return gameMode;}		//function to return game mode

	public String getTurn() {return turn;}			//function to return current player's turn
	
	public char getMove() {return move;}			//function to return current player's move
	
	//function to register info to a specific cell
	public void makeMove(int row, int column, char move) {
		if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && grid[row][column] == Cell.EMPTY) {
			if (turn == "Blue") {
				grid[row][column] = ((move == 'S') ? Cell.BLUE_S : Cell.BLUE_O);
				this.turn = "Red";
				this.move = 'S';
			}
			else {
				grid[row][column] = ((move == 'S') ? Cell.RED_S : Cell.RED_O);
				this.turn = "Blue";
				this.move = 'S';
			}
		}
	}
}

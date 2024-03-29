package sosGame;

import java.util.ArrayList;

public class GeneralGame extends GameLogic{
	
	public GeneralGame(int boardSize) {
		this.boardSize = boardSize;
		grid = new Cell[boardSize][boardSize];
		initBoard();
	}
	
	public int ShowPlay() {return plays;}
	
	//function to register info to a specific cell
	public ArrayList<int[]> makeMove(int row, int column, char move) {
		String currTurn = this.turn;
		ArrayList<int[]> drawQueue = new ArrayList<>();
		if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && grid[column][row] == Cell.EMPTY) {
			if (currTurn == "Blue") {
				grid[column][row] = (move == 'S') ? Cell.BLUE_S : Cell.BLUE_O;
				this.turn = "Red";
			}
			else {
				grid[column][row] = (move == 'S') ? Cell.RED_S : Cell.RED_O;
				this.turn = "Blue";
			}
				this.move = 'S';
		}
		plays++;		
		
		if(move == 'S')
			drawQueue.addAll(checkLineS(column, row));
		else
			drawQueue.addAll(checkLineO(column, row));
		
		if(currTurn == "Blue")
			bScore = bScore + drawQueue.size();
		else
			rScore = rScore + drawQueue.size();
		
		if(plays == boardSize * boardSize)
			checkWinGeneral();
		return drawQueue;
	}
	
	public void checkWinGeneral() {
		if(bScore > rScore)
			winner = "Blue";
		else if(rScore > bScore)
			winner = "Red";
		else
			winner = "Tie";
	}
}

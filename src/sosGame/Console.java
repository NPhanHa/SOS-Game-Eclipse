package sosGame;

import sosGame.GameLogic.Cell;
import sosGame.GameLogic;
import sosGame.GeneralGame;
import sosGame.SimpleGame;

public class Console {

	public GameLogic game;
	
	public Console(SimpleGame simpleGame) {
		this.game = simpleGame;
}
	
	public Console(GeneralGame generalGame) {
		this.game = generalGame;
	}

	public void displayBoard() {
		for(int i = 0; i < game.getBoardSize(); i ++) {
			System.out.print("--------");
		}
		
		for(int row = 0; row < game.getBoardSize(); row++) {
			System.out.println("");
			for(int column = 0; column < game.getBoardSize(); column++) {
				System.out.print("|" + symbol(game.getCell(row, column)));
			}
			System.out.println("||");
			
			for(int i = 0; i < game.getBoardSize(); i ++) {
				System.out.print("--------");
			}
		}
		System.out.println("\nScore: Blue:" + game.getBlueScore() + " Red:" + game.getRedScore());
		System.out.println("Winner: " + game.getWinner());
	}
	
	private String symbol(Cell cell) {
		if(cell == Cell.BLUE_O)
			return " O_(B) ";
		else if(cell == Cell.BLUE_S)
			return " S_(B) ";
		else if(cell == Cell.RED_O)
			return " O_(R) ";
		else if (cell == Cell.RED_S)
			return " S_(R) ";
		else
			return "       ";
	}
}
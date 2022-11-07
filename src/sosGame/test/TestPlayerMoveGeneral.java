package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.GeneralGame;
import sosGame.GameLogic;
import sosGame.GameLogic.Cell;
import sosGame.Board;
import sosGame.Console;

public class TestPlayerMoveGeneral {
	public GeneralGame testGeneral;
	public Console myConsole;
	
	@Before
	public void setUp() throws Exception {
		testGeneral = new GeneralGame(5);
		testGeneral.setGameMode(2);
	}

	@Test
	public void ValidMoveGeneral() {
		//new Board(testGeneral);
		testGeneral.makeMove(2, 3, 'O'); 		//Blue's turn
		testGeneral.makeMove(3, 2, 'O');      //Red's turn
		testGeneral.makeMove(4, 4, 'S');      //Blue's turn
		
		//test if cell (0,0) is empty
		assertEquals("", testGeneral.getCell(0, 0), Cell.EMPTY);
		
		//test if cell (2,3) (3,2) and (4,4) is correct
		assertEquals("", testGeneral.getCell(2, 3), Cell.BLUE_O);
		assertEquals("", testGeneral.getCell(3, 2), Cell.RED_O);
		assertEquals("", testGeneral.getCell(4, 4), Cell.BLUE_S);
		
		myConsole = new Console(testGeneral);
		System.out.println("\n\n\nValidMoveGeneral()");
		myConsole.displayBoard();
		
	}
	
	@Test
	public void InvalidMoveGeneral() {
		//new Board(testGeneral);
		testGeneral.makeMove(2, 3, 'O');	//Blue
		testGeneral.makeMove(2, 3, 'S');	//Red
		testGeneral.makeMove(3, 2, 'S');	//Red
		
		//Test of the value of cell (2,3) has been changed
		assertEquals("", testGeneral.getCell(2, 3), Cell.BLUE_O);
		
		//Test if blue player still keep their turn
		assertEquals("", testGeneral.getTurn(), "Blue");
		
		myConsole = new Console(testGeneral);
		System.out.println("InvalidMoveGeneral()");
		myConsole.displayBoard();
	}
}

package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.Board;
import sosGame.GameLogic;
import sosGame.GameLogic.Cell;

public class PlayerTest {
	public GameLogic testGameLogic;
	@Before
	public void setUp() throws Exception {
		testGameLogic = new GameLogic(5);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void TurnSwitching() {
		new Board(testGameLogic);
		testGameLogic.makeMove(2, 3, 'O'); 		//Blue's turn
		assertEquals("", testGameLogic.getTurn(), "Red");	//test if the turn is switched
		testGameLogic.makeMove(3, 2, 'O');      //Red's turn
		assertEquals("", testGameLogic.getTurn(), "Blue");	//test if the turn is switched
		testGameLogic.makeMove(4, 4, 'S');      //Blue's turn
	}

	@Test
	public void ValidMove() {
		new Board(testGameLogic);
		testGameLogic.makeMove(2, 3, 'O'); 		//Blue's turn
		testGameLogic.makeMove(3, 2, 'O');      //Red's turn
		testGameLogic.makeMove(4, 4, 'S');      //Blue's turn
		
		//test if cell (0,0) is empty
		assertEquals("", testGameLogic.getCell(0, 0), Cell.EMPTY);
		
		//test if cell (2,3) (3,2) and (4,4) is correct
		assertEquals("", testGameLogic.getCell(2, 3), Cell.BLUE_O);
		assertEquals("", testGameLogic.getCell(3, 2), Cell.RED_O);
		assertEquals("", testGameLogic.getCell(4, 4), Cell.BLUE_S);
	}
	
	@Test
	public void InvalidMove() {
		new Board(testGameLogic);
		testGameLogic.makeMove(2, 3, 'O');
		testGameLogic.makeMove(3, 2, 'S');
		testGameLogic.makeMove(2, 3, 'S');
		
		//Test of the value of cell (2,3) has been changed
		assertEquals("", testGameLogic.getCell(2, 3), Cell.BLUE_O);
		
		//Test if blue player still keep their turn
		assertEquals("", testGameLogic.getTurn(), "Blue");
	}
}

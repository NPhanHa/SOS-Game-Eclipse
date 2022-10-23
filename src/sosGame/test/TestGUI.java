package sosGame.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.Board;
import sosGame.GameLogic;

public class TestGUI {
	public GameLogic testGameLogic;
	
	@Before
	public void setUp() throws Exception {
		testGameLogic = new GameLogic(5);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEmptyBoard() {
		new Board(testGameLogic);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUsedBoard() {
		new Board(testGameLogic);
		testGameLogic.makeMove(0, 1, 'S');		//Blue's turn
		testGameLogic.makeMove(1, 2, 'O');		//Red's turn
		testGameLogic.makeMove(0, 3, 'S');		//Blue's turn
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

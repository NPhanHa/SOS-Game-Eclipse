package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.SimpleGame;
import sosGame.Console;

public class TestSimpleScore {
	public SimpleGame testSimpleScoreWin; 
	public SimpleGame testSimpleScoreTie;
	public Console myConsole;
	
	@Before
	public void setUp() throws Exception {
		testSimpleScoreWin = new SimpleGame(5);
		testSimpleScoreWin.setGameMode(1);
		testSimpleScoreTie = new SimpleGame(3);
		testSimpleScoreTie.setGameMode(1);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void simpleScore() {
		
		testSimpleScoreWin.makeMove(0, 1, 'S');		//Blue
		testSimpleScoreWin.makeMove(0, 3, 'S');		//Red
		testSimpleScoreWin.makeMove(0, 2, 'O');		//Blue
		
		//test if blue has more point than red, and if winner is blue
		assertEquals("", testSimpleScoreWin.getBlueScore(), 1);
		assertEquals("", testSimpleScoreWin.getRedScore(), 0);
		assertEquals("", testSimpleScoreWin.getWinner(), "Blue");
		
		myConsole = new Console(testSimpleScoreWin);
		System.out.println("\n\n\nsimpleScore())");
		myConsole.displayBoard();
		
	}
	
	@Test
	public void simpleTie() {
		
		testSimpleScoreTie.makeMove(0, 0, 'S');		//Blue
		testSimpleScoreTie.makeMove(0, 1, 'S');		//Red
		testSimpleScoreTie.makeMove(0, 2, 'S');		//Blue
		testSimpleScoreTie.makeMove(1, 0, 'S');		//Red
		testSimpleScoreTie.makeMove(1, 1, 'S');		//Blue
		testSimpleScoreTie.makeMove(1, 2, 'S');		//Red
		testSimpleScoreTie.makeMove(2, 0, 'S');		//Blue
		testSimpleScoreTie.makeMove(2, 1, 'S');		//Red
		testSimpleScoreTie.makeMove(2, 2, 'S');		//Blue
		
		//test if blue has same point than red, and game ends with a tie
		assertEquals("", testSimpleScoreTie.getBlueScore(), 0);
		assertEquals("", testSimpleScoreTie.getRedScore(), 0);
		assertEquals("", testSimpleScoreTie.getWinner(), "Tie");
		
		myConsole = new Console(testSimpleScoreTie);
		System.out.println("simpleTie()");
		myConsole.displayBoard();
	}

}

package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.GeneralGame;
import sosGame.Board;
import sosGame.Console;

public class TestGeneralScore {
	public GeneralGame testGeneralScoreWin; 
	public GeneralGame testGeneralScoreTie;
	public Console myConsole;
	
	@Before
	public void setUp() throws Exception {
		testGeneralScoreWin = new GeneralGame(3);
		testGeneralScoreWin.setGameMode(2);
		testGeneralScoreTie = new GeneralGame(3);
		testGeneralScoreTie.setGameMode(2);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void generalScore() {
		
		testGeneralScoreWin.makeMove(0, 0, 'S');		//Blue
		testGeneralScoreWin.makeMove(0, 2, 'S');		//Red
		testGeneralScoreWin.makeMove(0, 1, 'O');		//Blue
		testGeneralScoreWin.makeMove(2, 0, 'S');		//Red
		testGeneralScoreWin.makeMove(2, 1, 'O');		//Blue
		testGeneralScoreWin.makeMove(2, 2, 'S');		//Red
		testGeneralScoreWin.makeMove(1, 0, 'O');		//Blue
		testGeneralScoreWin.makeMove(1, 2, 'O');		//Red
		testGeneralScoreWin.makeMove(1, 1, 'O');		//Blue
		
		//test if blue has more point than red, and if winner is blue
		assertEquals("", testGeneralScoreWin.getBlueScore(), 4);
		assertEquals("", testGeneralScoreWin.getRedScore(), 2);
		assertEquals("", testGeneralScoreWin.getWinner(), "Blue");
		
		myConsole = new Console(testGeneralScoreWin);
		System.out.println("\n\n\ngeneralScore())");
		myConsole.displayBoard();	
	}
	
	@Test
	public void generalTie() {
		
		testGeneralScoreTie.makeMove(0, 0, 'S');		//Blue
		testGeneralScoreTie.makeMove(0, 2, 'S');		//Red
		testGeneralScoreTie.makeMove(0, 1, 'O');		//Blue
		testGeneralScoreTie.makeMove(2, 0, 'S');		//Red
		testGeneralScoreTie.makeMove(2, 1, 'O');		//Blue
		testGeneralScoreTie.makeMove(2, 2, 'S');		//Red
		testGeneralScoreTie.makeMove(1, 0, 'O');		//Blue
		testGeneralScoreTie.makeMove(1, 2, 'O');		//Red
		testGeneralScoreTie.makeMove(1, 1, 'S');		//Blue
		
		//test if blue has same point than red, and game ends with a tie
		assertEquals("", testGeneralScoreTie.getBlueScore(), 2);
		assertEquals("", testGeneralScoreTie.getRedScore(), 2);
		assertEquals("", testGeneralScoreTie.getWinner(), "Tie");
		
		myConsole = new Console(testGeneralScoreTie);
		System.out.println("generalTie()");
		myConsole.displayBoard();
	}

}

package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.AI;
import sosGame.Console;
import sosGame.SimpleGame;

public class TestComputerSimple {
	public SimpleGame testSimpleGame;
	public Console myConsole;
	public AI myAI;

	@Before
	public void SetUp() throws Exception {
		testSimpleGame = new SimpleGame(6);
		testSimpleGame.setGameMode(1);
		myAI = new AI(testSimpleGame);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	//Testing the algorithm if computer is trying to win
	public void testComputerWin() {
		
		testSimpleGame.makeMove(0, 1, 'S');
		myAI.computerPlaySimple(0, 1);
		testSimpleGame.makeMove(0, 3, 'S');
		myAI.computerPlaySimple(0, 3);
		
		myConsole = new Console(testSimpleGame);
		myConsole.displayBoard();
	}
}

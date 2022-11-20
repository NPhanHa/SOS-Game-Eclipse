package sosGame.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sosGame.AI;
import sosGame.Console;
import sosGame.SimpleGame;

public class TestComputerGeneral {
	public SimpleGame testGeneralGame;
	public Console myConsole;
	public AI myAI;

	@Before
	public void SetUp() throws Exception {
		testGeneralGame = new SimpleGame(3);
		testGeneralGame.setGameMode(1);
		myAI = new AI(testGeneralGame);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	//Testing the algorithm if computer is trying to win
	public void testComputerWin() {
		
		testGeneralGame.makeMove(0, 0, 'S');
		myAI.computerPlaySimple(0, 0);
		testGeneralGame.makeMove(0, 2, 'S');
		myAI.computerPlaySimple(0, 2);
		testGeneralGame.makeMove(2, 2, 'S');
		myAI.computerPlaySimple(2, 2);
		testGeneralGame.makeMove(2, 0, 'S');
		myAI.computerPlaySimple(2, 0);
		testGeneralGame.makeMove(1, 1, 'S');
		
		myConsole = new Console(testGeneralGame);
		myConsole.displayBoard();
	}
}

package sosGame;

import java.util.ArrayList;
import java.util.Random;

import sosGame.GameLogic.Cell;

public class AI {
	private int gameMode;
	private int boardSize;
	private Random random;
	private SimpleGame simpleGame;
	private GeneralGame generalGame;
	private int pastX, pastY;
	
	AI(SimpleGame simpleGame){
		this.gameMode = 1;
		this.simpleGame = simpleGame;
		this.boardSize = simpleGame.getBoardSize();
		random = new Random();
	}
	
	AI(GeneralGame generalGame){
		this.gameMode = 2;
		this.generalGame = generalGame;
		this.boardSize = generalGame.getBoardSize();
		random = new Random();
	}
	
	public int getPastX() {return pastX;}
	
	public int getPastY() {return pastY;}
	
	//Function let computer play in simple mode
	public ArrayList<int[]> computerPlaySimple(int x, int y) {
		System.out.println("Past coord: " + x + " " + y); 		//test
		
		//Check if previous play was S
		if(simpleGame.getCell(x, y) == Cell.BLUE_S ||
		   simpleGame.getCell(x, y) == Cell.RED_S) {
			System.out.println("Past S");			//test
			for(int i = -1; i <= 1; ++i) {
				for(int j = -1; j <= 1; ++j) {
					if(i == 0 && j == 0)
						continue;
					if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
						continue;
					if(x + i + i >= boardSize || x + i + i < 0 || y + j + j >= boardSize || y + j + j < 0)
						continue;
					if(simpleGame.getCell(x + i, y + j) == Cell.BLUE_O || 
					   simpleGame.getCell(x + i, y + j) == Cell.RED_O) {
						
						if(simpleGame.getCell(x + i + i, y + j + j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
						   return simpleGame.makeMove(x + i + i, y + j + j, 'S');
						}
						else
							continue;
					}
					if(simpleGame.getCell(x + i + i, y + j + j) == Cell.BLUE_S || 
					   simpleGame.getCell(x + i + i, y + j + j) == Cell.RED_S) {
								
						if(simpleGame.getCell(x + i, y + j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
						   return simpleGame.makeMove(x + i, y + j, 'O');
						}
						else
							continue;
					}
				}
			}
		}
		
		//Check if previous play was O
		if(simpleGame.getCell(x, y) == Cell.BLUE_O ||
		   simpleGame.getCell(x, y) == Cell.RED_O) {
			System.out.println("Past O");			//test
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
						continue;
					if(x - i >= boardSize || x - i < 0 || y - j >= boardSize || y - j < 0)
						continue;
					if(simpleGame.getCell(x + i, y + j) == Cell.BLUE_S ||
					   simpleGame.getCell(x + i, y + j) == Cell.RED_S) {
						if(simpleGame.getCell(x - i, y - j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
							return simpleGame.makeMove(x - i, y - j, 'S');
						}
						else
							continue;
					}
				}
			}
		}
		return PlayRandom();
	}
	
	//Function let computer play in general mode
	public ArrayList<int[]> computerPlayGeneral(int x, int y) {
		System.out.println("Past coord: " + x + " " + y); 		//test
		
		//Check if previous play was S
		if(generalGame.getCell(x, y) == Cell.BLUE_S ||
			generalGame.getCell(x, y) == Cell.RED_S) {
			System.out.println("Past S");			//test
			for(int i = -1; i <= 1; ++i) {
				for(int j = -1; j <= 1; ++j) {
					if(i == 0 && j == 0)
						continue;
					if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
						continue;
					if(x + i + i >= boardSize || x + i + i < 0 || y + j + j >= boardSize || y + j + j < 0)
						continue;
					if(generalGame.getCell(x + i, y + j) == Cell.BLUE_O || 
					   generalGame.getCell(x + i, y + j) == Cell.RED_O) {
						
						if(generalGame.getCell(x + i + i, y + j + j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
						   return generalGame.makeMove(x + i + i, y + j + j, 'S');
						}
						else
							continue;
					}
					if(generalGame.getCell(x + i + i, y + j + j) == Cell.BLUE_S || 
					   generalGame.getCell(x + i + i, y + j + j) == Cell.RED_S) {
								
						if(generalGame.getCell(x + i, y + j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
						   return generalGame.makeMove(x + i, y + j, 'O');
						}
						else
							continue;
					}
				}
			}
		}
		
		//Check if previous play was O
		if(generalGame.getCell(x, y) == Cell.BLUE_O ||
		   generalGame.getCell(x, y) == Cell.RED_O) {
			System.out.println("Past O");			//test
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(x + i >= boardSize || x + i < 0 || y + j >= boardSize || y + j < 0)
						continue;
					if(x - i >= boardSize || x - i < 0 || y - j >= boardSize || y - j < 0)
						continue;
					if(generalGame.getCell(x + i, y + j) == Cell.BLUE_S ||
					   generalGame.getCell(x + i, y + j) == Cell.RED_S) {
						if(generalGame.getCell(x - i, y - j) == Cell.EMPTY) {
							System.out.println("Got it right");		//test
							return generalGame.makeMove(x - i, y - j, 'S');
						}
						else
							continue;
					}
				}
			}
		}
		return PlayRandom();
	}
	
	public ArrayList<int[]> PlayRandom() {
		int randX;
		int randY;
		int randMove;	//0 - O move, 1 - S move
		ArrayList<int[]> temp;
		String computerMove = "OS";
		while(true) {
			randX = random.nextInt(boardSize);
			randY = random.nextInt(boardSize);
			randMove = random.nextInt(2);
			
			if(gameMode == 1) {
				if(simpleGame.getCell(randX, randY) == Cell.EMPTY) {
					System.out.println("Random Move: " + randX + " " + randY);		//test
					System.out.println("Random Char: " + computerMove.charAt(randMove));  //test
					pastX = randX;
					pastY = randY;
					temp = simpleGame.makeMove(randX, randY, computerMove.charAt(randMove));
					break;
				}
			}
			else {
				if(generalGame.getCell(randX, randY) == Cell.EMPTY) {
					System.out.println("Random Move: " + randX + " " + randY);		//test
					System.out.println("Random Char: " + computerMove.charAt(randMove));  //test
					pastX = randX;
					pastY = randY;
					temp = generalGame.makeMove(randX, randY, computerMove.charAt(randMove));
					break;
				}
			}
		}
		return temp;
	}
}

package sosGame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import sosGame.GameLogic;
import sosGame.GameLogic.Cell;

@SuppressWarnings("serial")
public class Board extends JFrame {

	public static final int CELL_SIZE = 100; 
	public static final int CELL_CENTER = CELL_SIZE / 2;
	
	public static final int X_MARGIN = 100;
	public static final int Y_MARGIN = 100;

	private int CANVAS_WIDTH; 
	private int CANVAS_HEIGHT;
	
	private int boardSize;
	private char move = 'S';
	private int gameMode;
	private String currTurn;
	private ArrayList<int[]> drawQueue = new ArrayList<>();
	private ArrayList<String> turnQueue = new ArrayList<>();
	private int bScore = 0;
	private int rScore = 0;
	public int endGameOption = 0;	//1 = new game

	private GameBoardCanvas gameBoardCanvas; 
	private JLabel gameStatusBar; 
	
	private JPanel bluePanel;
	private JLabel blueLabel;
	private ButtonGroup blueGroup;
	private JRadioButton blueSRadio;
	private JRadioButton blueORadio;
	
	private JPanel redPanel;
	private JLabel redLabel;
	private ButtonGroup redGroup;
	private JRadioButton redSRadio;
	private JRadioButton redORadio;
	
	private JLabel scoreTitle;
	private JLabel blueDisplayScore;
	private JLabel redDisplayScore;
	public static JButton newGameButton;

	private Board myBoard;
	private GUI myGUI;
	private GameLogic gameLogic;
	private SimpleGame simpleGame;
	private GeneralGame	generalGame;
	
	public Board(SimpleGame simpleGame) {
		this.simpleGame = simpleGame;
		boardSize = simpleGame.getBoardSize();
		gameMode = simpleGame.getGameMode();
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);
	}
	
	public Board(GeneralGame generalGame) {
		this.generalGame = generalGame;
		boardSize = generalGame.getBoardSize();
		gameMode = generalGame.getGameMode();
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}
	
	private void setContentPane(){
		gameBoardCanvas = new GameBoardCanvas();  
		CANVAS_WIDTH  = CELL_SIZE * boardSize + 2 * X_MARGIN;  
		CANVAS_HEIGHT = CELL_SIZE * boardSize + 2 * Y_MARGIN;  

		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		
		gameStatusBar = new JLabel("", SwingConstants.CENTER);
		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setPreferredSize(new Dimension(100,30));
		
		//Setup blueLabel and blueGroup
		blueLabel  = new JLabel("Blue Player: ");
		blueGroup = new ButtonGroup();
		
		//Setup S Radio button for blue player
		blueSRadio = new JRadioButton("S");
		blueGroup.add(blueSRadio);
		blueSRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((gameMode == 1 && simpleGame.getTurn() == "Blue") 
				|| (gameMode == 2 && generalGame.getTurn()== "Blue")) 
					move = 'S';
			}
		});
		
		//Setup O Radio button for blue player
		blueORadio = new JRadioButton("O");
		blueGroup.add(blueORadio);
		blueORadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((gameMode == 1 && simpleGame.getTurn() == "Blue")
				|| (gameMode == 2 && generalGame.getTurn()== "Blue"))
					move = 'O';
			}
		});
		
		//Setup panel for blue player and add its components
		bluePanel  = new JPanel();
		bluePanel.add(blueLabel);
		bluePanel.add(blueSRadio);
		bluePanel.add(blueORadio);
		
		//Set S Radio button for blue player selected as default
		blueSRadio.setSelected(true);
		
		//Setup redLabel and redGroup
		redLabel  = new JLabel("Red Player: ");
		redGroup = new ButtonGroup();
		
		//Setup S Radio button for red player
		redSRadio = new JRadioButton("S");
		redGroup.add(redSRadio);
		redSRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((gameMode == 1 && simpleGame.getTurn() == "Red")
				|| (gameMode == 2 && generalGame.getTurn()== "Red"))		
					move = 'S';
			}
		});
		
		//Setup O Radio button for red player
		redORadio = new JRadioButton("O");
		redSRadio.setEnabled(false);
		redORadio.setEnabled(false);
		redGroup.add(redORadio);
		redORadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((gameMode == 1 && simpleGame.getTurn() == "Red")
				|| (gameMode == 2 && generalGame.getTurn()== "Red"))		
					move = 'O';
			}
		});
		
		//Setup panel for red player and add its components
		redPanel  = new JPanel();
		redPanel.add(redLabel);
		redPanel.add(redSRadio);
		redPanel.add(redORadio);
		
		//Set S Radio button for red player selected as default
		redSRadio.setSelected(true);
		
		scoreTitle = new JLabel("SCORE:");
		scoreTitle.setForeground(Color.BLACK);
		scoreTitle.setBounds(CANVAS_WIDTH / 2, 10, 20, 15);
		gameBoardCanvas.add(scoreTitle);
		
		blueDisplayScore = new JLabel(String.valueOf(bScore));
		blueDisplayScore.setForeground(Color.BLUE);
		gameBoardCanvas.add(blueDisplayScore);
		
		redDisplayScore = new JLabel(String.valueOf(rScore));
		redDisplayScore.setForeground(Color.RED);
		gameBoardCanvas.add(redDisplayScore);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END); 
		contentPane.add(bluePanel, BorderLayout.WEST);
		contentPane.add(redPanel, BorderLayout.EAST);
		contentPane.add(newGameButton, BorderLayout.PAGE_START);
	}
	
	//function to swap 
	private void SwapEnableButtons(char buttons) {
		blueSRadio.setSelected(true);
		blueORadio.setSelected(false);
		redSRadio.setSelected(true);
		redORadio.setSelected(false);
		
		if(buttons == 'R') {
			blueSRadio.setEnabled(false);
			blueORadio.setEnabled(false);
			redSRadio.setEnabled(true);
			redORadio.setEnabled(true);
			System.out.println("Switch to Red");		//test
		}
		else {
			blueSRadio.setEnabled(true);
			blueORadio.setEnabled(true);
			redSRadio.setEnabled(false);
			redORadio.setEnabled(false);
			System.out.println("Switch to Blue");		//test
		}
	}
	

	class GameBoardCanvas extends JPanel {

		GameBoardCanvas(){

			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					int selectCol = -1; 
					int selectRow = -1;
					
					if(e.getX() >= X_MARGIN && e.getX() <= CANVAS_WIDTH - X_MARGIN 
					&& e.getY() >= Y_MARGIN && e.getY() <= CANVAS_HEIGHT - Y_MARGIN) {
						selectCol = (e.getX() - X_MARGIN) / CELL_SIZE;
						selectRow = (e.getY() - Y_MARGIN) / CELL_SIZE;
					}
					
					if(selectCol == -1 || selectRow == -1) {
						return;
					}
					
					if(gameMode == 1) {
						currTurn = simpleGame.getTurn();
						if (simpleGame.getCell(selectRow, selectCol) == Cell.EMPTY) {
							if (currTurn == "Red")
								SwapEnableButtons('B');
							else
								SwapEnableButtons('R');
							System.out.println(selectRow + " " + selectCol);	//test
							System.out.println(simpleGame.getTurn() + " " + move);		//test
							ArrayList<int[]>temp = simpleGame.makeMove(selectRow, selectCol, move);
							while(!temp.isEmpty()) {
								drawQueue.add(temp.remove(0));
								turnQueue.add(currTurn);
							}
							repaint();
							if(simpleGame.getWinner() != "") {
								winnerPopup(simpleGame.getWinner());
								
							}
							move = 'S';			//set move back to default
						}
						else 
							JOptionPane.showMessageDialog(null, "This cell is occupied!\nPlease choose an empty cell.",
									  "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						currTurn = generalGame.getTurn();
						if (generalGame.getCell(selectRow, selectCol) == Cell.EMPTY) {
							if (currTurn == "Red")
								SwapEnableButtons('B');
							else
								SwapEnableButtons('R');
							System.out.println(selectRow + " " + selectCol);	//test
							System.out.println(generalGame.getTurn() + " " + move);		//test
							ArrayList<int[]>temp = generalGame.makeMove(selectRow, selectCol, move);
							while(!temp.isEmpty()) {
								drawQueue.add(temp.remove(0));
								turnQueue.add(currTurn);
							}
							repaint();
							if(generalGame.getWinner() != "")
								winnerPopup(generalGame.getWinner());
							move = 'S';			//set move back to default
						}
						else
							JOptionPane.showMessageDialog(null, "This cell is occupied!\nPlease choose an empty cell.",
									  "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}

		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			drawScoringLines(g);
			if(gameMode == 1) {
				DisplayStatus(simpleGame);
				DisplayScore(simpleGame);
			}
			else {
				DisplayStatus(generalGame);
				DisplayScore(generalGame);
			}
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.BLACK);
			((Graphics2D) g).setStroke(new BasicStroke(3));
			
			//Drawing the grid
			for (int row = 0; row < boardSize + 1; ++row) {
				g.drawLine(X_MARGIN,Y_MARGIN + row * CELL_SIZE,
						   X_MARGIN + boardSize * CELL_SIZE, Y_MARGIN + row * CELL_SIZE);
			}
			for (int col = 0; col < boardSize + 1; ++col) {
				g.drawLine(X_MARGIN + col * CELL_SIZE, Y_MARGIN,
						   X_MARGIN + col * CELL_SIZE, Y_MARGIN + boardSize * CELL_SIZE);
			}
		}

		private void drawBoard(Graphics g) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			for (int row = 0; row < boardSize; ++row) {
				for (int col = 0; col < boardSize; ++col) {
					int x = X_MARGIN + CELL_CENTER + col * CELL_SIZE - 15;
					int y = Y_MARGIN + CELL_CENTER + row * CELL_SIZE + 15;
					if ((gameMode == 1 && simpleGame.getCell(row, col) == Cell.BLUE_S)
					||  (gameMode == 2 && generalGame.getCell(row, col)== Cell.BLUE_S)){
						g.setColor(Color.BLUE);
						g.drawString(String.valueOf('S'), x, y);
						
					}
					else if ((gameMode == 1 && simpleGame.getCell(row, col) == Cell.BLUE_O) 
						 ||  (gameMode == 2 && generalGame.getCell(row, col)== Cell.BLUE_O)){
						g.setColor(Color.BLUE);
						g.drawString(String.valueOf('O'), x, y);
					}
					else if ((gameMode == 1 && simpleGame.getCell(row, col) == Cell.RED_S) 
						 ||  (gameMode == 2 && generalGame.getCell(row, col)== Cell.RED_S)){
						g.setColor(Color.RED);
						g.drawString(String.valueOf('S'), x, y);
					}
					else if ((gameMode == 1 && simpleGame.getCell(row, col) == Cell.RED_O)
						 ||	 (gameMode == 2 && generalGame.getCell(row, col)== Cell.RED_O)){
						g.setColor(Color.RED);
						g.drawString(String.valueOf('O'), x, y);
					}
					else
						continue;
				}
			}
		}
		
		private void drawScoringLines(Graphics g) {
			if(drawQueue == null)
				return;
			
			int[] coords;
			int firstX, firstY, secondX, secondY;
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));			
			for(int i = 0; i < drawQueue.size(); i++) {
				if(turnQueue.get(i) == "Blue")
					g.setColor(Color.BLUE);
				else
					g.setColor(Color.RED);
				coords = drawQueue.get(i);
				firstX = X_MARGIN + CELL_CENTER + coords[0] * CELL_SIZE;
				firstY = Y_MARGIN + CELL_CENTER + coords[1] * CELL_SIZE;
				secondX = X_MARGIN + CELL_CENTER + coords[2] * CELL_SIZE;
				secondY = Y_MARGIN + CELL_CENTER + coords[3] * CELL_SIZE;
				
				g.drawLine(firstX, firstY, secondX, secondY);
				System.out.println("drawing lines");		//test
			}
		}
		
		//function to change current game's status
		private void DisplayStatus(GameLogic gameLogic) {
			String currMode;
			currMode = ((gameLogic.getGameMode() == 1) ? "Simple Mode" : "General Mode");
			gameStatusBar.setText("Current Player: " + gameLogic.getTurn() + "    ||    " + "Game Mode : " + currMode);
		}
		
		private void DisplayScore(GameLogic gameLogic) {
			blueDisplayScore.setText(String.valueOf(gameLogic.getBlueScore()));
			redDisplayScore.setText(String.valueOf(gameLogic.getRedScore()));
		}
		
		private void winnerPopup(String winner) {
			if(winner == "Tie")
				JOptionPane.showMessageDialog(null," Game Over! The game has ended with a tie!",
					  "Game Over!", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, winner + " player won the game!",
					  "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
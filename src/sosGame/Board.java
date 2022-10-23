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

import sosGame.GameLogic;
import sosGame.GameLogic.Cell;

@SuppressWarnings("serial")
public class Board extends JFrame {

	public static final int CELL_SIZE = 100; 
	public static final int CELL_CENTER = CELL_SIZE / 2;
	
	public static final int X_MARGIN = 20;
	public static final int Y_MARGIN = 20;

	private int CANVAS_WIDTH; 
	private int CANVAS_HEIGHT;
	
	private int boardSize;
	private char move;

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

	private GameLogic gameLogic;

	public Board(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		boardSize = gameLogic.getBoardSize();
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("Tic Tac Toe");
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
				if(gameLogic.getTurn() == "Blue") {move = 'S';}
			}
		});
		
		//Setup O Radio button for blue player
		blueORadio = new JRadioButton("O");
		blueGroup.add(blueORadio);
		blueORadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameLogic.getTurn() == "Blue") {move = 'O';}
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
				if(gameLogic.getTurn() == "Red") {move = 'S';}
			}
		});
		
		//Setup O Radio button for red player
		redORadio = new JRadioButton("O");
		redSRadio.setEnabled(false);
		redORadio.setEnabled(false);
		redGroup.add(redORadio);
		redORadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameLogic.getTurn() == "Red") {move = 'O';}
			}
		});
		
		//Setup panel for red player and add its components
		redPanel  = new JPanel();
		redPanel.add(redLabel);
		redPanel.add(redSRadio);
		redPanel.add(redORadio);
		
		//Set S Radio button for red player selected as default
		redSRadio.setSelected(true);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END); 
		contentPane.add(bluePanel, BorderLayout.WEST);
		contentPane.add(redPanel, BorderLayout.EAST);
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
			
			//Set default move
			move = 'S';

			
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					int selectRow = (e.getY() - Y_MARGIN) / CELL_SIZE;
					int selectCol = (e.getX() - X_MARGIN) / CELL_SIZE;
											
					if (gameLogic.getCell(selectRow, selectCol) == Cell.EMPTY) {
						if (gameLogic.getTurn() == "Red")
							SwapEnableButtons('B');
						else
							SwapEnableButtons('R');
						System.out.println(selectRow + " " + selectCol);	//test
						System.out.println(gameLogic.getTurn() + " " + move);		//test
						gameLogic.makeMove(selectRow, selectCol, move);
						move = 'S';			//set move back to default
						repaint();
					}
					else {
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
			DisplayStatus(gameLogic);
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
					if (gameLogic.getCell(row, col) == Cell.BLUE_S) {
						g.setColor(Color.BLUE);
						g.drawString(String.valueOf('S'), x, y);
						
					}
					else if (gameLogic.getCell(row, col) == Cell.BLUE_O) {
						g.setColor(Color.BLUE);
						g.drawString(String.valueOf('O'), x, y);
					}
					else if (gameLogic.getCell(row, col) == Cell.RED_S) {
						g.setColor(Color.RED);
						g.drawString(String.valueOf('S'), x, y);
					}
					else if (gameLogic.getCell(row, col) == Cell.RED_O) {
						g.setColor(Color.RED);
						g.drawString(String.valueOf('O'), x, y);
					}
					else
						continue;
				}
			}
		}
		
		//function to change current game's status
		private void DisplayStatus(GameLogic gameLogic) {
			String currMode;
			currMode = ((gameLogic.getGameMode() == 1) ? "Simple Mode" : "General Mode");
			gameStatusBar.setText("Current Player: " + gameLogic.getTurn() + "    ||    " + "Game Mode : " + currMode);
		}

	}
}
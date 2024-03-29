//Project: SOS_Game
//Instructor: Leo Chen
//Student: Phan Ha
//CS 499

package sosGame;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Canvas;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.Action;

import sosGame.Board;
import sosGame.GameLogic;

public class GUI extends JFrame {
	
	private int boardSize;
	private int gameMode;		//1-Simple Mode, 2-General Mode
	public static int endGameOption = 0;	//1 = new game
	public static int compPlayer = 0;		//0 - no computer; 1 - blue computer 
											//2 - red computer; 3 - two computers
	public static int recordOption = 0;
	
	public SimpleGame myGameSimple;
	private GeneralGame myGameGeneral;
	public Board myBoard;
	
	//Define panel and its components
	private JPanel uiPanel;
	private JTextField sizeTextField;
	private JLabel gameLabel;
	private JLabel requirement;
	private JLabel boardSizeLabel;
	private JLabel computerPlayerLabel;
	private JRadioButton simpleGameRadio;
	private JRadioButton generalGameRadio;
	private JCheckBox blueCompCheck;
	private JCheckBox redCompCheck;
	private final ButtonGroup gameModeGroup = new ButtonGroup();
	private JButton startGameButton;
	private JButton clearButton;
	private JCheckBox recordCheck;
	private JButton replayButton;
	
	 /*Create Frame */
	public GUI() {
		//set default value for boardSize and gameMode
		boardSize = 0;
		gameMode = 1;
		
		//setup panel
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		uiPanel = new JPanel();
		uiPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(uiPanel);
		uiPanel.setLayout(null);
		
		//setup label for game mode
		gameLabel = new JLabel("Game Mode:");
		gameLabel.setBounds(10, 10, 100, 15);
		uiPanel.add(gameLabel);
		
		//setup radio buttons for simple game mode
		simpleGameRadio = new JRadioButton("Simple game");
		simpleGameRadio.setSelected(true);
		simpleGameRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMode = 1;
			}
		});
		gameModeGroup.add(simpleGameRadio);				//add radio button to group
		simpleGameRadio.setBounds(10, 30, 120, 15);
		uiPanel.add(simpleGameRadio);
		
		//setup radio buttons for general game mode
		generalGameRadio = new JRadioButton("General game");
		generalGameRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMode = 2;
			}
		});
		gameModeGroup.add(generalGameRadio);			//add radio button to group
		generalGameRadio.setBounds(10, 50, 130, 15);
		uiPanel.add(generalGameRadio);
		
		//setup label for board size
		boardSizeLabel = new JLabel("Board Size:");
		boardSizeLabel.setBounds(250, 10, 100, 15);
		uiPanel.add(boardSizeLabel);
		
		//setup the text field for user to type in board size
		sizeTextField = new JTextField();
		sizeTextField.setBounds(340, 10, 40, 15);
		uiPanel.add(sizeTextField);
		sizeTextField.setColumns(10);
		
		//setup the label for board size requirement
		requirement = new JLabel("Select size from 3 to 10");
		requirement.setBounds(220, 30, 200, 15);
		uiPanel.add(requirement);
		
		//setup label for computer players
		computerPlayerLabel = new JLabel("Computer Player:");
		computerPlayerLabel.setBounds(10, 90, 150, 15);
		uiPanel.add(computerPlayerLabel);
		
		//setup check box for blue computer player
		blueCompCheck = new JCheckBox("Blue Computer");
		blueCompCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if	 (blueCompCheck.isSelected() == true) {compPlayer += 1;}
				else {compPlayer -= 1;}
			}
			});
		blueCompCheck.setBounds(10, 110, 150, 15);
		uiPanel.add(blueCompCheck);
		
		//setup check box for red computer player
		redCompCheck = new JCheckBox("Red Computer");
		redCompCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if	 (redCompCheck.isSelected() == true) {compPlayer += 2;}
				else {compPlayer -= 2;}
			}
		});
		redCompCheck.setBounds(10, 130, 150, 15);
		uiPanel.add(redCompCheck);
				
		recordCheck = new JCheckBox("Record Game");
		recordCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if   (recordCheck.isSelected() == true) {recordOption += 1;}
				else {recordOption -= 1;}
			}
		});
		recordCheck.setBounds(220, 110, 150, 15);
		uiPanel.add(recordCheck);
		
				
		Board.newGameButton = new JButton("New Game");
		Board.newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myBoard.dispose();
				setVisible(true);
			}
		});
		
		//setup the button to start game
		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					boardSize = Integer.parseInt(sizeTextField.getText());
					if(boardSize >= 3 && boardSize <= 10) {
							if(gameMode == 1) {
								myGameSimple = new SimpleGame(boardSize);
								myGameSimple.setGameMode(gameMode);
								myBoard = new Board(myGameSimple, 0);
							}
							else {
								myGameGeneral = new GeneralGame(boardSize);
								myGameGeneral.setGameMode(gameMode);
								myBoard = new Board(myGameGeneral, 0);
							}
							setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid board size!\nPlease choose a valid board size.",
								  "Invalid Value", JOptionPane.INFORMATION_MESSAGE);
					}
			}
		});
		startGameButton.setBounds(50, 200, 140, 25);
		uiPanel.add(startGameButton);
		
		//setup the button to clear all the options and return to default setting
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeTextField.setText("");
				simpleGameRadio.setSelected(true);
				gameMode = 1;
			}
		});
		clearButton.setBounds(120, 250, 140, 25);
		uiPanel.add(clearButton);
		
		replayButton = new JButton("Replay Game");
		replayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == replayButton) {
					JFileChooser fileChooser = new JFileChooser();
					int response = fileChooser.showOpenDialog(null);
					if(response == JFileChooser.APPROVE_OPTION) {
						recordCheck.setSelected(false);
						recordOption = 0;
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						readFile(file);
					}
				}
			}
		});
		replayButton.setBounds(200, 200, 140, 25);
		uiPanel.add(replayButton);
	}
	
		//Launch the application
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
						GUI frame = new GUI();
						frame.setVisible(true);
				}
			});
		}
		
		private void readFile(File file) {
			String fileName = file.getName();
			String line;
			int replayBoardSize, replayGameMode;
			char move;
			ArrayList<int[]> coordLog = new ArrayList<>();
			ArrayList<Character> moveLog = new ArrayList<>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				
				line = reader.readLine();
				replayBoardSize = Character.getNumericValue(line.charAt(12));
				line = reader.readLine();
				replayGameMode = Character.getNumericValue(line.charAt(11));
				line = reader.readLine();
				compPlayer = Character.getNumericValue(line.charAt(17));
				
				while((line = reader.readLine()) != null) {
					coordLog.add(new int[]  {Character.getNumericValue(line.charAt(7)), Character.getNumericValue(line.charAt(9))});
					move = line.charAt(12);
					moveLog.add(move);
				}
				
				if(fileName.indexOf("Simple") != -1) {
					myGameSimple = new SimpleGame(replayBoardSize);
					myGameSimple.setGameMode(replayGameMode);
					myBoard = new Board(myGameSimple, 1);
				}
				else if(fileName.indexOf("General") != -1) {
					myGameGeneral = new GeneralGame(replayBoardSize);
					myGameGeneral.setGameMode(replayGameMode);
					myBoard = new Board(myGameGeneral, 1);
				}
				setVisible(false);
				myBoard.setCoordLog(coordLog);
				myBoard.setMoveLog(moveLog);
				myBoard.replayGame();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}



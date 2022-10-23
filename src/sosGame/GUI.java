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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
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
	
	private GameLogic myGame;
	private Board myBoard;
	
	//Define panel and its components
	private JPanel uiPanel;
	private JTextField sizeTextField;
	private JLabel gameLabel;
	private JLabel requirement;
	private JLabel boardSizeLabel;
	private JRadioButton simpleGameRadio;
	private JRadioButton generalGameRadio;
	private final ButtonGroup gameModeGroup = new ButtonGroup();
	private JButton startGameButton;
	private JButton clearButton;
	
	 /*Create Frame */
	public GUI() {
		//set default value for boardSize and gameMode
		boardSize = 0;
		gameMode = 1;
		
		//setup panel
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
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
		
		//setup the button to start game
		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boardSize = Integer.parseInt(sizeTextField.getText());
					if(boardSize >= 3 && boardSize <= 10) {
						myGame = new GameLogic(boardSize);
						myGame.setGameMode(gameMode);
						myBoard = new Board(myGame);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid board size!\nPlease choose a valid board size.",
								  "Invalid Value", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(Exception err) {
					
				}
			}
		});
		startGameButton.setBounds(50, 90, 120, 25);
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
		clearButton.setBounds(200, 90, 120, 25);
		uiPanel.add(clearButton);
	}
	
		//Launch the application
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI frame = new GUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}



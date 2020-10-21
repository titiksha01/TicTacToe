package gameDevelopment;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener{

	public static int BOARD_SIZE = 3;
	public static enum GameStatus{
		Incomplete, Player1Wins, Player2Wins, Tie
	}
	private JButton[][] buttons= new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean CrossTurn = true;
	
	public TicTacToe() {
		super.setTitle("TicTacToe!");
		super.setSize(800,800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Arial", 1, 150); //1 means bold
		for(int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton  button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this); //attach this button with the current object
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		makeMove(clicked);
		GameStatus status = getGameStatus();
		if(status == GameStatus.Incomplete) {
			return;
		}
		
		declareWinner(status);
		
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
		if (choice == JOptionPane.YES_OPTION) {
			for(int row = 0; row < BOARD_SIZE; row++) {
				for (int col = 0; col < BOARD_SIZE; col++) {
					buttons[row][col].setText("");
					}
				}
			CrossTurn = true;
		}
		else {
			super.dispose();
		}
		}
	
	private void makeMove(JButton clicked) {
		String buttonText = clicked.getText();
		if (buttonText.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move. Place is already filled");
		}
		else {
			if (CrossTurn) {
				clicked.setText("X");
			}
			else {
				clicked.setText("0");
			}
			CrossTurn = !CrossTurn;
		}
	}
	 
	private GameStatus getGameStatus() {
		String text1 = "";
		String text2 = "";
		int row = 0, col = 0;
		//row check
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
			col++;
			}
			if (col == BOARD_SIZE - 1) {
				if(text1.equals("X")) {
					return GameStatus.Player1Wins;
				}
				else {
					return GameStatus.Player2Wins;
				}
			}
			row++;
		}
		//column check
		col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
			row++;
			}
			if (row == BOARD_SIZE - 1) {
				if(text1.equals("X")) {
					return GameStatus.Player1Wins;
				}
				else {
					return GameStatus.Player2Wins;
				}
			}
			col++;
		}
		
		//diagonal1
		row = 0;
		col = 0;
		while (row < BOARD_SIZE - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
		row++;
		col++;
		}
		if (row == BOARD_SIZE - 1) {
			if(text1.equals("X")) {
				return GameStatus.Player1Wins;
			}
			else {
				return GameStatus.Player2Wins;
			}
		}
		
		//diagonal2
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
		row--;
		col++;
		}
		if (row == 0) {
			if(text1.equals("X")) {
				return GameStatus.Player1Wins;
			}
			else {
				return GameStatus.Player2Wins;
			}
		}
		
		String txt = "";
		for (row = 0; row < BOARD_SIZE; row++) {
			for (col = 0; col < BOARD_SIZE; col ++) {
				txt = buttons[row][col].getText();
				if (txt.length() == 0) {
					return GameStatus.Incomplete;
				}
			}
		}
		
	return GameStatus.Tie;
	}
	
	private void declareWinner (GameStatus status) {
		if (status == GameStatus.Player1Wins) {
			JOptionPane.showMessageDialog(this,"Player1 Wins!");
		}
		else if (status == GameStatus.Player2Wins) {
			JOptionPane.showMessageDialog(this,"Player2 Wins!");
		}
		else{
				JOptionPane.showMessageDialog(this,"It's a Tie!");
			}
	}
}

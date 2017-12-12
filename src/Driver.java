import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Driver {
	
	static JFrame frame = new JFrame("Tic-Tac-Toe");
	static JPanel panel = new JPanel();
	static JPanel topPanel = new JPanel();
	static JPanel boardPanel = new JPanel();
	static Board board = new Board();
	static JButton[][] buttons = new JButton[3][3];
	static JButton clear = new JButton("Clear");
	static JRadioButton level1 = new JRadioButton("Easy mode");
	static JRadioButton level2 = new JRadioButton("Hard mode");
	
	static int player = 1;
	static boolean started = false;
	static AI ai = new AI(1);
	static boolean ended = false;
	
	public static void main(String[] args) {
		
		frame.setSize(500, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setSize(500, 600);
		topPanel.setPreferredSize(new Dimension(500, 100));
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		
		panel.add(topPanel, BorderLayout.PAGE_START);
		
		JLabel question = new JLabel("Click AI if you would like AI to make first move: ");
		JButton aiButton = new JButton("AI");
		
		aiButton.setPreferredSize(new Dimension(100, 25));
		aiButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!started) {
					int[] point = ai.getMove(board);
					board.makeMove(2, point[0], point[1]);
					buttons[point[0]][point[1]].setText("O");
					player = 1;
				}
				
				started = true;				
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				started = false;
				board = new Board();
				ended = false;
				for(int i = 0; i < buttons.length; i++) {
					for(int j = 0; j < buttons.length; j++) {
						buttons[i][j].setText("");
					}
				}
				player = 1;
			}
		});
		
		level1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ai = new AI(1);
			}
		});
		
		level2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ai = new AI(2);
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(level1);
		group.add(level2);
		
		level1.setSelected(true);
		
		topPanel.add(question);
		topPanel.add(aiButton);
		topPanel.add(clear);
		topPanel.add(level1);
		topPanel.add(level2);
		
		panel.add(topPanel);

		
		
		GridLayout layout = new GridLayout(3, 3);
		boardPanel.setLayout(layout);
		
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				buttons[i][j].setBackground(Color.WHITE);
				buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				
				buttons[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						buttonClicked(e.getSource());
					}
				});
				
				boardPanel.add(buttons[i][j]);

			}
		}
		
		panel.add(boardPanel, BorderLayout.PAGE_END);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	public static void buttonClicked(Object object) {
		started = true;
		int winner;
		int x = 0; 
		int y = 0;
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons.length; j++) {
				if(buttons[i][j] == object) {
					x = i;
					y = j;
					break;
				}
			}
		}
		
		if(!ended && !boardIsFull(board)){
			if(board.makeMove(player, x, y)) {
				if(player == 1) {
					player = 2;
					buttons[x][y].setText("X");
				} else {
					player = 1;
					buttons[x][y].setText("O");
				}
			}
			
			winner = board.winner();
			if(winner != 0) {
				System.out.println(winner);
				if(winner == 1) {
					JOptionPane.showMessageDialog(frame, "Congrats, you beat the AI!");
				} else {
					JOptionPane.showMessageDialog(frame, "The AI wins, better luck next time!");
				}
				ended = true;
			}
		}
		
		if(!ended && !boardIsFull(board)) {
			//Get move from AI
			if(player == 2) {
				int[] point = ai.getMove(board);
				board.makeMove(2, point[0], point[1]);
				buttons[point[0]][point[1]].setText("O");
				player = 1;
			}
			winner = board.winner();
			if(winner != 0) {
				System.out.println(winner);
				if(winner == 1) {
					JOptionPane.showMessageDialog(frame, "Congrats, you beat the AI!");
				} else {
					JOptionPane.showMessageDialog(frame, "The AI wins, better luck next time!");
				}
				ended = true;
			}
		}
		
		
	}
	
	public static boolean boardIsFull(Board board) {
		for(int i = 0; i < board.getBoard().length; i++) {
			for(int j = 0; j < board.getBoard().length; j++) {
				if(board.getBoard()[i][j] == 0) {
					return false;
				}
			}
		}
		return true;		
	}
	

}

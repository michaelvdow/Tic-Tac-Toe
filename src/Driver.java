import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver {
	
	static JFrame frame = new JFrame("Tic-Tac-Toe");
	static JPanel panel = new JPanel();
	static Board board = new Board();
	static JButton[][] buttons = new JButton[3][3];
	
	static int player = 1;
	
	public static void main(String[] args) {
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		panel.setSize(500, 500);
		
		GridLayout layout = new GridLayout(3, 3);
		panel.setLayout(layout);
		
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
				
				panel.add(buttons[i][j]);

			}
		}
		
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	public static void buttonClicked(Object object) {
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
		
		if(board.makeMove(player, x, y)) {
			if(player == 1) {
				player = 2;
				buttons[x][y].setText("X");
			} else {
				player = 1;
				buttons[x][y].setText("O");
			}
		}
		
		int winner = board.winner();
		if(winner != 0) {
			System.out.println(winner);
		}
		
		//Get move from AI
		if(player == 2) {
			
		}
		
	}
	
	

}

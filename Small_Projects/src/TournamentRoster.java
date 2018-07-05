import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TournamentRoster {

	public static void main(String[] args) {
	
		pickFights fightz = new pickFights();
		fightz.setVisible(true);
	}
	
}//end class

class pickFights extends JFrame implements ActionListener
{
	//Set Variables
	private JLabel label, label2, label3, label4,label5,thisRound;
	private JPanel panel, panel2;
	private JButton btn1, btn2, btn3;
	private TextField text = new TextField(10);
	JTextArea speak;
	List FList = null;
	int rounds = 0, round = 1;
	
	pickFights(){
		g();
	}//end constructor

	public void g() {
		this.setTitle("Tournament Battle Setup!");
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initializations
		panel = new JPanel();
		panel2 = new JPanel(new GridBagLayout());
		label = new JLabel("Enter number of Fighterz:");
		btn1 = new JButton("Tournament Start!!");
		btn1.addActionListener(this);
		btn2 = new JButton("Next Fight!!");
		btn2.addActionListener(this);
		btn3 = new JButton("End Battles");
		btn3.addActionListener(this);
		
		
		//add to panel
		panel.add(label);
		panel.add(text);
		panel.add(btn1, BorderLayout.AFTER_LAST_LINE);
		this.add(panel);
			
	}//end g
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel win = new JLabel("The Winner is: ");
		int fighter1, fighter2;//set as 1 to take into account first match
		//GridBagConstraints
		GridBagConstraints word1 = new GridBagConstraints();
		word1.fill = GridBagConstraints.HORIZONTAL;
		word1.anchor = GridBagConstraints.PAGE_START;
		word1.ipady = 5;
		word1.gridx = 0;
		word1.gridy = 0;
		GridBagConstraints word2 = new GridBagConstraints();
		word2.fill = GridBagConstraints.HORIZONTAL;
		word2.anchor = GridBagConstraints.FIRST_LINE_START;
		word2.ipady = 1;
		word2.gridx = 0;
		word2.gridy = 1;
		GridBagConstraints word3 = new GridBagConstraints();
		word3.fill = GridBagConstraints.HORIZONTAL;
		word3.anchor = GridBagConstraints.FIRST_LINE_START;
		word3.gridx = 0;
		word3.gridy = 2;
		GridBagConstraints word4 = new GridBagConstraints();
		word4.fill = GridBagConstraints.HORIZONTAL;
		word4.anchor = GridBagConstraints.FIRST_LINE_START;
		word4.gridx = 0;
		word4.gridy = 3;
		GridBagConstraints btn = new GridBagConstraints();
		btn.fill = GridBagConstraints.HORIZONTAL;
		btn.gridy = 4;
		btn.gridx = 0;
		btn.gridwidth = 2;
		GridBagConstraints t = new GridBagConstraints();
		t.gridy = 3;
		t.gridx = 1;
		
		if (e.getSource() == btn1) {
			int fighters;
			fighters = Integer.parseInt(text.getText());
			if((fighters % 2) != 0) {
				text.setText("Needs to be an even Number");	
			}else {
			FList = createFighterList(fighters);
			label2 = new JLabel("Number of Fighters are " + fighters);
			rounds = fighters / 2;//Change number depending on # of fighters
			label3 = new JLabel("Number of Rounds are " + rounds);
			fighter1 = getFighter(FList);
			fighter2 = getFighter(FList);
			label4 = new JLabel("First fight is: " + fighter1 + " vs " + fighter2);
			text.setText("");
			
			//Setup Panel
			this.remove(panel);
			panel2.add(label2, word1);
			panel2.add(label3, word2);
			panel2.add(label4, word3);
			panel2.add(win, word4);
			panel2.add(text,t);
			panel2.add(btn2, btn);
			this.add(panel2);
			this.validate();
			}
		}if (e.getSource() == btn2) {
			round++;
			fighter1 = getFighter(FList);
			fighter2 = getFighter(FList);
			thisRound = new JLabel("Round " + round);
			String winner = text.getText();
			storeWinner(winner);
			text.setText("");
			label5 = new JLabel("Next fight is: " + fighter1 + " vs " + fighter2);

			//Setup Panel
			panel2.removeAll();
			panel2.add(label5, word1);
			panel2.add(thisRound, word2);
			panel2.add(btn2, btn);
			panel2.add(win, word4);
			panel2.add(text,t);
			panel2.validate();
			if(round == rounds) {
			label5.setText("This is the Last battle: " +  fighter1 + " vs " + fighter2);
			panel2.remove(btn2);
			panel2.add(btn3, btn);
			this.validate();
			}
		}if (e.getSource() == btn3) {
			String winner = text.getText();
			storeWinner(winner);
		}

	}//end actionPerformed
	
	/*Creates a list of fighter numbers*/
	List createFighterList(int f) {
		List<Integer> fights = new ArrayList<Integer>();
		for(int i = 1; i <= f; i++) {
			fights.add(i);
		}
		return fights;
	}//end CFL
	
	//Gets random Fighter from remaining fighters
	int getFighter(List s) {
		Random ran = new Random();
		int choice = ran.nextInt(s.size());
		int fighter = (int) s.get(choice);
		s.remove(choice);
		return fighter;
	}
	
	//Store the numbers of the remaining fighters
	void storeWinner(String w) {
		String path = System.getProperty("user.dir");
		File f = new File(path + "//Winners.txt");
		try {
			if(!f.exists()) {
				f.createNewFile();
				}	
			BufferedWriter write = new BufferedWriter(new FileWriter(f, true));
			write.append(w + " ");
			write.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(pickFights.this,
					"Sorry but this did not work!", "Error", 
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}//end class
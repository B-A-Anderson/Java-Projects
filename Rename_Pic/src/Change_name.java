import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Change_name extends JFrame implements ActionListener
{
	private JLabel info;
	private JPanel p1;
	private JButton ok, change;
	JTextArea directions;
	TextField text = new TextField(20);
	File path;
	File directory = null;
	static int cnt = 0;
		
	public Change_name(String s)
	{
		path = new File(s);
		g();
	}//end Constructor
	
	public void g()
	{
		this.setTitle("Picture Name Update");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		p1 = new JPanel(new GridBagLayout());
		
		//GridBagConstraints
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.HORIZONTAL;
		t.anchor = GridBagConstraints.FIRST_LINE_START;
		t.gridx = 1;
		t.gridy = 0;
		GridBagConstraints button = new GridBagConstraints();
		button.fill = GridBagConstraints.HORIZONTAL;
		button.anchor = GridBagConstraints.FIRST_LINE_END;
		button.weighty = 1;
		button.gridy = 1;
		GridBagConstraints dir = new GridBagConstraints();
		dir.fill = GridBagConstraints.HORIZONTAL;
		dir.anchor = GridBagConstraints.FIRST_LINE_START;
		dir.weighty = 50;
		dir.gridwidth = 2;
		dir.gridx = 0;
		dir.gridy = 3;
		
		//Initializations
		info = new JLabel("Enter Folder Name: ");
		directions = new JTextArea("Enter Folder Name and Press 'Create Folder' to save Folder name. " +
		"Must be used before 'Update Names' function for correct names to be created!");
		directions.setFont(directions.getFont().deriveFont(15f));
		directions.setLineWrap(true);
		directions.setWrapStyleWord(true);
		directions.setBorder(BorderFactory.createLineBorder(Color.black));
		directions.setBackground(this.getBackground());
		change = new JButton("Update Names");
		//c.fill = GridBagConstraints.HORIZONTAL;
		change.addActionListener(this);
		ok = new JButton("Create Folder");
		ok.addActionListener(this);
		p1.add(info,c);
		p1.add(text,t);
		p1.add(ok,button);
		p1.add(change,button);
		p1.add(directions, dir);
		this.add(p1);
	}//End g
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ok) {
			try {
				String paths = new File(".").getCanonicalPath();
				System.out.println(paths);
			}catch(IOException c){
		      System.out.println("Error: "+ c);
		}//end catch
		
			/* Create folder using Name given*/
			String name = text.getText();
			directory = new File(path +"\\" + name);
			directory.mkdirs();
		}else if(e.getSource()== change) {
			//When a new item is added to the folder
				updtNme(directory);// update its name
		}//End if
		JOptionPane.showMessageDialog( Change_name.this,
                "Operation Finished!",
                "About", JOptionPane.PLAIN_MESSAGE );
	}//end actionPerformed
	
	static void updtNme(File d)
	{
		String[] list = d.list();
		String nuname = d.getAbsolutePath();
		String name = nuname.substring(nuname.lastIndexOf(File.separator)+1); //Name of current folder
		while(cnt != list.length)
		{	
			String fname = nuname + "\\" + list[0]; //Name of current File
			File i = new File(fname);	
			fname = nuname + "\\" + name + "-" + cnt + ".jpg"; //Rename file to folder name plus # created
			File nuPic = new File(fname);	
			i.renameTo(nuPic);//Is it really this easy?
			list = d.list();
			cnt++;
		}//end while
	}//end updtName
}//end Class

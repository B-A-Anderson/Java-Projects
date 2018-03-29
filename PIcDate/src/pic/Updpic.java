package pic;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Updpic {
	
	public Updpic()
	{
		picFrame text = new picFrame();
		text.setTitle("TextBox Manipulator");
		JMenuBar Bar = createMenu();
		text.setJMenuBar(Bar);
	}
	
	public void chooseTB()
	{
		
	}
	
	public JMenuBar createMenu()
	{
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");		
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		
		//Create TextBox Submenu
		JMenu boxChoice = new JMenu("TextBox Styles");
		String boxtypes[] = {"Regular", "Clear", "Shout", "tiny"};
		ButtonGroup boxGroup = new ButtonGroup();
		JRadioButtonMenuItem[] boxes = new JRadioButtonMenuItem[boxtypes.length];
		for (String type : boxtypes)
		{
			JRadioButtonMenuItem Box = new JRadioButtonMenuItem(type);
			boxChoice.add(Box);
			boxGroup.add(Box);
			//Box.addActionListener(arg0);
		}
		menu.add(boxChoice);
		menu.add(save);
		menu.add(exit);
		bar.add(menu);
		return bar;
	}

}

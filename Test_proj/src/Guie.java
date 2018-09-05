import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/**************************
* Class to create GUI
* TExtfield takes input
* Second field that appears
* Will display results
***************************/
public class Guie extends JFrame implements ActionListener 
{
	private JPanel emptyPanel,p1,p2;
	private JButton srch,open,done,srchagain,browse;
	private JLabel title,wrng;
	private JScrollPane pane;
	TextField text = new TextField(20);
	JTextArea directions;
	int count = 0;
	int bord = 2;
	File frst;
	File dir;
	
	public Guie (String path) 
	{
		frst = new File(path);
		g();
	}//End Constructor
	
	public void g()
	{
		this.setTitle("Search Function");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450, 500);
		title = new JLabel ("Search for Files and Folders");
		title.setFont(new Font("Cooper black", Font.PLAIN, 20));
		wrng = new JLabel ("Sorry But this is not a folder, Please choose OPEN");
		wrng.setVisible(false);
		emptyPanel = new JPanel ();
		emptyPanel.setSize(600,600);
		emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
		emptyPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));	
		p1 = new JPanel(new GridBagLayout());
		p1.setSize(emptyPanel.getSize());
		p1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		srch = new JButton ("SEARCH!");
		srch.setBackground(Color.yellow);
		srch.addActionListener(this);
		browse = new JButton ("Browse Pics");
		browse.setBackground(Color.cyan);
		browse.addActionListener(this);
		srchagain = new JButton ("Continue Search!");
		srchagain.setBackground(Color.orange);
		srchagain.addActionListener(this);
		srchagain.setVisible(false);
		open = new JButton ("OPEN!");
		open.setBackground(Color.magenta);
		open.addActionListener(this);
		open.setVisible(false);
		
		//GridBagConstraints
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 2;
		c.weighty = 0.4;
		c.gridy = 0;
		
		GridBagConstraints direc = new GridBagConstraints();
		direc.fill = GridBagConstraints.HORIZONTAL;
		direc.anchor = GridBagConstraints.CENTER;
		direc.gridwidth = 2;
		direc.weighty = 1;
		direc.gridx = 0;
		direc.gridy = 1;
		
		
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.HORIZONTAL;
		t.anchor = GridBagConstraints.FIRST_LINE_START;
		t.weightx = 0.5;
		t.gridx = 0;
		t.gridy = 2;
		
		GridBagConstraints bt = new GridBagConstraints();
		bt.fill = GridBagConstraints.HORIZONTAL;
		bt.gridx = 1;
		bt.gridy = 2;
		
		GridBagConstraints bt2 = new GridBagConstraints();
		bt2.gridx = 1;
		bt2.gridy = 3;
		
		GridBagConstraints bt3 = new GridBagConstraints();
		bt3.gridx = 2;
		bt3.gridy = 4;
			
		// create File menu and Exit menu item
				JMenu fileMenu = new JMenu( "File" );
				JMenuBar bar = new JMenuBar();  // create menu bar
				setJMenuBar( bar );  // set the menu bar for the JFrame	
			    fileMenu.setMnemonic( 'F' );
			    //Create About Button for JMenu
			    JMenuItem aboutItem = new JMenuItem( "About..." );
			    aboutItem.setMnemonic( 'A' );
			    aboutItem.addActionListener(
			            new ActionListener() {
			               public void actionPerformed( ActionEvent e )
			               {
			                  JOptionPane.showMessageDialog( Guie.this,
			                     "Finished On March 26! YAY!!",
			                     "About", JOptionPane.PLAIN_MESSAGE );
			               }
			            }
			         );
			    // Create Help menu for JMenu
			    JMenuItem helpItem = new JMenuItem( "Help!" );
			    helpItem.setMnemonic('H');
			    helpItem.addActionListener(
			            new ActionListener() {
				               public void actionPerformed( ActionEvent e )
				               {
				                  JOptionPane.showMessageDialog( Guie.this,
				                	"Enter name of Document and press SEARCH. " +
				                	"If the name is not known simply press BROWSE to look for it manually. " +
				                	"Press DONE when finished with the program.",
				                	"Help", JOptionPane.INFORMATION_MESSAGE);
				               }
			            	}
			    		);
			    // create exit item for JMenu
				JMenuItem exitItem = new JMenuItem( "Exit" );
			    exitItem.setMnemonic( 'x' );
			     exitItem.addActionListener(
			             new ActionListener() {
			                public void actionPerformed( ActionEvent e )
			                {
			                   System.exit( 0 );
			                }
			             }
			          );

		//Add Buttons and Labels to Panels
		p1.add(title,c);
		p1.add(wrng, direc);
		p1.add(text, t);
		p1.add(srch, bt);	
		p1.add(browse, bt2);
		p1.add(open, bt3);
		p1.add(srchagain, bt);//*/
		emptyPanel.add(p1,BorderLayout.CENTER);
		this.add(emptyPanel);
		
		//Add items to the Menu
		fileMenu.add( aboutItem );
		fileMenu.add( helpItem );
		fileMenu.add( exitItem );
		bar.add( fileMenu );    //Add File menu
				
		dir = frst; //Set Starting point
		
		
	}//End g
	
	@Override
	
	public void actionPerformed(ActionEvent e) 
	{
		String[] SL = {};
		if(e.getSource()== browse)
		{
			String s = text.getText();
			//this.add(p2);//Adds other panel to the Frame
			this.validate();
			String[] sList = searchList(s,1);
			createList(sList);
		}//end if	
		
		p1.remove(srch);
		browse.setVisible(false);
		srchagain.setVisible(true);//Switch buttons
		p1.validate(); //refresh p1

		if(e.getSource()== srch)
		{
			String s = text.getText();
			try {
				search4(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()== done)
		{
			this.dispose();
		}
		
		if(e.getSource()== srchagain)
		{
			p1.remove(pane);
			String sa = text.getText();
			String[] LAgain = keepSearch(sa);					
			createList(LAgain);
			open.setVisible(true);
			p1.validate();
		}
		
		if(e.getSource()== open)
		{
			String sa = text.getText();
			openFound(sa);
		}
	}//End actionPerformed
	
	void createList(String[] sL)
	{
		JList tx = new JList(sL); //Create JList made from String Array
		pane = new JScrollPane(tx); //JList is Scrollable
		pane.setPreferredSize(new Dimension(200, 200)); //Set Size for jList
		//GridBagContraints
		GridBagConstraints list = new GridBagConstraints();
		list.fill = GridBagConstraints.HORIZONTAL;
		list.anchor = GridBagConstraints.CENTER;
		list.gridwidth = 2;
		list.weighty = 0.5;
		list.gridx = 0;
		list.gridy = 4;
		p1.add(pane, list);	
		
		MouseListener mouseListener = new MouseAdapter() 
		{
		    public void mouseClicked(MouseEvent e) 
		    {
		        if (e.getClickCount() == 1) 
		         {
		            int index = tx.locationToIndex(e.getPoint());
		            String next = (String) tx.getSelectedValue();
		            String[] SL = searchList(next,1);
		            text.setText(next);
		         }//end if
		    }//end mouseClicked
		};//end MotionListener
		tx.addMouseListener(mouseListener);
	}//End createList
	
	String[]searchList(String s, int a)
	{
		String[] found = {};
		Search4File file = new Search4File();
		if (a == 1){
		found = file.search(s, dir);
		}else if( a == 2){
		dir = file.updateDir(dir, s);
		found = file.searchHarder(s, dir);	
		}//end if
		count++;
		return found;
	}//end searchList
	
	//Continue Searching
	String[]keepSearch(String s)
	{
		File dir2 = dir;
		String[] found = {};
		Search4File file = new Search4File();
		found = file.search(s, dir);	
		dir = file.updateDir(dir, s);
		if (dir2 == dir)
		{
			p1.add(wrng);
			p1.validate();
		}
		found = file.searchHarder(s, dir);	
		return found;
	}//end keepSearch
	
	void search4(String s) throws IOException
	{
		Search4File file = new Search4File();
		String[] foundit = file.searchThrough(s, dir);
		createList(foundit);
	}//end search4
	
	void openFound(String s)
	{
		Search4File file = new Search4File();
		dir = file.updateDir(dir, s);
		file.openFile(s, dir);
		dir = frst;  //Reset Starting point
		System.exit(0);
	}//end openFound
}//End Class

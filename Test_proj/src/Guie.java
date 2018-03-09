import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**************************
* Class to create GUI
* TExtfield takes input
* Second field that appears
* Will display results
***************************/
public class Guie extends JFrame implements ActionListener 
{
	private JPanel p1,p2;
	private JButton srch,open,done,srchagain,browse;
	private JLabel title;
	TextField text = new TextField(20);
	JList tx;
	int count = 0;
	File frst = new File("C:\\Users\\Necro\\OneDrive\\Pictures\\GTSPictures");
	File dir;
	
	public Guie () 
	{
		g();
	}//End Constructor
	
	public Guie (String s) 
	{
		frst = new File(s);
		g();
	}//End Constructor
	
	public void g()
	{
		this.setTitle("Search Function");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.setLayout(new GridLayout(2,1));
		title = new JLabel ("Search for Files and Folders");
		title.setFont(new Font("Cooper black", Font.PLAIN, 23));
		p1 = new JPanel (); //Panel 1
		p1.setBackground(Color.CYAN);
		p2 = new JPanel (); //panel 3 only added when user picks Calendar
		p2.setBackground(Color.BLACK);
		srch = new JButton ("SEARCH!");
		srch.setBackground(Color.yellow);
		srch.addActionListener(this);
		browse = new JButton ("Browse Pics");
		browse.setBackground(Color.green);
		browse.addActionListener(this);
		srchagain = new JButton ("Continue Search!");
		srchagain.setBackground(Color.orange);
		srchagain.addActionListener(this);
		open = new JButton ("OPEN!");
		open.setBackground(Color.magenta);
		open.addActionListener(this);
		done = new JButton ("Done");
		done.addActionListener(this);
		//Add Buttons and Labels to Panels
		p1.add(title);
		p1.add(text, BorderLayout.SOUTH);
		p1.add(srch, BorderLayout.SOUTH);	
		p1.add(browse, BorderLayout.SOUTH);
		p2.add(done, BorderLayout.BEFORE_FIRST_LINE );
		this.add(p1);
		dir = frst; //Set Starting point
		
		// create File menu and Exit menu item
				JMenu fileMenu = new JMenu( "File" );
				JMenuBar bar = new JMenuBar();  // create menubar
				setJMenuBar( bar );  // set the menubar for the JFrame	
			    fileMenu.setMnemonic( 'F' );
			    JMenuItem aboutItem = new JMenuItem( "About..." );
			    aboutItem.setMnemonic( 'A' );
			    aboutItem.addActionListener(
			            new ActionListener() {
			               public void actionPerformed( ActionEvent e )
			               {
			                  JOptionPane.showMessageDialog( Guie.this,
			                     "On January 7th 2018 I am pledging to finish "
			                     + "this program before my birthday When I do\n"
			                     + "I will update this message.",
			                     "About", JOptionPane.PLAIN_MESSAGE );
			               }//end actionPerformed
			            }//End actionListener
			         );//End AboutItem
			 // create exit item for JMenu
				JMenuItem exitItem = new JMenuItem( "Exit" );
			    exitItem.setMnemonic( 'x' );
			     exitItem.addActionListener(
			             new ActionListener() {
			                public void actionPerformed( ActionEvent e )
			                {
			                   System.exit( 0 );
			                }
			             }//End actionListener
			          );//End exitItem
				//Add items to the Menu
				fileMenu.add(aboutItem);
				fileMenu.add( exitItem );
			    bar.add( fileMenu );    //Add Menu to Frame	    
	}//End g
	
	@Override
	
	public void actionPerformed(ActionEvent e) 
	{
		String[] SL = {};
		if(e.getSource()== browse)
		{			
			String s = null;
			String[] sList = searchList(s,1);
			createList(sList);
			this.add(p2);//Adds other panel to the Frame
			
			this.validate();
			p1.remove(srch);//Switch buttons	
			p1.remove(browse);
			p1.add(srchagain);
		}else if(e.getSource()== srch) {
			if (text.getText().isEmpty() == true)
			{		
				JOptionPane.showMessageDialog( null,
	                     "Sorry but there is no input here."
	                     + "Please add input if you want to"
	                     + "use the search function.",
	                     "Error", JOptionPane.ERROR_MESSAGE );				
			} else {
				String s = text.getText();				
				try {
					Search4File file = new Search4File();
					file.searchThrough(s, dir);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			p1.remove(srch);//Switch buttons	
			p1.remove(browse);
			p1.add(srchagain);
			}//end if
		}//end if				
		p1.validate(); //refresh panels
		p2.validate();

		if(e.getSource()== done)
		{
			this.dispose();
		}
		
		if(e.getSource()== srchagain)
		{
			String sa = text.getText();
			if (text.getText().isEmpty() == true)
			{		
				JOptionPane.showMessageDialog( null,
	                     "There is no input to search.\n"
	                     + "Please add search input into the text Area.",
	                     "Error", JOptionPane.ERROR_MESSAGE );				
			} else {
			String[] LAgain = keepSearch(sa);
			p2.removeAll();
			p1.add(open);		
			p1.validate();
			createList(LAgain);
			p2.add(done);
			p2.validate();
			}
		}
		
		if(e.getSource()== open)
		{
			String sa = text.getText();
			openFound(sa);
			p2.removeAll();
			p1.remove(srchagain);
			p1.add(browse);
			p1.add(srch);
			p1.remove(open);
			p1.validate();
			p2.add(done);
			p2.validate();
		}
	}//End actionPerformed
	
	void createList(String[] sL)
	{
		JList tx = new JList(sL); //Create JList made from String Array
		JScrollPane pane = new JScrollPane(tx); //JList is Scrollable
		tx.setPreferredSize(new Dimension(200, 200)); //Set Size for jList
		p2.add(tx, BorderLayout.SOUTH);	
		
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
		            System.out.println("Clicked On Item " + index);
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
		dir = file.updateDir(dir, s, 'a');
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
		dir = file.updateDir(dir, s, 'a');
		if (dir2 == dir)
		{
			JOptionPane.showMessageDialog( null,
                    "It seems that there is no Directory with that name."
                    + "Please add a valid Directory name or choose OPEN"
                    + " to open your picture.",
                    "Error", JOptionPane.ERROR_MESSAGE );	
		}
		found = file.searchHarder(s, dir);	
		return found;
	}//end keepSearch
	
	void openFound(String s)
	{
		Search4File file = new Search4File();
		dir = file.updateDir(dir, s, 'a');
		file.openFile(s, dir);
		dir = frst;  //Reset Starting point
	}//end openFound
}//End Class

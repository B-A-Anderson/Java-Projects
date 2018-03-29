
package pic;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Picgui extends JFrame implements ActionListener 
{
	//Setup and Variables
	private JPanel reg,pcpnl;
	private JButton opn, opn2, updt;
	private JLabel title;
	private JRadioButtonMenuItem items[];
    String styles[] = { "Itallic", "BOLD", "Reg" };

    
    // Constructor
	public Picgui () 
	{
		g();
	}//End Constructor
	
	//Setup the Gui
	void g()
	{
		//This will center the JFrame in the middle of the screen
        this.setLocationRelativeTo(null);
		this.setTitle("Picture Modifier");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		title = new JLabel ("Choose file to Update");
		title.setFont(new Font("Cooper black", Font.PLAIN, 23));		
		reg = new JPanel();
		pcpnl = new JPanel();
		opn = new JButton("Open Picture.");
		opn.addActionListener(this);
		opn2 = new JButton("Open Another Picture");
		opn2.addActionListener(this);
		updt = new JButton("Add Text to Picture");
		updt.addActionListener(this);
		
		// create File menu and Exit menu item
		JMenu fileMenu = new JMenu( "File" );
		JMenuBar bar = new JMenuBar();  // create menu bar
		setJMenuBar( bar );  // set the menu bar for the JFrame	
	    JMenuItem aboutItem = new JMenuItem( "About..." );
	    aboutItem.setMnemonic( 'A' );
	    aboutItem.addActionListener(
	            new ActionListener() {
	               public void actionPerformed( ActionEvent e )
	               {
	                  JOptionPane.showMessageDialog( Picgui.this,
	                     "On January 7th 2018 I am pledging to finish "
	                     + "this program before my birthday!\nWhen I do"
	                     + " I will update this message.",
	                     "About", JOptionPane.PLAIN_MESSAGE );
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
	     
	      // construct each menu item and add to popup menu; also
	      // enable event handling for each menu item
	     ButtonGroup styleGroup = new ButtonGroup();
	     items = new JRadioButtonMenuItem[ 3 ];
	      for ( int i = 0; i < items.length; i++ ) {         
	         items[ i ] = new JRadioButtonMenuItem( styles[ i ] );
	       
	         styleGroup.add( items[ i ] );	         
	      }

	    // create the Format menu, its submenus and menu items
	    JMenu formatMenu = new JMenu( "Format" );  
	    formatMenu.setMnemonic( 'r' );

		//Add Buttons and Labels to Panels
		reg.add(title);
		reg.add(opn,BorderLayout.SOUTH);
		this.add(reg);	//Add JPanel to frame
		
	    this.setVisible( true );
		
		//Add items to the Menu
		fileMenu.add(aboutItem);
		fileMenu.add( exitItem );
	    bar.add( fileMenu );    //Add File menu
		
	}//End g
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()== opn)
		{
				Picopen po = new Picopen();
				//BufferedImage image = null;
				try {
					String path = po.getPath();
					po.openPic(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pcpnl.add(opn2,BorderLayout.WEST);
				pcpnl.add(updt,BorderLayout.SOUTH);
				this.remove(reg);
				this.add(pcpnl);
				this.validate();			
		}else if(e.getSource()== opn2) {
					Picopen po = new Picopen();
					String path = po.getPath();
					try {
						po.openPic(path);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}else if(e.getSource()== updt) {
				
			}//End if
	}//end actionPerformed
			
}//End Class



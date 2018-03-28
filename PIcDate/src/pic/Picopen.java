package pic;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Nested Class for Dialog Box
class picFrame extends JDialog 
{
	JLabel picbl;
	//Constructor
	public picFrame() 
	{
		setSize(700,700);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Modify Picture");	
		setVisible(false);
		
	}//end Constructor
	
	void addPic(ImageIcon i)
	{
		picbl = new JLabel(i);
		picbl.revalidate();
		add(picbl);
		picbl.repaint();
	}
	
	void updtPic()
	{
		ImageIcon i = (ImageIcon) picbl.getIcon();
		Image image = i.getImage();
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(30f));
	    g.drawString("Hello World!", 100, 100);
	    g.dispose();
	}
}

public class Picopen
{
	final String[] EXTENSIONS = new String[]{"png", "bmp", "jpg", "jpeg"};
	
	//Create a JFileChooser GUI to choose file.
	JFileChooser choice = new JFileChooser( new File("C:\\Users\\Necro\\Pictures\\Descktop pics"));
	
	Picopen()
	{
		//Nothing needed here
	}//End Constructor
	
	public String getPath()
	{
		String path = null;
		int returnVal = choice.showOpenDialog(null);
		
		return path;
	}
	
	//Open Picture using path given
	void openPic(String s) throws IOException
	{
		ImageIcon image = null;
		picFrame picture = new picFrame();
		try {
			image = new ImageIcon(ImageIO.read(new File(s)));
		}catch (IOException e) {}
	     picture.addPic(image);	     
	     picture.repaint();
	     picture.setVisible(true);
	}
	
}


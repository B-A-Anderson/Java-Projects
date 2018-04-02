package pic;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
}

public class Picopen
{
	final String[] EXTENSIONS = new String[]{"png", "bmp", "jpg", "jpeg"};
	
	//Create a JFileChooser GUI to choose file.
	JFileChooser choice = new JFileChooser( new File("C:\\Users\\Necro\\Pictures\\Descktop pics")); //Picture Locations
	
	Picopen()
	{
		//Nothing needed here
	}//End Constructor
	
	public String getPath()
	{
		String path = null;
		int returnVal = choice.showOpenDialog(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extensions", EXTENSIONS); //Only files with the specified extensions should be visible
		choice.setFileFilter(filter);
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
			 File f = choice.getSelectedFile();
			 path = f.getAbsolutePath();
		 }
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
	     int width = image.getIconWidth();
	     int height = image.getIconHeight();
	     picture.setBounds(0, 0, width, height);
	     picture.repaint();
	     picture.setVisible(true);
	}
	
}


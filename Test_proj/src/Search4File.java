import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
/*Use string for search for file with same name in folders
*Probably use XML look at previous code to see how
**/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Search4File 
{
	final String[] EXTENSIONS = new String[]{"gif", "png", "bmp", "doc", "jpg", "mp4"};
	public static String[] List = null;
	File dir;
	Desktop desktop = Desktop.getDesktop();
	Search4File()
	{
		//Nothing needed here
	}//End search4File
	
	//Simplified for now Simply update JList
	String[] search(String str, File dir) 
	{	
		// array of supported extensions (use a List if you prefer)
		if (dir.isDirectory()) //Check if dir. is a folder
		{
			List = dir.list();
			int all = List.length;
			String nuname = "";			
		}else{
			List = dir.list();
			List[0] = "This is not a directory. Use the OPEN Button!";
		}//End if
		return List;
	}//End search
	
	/* Second search function. Searches for both
	 * folders and pictures. Will open pictures
	 * when found?
	 */
	String[] searchHarder(String name, File dir)
	{
		String nuname = dir.getAbsolutePath();
		String [] moreFiles = {};		
		dir = new File(nuname);
		if (dir.isDirectory())
		{
			moreFiles = dir.list();
		}else{
			moreFiles[0] = "This is not a directory. Use the OPEN Button!";
		}//end if
      return moreFiles;
	 }//end searchHarder
	
	/* The function that is called by the SEARCH Button. Uses a name
	 * and extension to find picture.
	 */
	void searchThrough(String str, File direct) throws IOException 
	{	
		dir = direct;
		String nuname = null;
		File [] moreFiles = updateList(dir);
			for(File more : moreFiles)
			{
				if (more.isDirectory()){ //Check for nested Folder. If folder exist
					searchThrough(str,more);
				 }else{
					nuname = more.getName();
					String name = nuname.substring(0, nuname.length() - 4);
					if(name.equals(str)) { //If element in directory equals search String
						desktop.open(more); //Open it
						break;			
						}//end if
				 }//end if
			}//end for
			System.out.println("The FIle does not exist"); //Need to change for GUI
	}//End search

/*Updates the directory after every search to point to
The directory chosen in search*/
File updateDir(File d, String update, char choice)
{
	File nuDir = null;
	if (choice == 'a')
	{
		String nuname = d.getAbsolutePath();
		nuname = nuname + "\\" + update;
		 nuDir = new File(nuname);
		
	}else if (choice == 's') {
		String path = d.getAbsolutePath();
		while('\'' != path.charAt(path.length() - 1)) //While the last character of nuName does not equal backslash
				{
					path = path.substring(0, path.length() - 1);
				}
		path = path.substring(0, path.length() - 1); // to delete the backslash
		nuDir = new File(path);			
	}//end if
	if (!nuDir.isDirectory())
		{
			nuDir = d;
		}
	return nuDir;
}//End UpdateDir
	File [] updateList(File f)
	{
		File [] list= null;
		String st = f.getAbsolutePath();
		{
			list = new File(st).listFiles();
		}
		
		return list;
	}//End updateList
	
	/*Opens the File that has been requested*/
	void openFile(String name, File fle)
	{
		String nuname = fle.getAbsolutePath() + "\\";
		for (final String ext : EXTENSIONS) 
		  {
              if (name.endsWith("." + ext))
              {	
            	nuname = nuname + name;
             	fle = new File(nuname); 	
              	try
              	{	                	   
              	    desktop.open(fle);
              	}
              	catch (IOException e) {
                      e.printStackTrace();
                  }
              }//end if
		  }//end for
	}//End openFile

}//End Class

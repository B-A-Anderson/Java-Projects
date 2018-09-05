import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Search4File 
{
	//Add any extension for a file you would like to search
	final String[] EXTENSIONS = new String[]{"gif", "png", "bmp", "doc", "jpg", "mp4","js","docx"};
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
	
	/*Opens the File that has been requested*/
	void openFile(String name, File fle)
	{
		String nuname = fle.getAbsolutePath();
		fle = new File(nuname);
        if (fle.isDirectory()) {	
          try {	                	   
          	  desktop.open(fle);
          	  }
            catch (IOException e) {
                   e.printStackTrace();
               }
           }//end if
	}//end openFIle
	
	/*Updates the directory after every search to point to
	The directory chosen in search*/
	File updateDir(File d, String update)
	{
		String nuname = d.getAbsolutePath();
		nuname = nuname + "\\" + update;
		File nudir = new File(nuname);
		if (!nudir.isDirectory())
		{
			nudir = d;
		}
		return nudir;
	}

	String[] searchThrough(String str, File direct) throws IOException 
	{	
		dir = direct;
		File reset = direct;
		String nuname = null;
		File [] moreFiles = updateList(dir);
		Set<File> foundSet = new HashSet<File>();
		int a = 0, b = 0, open = 0;
		if (dir.isDirectory()) //Check if dir is a folder
		{	
			do{
				nuname = moreFiles[a].getName();
				String searchname = nuname.toLowerCase().replaceAll("[_|.|-]", " ");
				if (searchname.contains(str)) {//If first element in directory equals string
					foundSet.add(moreFiles[a]);
				}else if (moreFiles[a].isDirectory()){ //else if first element in folder is another folder					
					 dir = updateDir(dir,nuname); //Update Directory
					 a = 0; // Reset count
					 moreFiles = updateList(dir); //Update 
				}//End if
					a++; 
			}while(a < moreFiles.length);
	    }//End if
		String [] names = new String[foundSet.size()];
		for(File f : foundSet) {
			names[b++] = f.getName();
		}
				
		return names;
	}//End search
	
	File [] updateList(File f)
	{
		File [] list= null;
		String st = f.getAbsolutePath();
		{
			list = new File(st).listFiles();
		}
		
		return list;
	}
	
}//End Class

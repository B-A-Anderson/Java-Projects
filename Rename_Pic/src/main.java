
public class main {
	public static void main(String[] args)
	{
		String directory = System.getProperty("user.dir");
	//Create a program to change a photos name and save it.	
	Change_name update= new Change_name(directory);
	update.setVisible(true);
	}//End main
}//End class



public class Search_proj 
{
	public static void main(String[] args)
	{
		// Create a program that can be used to search multiple folders at once
	String directory = System.getProperty("user.dir");
	Guie make = new Guie(directory);	
	make.setVisible(true);
	}//End main
}//End class


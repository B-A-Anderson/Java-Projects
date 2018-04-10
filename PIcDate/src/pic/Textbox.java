package pic;

public interface Textbox {
	
	
	//Sets the Font for the Textbox
	public void setFont();
	
	//Set Textbox Font Size
	public int setSize();
	
	//Clear TextBox
	public void clear();
	
	//Create writable Textbox
	default void createBox()
	{
		
	}
}

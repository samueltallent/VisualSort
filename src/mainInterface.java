import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * creates the selectionMenu which is the main frame for the program
 */
public class MainInterface
{
	protected static SelectionMenu interOne;
	public static void main(String args[])
	{
    	interOne = new SelectionMenu();
    	//InformationWindow w = new InformationWindow();
    }
}

/**
 * Main frame of the program
 */
class SelectionMenu extends JFrame
{
	protected static ArrayList<String> words;
	private static final int FRAME_WIDTH = 1600;
	private static final int FRAME_HEIGHT = 900;
	protected static DrawPanel drawer = new DrawPanel();
	
	/**
	 * Creates a selectionMenu with a LeftPanel and a drawPanel
	 */
	public SelectionMenu()
	{
		words = new ArrayList<String>();
		Scanner in = null;
		try
		{
			in = new Scanner(new File("words.txt"));
		}
		catch(IOException e){}
		
		while(in.hasNext())
		{
			words.add(in.next());
		}
		this.setTitle("Visual Sort");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		this.setLayout(new BorderLayout());
		
		add(new LeftPanel(), BorderLayout.WEST);
		add(drawer, BorderLayout.CENTER);
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}



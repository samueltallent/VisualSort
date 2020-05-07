import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Class that contains settings for searches
 */
public class SearchMenu extends JPanel
{
	protected static JComboBox searchType;
	protected static JButton startButton;
	protected static JButton pauseButton;
	
	private JRadioButton ascending;
	private JRadioButton descending;
	private ButtonGroup group;
	
	private JPanel nested1;
	private	JPanel nested2;
	private	JPanel nested3;
	private JPanel nested4;
	
	@SuppressWarnings("unchecked")
	/**
	 * Creates a searchmenu
	 */
	public SearchMenu()
	{
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Searches"));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		nested1 = new JPanel();
		nested2 = new JPanel();
		nested3 = new JPanel(new GridLayout(1,2,25,0));
		nested4 = new JPanel();
		
		String [] searchTypes = {"Linear", "Binary"}; 
		searchType = new JComboBox(searchTypes);
		
		startButton = new JButton("START");
		pauseButton = new JButton("PAUSE/RESUME");
		startButton.addActionListener(new DrawPanel());
		pauseButton.addActionListener(new DrawPanel());
		
//		startButton.addActionListener(mainInterface.interOne.drawer);
//		pauseButton.addActionListener(mainInterface.interOne.drawer);

		nested1.add(new JLabel("Select a type of Search:"));
		nested1.add(searchType);
		
		nested2.add(new JLabel("Enter a number:"));
		
		nested4.setLayout(new BorderLayout());
		nested4.add(new JPanel(), BorderLayout.EAST);
		nested4.add(new JPanel(), BorderLayout.WEST);
		nested4.add(startButton,BorderLayout.CENTER);	
		//nested4.add(pauseButton);	
				
		add(nested1);
		//add(nested2);
		//add(nested3);
		add(Box.createRigidArea(new Dimension(0, 115)));

		add(nested4);
		//add(Box.createRigidArea(new Dimension(0, 15)));
		add(Box.createRigidArea(new Dimension(0, 10)));


	}		
}
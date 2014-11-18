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
 * Class that contains settings for sorts
 */
public class sortMenu extends JPanel implements ActionListener
{
	protected static JButton startButton;
	protected static JButton pauseButton;
	
	protected static String sortTypeString;
	
	public static JComboBox sortType;
	
	public static JRadioButton ascending;
	public static JRadioButton descending;
	public static ButtonGroup group;
	
	private JPanel nested1;
	private	JPanel nested2;
	private	JPanel nested3;
	private JPanel nested4;
	
	@SuppressWarnings("unchecked")
	/**
	 * creates a new sortMenu
	 */
	public sortMenu()
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Sorts"));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		sortTypeString = "Selection";
		nested1 = new JPanel();
		nested2 = new JPanel();
		nested3 = new JPanel(new GridLayout(1,2,25,0));
		nested4 = new JPanel();
		
		ascending = new JRadioButton("Ascending");
		descending = new JRadioButton("Descending");
		group = new ButtonGroup();
		ascending.setSelected(true);
		
		group.add(ascending);
		group.add(descending);
		
		String [] sortTypes = {"Selection", "Insertion", "Bubble"/*, "Merge", "Heap"*/};
		sortType = new JComboBox(sortTypes);
		sortType.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					sortTypeString = ""+sortType.getSelectedItem();
			}
		});
		startButton = new JButton("START");
		pauseButton = new JButton("PAUSE/RESUME");
		startButton.addActionListener(new drawPanel());
		pauseButton.addActionListener(new drawPanel());
		pauseButton.addKeyListener(new KeyListener()
		{
			@Override 
              public void keyPressed(KeyEvent e)
              {
                  if(e.getKeyChar() == 'p' || e.getKeyChar() == 'P')
                  	pauseButton.doClick();
                  	
              }
              @Override
              public void keyReleased(KeyEvent e)
              {
              }
              
              @Override
              public void keyTyped(KeyEvent e)
              {
              }
		});

//		startButton.addActionListener(mainInterface.interOne.drawer);
//		pauseButton.addActionListener(mainInterface.interOne.drawer);
		
		nested1.add(new JLabel("Select a type of Sort:"));
		nested1.add(sortType);
		
		nested2.add(new JLabel("Select ascending or descending:"));
		
		nested3.add(ascending);
		nested3.add(descending);
		
		nested4.add(startButton);
		nested4.add(pauseButton);	
		
		add(nested1);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(nested2);
		add(nested3);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(nested4);
		
	}
	/**
	 * Manages what should happen when an action is performed
	 * @param e the actionevent that is performed
	 */
	public void actionPerformed(ActionEvent e)
    { 		
    }
    

}
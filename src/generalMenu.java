import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Class for general menu that allows selection of data type and can open information window
 */
public class GeneralMenu extends JPanel implements ActionListener
{
	private JComboBox dataType;
	private JTextArea information;
	private JLabel genLabel;
	private JLabel dataLabel;
	private JPanel dataPanel;
	private JPanel dataTypePanel;
	private JPanel genPanel;
	private JScrollPane scroller;
	public static String dataSelected;
	private JButton infoButton;
	protected static JButton reset;
	
	
	@SuppressWarnings("unchecked")
		/**
		 * Creates a generalMenu
		 */
	public GeneralMenu()
	{
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Information"));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		String [] dataTypes = {"String", "Integer", "Double"};
		dataType = new JComboBox(dataTypes);
		dataType.addActionListener(this);
		
		dataPanel = new JPanel();
		dataLabel = new JLabel("Choose type of data:");
		dataTypePanel = new JPanel(new GridBagLayout());
		dataPanel.add(dataLabel);
		dataPanel.add(dataType);

		dataSelected = ""+dataType.getSelectedItem();
		//dataTypePanel.add(dataType);
		
		information = new JTextArea(10,20);
		information.setWrapStyleWord(true);
		information.setLineWrap(true);
		information.setBackground(new Color(238,238,238));
		information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Algorithms"));
		scroller = new JScrollPane(information);
		infoButton = new JButton("Information");
		reset = new JButton("Reset");
		infoButton.addActionListener(this);
		reset.addActionListener(new DrawPanel());
		

		genPanel = new JPanel();
		genPanel.setLayout(new BoxLayout(genPanel, BoxLayout.LINE_AXIS));
		genLabel = new JLabel("Algorithms info:    ");
		//genPanel.add(genLabel);
		JPanel nested = new JPanel();
		nested.add(infoButton);
		nested.add(reset);


		
		add(dataPanel);
		add(nested);
	//	add(dataTypePanel);
		add(Box.createRigidArea(new Dimension(0, 15)));

		add(genPanel);
		add(Box.createRigidArea(new Dimension(0, 150)));		
	}
	
	/**
	 * Manages what should happen when an action is performed
	 * @param e the actionevent that is performed
	 */
	public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource() == dataType)
    		dataSelected = dataType.getSelectedItem().toString();
    	if(e.getSource() == infoButton)
    		new InformationWindow();
    	if(e.getSource() == reset)
    	{
    		DrawPanel.nodes.clear();
    		new DrawPanel();
    	}
    }
}
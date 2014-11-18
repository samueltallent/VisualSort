import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * The left panel of the frame, contains settings for the program
 */
public class leftPanel extends JPanel
{
	private JTextArea text;
	
	/**
	 * Creates a leftPanel with a generalMenu, sortMenu, and a searchMenu
	 */
	public leftPanel()
	{
		setLayout(new GridLayout(4,0));

		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Settings"));
		
		add(new generalMenu());
		add(new sortMenu());
		add(new searchMenu());
		
		JPanel infoPanel = new JPanel();
		//infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Information"));
		text = new JTextArea(5,5);
		text.setBackground(new Color(238,238,238));
		text.setEditable(false);
		text.setText("\n\n\n\n\n\n\n\n\n\n    Samuel Tallent's Algorithm Program\n\t                    Version 1.0");
		infoPanel.add(text);
		add(infoPanel);
	}
	

	
	
	
}
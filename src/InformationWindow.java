import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Class for a JFrame that contains information about each of the algorithms
 */
public class InformationWindow extends JFrame implements ActionListener
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	JPanel leftPanel;
	JPanel centerPanel;
	ButtonGroup algorithms;
	JTextArea text;
	
	/**
	 * Creates a new InformationWindow
	 */
	public InformationWindow()
	{
		this.setTitle("Algorithm Information");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new BorderLayout());
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Information"));
		
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Algorithms"));
		text = new JTextArea(10,25);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setBackground(new Color(238,238,238));
		
		text.setText("Scan all items and find the smallest. Swap it into position as the first item. Repeat the selection sort on the remaining N-1 items."
				+"\n\n(n^2) comparisons\n(n) swaps\n\nBest: O(N^2)\nWorst:O(N^2)");
		
		centerPanel.add(text);
		
		String [] algs = {"Selection", "Insertion", "Bubble", "Linear", "Binary"};
		ButtonGroup algTypes = new ButtonGroup();
		leftPanel.add(new JLabel("Sorts:"));
		for(String a : algs)
		{
			JRadioButton button = new JRadioButton(a);
			if(a.equals("Selection"))
				button.setSelected(true);
			if(a.equals("Linear"))
			{
				leftPanel.add(new JLabel(" "));
				leftPanel.add(new JLabel("Searches:"));
			}
			leftPanel.add(button);
			algTypes.add(button);
			button.addActionListener(this);
		}
		
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	/**
	 * Manages what should happen when an action is performed
	 * @param e the actionevent that is performed
	 */
	public void actionPerformed(ActionEvent e)
	{
		String input = ((JRadioButton) e.getSource()).getText();
		
		switch(input)
		{
			case "Selection": text.setText("Scan all items and find the smallest. Swap it into position as the first item. Repeat the selection sort on the remaining N-1 items."
				+"\n\n(n^2) comparisons\n(n) swaps\n\nBest: O(N^2)\nWorst:O(N^2)");
				break;
			case "Insertion": text.setText("Start with a sorted list of 1 element on the left, and N-1 unsorted items on the right. Take the first unsorted item (element #2) and insert it into the sorted list, moving elements as necessary. We now have a sorted list of size 2, and N -2 unsorted elements. Repeat for all elements."+
				"\n\n(n^2) comparisons and swaps\n\nBest: O(N)\nWorst: O(N^2)");
				break;
			case "Bubble": text.setText("Starting on the left, compare adjacent items and keep “bubbling” the larger one to the right (it’s in its final place). Bubble sort the remaining N -1 items."+
				"\n\n(n^2)comparisons and swaps\n\nBest: O(N)\nWorst: O(N^2)");
				break;
			case "Linear": text.setText("Searches through each element from left to right.\n\nO(N)");
				break;
			case "Binary": text.setText("The algorithm compares the search key value with the key value of the middle element of the array. If the keys match, then a matching element has been found and its index, or position, is returned. Otherwise, if the search key is less than the middle element's key, then the algorithm repeats its action on the sub-array to the left of the middle element or, if the search key is greater, on the sub-array to the right. If the remaining array to be searched is empty, then the key cannot be found in the array and a special \"not found\" indication is returned.\n\n"+
				"O(log n)");
		}
	}
}
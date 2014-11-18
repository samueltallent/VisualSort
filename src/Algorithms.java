import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.io.*;
import java.awt.Point;
import javax.swing.*;

/**
 * Algorithms class that controls which type of algorithm(String, int, double) should be used.
 */
public class Algorithms
{
    private int degreeb;
    protected ArrayList<Integer> intlist;
    protected ArrayList<Double> doublelist;
    protected ArrayList<String> stringlist;
    protected static ArrayList<Node> nodes;
    protected static Queue<Point> switches;
    public static final int SELECTED_ITEM = -5;
    public static final int WRONG_ITEM = -10;
    
    protected static boolean isPaused;
    IntegerAlgorithms intalgs;
    StringAlgorithms stringalgs;
    DoubleAlgorithms doublealgs;
    
    /**
     * Creates an Algorithms to control which type of data should be used
     * @param ns an ArrayList<Node> of the nodes on the screen
     */
    public Algorithms(ArrayList<Node> ns, drawPanel drawer)
    {
    	intalgs = null;
    	stringalgs = null;
    	doublealgs = null;
    	degreeb = 0;
    	nodes = drawer.getNodes();
    	//System.out.println ("\nNodeAlgs: " + nodes);
    		for(Node n : nodes)
    		{
    			//System.out.println(n.text + " xPosition: " + n.xPosition + " xOrigin: " + n.xOrigin);
    		}
    		
    	Node n1 = nodes.get(0);
    	switches = new LinkedList<Point>();
    	if(n1.getType().equals("Integer"))
    	{

    		intlist = new ArrayList<Integer>();
    		for(Node n : nodes)
    			intlist.add(n.getInt());
			intalgs = new IntegerAlgorithms(intlist);

    	}
    			
    	else if(n1.getType().equals("Double"))
    	{
    		doublelist = new ArrayList<Double>();
    		for(Node n : nodes)
    			doublelist.add(n.getDouble());
    		doublealgs = new DoubleAlgorithms(doublelist);
    	}
    	else
    	{
    		stringlist = new ArrayList<String>();
    		for(Node n : nodes)
    			stringlist.add(n.getString());
    		stringalgs = new StringAlgorithms(stringlist);
    	}
    	
    }
	
	/**
	 * Resets the nodes in the list and the ints in the list
	 * @param String type of data to reset
	 */
	public void resetList(String type)
	{
		if(type.equals("Integer"))
    	{

    		intlist = new ArrayList<Integer>();
    		for(Node n : nodes)
    			intlist.add(n.getInt());
			intalgs = new IntegerAlgorithms(intlist);

    	}
    			
    	else if(type.equals("Double"))
    	{
    		doublelist = new ArrayList<Double>();
    		for(Node n : nodes)
    			doublelist.add(n.getDouble());
    		doublealgs = new DoubleAlgorithms(doublelist);
    	}
    	else
    	{
    		stringlist = new ArrayList<String>();
    		for(Node n : nodes)
    			stringlist.add(n.getString());
    		stringalgs = new StringAlgorithms(stringlist);
    	}
	}
	
	/**
	 * Performs each action that is stored in the queue
	 */
	public void performSwitches()
	{
			//System.out.println ("PerformSwitches on thread " + drawPanel.count);

		while(!switches.isEmpty())
		{
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e){}
			//System.out.println (nodes);
			Point p = null;
			if(switches.peek() != null)
				if(switches.peek() != null)
					p = switches.remove();
			Node a = nodes.get((int)p.getX());
			Node b = null;
			int bint = 0;
			if(p.getY() > -1)
			{
				b = nodes.get((int)p.getY());	
			}
			
			else
			{
				bint = (int)p.getY();
			}
				
		//	System.out.println ("A: " + a.text + " " + p.getX());
		//	System.out.println ("B: " + b.text + " " + p.getY());
			
			if(bint == -10)
			{
				a.setImage(a.wImg);
				if(!drawPanel.pointer.isRunning())
					drawPanel.pointer.setRunning(true);
				drawPanel.pointer.setNode(a);
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e){}
				//System.out.println (a.text);
			}
			
			else if(bint == -5)
			{
				a.setImage(a.sImg);
				
				if(!drawPanel.pointer.isRunning())
					drawPanel.pointer.setRunning(true);
				drawPanel.pointer.setNode(a);
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e){}
				while(!switches.isEmpty())
					switches.remove();
			}
			
			
			else if(b != null)
			{
				int aloc = (int)p.getX();
				int bloc = (int)p.getY();
				Node tempN = nodes.get(aloc);
				nodes.set(aloc, b);
				nodes.set(bloc, tempN);
				for(Node n : nodes)
					n.setImage(n.image);
				a.setImage(a.sImg);
				try
				{	
					Thread.sleep(100);
				}
				catch(InterruptedException e){}
				while(a.degreeb <=180)
					a.switchPositions(b);
				a.degreeb = 0;
			}
			else;
			
		}
	}
	
	/**
	 * Decides which ascendingBubbleSort to use
	 */
	public void ascendingBubbleSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
		{
			resetList("String");
			stringalgs.ascendingBubbleSort();
		}
		else if(intalgs == null)
		{
			resetList("Double");
			doublealgs.ascendingBubbleSort();

		}
		else
		{
			resetList("Integer");
			intalgs.ascendingBubbleSort();
		}
			
	}
	
	/**
	 * Decides which ascendingBubbleSort to use
	 */
	public void descendingBubbleSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.descendingBubbleSort();
		else if(intalgs == null)
			doublealgs.descendingBubbleSort();
		else
			intalgs.descendingBubbleSort();
	}

	/**
	 * Decides which ascendingSelectionSort to use
	 */
	public void ascendingSelectionSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.ascendingSelectionSort();
		else if(intalgs == null)
			doublealgs.ascendingSelectionSort();
		else
			intalgs.ascendingSelectionSort();
	}
	
	/**
	 * Decides which descendingSelectionSort to use
	 */
	public void descendingSelectionSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.descendingSelectionSort();
		else if(intalgs == null)
			doublealgs.descendingSelectionSort();
		else
			intalgs.descendingSelectionSort();
	}
	
	/**
	 * Decides which ascendingInsertionSort to use
	 */
	public void ascendingInsertionSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.ascendingInsertionSort();
		else if(intalgs == null)
			doublealgs.ascendingInsertionSort();
		else
			intalgs.ascendingInsertionSort();
	}
	
	/**
	 * Decides which descendingInsertionSort to use
	 */
	public void descendingInsertionSort()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.descendingInsertionSort();
		else if(intalgs == null)
			doublealgs.descendingInsertionSort();
		else
			intalgs.descendingInsertionSort();
	}
	
	/**
	 * Decides which linearSearch to use
	 */
	public void linearSearch()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.linearSearch();
		else if(intalgs == null)
			doublealgs.linearSearch();
		else
		{
			resetList("Integer");
			intalgs.linearSearch();
		}
			
	}
	
	/**
	 * Decides which binarySearch to use
	 */
	public void binarySearch()
	{
		for(Node n : nodes)
			n.reset();
		if(intalgs == null && doublealgs == null)
			stringalgs.binarySearch();
		else if(intalgs == null)
			doublealgs.binarySearch();
		else
			intalgs.binarySearch();
	}
}

/**
* Class that contains all algorithms for Integers
*/
class IntegerAlgorithms
{
	ArrayList<Integer> list;
	
	/**
	 * Creates an IntegerAlgorithms that can call the integer algorithm methods
	 * @param ints an ArrayList<Integer> that represents the nodes displayed on the screen
	 */
	public IntegerAlgorithms(ArrayList<Integer> ints)
	{
		list = ints;
	}
	
	/**
	 * Sorts a list using an Ascending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingBubbleSort()
	{
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i) < list.get(i-1))
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					int temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
	//	System.out.println (list);
	}
	
	/**
	 * Sorts a list using an Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public boolean descendingBubbleSort()
	{
		//System.out.println (list);
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i) > list.get(i-1))
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					int temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
		//System.out.println (list);
		return sorted;
	}
	/**
		* Sorts a list using an Ascending Selection Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingSelectionSort()
	{
		int smallest;
		for(int p = 0; p < list.size()-1; p++)
		{
			smallest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i) < list.get(smallest))
				{
					smallest = i;
				}
			}
			if(list.get(smallest) != list.get(p))
			{
				Algorithms.switches.add(new Point(smallest, p));
				int temp = list.get(smallest);
				list.set(smallest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using an Descending Selection sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingSelectionSort()
	{
		int largest;
		for(int p = 0; p < list.size()-1; p++)
		{
			largest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i) > list.get(largest))
				{
					largest = i;
				}
			}
			if(list.get(largest) != list.get(p))
			{
				Algorithms.switches.add(new Point(largest, p));
				int temp = list.get(largest);
				list.set(largest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using an Ascending Insertion Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingInsertionSort()
	{
		for(int i = 1; i < list.size(); i++)
		{
			int key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1) > key)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
	}
	
	/**
	 * Sorts a list using an Descending Insertion Sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingInsertionSort()
	{
		for(int i = 1; i < list.size(); i++)
		{
			int key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1) < key)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
	}
	
	/**
	 * Searches through the list linearly
	 */
	public void linearSearch()
	{
		int key = Integer.parseInt(JOptionPane.showInputDialog("What value would you like to search for?"));
		for(Node n : Algorithms.nodes)
			n.reset();
	//	System.out.println ("INT LIST" + list);
		for(int i = 0; i < list.size(); i++)
		{

			int value = list.get(i);
			Node a = Algorithms.nodes.get(i);
			//System.out.println (a.text);
			if(key != value)
			{
				//a.setImage(a.wImg);
				Algorithms.switches.add(new Point(i, Algorithms.WRONG_ITEM));
				//System.out.println ("REACHED");				
			}
			else
			{
				Algorithms.switches.add(new Point(i, Algorithms.SELECTED_ITEM));
			}
				
		}
	}
	
	/**
	 * Searches through the list using a binary search
	 */
	public void binarySearch()
	{
		for(Node n : Algorithms.nodes)
			n.reset();
		int key = Integer.parseInt(JOptionPane.showInputDialog("What value would you like to search for?"));
		int min = 0;
		int max = list.size() - 1;
		int mid;
		while(min <= max)
		{
			mid = (min+max)/2;
			if(key < list.get(mid))
			{
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				max = mid-1;	
			}

			else if(key > list.get(mid))
			{
				
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				min = mid+1;
			}
			else
			{
				Algorithms.switches.add(new Point(mid, Algorithms.SELECTED_ITEM));
				break;			
			}
		}
	}
}

/**
 * Class of algorithms for doubles
 */
 
class DoubleAlgorithms
{
	ArrayList<Double> list;
	
	/**
	 * Creates a DoubleAlgorithms that can call the double algorithm methods
	 * @param ints an ArrayList<Double> that represents the nodes displayed on the screen
	 */
	public DoubleAlgorithms(ArrayList<Double> doubles)
	{
		list = doubles;
	}
	
	/**
	 * Sorts a list using an Ascending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public boolean ascendingBubbleSort()
	{
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i) < list.get(i-1))
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					double temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
		//System.out.println (list);
		return sorted;
	}
	
	/**
	 * Sorts a list using an Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public boolean descendingBubbleSort()
	{
	//	System.out.println (list);
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i) > list.get(i-1))
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					double temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
	//	System.out.println (list);
		return sorted;
	}
	
	/**
	 * Sorts a list using an Ascending Selection Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingSelectionSort()
	{
		int smallest;
		for(int p = 0; p < list.size()-1; p++)
		{
			smallest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i) < list.get(smallest))
				{
					smallest = i;
				}
			}
			if(list.get(smallest) != list.get(p))
			{
				Algorithms.switches.add(new Point(smallest, p));
				double temp = list.get(smallest);
				list.set(smallest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using a Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingSelectionSort()
	{
		int largest;
		for(int p = 0; p < list.size()-1; p++)
		{
			largest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i) > list.get(largest))
				{
					largest = i;
				}
			}
			if(list.get(largest) != list.get(p))
			{
				Algorithms.switches.add(new Point(largest, p));
				double temp = list.get(largest);
				list.set(largest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using an Ascending Insertion Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingInsertionSort()
	{
	//	System.out.println (list);
		for(int i = 1; i < list.size(); i++)
		{
			double key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1) > key)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
	//	System.out.println (list);
	}
	
	/**
	 * Sorts a list using a Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingInsertionSort()
	{
	//	System.out.println (list);
		for(int i = 1; i < list.size(); i++)
		{
			double key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1) < key)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
	//	System.out.println (list);
	}
	
	
	public void linearSearch()
	{
		double key = Double.parseDouble(JOptionPane.showInputDialog("What value would you like to search for?"));
		for(Node n : Algorithms.nodes)
			n.setImage(n.image);
		//System.out.println (list);
		for(int i = 0; i < list.size(); i++)
		{
			double value = list.get(i);
		//	System.out.println (value);
			Node a = Algorithms.nodes.get(i);
			//System.out.println (a.text);
			if(key != value)
			{
				//a.setImage(a.wImg);
				Algorithms.switches.add(new Point(i, Algorithms.WRONG_ITEM));
				//System.out.println ("REACHED");
				
			}
			else
				Algorithms.switches.add(new Point(i, Algorithms.SELECTED_ITEM));
		}
	}
	
	/**
	 * Searches through the list using a binary search
	 */
	public void binarySearch()
	{
		for(Node n : Algorithms.nodes)
			n.reset();
		double key = Double.parseDouble(JOptionPane.showInputDialog("What value would you like to search for?"));
		int min = 0;
		int max = list.size() - 1;
		int mid;
		while(min <= max)
		{
			mid = (min+max)/2;
			if(key < list.get(mid))
			{
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				max = mid-1;	
			}

			else if(key > list.get(mid))
			{
				
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				min = mid+1;
			}
			else
			{
				Algorithms.switches.add(new Point(mid, Algorithms.SELECTED_ITEM));
				break;			
			}
		}
	}
}

/**
* Class that contains all algorithms for Integers
*/
class StringAlgorithms
{
	ArrayList<String> list;
	
	/**
	 * Creates a StringAlgorithms that can call the String algorithm methods
	 * @param ints an ArrayList<String> that represents the nodes displayed on the screen
	 */
	public StringAlgorithms(ArrayList<String> strings)
	{
		list = strings;
	}
	
	/**
	 * Sorts a list using an Ascending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public boolean ascendingBubbleSort()
	{
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i).compareTo(list.get(i-1)) < 0)
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					String temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
	//	System.out.println (list);
		return sorted;
	}
	
	/**
	 * Sorts a list using a Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public boolean descendingBubbleSort()
	{
	//	System.out.println (list);
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = list.size()-1; i >= p; i--)
			{
				if(list.get(i).compareTo(list.get(i-1)) > 0)
				{
					Algorithms.switches.add(new Point(i,i-1));
					//System.out.println ("ADDED: " + list.get(i) + " ("+i+") " + list.get(i-1)+ " (" + (i-1) +")");
					String temp = list.get(i);
					list.set(i,list.get(i-1));
					list.set(i-1, temp);
					sorted = false;
				}
			}
			p++;
		}
		//System.out.println (list);
		return sorted;
	}

	/**
	 * Sorts a list using an Ascending Selection Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingSelectionSort()
	{
		int smallest;
		for(int p = 0; p < list.size()-1; p++)
		{
			smallest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i).compareTo(list.get(smallest)) < 0)
				{
					smallest = i;
				}
			}
			if(list.get(smallest) != list.get(p))
			{
				Algorithms.switches.add(new Point(smallest, p));
				String temp = list.get(smallest);
				list.set(smallest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using a Descending Selection Sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingSelectionSort()
	{
		int largest;
		for(int p = 0; p < list.size()-1; p++)
		{
			largest = p;
			for(int i = p+1; i < list.size(); i++)
			{
				if(list.get(i).compareTo(list.get(largest)) > 0)
				{
					largest = i;
				}
			}
			if(list.get(largest) != list.get(p))
			{
				Algorithms.switches.add(new Point(largest, p));
				String temp = list.get(largest);
				list.set(largest, list.get(p));
				list.set(p, temp);
			}
		}
	}
	
	/**
	 * Sorts a list using an Ascending Insertion Sort and fills the queue with the switches to be made graphically.
	 */
	public void ascendingInsertionSort()
	{
	//	System.out.println (list);
		for(int i = 1; i < list.size(); i++)
		{
			String key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1).compareTo(key) > 0)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
	//	System.out.println (list);
	}
	
	/**
	 * Sorts a list using a Descending Bubble Sort and fills the queue with the switches to be made graphically.
	 */
	public void descendingInsertionSort()
	{
		//System.out.println (list);
		for(int i = 1; i < list.size(); i++)
		{
			String key = list.get(i);
			int position = i;
			while(position > 0 && list.get(position-1).compareTo(key) < 0)
			{
				Algorithms.switches.add(new Point(position, position-1));
				list.set(position, list.get(position-1));
				position--;
			}
			list.set(position, key);				
		}
		//System.out.println (list);
	}
	
	/**
	 * Searches through the list using a binary search
	 */
	public void linearSearch()
	{
		String key = JOptionPane.showInputDialog("What value would you like to search for?");
		for(Node n : Algorithms.nodes)
			n.setImage(n.image);
		//System.out.println (list);
		for(int i = 0; i < list.size(); i++)
		{
			String value = list.get(i);
			//System.out.println (value);
			Node a = Algorithms.nodes.get(i);
		//	System.out.println (a.text);
			if(!key.equals(value))
			{
				//a.setImage(a.wImg);
				Algorithms.switches.add(new Point(i, Algorithms.WRONG_ITEM));
				//System.out.println ("REACHED");
				
			}
			else
				Algorithms.switches.add(new Point(i, Algorithms.SELECTED_ITEM));
		}
	}
	
	/**
	 * Searches through the list using a binary search
	 */
	public void binarySearch()
	{
		for(Node n : Algorithms.nodes)
			n.reset();
		String key = JOptionPane.showInputDialog("What value would you like to search for?");
		int min = 0;
		int max = list.size() - 1;
		int mid;
		while(min <= max)
		{
			mid = (min+max)/2;
			if(key.compareTo(list.get(mid)) < 0)
			{
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				max = mid-1;	
			}

			else if(key.compareTo(list.get(mid)) > 0)
			{
				
				Algorithms.switches.add(new Point(mid, Algorithms.WRONG_ITEM));
				min = mid+1;
			}
			else
			{
				Algorithms.switches.add(new Point(mid, Algorithms.SELECTED_ITEM));
				break;			
			}
		}
	}
}
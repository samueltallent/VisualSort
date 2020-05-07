import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class for a node that represents each index in an array and the data that it stores
 */
public class Node
{
	protected int xPosition;
	protected int yPosition;
	protected int xOrigin;
	protected int yOrigin;
	
	public static int boxWidth;
	public static int boxHeight;
	
	private int intData;
	private String stringData;
	private double doubleData;
	String text;
	protected BufferedImage bImg;
	protected BufferedImage sImg;
	protected BufferedImage wImg;
	protected BufferedImage image;
	private String type;
    protected int degreeb = 0;
    protected Node toSwitch;
    protected static boolean isPaused;
	
	/**
	 * Creates a new node with x and y positions and data
	 * @param x the x position
	 * @param y the y position
	 * @param data the data of the Node
	 */
	public Node(int x, int y, int data)
	{
		xPosition = x;
		yPosition = y;
		xOrigin = x;
		yOrigin = y;
		
		intData = data;
		text = ""+data;
		
		bImg = null;
		type = "Integer";
		
		isPaused = false;
	}
	
	/**
	 * Creates a new node with x and y positions and data
	 * @param x the x position
	 * @param y the y position
	 * @param data the data of the Node
	 */
	public Node(int x, int y, String data)
	{
		xPosition = x;
		yPosition = y;
		xOrigin = x;
		yOrigin = y;


		stringData = data;
		text = data;
		
		bImg = null;
		type = "String";
	}
	
	/**
	 * Creates a new node with x and y positions and data
	 * @param x the x position
	 * @param y the y position
	 * @param data the data of the Node
	 */
	public Node(int x, int y, double data)
	{
		xPosition = x;
		yPosition = y;
		xOrigin = x;
		yOrigin = y;
		
		doubleData = data;
		text = ""+data;
		
		bImg = null;
		type = "Double";
	}
	
	/**
	 * Gets the data of the node as text
	 * @return String text of the data
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Gets the int data of the node 
	 * @return int data
	 */
	public int getInt()
	{
		return intData;
	}
	
	/**
	 * Gets the double data of the node 
	 * @return double data
	 */
	public double getDouble()
	{
		return doubleData;
	}
	
	/**
	 * Gets the String data of the node 
	 * @return String data
	 */
	public String getString()
	{
		return stringData;
	}
	
	/**
	 * Sets the current image of the node
	 * @param b the image to set as the current image
	 */
	public void setImage(BufferedImage b)
	{
		bImg = b;
	}
	
	/**
	 * Sets the selected image of the node
	 * @param b the image to set as the selected image
	 */
	public void setSelectedImage(BufferedImage b)
	{
		sImg = b;
	}

	/**
	 * Sets the wrong image of the node
	 * @param b the image to set as the wrong image
	 */	
	public void setWrongImage(BufferedImage b)
	{
		wImg = b;
	}
	
	/**
	 * Returns the current image of the node
	 * @return BufferedImage the current image of the node
	 */
	public BufferedImage getImage()
	{
		return bImg;
	}
	
	/**
	 * Gets the type of the node
	 * @return String the type of data the node contains as a String
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Resets the node's data that is relevant to displaying on the screen
	 */
	public void reset()
	{
		xOrigin = xPosition;
		yOrigin = yPosition;
		bImg = image;
	}
	
	/**
	 * Switches the positions of two nodes by updating their x and y positions on a Thread
	 * @param b the node to switch with this node
	 */
	public void switchPositions(Node b)
	{
		int centerx = (b.xOrigin + xOrigin)/2;
		int centery = yOrigin;
		int radius = (b.xOrigin - xOrigin)/2;
		int degreea = degreeb + 180;
		
//		if(degreeb == 0)
//			System.out.println ("switching" + text + " " + b.text);
		if(degreeb <= 180)
		{
			double radianb = (degreeb/180.0)*Math.PI;
			b.xPosition = (int)(centerx + Math.cos(radianb)*radius);
			if(b.yPosition < 50)
				b.yPosition = 50;
			else
				b.yPosition = (int)(centery - Math.sin(radianb)*radius);
			
			double radiana = (degreea/180.0)*Math.PI;
			xPosition = (int)(centerx + Math.cos(radiana)*radius);
			yPosition = (int)(centery - Math.sin(radiana)*radius);
			if(!isPaused)
				degreeb++;
			try
			{	
				Thread.sleep(3);
			}
			catch(InterruptedException e){}
		}
		if(degreeb == 181)
		{
			xOrigin = xPosition;
			b.xOrigin = b.xPosition;
			try
			{	
				Thread.sleep(100);
			}
			catch(InterruptedException e){}
		}
		

	}
	
	/**
	 * Returns the string text of the node
	 * @return String the string text of the node
	 */	
	public String toString()
	{
		return (text);
	}
}
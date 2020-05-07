import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

/**
 * Pointer class for pointing at the selected item when searching
 */
public class Pointer
{
	protected int x;
	protected int y;
	private Node node;
	private boolean running;
	protected BufferedImage image;
	
	/**
	 * Creates a new pointer
	 */
	public Pointer()
	{
		y = 525;
		running = false;
	}
	
	/**
	 * Sets the node that the pointer should point at
	 * @param n the node to point at
	 */
	public void setNode(Node n)
	{
		node = n;
		x = node.xPosition + Node.boxWidth/2 + 35;
		try
	    {
	    	image = ImageIO.read(getClass().getResourceAsStream("img/arrow1.png"));
	    } catch (IOException e) {};
		
	}
	
	/**
	 * Sets whether or not the pointer should be visible
	 * @param b is the pointer visible
	 */
	public void setRunning(boolean b)
	{
		running = b;
	}
	
	/**
	 * Gets whether or not the pointer is running
	 * @return boolean is the pointer visible
	 */
	public boolean isRunning()
	{
		return running;
	}
}
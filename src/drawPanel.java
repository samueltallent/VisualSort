import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.DecimalFormat;


/**
 * drawPanel is the class where the graphical portion of the program is displayed
 */
public class drawPanel extends JComponent implements ActionListener
{
	private JTextArea text;
	private String dataType;
	private int x1;
	private int y1;
	private static int xdist;
	Graphics g2;
	private static int boxWidth;
	private static int boxHeight;
	static Thread repainter;
	protected static boolean started;
	private boolean finished;
	protected static Pointer pointer;
	Algorithms algs;
	protected static int count = 0;
	
	protected static ArrayList<Node> nodes = new ArrayList<Node>();
	
	/**
	 * creates a drawPanel that displays the graphics in the application
	 */
	public drawPanel()
	{
		//System.out.println (count++);
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Draw Area"));
		this.addKeyListener(new KeyListener()
		{
			@Override 
              public void keyPressed(KeyEvent e)
              {
                  System.out.println (e.getKeyChar());
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
		
		dataType = generalMenu.dataSelected;
		started = false;
		finished = false;
		xdist = 130;
		
		
		repainter = new Thread(new Runnable()
		{
		    @Override
		    public void run()
		    {
		        while (true)
		        {
		        	repaint();
				    try 
			        {
			        	if(started && !finished)
			        	{
			        		//System.out.println ("Started");
			        		algs.performSwitches();
			        	}
			            Thread.sleep(5);
			        } catch (InterruptedException ignored) {ignored.printStackTrace();}
			        catch(IndexOutOfBoundsException exception){exception.printStackTrace();}
			        catch(NullPointerException exception) { }
		        }
		    }
		});
		repainter.setName("Panel repaint");
		repainter.setPriority(Thread.MIN_PRIORITY);
		repainter.start();
		x1 = 20;
		y1 = 360;
		setVisible(true);
	}
	
	/**
	 * Sets the data of the ArrayList<Node> based on what data type is selected in the general menu
	 */
	public void setData()
	{
		
		dataType = generalMenu.dataSelected;
		
		if(nodes.size() != 0)
			nodes.clear();
		switch(dataType)
		{
			case "String":
				for(int i = 0; i < 10; i++)
				{
					int randInt = (int)((Math.random()*2009)+1);
					nodes.add(new Node(x1 + (i * xdist), y1+100, selectionMenu.words.get(randInt)));
				}
				break;
			case "Integer":
				for(int i = 0; i < 10; i++)
				{
					int randInt = (int)((Math.random()*100)+1);
					nodes.add(new Node(x1 + (i * xdist), y1+100, randInt));
				}
				break;
			case "Double":
				for(int i = 0; i < 10; i++)
				{
					DecimalFormat df = new DecimalFormat("#.00"); 
					double randDouble = ((Math.random()*100)+1);
					randDouble = Double.parseDouble(df.format(randDouble));
					nodes.add(new Node(x1 + (i * xdist), y1+100, randDouble));
				}
				break;
		}
		started = true;
		//System.out.println ("NODES " + nodes.size());
	}

	
	/**
	 * Overrides JComponent's paintComponent and calls the render method
	 * @param g The Graphics component of drawPanel.
	 */
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        //System.out.println ("Reached repaint");
        render(g);
    }

	/**
	 * Manages what should be rendered at any given time on the drawPanel.
	 * @param g The Graphics component of drawPanel.
	 */
    private void render(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;

	    if(started && nodes.size() != 0)
	    {
	    	for(Node n : nodes)
				g2.drawImage(n.getImage(), n.xPosition, n.yPosition, n.getImage().getWidth(), n.getImage().getHeight(), null);
			
			if(pointer != null && pointer.image != null && pointer.isRunning())
			{
				g2.drawImage(pointer.image, pointer.x, pointer.y, pointer.image.getWidth(), pointer.image.getHeight(), null);
			} 
	    }

    }
    
    /**
     * Returns an ArrayList<Node> of the nodes in the drawPanel
     * @return ArrayList<Node> nodes in the drawPanel
     */
    public ArrayList<Node> getNodes()
    {
    	return nodes;
    }

	/**
	 * Sets the different types of images for each of the Nodes
	 */
	private void createImageArray()
	{
    	BufferedImage img = null;
    	BufferedImage selected = null;
    	BufferedImage wrong = null;

		try
	    {
			img = ImageIO.read(getClass().getResourceAsStream("img/Rectangle1.png"));
			selected = ImageIO.read(getClass().getResourceAsStream("img/Rectangle2.png"));
			wrong = ImageIO.read(getClass().getResourceAsStream("img/Rectangle3.png"));
	    	boxWidth = img.getWidth();
	    	boxHeight = img.getHeight();	    	
	    } catch (IOException e) { /*e.printStackTrace();*/};
	    
		for(int i = 0; i < 10; i++)
		{	    	
	    	Node n = nodes.get(i);
	 
		    BufferedImage tempImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			    
		    Graphics2D g3 = tempImg.createGraphics();
		    g3.drawImage(img,0,0,null);
		    g3.setFont(new Font("Monospaced", Font.PLAIN, 32));
		    g3.setColor(new Color(192,192,192));
		    FontMetrics fm = g3.getFontMetrics(new Font("Monospaced", Font.PLAIN, 32));
		    int xoff = (int)(fm.getStringBounds(n.getText(), g3).getWidth());
		    xoff *= (15.0/19)/2;
		    xoff = (116 - xoff)/5;
		    
		    g3.drawString(n.getText(), xoff, 35);
		    //System.out.println ()
			    
		    
		    try
		    {
		    	ImageIO.write(tempImg, "png", new File("saved.png"));
		    }
		    catch(IOException e) {};
		    //imgs.add(tempImg);
		    n.setImage(tempImg);
		    n.image = tempImg;
		    	
	    }
	    
	    for(int i = 0; i < 10; i++)
		{	    	
	    	Node n = nodes.get(i);
	 
		    BufferedImage tempImg = new BufferedImage(selected.getWidth(), selected.getHeight(), BufferedImage.TYPE_INT_ARGB);
			    
		    Graphics2D g3 = tempImg.createGraphics();
		    g3.drawImage(selected,0,0,null);
		    g3.setFont(new Font("Monospaced", Font.PLAIN, 32));
		    g3.setColor(new Color(192,192,192));
		    FontMetrics fm = g3.getFontMetrics(new Font("Monospaced", Font.PLAIN, 32));
		    int xoff = (int)(fm.getStringBounds(n.getText(), g3).getWidth());
		    xoff *= (15.0/19)/2;
		    xoff = (116 - xoff)/5;
		    
		    g3.drawString(n.getText(), xoff, 35);
		    //System.out.println ()
			    
		    
		    try
		    {
		    	ImageIO.write(tempImg, "png", new File("saved2.png"));
		    }
		    catch(IOException e) {};
		    //selImgs.add(tempImg);
		    n.setSelectedImage(tempImg);
		    	
	    }
	    
	    for(int i = 0; i < 10; i++)
		{	    	
	    	Node n = nodes.get(i);
	 
		    BufferedImage tempImg = new BufferedImage(wrong.getWidth(), wrong.getHeight(), BufferedImage.TYPE_INT_ARGB);
			    
		    Graphics2D g3 = tempImg.createGraphics();
		    g3.drawImage(wrong,0,0,null);
		    g3.setFont(new Font("Monospaced", Font.PLAIN, 32));
		    g3.setColor(new Color(192,192,192));
		    FontMetrics fm = g3.getFontMetrics(new Font("Monospaced", Font.PLAIN, 32));
		    int xoff = (int)(fm.getStringBounds(n.getText(), g3).getWidth());
		    xoff *= (15.0/19)/2;
		    xoff = (116 - xoff)/5;
		    
		    g3.drawString(n.getText(), xoff, 35);   
		    try
		    {
		    	ImageIO.write(tempImg, "png", new File("saved3.png"));
		    }
		    catch(IOException e) {};
		    //wrongImgs.add(tempImg);
		    n.setWrongImage(tempImg);
		    	
	    }						
	}

	/**
	 * Manages what should happen when an action is performed
	 * @param e the actionevent that is performed
	 */
	public void actionPerformed(ActionEvent e)
    {
    	
    	if(!started)
    	{
    		setData();
    		algs = new Algorithms(nodes, this);
    		createImageArray();
    	}
    	
    	
    	
    	if(e.getSource() == sortMenu.startButton)
    	{
    		if(pointer != null && pointer.isRunning())
    			pointer.setRunning(false);
    		algs = new Algorithms(nodes, this);
    		if(sortMenu.ascending.isSelected())
    		{
    			String sortType = ""+sortMenu.sortType.getSelectedItem();
    			switch(sortType)
    			{
    				case "Bubble": algs.ascendingBubbleSort();
    					break;
    				case "Selection": 
    					algs.ascendingSelectionSort();
    					break;
    				case "Insertion": algs.ascendingInsertionSort();
    					break;
    			}
    		}
    		else
    		{
    			String sortType = ""+sortMenu.sortType.getSelectedItem();
    			switch(sortType)
    			{
    				case "Bubble": algs.descendingBubbleSort();
    					break;
    				case "Selection": algs.descendingSelectionSort();
    					break;
    				case "Insertion": algs.descendingInsertionSort();
    					break;
    			}
    		}
    		try
    		{
    			Thread.sleep(50);
    			finished = false;
    			Thread.sleep(50);
    			finished = false;
    			Thread.sleep(100);
    			finished = true;
    		}
    		catch(Exception exc){}
    		


    	}
    	
    	if(e.getSource() == sortMenu.pauseButton)
    	{
    		Node.isPaused = !Node.isPaused;
    	}
    	
    	if(e.getSource() == searchMenu.startButton)
    	{
    		String searchType = "" + searchMenu.searchType.getSelectedItem();
    		algs = new Algorithms(nodes, this);
    		pointer = new Pointer();
    		for(Node n : nodes)
    			n.reset();
    		switch(searchType)
    		{
    			case "Linear": algs.linearSearch();
    				break;
    			case "Binary": algs.binarySearch();
    				break;
    		}
    		
    		try
    		{
    			Thread.sleep(50);
    			finished = false;
    			Thread.sleep(50);
    			finished = false;
    			Thread.sleep(100);
    			finished = true;
    		}
    		catch(Exception exc){}
    	}
    
    	if(e.getSource() == searchMenu.pauseButton)
    	{
    		Algorithms.isPaused = !Algorithms.isPaused;
    	}
    	
    	if(e.getSource() == generalMenu.reset)
    	{	
    		finished = true;
    		started = false;
    		nodes.clear();
    		setData();
    		algs = new Algorithms(nodes, this);
    		new drawPanel();	
    	}
    }

}
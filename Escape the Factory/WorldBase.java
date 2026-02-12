 
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldBase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldBase extends World
{
    private int xSize;
    private int ySize;
    private static boolean isFirst = true;

    /**
     * Constructor for objects of class WorldBase.
     * 
     */
    public WorldBase()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        this(1280,800);
    }

    /**
     * Constructor for objects of class World Base.
     * 
     */
    public WorldBase(int xSize, int ySize)
    {    
        super(xSize, ySize, 1); 
        this.xSize = xSize;
        this.ySize = ySize;
        
        if (isFirst)
        {
            GameData.resetVariables();
            isFirst = false;
            System.out.println("Reset");
        }
    }

    /**
     * Put key on top
     */
    public void putKeyOnTop()
    {
        // Make key
        Label keyAmount = new Label("Keys: ", "keys");
        this.addObject(keyAmount,100,50);

        // Make timecounter
        Label timer = new Label("Time Left: ", "timer");
        this.addObject(timer,100,80);
    }

    /**
     * Set the backround of the world scaled
     */
    public void setBackgroundScaled(String image, int x, int y)
    {
        GreenfootImage background = new GreenfootImage(image);
        background.scale(x, y);
        this.setBackground(background);
    }

    /**
     * Get x
     */

    public int getXSize()
    {
        return this.xSize;
    }

    /**
     * Get y
     */

    public int getYSize()
    {
        return this.ySize;
    }

        /**
     * Set x
     */

    public void setXSize(int i)
    {
        this.xSize = i;
    }

    /**
     * Get y
     */

    public void setYSize(int i)
    {
        this.ySize = i;
    }

    /**
     * Act (dispays key count)
     */
    public void updateKeys()
    {
        
    }

}

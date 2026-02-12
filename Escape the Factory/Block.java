import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    private int xSize;
    private int ySize;
    private int minDistance;
    
    // Static variable to track which block is currently being moved
    // In the code this is used so the player can only move a block
    private static Block currentlyMoving = null;
    
    // Array with classes that the block can't go through
    private Class<? extends Actor>[] actorsToCheck = (Class<? extends Actor>[]) new Class[] 
    { 
        MaizeWall.class,
        Block.class,
        TressureChest.class,
        Player.class
    };

    /**
     * Constructor for objects of class Block
     */
    public Block(int xSize, int ySize)
    {
        super();
        this.xSize = xSize;
        this.ySize = ySize;
        GreenfootImage img = this.getImage();
        img.scale(xSize, ySize);
        this.setImage(img);
        this.minDistance = 44;
    }

    /**
     * Act - This is called in subclasses so nothing goes in it here
     */
    public void act()
    {
    }

    /**
     * Get the x size
     */
    public int getXSize()
    {
        return this.xSize;
    }

    /**
     * Set the x size
     */
    public void setXSize(int xSize)
    {
        this.xSize = xSize;
    }

    /**
     * Get the y size
     */
    public int getYSize()
    {
        return this.ySize;
    }

    /**
     * Set the y size
     */
    public void setYSize(int ySize)
    {
        this.ySize = ySize;
    }

    /**
     * Get the minimum distance
     */
    public int getMinDistance()
    {
        return this.minDistance;
    }

    /**
     * Set the minimum distance
     */
    public void setMinDistance(int minDistance)
    {
        this.minDistance = minDistance;
    }

    /**
     * Get the currently moving block
     */
    public static Block getCurrentlyMoving()
    {
        return currentlyMoving;
    }

    /**
     * Set the currently moving block
     */
    public static void setCurrentlyMoving(Block block)
    {
        currentlyMoving = block;
    }

    /**
     * Set the image but scaled to the size
     */
    public void setImageScaled(GreenfootImage img)
    {
        img.scale(this.xSize, this.ySize);
        this.setImage(img);
    }

    /**
     * Check if the block is contacting other classes in the array for objects to check
     */
    public Actor isContactingOthers()
    {
        for (int i=0; i<actorsToCheck.length; i++)
        {
            if(this.isTouching(actorsToCheck[i]))
                return getOneIntersectingObject(actorsToCheck[i]);
        }
        return null;
    }

     /**
     * Check if the block is at the edge of the world
     */
    public boolean checkAtEdge()
    {
         // Get world size
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        // get location
        int x = getX();
        int y = getY();

        // Check if the actor is outside the world
        return (x <= 0 || x >= worldWidth-1 || y <=0 || y >= worldHeight-1);
    }

}

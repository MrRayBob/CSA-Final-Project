import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MaizeWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaizeWall extends Actor
{
    private int xSize;
    private int ySize;
    static private String[] imageOptions = {"Crate option 1.png", "Crate option 2.png"};
   
    /**
     * Constructor
     */
    public MaizeWall()
    {
        super();
        this.xSize = 40;
        this.ySize = 40;

        // set a random crate image
        int randomImage = (int) (Math.random() * imageOptions.length);
        GreenfootImage img = new GreenfootImage(imageOptions[randomImage]);
        img.scale(getXSize(), getYSize());
        this.setImage(img);
    }

     /**
     * Act 
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
    public void setXSize(int newXSize)
    {
        this.xSize = newXSize;
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
    public void setYSize(int newYSize)
    {
        this.ySize = newYSize;
    }
}

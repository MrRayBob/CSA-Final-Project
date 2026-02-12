import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class endWorldPortal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class endWorldPortal extends Portal
{

    private boolean displayOn;
    /**
     * Default constructor
     */
    public endWorldPortal(int rotation)
    {
        super();
        displayOn = false;

        GreenfootImage img = new GreenfootImage("Factory Door.png");
        img.scale(50,50);
        this.setImage(img);
        this.setRotation(rotation);
    }

    /**
     * Acting
     */
    public void act()
    {
        this.warp();
        
    }

    /**
     * Warp function checks if someone is on/in portal and warps them
     */
    public void warp()
    {   
        if (this.isTouching(Player.class))
        {
            if (GameData.numKeys >= GameData.totalKeys)
            {
                try {
                    World newWorld = new GameOver(true);
                    Greenfoot.setWorld(newWorld);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                getWorld().showText("You must get all the keys to open this door! \n Try going to different worlds to get them!", 640, 250);
                
            }
        }
        else
        {
            getWorld().showText("", 640, 250);
        }
    }
}

 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class Portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal extends Actor
{
    private boolean active;
    // private WorldBase goToWorld;
    private Class<? extends WorldBase> goToWorld;

    /**
     * Default constructor
     */
    public Portal()
    {
        super();
        this.active = false;
        this.goToWorld = null;
        this.setImage(new GreenfootImage("Factory Door.png"));
    }

    /**
     * Main constructor
     */
    public Portal(boolean isActive, Class<? extends WorldBase> inGoToWorld, int rotation)
    {
        super();
        this.active = isActive;
        this.goToWorld = inGoToWorld;
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
        if (this.active != true)
            return;
        
        if (this.isTouching(Player.class) && goToWorld != null)
        {
            try {
                World newWorld = goToWorld.getDeclaredConstructor().newInstance();
                Greenfoot.setWorld(newWorld);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

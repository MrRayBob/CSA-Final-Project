import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingBlock extends Block
{
    private int speed;
    private int penalty;

    /**
     * Constructor for Moving Block
     */
    public MovingBlock(int x, int y)
    {
        super(x,y);

        // random speed from 2 to 4.75
        speed = (int) (Math.random() * 2.76) + 2;

        // Random time penelty from 1 second to 24 seconds
        penalty = (int) (Math.random() * 24) + 1;

        int randomNum = (int) (Math.random() * 2) + 1;
        setImageScaled(new GreenfootImage("Crate option " + randomNum + ".png"));
    }

    /**
     * Act - do whatever the MovingBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX(), getY() + speed);

        if (this.checkAtEdge())
            speed = -speed;

        if (super.isContactingOthers() instanceof Player)
        {
            setLocation(getX(), getY() - speed);
            speed = -speed;
            GameData.currentTimeSec += penalty; // penilize player
        }
        else if (super.isContactingOthers() != null)
        {
            setLocation(getX(), getY() - speed);
            speed = -speed;
        }
    }
}

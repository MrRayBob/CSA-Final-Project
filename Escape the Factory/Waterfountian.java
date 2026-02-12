import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Waterfountian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waterfountian extends Actor
{
    public GreenfootImage[] frames = new GreenfootImage[16];
    
    // sizes
    int x;
    int y;
    
    // for animation
    int currentSlide;
    int cycleTimingCount;
    
    public Waterfountian(int x, int y)
    {
        super();
        
        this.x = x;
        this.y = y;
        this.currentSlide = 0;
        this.cycleTimingCount = 0;
        
        for (int i=0; i<frames.length; i++)
        {
            String index;
            if (i<10)
            {
                index = "0" + i;
            }
            else 
                index = "" + i;
            GreenfootImage img = new GreenfootImage("/Waterfountian/frame_" + index + "_delay-0.07s.png");
            img.scale(x,y);
            frames[i] = img;
        }
    }
    /**
     * Act - do whatever the Waterfountian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (cycleTimingCount < 4)
        {
            cycleTimingCount++;
            return;
        }
        else
        {
            cycleTimingCount = 0;
            currentSlide++;
            if (currentSlide >= frames.length)
            {
                currentSlide = 0;
            }
            this.setImage(frames[currentSlide]);
        }
    }
}

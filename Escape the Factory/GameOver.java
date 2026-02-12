import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends WorldBase
{

    /**
     * Dfault constructor opens win world
     */
    public GameOver()
    {
    
        super(1280,800);

        Label game = new Label("You Win!", 80, Color.GREEN, new Color(0,0,0,0));
        this.addObject(game, 640, 300);
        this.showText("You were able to escape the Factory in time! :)", 640, 450);

    }

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver(boolean win)
    {
        super(1280,800);
        
        if (!win)
        {
            Label game = new Label("Game Over", 80, Color.RED, new Color(0,0,0,0));
            this.addObject(game, 640, 300);
            this.showText("You weren't able to escape the Factory in time :(", 640, 450);
        }
        else
        {
            Label game = new Label("You Win!", 80, Color.GREEN, new Color(0,0,0,0));
            this.addObject(game, 640, 300);
            this.showText("You were able to escape the Factory in time! :)", 640, 450);
        }
    }
}

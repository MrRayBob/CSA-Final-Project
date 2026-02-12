 
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TItleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends WorldBase
{

    /**
     * Constructor for objects of class TItleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(1280, 800); 
        makeActors();
        super.setBackgroundScaled("Generic Backround.png", 1280, 800);
        GameData.resetVariables();
    }

    /**
     * Make buttons and labels
     */
    public void makeActors()
    {
        // Begin game button
        Button begin = new Button("Begin Game", 34, Color.WHITE, Color.BLUE, MainWorld.class);
        this.addObject(begin,640,400);

        // Title
        Label title = new Label("Excape the Factory", 100, Color.BLACK, new Color(0,0,0,0));
        this.addObject(title,640,200);

        // Intstructions
        Button instruct = new Button("Instructions", 34, Color.WHITE, Color.BLUE, Instructions.class);
        this.addObject(instruct,640,500);
    }

}

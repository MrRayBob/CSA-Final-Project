import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Label
{
    private Class<? extends WorldBase> goToWorld;
    private Runnable action;

    /**
     * Constructor that adds the ability to go to a world
     */
    public Button(String text, Class<? extends WorldBase> w)
    {
        super(text);
        this.goToWorld = w;
        action = null;
    }

    /**
     * More complex button constructor
     */
    public Button(String text, int fontSize, Color textColor, Color backgroundColor, Class<? extends WorldBase> w)
    {
        super(text, fontSize, textColor, backgroundColor);
        this.goToWorld = w;
        action = null;
    }

    /**
     * button constructor than allows you to pass a function to run when pressed
     */
    public Button(String text, int fontSize, Color textColor, Color backgroundColor, Class<? extends WorldBase> w, Runnable path)
    {
        super(text, fontSize, textColor, backgroundColor);
        this.goToWorld = w;
        action = path;
    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null)
            return;
        GreenfootImage image = super.getImage();

        int xSize = image.getWidth();
        int ySize = image.getHeight();

        int x = super.getX();
        int y = super.getY();

        int mouseX = mouse.getX();
        int mouseY = mouse.getY();

        if ((mouseX >= x - (xSize / 2) && mouseX <= x + (xSize / 2)) && (mouseY >= y - (ySize / 2) && mouseY <= y + (ySize / 2)))
        {
            int button = mouse.getButton();

            if (button == 1 || button == 2)
                switchWorlds();
            
            if (action != null) 
                action.run();
        }
    }

    /**
     * Switch to the new world when the button is pressed
     */
    public void switchWorlds()
    {
        try 
        {
            World newWorld = goToWorld.getDeclaredConstructor().newInstance();
            Greenfoot.setWorld(newWorld);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    private String text;
    private String mode;
    private int fontSize;
    private Color textColor;
    private Color backgroundColor;
    private GreenfootImage image;

    /**
     * Default label constructor
     */
    public Label(String text)
    {
        this(text, 28, Color.WHITE, Color.BLUE);
        this.mode = "";
    }

    /**
     * Constructor for mode variable
     */
    public Label(String text, String mode)
    {
        this(text, 24, Color.WHITE, new Color(0,0,0,0));
        this.mode = mode;
        switch(mode)
        {
            case "keys":
                backgroundColor = new Color(0,0,0);
                break;
            case "timer":
                backgroundColor = new Color(0,0,0);
                break;
        }
    }

    /**
     * Real constructor that makes with arguments
     */
    public Label(String text, int fontSize, Color textColor, Color backgroundColor)
    {
        this.text = text;
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.mode = "";
    }

    /**
     * Set the text
     */
    public void setText(String str)
    {
        this.text = str;
    }  

    /**
     * Update the image being shown to match the variables
     */
    public void updateImage()
    {
        switch(this.mode)
        {
            case "keys":
                image = new GreenfootImage(text + GameData.numKeys + "/" + GameData.totalKeys, fontSize, textColor,backgroundColor);
                break;
            case "timer":
                image = new GreenfootImage(String.format("%s%.2f", text, (GameData.totalTimeSec - GameData.currentTimeSec)), fontSize, textColor, backgroundColor);
                break;
            default:
                image = new GreenfootImage(text, fontSize, textColor, backgroundColor);
        }
        setImage(image);
    }

    /**
     * Act - do whatever the Label wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateImage();
        if (this.mode.equals("timer"))
            GameData.currentTimeSec+= (1 / (double) 60);
    }

    /**
     * Get imagge
     */
    public GreenfootImage getImage()
    {
        return this.image;
    }
}

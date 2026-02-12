import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TressureChest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TressureChest extends Actor
{
    private int value;
    
    // images
    private String closedImage;
    private String openedImage;

    // size
    private int xSize;
    private int ySize;

    // distance for opening
    private int minDistance;
    
    // Sound effect
    GreenfootSound opening = new GreenfootSound("Chest Open.mp3");

    /** 
     * Custom constructor
     */
    public TressureChest(int min)
    {
        super();
        this.value = 1;

        // images
        this.closedImage = "Tressure Chest Closed.png";
        this.openedImage = "Tressure Chest Open.png";

        // size
        this.xSize = 40;
        this.ySize = 40;

        // distance
        this.minDistance = min;
    }

    /** 
     * Custom constructor With size
     */
    public TressureChest(int min, int x, int y)
    {
        super();
        this.value = 1;

        // images
        this.closedImage = "Tressure Chest Closed.png";
        this.openedImage = "Tressure Chest Open.png";

        // size
        this.xSize = x;
        this.ySize = y;

        // distance
        this.minDistance = min;
    }

    /**
     * Act - do whatever the TressureChest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (this.getKeysLeft() == 0)
        {
            this.setImage(openedImage);

            GreenfootImage img = this.getImage();
            img.scale(this.xSize, this.ySize);
            this.setImage(img);
        }
        else
        {
            this.setImage(closedImage);

            GreenfootImage img = this.getImage();
            img.scale(this.xSize, this.ySize);
            this.setImage(img);
        }
        interact();
    }

    /**
     * get wether or not opened
     */
    public boolean getOpened()
    {
        return (getKeysLeft() == 0);
    }

    /**
     * Get keys left
     */
    public int getKeysLeft()
    {
        WorldBase currentWorld = (WorldBase) getWorld();

        if (currentWorld instanceof MaizeWorld)
        {
            return GameData.keysInChestMaize;
        }
        else if (currentWorld instanceof BlockMover)
        {
            return GameData.keysInChestBlock;
        }
        else if (currentWorld instanceof MovingObjects)
        {
            return GameData.keysInChestMovingOb;
        }
        return 0;
    }

    /**
     * set Key
     */
    public void setKey(int n)
    {
       WorldBase currentWorld = (WorldBase) getWorld();

        if (currentWorld instanceof MaizeWorld)
        {
            GameData.keysInChestMaize = n;
        }
        else if (currentWorld instanceof BlockMover)
        {
            GameData.keysInChestBlock = n;
        }
        else if (currentWorld instanceof MovingObjects)
        {
            GameData.keysInChestMovingOb = n;
        }
    }

    /**
     * Interacting
     */

    // ***
    // TODO: USE THIS getObjectsInRange(int r)
    public void interact()
    {
        // Get the player object in the world
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        
        // distance to the player
        double distance = Math.sqrt(
            Math.pow(this.getX() - player.getX(), 2) + 
            Math.pow(this.getY() - player.getY(), 2)
        );
                
        if (Greenfoot.isKeyDown("i") && distance < minDistance)
        {
            if (this.getKeysLeft() > 0)
            {
                opening.play();
            }
            GameData.numKeys += this.getKeysLeft();
            this.setKey(0);
        }
    }
    

    /** 
     * Get the y size
     */
    public int getYsize()
    {
        return this.ySize;
    }
}

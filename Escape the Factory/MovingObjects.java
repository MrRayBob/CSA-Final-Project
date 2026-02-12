import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingObjects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingObjects extends WorldBase
{

    /**
     * Constructor for objects of class MovingObjects.
     * 
     */
    public MovingObjects()
    {   
        super(1280,800);
        this.setBackgroundScaled("Generic Backround.png", this.getXSize(), this.getYSize());
        super.putKeyOnTop();
        createActors();
    }
    
    /**
     * Create actors
     */
    public void createActors()
    {
        // create moving blocks these can be changed to be any size
        int size = 80;

        // calculate how many blocks can fit in the world
        int worldSizeUseable = 1260;
        int amount = worldSizeUseable / size;
        int spaceBetween = (worldSizeUseable - (amount * size)) / amount;

        // Create the player
        Player player = new Player();
        addObject(player, 10, 400);

        // create tressure chest
        TressureChest chest = new TressureChest(50);
        addObject(chest, 640, chest.getYsize()/2);

        // Exit door
        Portal mainWorld = new Portal(true, MainWorld.class, 90);
        addObject(mainWorld, 1270, 400);

        // create the blocks and place them in the world
        for (int i=1; i<amount; i++)
        {
            int randomY = (int) (Math.random() * 800 - (chest.getYsize() - size - 1)) + chest.getYsize() + size;

            MovingBlock block = new MovingBlock(size, size);
            addObject(block, (size/2) + (i*(size + spaceBetween)), randomY);
        }
    }
}

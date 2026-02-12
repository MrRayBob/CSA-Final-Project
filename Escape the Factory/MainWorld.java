import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MainWorld extends WorldBase
{
    /**
     * Default constuctor
     */
    public MainWorld()
    {
        super(1280,800);
        this.setBackgroundScaled("Generic Backround.png", this.getXSize(), this.getYSize()); // Replace "background.jpg" with your image filename
        this.createActors();
        super.putKeyOnTop();
    }

    /**
     * Construct actors
     */
    public void createActors()
    {
        Player person = new Player();
        
        Portal maize = new Portal(true, MaizeWorld.class, 0);
        Portal blockWorld = new Portal(true, BlockMover.class, 0);
        Portal movingWorld = new Portal(true, MovingObjects.class, 0);

        Portal endGame = new endWorldPortal(0);
        
        Waterfountian fountian = new Waterfountian(125,125);

        this.addObject(person, 300,150);
        
        this.addObject(fountian, 640,400);

        // Worlds
        this.addObject(maize, 320,400);
        this.showText("Maze Room", 320, 440);
        
        this.addObject(blockWorld, 960,400);
        this.showText("Block Mover Room", 960, 440);
        
        this.addObject(movingWorld, 640,700);
        this.showText("Doging Blocks Room", 640, 740);
        
        this.addObject(endGame, 640,100);
        this.showText("Exit", 640, 140);
    }
}


 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    // speed it moves at
    private int speed;

    // current speeds
    private int curSpeedX;
    private int curSpeedY;

    // last position
    private int lastX;
    private int lastY;

    // size of image
    private int xSize;
    private int ySize;
    
    // sound
    GreenfootSound moving = new GreenfootSound("Walking Short.mp3");

    // used to know if your currently moving and which keys
    boolean isMoving;
    private boolean[] validCommandsCurrent = new boolean[4];
    // W A S D

    @SuppressWarnings("unchecked")

    // Array with stuff you can't go through
    private Class<? extends Actor>[] actorsToCheck = (Class<? extends Actor>[]) new Class[] 
    { 
        MaizeWall.class,
        Block.class,
        TressureChest.class,
        Waterfountian.class
    };

    /**
     * Custom constructor
     */
    public Player()
    {
        super();
        // Set size
        GreenfootImage img = new GreenfootImage("/PlayerMovements/Down.png");
        img.scale(50, 50);
        xSize = 50;
        ySize = 50;

        this.setImage(img);
        this.speed = 4;
        this.isMoving = false;
    }

    /**
     * Change size
     */
    public void setSize(int s)
    {
        // Set size
        xSize = s;
        ySize = s;
        GreenfootImage img = this.getImage();
        img.scale(s, s);
        this.setImage(img);
    }

    /**
     * get size
     */
    public int getSize()
    {
        return this.xSize;
    }

    /**
     * Change speed
     */
    public void setSpeed(int s)
    {
        this.speed = s;
    }

    /**
     * get speed
     */
    public int getSpeed()
    {
        return this.speed;
    }

    /**
     * Get the speed currently moving at in x axis
     */
    public int getCurrentXSpeed()
    {
        return this.curSpeedX;
    }

    /**
     * Get the speed currently moving at in y axis
     */
    public int getCurrentYSpeed()
    {
        return this.curSpeedY;
    }

    /**
     * Get the last position
     */
    public int getLastX()
    {
        return this.lastX;
    }

    /**
     * Get the last position
     */
    public int getLastY()
    {
        return this.lastY;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the envwironment.
     */
    public void act()
    {
        // Add your action code here.
        moveAround();
        changeImage();
        playSound();

        if (GameData.currentTimeSec >= GameData.totalTimeSec)
        {
            WorldBase gameOver = new GameOver(false);
            Greenfoot.setWorld(gameOver);
        }
    }

    /**
     * Move around
     */
    public void moveAround()
    {
        int newX = getX();
        int newY = getY();
        
        // Calculate new position based on key presses
        if (Greenfoot.isKeyDown("w")) {
            newY -= speed;
        }
        if (Greenfoot.isKeyDown("s")) {
            newY += speed;
        }
        if (Greenfoot.isKeyDown("a")) {
            newX -= speed;
        }
        if (Greenfoot.isKeyDown("d")) {
            newX += speed;
        }

        // Store original position
        int originalX = getX();
        int originalY = getY();
        
        isMoving = checkMoving(originalX, originalY);

        setLocation(newX, newY);
        if (isContacting() == null && !checkAtEdge())
        {
            this.curSpeedX = originalX - newX;
            this.curSpeedY = originalY - newY;

            isMoving = checkMoving(originalX, originalY);

            // all potential movements valid
            setValidAray('W', Greenfoot.isKeyDown("w"));
            setValidAray('A', Greenfoot.isKeyDown("a"));
            setValidAray('S', Greenfoot.isKeyDown("s"));
            setValidAray('D', Greenfoot.isKeyDown("d"));

            this.lastX = originalX;
            this.lastY = originalY;

            return;
        }

        setLocation(originalX, newY);
        if (isContacting() == null && !checkAtEdge())
        {
            this.curSpeedX = 0;
            this.curSpeedY = originalY - newY;

            isMoving = checkMoving(originalX, originalY);

            // Only W and S valid
            setValidAray('W', Greenfoot.isKeyDown("w"));
            setValidAray('A', false);
            setValidAray('S', Greenfoot.isKeyDown("s"));
            setValidAray('D', false);

            this.lastX = originalX;
            this.lastY = originalY;

            return;
        }

        setLocation(newX, originalY);
        if (isContacting() == null && !checkAtEdge())
        {
            this.curSpeedX = originalX - newX;
            this.curSpeedY = 0;

            isMoving = checkMoving(originalX, originalY);

            // Only A and D valid
            setValidAray('W', false);
            setValidAray('A', Greenfoot.isKeyDown("a"));
            setValidAray('S', false);
            setValidAray('D', Greenfoot.isKeyDown("d"));

            this.lastX = originalX;
            this.lastY = originalY;

            return;
        }

        setLocation(originalX, originalY);
        if (isContacting() == null && !checkAtEdge())
        {
            this.curSpeedX = 0;
            this.curSpeedY = 0;
            isMoving = false;

            // None valid
            setValidAray('W', false);
            setValidAray('A', false);
            setValidAray('S', false);
            setValidAray('D', false);

            this.lastX = originalX;
            this.lastY = originalY;

            return;
        }

        this.lastX = originalX;
        this.lastY = originalY;
    }
    
    /**
     * Used to check if the player is moving
     */
    private boolean checkMoving(int ogX, int ogY)
    {
        int newX = getX();
        int newY = getY();

        if (ogX == newX && ogY == newY)
            return false;
        return true;
    }

    /**
     * Set things in valid array
     */
    private void setValidAray(char in, boolean val)
    {
        switch(in)
        {
            case 'W':
                validCommandsCurrent[0] = val;
            case 'A':
                validCommandsCurrent[1] = val;
            case 'S':
                validCommandsCurrent[2] = val;
            case 'D':
                validCommandsCurrent[3] = val;
        }
    }

    /**
     * Check if the person is now at the edge of the world true if at end of world false if not
     */
    public boolean checkAtEdge()
    {
         // Get world size
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        // get location
        int x = getX();
        int y = getY();

        // Check if the actor is outside the world
        return (x <= 0 || x >= worldWidth-1 || y <=0 || y >= worldHeight-1);
    }

    /**
     * Get the state of the array
     */
    public boolean[] getMoveableArray()
    {
        return this.validCommandsCurrent;
    }

    /** 
     * check if a command is vaid
     */
    public boolean checkValid(char in)
    {
        switch(in)
        {
            case 'W':
                return validCommandsCurrent[0];
            case 'A':
                return validCommandsCurrent[1];
            case 'S':
                return validCommandsCurrent[2];
            case 'D':
                return validCommandsCurrent[3];
            default:
                return false;
        }
    }

    /**
     * Check if tocuhing
     */
    public Actor isContacting()
    {
        for (int i=0; i<actorsToCheck.length; i++)
        {
            if(this.isTouching(actorsToCheck[i]))
                return getOneIntersectingObject(actorsToCheck[i]);;
        }
        return null;
    }

    /**
     * Used to interact and move things
     */
    public int getCurrentlyMovingSpeed()
    {
        if (isMoving)
            return this.speed;
        return 0;
    }

    /**
     * get the object that is blocking the player
     */
    public Actor getBlockingObject()
    {
        return this.isContacting();
    }

    /**
     * Set the image based on buttons pressed
     */
    public void changeImage()
    {
        GreenfootImage img = null;
        if(Greenfoot.isKeyDown("w"))
        {
            img = new GreenfootImage("/PlayerMovements/Up.png");
        }
        else if(Greenfoot.isKeyDown("s"))
        {
            img = new GreenfootImage("/PlayerMovements/Down.png");
        }
        else if(Greenfoot.isKeyDown("a"))
        {
            img = new GreenfootImage("/PlayerMovements/Left.png");
        }
        else if(Greenfoot.isKeyDown("d"))
        {
            img = new GreenfootImage("/PlayerMovements/Right.png");
        }

        if (img != null)
        {
            img.scale(xSize, ySize);
            this.setImage(img);
        }
    }
    public void playSound()
    {
        if (this.isMoving)
        {
            moving.play();
        }
        else
        {
            moving.stop();
        }
    }

}

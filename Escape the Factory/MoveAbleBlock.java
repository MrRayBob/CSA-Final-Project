import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoveAbleBlock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveAbleBlock extends Block
{
    
    GreenfootSound moving = new GreenfootSound("Stone Slide.mp3");
    /**
     * Overrided constructor that sets up image
     */
    public MoveAbleBlock(int x, int y)
    {
        super(x,y);
        GreenfootImage img = new GreenfootImage("Coal Create.png");
        super.setImageScaled(img);
    }
    
    /**
     * Act - do whatever the MoveAbleBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.interact();
    }

    /**
     * Interact allows the player to push the block around
     */
    public void interact()
    {
        // Get the player object in the world
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        
        // Calculate horizontal and vertical distances separately
        int horizontalDistance = Math.abs(this.getX() - player.getX());
        int verticalDistance = Math.abs(this.getY() - player.getY());
        
        // Check if player is close enough either horizontally or vertically to push the block
        boolean isCloseEnough = (horizontalDistance < super.getMinDistance() && verticalDistance < super.getYSize()) || 
                              (verticalDistance < super.getMinDistance() && horizontalDistance < super.getXSize());
        
        // If this block is already being moved
        if (super.getCurrentlyMoving() == this && isCloseEnough) {
            if (Greenfoot.isKeyDown("i")) {
                moveAround(player);  // Continue moving
            } else {
                super.setCurrentlyMoving(null);  // Release the block when 'i' is released
                moving.stop();
            }
            System.out.println(super.getCurrentlyMoving());
            return;
        }

        if (super.getCurrentlyMoving() == this && !isCloseEnough) {
            super.setCurrentlyMoving(null);
            moving.stop();
        }

        // If no block is being moved and player is close enough
        if (super.getCurrentlyMoving() == null && Greenfoot.isKeyDown("i") && isCloseEnough) {
            super.setCurrentlyMoving(this);
            moveAround(player);
        }
    }

    /**
     * Move around moves the block based on the inputs and player
     */
    public void moveAround(Player person)
    {
        // Get the player's intended movement direction based on key presses
        int intendedDeltaX = 0;
        int intendedDeltaY = 0;
        
        if (Greenfoot.isKeyDown("w")) {
            intendedDeltaY -= person.getSpeed();
        }
        if (Greenfoot.isKeyDown("s")) {
            intendedDeltaY += person.getSpeed();
        }
        if (Greenfoot.isKeyDown("a")) {
            intendedDeltaX -= person.getSpeed();
        }
        if (Greenfoot.isKeyDown("d")) {
            intendedDeltaX += person.getSpeed();
        }
        
        // If player isn't trying to move, don't move the block
        if (intendedDeltaX == 0 && intendedDeltaY == 0) {
            return;
        }
        
        int newX = getX() + intendedDeltaX;
        int newY = getY() + intendedDeltaY;
        
        // Store original position
        int originalX = getX();
        int originalY = getY();
        
        // Try to move to new position
        setLocation(newX, newY);
        
        // If we hit something or edge, move back
        if ((isContactingOthers() != null || checkAtEdge()) && !person.checkAtEdge()) {
            setLocation(originalX, originalY);
        }
        moving.play();
        System.out.println(person.getBlockingObject());
    }
}

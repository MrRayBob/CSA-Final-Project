import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlockMover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockMover extends WorldBase
{

    private int blockXSize;
    private int blockYSize;

    /**
     * Constructor for objects of class BlockMover.
     * 
     */
    public BlockMover()
    {
        super.setBackgroundScaled("Generic Backround.png",1280,800);
        this.blockXSize = 100;
        this.blockYSize = 60;
        createActors();

        if (GameData.layout == null)
        {
            GameData.layout = new Block[12][12];
            createBlocks();
        }
        placeBlocks();
        System.out.println("Value at (4,0): " + GameData.layout[4][0]);

        super.putKeyOnTop();
    }

    /**
     * Create actors
     */
    public void createActors()
    {
        Player person = new Player();
        Portal portalMain = new Portal(true, MainWorld.class, 90);
        TressureChest chest = new TressureChest(58,60,60);
        chest.setRotation(0);
        
        this.addObject(person, 5,300);
        this.addObject(portalMain, 1250,400);
        this.addObject(chest,580, 35);
    }

    /**
     * Make all the blocks
     */
    private void createBlocks()
    {
        for (int row=0; row<GameData.layout.length; row++)
        {
            for (int col=0; col<GameData.layout[row].length; col++)
            {
                int random = (int) (Math.random() * 5); // random num 0 to 2

                if (row == 4 && col == 0)
                    GameData.layout[row][col] = null;
                else 
                {
                    switch(random)
                    {
                        case 0:
                        case 1:
                        case 2:
                            GameData.layout[row][col] = null;
                            break;
                        case 3:
                        case 4:
                            GameData.layout[row][col] = new MoveAbleBlock(blockXSize, blockYSize);
                            break;
                    }
                }
            }
        }

        GameData.layout[0][5] = null;
        GameData.layout[5][11] = null;
        GameData.layout[6][11] = null;
    }

    /**
     * Put all the blocks
     */
    private void placeBlocks()
    {
        for (int row=0; row<GameData.layout.length; row++)
        {
            for (int col=0; col<GameData.layout[row].length; col++)
            {
                if (GameData.layout[row][col] != null)
                    this.addObject(GameData.layout[row][col], (col*blockXSize + 5 + (blockXSize/2)) + (col * 5), (row*blockYSize + 5 + (blockYSize/2)) + (row * 5));
                System.out.print(GameData.layout[row][col]);
            }
            System.out.println();
        }
    }
}

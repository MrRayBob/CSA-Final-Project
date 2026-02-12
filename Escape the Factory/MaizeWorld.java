import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MaizWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaizeWorld extends WorldBase
{
    private static int pathChoice;

    private static int chestX;
    private static int chestY;

    private static int portalY;
    private static int portalX;
    private static int portalRotation;


    /**
     * Constructor for objects of class MaizWorld.
     * 
     */
    public MaizeWorld()
    {
        super();
        super.setBackgroundScaled("Generic Backround.png",1280,800);

        if (!GameData.maizeSetUp)
        {
            this.setupMaize();
            GameData.maizeSetUp = true;
        }
        
        preparePath();
        this.createActors();
        super.putKeyOnTop();
    }

    /**
     * Create actors
     */
    public void createActors()
    {
        Player person = new Player();
        double ratio = (double) 15.0 / person.getSize();
        int oldSpeed = person.getSpeed();

        person.setSize(15);
        person.setSpeed((int) (oldSpeed * ratio * 2));
        
        this.addObject(person, 5,400);
    }

    /**
     * Prepare and render maize path
     */
    private void preparePath()
    {
        System.out.println(pathChoice);
        switch(pathChoice)
        {
            case 0:
                for (int i=0; i<GameData.maizePath1.length; i++) // y
                {
                    for (int j=0; j<GameData.maizePath1[i].length; j++) // x
                    {
                        MaizeWall wall = new MaizeWall();
                        if (GameData.maizePath1[i][j] == 1)
                        {
                            this.addObject(wall, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                        if (i == chestY && j == chestX)
                        {
                            TressureChest chest = new TressureChest(34);
                            chest.setRotation(0);
                            // this is still on the size of wall to keep it consistent
                            this.addObject(chest, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                        if (i == portalY && j == portalX)
                        {
                            Portal portalMaize = new Portal(true, MainWorld.class, portalRotation);
                            // this is still on the size of wall to keep it consistent
                            this.addObject(portalMaize, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                    }
                }
                break;
            case 1:
                for (int i=0; i<GameData.maizePath2.length; i++)
                {
                    for (int j=0; j<GameData.maizePath2[i].length; j++)
                    {
                        MaizeWall wall = new MaizeWall();
                        if (GameData.maizePath2[i][j] == 1)
                        {
                            this.addObject(wall, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                        if (i == chestY && j == chestX)
                        {
                            TressureChest chest = new TressureChest(34);
                            chest.setRotation(0);
                            // this is still on the size of wall to keep it consistent
                            this.addObject(chest, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                        if (i == portalY && j == portalX)
                        {
                            Portal portalMaize = new Portal(true, MainWorld.class, portalRotation);
                            // this is still on the size of wall to keep it consistent
                            this.addObject(portalMaize, (wall.getXSize() * j) + wall.getXSize()/2, (wall.getYSize() * i) + wall.getYSize()/2);
                        }
                    }
                }
                break;
        }
    }

    /**
     * chose map
     */
    private void setupMaize()
    {
        int randomMapNum = (int) (Math.random() * 2); // 0-1
        int randomChestNum = (int) (Math.random() * GameData.viableChestLocations[0].length);
        int randomPortalNum = (int) (Math.random() * GameData.viablePortalLocations[0].length);

        this.pathChoice = randomMapNum;

        this.chestX = GameData.viableChestLocations[0][randomChestNum];
        this.chestY = GameData.viableChestLocations[1][randomChestNum];

        this.portalX = GameData.viablePortalLocations[0][randomPortalNum];
        this.portalY = GameData.viablePortalLocations[1][randomPortalNum];
        this.portalRotation = GameData.viablePortalLocations[2][randomPortalNum];
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends WorldBase
{

    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {
        makeActors();
        super.setBackgroundScaled("Generic Backround.png", 1280, 800);
    }

    /**
     * Make buttons and labels
     */
    public void makeActors()
    {

        // Title
        Label title = new Label("Excape the Factory", 100, Color.BLACK, new Color(0,0,0,0));
        this.addObject(title,640,125);

        // Instructions
        String gameDetails = "Game Details: \n" +
        "Welcome to Excape the Factory! Your mission is to escape the factory \n" + 
        "in " + GameData.totalTimeSec + " seconds or less, if you don't exacpe in this time the Factory will \n" +
        "be destroyed with you inside! To achive this goal there is a locked door to the outside that \n" +
        "Requires " + GameData.totalKeys + " keys to open, you must venture to different areas of the Factory \n" + 
        "to get all the keys. The keys will be contained in Chest in each level of the Factory \n" +
        "There will be two counters in the top left hand corner, One will be for the amount of keys you've found\n" +
        "so far and one for the amount of time remaining in seconds.";

        String moveMentControl = "Movement and Controls: \n" +
        "To move around you can use W, A, S, and D. For moving between the different \n" +
        "Rooms in the Factory all you need to do is walk up through the doors in the main level to go between worlds. \n" +
        "To interact with objects such as chests which contain the keys or blocks that you are able to move, simply \n" +
        "press down 'i' \n";

        String roomSpesific = "Room Spesific: \n" +
        "1. In the doging blocks room if you hit a block a random amount of time comes off your remaining time\n" +
        "2. In the moving blocks room grab blocks with 'i' and move them to get to the chest and door \n" +
        "3. The Maze map switches between two maps and chest/door locations are different \n";

        this.showText(gameDetails, 640, 280);
        this.showText(moveMentControl, 640, 460);
        this.showText(roomSpesific, 640, 595);

        // Back to menue button
        Button instruct = new Button("Return to main menu", 34, Color.WHITE, Color.BLUE, TitleScreen.class);
        this.addObject(instruct,640,700);
    }
}

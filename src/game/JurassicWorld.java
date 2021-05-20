package game;

import edu.monash.fit2099.engine.*;
import game.grounds.Lake;

import java.util.Random;

/**
 * @author jinyeopoh
 * @version 1.0.0
 */
public class JurassicWorld extends World {
    private boolean isChallengeMode = false;

    /**
     * turn counter for raining
     */
    private int turns = 0;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public JurassicWorld(Display display) {
        super(display);
    }

    /**
     * Run the game.
     *
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     *
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     *
     * It also prompts user for the game mode, quitting option and raining for the world
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        selectGameMode(display);

        // If user select a challenge game
        if( isChallengeMode)
            ((Player)player).setTurnsAndPoints();


        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);

            // Prompts for quitting the game
            if(quitGame(display)){
                break;
            }

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            int numOfSips = sips();
            Util.setRaining(false);
            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                if(++turns > 10 && isRaining()){
                    Util.setRaining(true); // This is true for one turn only
                    addWaterToLake(numOfSips, gameMap);
                    turns = 0;
                }

                gameMap.tick();
            }

            // Check if player satisfies winning or losing condition
            if( isChallengeMode){
                if( ((Player)player).isWin() ){
                    display.println("WIN!!!!!!!!");
                    break;
                }

                if( ((Player)player).isLose() ){
                    display.println("LOST!!!!!!!!");
                    break;
                }

            }

        }
        display.println(endGameMessage());
    }

    /**
     * Allows user to select game mode
     * @param display Display object for getting char input
     */
    private void selectGameMode(Display display){
        boolean again = true;
        while( again ){
            System.out.println();
            System.out.println("Select game mode");
            System.out.println("Challenge game - 1");
            System.out.println("Sandbox game - 2");
            System.out.print("Input : ");
            char userInput = display.readChar();
            System.out.println();

            switch (userInput){
                case '1':
                    isChallengeMode = true;
                    again = false;
                    break;
                case '2':
                    isChallengeMode = false;
                    again = false;
                    break;
            }
        }


    }

    /**
     * Asks if user wants to quit the game at any time
     * @param display Display object to get char input from the user
     * @return return false if user wants to quit, otherwise true
     */
    private boolean quitGame(Display display){
        System.out.println();
        System.out.println("Would you quit the game now?");
        System.out.println("Quit now - q");
        System.out.println("continue game - press any");
        System.out.print("Input : ");
        char input = display.readChar();
        System.out.println();

        return input == 'q';
    }

    /**
     * Calculate the chance of raining
     * @return true if raining or false
     */
    private boolean isRaining(){
        return Util.calcPercentage(Util.TWENTY_PERCENT_CHANCE);
    }

    /**
     * Calculates the rain fall and number of sips to increment
     * @return number of sips
     */
    private int sips(){
        double rainFall = ((double)new Random().nextInt(6)+1)/10;
        return ((int)(rainFall*20));
    }

    /**
     * Finds lake object in the map and add given amount of water to it
     * @param sips number of water to add
     * @param gameMap the gameMap in which contains lakes in it
     */
    private void addWaterToLake(int sips, GameMap gameMap){
        // Find all lakes in the map
        for(int y: gameMap.getYRange()){
            for(int x: gameMap.getXRange()){
                if( gameMap.at(x, y).getGround() instanceof Lake) {
                    ((Lake) gameMap.at(x, y).getGround()).incrementSips(sips);
                }
            }
        }
    }
}

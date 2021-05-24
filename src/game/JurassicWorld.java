package game;

import edu.monash.fit2099.engine.*;
import game.actions.QuitAction;
import game.dinosaurs.Dinosaur;
import game.grounds.Lake;

import java.util.Random;

/**
 * @author jinyeopoh
 * @version 1.0.0
 */
public class JurassicWorld extends World {
    private boolean isChallengeMode = false;
    private boolean endGame = false;

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
        if(endGame){
            display.println(endGameMessage());
            return;
        }

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

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning()){
                    processActorTurn(actor);
                    if(endGame)
                        break;
                }
            }
            // Check for quitting the game after player's turn
            if(endGame){
                break;
            }


            int numOfSips = sips();
            Util.setRaining(false);
            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                if(++turns > 10 && isGoingToRain()){
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
     * Gives an Actor its turn.
     * And if actor is player, display quit menu
     * The Actions an Actor can take include:
     * <ul>
     * <li>those conferred by items it is carrying</li>
     * <li>movement actions for the current location and terrain</li>
     * <li>actions that can be done to Actors in adjacent squares</li>
     * <li>actions that can be done using items in the current location</li>
     * <li>skipping a turn</li>
     * </ul>
     *
     * @param actor the Actor whose turn it is.
     */
    @Override
    protected void processActorTurn(Actor actor) {
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();

        Actions actions = new Actions();

        // If actor is player, display quit menu
        if( actor instanceof Player){
            actions.add(new QuitAction());
        }

        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
            // Game rule. If you're carrying it, you can drop it.
            actions.add(item.getDropAction());
        }

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Game rule. You don't get to interact with the ground if someone is standing
            // on it.
            if (actorLocations.isAnActorAt(destination)) {
                actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
            } else {
                actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
            }
            actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
            // Game rule. If it's on the ground you can pick it up.
            actions.add(item.getPickUpAction());
        }
        actions.add(new DoNothingAction());

        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
        lastActionMap.put(actor, action);

        // If player selects to quit the game
        if( actor instanceof Player && action instanceof QuitAction){
            endGame = true;
            return;
        }

        String result = action.execute(actor, map);
        display.println(result);
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
            System.out.println("Quit game - 3");
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
                case '3':
                    endGame = true;
                    again = false;
                    break;
            }
        }


    }

    /**
     * Calculate the chance of raining
     * @return true if raining or false
     */
    private boolean isGoingToRain(){
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
                Actor actorHere = gameMap.at(x, y).getActor();
                if (actorHere != null && actorHere.hasCapability(Type.UNCONSCIOUS)){
                    ((Dinosaur)actorHere).increaseWater(10);
                    actorHere.removeCapability(Type.UNCONSCIOUS);

                }
                if( gameMap.at(x, y).getGround() instanceof Lake) {
                    ((Lake) gameMap.at(x, y).getGround()).incrementSips(sips);
                }
            }
        }
    }
}

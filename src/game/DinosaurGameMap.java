package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;
import game.grounds.Dirt;

import java.util.List;

/**
 * @author Jinyeop Oh
 * @version 2.0.0
 */
public class DinosaurGameMap extends GameMap {
    /**
     * The GameMap to switch
     */
    private GameMap otherGameMap;

    /**
     * Represents if this map is the north map
     */
    private boolean isNorthMap = false;

    /**
     * Represents if this map is the south map
     */
    private boolean isSouthMap = false;


    public DinosaurGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * Iterate all grounds and if it is instance of Dirt, then grow bush by 0.1% chance
     */
    public void growBush(){
        // Produces a bush from each dirts before the start of game
        for (int y : heights) {
            for (int x : widths) {
                if(at(x, y).getGround() instanceof Dirt)
                    at(x, y).tick();
            }
        }
    }

    /**
     * Sets opposite map and calls expandMap
     * @param otherGameMap
     */
    public void setOtherGameMap(GameMap otherGameMap) {
        this.otherGameMap = otherGameMap;
        expandExit();
    }


    /**
     * Checks this is the north or the south first.
     * Then iterate for all the appropriate x coordinates and call expandExitFromHere with appropriate x and y
     */
    private void expandExit(){
        if( isNorthMap){
            // The y coordinate of bottom-end of the north map
            int y = getYRange().max();
            for (int x : widths) {
                Location here = this.at(x, y);
                expandExitFromHere(here, x + 1, y + 1, "South-East", "3");
                expandExitFromHere(here, x, y + 1, "South", "2");
                expandExitFromHere(here, x - 1, y + 1, "South-West", "1");
            }
        }

        if( isSouthMap){
            int y = 0;
            for (int x : widths) {
                Location here = this.at(x, y);
                expandExitFromHere(here, x, y - 1, "North", "8");
                expandExitFromHere(here, x + 1, y - 1, "North-East", "9");
                expandExitFromHere(here, x - 1, y - 1, "North-West", "7");
            }
        }

    }


    /**
     * Checks for the validity and add new Exit instance and the destination will be "otherMap"
     * @param here the current location
     * @param x X coordinate
     * @param y Y coordinate
     * @param name name of the Exit
     * @param hotKey the hotkey for the appropiate Action
     */
    private void expandExitFromHere(Location here, int x, int y, String name, String hotKey) {
        // pre : if given x is out of range
        if ( !widths.contains(x)) {
            return;
        }

        // If player moves from the south map to the north map
        if( y == -1){
            here.addExit(new Exit(name, otherGameMap.at(x, otherGameMap.getYRange().max()), hotKey));
        }

        // If player moves from the north map to the south map
        if( getYRange().max() < y){
            here.addExit(new Exit(name, otherGameMap.at(x, 0), hotKey));
        }
    }

    // setters
    public void setNorthMap(boolean northMap) {
        isNorthMap = northMap;
    }

    public void setSouthMap(boolean southMap) {
        isSouthMap = southMap;
    }
}

package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * @author Jinyeop Oh
 * @version 1.0.1
 */
public class DinosaurGameMap extends GameMap {
    public DinosaurGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    @Override
    protected Location makeNewLocation(int x, int y) {
        return new GroundLocation(this, x, y);
    }

    public void growBushRandomly(){
        for (int y : heights) {
            for (int x : widths) {
                super.at(x, y).tick();
            }
        }
    }
}

package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author jinyeopoh
 * @version 1.0.0
 */
public class QuitAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Quit the game now!";
    }

    @Override
    public String hotkey() {
        return "q";
    }
}

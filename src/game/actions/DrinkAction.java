package game.actions;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.dinosaurs.Dinosaur;
import game.grounds.Lake;

public class DrinkAction extends Action {
    private final Location waterLocation;

    public DrinkAction(Location waterLocation) {
        this.waterLocation = waterLocation;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Ground lakeGround = waterLocation.getGround();
        if(((Lake)lakeGround).getSips() > 0)
            if(actor.hasCapability(Type.STEGOSAUR) ||
                    actor.hasCapability(Type.ALLOSAUR) ||
                    actor.hasCapability(Type.PTERODACTYLS)) {
                ((Dinosaur)actor).increaseWater(30);
                ((Lake)lakeGround).decrementSips();

            }
            if(actor.hasCapability(Type.BRACHIOSAUR)) {
                ((Dinosaur)actor).increaseWater(80);
                ((Lake)lakeGround).decrementSips();
            }
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water";
    }
}

package game.Behaviours;


import edu.monash.fit2099.engine.*;
import game.Type;
import game.Util;

public class BreedBehaviour implements Behaviour {


    public BreedBehaviour() {

    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        NumberRange widths = map.getXRange();
        NumberRange heights = map.getYRange();
        int minimumDistance = 1000000;
        Actor otherActor = null;

        for (int x : widths) {
            for (int y : heights) {
                Actor thisActor = map.at(x, y).getActor();
                if (thisActor != null) {
                    if (thisActor.hasCapability(Type.STEGOSAUR) || thisActor.hasCapability(Type.BRACHIOSAUR)) {
                        if (Util.isBreedable(actor, thisActor)) {
                            Location there = map.at(x, y);
                            int currentDistance = Util.distance(here, there);
                            if (currentDistance < minimumDistance) {
                                minimumDistance = currentDistance;
                                otherActor = map.at(x, y).getActor();
                            }
                        }
                    }

                }
            }
        }
        return new FollowBehaviour(otherActor).getAction(actor, map);
    }
}

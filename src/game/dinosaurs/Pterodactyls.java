package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Type;
import game.behaviours.*;


public class Pterodactyls extends Dinosaur {
    /**
     * minimum int where dinosaur will be hungry
     */
    private int MIN_HUNGER = 30;

    private boolean getAttacked = false;
    private int turns = 0;






    /**
     * Create dinosaur with specified gender
     *
     * @param name        name of dinosaur
     * @param displayChar display character which will be shown on map
     * @param hitPoints   health of dinosaur
     * @param gender      gender of dinosaur
     */
    public Pterodactyls(String name, char displayChar, int hitPoints, Type gender) {
        super(name, 'v', 50, gender);
        this.hitPoints = 30;
        addCapability(Type.PTERODACTYLS);
        this.age = PET_ADULT_AGE;
    }

    /**
     * createa dinosaur with random gender
     *
     * @param name        name of dinosaur
     * @param displayChar display character which will be shown on map
     * @param hitPoints   health of dinosaur
     */
    public Pterodactyls(String name, char displayChar, int hitPoints) {
        super(name, 'v', hitPoints);
        this.hitPoints = 30;
        addCapability(Type.PTERODACTYLS);
        addCapability(Type.BABY);
        this.age = 0;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.isConscious()) {
            behaviour.clear();
            if (this.hitPoints >= MIN_HUNGER && !(this.hasCapability(Type.PREGNANT))) {
                behaviour.add(new BreedBehaviour());
            }

            if (isHungry(MIN_HUNGER, map)) {
                behaviour.add(new SeekFoodBehaviour());
            }
            for (Behaviour index : behaviour) {
                Action action = index.getAction(this, map);
                if (action != null) {
                    tick(this, map);
                    return action;
                }
            }
        }
        tick(this, map);
        return new DoNothingAction();
    }

    @Override
    public int getMIN_HUNGER() {
        return MIN_HUNGER;
    }
}
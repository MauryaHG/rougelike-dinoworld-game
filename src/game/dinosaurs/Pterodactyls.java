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
    private int fuel = 30;






    /**
     * creates adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Pterodactyls(String name, Type gender) {
        super(name, 'v', 50, gender);
        this.hitPoints = 60;
        addCapability(Type.PTERODACTYLS);
        addCapability(Type.CARNIVORE);
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
        this.hitPoints = 50;
        addCapability(Type.PTERODACTYLS);
        addCapability(Type.CARNIVORE);
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
            if(fuel > 0 ){
                this.addCapability(Type.CAN_FLY);
                this.removeCapability(Type.CANT_FLY);
            }else {
                this.addCapability(Type.CANT_FLY);
                this.removeCapability(Type.CAN_FLY);
            }

            if (this.hitPoints >= MIN_HUNGER && isThirsty(map) && !(this.hasCapability(Type.PREGNANT))) {
                behaviour.add(new BreedBehaviour());
            }

            if (isHungry(MIN_HUNGER, map)) {
                behaviour.add(new SeekFoodBehaviour());
            }

            if (!isThirsty(map)){
                behaviour.add(new SeekWaterBehaviour());
            }
            behaviour.add(new WanderBehaviour());
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


    public void decreaseFuel(){
        fuel --;
    }

}


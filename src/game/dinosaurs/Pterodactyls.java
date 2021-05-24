package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.Type;
import game.actions.AttackAction;
import game.actions.FeedAction;
import game.behaviours.*;
import game.grounds.Tree;


public class Pterodactyls extends Dinosaur {
    /**
     * minimum int where dinosaur will be hungry
     */
    private int MIN_HUNGER = 30;

    private boolean getAttacked = false;
    /**
     * fuel of the dinosaur
     */
    private int fuel = 30;


    /**
     * creates adult dinosaur with specified gender
     * @param name name of dinosaur
     * @param gender gender of dinosaur
     */
    public Pterodactyls(String name, Type gender) {
        super(name, 'v', 50, 100, gender);
        this.hitPoints = 30;
        addCapability(Type.PTERODACTYLS);
        addCapability(Type.CARNIVORE);
        this.age = PET_ADULT_AGE;
    }

    /**
     * create a dinosaur with random gender
     * @param name        name of dinosaur
     */
    public Pterodactyls(String name) {
        super(name, 'v', 50,100);
        this.hitPoints = 25;
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
        if (!this.hasCapability(Type.UNCONSCIOUS)) {
            behaviour.clear();
            if(map.locationOf(this).getGround().hasCapability(Type.TREE)){
                fuel = 30;
            }
            if(fuel < 0 && !map.locationOf(this).getGround().hasCapability(Type.LAKE)){
                this.addCapability(Type.CANT_FLY);
                this.removeCapability(Type.CAN_FLY);
            }else {
                this.addCapability(Type.CAN_FLY);
                this.removeCapability(Type.CANT_FLY);
            }

            if (this.hitPoints >= MIN_HUNGER && !isThirsty(map) && !(this.hasCapability(Type.PREGNANT))) {
                behaviour.add(new BreedBehaviour());
            }

            if (isHungry(MIN_HUNGER, map)) {
                behaviour.add(new SeekFoodBehaviour());
            }

            if (isThirsty(map)){
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
    /**
     *  Allow player can feed this
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be done to actor
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        if( otherActor instanceof Player ){
            return new Actions(new FeedAction(this));
        }
        if(2 otherActor instanceof Allosaur){
            return new Actions(new AttackAction(this));
        }
        return new Actions();
    }

    @Override
    public int getMIN_HUNGER() {
        return MIN_HUNGER;
    }

    /**
     * decreases the fuel by 1
     */
    public void decreaseFuel(){
        fuel --;
    }

}


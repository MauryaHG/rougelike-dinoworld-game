package game;

/**
 * @author Jinyeop Oh and Maurya Gamage
 * @version 1.1.0
 */
public enum Type {
    PLAYER,
    DIRT, BUSH, TREE, FRUIT,
    STEGOSAUR, BRACHIOSAUR, ALLOSAUR, ALIVE, DEAD, MALE, FEMALE, BREEDING, PREGNANT,
    EGG, STEGOSAUR_EGG, BRACHIOSAUR_EGG, ALLOSAUR_EGG, PTERODACTYLS_EGG, UNCONSCIOUS,
    VEGETARIAN_MEAL, CARNIVORE_MEAL, LASER_GUN,
    ON_GROUND, ON_TREE, BABY, ON_BUSH,
    CORPSE, ALLOSAUR_CORPSE, BRACHIOSAUR_CORPSE, STEGOSAUR_CORPSE,
    PTERODACTYLS,
    LAKE;
    // These are used to distinguish whether Fruit is to be picked up or harvested(ON_GROUND: picked up / ON_TREE and ON_BUSH: harvested)
}

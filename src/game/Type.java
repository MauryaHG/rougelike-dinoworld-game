package game;

/**
 * @author Jinyeop Oh and Maurya Gamage
 * @version 1.0.3
 */
public enum Type {
    DIRT, BUSH, TREE, FRUIT, STEGOSAUR, BRACHIOSAUR, ALLOSAUR, ALIVE, DEAD, MALE, FEMALE, PLAYER,
    STEGOSAUR_EGG, BRACHIOSAUR_EGG, ALLOSAUR_EGG, UNCONSCIOUS,
    ON_GROUND, ON_TREE, ON_BUSH // These are used to distinguish whether Fruit is to be picked up or harvested(ON_GROUND: picked up / ON_TREE and ON_BUSH: harvested)
}

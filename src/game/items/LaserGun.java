package game.items;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {
    public LaserGun(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }

    @Override
    public int damage() {
        return super.damage();
    }

    @Override
    public String verb() {
        return super.verb();
    }
}

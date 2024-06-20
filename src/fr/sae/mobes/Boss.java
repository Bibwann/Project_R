package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Entity;

public final class Boss extends Entity {
    
    private static Boss instance = null;

    // Private constructor to prevent instantiation outside of the class
    private Boss(String name, int level, Image sprite, Image battleSprite) {
        super(name, level, sprite, battleSprite);
        this.hpMax = 300; // Maximum hit points
        this.manaMax = 100; // Maximum mana points
        this.dmg = 50; // Damage points
        this.hpActual = this.hpMax; // Current hit points initialized to maximum
        this.manaActual = this.manaMax; // Current mana points initialized to maximum
    }

    // Method to get the singleton instance of Boss
    public static synchronized Boss getInstance() throws SlickException {
        if (instance == null) {
            // Create a new instance if it doesn't exist yet
            instance = new Boss("Nyan Cat", 99, null, new Image("data/Boss/sprite.png"));
        }
        return instance;
    }
}

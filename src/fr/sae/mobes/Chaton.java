package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Mobs;

public class Chaton extends Mobs {

    public Chaton(String name, int level) throws SlickException {
        super(name, level, new Image("data/Cats/cat.png"), new Image("data/Cats/cat.png"));

        // Set specific attributes for Chaton
        this.hpMax = 30; // Maximum hit points
        this.manaMax = 0; // Maximum mana points (assuming cats don't use mana)
        this.dmg = 20; // Damage points

        this.hpActual = 30; // Current hit points initialized to maximum
        this.manaActual = 0; // Current mana points initialized (assuming not used)
    }

    // Method to calculate damage using griffes (claws)
    public int griffesHit() {
        return (this.dmgMultipler(this.dmg) * this.dmg); // Calculate damage based on multiplier
    }
}


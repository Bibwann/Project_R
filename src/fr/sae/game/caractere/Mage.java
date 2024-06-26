package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mage extends Player {

    public Mage(String name, int level, Image sprite) throws SlickException {
        super(name, level, new Image("data/Mage/Walk.png"), new Image("data/Mage/Battler.png"), new Image("data/Mage/Face.png"));
        
        // Initialize specific attributes for the Mage class
        this.hpMax = 80;       // Maximum health points
        this.manaMax = 100;    // Maximum mana points
        this.dmg = 15;         // Base damage
        
        this.hpActual = 80;    // Current health points
        this.manaActual = 100; // Current mana points
        this.className = "Mage";  // Class name
        
        this.maxPotions = 7;
        this.potions = 7;
        this.healAmount = 20;
    }

    // Spells and abilities specific to the Mage can be defined here
}

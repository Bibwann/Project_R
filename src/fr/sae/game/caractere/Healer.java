package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Healer extends Player {

    public Healer(String name, int level, Image sprite) throws SlickException {
        super(name, level, new Image("data/Healer/Walk.png"), new Image("data/Healer/Battler.png"), new Image("data/Healer/Face.png"));
        
        // Initialize specific attributes for the Healer class
        this.hpMax = 90;       // Maximum health points
        this.dmg = 2;          // Base damage
        
        this.hpActual = 90;    // Current health points
        this.className = "Healer";  // Class name
        
        this.maxPotions = 15;
        this.potions = 15;
        this.healAmount = 110;
    }

    // Spells and abilities specific to the Healer can be defined here
}

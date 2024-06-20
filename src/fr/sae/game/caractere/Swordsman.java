package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Swordsman extends Player {

    public Swordsman(String name, int level) throws SlickException {
        super(name, level, new Image("data/Swordman/Walk.png"), new Image("data/Swordman/Battler.png"), new Image("data/Swordman/Face.png"));
        
        // Initialize specific attributes for the Swordsman class
        this.hpMax = 120;      // Maximum health points
        this.manaMax = 40;     // Maximum mana points
        this.dmg = 10;         // Base damage
        
        this.hpActual = 120;   // Current health points
        this.manaActual = 40;  // Current mana points
        
        this.className = "Swords-Man";  // Class name
    }

    // Spells and abilities specific to the Swordsman can be defined here
    
    // Method representing a sword attack
    public double swordHit() {
        return (this.dmgMultipler(this.dmg) * this.dmg);
    }
        
}

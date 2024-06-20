package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Healer extends Player {

    public Healer(String name, int level, Image sprite) throws SlickException {
        super(name, level, new Image("data/Healer/Walk.png"), new Image("data/Healer/Battler.png"), new Image("data/Healer/Face.png"));
        
        // Initialize specific attributes for the Healer class
        this.hpMax = 80;       // Maximum health points
        this.manaMax = 100;    // Maximum mana points
        this.dmg = 2;          // Base damage
        
        this.hpActual = 80;    // Current health points
        this.manaActual = 20;  // Current mana points
        this.className = "Healer";  // Class name
    }

    // Spells and abilities specific to the Healer can be defined here
}

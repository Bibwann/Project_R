package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import java.util.Arrays;

public class Berserker extends Player {

    protected ArrayList<Integer> inventory;

    public Berserker(String name, int level, Image sprite) throws SlickException {
        super(name, level, new Image("data/Berserker/Walk.png"), new Image("data/Berserker/Battler.png"), new Image("data/Berserker/Face.png"));

        // Initialize basic attributes for the Berserker
        this.hpMax = 100;  // Maximum health points
        this.manaMax = 0;   // Maximum mana points (Berserkers have no mana)
        this.dmg = 12;      // Base damage

        this.hpActual = 100;   // Current health points
        this.manaActual = 0;   // Current mana points
        this.className = "Berserker";  // Class name
        
        this.maxPotions = 5;
        this.potions = 5;
        this.healAmount = 25;

    }
    
    @Override
    public int getDmg() {
    	  double hpPercentage = (double) hpActual / hpMax;
    	  double damageMultiplier = 2 - hpPercentage; // Exemple : si 50% HP, multiplier = 1.5
    	  return (int) (this.dmg * damageMultiplier);    }

    
}

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
        this.dmg = 10;      // Base damage

        this.hpActual = 100;   // Current health points
        this.manaActual = 0;   // Current mana points
        this.className = "Berserker";  // Class name
        this.inventory = new ArrayList<>(Arrays.asList(0, 1, 2));  // Initial inventory

    }
    
    // Berserker's passive ability: increases damage based on missing health percentage
    protected int passif(int damage) {
        // Berserker's passive ability: deals increased damage based on missing health percentage
        return damage * (1 - (this.hpActual / this.hpMax));
    }

    public ArrayList<Integer> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Integer> inventory) {
        this.inventory = inventory;
    }
    
    // Attack method for the Berserker, applying the passive effect
    public void hit(Entity entity, int damage) {
        entity.getHit(passif(damage));
    }
}

package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.sae.game.Global;
import fr.sae.game.caractere.Mobs;

public class Chaton extends Mobs {

    public Chaton(String name, int level) throws SlickException {
        super(name, level, new Image(Global.egg2), new Image(Global.egg2));

        // Set specific attributes for Chaton
        this.hpMax = (int) (40*(level*1.2)); // Maximum hit points
        this.manaMax = 0; // Maximum mana points (assuming cats don't use mana)
        this.dmg = (int) (20*(level*1.2)); // Damage points

        this.hpActual = this.hpMax; // Current hit points initialized to maximum
        this.manaActual = 0; // Current mana points initialized (assuming not used)
        
        this.maxPotions = 2;
        this.potions = 2;
        this.healAmount =(int) (7*(level*1.2));
    }

}


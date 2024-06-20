package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Entity;

public final class Boss extends Entity {
	
    private static Boss instance = null;

    private Boss(String name, int level, Image sprite, Image battleSprite) {
        super(name, level, sprite, battleSprite);
        this.hpMax = 300; 
        this.manaMax = 100; 
        this.dmg = 50; 
        this.hpActuel = this.hpMax;
        this.manaActuel = this.manaMax;
    }

    public static synchronized Boss getInstance() throws SlickException {
        if (instance == null) {
        	
            instance = new Boss("Nyan Cat",99,null, new Image("data/Boss/sprite.png"));
        }
        return instance;
    }
}

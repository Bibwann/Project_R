package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Entity;
import fr.sae.game.caractere.Mobs;

public final class Boss extends Mobs {
    
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
            instance = new Boss("Nyan Cat", 99, new Image("data/Boss/sprite.png"), new Image("data/Boss/sprite.png"));
        }
        return instance;
    }
    
    public void Dead(StateBasedGame sbg) {
		if (this.getHpActuel()<1) {
			sbg.enterState(8);
		}
	}
    
    private Image getBattleSprite() { //Battle sprite
		 return this.Battlesprite.getFlippedCopy(true, false).getScaledCopy(1.0f);
   }
}

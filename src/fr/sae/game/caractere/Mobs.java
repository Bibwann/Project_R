package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public abstract class Mobs extends Entity {
	
	public Mobs(String name, int level, Image sprite, Image BattleSprite) {
		super(name, level, sprite,BattleSprite);
	}

}

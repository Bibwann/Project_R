package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class NPC extends Entity {
	
	@SuppressWarnings("null") //Les PNG ne peuvent pas avoir de level dcp on met en null leurs levels, l'ide aime pas ça
	
	public NPC(String name, int level, Image sprite, Image BattleSprite) {
		super(name, level, sprite,BattleSprite);
		
		this.level=(Integer) null;
		
		this.name=name;
		this.sprite=sprite;
		
		
	}

}

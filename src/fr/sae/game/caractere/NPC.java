package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class NPC extends Entity {
	
	@SuppressWarnings("null")
	public NPC(String name, int level, Image sprite) {
		super(name, level, sprite);
		
		this.level=(Integer) null;
		
		this.name=name;
		this.sprite=sprite;
		
		
	}

}

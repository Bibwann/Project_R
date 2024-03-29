package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Player extends Entity { // A mettre en abstract et lui faire heriter 
	
    protected Rectangle hitbox;

	public Player(String name, int level, Image sprite) {
		super(name, level, sprite);

		this.hitbox=new Rectangle(100, 100, 32, 32);
		
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	

}

package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Player extends Entity { // A mettre en abstract et lui faire heriter 
	
    protected Rectangle hitbox;
    protected int experienceBarrActu=0;
    protected int experienceBarrLvlUp=1000;

	public Player(String name, int level, Image sprite) {
		super(name, level, sprite);

		this.hitbox=new Rectangle(100, 100, 32, 48);
	}

	public Rectangle getHitbox() {
		return hitbox;
	}	

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public void levelUp() {
		
		this.level+=1;
		this.hpMax +=10;
		
		this.experienceBarrActu=0;
		this.experienceBarrLvlUp*=1.1;
		
		}

	public int getExperienceBarrActu() {
		return experienceBarrActu;
	}

	public void setExperienceBarrActu(int experienceBarrActu) {
		this.experienceBarrActu = experienceBarrActu;
	}
	

}

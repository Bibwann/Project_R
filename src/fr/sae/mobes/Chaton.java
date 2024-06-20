package fr.sae.mobes;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Mobs;

public class Chaton extends Mobs{

	public Chaton(String name, int level) throws SlickException {
		super(name, level, new Image("data/Cats/cat.png"), new Image("data/Cats/cat.png"));
		
		
		this.hpMax=30;
		this.manaMax=0;
		this.dmg=20;
		
		this.hpActuel=30;
		this.manaActuel=0;
	}
	
	//Sorts
	
	public int griffesHit() {
		return (this.dmgMultipler(this.dmg)*this.dmg);

	}
}

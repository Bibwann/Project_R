package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Swordsman extends Player{

	public Swordsman(String name, int level) throws SlickException {
		super(name, level, new Image("data/Swordman/Walk.png"), new Image("data/Swordman/Battler.png"), new Image("data/Swordman/Face.png"));
		
		this.hpMax=120;
		this.manaMax=40;
		this.dmg=10;
		
		this.hpActuel=120;
		this.manaActuel=40;
		
		this.className = "Swords-Man";
		
	}

	
	//Sorts
	
	public double swordHit() {
		return (this.dmgMultipler(this.dmg)*this.dmg);
	}
		
}

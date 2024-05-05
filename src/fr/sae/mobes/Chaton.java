package fr.sae.mobes;

import org.newdawn.slick.Image;

import fr.sae.game.caractere.Mobs;

public class Chaton extends Mobs{

	public Chaton(String name, int level, Image sprite,Image BattleSprite) {
		super(name, level, sprite, BattleSprite);
		
		
		this.hpMax=30;
		this.manaMax=0;
		this.dmg=20;
		
		this.hpActuel=30;
		this.manaActuel=0;
	}
	
	//Sorts
	
	public double griffesHit() {
		return (this.dmgMultipler(this.dmg)*this.dmg);

	}
}

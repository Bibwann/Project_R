package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Healer extends Player implements Spell {

	public Healer(String name, int level, Image sprite) {
		super(name, level, sprite);
		
		this.hpMax=80;
		this.manaMax=100;
		this.dmg=2;
		
		this.hpActuel=80;
		this.manaActuel=20;
	}

}

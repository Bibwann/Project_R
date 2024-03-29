package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Mage extends Player implements Spell {

	public Mage(String name, int level, Image sprite) {
		super(name, level, sprite);
		this.hpMax=80;
		this.manaMax=100;
		this.dmg=8;
		
		this.hpActuel=80;
		this.manaActuel=100;
	}

}

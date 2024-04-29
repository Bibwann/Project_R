package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Mage extends Player{

	private String classname;

	public Mage(String name, int level, Image sprite) {
		super(name, level, sprite);
		
		this.classname="Mage";

		this.hpMax=80;
		this.manaMax=100;
		this.dmg=8;
		
		this.hpActuel=80;
		this.manaActuel=100;
	}
	
	public void initSprite() {
		//this.sprite= --> set le sprite ici
	}

	//Sorts
}

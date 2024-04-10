package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Swordsman extends Player{

	private String classname;

	public Swordsman(String name, int level, Image sprite) {
		super(name, level, sprite);
		
		this.classname="Healer";

		this.hpMax=120;
		this.manaMax=40;
		this.dmg=7;
		
		this.hpActuel=120;
		this.manaActuel=40;
		
	}
	
	public void setSprite() {
		//this.sprite= --> set le sprite ici
	}

}

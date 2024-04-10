package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Berserker extends Player{


	private String classname;

	public Berserker(String name, int level, Image sprite) {
		super(name, level, sprite);
		
		this.classname="Healer";

		this.hpMax=100;
		this.manaMax=0;
		this.dmg=5;
		
		this.hpActuel=100;
		this.manaActuel=0;
		
	}
	
	private int passif() {
		return 1+(1-(this.hpActuel/this.hpMax));
	}
	
	public void setSprite() {
		//this.sprite= --> set le sprite ici
	}

}

package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public class Healer extends Player{

	private String classname;
	public Healer(String name, int level, Image sprite) {
		super(name, level, sprite,null,null);
		
		this.classname="Healer";
		this.hpMax=80;
		this.manaMax=100;
		this.dmg=2;
		
		this.hpActuel=80;
		this.manaActuel=20;
	}

	//Sorts 
	
	

}

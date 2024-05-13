package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Healer extends Player{

	private String classname;
	public Healer(String name, int level, Image sprite) throws SlickException {
		super(name, level, new Image("data/Healer/Walk.png"), new Image("data/Healer/Battler.png"), new Image("data/Healer/Face.png"));
		
		this.classname="Healer";
		this.hpMax=80;
		this.manaMax=100;
		this.dmg=2;
		
		this.hpActuel=80;
		this.manaActuel=20;
	}

	//Sorts 
	
	

}

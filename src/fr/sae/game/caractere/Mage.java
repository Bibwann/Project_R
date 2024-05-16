package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mage extends Player{

	private String classname;

	public Mage(String name, int level, Image sprite) throws SlickException {
		super(name, level, new Image("data/Mage/Walk.png"), new Image("data/Mage/Battler.png"), new Image("data/Mage/Face.png"));
		
		this.classname="Mage";

		this.hpMax=80;
		this.manaMax=100;
		this.dmg=8;
		
		this.hpActuel=80;
		this.manaActuel=100;
	}
	

	//Sorts
}

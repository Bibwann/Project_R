package fr.sae.game.caractere;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import java.util.Arrays;

public class Berserker extends Player{


	protected ArrayList<Integer> inventory;

	public Berserker(String name, int level, Image sprite) throws SlickException {
		super(name, level, new Image("data/Berserker/Walk.png"), new Image("data/Berserker/Battler.png"), new Image("data/Berserker/Face.png"));
		

		this.hpMax=100;
		this.manaMax=0;
		this.dmg=10;
		
		this.hpActuel=100;
		this.manaActuel=0;
		this.className = "Berserker";
		this.inventory=new ArrayList<>(Arrays.asList(0,1,2));
		
		
		
	}
	
	private int passif(int damage) {
		//Passif du berserker qui dit qu'il tape le % de vie qu'il lui manque ( en gros si il lui manque 99% de sa vie il tape du 1.99 )
		return damage*(1-(this.hpActuel/this.hpMax));
	}

	public ArrayList<Integer> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Integer> inventory) {
		this.inventory = inventory;
	}
	
	
	//Sorts
	public void hit(Entity entity, int damage) {
		entity.getHit(passif(damage));
	}
}

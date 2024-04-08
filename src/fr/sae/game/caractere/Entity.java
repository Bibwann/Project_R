package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public abstract class Entity  {
	
	protected int hpActuel;
	protected int manaActuel;
	protected int hpMax;
	protected int manaMax;
	
	protected int dmg;

	
	
	protected String name;
	protected int level;
	protected Image sprite;
	

	public Entity(String n, int l,Image s) {
		this.name = n;
		this.level = l;
		this.sprite = s;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public Image getSprite() {
		return sprite;
	}


	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public double dmgMultipler(int spellDmg) {
		return (spellDmg*(1+((this.level)*0.05)));
	}
	
}

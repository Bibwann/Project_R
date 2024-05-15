package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public abstract class Entity  {
	protected int hpActuel;
	protected int manaActuel;
	protected int hpMax;
	protected int manaMax;
	
	protected int dmg;
	
	protected String name;
	protected String classname=null;
	protected int level;
	protected Image sprite;
	protected Image Battlesprite;

	public Entity(String n, int l,Image s,Image bs) {
		this.name = n;
		this.level = l;
		this.sprite = s;
		this.Battlesprite = bs;

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
	
	public boolean isDead() {
		return this.hpActuel<=0;
	}
	
	public void resetStats(){
		this.hpActuel = this.hpMax;
		this.manaActuel = this.manaMax;
	}
	
	public void getHit(int damage) {
		this.hpActuel -= damage;
	}
	
	public int getHpActuel() {
		return this.hpActuel;
	}
}

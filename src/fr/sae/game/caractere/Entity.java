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
	
	public int dmgMultipler(int spellDmg) {
		return (spellDmg*(1+(this.level)));
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

	public int getManaActuel() {
		return manaActuel;
	}

	public void setManaActuel(int manaActuel) {
		this.manaActuel = manaActuel;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public Image getBattlesprite() {
		return Battlesprite;
	}

	public void setBattlesprite(Image battlesprite) {
		Battlesprite = battlesprite;
	}

	public void setHpActuel(int hpActuel) {
		this.hpActuel = hpActuel;
	}
	
	public void healEntity() {
		if(this.hpActuel + 50 > this.hpMax) {
			this.hpActuel = this.hpMax;
		} else 
			this.hpActuel += 50;
	}
}

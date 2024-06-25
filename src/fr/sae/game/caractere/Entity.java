package fr.sae.game.caractere;

import org.newdawn.slick.Image;

public abstract class Entity {
    protected int hpActual;
    protected int manaActual;
    protected int hpMax;
    protected int manaMax;
    
    protected int dmg;
    
    protected String name;
    
    protected int level;
    
    protected int maxPotions;
    protected int potions;
    protected int healAmount;
    
    protected Image sprite;
    protected Image Battlesprite;

    public Entity(String n, int l, Image s, Image bs) {
        this.name = n;
        this.level = l;
        this.sprite = s;
        this.Battlesprite = bs;
    }

    // Getters and setters for basic attributes
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

    // Method to calculate damage considering spell damage and entity level
    public int dmgMultipler(int spellDmg) {
        return (spellDmg * (1 + (this.level)));
    }

    // Method to check if the entity is dead
    public boolean isDead() {
        return this.hpActual <= 0;
    }

    // Reset entity's stats to maximum values
    public void resetStats() {
        this.hpActual = this.hpMax;
        this.manaActual = this.manaMax;
        this.potions = this.maxPotions;
    }

    // Method to apply damage to the entity
    public void getHit(int damage) {
        this.hpActual -= damage;
    }

    // Getters and setters for health and mana
    public int getHpActuel() {
        return this.hpActual;
    }

    public int getManaActuel() {
        return manaActual;
    }

    public void setManaActuel(int manaActual) {
        this.manaActual = manaActual;
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

    // Getter and setter for damage
    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    // Getter and setter for Battle sprite
    public Image getBattlesprite() {
        return Battlesprite;
    }

    public void setBattlesprite(Image battlesprite) {
        Battlesprite = battlesprite;
    }

    // Setter for current health
    public void setHpActuel(int hpActual) {
        this.hpActual = hpActual;
    }

    // Method to heal the entity by 50 HP, ensuring it doesn't exceed max HP
    public void healEntity(int heal) {
        if (this.hpActual + heal > this.hpMax) {
            this.hpActual = this.hpMax;
        } else {
            this.hpActual += heal;
        }
    }
    
    public int getPotions() {
    	return this.potions;
    }
    public int getHealAmount() {
    	return this.healAmount;
    }
    
    public void removePotion() {
    	this.potions --;
    }
    
    public boolean hasPotions() {
    	return this.potions > 0;
    }
    
}

package fr.sae.game.caractere;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import fr.sae.game.Global;

public abstract class Mobs extends Entity {
	
	int deformationHitboxX=22; //Cette variable permet de reajuster la hitbox du player pour qu'il passe +/- proche des murs ( a  voir avec les hitbox pour comprendre)
	int deformationHitboxY=8; //Cette variable permet de reajuster la hitbox du player pour qu'il passe +/- proche des murs ( a  voir avec les hitbox pour comprendre)
	
	protected Rectangle hitbox;
	protected Rectangle battleHitbox;
	
	public Mobs(String name, int level, Image sprite, Image BattleSprite) {
		super(name, level, sprite,BattleSprite);
		
		this.hitbox = new Rectangle(Global.SpawnX, Global.SpawnY, 36-deformationHitboxY, 46-deformationHitboxX);
		this.battleHitbox = new Rectangle(0, 0, 0, 0);
	}

	public Rectangle getBattleHitbox() {
        return battleHitbox;
    }
	
	public void setBattlehitbox(Rectangle battlehitbox) {
		battleHitbox = battlehitbox;
	}
	
	private Image getBattleSprite() { //Sprite de combat
		return this.Battlesprite.getSubImage(142, 48, 48, 48).getFlippedCopy(false, false);	
	}
	
    public void BattleScene(Graphics g, int posY) {
    	g.drawImage(this.getBattleSprite(), Global.MobsBattleDistance, posY);
	}
}

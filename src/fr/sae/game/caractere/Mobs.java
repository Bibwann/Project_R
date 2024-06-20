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
		 return this.Battlesprite.getSubImage(142, 48, 48, 48).getFlippedCopy(false, false).getScaledCopy(2.0f);
    }
	
	public void moveUp(float distance) {
    	//this.UpSprite();
		battleHitbox.setY(hitbox.getY() - distance * Global.speed); // Réduire la vitesse en ajustant le multiplicateur (ici 0.1f)
    }

    // Méthode pour déplacer le mob vers le bas
    public void moveDown(float distance) {
    	//this.DownSprite();
        battleHitbox.setY(hitbox.getY() + distance * Global.speed);

    }

    // Méthode pour déplacer le mob vers la gauche
    public void moveLeft(float distance) {
    	//this.LeftSprite();
    	battleHitbox.setX(hitbox.getX() - distance * Global.speed);

    }

    // Méthode pour déplacer le mob vers la droite
    public void moveRight(float distance) {
    	//this.RightSprite();
    	battleHitbox.setX(hitbox.getX() + distance * Global.speed);
    }
    
    public void BattleScene(Graphics g, int posY) {
    	g.drawImage(this.getBattleSprite(), Global.MobsBattleDistance, posY);
	}
}

package fr.sae.game.caractere;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import fr.sae.game.Global;

public abstract class Player extends Entity { // A mettre en abstract et lui faire heriter 
	
    protected Rectangle hitbox;
    protected int experienceBarrActu=0;
    protected int experienceBarrLvlUp=1000;

    public Player(String name, int level, Image sprite) {
        super(name, level, sprite);

        this.hitbox=new Rectangle(100, 100, 32, 48);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void Sprite(Graphics g,int x , int y) {
    	g.drawImage(this.getSprite(), x,y);
	}

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    
    public void levelUp() {
        
        this.level+=1;
        this.hpMax +=10;
        
        this.dmg*=1.1;
        
        this.experienceBarrActu=0;
        this.experienceBarrLvlUp*=1.1;
    }

    public int getExperienceBarrActu() {
        return experienceBarrActu;
    }

    public void setExperienceBarrActu(int experienceBarrActu) {
        this.experienceBarrActu = experienceBarrActu;
    }

    public void moveUp(float distance) {
        hitbox.setY(hitbox.getY() - distance * Global.speed); // Réduire la vitesse en ajustant le multiplicateur (ici 0.1f)
    }

    // Méthode pour déplacer le joueur vers le bas
    public void moveDown(float distance) {
        hitbox.setY(hitbox.getY() + distance * Global.speed);
    }

    // Méthode pour déplacer le joueur vers la gauche
    public void moveLeft(float distance) {
        hitbox.setX(hitbox.getX() - distance * Global.speed);
    }

    // Méthode pour déplacer le joueur vers la droite
    public void moveRight(float distance) {
        hitbox.setX(hitbox.getX() + distance * Global.speed);
    }
    
    public void BattleScene(Graphics g, int posY) {
    	g.drawImage(this.getSprite(), 50, posY);
	}
}

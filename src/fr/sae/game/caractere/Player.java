package fr.sae.game.caractere;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;

import fr.sae.game.Global;

public abstract class Player extends Entity { 
	
	int deformationHitboxX=22; //Cette variable permet de reajuster la hitbox du player pour qu'il passe +/- proche des mures ( a  voir avec les hitbox pour comprendre)
	int deformationHitboxY=8; //Cette variable permet de reajuster la hitbox du player pour qu'il passe +/- proche des mures ( a  voir avec les hitbox pour comprendre)

	protected Image chibi;
    protected Rectangle hitbox;
    protected Rectangle Battlehitbox;

    protected int experienceBarrActu=0;
    protected int experienceBarrLvlUp=1000;
    
    protected int Orientation;
	private Animation animation;

    public Player(String name, int level, Image sprite,Image BattleSprite, Image chibi) {
        super(name, level, sprite,BattleSprite);
        
        this.Orientation=0;
        this.hitbox=new Rectangle(Global.SpawnX, Global.SpawnY, 36-deformationHitboxY, 46-deformationHitboxX);
        this.Battlehitbox=new Rectangle(0, 0, 0, 0); //Hitbox unique a la battle scene pour pas que le plaer lance le combat de la ou il etait

    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void Sprite(Graphics g) {
        this.animation.draw(this.getHitbox().getX()-deformationHitboxY/2,this.getHitbox().getY()-deformationHitboxX);
        
        //g.drawImage(this.getAnimation().getImage(1).getSubImage(4, this.Orientation+2, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
        //g.drawImage(this.getSprite().getSubImage(4, this.Orientation+2, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
	}

	private Image getBattleSprite() { //Sprite de combat
		return this.Battlesprite.getSubImage(0, 0, 48, 64).getFlippedCopy(true, false);	}

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
    	//this.UpSprite();
        hitbox.setY(hitbox.getY() - distance * Global.speed); // Réduire la vitesse en ajustant le multiplicateur (ici 0.1f)
    }

    // Méthode pour déplacer le joueur vers le bas
    public void moveDown(float distance) {
    	//this.DownSprite();
        hitbox.setY(hitbox.getY() + distance * Global.speed);

    }

    // Méthode pour déplacer le joueur vers la gauche
    public void moveLeft(float distance) {
    	//this.LeftSprite();
        hitbox.setX(hitbox.getX() - distance * Global.speed);

    }

    // Méthode pour déplacer le joueur vers la droite
    public void moveRight(float distance) {
    	//this.RightSprite();
        hitbox.setX(hitbox.getX() + distance * Global.speed);
    }
    
    public void BattleScene(Graphics g, int posY) {
    	g.drawImage(this.getBattleSprite(), Global.PlayerBattleDistance, posY);
	}

	public Rectangle getBattlehitbox() {
		return Battlehitbox;
	}

	public void setBattlehitbox(Rectangle battlehitbox) {
		Battlehitbox = battlehitbox;
	}
	
	public void DownSprite() { //Defini l'orientation du sprite
		if(this.Orientation !=0) {
			this.Orientation=0;
			this.Animation();
		}
        //g.drawImage(this.getSprite().getSubImage(0, 0, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
	}
	
	public void LeftSprite() { //Defini l'orientation du sprite
		if (this.Orientation !=48) {
			this.Orientation=48;
			this.Animation();
		}

        //g.drawImage(this.getSprite().getSubImage(0, 48, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
	}
	
	public void RightSprite() { //Defini l'orientation du sprite
		if (this.Orientation !=96) {
			this.Orientation=96;
			this.Animation();		
		}

        //g.drawImage(this.getSprite().getSubImage(0, 96, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
	}
	
	public void UpSprite() { //Defini l'orientation du sprite
		if (this.Orientation != 144) {
			this.Orientation=144;
			this.Animation();	
		}

        //g.drawImage(this.getSprite().getSubImage(0, 144, 42, 48), this.getHitbox().getX(), this.getHitbox().getY());
	}

	public void Animation() {
		try {
			Image[] frames = {
		            this.getSprite().getSubImage(0+4, this.Orientation+2, 42, 48),
		            this.getSprite().getSubImage(48+4, this.Orientation+2, 42, 48),
		            this.getSprite().getSubImage(96+4, this.Orientation+2, 42, 48)
		        };
		        this.animation= new Animation(frames, 300);
		        
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public int getOrientation() {
		return Orientation;
	}

	public void setOrientation(int orientation) {
		Orientation = orientation;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public void AnimateWhileMoove() {
		if (Global.InputHandler.isPressed()) {
	    	this.getAnimation().start();
		} else {
	    	this.getAnimation().stop();
			this.getAnimation().setCurrentFrame(1);
		}
	}

}

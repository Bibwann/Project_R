package fr.sae.game.caractere;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Animation;

import fr.sae.game.Global;

public abstract class Player extends Entity {
    
    // Variables for adjusting hitbox deformation
    int deformationHitboxX = 22; // Adjusts the player's hitbox for proximity to walls (to be reviewed with hitboxes)
    int deformationHitboxY = 8;  // Adjusts the player's hitbox for proximity to walls (to be reviewed with hitboxes)

    protected Image chibi;        // Character's chibi image
    protected Rectangle hitbox;   // Hitbox for normal gameplay
    protected Rectangle Battlehitbox; // Hitbox specific to battle scenes

    protected int experienceBarrActu = 0;  // Current experience points
    protected int experienceBarrLvlUp = 1000;  // Experience points needed for level up

    protected int Orientation;    // Orientation of the player
    private Animation animation;  // Animation object for player's movements
    protected String className;  // Class name of the player

    public Player(String name, int level, Image sprite, Image BattleSprite, Image chibi) {
        super(name, level, sprite, BattleSprite);
        
        // Initialize player attributes
        this.Orientation = 0;
        this.hitbox = new Rectangle(Global.SpawnX, Global.SpawnY, 36 - deformationHitboxY, 46 - deformationHitboxX);
        this.Battlehitbox = new Rectangle(0, 0, 0, 0); // Unique battle scene hitbox to prevent starting combat from previous position
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    // Method to draw player sprite
    public void Sprite(Graphics g) {
        this.animation.draw(this.getHitbox().getX() - deformationHitboxY / 2, this.getHitbox().getY() - deformationHitboxX);
    }

    // Method to get battle sprite
    private Image getBattleSprite() {
        return this.Battlesprite.getSubImage(0, 0, 48, 64).getFlippedCopy(true, false).getScaledCopy(2.0f);
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    
    // Method to level up the player
    public void levelUp() {
        this.level += 1;
        this.hpMax += 10;
        this.dmg *= 1.1;
        this.healAmount += 5;
    }

    // Getter for current experience points
    public int getExperienceBarrActu() {
        return experienceBarrActu;
    }

    // Setter for current experience points
    public void setExperienceBarrActu(int experienceBarrActu) {
        this.experienceBarrActu = experienceBarrActu;
    }

    // Method to move player up
    public void moveUp(float distance, int delta) {
        hitbox.setY(hitbox.getY() - distance * Global.speed * delta);
    }

    // Method to move player down
    public void moveDown(float distance, int delta) {
        hitbox.setY(hitbox.getY() + distance * Global.speed * delta);
    }

    // Method to move player left
    public void moveLeft(float distance, int delta) {
        hitbox.setX(hitbox.getX() - distance * Global.speed * delta);
    }

    // Method to move player right
    public void moveRight(float distance, int delta) {
        hitbox.setX(hitbox.getX() + distance * Global.speed * delta);
    }
    
    // Method to draw player sprite in battle scene
    public void BattleScene(Graphics g, int posY) {
        g.drawImage(this.getBattleSprite(), Global.PlayerBattleDistance, posY);
    }

    // Getter for battle hitbox
    public Rectangle getBattlehitbox() {
        return Battlehitbox;
    }

    // Setter for battle hitbox
    public void setBattlehitbox(Rectangle battlehitbox) {
        Battlehitbox = battlehitbox;
    }
    
    // Method to set sprite orientation down
    public void DownSprite() {
        if (this.Orientation != 0) {
            this.Orientation = 0;
            this.Animation();
        }
    }
    
    // Method to set sprite orientation left
    public void LeftSprite() {
        if (this.Orientation != 48) {
            this.Orientation = 48;
            this.Animation();
        }
    }
    
    // Method to set sprite orientation right
    public void RightSprite() {
        if (this.Orientation != 96) {
            this.Orientation = 96;
            this.Animation();
        }
    }
    
    // Method to set sprite orientation up
    public void UpSprite() {
        if (this.Orientation != 144) {
            this.Orientation = 144;
            this.Animation();
        }
    }

    // Method to initialize animation frames
    public void Animation() {
        try {
            Image[] frames = {
                this.getSprite().getSubImage(0 + 4, this.Orientation + 2, 42, 48),
                this.getSprite().getSubImage(48 + 4, this.Orientation + 2, 42, 48),
                this.getSprite().getSubImage(96 + 4, this.Orientation + 2, 42, 48)
            };
            this.animation = new Animation(frames, 300);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    // Getter for player orientation
    public int getOrientation() {
        return Orientation;
    }

    // Setter for player orientation
    public void setOrientation(int orientation) {
        Orientation = orientation;
    }

    // Getter for animation object
    public Animation getAnimation() {
        return animation;
    }

    // Setter for animation object
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
    
    // Method to animate while moving
    public void AnimateWhileMoove() {
        if (Global.InputHandler.isPressed()) {
            this.getAnimation().start();
        } else {
            this.getAnimation().stop();
            this.getAnimation().setCurrentFrame(1);
        }
    }
    
    // Getter for class name
    public String getClassName() {
        return this.className;
    }
    
    public int takeXp(int xp) {

        int nbLevelWin=(this.experienceBarrActu+xp)/this.experienceBarrLvlUp; //Division euclidienne
        this.experienceBarrActu=(this.experienceBarrActu+xp)%this.experienceBarrLvlUp; //Modulo

        for(int i=0;i<nbLevelWin;i++) {
            this.levelUp();
        }

        return nbLevelWin;
    } 


}

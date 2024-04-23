package fr.sae.game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//Classe a refaire, gere les animation ( provient de ma beta teste )

public class EntityAnimations {
	
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation upAnimation;
    private Animation downAnimation;

    public EntityAnimations(Image layoutImage) throws SlickException {
    	
        leftAnimation = new Animation();
        leftAnimation.addFrame(layoutImage.getSubImage(0, 250, 24, 24), 300);

        rightAnimation = new Animation();
        rightAnimation.addFrame(layoutImage.getSubImage(48, 200, 24, 24), 300);


        upAnimation = new Animation();
        upAnimation.addFrame(layoutImage.getSubImage(0, 172, 28, 26), 300);


        downAnimation = new Animation();
        downAnimation.addFrame(layoutImage.getSubImage(24, 98, 24, 32), 300);

    }

    public Animation getLeftAnimation() {
        return leftAnimation;
    }

    public Animation getRightAnimation() {
        return rightAnimation;
    }

    public Animation getUpAnimation() {
        return upAnimation;
    }

    public Animation getDownAnimation() {
        return downAnimation;
    }
}

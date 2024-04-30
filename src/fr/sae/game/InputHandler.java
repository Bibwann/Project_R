package fr.sae.game;
import org.newdawn.slick.Input;

public class InputHandler { //Classe permettant de savoir si une touche est enfoncé ou pas
    private boolean keyPressed;
    
    private long time;
    private long delta;
    private long interval;

    public InputHandler(long interval) {
    	
        this.interval = interval;
        this.time = System.currentTimeMillis();
        this.delta = 0;
        this.keyPressed=false;
    }

    public void keyPressed() {
    	keyPressed=true;
    	this.time = System.currentTimeMillis(); 
    }

    public void keyReleased() {
        keyPressed = false;
    }

    public boolean isPressed() {
        
        this.delta= (System.currentTimeMillis() - this.time);
        
        if ( this.delta >= this.interval) {
        	this.keyPressed=false;
            return false;
            
        }else {	
        	this.keyPressed=true;
        	return true;
        }
    }
}

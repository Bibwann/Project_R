package fr.sae.game.maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;
import fr.sae.game.Warp;

public class Underground1 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	Warp Warp4;
	public Underground1(int stateID) {
	}


	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1600, 100, 120, 30, 1640,1020);
        this.Warp2= new Warp(390, 100, 60, 60, 1100, 600);
        this.Warp3= new Warp(200, 1070, 120, 10, 1210, 180);
        this.Warp4= new Warp(1000, 960, 60, 60, 1680, 320);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 24; 
		g.drawImage(new Image("data/maps/Map014.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 25);//HAUT
        this.Warp2.warp(Global.P1, sbg, 13);//ECHELLE
        this.Warp3.warp(Global.P1, sbg, 26);//BAS
        this.Warp4.warp(Global.P1, sbg, 12);//ESCALIER
       

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapUnderground1.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.Warp4.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapUnderground1,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		
	}

	@Override
	public int getID() {
		return 24;
	}

}
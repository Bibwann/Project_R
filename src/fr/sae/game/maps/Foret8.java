package fr.sae.game.maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;
import fr.sae.game.Warp;

public class Foret8 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	public Foret8(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1200, 0, 90, 10, 1220, 1020);//HAUT
		this.Warp2= new Warp(1600, 0, 90, 10, 1620, 1020);//HAUT
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(new Image("data/maps/Map008.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 14);
        this.Warp2.warp(Global.P1, sbg, 14);
       

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (true) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet8.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet8,delta);
		Global.P1.AnimateWhileMoove();
		
	}

	@Override
	public int getID() {
		return 18;
	}

}
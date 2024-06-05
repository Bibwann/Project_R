package fr.sae.game.maps;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;
import fr.sae.game.Warp;

public class Foret6 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	public Foret6(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(860, 1070, 470, 10, 1100, 50);
		this.Warp2= new Warp(330, 130, 60, 60, 1100, 600);
	}


	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.actualId = 16;
		
		g.drawImage(new Image("data/maps/Map006.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 11);
        this.Warp2.warp(Global.P1, sbg, 26);//GROTTE
       

        
//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (true) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet6.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet6,delta);
		Global.P1.AnimateWhileMoove();
		sbg.enterState(8);

		
	}

	@Override
	public int getID() {
		return 16;
	}

}
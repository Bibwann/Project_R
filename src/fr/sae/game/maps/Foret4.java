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

public class Foret4 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	Warp Warp4;
	Warp Warp5;
	Warp Warp6;
	Warp Warp7;
	
	public Foret4(int stateID) {
	}

	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(180, 0, 140, 10, 290, 1020);//H
		this.Warp2= new Warp(1000, 0, 140, 10, 1070, 1020);//H
		this.Warp3= new Warp(1200, 1070, 90, 10, 1220, 50);//BAS
		this.Warp4= new Warp(1600, 1070, 90, 10, 1650, 50);//BAS
		this.Warp5= new Warp(1910, 500, 10, 180, 50, 560);//D
		this.Warp6= new Warp(535, 200, 60, 45, 100, 100);//GROTTE
		this.Warp7= new Warp(135, 905, 60, 45, 100, 100);//GROTTE
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.actualId = 14;
		
		g.drawImage(new Image("data/maps/Map004.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 13);
        this.Warp2.warp(Global.P1, sbg, 13);
        this.Warp3.warp(Global.P1, sbg, 18);
        this.Warp4.warp(Global.P1, sbg, 18);
        this.Warp5.warp(Global.P1, sbg, 19);
        this.Warp6.warp(Global.P1, sbg, 14);
        this.Warp7.warp(Global.P1, sbg, 14);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet3.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.Warp4.draw(g);
		    this.Warp5.draw(g);
		    this.Warp6.draw(g);
		    this.Warp7.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet3,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();


	}

	@Override
	public int getID() {
		return 14;
	}

}
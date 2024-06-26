package fr.sae.game.maps;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;
import fr.sae.mobes.Chaton;

public class Foret2 extends BasicGameState{
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	Warp Warp4;
	Warp Warp5;
	Warp Warp6;
	Warp Warp7;
	public Foret2(int stateID) {
	}

	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.Warp1= new Warp(0, 60, 10, 380, 1860, 160);//G
        this.Warp2= new Warp(1400, 1070, 380, 10, 1540, 40);//BAS
        this.Warp3= new Warp(860, 640, 60, 60, 210, 210);//GROTTE
        this.Warp4= new Warp(1720, 320, 60, 60, 1100, 950);//ESCALIER
        this.Warp5= new Warp(980, 1070, 80, 10, 900, 40);//BAS
        this.Warp6= new Warp(650, 1070, 75, 10, 670, 40);//BAS
        this.Warp7= new Warp(870, 0, 190, 10, 945, 1020);//haut
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		Global.actualId = 12;
		g.drawImage(new Image("data/maps/Map002.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 15);
        this.Warp2.warp(Global.P1, sbg, 13);
        this.Warp3.warp(Global.P1, sbg, 25);
        this.Warp4.warp(Global.P1, sbg, 24);
        this.Warp5.warp(Global.P1, sbg, 13);
        this.Warp6.warp(Global.P1, sbg, 13);
        this.Warp7.warp(Global.P1, sbg, 22);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet2.drawCollisions(g);
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

		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet2,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Global.P1.cannotRandomBattle();

		
//----------------------------------------------------------------------------------------------------------------------------
	//Temp
		//Lance un combat de force -- present pour le debug a retirer
		//if (!Global.tmpVarLaunchBattleScene ) {
			//Global.canMoovPlayer=false;
			//Global.tmpVarLaunchBattleScene=false;
			//Global.mobs[0]=new Chaton("Chat-Pitaine", 1);
			//Global.mobs[1]=new Chaton("Chat-Thon", 1);
	        //sbg.enterState(100);

		//}
		
//----------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public int getID() {
		return 12;
	}

}

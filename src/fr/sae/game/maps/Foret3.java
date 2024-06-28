package fr.sae.game.maps;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.Global;
import fr.sae.game.Warp;

public class Foret3 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	Warp Warp4;
	Warp Warp5;
	Warp Warp6;
	Warp Warp7;
	Warp Warp8;
	Warp Warp9;
	Warp Warp10;
	private DialogueBox dialogueBoxBranche;
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
	public Foret3(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1330, 0, 450, 10, 1540, 1020);//H

		this.Warp2= new Warp(220, 1070, 110, 10, 200, 40);//BAS
		this.Warp3= new Warp(1000, 1070, 110, 10, 1060, 50);//BAS
		this.Warp4= new Warp(0, 0, 10, 170, 1870, 30);//G
		this.Warp5= new Warp(0, 830, 10, 180, 1870, 900);//G
		this.Warp6= new Warp(1910, 60, 10, 370, 50, 120);//D
		this.Warp7= new Warp(1910, 750, 10, 240, 50, 810);//D
		this.Warp8= new Warp(880, 0, 260, 10, 1000, 1020);//H
		this.Warp9= new Warp(650, 0, 75, 10, 670, 1020);//H
		this.Warp10= new Warp(130, 210, 65, 40, 1870, 200);//GROTTE
		

		this.dialogueBoxBranche = new DialogueBox(new String[] {
  				"Ceci est une branche"
  	        });
  	    this.dialogueBoxBranche.setTriggerZone(1123, 629, 80, 90);
  	    
  	    this.dialogueBoxBranche.setChoices(Arrays.asList("Taper la branche", "Ne rien faire"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	this.tmpDialogbox1.setActiveTempDialogbox(true);
  	                this.tmpDialogbox1.setMessages(new String[] {"Aie !"});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi

  	                this.tmpDialogbox1.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
  	                    switch (choice2) {

  	                        case 0:
  	                        	this.tmpDialogbox1.setActiveTempDialogbox(true);
  	                            this.tmpDialogbox1.setMessages(new String[] {"AIEEEE !!!!!"});
  	                            
  	                            //Permet de dire qu'il s'agissait du dernier choix
  	                            this.tmpDialogbox1.setChoices(Arrays.asList(),null);
  	                            break;

  	                        case 1:
  	                        	this.tmpDialogbox1.setActiveTempDialogbox(false);
  	                        	
  	                    }
  	                    
  	                });
  	                break;
  	                
  	                
  	            case 1:
  	            	this.tmpDialogbox1.setActiveTempDialogbox(false);
  	            	break;

              }       
       });

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.actualId = 13;
		
		g.drawImage(new Image("data/maps/Map003.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 12);
        this.Warp2.warp(Global.P1, sbg, 14);
        this.Warp3.warp(Global.P1, sbg, 14);
        this.Warp4.warp(Global.P1, sbg, 17);//G
        this.Warp5.warp(Global.P1, sbg, 17);//G
        this.Warp6.warp(Global.P1, sbg, 20);//D
        this.Warp7.warp(Global.P1, sbg, 20);//D
        this.Warp8.warp(Global.P1, sbg, 12);
        this.Warp9.warp(Global.P1, sbg, 12);
        this.Warp10.warp(Global.P1, sbg, 25);
        dialogueBoxBranche.render(g);
        

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
		    this.Warp8.draw(g);
		    this.Warp9.draw(g);
		    this.Warp10.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.tmpDialogbox1.renderTempDialgbox(g);

	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet3,delta,sbg);
		
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox1.updateTempDialgbox(i, gc);



	}

	@Override
	public int getID() {
		return 13;
	}

}
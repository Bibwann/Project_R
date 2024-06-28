package fr.sae.game.maps;

import org.newdawn.slick.state.BasicGameState;

import java.util.Arrays;

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
	//Warp Warp7;
	private DialogueBox dialogueBoxBranche;
	private DialogueBox dialogueBoxPanneau;
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
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
		//this.Warp7= new Warp(870, 0, 190, 10, 945, 1020);//haut
		
		
		this.dialogueBoxPanneau = new DialogueBox(new String[] {
				"\n" +
			    "  |  \n" +
			    "  |         Château Ensoleillé\n" +
			    "  |  \n"+
			    "  V         Marchand  \n"+
			    "\n"
			});	
		this.dialogueBoxPanneau.setTriggerZone(987,127,75,75);
		
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
  				"Ceci est une branche"
  	        });
  	    this.dialogueBoxBranche.setTriggerZone(1380, 259, 80, 90);
  	    
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
        //this.Warp7.warp(Global.P1, sbg, 22);
        dialogueBoxBranche.render(g);
        dialogueBoxPanneau.render(g);

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
		    //this.Warp7.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxPanneau.draw(g);
		    this.tmpDialogbox1.renderTempDialgbox(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet2,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		Global.P1.AnimateWhileMoove();
		Global.P1.cannotRandomBattle();
		this.dialogueBoxPanneau.dialogBox(i);
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox1.updateTempDialgbox(i, gc);
		
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

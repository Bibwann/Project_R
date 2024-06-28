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

public class Foret13 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	DialogueBox dialogueBoxBranche;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Foret13(int stateID) {
	}

	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(860, 1070, 470, 10, 1100, 50);
		this.Warp2= new Warp(330, 130, 60, 60, 1100, 600);
		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(985, 690, 86, 80);
	    
	    this.dialogueBoxBranche.setChoices(Arrays.asList("Taper la branche", "Ne rien faire"), choice1 -> {
            switch (choice1) {
	            case 0:
	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                this.tmpDialogbox2.setMessages(new String[] {"Aie !"});
	                //Ajoutez recursivement des choix ici de la meme maniere que moi

	                this.tmpDialogbox2.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
	                    switch (choice2) {

	                        case 0:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                            this.tmpDialogbox2.setMessages(new String[] {"AIEEEE !!!!!"});
	                            
	                            //Permet de dire qu'il s'agissait du dernier choix
	                            this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                            break;

	                        case 1:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(false);

	                    }
	                    
	                });
	                break;
	                
	                
	            case 1:
	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
	            	break;

            }       
     });
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 23;
		g.drawImage(new Image("data/maps/Map014.png").getScaledCopy(Global.width, Global.height), 0, 0);
		dialogueBoxBranche.render(g);
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        
        this.Warp1.warp(Global.P1, sbg, 11);
        this.Warp2.warp(Global.P1, sbg, 16);
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet13.drawCollisions(g);
		    
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.dialogueBoxBranche.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet13,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Input input =gc.getInput();
		Global.updatePlayerMovement(input,Global.CollisionMapForet6,delta,sbg);
		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox2.updateTempDialgbox(i, gc);
		
	}

	@Override
	public int getID() {
		return 23;
	}

}
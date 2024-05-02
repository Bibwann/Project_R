package fr.sae.game.maps;

import org.newdawn.slick.state.BasicGameState;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.EntityAnimations;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;

public class Foret1 extends BasicGameState{
	
	Warp Warp1;
	Warp Warp2;
	DialogueBox dialogueBoxPanneau; 
    DialogueBox dialogueBoxBranche;
	private DialogueBox tmpDialogbox= new DialogueBox(new String[] {});

	public Foret1(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(Global.width-10, Global.height-320, 10, 320, 500, 500);
		this.Warp2= new Warp(1324, 0, 66, 10, 500, 500);
	    this.tmpDialogbox.setTriggerZone(0, 0, 0, 0);


		this.dialogueBoxPanneau = new DialogueBox(new String[] {
				"\n" +
			    "  ^  \n" +
			    "  |         Montagne du Nord\n" +
			    "  |  \n",
			    "\n" +
			    "\n" +
			    " --->       Village\n"+
			    "\n"
			});		
		this.dialogueBoxPanneau.setTriggerZone(Global.width-530,570,66,10);

		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"\n "+
			    "     \n" +
			    "           Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(718, 440, 86, 80);
	    
	    this.dialogueBoxBranche.setChoices(Arrays.asList("Taper la branche", "Ne rien faire"), choice -> {
            switch (choice) {
	            case 0:
	                this.tmpDialogbox.setMessages(new String[] {"\n"+"\n"+"           Aie !!!!!!!!!"});
	                this.tmpDialogbox.setActiveTempDialogbox(true);
	                //Ajoutez recursivement des choix ici
	                break;
            }
        });

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("data/maps/Map001.png").getScaledCopy(Global.width, Global.height), 0, 0);
        dialogueBoxPanneau.render(g);
        dialogueBoxBranche.render(g);
        
        if (this.tmpDialogbox.getMessages().length != 0) {
        	tmpDialogbox.render(g);
        }


        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        
		this.Warp1.warp(Global.P1, sbg, 12);
		this.Warp2.warp(Global.P1, sbg, 13);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (true) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet1.drawCollisions(g);
		    
		    this.dialogueBoxPanneau.draw(g);
		    this.dialogueBoxBranche.draw(g);

		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------

		}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		Global.updatePlayerMovement(input, Global.CollisionMapForet1);
		Global.P1.AnimateWhileMoove();
		
		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
        this.dialogueBoxPanneau.dialogBox(i);
        this.dialogueBoxBranche.dialogBox(i,gc.getInput());
        this.tmpDialogbox.dialogBox(i); 

        
        
	}

	@Override
	public int getID() {
		return 11;
	}

}

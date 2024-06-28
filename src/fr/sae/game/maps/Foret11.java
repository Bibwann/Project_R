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

public class Foret11 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	DialogueBox dialogueBoxBranche;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Foret11(int stateID) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(0, 570, 10, 150, 1860, 560);//gauche
		this.Warp2= new Warp(200, 0, 1500, 10, 945, 1020);//haut
		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(984, 509, 86, 80);
	    
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
		Global.actualId = 21;
		g.drawImage(new Image("data/maps/Map012.png").getScaledCopy(Global.width, Global.height), 0, 0);
		dialogueBoxBranche.render(g);
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 20);
        this.Warp2.warp(Global.P1, sbg, 22);
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet11.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.dialogueBoxBranche.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet11,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();
		Input input =gc.getInput();
		Global.updatePlayerMovement(input,Global.CollisionMapForet11,delta,sbg);
		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox2.updateTempDialgbox(i, gc);

	}

	@Override
	public int getID() {
		return 21;
	}

}
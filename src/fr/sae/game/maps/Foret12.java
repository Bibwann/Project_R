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
import fr.sae.mobes.Boss;
import fr.sae.mobes.Chaton;

public class Foret12 extends BasicGameState {
	Warp Warp1;
	DialogueBox dialogueBoxBranche;
	DialogueBox dialogueBoxCBTFIN;
	private DialogueBox dialogueBoxPanneau;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Foret12(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(200, 1070, 1500, 10, 945, 50);//BAS
		
		
		
		this.dialogueBoxCBTFIN = new DialogueBox(new String[] {
				"Bonjour jeunes aventuriers, qui êtes-vous ?"
	        });
		this.dialogueBoxCBTFIN.setTriggerZone(926,373, 80, 30);
	    this.dialogueBoxCBTFIN.setChoices(Arrays.asList("Se présenter","Partir"), choice1 -> {
            switch (choice1) {
	            case 0:
    	    		this.tmpDialogbox2.setActiveTempDialogbox(true);

	        	    this.tmpDialogbox2 = new DialogueBox(new String[] {
	        				
	        				 "Oh ! Tu es donc venu m'apporter l'objet du vieux mage ?\n"+"C'est parfait, donne-le moi s'il te plaît."
	        	        });
	        	    this.tmpDialogbox2.setChoices(Arrays.asList("Donner","Garder"), choice2 -> {
	        	    	switch (choice2) {
	        	    	case 0:
	        	    		this.tmpDialogbox2 = new DialogueBox(new String[] {
	    	        				"À l'aide de cet objet qui était une pelote de laine, nous allons pouvoir nous battre contre Le Chat qui a pris le contrôle de ce royaume..."
		        	        });
	        	    		this.tmpDialogbox2.setActiveTempDialogbox(false);
            				Global.Underground3Battle=false;

                			Global.canMoovPlayer=false;
                			Global.actualId=8;
            				try {
								Global.mobs[0]=Boss.getInstance();

            					//Global.mobs[0]= Boss.getInstance();
							} catch (SlickException e) {
								e.printStackTrace();
							}
            	        	sbg.enterState(100);
            	        	this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	        	    		break;
	        	    	
	        	    	case 1:
	        	    		
	        	    		
	        	    		this.tmpDialogbox2.setActiveTempDialogbox(false);
	    	            	break;
	        	    }
	        	    });
	                break;
	                
	            case 1:
	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
	            	break;

            }       
     });
		
		
		this.dialogueBoxPanneau = new DialogueBox(new String[] {
				"\n" +
			    "  ^  \n" +
			    "  |         Château Ensoleillé\n" +
			    "  |  \n"+
			    "\n"+
			    "  |  \n" +
			    "  |         Forêt de Luminara\n" +
			    "  v  \n"+
			    "\n"
			});	
		
		this.dialogueBoxPanneau.setTriggerZone(1051,940,75,75);
		
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(1848, 0, 86, 80);
	    
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
		Global.actualId = 22;
		g.drawImage(new Image("data/maps/Map013.png").getScaledCopy(Global.width, Global.height), 0, 0);
		g.drawImage(new Image("data/npc/Erza/Erza3.png").getSubImage(0, 0, 48, 48),939, 325);
		dialogueBoxBranche.render(g);
		dialogueBoxPanneau.render(g);
		dialogueBoxCBTFIN.render(g);
		
		tmpDialogbox2.renderTempDialgbox(g);

        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 21);
        

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet12.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxPanneau.draw(g);
		    this.dialogueBoxCBTFIN.draw(g);
		    this.tmpDialogbox2.draw(g);
		    
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet12,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Input input =gc.getInput();
		Global.updatePlayerMovement(input,Global.CollisionMapForet12,delta,sbg);
		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxPanneau.dialogBox(i);
		this.dialogueBoxCBTFIN.dialogBox(i,gc.getInput());
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox2.updateTempDialgbox(i, gc);

	}

	@Override
	public int getID() {
		return 22;
	}

}
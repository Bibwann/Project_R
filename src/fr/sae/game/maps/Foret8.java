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

public class Foret8 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	private DialogueBox dialogueBoxBranche;
	private DialogueBox dialogueBoxChest;
	private DialogueBox dialogueBoxArbre1;
	private DialogueBox dialogueBoxArbre2;
	private DialogueBox dialogueBoxArbre3;
	private DialogueBox dialogueBoxArbre4;
	private DialogueBox dialogueBoxArbre5;


private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
	public Foret8(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1200, 0, 90, 10, 1220, 1020);//HAUT
		this.Warp2= new Warp(1600, 0, 90, 10, 1620, 1020);//HAUT
		
		this.dialogueBoxArbre1 = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
  					    " \n " +
  					    " \n " +
  					    "                                        5567 - 56.."
  					    
  			});	
  		this.dialogueBoxArbre1.setTriggerZone(263,757,60,30);
  		
  		this.dialogueBoxArbre2 = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
  					    " \n " +
  					    " \n " +
  					    "                                        5567 - 56.."
  					    
  			});	
  		this.dialogueBoxArbre2.setTriggerZone(390,503,60,30);
  		
  		this.dialogueBoxArbre3 = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
  					    " \n " +
  					    " \n " +
  					    "                                        5567 - 56.."
  					    
  			});	
  		this.dialogueBoxArbre3.setTriggerZone(464,688,60,30);
  		
  		this.dialogueBoxArbre4 = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
  					    " \n " +
  					    " \n " +
  					    "                                        5567 - 56.."
  					    
  			});	
  		this.dialogueBoxArbre4.setTriggerZone(658,688,60,30);
  		
  		this.dialogueBoxArbre5 = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
  					    " \n " +
  					    " \n " +
  					    "                                        5567 - 56.."
  					    
  			});	
  		this.dialogueBoxArbre5.setTriggerZone(531,874,60,30);
  		
		if (Global.Chest1Map8) {
		    this.dialogueBoxChest = new DialogueBox(new String[] {
					"Vous obtenez : une potion de force.\n"+"\n"+"Votre force augmente de +2"
				});	
			this.dialogueBoxChest.setTriggerZone(335,760,80,70);
			Global.Chest1Map8=false;
		    }
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
  				"Ceci est une branche"
  	        });
  	    this.dialogueBoxBranche.setTriggerZone(1389, 256, 80, 90);
  	    
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
		Global.actualId = 18;
		g.drawImage(new Image("data/maps/Map008.png").getScaledCopy(Global.width, Global.height), 0, 0);
		g.drawImage(new Image("data/chest/chest.png").getSubImage(0, 0, 48, 48),363, 785);
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 14);
        this.Warp2.warp(Global.P1, sbg, 14);
        dialogueBoxBranche.render(g);
        dialogueBoxChest.render(g);
        dialogueBoxArbre1.render(g);
        dialogueBoxArbre2.render(g);
        dialogueBoxArbre3.render(g);
        dialogueBoxArbre4.render(g);
        dialogueBoxArbre5.render(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet8.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxChest.draw(g);
		    this.dialogueBoxArbre1.draw(g);
		    this.dialogueBoxArbre2.draw(g);
		    this.dialogueBoxArbre3.draw(g);
		    this.dialogueBoxArbre4.draw(g);
		    this.dialogueBoxArbre5.draw(g);
		    this.tmpDialogbox1.renderTempDialgbox(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet8,delta,sbg);
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionsMapVide,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.dialogueBoxChest.dialogBox(i,gc.getInput());
		this.dialogueBoxArbre1.dialogBox(i);
		this.dialogueBoxArbre2.dialogBox(i);		
		this.dialogueBoxArbre3.dialogBox(i);	
		this.dialogueBoxArbre4.dialogBox(i);
		this.dialogueBoxArbre5.dialogBox(i);
		this.tmpDialogbox1.updateTempDialgbox(i, gc);


	}

	@Override
	public int getID() {
		return 18;
	}

}
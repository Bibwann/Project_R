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

public class Foret4 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	Warp Warp4;
	Warp Warp5;
	Warp Warp6;
	Warp Warp7;
	private DialogueBox dialogueBoxBranche;
	private DialogueBox dialogueBoxTonneau1;
	private DialogueBox dialogueBoxTonneau2;
	private DialogueBox dialogueBoxChest;
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
	
	public Foret4(int stateID) {
	}

	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(180, 0, 140, 10, 290, 1020);//H

		this.Warp2= new Warp(1000, 0, 140, 10, 1070, 1020);//H
		this.Warp3= new Warp(1200, 1070, 90, 10, 1220, 50);//BAS
		this.Warp4= new Warp(1600, 1070, 90, 10, 1650, 50);//BAS
		this.Warp5= new Warp(1910, 500, 10, 180, 50, 560);//D
		this.Warp6= new Warp(535, 200, 60, 45, 1105, 890);//GROTTE
		this.Warp7= new Warp(135, 905, 60, 45, 420, 210);//GROTTE
		
		if (Global.Chest1Map4) {
		    this.dialogueBoxChest = new DialogueBox(new String[] {
					"Vous obtenez : une potion de force.\n"+"\n"+"Votre force augmente de +2"
				});	
			this.dialogueBoxChest.setTriggerZone(587,250,80,70);
			Global.Chest1Map4=false;
		    }
		
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
  				"Ceci est une branche"
  	        });
  	    this.dialogueBoxBranche.setTriggerZone(1064, 452, 80, 90);
  	    
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
  	    
  	  //Dialogbox Avec choix multiples tonneau
		this.dialogueBoxTonneau1 = new DialogueBox(new String[] {
				"Le tonneau semble mal fermé"
	        });
	    this.dialogueBoxTonneau1.setTriggerZone(1717, 60, 60, 60);
	    
	    this.dialogueBoxTonneau1.setChoices(Arrays.asList("Ouvir", "Partir"), choice1 -> {
            switch (choice1) {
	            case 0:
	            	this.tmpDialogbox1.setActiveTempDialogbox(true);
	                this.tmpDialogbox1.setMessages(new String[] {"Il reste des graines de blé au fond du tonneau."+"\n"+"Vous obtenez : graines de blé"});
	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	                this.tmpDialogbox1.setChoices(Arrays.asList(),null);
                    break;
	                
	          case 1:
	            	this.tmpDialogbox1.setActiveTempDialogbox(false);
	            	break;
            }       
     });

	  //Dialogbox Avec choix multiples tonneau
	  		this.dialogueBoxTonneau2 = new DialogueBox(new String[] {
	  				"Le tonneau semble mal fermé"
	  	        });
	  	    this.dialogueBoxTonneau2.setTriggerZone(1717, 133, 60, 60);
	  	    
	  	    this.dialogueBoxTonneau2.setChoices(Arrays.asList("Ouvir", "Partir"), choice1 -> {
	              switch (choice1) {
	  	            case 0:
	  	            	this.tmpDialogbox1.setActiveTempDialogbox(true);
	  	                this.tmpDialogbox1.setMessages(new String[] {"Il reste des graines de blé au fond du tonneau."+"\n"+"Vous obtenez : graines de blé"});
	  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	  	                this.tmpDialogbox1.setChoices(Arrays.asList(),null);
	                      break;
	  	                
	  	          case 1:
	  	            	this.tmpDialogbox1.setActiveTempDialogbox(false);
	  	            	break;
	              }       
	       });
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.actualId = 14;
		
		g.drawImage(new Image("data/maps/Map004.png").getScaledCopy(Global.width, Global.height), 0, 0);
		g.drawImage(new Image("data/chest/chest.png").getSubImage(0, 0, 48, 48),616, 282);
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
        this.Warp6.warp(Global.P1, sbg, 26);
        this.Warp7.warp(Global.P1, sbg, 24);
        dialogueBoxBranche.render(g);
        dialogueBoxTonneau1.render(g);
        dialogueBoxTonneau2.render(g);
        dialogueBoxChest.render(g);
        
        

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet4.drawCollisions(g);
		    
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.Warp4.draw(g);
		    this.Warp5.draw(g);
		    this.Warp6.draw(g);
		    this.Warp7.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxTonneau1.draw(g);
		    this.dialogueBoxTonneau2.draw(g);
		    this.dialogueBoxChest.draw(g);
		    this.tmpDialogbox1.renderTempDialgbox(g);
	    	}
	    
//--------------------------------------------------------------------------------------------------------------------------
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet4,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.dialogueBoxTonneau1.dialogBox(i,gc.getInput());
		this.dialogueBoxTonneau2.dialogBox(i,gc.getInput());
		this.dialogueBoxChest.dialogBox(i);
		this.tmpDialogbox1.updateTempDialgbox(i, gc);

	}

	@Override
	public int getID() {
		return 14;
	}

}
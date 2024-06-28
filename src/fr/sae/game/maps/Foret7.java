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
import fr.sae.mobes.Chaton;

public class Foret7 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	private DialogueBox dialogueBoxCbt;
	private DialogueBox dialogueBoxchest;
	private DialogueBox dialogueBoxchest2;
	private DialogueBox dialogueBoxBranche;
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
	
	public Foret7(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1910, 0, 10, 70, 50, 60);//D
		this.Warp2= new Warp(1910, 840, 10, 130, 50, 900);//D
		this.Warp3= new Warp(0, 0, 1160, 10, 400, 1020);//HAUT
		
		this.dialogueBoxCbt = new DialogueBox(new String[] {
				"La gourmandise est un péché qui mène à la mort."
	        });
	    this.dialogueBoxCbt.setTriggerZone(0,630, 1000, 240);
	    
	    this.dialogueBoxCbt.setChoices(Arrays.asList("Continuer"), choice1 -> {
            switch (choice1) {
	            case 0:
	        	    this.dialogueBoxCbt.setTriggerZone(-1, -1, 0, 0);

                	this.tmpDialogbox1.setActiveTempDialogbox(false);
                	Global.Foret7Battle=false;
  	                Global.canMoovPlayer=false;
      			
  	                try {
						Global.mobs[0]=new Chaton("Chat-Rmeur", 2);
						Global.mobs[1]=new Chaton("Chat-Cale", 2);


  	                	} catch (SlickException e) {
  	                		e.printStackTrace();
  	                	}
  	                sbg.enterState(100);

	                
	                break;
	                
	            case 1:
	            	this.tmpDialogbox1.setActiveTempDialogbox(false);
	            	break;

            }       
     });
	    if (Global.Chest1Map7) {
	    this.dialogueBoxchest = new DialogueBox(new String[] {
				"Vous obtenez : un vieux parchemin déchiré.\n"+"\n"+"Le vieux parchemin déchiré est illisible..."
			});	
		this.dialogueBoxchest.setTriggerZone(395,909,70,50);
		Global.Chest1Map7=false;
	    }
	    
		if (Global.Chest2Map7) {
		this.dialogueBoxchest2 = new DialogueBox(new String[] {
				"Vous obtenez : une bague de sagesse.\n"+"\n"+"C'est beau, mais inutile..."
			});	
		this.dialogueBoxchest2.setTriggerZone(1584,508,70,60);
		Global.Chest2Map7=false;
		}
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
  				"Ceci est une branche"
  	        });
  	    this.dialogueBoxBranche.setTriggerZone(460, 505, 80, 90);
  	    
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
		Global.actualId = 17;
		
		g.drawImage(new Image("data/maps/Map007.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/chest/chest.png").getSubImage(0, 0, 48, 48),400, 900);
        g.drawImage(new Image("data/chest/chest.png").getSubImage(0, 0, 48, 48),1584, 508);

        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        
        if(Global.Foret7Battle) {
        	dialogueBoxCbt.render(g);
        }
        dialogueBoxchest.render(g);
        dialogueBoxchest2.render(g);
        dialogueBoxBranche.render(g);
        this.Warp1.warp(Global.P1, sbg, 13);
        this.Warp2.warp(Global.P1, sbg, 13);
        this.Warp3.warp(Global.P1, sbg, 15);
       
        

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
	    	
	    	if(Global.Foret7Battle) {
			    this.dialogueBoxCbt.draw(g);
			   
	        }

		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet7.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.dialogueBoxchest.draw(g);
		    this.dialogueBoxchest2.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.tmpDialogbox1.renderTempDialgbox(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.actualId=17;
		//Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet7,delta);
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet7,delta,sbg);
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionsMapVide,delta,sbg);

		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();


		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		
		
		
		
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }

		if(Global.Foret7Battle) {

			this.dialogueBoxCbt.dialogBox(i,gc.getInput());
		}
		
		this.dialogueBoxchest.dialogBox(i);
		this.dialogueBoxchest2.dialogBox(i);
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.tmpDialogbox1.updateTempDialgbox(i, gc);
	}

	@Override
	public int getID() {
		return 17;
	}

}
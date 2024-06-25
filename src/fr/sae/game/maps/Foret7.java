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
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
	
	public Foret7(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1910, 0, 10, 70, 50, 60);//D
		this.Warp2= new Warp(1910, 840, 10, 130, 50, 900);//D
		this.Warp3= new Warp(0, 0, 1160, 10, 400, 1020);//HAUT
		
		this.dialogueBoxCbt = new DialogueBox(new String[] {
				"\n "+
			    "     \n" +
			    "           Un chat vous agresse brutalement"
	        });
	    this.dialogueBoxCbt.setTriggerZone(0,630, 1000, 800);
	    
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
	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 17;
		g.drawImage(new Image("data/maps/Map007.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/chest/chest.png").getSubImage(0, 0, 48, 48),400, 900);

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
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.actualId=17;
		//Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet7,delta);
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet7,delta,sbg);
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
	}

	@Override
	public int getID() {
		return 17;
	}

}
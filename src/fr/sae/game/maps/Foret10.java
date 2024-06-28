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

public class Foret10 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	DialogueBox dialogueBoxBranche;
	DialogueBox dialogueBoxEpouventail;
	DialogueBox dialogueBoxGarde;
	DialogueBox dialogueBoxGarde2;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Foret10(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(0, 60 ,10 , 320, 1860, 210);//GAUCHE
		this.Warp2= new Warp(0, 740, 10, 200, 1860, 800);//GAUCHE
		this.Warp3= new Warp(1910, 500, 10, 130, 50, 620);//DROITE
		
		this.dialogueBoxGarde = new DialogueBox(new String[] {
					"Vous ne pouvez pas passer, vous devez d'abord être plus puissant pour poursuivre votre chemin. (niveau 10)\n"
					+ "Battez-vous ou explorez les environs à la recherche de coffres."
					
		});	
		this.dialogueBoxGarde.setTriggerZone(1833,508,20,130);

		//Dialogbox sans choix garde foret

		this.dialogueBoxGarde2 = new DialogueBox(new String[] {
					"Puisse le sort vous être favorable."
				    
		});	
		this.dialogueBoxGarde2.setTriggerZone(-1,-1,0,0);
	
		
		this.dialogueBoxEpouventail = new DialogueBox(new String[] {
				"L'épouvantail vous fixe étrangement..."
	        });
	    this.dialogueBoxEpouventail.setTriggerZone(796, 382, 86, 80);
	    
	    this.dialogueBoxEpouventail.setChoices(Arrays.asList("Frapper avec votre épée", "Continuer à Regarder"), choice1 -> {
            switch (choice1) {
	            case 0:
	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                this.tmpDialogbox2.setMessages(new String[] {"Aie !!! Que faites-vous, ignorant !!"});
	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                break;
	                
	                
	            case 1:
	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                this.tmpDialogbox2.setMessages(new String[] {
	                		"\n"+
	                		"    3      5\n"+
	                		"        2   \n"+
	                		"    4      1"
	               
	                });
	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	            	break;

            }       
     });
		
		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(912, 502, 86, 80);
	    
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
		Global.actualId = 20;
		g.drawImage(new Image("data/maps/Map010.png").getScaledCopy(Global.width, Global.height), 0, 0);
		g.drawImage(new Image("data/npc/Guts/GardeNoir3.png").getSubImage(0, 0, 48, 48),1833, 508);
		dialogueBoxBranche.render(g);
		dialogueBoxEpouventail.render(g);
		dialogueBoxGarde.render(g);
		dialogueBoxGarde2.render(g);

		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 13);
        this.Warp2.warp(Global.P1, sbg, 13);
        this.Warp3.warp(Global.P1, sbg, 21);
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

        
	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet10.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxEpouventail.draw(g);
		    this.dialogueBoxGarde.draw(g);
		    this.dialogueBoxGarde2.draw(g);
		    
		    
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet10,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Global.P1.canRandomBattle();
		Input input =gc.getInput();

		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.dialogueBoxEpouventail.dialogBox(i,gc.getInput());
		this.dialogueBoxGarde.dialogBox(i);
		this.dialogueBoxGarde2.dialogBox(i);
		
		this.tmpDialogbox2.updateTempDialgbox(i, gc);
	
	
	if ((Global.P1.getLevel() > 10)&& !Global.HaveHitLvl10){
		Global.CollisionMapForet10.deletLastCollidable();
		Global.HaveHitLvl10=true;
		this.dialogueBoxGarde.setTriggerZone(-1, -1, 0, 0);
		this.dialogueBoxGarde2.setTriggerZone(1833,508,20,130);
		
	}
	}
	@Override
	public int getID() {
		return 20;
	}

}
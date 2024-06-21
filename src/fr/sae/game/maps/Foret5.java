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

public class Foret5 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	DialogueBox dialogueBoxArbre;
	DialogueBox dialogueBoxBranche;
	DialogueBox dialogueBoxGuts;
	DialogueBox dialogueBoxGuts2;
	
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	
	public Foret5(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(0, 560, 10, Global.height-560, 1800, 860);//GAUCHE
		this.Warp2= new Warp(1910, 60, 10, 380, 40, 160);//DROITE
		this.Warp3= new Warp(0, 1070, 1160, 10, 400, 50);//BAS
		
		//Obligatoire que tmpDialogbox1 aie une triggerzone hors map
	    this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);
	    
	  //Dialogbox sans choix garde foret
	    
  		this.dialogueBoxGuts = new DialogueBox(new String[] {
  						"Attention !! \n"+
  						"Vous vous dirigez vers la dangereuse Lorêt Luminara et vous n'êtes pas assez fort  pour que je vous laisse passer.\n"+
  						"Revenez quand vous serez plus puissant."
  					    
  			});	
  		this.dialogueBoxGuts.setTriggerZone(961,579,110,110);
	    
  		//Dialogbox sans choix garde foret
	    
  		this.dialogueBoxGuts2 = new DialogueBox(new String[] {
  						"Attention !! \n"+
  						"Vous vous dirigez vers la dangereuse Lorêt Luminara et vous n'êtes pas assez fort  pour que je vous laisse passer.\n"+
  						"Revenez quand vous serez plus puissant."
  					    
  			});	
  		this.dialogueBoxGuts2.setTriggerZone(-1,-1,0,0);
	    
  		
	  //Dialogbox Avec choix multiples
  		this.dialogueBoxArbre = new DialogueBox(new String[] {
  				"\n "+
  			    "     \n" +
  			    "       Cet arbre ancien est solidement ancré dans le sol"
  	        });
  	    this.dialogueBoxArbre.setTriggerZone(1070, 370, 120, 100);
  	    
  	    this.dialogueBoxArbre.setChoices(Arrays.asList("Grimper", "Continuer votre chemin"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
  	                this.tmpDialogbox2.setMessages(new String[] {"Alors que vous grimpez dans l'arbre, un chat sauvage surgit des branches supérieur et vous attaque !"});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
  	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
                      break;
  	                
  	          case 1:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
  	            	break;
              }       
       });
	    
	    
	    
	  //Dialogbox Avec choix multiples
	  	this.dialogueBoxBranche = new DialogueBox(new String[] {
	  				"\n "+
	  			    "     \n" +
	  			    "           Ceci est une branche"
	  	        });
	  	    this.dialogueBoxBranche.setTriggerZone(120, 310, 80, 90);
	  	    
	  	    this.dialogueBoxBranche.setChoices(Arrays.asList("Taper la branche", "Ne rien faire"), choice1 -> {
	              switch (choice1) {
	  	            case 0:
	  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"           Aie !"});
	  	                //Ajoutez recursivement des choix ici de la meme maniere que moi

	  	                this.tmpDialogbox2.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
	  	                    switch (choice2) {

	  	                        case 0:
	  	                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
	  	                            this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"           AIEEEE !!!!!"});
	  	                            
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
		
		Global.actualId = 15;
		
		g.drawImage(new Image("data/maps/Map005.png").getScaledCopy(Global.width, Global.height), 0, 0);
		g.drawImage(new Image("data/npc/Guts/GardeNoir3.png").getSubImage(0, 0, 48, 48),954, 570);
		dialogueBoxBranche.render(g);
		dialogueBoxArbre.render(g);
		dialogueBoxGuts.render(g);
		dialogueBoxGuts2.render(g);
		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 11);//GAUCHE
        this.Warp2.warp(Global.P1, sbg, 12);//DROITE
        this.Warp3.warp(Global.P1, sbg, 17);//BAS
        
        //Obligatoire, premet de rendre a l'ecran la dialogbox temp quand elle est necessaire
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (true) {
		    g.draw(Global.P1.getHitbox());
		    Global.CollisionMapForet5.drawCollisions(g);
		    
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxArbre.draw(g);
		    this.dialogueBoxGuts.draw(g);
		    this.dialogueBoxGuts2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet5,delta);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();

		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
				
		//Dialogbox sans input --> sans choix
        this.dialogueBoxGuts.dialogBox(i);
        this.dialogueBoxGuts2.dialogBox(i);
		//Dialogbox avec input --> avec choix
		this.dialogueBoxBranche.dialogBox(i,gc.getInput());
		this.dialogueBoxArbre.dialogBox(i,gc.getInput());
		
		//Structure obligatoire qui check l'etat de la dialogbox temp
        this.tmpDialogbox2.updateTempDialgbox(i, gc);
        
      //----------------------------------------------------------------------------------------------------------------------------
    	//Temp
    		//Lance un combat de force -- present pour le debug a retirer
    		//if (Global.tmpVarLaunchBattleScene) {
    			//Global.canMoovPlayer=false;
    			//Global.tmpVarLaunchBattleScene=false;
    			//Global.mobs[0]=new Chaton("Chat-Pitaine", 1);
    			//Global.mobs[1]=new Chaton("Chat-Thon", 1);
    	        //sbg.enterState(100);

    		//}
    		
    //----------------------------------------------------------------------------------------------------------------------------
	if (Global.CoeurVaillant) {
		 
		this.dialogueBoxGuts.setTriggerZone(-1, -1, 0, 0);
		this.dialogueBoxGuts.setTriggerZone(961,579,110,110);
	}
	
	}

	@Override
	public int getID() {
		return 15;
	}

}
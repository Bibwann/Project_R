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

public class Underground3 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	DialogueBox dialogueBoxPot;
	DialogueBox dialogueBoxGrille;
	DialogueBox dialogueBoxPelle;
	DialogueBox dialogueBoxSquelette;
	DialogueBox dialogueBoxStele;
	DialogueBox dialogueBoxDragon;
	DialogueBox dialogueBoxTombe;
	
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	private DialogueBox dialgoboxLore;
	
	public Underground3(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1190, 120, 120, 10, 240, 1020);//HAUT
        this.Warp2= new Warp(140, 130, 50, 40, 350, 240);//CORDE
        this.Warp3= new Warp(990, 890, 70, 70, 550, 250);//ESCALIER
		
        
		//Obligatoire que tmpDialogbox1 aie une triggerzone hors map
	    this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);
	    
	    //Lore
	    this.dialgoboxLore = new DialogueBox(new String[] {
	            "Un chat vous attaque subitement !"
	        });
	    
	    this.dialgoboxLore.setChoices(Arrays.asList("Continuer"), choice1 -> {
            switch (choice1) {
	            case 0:	            
	            		
	                //Ajoutez recursivement des choix ici de la meme maniere que moi

	                        	this.tmpDialogbox2.setActiveTempDialogbox(false);
	                				Global.Underground3Battle=false;

	                    			Global.canMoovPlayer=false;
	                    			
	                				try {
										Global.mobs[0]=new Chaton("Chat-Tastrophe", 1);

	                					//Global.mobs[0]= Boss.getInstance();
									} catch (SlickException e) {
										e.printStackTrace();
									}
	                	        	sbg.enterState(100);
	                    		
	                            break;

	                        case 1:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(false);

	                    }
  
     });
     
	   
  		this.dialogueBoxStele = new DialogueBox(new String[] {
  				"Les arbres sont nos amis"
  					    
  			});	
  		this.dialogueBoxStele.setTriggerZone(1617,152,160,40);
  		
  		this.dialogueBoxDragon = new DialogueBox(new String[] {
  				"Le dragon vous regarde droit dans les yeux...\n"+"Vous détournez le regard après quelques secondes."
  					    
  			});	
  		this.dialogueBoxDragon.setTriggerZone(1677,861,80,40);
  		
  	
  		this.dialogueBoxTombe = new DialogueBox(new String[] {
  				"C'..t ... t..e pa.ti...ier ....our..n Nerox..u sa.. q....le clas....u ve.. jou.. ?"
  					    
  			});	
  		this.dialogueBoxTombe.setTriggerZone(1332,487,60,40);
	    
	  //Dialogbox sans choix du panneau
  		this.dialogueBoxPelle = new DialogueBox(new String[] {
  				"La pelle est trop lourde pour que vous puissiez la soulever."
  					    
  			});	
  		this.dialogueBoxPelle.setTriggerZone(120,780,60,60);
	    
  		
  	//Dialogbox sans choix du panneau
  		this.dialogueBoxSquelette = new DialogueBox(new String[] {
  				"Un étrange squelette gît sous vos pieds..."
  					    
  			});	
  		this.dialogueBoxSquelette.setTriggerZone(800,260,60,60);
	    
	  //Dialogbox Avec choix multiples
  		this.dialogueBoxGrille = new DialogueBox(new String[] {
  			    "La pièce est sombre et vous ne distinguez rien derrière cette grille."
  	        });
  	    this.dialogueBoxGrille.setTriggerZone(395, 130, 465, 10);
  	    
  	    this.dialogueBoxGrille.setChoices(Arrays.asList("Passer la main","Reculer"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
  	                this.tmpDialogbox2.setMessages(new String[] {
  	                		 "Vous dérangez un rat qui s'enfuit en courant."});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
  	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
  	          case 1:
	            	
  	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
  	            	break;

              }       
       });

	    //Dialogbox Avec choix multiples
  		this.dialogueBoxPot = new DialogueBox(new String[] {
  				"Il semble y avoir quelque chose de gluant au fond du sceau."
  	        });
  	    this.dialogueBoxPot.setTriggerZone(790, 140, 80, 70);
  	    
  	    this.dialogueBoxPot.setChoices(Arrays.asList("Prendre", "Laisser"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	if(!Global.CoeurVaillant) {
	  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	  	                this.tmpDialogbox2.setMessages(new String[] {"Vous tenez un coeur froid dans votre main.\n"  + 
	  	                		"Étonnamment, ce cœur bat toujours faiblement."});
	  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	  	                this.tmpDialogbox2.setChoices(Arrays.asList("Ranger le coeur dans votre sac", "Déposer le coeur dans le sceau"), choice2 -> {
		                    switch (choice2) {
	
		                        case 0:
		                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
		                            this.tmpDialogbox2.setMessages(new String[] {"Vous obtenez : coeur vaillant.\n"
		                            		+ "Vous débordez de confiance et semblez prêt à conquérir le monde."});
		                            if (!Global.CoeurVaillant) {
			                            Global.CollisionMapForet5.deletLastCollidable();
		                            }
		                            Global.CoeurVaillant=true;
	
		                            
		                            //Permet de dire qu'il s'agissait du dernier choix
		                            this.tmpDialogbox2.setChoices(Arrays.asList(),null);
		                            break;
	
		                        case 1:
		                        	this.tmpDialogbox2.setActiveTempDialogbox(false);

		                    }
		                    
		                });
  	            	} else {
  	            		this.tmpDialogbox2.setActiveTempDialogbox(true);
                        this.tmpDialogbox2.setMessages(new String[] {"Ce sceau est vide"});
                
                        //Permet de dire qu'il s'agissait du dernier choix
                        this.tmpDialogbox2.setChoices(Arrays.asList(),null);
  	            	}
	                break;
  	                
  	            case 1:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
  	            	break;

              }       
       });
	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 26;
		g.drawImage(new Image("data/maps/Map016.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
		dialogueBoxPot.render(g);
		dialogueBoxGrille.render(g);
		dialogueBoxPelle.render(g);
		dialogueBoxSquelette.render(g);
		dialogueBoxTombe.render(g);
		dialogueBoxStele.render(g);
		dialogueBoxDragon.render(g);
		
		if (Global.Underground3Battle) {
            this.dialgoboxLore.renderForceDialogbox(g);
        }
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 24);
        this.Warp2.warp(Global.P1, sbg, 16);//GROTTE SORTIE
        this.Warp3.warp(Global.P1, sbg, 14);//GROTTE SORTIE 2
      //Obligatoire, premet de rendre a l'ecran la dialogbox temp quand elle est necessaire
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapUnderground3.drawCollisions(g);
		    
		    this.dialogueBoxPot.draw(g);
		    this.dialogueBoxGrille.draw(g);
		    this.dialogueBoxPelle.draw(g);
		    this.dialogueBoxSquelette.draw(g);
		    this.dialogueBoxTombe.draw(g);
			this.dialogueBoxStele.draw(g);
			this.dialogueBoxDragon.draw(g);
		    
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapUnderground3,delta,sbg);
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionsMapVide,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		
		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
		
		if(Global.Underground3Battle) {
            this.dialgoboxLore.forceDialogBox(i, gc.getInput());

		}
		//Dialogbox sans input --> sans choix
        this.dialogueBoxPelle.dialogBox(i);
        this.dialogueBoxSquelette.dialogBox(i);
        this.dialogueBoxDragon.dialogBox(i);
        this.dialogueBoxTombe.dialogBox(i);
        this.dialogueBoxStele.dialogBox(i);
        
		this.dialogueBoxPot.dialogBox(i,gc.getInput());
		this.dialogueBoxGrille.dialogBox(i,gc.getInput());
		
		//Structure obligatoire qui check l'etat de la dialogbox temp
        this.tmpDialogbox2.updateTempDialgbox(i, gc);
	}

	@Override
	public int getID() {
		return 26;
	}

}
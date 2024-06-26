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

public class Foret6 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	DialogueBox dialogueBoxBranche;
	DialogueBox dialogueBoxPuits;
	DialogueBox dialogueBoxPot;
	DialogueBox dialogueBoxTonneau;
	DialogueBox dialogueBoxTombe;
	DialogueBox dialogueBoxTombe2;
	DialogueBox dialogueBoxTombe3;
	DialogueBox dialogueBoxTombe4;
	
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	
	public Foret6(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(860, 1070, 470, 10, 1100, 50);
		this.Warp2= new Warp(330, 130, 60, 60, 150, 210);
		
		//Obligatoire que tmpDialogbox1 aie une triggerzone hors map
	    this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);
	
	    
	  //Dialogbox sans choix de la tombe
	  		this.dialogueBoxTombe = new DialogueBox(new String[] {
	  				"\n "+
	  					    "     \n" +
	  					    "                           Ici repo.. Ma..us le bar..deur\n"+
	  					    " \n " +
	  					    " \n " +
	  					    "                                        5521 - 5567"
	  					    
	  			});	
	  		this.dialogueBoxTombe.setTriggerZone(930,190,66,20);
	    
	  	//Dialogbox sans choix de la tombe
	  		this.dialogueBoxTombe2 = new DialogueBox(new String[] {
	  				"\n "+
	  					    "     \n" +
	  					    "                           Ici re.... Sar.. la foug..re ah.ha..\n"+
	  					    " \n " +
	  					    " \n " +
	  					    "                                        5567 - 56.."
	  					    
	  			});	
	  		this.dialogueBoxTombe2.setTriggerZone(270,510,60,10);
	    
	  		
	  	//Dialogbox sans choix de la tombe
	  		this.dialogueBoxTombe3 = new DialogueBox(new String[] {
	  				"\n "+
	  					    "     \n" +
	  					    "                           Ici r..ose Lem..ne le boss\n"+
	  					    " \n " +
	  					    " \n " +
	  					    "                                       54.. - .550"
	  					    
	  			});	
	  		this.dialogueBoxTombe3.setTriggerZone(400,250,66,20);
	    
	  	//Dialogbox sans choix de la tombe
	  		this.dialogueBoxTombe4= new DialogueBox(new String[] {
	  				"\n "+
	  					    "     \n" +
	  					    "                           Ici repose Matthieu l'alch...ste\n"+
	  					    " \n " +
	  					    " \n " +
	  					    "                                        5534 - 5580"
	  					    
	  			});	
	  		this.dialogueBoxTombe4.setTriggerZone(535,570,60,10);
	    
		//Dialogbox Avec choix multiples
	  		this.dialogueBoxPuits = new DialogueBox(new String[] {
	                "Vous ne voyez pas le fond du puits"
	            });
	        this.dialogueBoxPuits.setTriggerZone(1517, 370, 76, 90);

	        this.dialogueBoxPuits.setChoices(Arrays.asList("Sauter", "Crier", "Partir"), choice1 -> {
	            switch (choice1) {
	                case 1:
	                    this.tmpDialogbox2.setActiveTempDialogbox(true);
	                    this.tmpDialogbox2.setMessages(new String[] {"Votre voie résonne mais cela semble peu profond"});
	                    //Ajoutez recursivement des choix ici de la meme maniere que moi
	                    this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                    break;

	                case 0:
	                    this.tmpDialogbox2.setActiveTempDialogbox(false);
	                    Global.P1.getHitbox().setX(150); //coordonnées tp
	                    Global.P1.getHitbox().setY(210);
	                    sbg.enterState(26);

	                    break;


	                case 2:
	                    this.tmpDialogbox2.setActiveTempDialogbox(false);
	                    break;

	            }
	     });
	    
	    
	    
	  //Dialogbox Avec choix multiples
	  		this.dialogueBoxTonneau = new DialogueBox(new String[] {
	  				"\n "+
	  			    "     \n" +
	  			    "           Le tonneau semble mal fermé"
	  	        });
	  	    this.dialogueBoxTonneau.setTriggerZone(400, 815, 60, 30);
	  	    
	  	    this.dialogueBoxTonneau.setChoices(Arrays.asList("Ouvir", "Partir"), choice1 -> {
	              switch (choice1) {
	  	            case 0:
	  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"  Il reste des graines de blés au fond du tonneau"});
	  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	  	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                      break;
	  	                
	  	          case 1:
	  	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
	  	            	break;
	              }       
	       });
	    
	    
	    
	    
	  //Dialogbox Avec choix multiples
	  		this.dialogueBoxPot = new DialogueBox(new String[] {
	  				"\n "+
	  			    "     \n" +
	  			    "           Il s'agit d'un sceau"
	  	        });
	  	    this.dialogueBoxPot.setTriggerZone(1320, 180, 76, 90);
	  	    
	  	    this.dialogueBoxPot.setChoices(Arrays.asList("Fouiller", "Laisser"), choice1 -> {
	              switch (choice1) {
	  	            case 0:
	  	            	if (Global.Foret6Battle) {	
	  	            
	  	            		
	  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"  Un chat se cache dans le sceau"});
	  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	  	                this.tmpDialogbox2.setChoices(Arrays.asList("Carresser le chat", "Partir"), choice2 -> {
		                    switch (choice2) {

		                        case 0:
		                        	this.tmpDialogbox2.setActiveTempDialogbox(true);

		                        	this.tmpDialogbox2.setActiveTempDialogbox(false);
		                        	
		                    			Global.canMoovPlayer=false;
		                    			
		                				try {
											Global.mobs[0]=new Chaton("Chat-Pitaine", 1);
			                				Global.mobs[1]=new Chaton("Chat-Thon", 1);

		                					//Global.mobs[0]= Boss.getInstance();
										} catch (SlickException e) {
											e.printStackTrace();
										}
		                	        	sbg.enterState(100);

		                    			Global.Foret6Battle=false;

		                            break;

		                        case 1:
		                        	this.tmpDialogbox2.setActiveTempDialogbox(false);

		                    }
		                    
		                });
	  	                
	  	            	}else {
		  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
		  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"  Il y a un cadavre de chat ..."});
		  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
		  	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	  	            	}
	  	            	
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
	    this.dialogueBoxBranche.setTriggerZone(985, 690, 86, 80);
	    
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
		Global.actualId = 16;
		
		g.drawImage(new Image("data/maps/Map006.png").getScaledCopy(Global.width, Global.height), 0, 0);
		dialogueBoxBranche.render(g);
		dialogueBoxPuits.render(g);
		dialogueBoxPot.render(g);
		dialogueBoxTonneau.render(g);
		dialogueBoxTombe.render(g);
		dialogueBoxTombe2.render(g);
		dialogueBoxTombe3.render(g);
		dialogueBoxTombe4.render(g);
		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 11);
        this.Warp2.warp(Global.P1, sbg, 26);//GROTTE
      //Obligatoire, premet de rendre a l'ecran la dialogbox temp quand elle est necessaire
        this.tmpDialogbox2.renderTempDialgbox(g);

        
//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (false) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet6.drawCollisions(g);
		    
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxPuits.draw(g);
		    this.dialogueBoxPot.draw(g);
		    this.dialogueBoxTonneau.draw(g);
		    this.dialogueBoxTombe.draw(g);
		    this.dialogueBoxTombe2.draw(g);
		    this.dialogueBoxTombe3.draw(g);
		    this.dialogueBoxTombe3.draw(g);
		    
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input input =gc.getInput();
		
		Global.updatePlayerMovement(input,Global.CollisionMapForet6,delta);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();

		
		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
	
		
		//Dialogbox sans input --> sans choix
        this.dialogueBoxTombe.dialogBox(i);
        this.dialogueBoxTombe2.dialogBox(i);
        this.dialogueBoxTombe3.dialogBox(i);
        this.dialogueBoxTombe4.dialogBox(i);
        
		//Dialogbox avec input --> avec choix
        this.dialogueBoxBranche.dialogBox(i,gc.getInput());
        this.dialogueBoxPuits.dialogBox(i,gc.getInput());
        this.dialogueBoxPot.dialogBox(i,gc.getInput());
        this.dialogueBoxTonneau.dialogBox(i,gc.getInput());
        
        //Structure obligatoire qui check l'etat de la dialogbox temp
        this.tmpDialogbox2.updateTempDialgbox(i, gc);
	}

	@Override
	public int getID() {
		return 16;
	}

}
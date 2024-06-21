package fr.sae.game.maps;

import org.newdawn.slick.state.BasicGameState;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;
import fr.sae.mobes.Chaton;

public class Foret1 extends BasicGameState{
	
	Warp Warp1;
	Warp Warp2;
	//Warp Warp3;
	DialogueBox dialogueBoxPanneau; 
    DialogueBox dialogueBoxBranche;
    DialogueBox dialogueBoxFenetre;
    DialogueBox dialogueBoxMage;
    
	private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});

	public Foret1(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//Obligatoire que tmpDialogbox1 aie une triggerzone hors map
	    this.tmpDialogbox1.setTriggerZone(-1, -1, 0, 0);

		this.Warp1= new Warp(Global.width-10, Global.height-320, 10, 320, 40, 860);
		this.Warp2= new Warp(860, 0, 530, 10, 1000, 1020);
		//this.Warp3= new Warp(500, 500, 60, 60, 150, 210);

//-----------------------------------------------------------------------------------------------------------------------
//Ici se trouve l'horreur des dialogbox --> ces dialogbox servent d'exemples
	    
	    //Dialogbox sans choix du panneau
		this.dialogueBoxPanneau = new DialogueBox(new String[] {
				"\n" +
			    "  ^  \n" +
			    "  |         Cimetierre\n" +
			    "  |  \n",
			    "\n" +
			    "\n" +
			    " -- -- >       Château Ensolleilé\n"+
			    "\n"
			});	
		this.dialogueBoxPanneau.setTriggerZone(Global.width-530,570,66,10);

		//Dialogbox sans choix du mage
				this.dialogueBoxMage = new DialogueBox(new String[] {
						"Arghhh... Je mes forces me quitter peu a peu... \n"+
				"Je vous en conjure, aidez moi et apportez ce précieux objet aux membres de la famil-le royale qui resiste encore dans le Château Ensolleilé... \n"+
								"\n"+
				"Vous obtenez un paquet étrange \n"+
								"\n"+
				"Merci... Jeune aventurier... Je peux partir l'esprit serein..."
					});	
				this.dialogueBoxMage.setTriggerZone(580,505,70,40);
		
		//Dialogbox Avec choix multiples
				this.dialogueBoxFenetre = new DialogueBox(new String[] {
						"\n "+
					    "     \n" +
					    "           Vous distinguez une ombre imposante derrière le rideau"
			        });
			    this.dialogueBoxFenetre.setTriggerZone(400, 120, 60, 80);
			    
			    this.dialogueBoxFenetre.setChoices(Arrays.asList("Toquer à la fenêtre", "Partir"), choice1 -> {
		            switch (choice1) {
			            case 0:
			            	this.tmpDialogbox1.setActiveTempDialogbox(true);
			                this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"  L'ombre semble se déplace et se dirige vers l'armoire situé au fond de la piéce"});
			                //Ajoutez recursivement des choix ici de la meme maniere que moi

			                this.tmpDialogbox1.setChoices(Arrays.asList("Frapper plus fort contre la fenêtre", "Se retourner et partir en courant"), choice2 -> {
			                    switch (choice2) {

			                        case 0:
			                        	this.tmpDialogbox1.setActiveTempDialogbox(true);
			                            this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"      Une voie s'élève derrière la vitre : Cessez Immediatement !! "});
			                            
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
		
		


		//Dialogbox Avec choix multiples
		this.dialogueBoxBranche = new DialogueBox(new String[] {
				"\n "+
			    "     \n" +
			    "           Ceci est une branche"
	        });
	    this.dialogueBoxBranche.setTriggerZone(718, 440, 86, 80);
	    
	    this.dialogueBoxBranche.setChoices(Arrays.asList("Taper la branche", "Ne rien faire"), choice1 -> {
            switch (choice1) {
	            case 0:
	            	this.tmpDialogbox1.setActiveTempDialogbox(true);
	                this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           Aie !"});
	                //Ajoutez recursivement des choix ici de la meme maniere que moi

	                this.tmpDialogbox1.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
	                    switch (choice2) {

	                        case 0:
	                        	this.tmpDialogbox1.setActiveTempDialogbox(true);
	                            this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           AIEEEE !!!!!"});
	                            
	                            Global.egg1="Special Thanks: Sarah Barlatier";
	                            
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
		//sbg.enterState(12);
		Global.actualId = 11;
		
		//Fait les rendu des modeles
        g.drawImage(new Image("data/maps/Map001.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/npc/MageMourant/MageMourant4.png").getSubImage(512-64, 320, 64, 64),598, 475);
        
        dialogueBoxPanneau.render(g);
        dialogueBoxMage.render(g);
        dialogueBoxBranche.render(g);
        dialogueBoxFenetre.render(g);


        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();

        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        
		this.Warp1.warp(Global.P1, sbg, 15);
		this.Warp2.warp(Global.P1, sbg, 16);
		//this.Warp3.warp(Global.P1, sbg, 26);//TEST GROTTE TP
        //Obligatoire, premet de rendre a l'ecran la dialogbox temp quand elle est necessaire
        this.tmpDialogbox1.renderTempDialgbox(g);
        
//--------------------------------------------------------------------------------------------------------------------------
	//Temp	     

	    //Affiche toutes les collisions de la map et du joueur
	    if (true) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet1.drawCollisions(g);
		    this.dialogueBoxMage.draw(g);
		    this.dialogueBoxPanneau.draw(g);
		    this.dialogueBoxBranche.draw(g);
		    this.dialogueBoxFenetre.draw(g);

		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    //this.Warp3.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------

		}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		
		Global.updatePlayerMovement(input, Global.CollisionMapForet1,delta);
		Global.P1.AnimateWhileMoove();
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		
		
		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
		
		//Dialogbox sans input --> sans choix
        this.dialogueBoxPanneau.dialogBox(i);
        this.dialogueBoxMage.dialogBox(i);
		//Dialogbox avec input --> avec choix
        this.dialogueBoxBranche.dialogBox(i,gc.getInput());
        this.dialogueBoxFenetre.dialogBox(i,gc.getInput());
        
        //Structure obligatoire qui check l'etat de la dialogbox temp
        this.tmpDialogbox1.updateTempDialgbox(i, gc);
                
	}

	@Override
	public int getID() {
		return 11;
	}

}

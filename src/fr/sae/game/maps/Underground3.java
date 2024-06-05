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

public class Underground3 extends BasicGameState {
	//Warp Warp1;
	Warp Warp2;
	DialogueBox dialogueBoxPot;
	DialogueBox dialogueBoxGrille;
	DialogueBox dialogueBoxPelle;
	DialogueBox dialogueBoxSquelette;
	
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	
	public Underground3(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//this.Warp1= new Warp(860, 1070, 470, 10, 1100, 50);
		this.Warp2= new Warp(140, 130, 50, 40, 350, 240);
		
		//Obligatoire que tmpDialogbox1 aie une triggerzone hors map
	    this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);
	    
	    
	  //Dialogbox sans choix du panneau
  		this.dialogueBoxPelle = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    "         La pelle est trop loudre pour que vous la souleviez"
  					    
  			});	
  		this.dialogueBoxPelle.setTriggerZone(120,780,60,60);
	    
  		
  	//Dialogbox sans choix du panneau
  		this.dialogueBoxSquelette = new DialogueBox(new String[] {
  				"\n "+
  					    "     \n" +
  					    " Un squelette inanimé et plutot bien preservé malgrè son grand âge git sous vos    pieds "
  					    
  			});	
  		this.dialogueBoxSquelette.setTriggerZone(800,260,60,60);
	    
	  //Dialogbox Avec choix multiples
  		this.dialogueBoxGrille = new DialogueBox(new String[] {
  			    "Dans l'obscurité de la piéce, vous dinstinguez difficilement derrière les barreaux un rat mangeant une graine de blé sur la gauche.\n"+"Après un certain temps, vous remarquez enfin une masse frèle assise contre le mur  de droite. "
  	        });
  	    this.dialogueBoxGrille.setTriggerZone(395, 130, 465, 10);
  	    
  	    this.dialogueBoxGrille.setChoices(Arrays.asList("Passer la main et toucher le rat", "Passer la main et toucher la silhouette à droite","Reculer"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"Le rat s'enfuie en courant vers la droite...\n"+"Une main surgit alors de la silhouette et attrappe sauvagement le rat qui disparait de votre champs de vision."});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
  	                this.tmpDialogbox2.setChoices(Arrays.asList("Tendre la main vers la silhouette", "Partir"), choice2 -> {
	                    switch (choice2) {

	                        case 0:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                            this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"Alors que vous passez votre main entre 2 barreaux, un courant d'air frais travers  la pièce et fait glisser le voile qui cachait un simple squelette. "+ "\n"+ "Vous reconnaissez le cadavre d'un rat tout récent sur les ossements découverts" });
	                            
	                            //Permet de dire qu'il s'agissait du dernier choix
	                            this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                            break;

	                        case 1:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(false);
	                        	
	                    }
	                    
	                });
  	              break;
  	          case 1:
	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"     Vous touchez quelque chose de dur dans la pénombre, mais rien ne se passe"});
	                //Ajoutez recursivement des choix ici de la meme maniere que moi
	                this.tmpDialogbox2.setChoices(Arrays.asList("Tendre la main vers le rat", "Partir"), choice3 -> {
	                    switch (choice3) {

	                        case 0:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                            this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"    Alors que vous passez votre main entre 2 barreaux, le rat court au fond de la  pièce et s'enfuis par un trou dans le mur" });
	                            
	                            //Permet de dire qu'il s'agissait du dernier choix
	                            this.tmpDialogbox2.setChoices(Arrays.asList(),null);
	                            break;

	                        case 1:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(false);

	                    }
	                    
	                }); 
	                
	                break;
  	                
  	            case 2:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(false);
  	            	break;
  	                
  	                
  	            
  	            	

              }       
       });
	    
	    
	    
	    //Dialogbox Avec choix multiples
  		this.dialogueBoxPot = new DialogueBox(new String[] {
  				"\n "+
  			    "     \n" +
  			    "         Il semble se trouver quelque chose de gluant au fond du sceau"
  	        });
  	    this.dialogueBoxPot.setTriggerZone(790, 140, 80, 70);
  	    
  	    this.dialogueBoxPot.setChoices(Arrays.asList("Prendre", "Laisser"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
  	                this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"     Vous tenez un coeur froid dans votre main.\n"+ "\n"+"\n"+"     Etonnament ce coeur bat toujours faiblement."});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
  	                this.tmpDialogbox2.setChoices(Arrays.asList("Ranger le coeur dans votre sac", "Deposer le coeur dans le sceau"), choice2 -> {
	                    switch (choice2) {

	                        case 0:
	                        	this.tmpDialogbox2.setActiveTempDialogbox(true);
	                            this.tmpDialogbox2.setMessages(new String[] {"\n"+"\n"+"    +1 coeur vaillant dans l'inventaire"});
	                            
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
		
		g.drawImage(new Image("data/maps/Map016.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
		dialogueBoxPot.render(g);
		dialogueBoxGrille.render(g);
		dialogueBoxPelle.render(g);
		dialogueBoxSquelette.render(g);
		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        //this.Warp1.warp(Global.P1, sbg, 11);
        this.Warp2.warp(Global.P1, sbg, 16);//GROTTE SORTIE
      //Obligatoire, premet de rendre a l'ecran la dialogbox temp quand elle est necessaire
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (false) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapUnderground3.drawCollisions(g);
		    
		    this.dialogueBoxPot.draw(g);
		    this.dialogueBoxGrille.draw(g);
		    this.dialogueBoxPelle.draw(g);
		    this.dialogueBoxSquelette.draw(g);
		    
		    //this.Warp1.draw(g);
		    this.Warp2.draw(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapUnderground3,delta);
		Global.P1.AnimateWhileMoove();
		
		//Structure obligatoire pour les dialogbox sinon ca marche po jsp pk
		boolean i =input.isKeyPressed(Global.interract);
		
		//Dialogbox sans input --> sans choix
        this.dialogueBoxPelle.dialogBox(i);
        this.dialogueBoxSquelette.dialogBox(i);
        
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
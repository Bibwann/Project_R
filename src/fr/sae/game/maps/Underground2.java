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

public class Underground2 extends BasicGameState {
	Warp Warp1;
	Warp Warp2;
	Warp Warp3;
	DialogueBox dialogueBoxStele;
	DialogueBox dialogueBoxTombe;
	DialogueBox dialogueBoxMiroir;
	DialogueBox dialogueBoxTonneau;
	DialogueBox dialogueBoxTable;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Underground2(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(1610, 1070, 120, 10, 1670, 150);
		this.Warp2= new Warp(210, 130, 50, 40, 890, 750);
		this.Warp3= new Warp(1860, 130, 50, 40, 140, 100);

		
		this.dialogueBoxStele = new DialogueBox(new String[] {
  				"Les épouvantails sont plus bavards s'ils sont bien traités."
  					    
  			});	
  		this.dialogueBoxStele.setTriggerZone(1559,190,190,20);
  		
  		this.dialogueBoxMiroir = new DialogueBox(new String[] {
  				"Le miroir est cassé, il ne reflète rien."
  					    
  			});	
  		this.dialogueBoxMiroir.setTriggerZone(1058,172,66,20);
  		
  		this.dialogueBoxTonneau = new DialogueBox(new String[] {
  				"Ces tonneaux sont fermés depuis longtemps, de la mousse s'est formée dessus."
  					    
  			});	
  		this.dialogueBoxTonneau.setTriggerZone(523,180,180,150);
  		
  		this.dialogueBoxTable = new DialogueBox(new String[] {
  				"Une carte de la forêt est posée sur la table, mais des traces de griffes l'ont rendue illisible."
  					    
  			});	
  		this.dialogueBoxTable.setTriggerZone(125,606,120,40);
		
		
		this.dialogueBoxTombe = new DialogueBox(new String[] {
  				"Cette tombe surplombe un grand trou."
  	        });
  	    this.dialogueBoxTombe.setTriggerZone(855, 233, 76, 140);
  	    
  	    this.dialogueBoxTombe.setChoices(Arrays.asList("Lire la tombe", "Sauter"), choice1 -> {
              switch (choice1) {
  	            case 0:
  	            	 {	
  	            	this.tmpDialogbox2.setActiveTempDialogbox(true);
  	                this.tmpDialogbox2.setMessages(new String[] {"La chance sourit aux audacieux :)"});
  	                //Ajoutez recursivement des choix ici de la meme maniere que moi
  	                this.tmpDialogbox2.setChoices(Arrays.asList(),null);
  	            	}
  	            	
	                break;
  	              
  	            case 1:
  	            	sbg.enterState(7);
  	            	
  	            	break;
  	                
  	                
  	            
  	            	

              }       
       });

	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 25;
		g.drawImage(new Image("data/maps/Map015.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 24);// BAS
        this.Warp2.warp(Global.P1, sbg, 12);// ECHELLE
        
        this.Warp3.warp(Global.P1, sbg, 13);// ECHELLE DROITE
        dialogueBoxStele.render(g);
        dialogueBoxTombe.render(g);
        dialogueBoxMiroir.render(g);
        dialogueBoxTonneau.render(g);
        dialogueBoxTable.render(g);
//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapUnderground2.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.Warp2.draw(g);
		    this.Warp3.draw(g);
		    this.dialogueBoxStele.draw(g);
		    this.dialogueBoxTombe.draw(g);
		    this.dialogueBoxMiroir.draw(g);
		    this.dialogueBoxTonneau.draw(g);
		    this.dialogueBoxTable.draw(g);
		    this.tmpDialogbox2.renderTempDialgbox(g);
	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapUnderground2,delta,sbg);
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxStele.dialogBox(i);
		this.dialogueBoxTombe.dialogBox(i,gc.getInput());
		this.dialogueBoxTonneau.dialogBox(i);
		this.dialogueBoxMiroir.dialogBox(i);
		this.dialogueBoxTable.dialogBox(i);
		this.tmpDialogbox2.updateTempDialgbox(i, gc);
		
	}

	@Override
	public int getID() {
		return 25;
	}

}
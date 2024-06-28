package fr.sae.game.maps;

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

public class Foret9 extends BasicGameState {
	Warp Warp1;
	DialogueBox dialogueBoxMagasin;
	DialogueBox dialogueBoxPanneau;
	private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});
	public Foret9(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.Warp1= new Warp(0, 500, 10, 150, 1860, 560);
		this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);
		
		this.dialogueBoxMagasin = new DialogueBox(new String[] {
  				"La porte de la tente est fermée."
  					    
  			});	
  		this.dialogueBoxMagasin.setTriggerZone(787,551,180,25);
  		
  		this.dialogueBoxPanneau = new DialogueBox(new String[] {
  				"Horaires : 7j/7 de 2h00 à 8h00"
  					    
  			});	
  		this.dialogueBoxPanneau.setTriggerZone(986,551,70,25);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Global.actualId = 19;
		g.drawImage(new Image("data/maps/Map009.png").getScaledCopy(Global.width, Global.height), 0, 0);
		dialogueBoxPanneau.render(g);
		dialogueBoxMagasin.render(g);
		
        try {
	    	Global.P1.Sprite(g);
	    	Global.P1.getAnimation().stop();
	    	
        }catch(Exception e) {	
        	e.getMessage();
        	System.out.print(e);
        }
        this.Warp1.warp(Global.P1, sbg, 14);
        this.tmpDialogbox2.renderTempDialgbox(g);

//--------------------------------------------------------------------------------------------------------------------------
	//Temp	    

	    //Affiche toutes les collisions de la map et du joueur
	    if (Global.AfficherToutesLesCollisions) {
		    g.draw(Global.P1.getHitbox());
		    
		    Global.CollisionMapForet9.drawCollisions(g);
		    this.Warp1.draw(g);
		    this.dialogueBoxMagasin.draw(g);
		    this.dialogueBoxPanneau.draw(g);

	    	}
//--------------------------------------------------------------------------------------------------------------------------
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet9,delta,sbg);
		Input input =gc.getInput();
		

		
		if (gc.getInput().isKeyPressed(Global.pause)) {
            sbg.enterState(101); // Passer à l'état 101 (menu de pause)
        }
		Global.P1.AnimateWhileMoove();
		Global.P1.cannotRandomBattle();
		boolean i =input.isKeyPressed(Global.interract);
		this.dialogueBoxMagasin.dialogBox(i);
		this.dialogueBoxPanneau.dialogBox(i);
		this.tmpDialogbox2.updateTempDialgbox(i, gc);
		
	}

	@Override
	public int getID() {
		return 19;
	}

}
package fr.sae.warp;

import org.newdawn.slick.state.BasicGameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.EntityAnimations;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;

public class Taverne extends BasicGameState{

	public Taverne(int stateID) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	    Shape hitbox = Global.P1.getHitbox();
	    g.draw(hitbox);	
		}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	    Input input = gc.getInput();
	    
	    if (Global.P1 != null && Global.canMoovPlayer) {
	        if (input.isKeyDown(Input.KEY_LEFT)) {
	            Global.P1.moveLeft(1);
	        }
	        
	        if (input.isKeyDown(Input.KEY_RIGHT)) {
	            Global.P1.moveRight(1);
	        }
	        
	        if (input.isKeyDown(Input.KEY_UP)) {
	            Global.P1.moveUp(1);
	        }
	        
	        if (input.isKeyDown(Input.KEY_DOWN)) {
	            Global.P1.moveDown(1);
	        }
	    }
	}

	@Override
	public int getID() {
		return 7;
	}

}

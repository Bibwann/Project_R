package fr.sae.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Mobs;
import fr.sae.game.caractere.Player;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;

    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // Dessin des éléments de la scène de combat

        // Dessin des joueurs à gauche
    	
    	try {
	    		
	    	Global.P1.BattleScene(g, 200);
	    	Global.P2.BattleScene(g, 300);
    	} catch(Exception e) {
    		e.getMessage();
    	}
    	
    	
        // Dessin des ennemis
    	try {
	        for (int i = 0; i < this.enemy.length; i++) {
	        	
	        	if (this.enemy[i] == null) {
	                continue;
	            }
	
	            g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
	        }
    	} catch(Exception e) {
    		e.getMessage();
    	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public int getID() {
        return 100;
    }
}

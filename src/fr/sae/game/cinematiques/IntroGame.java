package fr.sae.game.cinematiques;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;

public class IntroGame extends BasicGameState {
	
	public IntroGame(int stateID) {
	}
	
    private StateBasedGame game;


	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;				
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Global.inCinematique=true;
		Global.InitializeGame();//Initialise les variables sur leurs valeur par default
		
		game.enterState(7); //      -->	Pour finir l'intro
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 5;
	}

}

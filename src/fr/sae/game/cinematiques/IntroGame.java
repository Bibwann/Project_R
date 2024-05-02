package fr.sae.game.cinematiques;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class IntroGame extends BasicGameState {
    private Image backgroundImage;
    private float opacity = 0f;
    private boolean fadeInComplete = false;

    public IntroGame(int stateID) {
    }

    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Global.canMoovPlayer=false;
        Global.InitializeGame();//Initialise les variables sur leurs valeur par default

        g.drawImage(new Image("data/maps/Map001.png").getScaledCopy(Global.width, Global.height), 0, 0, new Color(1f, 1f, 1f, opacity));
        Global.P1.Sprite(g);
        Global.P1.getAnimation().stop();


        if (fadeInComplete) {
            Global.canMoovPlayer=true;
            //Tout interraction necessitant de ne pas donner le controle au player se fait ici --> toutes intro quoi

//-----------------------------------------------------------------------------------------------------------------
// $  Partie de debug  $
// ----------------------------------------------------------------------------------------------------------------
//
    		// Ici est une zone ou on peux selectionner de force la map qu'on veux charger en tout premier apres 
    		// initialisation du jeux, pratique pour du debug mais reste une zone seulement de dev
    		
    		
    		//game.enterState(100);
    		
    		
// ----------------------------------------------------------------------------------------------------------------
// $ Fin de la partie debug $
//-----------------------------------------------------------------------------------------------------------------
    		game.enterState(11); //      -->	Pour finir l'intro
    		
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!fadeInComplete) {
            opacity += 0.001f; // Adjust this value to control the speed of the fade in
            if (opacity > 1f) {
                opacity = 1f;
                fadeInComplete = true;
            }
        }
    }


    @Override
    public int getID() {
        return 6;
    }
}
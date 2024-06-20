package fr.sae.game.cinematiques;

import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.DialogueBox;
import fr.sae.game.Global;

public class IntroGame extends BasicGameState {
    private Image backgroundImage;
    private float opacity = 0f;
    
	DialogueBox dialgoboxLore; 

    private boolean fadeInComplete = false;
    private boolean cinematiqueIsEnded=false;

    public IntroGame(int stateID) {
    }

    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        this.dialgoboxLore = new DialogueBox(new String[] {
				"Hello world"
			});	
        this.dialgoboxLore.setChoices(Arrays.asList("Continuer"), choice1 -> {
            switch (choice1) {
	            case 0:
	            	this.cinematiqueIsEnded=true;
	                break;
            }       
     });
		this.dialgoboxLore.setTriggerZone(-1,-1,0,0);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Global.canMoovPlayer=false;
        Global.InitializeGame();//Initialise les variables sur leurs valeur par default

        g.drawImage(new Image("data/maps/Map001.png").getScaledCopy(Global.width, Global.height), 0, 0, new Color(1f, 1f, 1f, opacity));
        Global.P1.Sprite(g);
        Global.P1.getAnimation().stop();


        if (fadeInComplete && cinematiqueIsEnded) {
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
        
        if (fadeInComplete && !cinematiqueIsEnded) {
        	this.dialgoboxLore.renderForceDialogbox(g);
        	
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		boolean i =gc.getInput().isKeyPressed(Global.interract);

        if (!fadeInComplete) {
            opacity += 0.001f; // Adjust this value to control the speed of the fade in
            if (opacity > 1f) {
                opacity = 1f;
                fadeInComplete = true;
            }
            
        }else if (fadeInComplete && !cinematiqueIsEnded) {
        	this.dialgoboxLore.forceDialogBox(i, gc.getInput());
        }
        
    	
    }


    @Override
    public int getID() {
        return 6;
    }
}
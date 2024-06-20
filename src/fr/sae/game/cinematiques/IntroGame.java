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
    private boolean cinematiqueIsEnded = false;

    public IntroGame(int stateID) {
    }

    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        // Initialize dialogue box with a single message and a continue choice
        this.dialgoboxLore = new DialogueBox(new String[] {
            "Hello world"
        });
        this.dialgoboxLore.setChoices(Arrays.asList("Continuer"), choice1 -> {
            switch (choice1) {
                case 0:
                    this.cinematiqueIsEnded = true; // Mark the cinematic as ended when the player chooses to continue
                    break;
            }       
        });
        this.dialgoboxLore.setTriggerZone(-1, -1, 0, 0); // Set trigger zone for dialogue box interaction
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Global.canMoovPlayer = false; // Disable player movement during cinematic
        Global.InitializeGame(); // Initialize game variables to default values

        // Render background image with fading effect
        g.drawImage(new Image("data/maps/Map001.png").getScaledCopy(Global.width, Global.height), 0, 0, new Color(1f, 1f, 1f, opacity));
        
        // Render player sprite
        Global.P1.Sprite(g);
        Global.P1.getAnimation().stop();

        // If fade in is complete and cinematic is ended, allow player movement and transition to game state
        if (fadeInComplete && cinematiqueIsEnded) {
            Global.canMoovPlayer = true;
            game.enterState(11); // Enter game state to finish the intro
        }
        
        // If fade in is complete and cinematic is not ended, render the dialogue box
        if (fadeInComplete && !cinematiqueIsEnded) {
            this.dialgoboxLore.renderForceDialogbox(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        boolean i = gc.getInput().isKeyPressed(Global.interract);

        // Perform fading in effect until opacity reaches 1
        if (!fadeInComplete) {
            opacity += 0.001f; // Adjust this value to control the speed of the fade in
            if (opacity > 1f) {
                opacity = 1f;
                fadeInComplete = true;
            }
        } else if (fadeInComplete && !cinematiqueIsEnded) {
            // If fade in is complete and cinematic is not ended, handle dialogue box interactions
            this.dialgoboxLore.forceDialogBox(i, gc.getInput());
        }
    }

    @Override
    public int getID() {
        return 6; // Return the state ID of this state
    }
}

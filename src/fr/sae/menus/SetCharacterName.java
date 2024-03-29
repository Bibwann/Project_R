package fr.sae.menus;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;

public class SetCharacterName extends BasicGameState {
    private TrueTypeFont font;
    private String characterName = "";
    private int maxNameLength = 10;
    private Color textColor = Color.white;
    private Color backgroundColor = new Color(0, 0, 0, 200);
    private int arrowPosition = 0;
    private String[] characters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public SetCharacterName(int stateID) {
    }
    
    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	
    	this.game = sbg;
        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        g.setColor(textColor);
        g.setFont(font);
        g.drawString("Enter Character Name:", (Global.width/2)-200, 200);
        g.drawString(characterName, (Global.width/2)-200, 250);
        g.drawString(">", (Global.width/2)-230, 250);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
    }

    @Override
    public void keyPressed(int key, char c) {
        if (Character.isLetterOrDigit(c) && characterName.length() < maxNameLength) {
            characterName += c;
        } else if (key == Input.KEY_BACK && characterName.length() > 0) {
            characterName = characterName.substring(0, characterName.length() - 1);
        } else if (key == Input.KEY_ENTER && characterName!="") {
        	
            Global.Player1Name = characterName;
            game.enterState(4);
            
            
        } else if (key == Input.KEY_LEFT) {
            arrowPosition--;
            if (arrowPosition < 0) {
                arrowPosition = characters.length - 1;
            }
        } else if (key == Input.KEY_RIGHT) {
            arrowPosition++;
            if (arrowPosition >= characters.length) {
                arrowPosition = 0;
            }
        } else if (key == Input.KEY_SPACE) {
            characterName += characters[arrowPosition];
        }
    }

    @Override
    public int getID() {
        return 2; 
    }
}
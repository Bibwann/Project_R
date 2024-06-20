package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class SetCharacterName extends BasicGameState {

    private TrueTypeFont font;
    private String characterName = "";
    private int maxNameLength = 15; // Increased max name length to 15 characters
    private Color textColor = Color.white;
    private Color backgroundColor = new Color(0, 0, 0, 200);
    private int arrowPosition = 1;

    private String[][] characters = { 
        { " ", "A", "B", "C", "D", "E", "F", "G", "H", " " },
        { "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R" },
        { "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b" },
        { "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" },
        { "m", "n", "o", "p", "q", "r", "s", "t", "u", "v" },
        { " " ,"w", "x", "y", "z", "0", "1", "2", "3", " " },
        { " ", " ", "4", "5", "6", "7", "8", "9", " ", " " }
    };
    private StateBasedGame game;

    public SetCharacterName(int stateID) {
    }

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
        g.drawString("Enter Character Name:", (Global.width/2)-200, 150); 
        g.drawString(characterName, (Global.width/2)-200, 200); 
        g.drawString(">", (Global.width/2)-230, 200); 

        int y = 300;
        int spacing = 60; 

        // Render the on-screen keyboard layout
        for (int i = 0; i < characters.length; i++) {
            int totalWidth = characters[i].length * spacing;

            for (int j = 0; j < characters[i].length; j++) {
                String character = characters[i][j];
                int x = (gc.getWidth() - totalWidth) / 2 + j * spacing + spacing / 2; 

                if (i * 10 + j == arrowPosition) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.white);
                }

                g.drawString(character, x, y + i * 50);
            }
        }

        // Render instructions for navigation and character selection
        String message = "Use the arrow keys to select a character and press SPACE to add it to your name.";
        int messageWidth = g.getFont().getWidth(message);
        g.drawString(message, (gc.getWidth() - messageWidth) / 2, gc.getHeight() - 50);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // No updates needed in this method for character name input
    }

    @Override
    public void keyPressed(int key, char c) {
        int maxPosition = characters.length * 10 - 1;
        int minPosition = 0;

        // Handle character input from keyboard
        if (Character.isLetterOrDigit(c) && characterName.length() < maxNameLength) {
            characterName += c;
        } else if (key == Input.KEY_BACK && characterName.length() > 0) {
            characterName = characterName.substring(0, characterName.length() - 1);
        } else if (key == Input.KEY_ENTER && !characterName.isEmpty()) {
            Global.Player1Name = characterName; // Set the global player name
            game.enterState(5); // Transition to another game state (change the number as per your game)
        } else if (key == Input.KEY_LEFT) {
            if (arrowPosition > minPosition && !characters[(arrowPosition - 1) / 10][(arrowPosition - 1) % 10].equals(" ")) {
                arrowPosition--;
            }
        } else if (key == Input.KEY_RIGHT) {
            if (arrowPosition < maxPosition && !characters[(arrowPosition + 1) / 10][(arrowPosition + 1) % 10].equals(" ")) {
                arrowPosition++;
            }
        } else if (key == Input.KEY_UP) {
            if (arrowPosition >= 10 && !characters[(arrowPosition - 10) / 10][(arrowPosition - 10) % 10].equals(" ")) {
                arrowPosition -= 10;
            }
        } else if (key == Input.KEY_DOWN) {
            if (arrowPosition < (characters.length - 1) * 10 && !characters[(arrowPosition + 10) / 10][(arrowPosition + 10) % 10].equals(" ")) {
                arrowPosition += 10;
            }
        } else if (key == Input.KEY_SPACE) {
            characterName += characters[arrowPosition / 10][arrowPosition % 10]; // Append selected character to the name
        }
    }

    @Override
    public int getID() {
        return 4; // Return the state ID associated with this SetCharacterName state
    }
}

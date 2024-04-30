package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class OptionMenu extends BasicGameState {
    private TrueTypeFont font, titleFont, menuFont;
    private String selectedOption;
    private boolean optionLocked = false;
    private int previousOption=0;
    
    private String[] options = {
         "Up: " + Global.reversedInputs.get(Global.up),
         "Down: " +  Global.reversedInputs.get(Global.down),
         "Left: " +  Global.reversedInputs.get(Global.left),
         "Right: " +  Global.reversedInputs.get(Global.right),
         "Interact: " +  Global.reversedInputs.get(Global.interract),
         "Pause: " +  Global.reversedInputs.get(Global.pause)
        };
    public OptionMenu(int stateID) {

    }
    
    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;

        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        selectedOption = options[0];

        // Initialisation des polices pour le titre et le menu
        Font awtTitleFont = new Font("Verdana", Font.BOLD, 36);
        titleFont = new TrueTypeFont(awtTitleFont, true);
        Font awtMenuFont = new Font("Verdana", Font.PLAIN, 24);
        menuFont = new TrueTypeFont(awtMenuFont, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
        
        g.setFont(titleFont);
        g.setColor(Color.green); // Change this line
        g.drawString("OPTIONS", (gc.getWidth() - titleFont.getWidth("OPTIONS")) / 2, gc.getHeight()*1/10);
        // Ajouter le titre "OPTIONS" en haut de l'écran
        g.drawString("OPTIONS", (gc.getWidth() - titleFont.getWidth("OPTIONS")) / 2, gc.getHeight()*1/10);

        g.setFont(menuFont);
        int y = gc.getHeight()*1/3;
        for (String option : options) {
            int x = (gc.getWidth() - menuFont.getWidth(option)) / 2;
            if (option.equals(selectedOption)) {
                g.setColor(optionLocked ? Color.blue : Color.green);
                g.fillRect(x - 10, y - 10, menuFont.getWidth(option) + 20, menuFont.getHeight(option) + 20); // Draw a background for the selected item
                g.setColor(Color.black); 
            } else {
                g.setColor(Color.white);
            }
            g.drawString(option, x, y);
            y += 50;
        }

        String exitOption = "Exit";
        int exitOptionWidth = menuFont.getWidth(exitOption);
        int margin = 50;
        int x = (gc.getWidth() - exitOptionWidth) / 2;
        int yExit = gc.getHeight() - menuFont.getHeight(exitOption) - margin;
        if (selectedOption.equals("Exit")) {
            g.setColor(optionLocked ? Color.blue : Color.green);
            g.fillRect(x - 10, yExit - 10, exitOptionWidth + 20, menuFont.getHeight(exitOption) + 20); // Draw a background for the selected item
            g.setColor(Color.black); 
        } else {
            g.setColor(Color.white);
        }
        g.drawString(exitOption, x, yExit);
    }
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) {
            if (selectedOption.equals("Exit")) {
                game.enterState(1);
            } else {
                optionLocked = !optionLocked;
            }
        } else if (!optionLocked) {
            if (key == Input.KEY_UP) {
                moveSelection(-1);
            } else if (key == Input.KEY_DOWN) {
                moveSelection(1);
            }
        } else {
            String optionName = selectedOption.split(": ")[0];
            switch (optionName) {
                case "Up":
                	previousOption=0;
                    Global.up = key;
                    optionLocked = !optionLocked;
                    break;
                case "Down":
                	previousOption=1;
                    Global.down = key;
                    optionLocked = !optionLocked;
                    break;
                    
                case "Left":
                	previousOption=2;
                    Global.left = key;
                    optionLocked = !optionLocked;
                    break;
                    
                case "Right":
                	previousOption=3;
                    Global.right = key;
                    optionLocked = !optionLocked;
                    break;
                    
                case "Interact":
                	previousOption=4;
                    Global.interract = key;
                    optionLocked = !optionLocked;
                    break;
                    
                case "Pause":
                	previousOption=5;
                    Global.pause = key;
                    optionLocked = !optionLocked;
                    break;
                    
                default:
                	System.err.println("Erreur dans OptionMenue, optionname contien une valeur inconnue");
            }
            updateOptions();
        }
    }

    @Override
    public void keyReleased(int key, char c) {
    }

    @Override
    public int getID() {
        return 3;
    }

    private void moveSelection(int direction) {
        int newIndex = getSelectedOptionIndex() + direction;
        if (newIndex >= 0 && newIndex < options.length) {
            selectedOption = options[newIndex];
        } else if (newIndex == options.length && direction > 0) {
            selectedOption = "Exit";
        } else if (selectedOption.equals("Exit") && direction < 0) {
            selectedOption = options[options.length - 1];
        } else if (newIndex < 0 && direction < 0) {
            selectedOption = "Exit";
        } else if (selectedOption.equals("Exit") && direction > 0) {
            selectedOption = options[0];
        }
    }

    private int getSelectedOptionIndex() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selectedOption)) {
                return i;
            }
        }
        return -1;
    }

    private void updateOptions() {
        options = new String[] {
            "Up: " + Global.reversedInputs.get(Global.up),
            "Down: " +  Global.reversedInputs.get(Global.down),
            "Left: " +  Global.reversedInputs.get(Global.left),
            "Right: " +  Global.reversedInputs.get(Global.right),
            "Interact: " +  Global.reversedInputs.get(Global.interract),
            "Pause: " +  Global.reversedInputs.get(Global.pause)
        };
        selectedOption = options[previousOption];
    }
}
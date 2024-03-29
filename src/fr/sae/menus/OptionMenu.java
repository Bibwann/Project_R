package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class OptionMenu extends BasicGameState {
    private TrueTypeFont font;
    private String selectedOption;
    private String[] options = {"Up: " + Global.up, "Down: " +  Global.down, "Left: " +  Global.left, "Right: " +  Global.right, "Interact: " +  Global.interract, "Exit"};

    public OptionMenu(int stateID) {
    }
    
    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;

        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        selectedOption = options[0];
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        g.setColor(Color.white);
        g.setFont(font);
        int y = 100;
        for (String option : options) {
            if (!option.equals("Exit")) {
                g.drawString(option, 200, y);
                y += 50;
            }
        }

        // Draw "Exit" at the bottom right of the window
        String exitOption = "Exit";
        int exitOptionWidth = font.getWidth(exitOption);
        int margin = 50; // Increase this value to move "Exit" further from the edge
        g.drawString(exitOption, gc.getWidth() - exitOptionWidth - margin, gc.getHeight() - font.getHeight(exitOption) - margin);

        g.setColor(Color.red);
        if (!selectedOption.equals("Exit")) {
            g.drawRect(180, getSelectedOptionIndex() * 50 + 100, 440, 40);
        } else {
            // Draw the red rectangle around "Exit"
            g.drawRect(gc.getWidth() - exitOptionWidth - margin - 10, gc.getHeight() - font.getHeight(exitOption) - margin - 10, exitOptionWidth + 10, font.getHeight(exitOption) + 10);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_UP) {
            moveSelection(-1);
        } else if (key == Input.KEY_DOWN) {
            moveSelection(1);
        } else if (key == Input.KEY_ENTER) {
            if (selectedOption.equals("Exit")) {
                game.enterState(1);
            } else {
                System.out.println("procedure de modification de touche");
            }
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
}
package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class PauseMenu extends BasicGameState {
    private TrueTypeFont font, titleFont, menuFont;
    private String selectedOption;
    private boolean optionLocked = false;
    private int previousOption = 0;

    // Array of options displayed in the pause menu
    private String[] options = {
        "Up: " + Global.reversedInputs.get(Global.up),
        "Down: " + Global.reversedInputs.get(Global.down),
        "Left: " + Global.reversedInputs.get(Global.left),
        "Right: " + Global.reversedInputs.get(Global.right),
        "Interact: " + Global.reversedInputs.get(Global.interract),
        "Pause: " + Global.reversedInputs.get(Global.pause),
        "Save",
        "Return to Game",
        "Quit Game"
    };

    public PauseMenu(int stateID) {

    }

    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;

        // Initialize fonts for rendering text
        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        selectedOption = options[0];

        // Initialize fonts for title and menu items
        Font awtTitleFont = new Font("Verdana", Font.BOLD, 36);
        titleFont = new TrueTypeFont(awtTitleFont, true);
        Font awtMenuFont = new Font("Verdana", Font.PLAIN, 24);
        menuFont = new TrueTypeFont(awtMenuFont, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        // Render "PAUSE" title at the top of the screen
        g.setFont(titleFont);
        g.setColor(Color.green);
        g.drawString("PAUSE", (gc.getWidth() - titleFont.getWidth("PAUSE")) / 2, gc.getHeight() * 1 / 10);

        // Render menu options
        g.setFont(menuFont);
        int y = gc.getHeight() * 1 / 3;
        for (String option : options) {
            int x = (gc.getWidth() - menuFont.getWidth(option)) / 2;

            // Highlight selected option with a background
            if (option.equals(selectedOption)) {
                g.setColor(optionLocked ? Color.blue : Color.green);
                g.fillRect(x - 10, y - 10, menuFont.getWidth(option) + 20, menuFont.getHeight(option) + 20);
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
            }

            g.drawString(option, x, y);
            y += 60; // Increase the y spacing between menu items
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // No updates needed in this method for pause menu
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) {
            // Handle actions based on selected option
            if (selectedOption.equals("Save")) {
                Global.SaveGame(); // Call global save method
                game.enterState(Global.actualId); // Return to game state (replace 1 with your main game state ID)
            
            } else if (selectedOption.equals("Return to Game")) {
                game.enterState(Global.actualId); // Return to game state (replace 1 with your main game state ID)
            } else if (selectedOption.equals("Quit Game")) {
                System.exit(0); // Quit the game
            } else {
                optionLocked = !optionLocked; // Toggle option lock state
            }
        } else if (!optionLocked) {
            // Navigate through menu options
            if (key == Input.KEY_UP) {
                moveSelection(-1); // Move selection up
            } else if (key == Input.KEY_DOWN) {
                moveSelection(1); // Move selection down
            }
        } else {
            // Modify key bindings when an option is locked
            String optionName = selectedOption.split(": ")[0];
            switch (optionName) {
                case "Up":
                    previousOption = 0;
                    Global.up = key; // Update key binding for "Up" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                case "Down":
                    previousOption = 1;
                    Global.down = key; // Update key binding for "Down" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                case "Left":
                    previousOption = 2;
                    Global.left = key; // Update key binding for "Left" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                case "Right":
                    previousOption = 3;
                    Global.right = key; // Update key binding for "Right" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                case "Interact":
                    previousOption = 4;
                    Global.interract = key; // Update key binding for "Interact" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                case "Pause":
                    previousOption = 5;
                    Global.pause = key; // Update key binding for "Pause" action
                    optionLocked = !optionLocked; // Toggle option lock state
                    break;
                default:
                    System.err.println("Error in PauseMenu, optionName contains an unknown value");
            }
            updateOptions(); // Update options array after modifying key bindings
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        // Key released event not used in this context
    }

    @Override
    public int getID() {
        return 101; // Return the state ID associated with this PauseMenu state
    }

    // Method to move selection up or down in the pause menu
    private void moveSelection(int direction) {
        int newIndex = getSelectedOptionIndex() + direction;

        // Adjust selected option index within bounds of options array
        if (newIndex >= 0 && newIndex < options.length) {
            selectedOption = options[newIndex];
        } else if (newIndex == options.length && direction > 0) {
            selectedOption = "Save"; // Wrap around to "Save" option
        } else if (selectedOption.equals("Save") && direction < 0) {
            selectedOption = options[options.length - 1]; // Wrap around from "Save" to last option
        } else if (newIndex < 0 && direction < 0) {
            selectedOption = "Save"; // Wrap around from first option to "Save"
        } else if (selectedOption.equals("Save") && direction > 0) {
            selectedOption = options[0]; // Wrap around from "Save" to first option
        }
    }

    // Method to retrieve the index of the currently selected option
    private int getSelectedOptionIndex() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selectedOption)) {
                return i;
            }
        }
        return -1;
    }

    // Method to update the options array after modifying key bindings
    private void updateOptions() {
        options = new String[] {
            "Up: " + Global.reversedInputs.get(Global.up),
            "Down: " + Global.reversedInputs.get(Global.down),
            "Left: " + Global.reversedInputs.get(Global.left),
            "Right: " + Global.reversedInputs.get(Global.right),
            "Interact: " + Global.reversedInputs.get(Global.interract),
            "Pause: " + Global.reversedInputs.get(Global.pause),
            "Save",
            "Return to Game",
            "Quit Game"
        };

        // Restore previous selected option after updating options
        selectedOption = options[previousOption];
    }
}

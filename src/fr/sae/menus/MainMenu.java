package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.Global;

public class MainMenu extends BasicGameState {
    private TrueTypeFont titleFont;
    private TrueTypeFont menuFont;
    private int selectedItemIndex;
    private String[] menuItems = {"Start Game", "Options", "Exit"};
    private StateBasedGame game;

    public MainMenu(int stateID) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        // Initialize fonts for rendering text
        Font font = new Font("Verdana", Font.BOLD, 32);
        titleFont = new TrueTypeFont(font, true);

        font = new Font("Verdana", Font.PLAIN, 24);
        menuFont = new TrueTypeFont(font, true);

        selectedItemIndex = 0; // Initially select the first menu item
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.setFont(titleFont);

        // Render the title image centered horizontally and positioned near the top of the screen
        Image image = new Image("data/nom.png");
        float imageX = (Global.width - image.getWidth()) / 2;
        float imageY = (Global.height - image.getHeight()) - 560;
        g.drawImage(image, imageX, imageY);

        // Render menu items vertically centered below the title image
        for (int i = 0; i < menuItems.length; i++) {
            String menuItem = menuItems[i];
            int x = (Global.width - menuFont.getWidth(menuItem)) / 2;
            int y = Global.height * 1 / 3 + i * 100;

            // Highlight selected menu item with green background and adjust text color for visibility
            if (i == selectedItemIndex) {
                g.setColor(Color.green);
                g.fillRect(x - 10, y - 10, menuFont.getWidth(menuItem) + 20, menuFont.getHeight(menuItem) + 20);
                g.setColor(Color.black); // Text color for selected item
            } else {
                g.setColor(Color.white); // Default text color
            }

            // Render menu item text
            g.setFont(menuFont);
            g.drawString(menuItem, x, y);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // Update method not used in this context
    }

    @Override
    public void keyPressed(int key, char c) {
        // Handle key presses to navigate menu options
        if (key == Input.KEY_UP) {
            selectedItemIndex = (selectedItemIndex - 1 + menuItems.length) % menuItems.length;
        } else if (key == Input.KEY_DOWN) {
            selectedItemIndex = (selectedItemIndex + 1) % menuItems.length;
        } else if (key == Input.KEY_ENTER) {
            // Execute actions based on selected menu item
            switch (selectedItemIndex) {
                case 0:
                    // Start Game
                    game.enterState(2); // Assuming state ID 2 is for starting the game
                    break;
                case 1:
                    // Options
                    game.enterState(3); // Assuming state ID 3 is for options menu
                    break;
                case 2:
                    // Exit
                    System.exit(0); // Exit the application
                    break;
            }
        }
    }

    @Override
    public int getID() {
        return 1; // Return the state ID associated with this MainMenu state
    }
}

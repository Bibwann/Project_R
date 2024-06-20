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
import fr.sae.menus.*;


public class GameOver extends BasicGameState {
    private TrueTypeFont titleFont;
    private TrueTypeFont menuFont;
    private int selectedItemIndex;
    private String[] menuItems = {"Main Menu", "Exit"};
    private StateBasedGame game;

    public GameOver(int stateID) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        Font font = new Font("Verdana", Font.BOLD, 32);
        titleFont = new TrueTypeFont(font, true);
        font = new Font("Verdana", Font.PLAIN, 24);
        menuFont = new TrueTypeFont(font, true);
        selectedItemIndex = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Game Over", (Global.width - titleFont.getWidth("Game Over")) / 2, Global.height*1/8);

        for (int i = 0; i < menuItems.length; i++) {
            String menuItem = menuItems[i];
            int x = (Global.width - menuFont.getWidth(menuItem)) / 2;
            int y = Global.height*1/3 + i * 100;

            if (i == selectedItemIndex) {
                g.setColor(Color.green);
                g.fillRect(x - 10, y - 10, menuFont.getWidth(menuItem) + 20, menuFont.getHeight(menuItem) + 20);
                g.setColor(Color.black); 
            } else {
                g.setColor(Color.white);
            }

            g.setFont(menuFont);
            g.drawString(menuItem, x, y);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_UP) {
            selectedItemIndex = (selectedItemIndex - 1 + menuItems.length) % menuItems.length;
        } else if (key == Input.KEY_DOWN) {
            selectedItemIndex = (selectedItemIndex + 1) % menuItems.length;
        } else if (key == Input.KEY_ENTER) {
            switch (selectedItemIndex) {
                case 0:
                    // Main Menu
                    game.enterState(1);
                    
                    break;
                case 1:
                    // Exit
                    System.exit(0);
                    break;

            }
        }
    }

    @Override
    public int getID() {
        return 7;
    }
}
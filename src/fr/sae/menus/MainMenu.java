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

public class MainMenu extends BasicGameState {
    private TrueTypeFont titleFont;
    private TrueTypeFont menuFont;
    private int selectedItemIndex;
    private String[] menuItems = {"Start Game","Options", "Exit", "testcommitdevdistant"};

    public MainMenu(int stateID) {
    }
    
    
    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	
    	this.game = sbg;
        Font font = new Font("Verdana", Font.BOLD, 32);
        titleFont = new TrueTypeFont(font, true);
        font = new Font("Verdana", Font.PLAIN, 20);
        menuFont = new TrueTypeFont(font, true);
        selectedItemIndex = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Main Menu", Global.height*4/5, Global.height*1/8);

        for (int i = 0; i < menuItems.length; i++) {
            if (i == selectedItemIndex) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.setFont(menuFont);
            g.drawString(menuItems[i], Global.height*4/5, Global.height*1/4 + i * 50);
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
                    // Start Game
                	game.enterState(2);
                	
                    break;
                case 1:
                	
                	game.enterState(3);
                    // Options
                    break;
                case 2:
                    // Exit
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public int getID() {
        return 1;
    }
}
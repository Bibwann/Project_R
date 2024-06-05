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

public class Credits extends BasicGameState {
    private TrueTypeFont titleFont;
    private TrueTypeFont creditsFont;
    private StateBasedGame game;

    public Credits(int stateID) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        Font font = new Font("Verdana", Font.BOLD, 32);
        titleFont = new TrueTypeFont(font, true);
        font = new Font("Verdana", Font.PLAIN, 24);
        creditsFont = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Credits", (gc.getWidth() - titleFont.getWidth("Credits")) / 2, gc.getHeight()*1/8);

        String[] credits = {
            "Game Design: Thomas Ponsoda",
            "Programming: Nieto Bastien, Federico Mantovani, Clement Dupouy-Paulin",
            "Music: Thomas Ponsoda",
            Global.egg1
        };

        for (int i = 0; i < credits.length; i++) {
            String credit = credits[i];
            int x = (gc.getWidth() - creditsFont.getWidth(credit)) / 2;
            int y = gc.getHeight()*1/3 + i * 50;

            g.setFont(creditsFont);
            g.drawString(credit, x, y);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) {
            game.enterState(1);
        }
    }

    @Override
    public int getID() {
        return 8;
    }
}
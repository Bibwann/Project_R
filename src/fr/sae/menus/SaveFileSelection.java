package fr.sae.menus;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class SaveFileSelection extends BasicGameState {

    private TrueTypeFont font;
    private String[] saveFiles = new String[3];
    private Color textColor = Color.white;
    private Color backgroundColor = new Color(0, 0, 0, 200);
    private int arrowPosition = 0;
    private StateBasedGame game;

    public SaveFileSelection(int stateID) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        Font awtFont = new Font("Verdana", Font.BOLD, 40); // Increase the font size
        font = new TrueTypeFont(awtFont, true);

        File folder = new File("data/saves");
        File[] files = folder.listFiles();

        for (int i = 0; i < saveFiles.length; i++) {
            if (i < files.length) {
                String fileName = files[i].getName();
                int pos = fileName.lastIndexOf(".");
                if (pos > 0) {
                    fileName = fileName.substring(0, pos);
                }
                saveFiles[i] = fileName;
            } else {
                saveFiles[i] = "Empty Slot";
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        g.setColor(Color.green);
        g.setFont(font);
        g.drawString("Select Save File", (gc.getWidth()/2)-(g.getFont().getWidth("Select Save File")/2), 150); 

        int y = 400; 
        int spacing = 150; 

        for (int i = 0; i < saveFiles.length; i++) {
            int x = (gc.getWidth() / 2) - 300; 

            if (i == arrowPosition) {
                g.setColor(Color.green);
                g.fillRect(x, y + i * spacing, 600, 100); 
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
            }

            String text = saveFiles[i].equals("Empty Slot") ? "New Save" : saveFiles[i];
            g.drawString(text, x + (600 - g.getFont().getWidth(text)) / 2, y + i * spacing + 100 / 2 - g.getFont().getHeight(text) / 2); // Center the text vertically

            g.setColor(Color.black);
            g.drawRect(x, y + i * spacing, 600, 100); // Draw a border around the rectangle
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
    }

    @Override
    public void keyPressed(int key, char c) {
        int maxPosition = saveFiles.length - 1;
        int minPosition = 0;

        if (key == Input.KEY_ENTER) {
            if (saveFiles[arrowPosition].equals("Empty Slot")) {
                // Create a new save file
                saveFiles[arrowPosition] = "Save" + (arrowPosition + 1);
                File newSaveFile = new File("data/saves/" + saveFiles[arrowPosition] + ".xml");
                try {
                    newSaveFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.enterState(4);
            } else {
                // Load the selected save file --> a ecrire plus tard
            	
                game.enterState(4);
            }
        } else if (key == Input.KEY_UP) {
            if (arrowPosition > minPosition) {
                arrowPosition--;
            }
        } else if (key == Input.KEY_DOWN) {
            if (arrowPosition < maxPosition) {
                arrowPosition++;
            }
        }
    }

    @Override
    public int getID() {
        return 2;
    }
}
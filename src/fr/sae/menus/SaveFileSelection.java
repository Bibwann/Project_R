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
    private boolean fileSelected = false; // Indicates if a file has been selected
    private String[] fileOptions = {"Load", "Delete"}; // Options after selecting a file
    private int fileOptionPosition = 0; // Position in the file options menu

    public SaveFileSelection(int stateID) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;

        // Initialize font
        Font awtFont = new Font("Verdana", Font.BOLD, 40); // Increase the font size
        font = new TrueTypeFont(awtFont, true);

        // Load existing save file names from the "data/saves" directory
        loadSaveFiles();
    }

    private void loadSaveFiles() {
        File folder = new File("data/saves");
        File[] files = folder.listFiles();

        // Populate saveFiles array
        for (int i = 0; i < saveFiles.length; i++) {
            if (files != null && i < files.length) {
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
        // Draw the background
        g.setColor(backgroundColor);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        // Draw the title
        g.setColor(Color.green);
        g.setFont(font);
        String title = "Select Save File";
        g.drawString(title, (gc.getWidth() / 2) - (font.getWidth(title) / 2), 150);

        if (fileSelected) {
            renderFileOptions(gc, g);
        } else {
            renderSaveFileSelection(gc, g);
        }
    }

    private void renderSaveFileSelection(GameContainer gc, Graphics g) {
        // Variables for positioning
        int y = 400;
        int spacing = 150;
        int boxWidth = 600;
        int boxHeight = 100;

        // Draw save file options
        for (int i = 0; i < saveFiles.length; i++) {
            int x = (gc.getWidth() / 2) - (boxWidth / 2);

            // Highlight selected option
            if (i == arrowPosition) {
                g.setColor(Color.green);
                g.fillRect(x, y + i * spacing, boxWidth, boxHeight);
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
            }

            // Draw the save file name or "New Save" for empty slots
            String text = saveFiles[i].equals("Empty Slot") ? "New Save" : saveFiles[i];
            g.drawString(text, x + (boxWidth - font.getWidth(text)) / 2, y + i * spacing + (boxHeight / 2) - (font.getHeight(text) / 2));

            // Draw border around the rectangle
            g.setColor(Color.black);
            g.drawRect(x, y + i * spacing, boxWidth, boxHeight);
        }
    }

    private void renderFileOptions(GameContainer gc, Graphics g) {
        // Variables for positioning
        int y = 400;
        int spacing = 150;
        int boxWidth = 600;
        int boxHeight = 100;

        // Draw file options ("Load" and "Delete")
        for (int i = 0; i < fileOptions.length; i++) {
            int x = (gc.getWidth() / 2) - (boxWidth / 2);

            // Highlight selected option
            if (i == fileOptionPosition) {
                g.setColor(Color.green);
                g.fillRect(x, y + i * spacing, boxWidth, boxHeight);
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
            }

            String text = fileOptions[i];
            g.drawString(text, x + (boxWidth - font.getWidth(text)) / 2, y + i * spacing + (boxHeight / 2) - (font.getHeight(text) / 2));

            // Draw border around the rectangle
            g.setColor(Color.black);
            g.drawRect(x, y + i * spacing, boxWidth, boxHeight);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // No updates needed in this method for save file selection
    }

    @Override
    public void keyPressed(int key, char c) {
        if (fileSelected) {
            handleFileOptions(key);
        } else {
            handleSaveFileSelection(key);
        }
    }

    private void handleSaveFileSelection(int key) {
        int maxPosition = saveFiles.length - 1;
        int minPosition = 0;

        if (key == Input.KEY_ENTER) {
            if (saveFiles[arrowPosition].equals("Empty Slot")) {
                // Create a new save file
                saveFiles[arrowPosition] = "Save" + (arrowPosition + 1);
                File newSaveFile = new File("data/saves/" + saveFiles[arrowPosition] + ".xml");
                try {
                    newSaveFile.createNewFile();
                    Global.actualfile = newSaveFile; // Set the global actual file reference
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.enterState(4); // Transition to another game state (change the number as per your game)
            } else {
                // File selected, show options
                fileSelected = true;
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

    private void handleFileOptions(int key) {
        int maxPosition = fileOptions.length - 1;
        int minPosition = 0;

        if (key == Input.KEY_ENTER) {
            if (fileOptions[fileOptionPosition].equals("Load")) {
                // Load the selected save file
                Global.actualfile = new File("data/saves/" + saveFiles[arrowPosition] + ".xml");
                Global.LoadGame(); // Load game state from the selected file (to be implemented)
                game.enterState(Global.actualId); // Transition to the loaded game state
            } else if (fileOptions[fileOptionPosition].equals("Delete")) {
                // Delete the selected save file
                File fileToDelete = new File("data/saves/" + saveFiles[arrowPosition] + ".xml");
                if (fileToDelete.delete()) {
                    saveFiles[arrowPosition] = "Empty Slot"; // Mark the slot as empty
                }
                fileSelected = false; // Return to save file selection
            }
        } else if (key == Input.KEY_UP) {
            if (fileOptionPosition > minPosition) {
                fileOptionPosition--;
            }
        } else if (key == Input.KEY_DOWN) {
            if (fileOptionPosition < maxPosition) {
                fileOptionPosition++;
            }
        } else if (key == Input.KEY_ESCAPE) {
            fileSelected = false; // Return to save file selection
        }
    }

    @Override
    public int getID() {
        return 2; // Return the state ID associated with this SaveFileSelection state
    }
}

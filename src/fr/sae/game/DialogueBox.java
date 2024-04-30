package fr.sae.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.TrueTypeFont;


import fr.sae.game.caractere.Player;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class DialogueBox {
    private Rectangle triggerZone;
    
    private String[] messages;
    private List<String> choices;
    private int currentIndex;
    private boolean visible;
    private boolean isWaitingForInput;
    private boolean canOpen;

    public DialogueBox(String[] messages) {
        this.messages = messages;
        this.currentIndex = 0;
        this.visible = false;
        this.isWaitingForInput = false;
        this.choices = new ArrayList<>();
        this.canOpen = true;

        this.triggerZone = null;
    }

    public void render(Graphics g) {
        if (visible) {
            int margin = 20; // Margin around the dialog box
            int dialogHeight = Global.height / 3;
            int dialogWidth = Global.width - 2 * margin;
            int dialogX = margin;
            int dialogY = Global.height - dialogHeight - margin;

            // Draw the background of the dialog box
            g.setColor(new Color(0, 0, 0, 0.8f));
            g.fillRect(dialogX, dialogY, dialogWidth, dialogHeight);

            // Create a new TrueTypeFont with the desired size
            Font awtFont = new Font("Courier New", Font.BOLD, dialogHeight / 10); // Increase the size of the font
            TrueTypeFont font = new TrueTypeFont(awtFont, false);

            g.setFont(font); // Set the font
            g.setColor(Color.white);

            // Split the message into lines
            String message = this.messages[this.currentIndex];
            List<String> lines = new ArrayList<>();
            for (String line : message.split("\n")) {
                while (font.getWidth(line) > dialogWidth - 2 * margin) {
                    int i = line.length() - 1;
                    while (font.getWidth(line.substring(0, i)) > dialogWidth - 2 * margin) {
                        i--;
                    }
                    lines.add(line.substring(0, i));
                    line = line.substring(i);
                }
                lines.add(line);
            }

            // Draw each line
            int y = dialogY + margin;
            for (String line : lines) {
                g.drawString(line, dialogX + margin, y);
                y += font.getHeight(line);
            }

            if (!choices.isEmpty()) {
                y = dialogY + 2 * margin;
                for (int i = 0; i < choices.size(); i++) {
                    g.drawString((i + 1) + ": " + choices.get(i), dialogX + margin, y);
                    y += font.getHeight(choices.get(i)) + margin; // Add a margin after each choice
                }
            }
        }
    }

    public void nextMessage() {
        if (this.currentIndex < this.messages.length - 1) {
            this.currentIndex++;
            this.isWaitingForInput = true;
        } else {
            this.currentIndex = 0;
            this.visible = false;
            this.canOpen = false;
            Global.switchModeControles();
            
        }
    }

    public void setChoices(List<String> choices) {
        this.choices.clear();
        this.choices.addAll(choices);
        this.isWaitingForInput = false;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        this.isWaitingForInput = true;
    }

    public boolean isVisible() {
        return this.visible;
    }
     
    public void setTriggerZone(int x, int y, int width, int height) {
        this.triggerZone = new Rectangle(x, y, width, height);
    }

    public boolean triggerZone(Rectangle other) {
        return this.triggerZone.intersects(other);
    }
    
    public boolean triggerZone(Player p) {
        return triggerZone(p.getHitbox());
    }
    
    public Rectangle gettriggerZone() {
        return this.triggerZone;
    }
    
    public void dialogBox(Input input) {
        if (visible && isWaitingForInput && input.isKeyPressed(Global.interract)) {
            if (this.currentIndex < this.messages.length - 1) {
                nextMessage();
            } else {
                this.visible = false;
                this.canOpen = false;
                this.currentIndex = 0; // Reset currentIndex to 0 when the dialogue box is closed
                Global.switchModeControles();
            }
        }
        else if (canOpen && input.isKeyPressed(Global.interract) && !Global.canMoovDialogbox && this.triggerZone(Global.P1)) {
            this.setVisible(true);
            Global.switchModeControles();
        }
    
        if (!input.isKeyDown(Global.interract)) {
            this.canOpen = true;
        }
    }
    
	 public void draw(Graphics g) {
	    	
	    	//Dessine la zone de warp
	        g.setColor(Color.green);
	        g.drawRect(this.triggerZone.getX(), this.triggerZone.getY(), this.triggerZone.getWidth(), this.triggerZone.getHeight());
	        g.setColor(Color.white);
	
	    }
    
    
}
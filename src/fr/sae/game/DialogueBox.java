package fr.sae.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.TrueTypeFont;

import fr.sae.game.caractere.Player;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DialogueBox {
    private Rectangle triggerZone; // Area that triggers the dialogue box
    private boolean isActiveTempDialogbox; // Indicates if the temporary dialog box is active
    private String[] messages; // Messages to display in the dialog box
    private List<String> choices; // List of choices available in the dialog box
    private List<String> preprocessedLines; // Preprocessed lines of messages
    private int currentIndex; // Current message index
    private boolean visible; // Indicates if the dialog box is visible
    private boolean isWaitingForInput; // Indicates if waiting for user input
    private boolean canOpen; // Indicates if the dialog box can be opened
    private boolean canBeReuse; // Indicates if the dialog box can be reused
    private int currentChoice; // Current selected choice
    private Consumer<Integer> choiceCallback; // Callback for choice selection
    private TrueTypeFont font; // Font for the dialog box
    private int dialogHeight; // Height of the dialog box
    private int dialogWidth; // Width of the dialog box
    private int margin; // Margin for the dialog box

    // Constructor to initialize the dialogue box with messages
    public DialogueBox(String[] messages) {
        this.messages = messages;
        this.currentIndex = 0;
        this.visible = false;
        this.isWaitingForInput = false;
        this.choices = new ArrayList<>();
        this.canOpen = true;
        this.triggerZone = new Rectangle(0, 0, 0, 0);
        this.canBeReuse = true;
        this.currentChoice = -1;
        this.choiceCallback = null;
        this.isActiveTempDialogbox = false;

        // Initialize the font and dimensions
        this.margin = 20;
        this.dialogHeight = Global.height / 3;
        this.dialogWidth = Global.width - 2 * margin;
        Font awtFont = new Font("Courier New", Font.BOLD, dialogHeight / 10);
        this.font = new TrueTypeFont(awtFont, false);

        preprocessMessages();
    }

    // Preprocess the messages to fit within the dialog box width
    private void preprocessMessages() {
        this.preprocessedLines = new ArrayList<>();
        for (String message : this.messages) {
            for (String line : message.split("\n")) {
                while (font.getWidth(line) > dialogWidth - 2 * margin) {
                    int i = line.length() - 1;
                    while (font.getWidth(line.substring(0, i)) > dialogWidth - 2 * margin) {
                        i--;
                    }
                    preprocessedLines.add(line.substring(0, i));
                    line = line.substring(i);
                }
                preprocessedLines.add(line);
            }
        }
    }

    // Render the dialog box
    public void render(Graphics g) {
        if (visible) {
            int dialogX = margin;
            int dialogY = Global.height - dialogHeight - margin;

            g.setColor(new Color(0, 0, 0, 0.94f));
            g.fillRect(dialogX, dialogY, dialogWidth, dialogHeight);

            g.setFont(font);
            g.setColor(Color.white);

            int y = dialogY + margin;
            for (String line : preprocessedLines) {
                g.drawString(line, dialogX + margin, y);
                y += font.getHeight(line);
            }

            if (!choices.isEmpty()) {
                int maxWidth = choices.stream().mapToInt(font::getWidth).max().orElse(0);
                int y1 = dialogY + dialogHeight - 3 * margin;
                int x1 = dialogX + dialogWidth - maxWidth - 3 * margin;

                for (int i = choices.size() - 1; i >= 0; i--) {
                    String choiceText = choices.get(i);
                    int textX = x1 + (maxWidth - font.getWidth(choiceText)) / 2;
                    if (i == currentChoice) {
                        g.setColor(Color.green);
                        g.fillRect(x1 - 10, y1 - 10, maxWidth + 20, font.getHeight(choiceText) + 20);
                        g.setColor(Color.black);
                    } else {
                        g.setColor(Color.white);
                    }
                    g.drawString(choiceText, textX, y1);
                    y1 -= font.getHeight(choiceText) + 2 * margin;
                }
            }
        }
    }

    // Set the choices for the dialog box and a callback for when a choice is made
    public void setChoices(List<String> choices, Consumer<Integer> callback) {
        this.choices = choices;
        this.currentChoice = 0;
        this.choiceCallback = callback;
    }

    // Move to the next message in the dialog box
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

    // Set the visibility of the dialog box
    public void setVisible(boolean visible) {
        this.visible = visible;
        this.isWaitingForInput = true;
    }

    // Check if the dialog box is visible
    public boolean isVisible() {
        return this.visible;
    }

    // Set the trigger zone for the dialog box
    public void setTriggerZone(int x, int y, int width, int height) {
        this.triggerZone = new Rectangle(x, y, width, height);
    }

    // Check if the player is in the trigger zone
    public boolean triggerZone(Rectangle player) {
        return this.triggerZone.intersects(player);
    }

    // Overload to check if the player object is in the trigger zone
    public boolean triggerZone(Player p) {
        return this.triggerZone(p.getHitbox());
    }

    // Get the trigger zone
    public Rectangle gettriggerZone() {
        return this.triggerZone;
    }

    // Invert the canBeReuse flag
    public void invertCanBeReuse() {
        this.canBeReuse = !this.canBeReuse;
    }

    // Get the messages of the dialog box
    public String[] getMessages() {
        return messages;
    }

    // Get the choices of the dialog box
    public List<String> getChoices() {
        return choices;
    }

    // Set the choices of the dialog box
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    // Set the messages of the dialog box and preprocess them
    public void setMessages(String[] messages) {
        this.messages = messages;
        preprocessMessages();
    }

    // Check if the temporary dialog box is active
    public boolean isActiveTempDialogbox() {
        return isActiveTempDialogbox;
    }

    // Set the active state of the temporary dialog box
    public void setActiveTempDialogbox(boolean isTempDialogbox) {
        this.isActiveTempDialogbox = isTempDialogbox;
    }

    // Handle the dialog box interaction
    public void dialogBox(boolean isKeyPressed) {
        if (this.visible && this.isWaitingForInput && isKeyPressed) {
            if (this.currentIndex < this.messages.length - 1) {
                nextMessage();
            } else {
                this.visible = false;
                this.canOpen = false;
                this.currentIndex = 0;
                this.isActiveTempDialogbox = false;
                Global.switchModeControles();
            }
        } else if (this.canOpen && !Global.canMoovDialogbox && ((isKeyPressed && this.triggerZone(Global.P1)) || this.isActiveTempDialogbox)) {
            this.setVisible(true);
            Global.switchModeControles();
        }

        if (!isKeyPressed && this.canBeReuse) {
            this.canOpen = true;
        }
    }

    // Handle the dialog box interaction with input for choices
    public void dialogBox(boolean isKeyPressed, Input input) {
        if (this.visible && this.isWaitingForInput) {
            if (isKeyPressed) {
                if (!choices.isEmpty()) {
                    if (choiceCallback != null) {
                        choiceCallback.accept(currentChoice);
                    }
                    this.visible = false;
                    this.canOpen = false;
                    this.currentIndex = 0;
                    Global.switchModeControles();
                } else if (this.currentIndex < this.messages.length - 1) {
                    nextMessage();
                } else {
                    this.visible = false;
                    this.canOpen = false;
                    this.currentIndex = 0;
                    Global.switchModeControles();
                }
            } else if (input.isKeyPressed(Input.KEY_UP)) {
                if (currentChoice > 0) {
                    currentChoice--;
                }
            } else if (input.isKeyPressed(Input.KEY_DOWN)) {
                if (currentChoice < choices.size() - 1) {
                    currentChoice++;
                }
            }
        } else if (this.canOpen && !Global.canMoovDialogbox && ((isKeyPressed && this.triggerZone(Global.P1)) || this.isActiveTempDialogbox)) {
            this.setVisible(true);
            Global.switchModeControles();
        }

        if (!isKeyPressed && this.canBeReuse) {
            this.canOpen = true;
        }
    }

    // Render the temporary dialog box
    public void renderTempDialgbox(Graphics g) {
        if (this.getMessages().length != 0) {
            this.render(g);
        }
    }

    // Forcefully render the dialog box
    public void renderForceDialogbox(Graphics g) {
        this.render(g);
    }

    // Forcefully handle the dialog box interaction
    public void forceDialogBox(boolean isKeyPressed) {
        if (this.visible && this.isWaitingForInput && isKeyPressed) {
            if (this.currentIndex < this.messages.length - 1) {
                nextMessage();
            } else {
                this.visible = false;
                this.canOpen = false;
                this.currentIndex = 0;
                this.isActiveTempDialogbox = false;
                Global.switchModeControles();
            }
        } else if (this.canOpen && !Global.canMoovDialogbox) {
            this.setVisible(true);
            Global.switchModeControles();
        }

        if (!isKeyPressed && this.canBeReuse) {
            this.canOpen = true;
        }
    }

    // Forcefully handle the dialog box interaction with input for choices
    public void forceDialogBox(boolean isKeyPressed, Input input) {
        if (this.visible && this.isWaitingForInput) {
            if (isKeyPressed) {
                if (!choices.isEmpty()) {
                    if (choiceCallback != null) {
                        choiceCallback.accept(currentChoice);
                    }
                    this.visible = false;
                    this.canOpen = false;
                    this.currentIndex = 0;
                    Global.switchModeControles();
                } else if (this.currentIndex < this.messages.length - 1) {
                    nextMessage();
                } else {
                    this.visible = false;
                    this.canOpen = false;
                    this.currentIndex = 0;
                    Global.switchModeControles();
                }
            } else if (input.isKeyPressed(Input.KEY_UP)) {
                if (currentChoice > 0) {
                    currentChoice--;
                }
            } else if (input.isKeyPressed(Input.KEY_DOWN)) {
                if (currentChoice < choices.size() - 1) {
                    currentChoice++;
                }
            }
        } else if (this.canOpen && !Global.canMoovDialogbox) {
            this.setVisible(true);
            Global.switchModeControles();
        }

        if (!isKeyPressed && this.canBeReuse) {
            this.canOpen = true;
        }
    }

    // Update the temporary dialog box
    public void updateTempDialgbox(boolean i, GameContainer gc) {
        if (this.getChoices().isEmpty()) {
            this.dialogBox(i);
        } else {
            this.dialogBox(i, gc.getInput());
        }
    }

    // Draw the trigger zone for debugging purposes
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(this.triggerZone.getX(), this.triggerZone.getY(), this.triggerZone.getWidth(), this.triggerZone.getHeight());
        g.setColor(Color.white);
    }
}

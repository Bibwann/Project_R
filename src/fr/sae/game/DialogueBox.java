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
    private Rectangle triggerZone;
    private boolean isActiveTempDialogbox;
    private String[] messages;
    private List<String> choices;
    private List<String> preprocessedLines;
    private int currentIndex;
    private boolean visible;
    private boolean isWaitingForInput;
    private boolean canOpen;
    private boolean canBeReuse;
    private int currentChoice;
    private Consumer<Integer> choiceCallback;
    private TrueTypeFont font;
    private int dialogHeight;
    private int dialogWidth;
    private int margin;

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

        // Initialize the font and dimensions once
        this.margin = 20;
        this.dialogHeight = Global.height / 3;
        this.dialogWidth = Global.width - 2 * margin;
        Font awtFont = new Font("Courier New", Font.BOLD, dialogHeight / 10);
        this.font = new TrueTypeFont(awtFont, false);

        preprocessMessages();
    }

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

    public void setChoices(List<String> choices, Consumer<Integer> callback) {
        this.choices = choices;
        this.currentChoice = 0;
        this.choiceCallback = callback;
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

    public boolean triggerZone(Rectangle player) {
        return this.triggerZone.intersects(player);
    }

    public boolean triggerZone(Player p) {
        return this.triggerZone(p.getHitbox());
    }

    public Rectangle gettriggerZone() {
        return this.triggerZone;
    }

    public void invertCanBeReuse() {
        this.canBeReuse = !this.canBeReuse;
    }

    public String[] getMessages() {
        return messages;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
        preprocessMessages();
    }

    public boolean isActiveTempDialogbox() {
        return isActiveTempDialogbox;
    }

    public void setActiveTempDialogbox(boolean isTempDialogbox) {
        this.isActiveTempDialogbox = isTempDialogbox;
    }

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

    public void renderTempDialgbox(Graphics g) {
        if (this.getMessages().length != 0) {
            this.render(g);
        }
    }

    public void renderForceDialogbox(Graphics g) {
        this.render(g);
    }

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

    public void updateTempDialgbox(boolean i, GameContainer gc) {
        if (this.getChoices().isEmpty()) {
            this.dialogBox(i);
        } else {
            this.dialogBox(i, gc.getInput());
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(this.triggerZone.getX(), this.triggerZone.getY(), this.triggerZone.getWidth(), this.triggerZone.getHeight());
        g.setColor(Color.white);
    }
}

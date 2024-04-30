package fr.sae.menus;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class ClassesSelect extends BasicGameState {
    private TrueTypeFont font, titleFont, menuFont;
    private String firstClass="";
    private String secondClass="";
    private String[] descriptions = {"Un guerrier robuste spécialisé dans le combat au corps à corps.",
            "Un combattant impitoyable qui excelle dans la force brute et l'attaque à deux mains.",
            "Un soigneur compétent qui utilise la magie pour restaurer la santé des alliés.",
            "Un lanceur de sorts puissant capable de manipuler les éléments pour infliger des dégâts magiques."};

    private String[] classes = {"Swordman", "Berserker", "Healer", "Mage"};
    private int selectedItemIndex = 0;
    private boolean isFirstClassSelected = false;
    private boolean isConfirmationVisible = false;
    private boolean isConfirmSelected = true;

    public ClassesSelect(int stateID) {
    }
    
    private StateBasedGame game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);

        Font awtTitleFont = new Font("Verdana", Font.BOLD, 36);
        titleFont = new TrueTypeFont(awtTitleFont, true);
        Font awtMenuFont = new Font("Verdana", Font.PLAIN, 24);
        menuFont = new TrueTypeFont(awtMenuFont, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        g.setFont(titleFont);
        g.setColor(Color.green);
        g.drawString("SELECT YOUR CLASS", (gc.getWidth() - titleFont.getWidth("SELECT YOUR CLASS")) / 2, gc.getHeight()*1/10);

        g.setFont(menuFont);
        int y = gc.getHeight()*1/3;
        for (int i = 0; i < classes.length; i++) {
            int x = (gc.getWidth() - menuFont.getWidth(classes[i])) / 2;
            if (i == selectedItemIndex) {
                g.setColor(Color.green);
                g.fillRect(x - 10, y - 10, menuFont.getWidth(classes[i]) + 20, menuFont.getHeight(classes[i]) + 20);
                g.setColor(Color.black);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(classes[i], x, y);
            y += 50;
        }

        g.setColor(Color.white);
        g.drawString("Première classe : " + firstClass, 50, 50);
        g.drawString("Deuxième classe : " + secondClass, 50, 80);

        if (isConfirmationVisible) {
            String confirmOption = "Confirmer";
            String cancelOption = "Annuler";
            int confirmOptionWidth = menuFont.getWidth(confirmOption);
            int cancelOptionWidth = menuFont.getWidth(cancelOption);
            int margin = 50;
            int xConfirm = (gc.getWidth() - confirmOptionWidth) / 2 - 100;
            int xCancel = (gc.getWidth() - cancelOptionWidth) / 2 + 100;
            int yOption = gc.getHeight() - menuFont.getHeight(confirmOption) - margin;
            if (isConfirmSelected) {
                g.setColor(Color.green);
                g.fillRect(xConfirm - 10, yOption - 10, confirmOptionWidth + 20, menuFont.getHeight(confirmOption) + 20);
                g.setColor(Color.black);
                g.drawString(confirmOption, xConfirm, yOption);
                g.setColor(Color.white);
                g.drawString(cancelOption, xCancel, yOption);
            } else {
                g.setColor(Color.green);
                g.fillRect(xCancel - 10, yOption - 10, cancelOptionWidth + 20, menuFont.getHeight(cancelOption) + 20);
                g.setColor(Color.black);
                g.drawString(cancelOption, xCancel, yOption);
                g.setColor(Color.white);
                g.drawString(confirmOption, xConfirm, yOption);
            }
        }

        if (!isConfirmationVisible) {
            g.setColor(Color.white);
            String descriptionTitle = "Description :";
            String description = descriptions[selectedItemIndex];
            int descriptionTitleX = (gc.getWidth() - menuFont.getWidth(descriptionTitle)) / 2;
            int descriptionX = (gc.getWidth() - menuFont.getWidth(description)) / 2;
            int descriptionY = gc.getHeight() - menuFont.getHeight(description) - 100;
            g.drawString(descriptionTitle, descriptionTitleX, descriptionY);
            g.drawString(description, descriptionX, descriptionY + 30);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!isConfirmationVisible) {
            if (key == Input.KEY_UP) {
                selectedItemIndex = (selectedItemIndex - 1 + classes.length) % classes.length;
            } else if (key == Input.KEY_DOWN) {
                selectedItemIndex = (selectedItemIndex + 1) % classes.length;
            } else if (key == Input.KEY_ENTER) {
                if (!isFirstClassSelected) {
                    firstClass = classes[selectedItemIndex];
                    isFirstClassSelected = true;
                } else {
                    if (!classes[selectedItemIndex].equals(firstClass)) {
                        secondClass = classes[selectedItemIndex];
                        isConfirmationVisible = true;
                    }
                }
            }
        } else {
            if (key == Input.KEY_ENTER) {
                if (isConfirmSelected) {                    
                    Global.Player1Classe=firstClass;
                    Global.Player2Classe=secondClass;
                    game.enterState(5);
                } else {
                    isFirstClassSelected = false;
                    firstClass = "";
                    secondClass = "";
                    isConfirmationVisible = false;
                }
            } else if (key == Input.KEY_LEFT || key == Input.KEY_RIGHT) {
                isConfirmSelected = !isConfirmSelected;
            }
        }
    }

    @Override
    public int getID() {
        return 4;
    }
}
package fr.sae.menus;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import fr.sae.game.Global;

public class ClassesSelect extends BasicGameState {
    private TrueTypeFont font;

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
    private boolean isConfirmSelected = true; // Added variable to track the selected option

    public ClassesSelect(int stateID) {
    }
    
    private StateBasedGame game;


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        Font awtFont = new Font("Verdana", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);

        int menuWidth = 200;
        int menuHeight = classes.length * 50; 

        int menuX = (Global.width - menuWidth) / 2;
        int menuY = (Global.height - menuHeight) / 2;

        int y = menuY;
        for (int i = 0; i < classes.length; i++) {
            if (i == selectedItemIndex) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(classes[i], menuX + 50, y);
            y += 50;
        }

        g.setColor(Color.white);
        g.drawString("Première classe : " + firstClass, 50, 50);
        g.drawString("Deuxième classe : " + secondClass, 50, 80);

        // Affichage du bouton de confirmation
        if (isConfirmationVisible) {
        	
            if (isConfirmSelected) {
                g.setColor(Color.green);
                g.drawString("Confirmer", Global.width - 150, 20); 
                g.setColor(Color.white);
                g.drawString("Annuler", Global.width - 250, 20); 
            } else {
                g.setColor(Color.white);
                g.drawString("Confirmer", Global.width - 150, 20); 
                g.setColor(Color.green);
                g.drawString("Annuler", Global.width - 250, 20); 
            }
        }

        // Affichage de la description de la classe survolée
        g.setColor(Color.white);
        
        g.drawString("Description :", 100, Global.height - 100);
        g.drawString(descriptions[selectedItemIndex],120, Global.height - 70); 
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
                    // Choix effectué, ferme l'application
                    
                    Global.Player1Classe=firstClass;
                    Global.Player2Classe=secondClass;

                    
                    game.enterState(5);
                } else {
                    // Réinitialiser les sélectiosns
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

package fr.sae.menus;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import fr.sae.game.Global;

public class ClassesSelect extends BasicGameState {

    private String firstClass="";
    private String secondClass="";
	
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
        new Font("Verdana", Font.BOLD, 24);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);

        int menuWidth = 200; // Largeur du menu
        int menuHeight = classes.length * 50; // Hauteur du menu en fonction du nombre de classes

        // Coordonnées du coin supérieur gauche du menu pour le centrer
        int menuX = (Global.width - menuWidth) / 2;
        int menuY = (Global.height - menuHeight) / 2;

        int y = menuY;
        for (int i = 0; i < classes.length; i++) {
            if (i == selectedItemIndex) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            // Coordonnées de rendu de chaque élément du menu
            g.drawString(classes[i], menuX + 50, y);
            y += 50;
        }

        // Affichage de l'indication des classes sélectionnées
        g.setColor(Color.white);
        g.drawString("Première classe : " + firstClass, 50, 50);
        g.drawString("Deuxième classe : " + secondClass, 50, 80);

        // Affichage du bouton de confirmation
        if (isConfirmationVisible) {
            // Highlight the selected option
            if (isConfirmSelected) {
                g.setColor(Color.green);
                g.drawString("Confirmer", 50, Global.height - 50);
                g.setColor(Color.white);
                g.drawString("Annuler", 150, Global.height - 50);
            } else {
                g.setColor(Color.white);
                g.drawString("Confirmer", 50, Global.height - 50);
                g.setColor(Color.green);
                g.drawString("Annuler", 150, Global.height - 50);
            }
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
                    // Sélection de la première classe principale
                    firstClass = classes[selectedItemIndex];
                    isFirstClassSelected = true;
                } else {
                    // Vérifie si la classe sélectionnée est différente de la première classe
                    if (!classes[selectedItemIndex].equals(firstClass)) {
                        // Sélection de la deuxième classe secondaire
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
                // Toggle the selected option
                isConfirmSelected = !isConfirmSelected;
            }
        }
    }

    @Override
    public int getID() {
        return 4;
    }
}

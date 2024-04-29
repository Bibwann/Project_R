package fr.sae.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Mobs;
import fr.sae.game.caractere.Player;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;

    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // Dessin des éléments de la scène de combat

        // Dessin des joueurs à gauche
    	
    	try {
    	     int player1Y = Global.height / 3; // Position du joueur 1 sur le premier tiers vertical de l'écran
    	     int player2Y = Global.height / 3 * 2; // Position du joueur 2 sur le deuxième tiers vertical de l'écran

    	    // Appel des méthodes BattleScene avec les paramètres appropriés
    	    Global.P1.BattleScene(g, player1Y);
    	    Global.P2.BattleScene(g, player2Y);

    	} catch(Exception e) {
    	    // Affichage de l'erreur
	    	System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try");
	    	System.out.println(e);
	    	// Tentative d'affichage des hitbox
    	    try {

    	    	// Récupération des hitbox des joueurs
    	        Shape hitbox1 = Global.P1.getBattlehitbox();
    	        Shape hitbox2 = Global.P2.getBattlehitbox();
    	        
    	        // Affichage des hitbox
    	        g.draw(hitbox1);
    	        g.draw(hitbox2);
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si aucune hitbox n'est trouvée
    	        throw new RuntimeException("Aucune hitbox trouvée pour l'un des joueurs", ex);
    	    }
    	}


        // Dessin des ennemis --> sait pas si ça marche
    	try {

    	    // Parcours des ennemis et affichage de leur sprite
    	    for (int i = 0; i < this.enemy.length-1; i++) {
    	        if (this.enemy[i] == null) {
    	            continue; // Si l'ennemi est null, passer au suivant
    	        }
    	        // Affichage du sprite de l'ennemi
    	        g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
    	    }

    	} catch(Exception e) {
    	    e.getMessage();
    	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public int getID() {
        return 100;
    }
}

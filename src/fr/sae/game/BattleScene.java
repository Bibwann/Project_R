package fr.sae.game;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Mobs;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;
    private boolean isWin;
    private boolean isLoose;
    private int currentTurn; // Ajout de la variable currentTurn
    private DialogueBox dialogueBox; // Ajout de la variable dialogueBox
    private DialogueBox dialogueBoxTour;
    private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});

    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
        Global.mobs = new Mobs[4];

        this.isWin = isWin = false;
        this.isLoose = isLoose = false;
        this.currentTurn = 0; // Initialisation de currentTurn à 0

        // Initialisation de la DialogueBox
        this.dialogueBox = new DialogueBox(new String[] {
            "C'est votre tour. Que voulez-vous faire ?",
            "C'est le tour de l'ennemi."
        });
        this.dialogueBox.setChoices(Arrays.asList("Attaquer", "Défendre", "Utiliser un sort", "Fuir"), choice -> {
            switch (choice) {
                case 0:
                    // Gérer l'attaque
                	this.tmpDialogbox1.setActiveTempDialogbox(true);
        			this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           Vous avez choisit d'attaquer !"});
                    break;
                case 1:
                    // Gérer la défense
                    break;
                case 2:
                    // Gérer l'utilisation d'un sort
                    break;
                case 3:
                    // Gérer la fuite
                    break;
            }
        });
        
        //exemple, inutile maintenant
        this.dialogueBoxTour = new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           À toi de jouer"
    	});
    	this.dialogueBoxTour.setChoices(Arrays.asList("Taper", "Ne rien faire"), choice1 -> {
    		switch (choice1) {
    		case 0:
    			this.tmpDialogbox1.setActiveTempDialogbox(true);
    			this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           Aie !"});
    			
    			//Ajoutez recursivement des choix ici de la meme maniere que moi
    			
    			this.tmpDialogbox1.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
    				switch (choice2) {
    				
    				case 0:
    					this.tmpDialogbox1.setActiveTempDialogbox(true);
    					this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           AIE !!!!!!!!!"});
    					
    					//Permet de dire qu'il s'agissait du dernier choix
    					this.tmpDialogbox1.setChoices(Arrays.asList(),null);
    					break;
    					
    				case 1:
    					this.tmpDialogbox1.setActiveTempDialogbox(false);
    				}
    			});
    			
    			break;
    			
    		case 1:
    			this.tmpDialogbox1.setActiveTempDialogbox(false);
    			break;
    			
    		}       
    	});
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	
		//this.dialogueBoxTour.renderForceDialogbox(g);
    	//this.dialogueBoxTour.render(g);
    	this.dialogueBox.renderForceDialogbox(g);
    	this.dialogueBox.render(g);

        // Dessin des éléments de la scène de combat

        // Dessin des joueurs à gauche
    	try {
    	    int player1Y = Global.height / 9; 
    	    // Appel de la méthode BattleScene pour le joueur 1
    	    Global.P1.BattleScene(g, player1Y);
    	} catch(Exception e) {
    	    // Affichage de l'erreur
    	    System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try pour P1");
    	    System.out.println(e);
    	    // Tentative d'affichage des hitbox
    	    try {
    	        // Récupération de la hitbox du joueur 1
    	        Shape hitbox1 = Global.P1.getBattlehitbox();
    	        // Affichage de la hitbox
    	        g.draw(hitbox1);
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si aucune hitbox n'est trouvée pour le joueur 1
    	        throw new RuntimeException("Aucune hitbox trouvée pour le joueur 1", ex);
    	    }
    	}

    	try {
    	    int player2Y = Global.height / 9 ;
    	    // Appel de la méthode BattleScene pour le joueur 2
    	    Global.P2.BattleScene(g, player2Y);
    	} catch(Exception e) {
    	    // Affichage de l'erreur
    	    System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try pour P2");
    	    //System.out.println(e);
    	    // Tentative d'affichage des hitbox
    	    try {
    	        // Récupération de la hitbox du joueur 2
    	        Shape hitbox2 = Global.P2.getBattlehitbox();
    	        // Affichage de la hitbox
    	        g.draw(hitbox2);
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si aucune hitbox n'est trouvée pour le joueur 2
    	        throw new RuntimeException("Aucune hitbox trouvée pour le joueur 2", ex);
    	    }
    	}
    	


        // Dessin des ennemis --> sait pas si ça marche
    	try {
    	    // Parcours des ennemis et affichage de leur sprite
    	    for (int i = 0; i < this.enemy.length-1; i++) {
    	        if (this.enemy[i] == null) {
    	            continue; // Si l'ennemi est null, passer au suivant
    	        }
    	        this.enemy[i].setBattlehitbox(new Rectangle(Global.MobsBattleDistance, Global.height/3, 32, 48));
    	        // Affichage du sprite de l'ennemi
    	        g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
    	    }
    	} catch(Exception e) {
    	    System.out.println("pas de sprite pour un mob");
    	    // Tentative de création et d'affichage d'une hitbox
    	    
    	    try {
    	    	for (int i = 0; i < this.enemy.length-1; i++) {
        	        if (this.enemy[i] == null) {
        	            continue; // Si l'ennemi est null, passer au suivant
        	        }
        	        // Affichage du sprite de l'ennemi
        	        //Shape hitbox = new Rectangle(550, 200+i*100, 48, 64);
        	        // Affichage de la hitbox
        	        g.draw(this.enemy[i].getBattleHitbox());
        	    }
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si la hitbox ne peut pas être créée
    	        throw new RuntimeException("Impossible de créer la hitbox", ex);
    	    }
    	}
    	
    	
    	//Si win ou loose
    	if (this.isWin) {
            sbg.enterState(2);
    	}
    	
    	if (this.isLoose) {
            sbg.enterState(2);

    	}
    	
    	this.tmpDialogbox1.renderTempDialgbox(g);
    	this.dialogueBox.renderForceDialogbox(g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
		Input input =gc.getInput();
		boolean i =input.isKeyPressed(Global.interract);

       //this.dialogueBox.dialogBox(i,gc.getInput());

    	 if (currentTurn % 2 == 0) {
             // Afficher la DialogueBox
         } else {
             // Sinon, c'est le tour de l'ennemi
             // Gérer l'interaction de l'ennemi
             // Par exemple, vous pouvez parcourir le tableau enemy et appeler une méthode e.takeTurn() pour chaque ennemi e
         }
    	 
    	 //this.dialogueBoxTour.forceDialogBox(i,gc.getInput());
    	 this.dialogueBox.forceDialogBox(i, gc.getInput());
    	 this.tmpDialogbox1.updateTempDialgbox(i, gc);
    }

    public int getID() {
        return 100;
    }
}

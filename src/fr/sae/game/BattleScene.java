package fr.sae.game;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import fr.sae.game.Global;
import fr.sae.game.caractere.Entity;
import fr.sae.game.caractere.Mobs;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;
    private boolean isWin;
    private boolean isLoose;
    private int currentTurn=0; // Ajout de la variable currentTurn
    private DialogueBox dialogueBox; // Ajout de la variable dialogueBox
    private DialogueBox dialogueBoxTourP1;
    private DialogueBox dialogueBoxTourP2;
    private DialogueBox dialogueBoxTourCurrentMob;
    private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});
    private String P1Name = "";
    private String P2Name = "";
    
    
    
    // turn managment
    private int currentTurnIndex=0;
    protected ArrayList<Entity> entities = new ArrayList<>();


    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
        

        this.isWin = isWin = false;
        this.isLoose = isLoose = false;
        this.currentTurn = 0; // Initialisation de currentTurn à 0

        // Initialisation de la DialogueBox
        this.dialogueBox = new DialogueBox(new String[] {
            "C'est votre tour. Que voulez-vous faire ?",
            "C'est le tour de l'ennemi."
        });
<<<<<<< HEAD
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
=======
       
        
        this.dialogueBoxTourP1 = new DialogueBox(new String[] {
>>>>>>> 240c07990c53f379bf58945784e20272b01881f3
    			"\n "+
    					"     \n" +
    					"           C'est au tour de P1 de jouer"
    	});
    	this.dialogueBoxTourP1.setChoices(Arrays.asList("Attaquer", "Défendre", "Utiliser un sort", "Fuir"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 1:
                // Gérer la défense
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 2:
                // Gérer l'utilisation d'un sort
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 3:
                // Gérer la fuite
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	this.dialogueBoxTourP2 = new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           C'est au tour de P2 de jouer"
    	});
    	this.dialogueBoxTourP2.setChoices(Arrays.asList("Attaquer", "Défendre", "Utiliser un sort", "Fuir"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 1:
                // Gérer la défense
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 2:
                // Gérer l'utilisation d'un sort
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
            case 3:
                // Gérer la fuite
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	this.dialogueBoxTourCurrentMob= new DialogueBox(new String[] {
    			"\n "+
    					"     \n" +
    					"           C'est au tour de bob de jouer"
    	});
    	
    	this.dialogueBoxTourCurrentMob.setChoices(Arrays.asList("Continuer"), choice -> {
            switch (choice) {
            case 0:
                // Gérer l'attaque
            	this.tmpDialogbox1.setActiveTempDialogbox(false);
    			this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()); // sert à incrémenter les tours (oui juste ça)

                break;
        }
    });
    	
    	
    }
    
    public void initializeBattle() {
        if(this.entities.isEmpty()) {
        	entities.add(Global.P1);
            
            for(int i=0; i < this.enemy.length; i++) {
            	if(i == 1 && this.entities.indexOf(Global.P2) == -1) {
            		entities.add(Global.P2);
            		i--;
            	} else if(this.enemy[i]!= null) {
            		entities.add(this.enemy[i]);
            	}
            }
        }
        
        //this.P1Name = Global.P1.getName();
        //this.P2Name = Global.P2.getName();
        
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
<<<<<<< HEAD
    	
		//this.dialogueBoxTour.renderForceDialogbox(g);
    	//this.dialogueBoxTour.render(g);
    	this.dialogueBox.renderForceDialogbox(g);
    	this.dialogueBox.render(g);
=======
        g.drawImage(new Image("data/BattleScenes/Foret.png").getScaledCopy(Global.width, Global.height), 0, 0);
        this.initializeBattle();
        // gère l'affichage de la dialogBox pour l'entité qui joue le tour

        if(this.currentTurnIndex == 0) {        	
        	this.dialogueBoxTourP1.renderForceDialogbox(g);
        	this.dialogueBoxTourP1.render(g);
        } else if(this.currentTurnIndex == 2) {        	
        	this.dialogueBoxTourP2.renderForceDialogbox(g);
        	this.dialogueBoxTourP2.render(g);
        } else {
        	this.dialogueBoxTourCurrentMob.renderForceDialogbox(g);
        	this.dialogueBoxTourCurrentMob.render(g);
        }
    	//this.dialogueBox.render(g);
>>>>>>> 240c07990c53f379bf58945784e20272b01881f3

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
<<<<<<< HEAD
    	    System.out.println("pas de sprite pour un mob");
=======
    	    //System.out.println(e.getMessage());
>>>>>>> 240c07990c53f379bf58945784e20272b01881f3
    	    // Tentative de création et d'affichage d'une hitbox
    	    
    	    try {
    	    	for (int i = 0; i < this.enemy.length-1; i++) {
        	        if (this.enemy[i] == null) {
        	            continue; // Si l'ennemi est null, passer au suivant
        	        }
        	        // Affichage du sprite de l'ennemi
<<<<<<< HEAD
        	        //Shape hitbox = new Rectangle(550, 200+i*100, 48, 64);
=======
        	        Shape hitbox = new Rectangle(1400, 200+i*200, 48, 64);
>>>>>>> 240c07990c53f379bf58945784e20272b01881f3
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
    	//this.dialogueBox.renderForceDialogbox(g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		boolean boolInput =input.isKeyPressed(Global.interract);
    	
		if(this.currentTurnIndex == 0) {			
			this.dialogueBoxTourP1.forceDialogBox(boolInput,gc.getInput());
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // sert à incrémenter les tours (oui juste ça)

		} else if (this.currentTurnIndex == 2) {
			this.dialogueBoxTourP2.forceDialogBox(boolInput,gc.getInput());
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // mais en ne depassant pas le nombre d'entités -1
		} else {
			this.dialogueBoxTourCurrentMob.forceDialogBox(boolInput,gc.getInput());
			//this.currentTurnIndex = (this.currentTurnIndex + 1 ) % (this.entities.size()-1); // oui c'est moche
		}
    	
    	this.tmpDialogbox1.updateTempDialgbox(boolInput, gc);
    	 
<<<<<<< HEAD
    	 //this.dialogueBoxTour.forceDialogBox(i,gc.getInput());
    	 this.dialogueBox.forceDialogBox(i, gc.getInput());
    	 this.tmpDialogbox1.updateTempDialgbox(i, gc);
=======
>>>>>>> 240c07990c53f379bf58945784e20272b01881f3
    }

    public int getID() {
        return 100;
    }
}

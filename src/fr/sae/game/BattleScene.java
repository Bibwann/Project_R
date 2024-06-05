package fr.sae.game;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.Sys;
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
import fr.sae.game.caractere.Player;
import fr.sae.mobes.Chaton;

public class BattleScene extends BasicGameState {
    private Mobs[] enemy;
    private int currentTurn=0; // Ajout de la variable currentTurn
    
    private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {"Le combat commence"});
    private DialogueBox tmpDialogbox2= new DialogueBox(new String[] {});

    
    private String P1Name = "";
    private String P2Name = "";
    private int P1Potions = 5;
    private int P2Potions = 5;
    private int playedTurn = 0;
    
    
    
    // turn managment
    private int currentTurnIndex=0;
    protected ArrayList<Entity> entities = new ArrayList<>();


    public BattleScene(int stateID) {
        this.enemy = Global.mobs;
        this.currentTurn = 0; // Initialisation de currentTurn à 0
        
    	
    }
    
    
    
    public void initializeBattle() {

        if(this.entities.isEmpty()) {
        	
        	this.tmpDialogbox1.setActiveTempDialogbox(true);
        	
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
    }
        
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
    	this.tmpDialogbox1.setTriggerZone(-1, -1, 0, 0);
    	this.tmpDialogbox2.setTriggerZone(-1, -1, 0, 0);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("data/BattleScenes/Bottom.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/BattleScenes/Foret.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        this.initializeBattle();
        // gère l'affichage de la dialogBox pour l'entité qui joue le tour

        // Dessin des joueurs à gauche
    	try {
    	    int player1Y = Global.height / 3; // Position du joueur 1 sur le premier tiers vertical de l'écran
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
    	    int player2Y = Global.height / 3 * 2; // Position du joueur 2 sur le deuxième tiers vertical de l'écran
    	    // Appel de la méthode BattleScene pour le joueur 2
    	    Global.P2.BattleScene(g, player2Y);
    	} catch(Exception e) {
    	    // Affichage de l'erreur
    	    System.out.println("Affichage des Hitbox prsk sprites ont buggé --> on est dans la classe Battle scene dans le render le 1er try pour P2");
    	    System.out.println(e);
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
    	        // Affichage du sprite de l'ennemi
    	        try {
    	        	this.enemy[i].BattleScene(g, Global.height / this.enemy.length * (i+1));
    	        }catch(Exception e) {
    	        	e.getMessage();
    	        }
    	        //g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
    	    }
    	} catch(Exception e) {
    	    //System.out.println(e.getMessage());
    	    // Tentative de création et d'affichage d'une hitbox
    	    
    	    try {
    	    	for (int i = 0; i < this.enemy.length-1; i++) {
        	        if (this.enemy[i] == null) {
        	            continue; // Si l'ennemi est null, passer au suivant
        	        }
        	        // Affichage du sprite de l'ennemi
        	        Shape hitbox = new Rectangle(1400, 400+i*200, 48, 64);
        	        // Affichage de la hitbox
        	        g.draw(hitbox);
        	    }
    	    } catch(Exception ex) {
    	        // Génération d'une nouvelle exception si la hitbox ne peut pas être créée
    	        throw new RuntimeException("Impossible de créer la hitbox", ex);
    	    }
    	}
    	
    	//Si win 
    	if (this.isWin()) {
    		Global.canMoovPlayer=true;
    		Global.P1.resetStats();
    		Global.P2.resetStats();
    		Global.mobs = new Mobs[4];
    		this.entities =  new ArrayList<>();
            sbg.enterState(Global.actualId);
    	}
    	
    	//Si loose
    	if (this.isLoose()) {
    		Global.P1.resetStats();
    		Global.P2.resetStats();
    		Global.mobs = new Mobs[4];
    		this.entities =  new ArrayList<>();
            sbg.enterState(Global.actualId);
    	}
    	
    	if(!this.isWin() || !this.isLoose()) {    		
    		this.tmpDialogbox1.renderTempDialgbox(g);
    		this.tmpDialogbox1.renderTempDialgbox(g);

    		tour(g);
    	}

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input input =gc.getInput();
		boolean boolInput =input.isKeyPressed(Global.interract);

    	this.tmpDialogbox1.updateTempDialgbox(boolInput, gc);
    	this.tmpDialogbox2.updateTempDialgbox(boolInput,gc);

    	Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapBattlescene,delta);
    	 
    }
    
    public boolean isWin() {
    	for (int i = 0; i < this.enemy.length; i++) {
    		if (!(enemy[i].isDead())) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean isLoose() {
    	return (Global.P1.isDead() && Global.P2.isDead());
	}

    public int getID() {
        return 100;
    }
    
    public void tour(Graphics g) {
    					
    	for(Entity e : this.entities) {
    		if (e instanceof Player) {
    			if (e.equals(Global.P1)) {

    				this.tmpDialogbox1=new DialogueBox(new String[] {
    						"\n "+
    							    "     \n" +
    							    "           Au tour de"+ Global.Player1Classe+" de jouer"
    					        });

    				this.tmpDialogbox1.setChoices(Arrays.asList("Attaquer", "Se soigner"), choice1 -> {
    		            switch (choice1) {
    			            case 0:
    			                this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           Aie !"});

    			                this.tmpDialogbox1.setChoices(Arrays.asList("Retaper la branche", "Ne rien faire"), choice2 -> {
    			                    switch (choice2) {

    			                        case 0:
    			                            this.tmpDialogbox1.setMessages(new String[] {"\n"+"\n"+"           AIE !!!!!!!!!"});
    			                            
    			                            //Permet de dire qu'il s'agissait du dernier choix
    			                            this.tmpDialogbox1.setChoices(Arrays.asList(),null);
    			                            break;

    			                        case 1:
    			                        	break;

    			                    }
    			                    
    			                });
    			                
    			                break;
    			                
    			            case 1:
    			            	break;
    		            }       
    		     });

    			}else {
    				
    			}
    			
    		}
    		else if (e instanceof Mobs) {
    			
    			
    		}
    		
    	}

    }
}

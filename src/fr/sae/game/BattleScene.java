package fr.sae.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
import fr.sae.mobes.Chaton;

public class BattleScene extends BasicGameState {

    private Mobs[] enemy;
  
    // turn managment
    private int currentTurnIndex = 0;
    private boolean playedTurn = true;
    private int P1Potions = 5;
    private int P2Potions = 5;
    private int EnemiesPotions = 0;
    private int hit;
    private int confusedDebuf = 0;
    private String enemyMessage;
    private List<String> enemyNames = new ArrayList<String>();
    private List<String> aliveEnemyNames = new ArrayList<String>();


    private List<String> deadEnemyNames = new ArrayList<String>();
    
    protected ArrayList<Entity> entities = new ArrayList<>();
    
    private DialogueBox tmpDialogbox1= new DialogueBox(new String[] {});


    public BattleScene(int stateID) {
        this.enemy = Global.mobs;    	
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
            		this.enemyNames.add(this.enemy[i].getName());
            		System.err.println(this.enemy[i].getName());
            	}
            }
            this.aliveEnemyNames = new ArrayList<>(this.enemyNames); // Copie de enemyNames pour éviter la modification concurrente

        }
    }
        
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Initialisation des ressources de la scène de combat
	    this.tmpDialogbox1.setTriggerZone(-1, -1, 0, 0);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("data/BattleScenes/Bottom.png").getScaledCopy(Global.width, Global.height), 0, 0);
        g.drawImage(new Image("data/BattleScenes/Foret.png").getScaledCopy(Global.width, Global.height), 0, 0);
        
        this.initializeBattle();
        // gère l'affichage de la dialogBox pour l'entité qui joue le tour
        
    	//this.dialogueBox.render(g);
        this.tmpDialogbox1.renderTempDialgbox(g);

        // Dessin des éléments de la scène de combat

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
    	    int player2Y = Global.height / 2 ; // Position du joueur 2 sur le deuxième tiers vertical de l'écran
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
    	


        // Dessin des ennemis 
    	try {
    	    // Parcours des ennemis et affichage de leur sprite
    	    for (int i = 0; i < this.enemy.length-1; i++) {
    	        if (this.enemy[i] == null) {
    	        	// Affichage du sprite de l'ennemi
    	        	continue;
    	        }
    	        try {
    	        	this.enemy[i].BattleScene(g, Global.height / (this.enemy.length + 1) * (i+1) + 150);
    	        }catch(Exception e) {
    	        	e.getMessage();
    	        }
    	        // Affichage du sprite de l'ennemi
//    	        g.drawImage(this.enemy[i].getSprite(), 550, 200 + i * 100);
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
//    		this.entities =  new ArrayList<>();
            sbg.enterState(Global.actualId);
    	}
    	
    	//Si loose
    	if (Global.P1.isDead() && Global.P2.isDead()) {
    		Global.P1.resetStats();
    		Global.P2.resetStats();
    		Global.mobs = new Mobs[4];
    		//this.entities =  new ArrayList<>();
            sbg.enterState(7);
    	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input =gc.getInput();
		boolean boolInput =input.isKeyPressed(Global.interract);
		
    	Global.updatePlayerMovement(gc.getInput(),Global.CollisionMapForet2,delta);
		Global.P1.AnimateWhileMoove();
		
		if(this.playedTurn && !this.entities.isEmpty() && !this.enemyNames.isEmpty()) {
			turn();
		}			
		
		this.tmpDialogbox1.updateTempDialgbox(boolInput, gc);
//		if(this.dialogInterract) {
//			this.init(gc, sbg);
//		}
		
    	 
    }
    
    public boolean isWin() {
    	for (int i = 0; i < this.entities.size() - 2; i++) {
    		//System.out.println(enemy[i].getName() + ' ' + enemy[i].getHpActuel() + ' ' + enemy[i].isDead());
    		if (!(enemy[i].isDead())) {
    			return false;
    		}
    	}
    	return true;
    }

    public void turn() {

    	this.playedTurn = false;
    	this.tmpDialogbox1.setActiveTempDialogbox(true);
    	if(this.currentTurnIndex == 0 && !Global.P1.isDead()) {
    		// Player 1 turn 
    		this.tmpDialogbox1.setMessages(new String[] {"C'est au tour de votre " + Global.P1.getClassName() + "\nHP: " + Global.P1.getHpActuel() + "/" + Global.P1.getHpMax()});
        	
        	this.tmpDialogbox1.setChoices(Arrays.asList("Attaquer", "Se soigner (" + this.P1Potions + ")", "Passer"), choice -> {
        		switch (choice) {
        		case 0: // Attack the enemies
        			this.hit = Global.P1.getDmg();
        			
//        			this.tmpDialogbox1.setActiveTempDialogbox(true);
        			
        			this.tmpDialogbox1.setMessages(new String[] {"Vous préparez votre coup, il est trop tard pour faire machine arrière ! \nQuel enemi allez-vous attaquer maintenant?"});
        			
        			
        			this.tmpDialogbox1.setChoices(this.aliveEnemyNames, choice2 -> {

        				for (int i = 0; i < Global.mobs.length; i++) {

        					if(Global.mobs[i]!=null) {

	        				    if (Global.mobs[i].getName()==(this.aliveEnemyNames.get(choice2))) {
	        				        choice2 = i;
	        				        break; 
	        				    }
        					}
        				}
        				
        				
        				switch (choice2) {	
        				case 0:
        					this.enemy[0].getHit(this.hit);
        					
//        					this.tmpDialogbox1.setActiveTempDialogbox(true);
                			if(this.enemy[0].isDead()) {
                				this.deadEnemyNames.add(this.enemyNames.get(0));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(0));

                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(0) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(0) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
        				
	        			case 1:
	    					this.enemy[1].getHit(Global.P1.getDmg());
	    					
//	    					this.tmpDialogbox1.setActiveTempDialogbox(true);
	    					if(this.enemy[1].isDead()) {
	    						this.deadEnemyNames.add(this.enemyNames.get(1));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(1));
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(1) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    					}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(1) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
	    				
	        			case 2:

	        				this.enemy[2].getHit(this.hit);
        					
//        					this.tmpDialogbox1.setActiveTempDialogbox(true);
                			if(this.enemy[2].isDead()) {
                				this.deadEnemyNames.add(this.enemyNames.get(2));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(2));

                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(2) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(2) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
                				
                		
        		
			        	case 3:
this.enemy[2].getHit(this.hit);
        					
//        					this.tmpDialogbox1.setActiveTempDialogbox(true);
                			if(this.enemy[2].isDead()) {
                				this.deadEnemyNames.add(this.enemyNames.get(2));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(2));

                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(2) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(2) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
						}
        			});
        			break;
        			
        		case 1: // Heal the first player
//        			this.tmpDialogbox1.setActiveTempDialogbox(true);
        			Global.P1.healEntity();
        			
        			if(this.P1Potions == 0) {
        				this.tmpDialogbox1.setMessages(new String[] { "Vous sentez la dernière goute de potion s'évaporer sur votre langue mais votre état ne s'améliore pas ! \n"
        						+ "Votre tour est passé, cheh."});
            			
            			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
            				switch (choice2) {
            				
            				case 0:
            					this.tmpDialogbox1.setActiveTempDialogbox(false);
            	    			this.playedTurn = true;
            					break;
            				}
            				
            				});        					
    					break;
        			} else {
        				this.P1Potions --;   
        				
        				this.tmpDialogbox1.setMessages(new String[] { "Vous êtes soigné et avez maintenant " + Global.P1.getHpActuel() + " HP !"});
            			
            			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
            				switch (choice2) {
            				
            				case 0:
            					this.tmpDialogbox1.setActiveTempDialogbox(false);
            	    			this.playedTurn = true;
            					break;
            					}
            				
            				});        					
    					break;
        			}   
        		case 2: // Passer son tour
        			
        			this.confusedDebuf += 4;
        			
        			this.tmpDialogbox1.setMessages(new String[] { "Vous passez votre tour, \nMais pourquoi ? L'enemi ne comprend pas et semble destabilisé"});
        				
        			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
        				switch (choice2) {
        				
        				case 0:
        					this.tmpDialogbox1.setActiveTempDialogbox(false);
        	    			this.playedTurn = true;
        					break;
        				}
        				
        				});        					
					break;
        			
        		}
        		
        	});
    		
    		// Increment turn
    		this.currentTurnIndex ++;
    	} else if (this.currentTurnIndex == 2 && !Global.P2.isDead()) {
    		
    		// Player 2 turn
    		
    		this.tmpDialogbox1.setMessages(new String[] {"C'est au tour de votre " + Global.P2.getClassName() + "\nHP: " + Global.P2.getHpActuel() + "/" + Global.P2.getHpMax()});
        	this.tmpDialogbox1.setChoices(Arrays.asList("Attaquer", "Se soigner (" + this.P2Potions + ")", "Passer"), choice -> {
        		switch (choice) {
        		case 0: // Attack the enemies
        			this.hit = Global.P2.getDmg();
        			
//        			this.tmpDialogbox1.setActiveTempDialogbox(true);

        			this.tmpDialogbox1.setMessages(new String[] {"Vous préparez votre coup, il est trop tard pour faire machine arrière ! \nQuel enemi allez-vous attaquer maintenant?"});
        		
        			
        			this.tmpDialogbox1.setChoices(this.aliveEnemyNames, choice2 -> {

        				for (int i = 0; i < Global.mobs.length; i++) {
        					if(Global.mobs[i]!=null) {

	        				    if (Global.mobs[i].getName()==(this.aliveEnemyNames.get(choice2))) {
	        				        choice2 = i;
	        				        break; 
	        				    }
        					}
        				}
        				
        				switch (choice2) {
        				case 0:
        					this.enemy[0].getHit(this.hit);
        					
//        					this.tmpDialogbox1.setActiveTempDialogbox(true);
                			if(this.enemy[0].isDead()) {
                				this.deadEnemyNames.add(this.enemyNames.get(0));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(0));

                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(0) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(0) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
        				
	        			case 1:
	    					this.enemy[1].getHit(this.hit);
	    					
//	    					this.tmpDialogbox1.setActiveTempDialogbox(true);
	    					if(this.enemy[1].isDead()) {
	    						this.deadEnemyNames.add(this.enemyNames.get(1));
	    						this.aliveEnemyNames.remove(this.enemyNames.get(1));

                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(1) + " a été vaincu !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			} else {
                				this.tmpDialogbox1.setMessages(new String[] { this.enemyNames.get(1) + " à reçu " + hit + " dégats !"});
                    			
                    			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                    				switch (choice3) {
                    				
                    				case 0:
                    					this.tmpDialogbox1.setActiveTempDialogbox(false);
                    	    			this.playedTurn = true;
                    					break;
                    				}
                    				
                    				});        					
            					break;
                			}
	    				
	        			case 2:
//	    					this.enemy[2].getHit(Global.P1.getDmg());
	    					
//	    					this.tmpDialogbox1.setActiveTempDialogbox(true);
	        				this.deadEnemyNames.add(this.enemyNames.get(2));
    						this.aliveEnemyNames.remove(this.enemyNames.get(2));

                			this.tmpDialogbox1.setMessages(new String[] {this.enemyNames.get(2) + " à reçu " + hit + " dégats !"});
                			
                			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                				switch (choice3) {
                				
                				case 0:
//                					this.tmpDialogbox1.setActiveTempDialogbox(false);
                	    			this.playedTurn = true;
                					break;
                				}
                				
                				});   
	    					break;
        		
			        	case 3:
//							this.enemy[3].getHit(Global.P1.getDmg());
							
//							this.tmpDialogbox1.setActiveTempDialogbox(true);
			        		this.deadEnemyNames.add(this.enemyNames.get(3));
    						this.aliveEnemyNames.remove(this.enemyNames.get(3));

                			this.tmpDialogbox1.setMessages(new String[] {this.enemyNames.get(3) + " à reçu " + hit + " dégats !"});
                			
                			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice3 -> {
                				switch (choice3) {
                				
                				case 0:
                					this.tmpDialogbox1.setActiveTempDialogbox(false);
                	    			this.playedTurn = true;
                					break;
                				}
                				
                				});   
							break;
						}
        			});
        			break;
        			
        		case 1: // Heal the second player
//        			this.tmpDialogbox1.setActiveTempDialogbox(true);
        			Global.P2.healEntity();
        			
        			if(this.P2Potions == 0) {
        				this.tmpDialogbox1.setMessages(new String[] { "Vous sentez la dernière goute de potion s'évaporer sur votre langue mais votre état ne s'améliore pas ! \n"
        						+ "Votre tour est passé, cheh."});
            			
            			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
            				switch (choice2) {
            				
            				case 0:
            					this.tmpDialogbox1.setActiveTempDialogbox(false);
            	    			this.playedTurn = true;
            					break;
            				}
            				
            				});        					
    					break;
        			} else {
        				this.P2Potions --;   
        				
        				this.tmpDialogbox1.setMessages(new String[] { "Vous êtes soigné et avez maintenant " + Global.P2.getHpActuel() + " HP !"});
            			
            			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
            				switch (choice2) {
            				
            				case 0:
            					this.tmpDialogbox1.setActiveTempDialogbox(false);
            	    			this.playedTurn = true;
            					break;
            				}
            				
            				});        					
    					break;
        			}   
        		case 2: // Passer son tour
        			
        			this.confusedDebuf += 7;
        			
        			this.tmpDialogbox1.setMessages(new String[] { "Vous passez votre tour, \nMais pourquoi ? L'enemi ne comprend pas et semble destabilisé"});
        				
        			this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
        				switch (choice2) {
        				
        				case 0:
        					this.tmpDialogbox1.setActiveTempDialogbox(false);
        	    			this.playedTurn = true;
        					break;
        				}
        				
        				});        					
					break;
        			
        		}
        		
        	}); 

    		
    		
    		// Increment turn
//        	this.playedTurn = true;
    		if(this.currentTurnIndex + 1 == this.entities.size()) {
    			this.currentTurnIndex = 0;
    		} else {
    			this.currentTurnIndex ++;
    		}
    	} else if (!this.entities.get(this.currentTurnIndex).isDead()) {
    		
//    		this.tmpDialogbox1.setActiveTempDialogbox(true);
    		
    		this.enemyMessage = "C'est le tour de ";
    		
    		for(int i = 0; i < this.entities.size()-2; i++) {    	
    			if(this.enemy[i].equals( this.entities.get(this.currentTurnIndex))) {
    				this.enemyMessage += this.enemyNames.get(i) + "\nHP: " + this.entities.get(this.currentTurnIndex).getHpActuel() + "/" + this.entities.get(this.currentTurnIndex).getHpMax() + "\n\n";
    			}
    		}
    	
    		// if the enemy has more than half of his hp then it will attack the weakest player
    		// Basically this is bullying  
    		
    		if(this.entities.get(this.currentTurnIndex).getHpActuel() < this.entities.get(this.currentTurnIndex).getHpMax() / 2 && this.EnemiesPotions > 0 ) {
    			this.entities.get(this.currentTurnIndex).healEntity();
    			this.EnemiesPotions --;
    			
    			this.enemyMessage += "Il décide de se soigner et possède maintenant " + this.entities.get(this.currentTurnIndex).getHpActuel() + " HP !";
    			
    			
    		} else {
    			hit = this.entities.get(this.currentTurnIndex).getDmg() - this.confusedDebuf;
    			if((Global.P1.getHpActuel() < Global.P2.getHpActuel() && !Global.P1.isDead()) || Global.P2.isDead()) {
    				Global.P1.getHit(hit);
    				this.enemyMessage += "Il attaque votre " + Global.P1.getClassName() + " et lui inflige " + this.hit + " Dégats !\n";
    				if(Global.P1.isDead()) {
    					this.enemyMessage += "Ce coup fût fatal !";
    				} else {
    					this.enemyMessage += "Il possède maintenant " + Global.P1.getHpActuel() + " HP !";
    				}
    			} else {
    				Global.P2.getHit(hit);
    				this.enemyMessage += "Il attaque votre " + Global.P2.getClassName() + " et lui inflige " + this.hit + " Dégats !\n";
    				if(Global.P2.isDead()) {
    					this.enemyMessage += "Ce coup fût fatal !";
    				} else {
    					this.enemyMessage += "Il possède maintenant " + Global.P2.getHpActuel() + " HP !";
    				}
    				
    			}
    		}
    			
    			
    			
    			
        	this.tmpDialogbox1.setMessages(new String[] {this.enemyMessage});       	
        	this.tmpDialogbox1.setChoices(Arrays.asList("Continuer"), choice2 -> {
				switch (choice2) {
				
				case 0:
					this.tmpDialogbox1.setActiveTempDialogbox(false);
					this.playedTurn = true;
					break;
				}
				
				});      
    		
    		// Mob turn
    		
    		// Increment turn
    		if(this.currentTurnIndex + 1 == this.entities.size()) {
    			this.currentTurnIndex = 0;
    		} else {
    			this.currentTurnIndex ++;
    		}
    	} else {
    		
    		this.playedTurn = true;
    		if(this.currentTurnIndex + 1 == this.entities.size()) {
    			this.currentTurnIndex = 0;
    		} else {
    			this.currentTurnIndex ++;
    		}
    	}

    	
    }
    
              


    public int getID() {
        return 100;
    }
}
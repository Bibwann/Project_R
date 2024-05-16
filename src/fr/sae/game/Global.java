package fr.sae.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;

import fr.sae.game.caractere.Berserker;
import fr.sae.game.caractere.Healer;
import fr.sae.game.caractere.Mage;
import fr.sae.game.caractere.Mobs;
import fr.sae.game.caractere.Player;
import fr.sae.game.caractere.Swordsman;
import fr.sae.game.maps.Foret1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Global {
	
	public static Player P1 = null;
	public static Player P2 = null;

	public static float speed =1.11f; //Vitesse du Player
	
	//Coordonee de spawn du Player 1 ( le tout premier spawn)
	public static int SpawnX =375;
	public static int SpawnY =280;
	
	//Variables definissant si on doit utiliser les touches de mouvements pour se deplacer dans les dialogbox ou pour deplacer le personnage
	
	public static boolean canMoovPlayer =true;
	public static boolean canMoovDialogbox =false;

	//Variable definissant la distance compare au bord gauche de l'ecran qu'auront les joueurs et les mobes en combat
	public static int PlayerBattleDistance =250;
	public static int MobsBattleDistance =750;

	//Variables global d'input
	
	public static int up =Input.KEY_UP;
	public static int down =Input.KEY_DOWN;
	public static int left =Input.KEY_LEFT;
	public static int right =Input.KEY_RIGHT;
	
	public static int pause =Input.KEY_ESCAPE;
	public static int interract =Input.KEY_SPACE;
	
	//Hashmap permettant le changement de touches proprement
    public static Map<String, Integer> dictionnaireInputs = new HashMap<String, Integer>() {

		private static final long serialVersionUID = 1L;

	{
        put("UP", Input.KEY_UP);
        put("DOWN", Input.KEY_DOWN);
        put("LEFT", Input.KEY_LEFT);
        put("RIGHT", Input.KEY_RIGHT);
        put("SPACE", Input.KEY_SPACE);
        put("A", Input.KEY_A);
        put("B", Input.KEY_B);
        put("C", Input.KEY_C);
        put("D", Input.KEY_D);
        put("E", Input.KEY_E);
        put("F", Input.KEY_F);
        put("G", Input.KEY_G);
        put("H", Input.KEY_H);
        put("I", Input.KEY_I);
        put("J", Input.KEY_J);
        put("K", Input.KEY_K);
        put("L", Input.KEY_L);
        put("M", Input.KEY_M);
        put("N", Input.KEY_N);
        put("O", Input.KEY_O);
        put("P", Input.KEY_P);
        put("Q", Input.KEY_Q);
        put("R", Input.KEY_R);
        put("S", Input.KEY_S);
        put("T", Input.KEY_T);
        put("U", Input.KEY_U);
        put("V", Input.KEY_V);
        put("W", Input.KEY_W);
        put("X", Input.KEY_X);
        put("Y", Input.KEY_Y);
        put("Z", Input.KEY_Z);
        put("0", Input.KEY_0);
        put("1", Input.KEY_1);
        put("2", Input.KEY_2);
        put("3", Input.KEY_3);
        put("4", Input.KEY_4);
        put("5", Input.KEY_5);
        put("6", Input.KEY_6);
        put("7", Input.KEY_7);
        put("8", Input.KEY_8);
        put("9", Input.KEY_9);
        put("TAB", Input.KEY_TAB);
        put("ENTER", Input.KEY_ENTER);
        put("ESCAPE", Input.KEY_ESCAPE);
        put("INSERT", Input.KEY_INSERT);
        put("DELETE", Input.KEY_DELETE);
        put("HOME", Input.KEY_HOME);
        put("END", Input.KEY_END);
        put("F1", Input.KEY_F1);
        put("F2", Input.KEY_F2);
        put("F3", Input.KEY_F3);
        put("F4", Input.KEY_F4);
        put("F5", Input.KEY_F5);
        put("F6", Input.KEY_F6);
        put("F7", Input.KEY_F7);
        put("F8", Input.KEY_F8);
        put("F9", Input.KEY_F9);
        put("F10", Input.KEY_F10);
        put("F11", Input.KEY_F11);
        put("F12", Input.KEY_F12);
        put("NUMPAD0", Input.KEY_NUMPAD0);
        put("NUMPAD1", Input.KEY_NUMPAD1);
        put("NUMPAD2", Input.KEY_NUMPAD2);
        put("NUMPAD3", Input.KEY_NUMPAD3);
        put("NUMPAD4", Input.KEY_NUMPAD4);
        put("NUMPAD5", Input.KEY_NUMPAD5);
        put("NUMPAD6", Input.KEY_NUMPAD6);
        put("NUMPAD7", Input.KEY_NUMPAD7);
        put("NUMPAD8", Input.KEY_NUMPAD8);
        put("NUMPAD9", Input.KEY_NUMPAD9);
        put("ADD", Input.KEY_ADD);
        put("SUBTRACT", Input.KEY_SUBTRACT);
        put("MULTIPLY", Input.KEY_MULTIPLY);
        put("DIVIDE", Input.KEY_DIVIDE);
        put("EQUALS", Input.KEY_EQUALS);
        put("COMMA", Input.KEY_COMMA);
        put("PERIOD", Input.KEY_PERIOD);
        put("SEMICOLON", Input.KEY_SEMICOLON);
        put("SLASH", Input.KEY_SLASH);
        put("MINUS", Input.KEY_MINUS);
        put("SPACE", Input.KEY_SPACE);
    }};

    public static Map<Integer, String> reversedInputs = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(Input.KEY_UP, "UP");
            put(Input.KEY_DOWN, "DOWN");
            put(Input.KEY_LEFT, "LEFT");
            put(Input.KEY_RIGHT, "RIGHT");
            put(Input.KEY_SPACE, "SPACE");
            put(Input.KEY_A, "A");
            put(Input.KEY_B, "B");
            put(Input.KEY_C, "C");
            put(Input.KEY_D, "D");
            put(Input.KEY_E, "E");
            put(Input.KEY_F, "F");
            put(Input.KEY_G, "G");
            put(Input.KEY_H, "H");
            put(Input.KEY_I, "I");
            put(Input.KEY_J, "J");
            put(Input.KEY_K, "K");
            put(Input.KEY_L, "L");
            put(Input.KEY_M, "M");
            put(Input.KEY_N, "N");
            put(Input.KEY_O, "O");
            put(Input.KEY_P, "P");
            put(Input.KEY_Q, "Q");
            put(Input.KEY_R, "R");
            put(Input.KEY_S, "S");
            put(Input.KEY_T, "T");
            put(Input.KEY_U, "U");
            put(Input.KEY_V, "V");
            put(Input.KEY_W, "W");
            put(Input.KEY_X, "X");
            put(Input.KEY_Y, "Y");
            put(Input.KEY_Z, "Z");
            put(Input.KEY_0, "0");
            put(Input.KEY_1, "1");
            put(Input.KEY_2, "2");
            put(Input.KEY_3, "3");
            put(Input.KEY_4, "4");
            put(Input.KEY_5, "5");
            put(Input.KEY_6, "6");
            put(Input.KEY_7, "7");
            put(Input.KEY_8, "8");
            put(Input.KEY_9, "9");
            put(Input.KEY_TAB, "TAB");
            put(Input.KEY_ENTER, "ENTER");
            put(Input.KEY_ESCAPE, "ESCAPE");
            put(Input.KEY_INSERT, "INSERT");
            put(Input.KEY_DELETE, "DELETE");
            put(Input.KEY_HOME, "HOME");
            put(Input.KEY_END, "END");
            put(Input.KEY_F1, "F1");
            put(Input.KEY_F2, "F2");
            put(Input.KEY_F3, "F3");
            put(Input.KEY_F4, "F4");
            put(Input.KEY_F5, "F5");
            put(Input.KEY_F6, "F6");
            put(Input.KEY_F7, "F7");
            put(Input.KEY_F8, "F8");
            put(Input.KEY_F9, "F9");
            put(Input.KEY_F10, "F10");
            put(Input.KEY_F11, "F11");
            put(Input.KEY_F12, "F12");
            put(Input.KEY_NUMPAD0, "NUMPAD0");
            put(Input.KEY_NUMPAD1, "NUMPAD1");
            put(Input.KEY_NUMPAD2, "NUMPAD2");
            put(Input.KEY_NUMPAD3, "NUMPAD3");
            put(Input.KEY_NUMPAD4, "NUMPAD4");
            put(Input.KEY_NUMPAD5, "NUMPAD5");
            put(Input.KEY_NUMPAD6, "NUMPAD6");
            put(Input.KEY_NUMPAD7, "NUMPAD7");
            put(Input.KEY_NUMPAD8, "NUMPAD8");
            put(Input.KEY_NUMPAD9, "NUMPAD9");
            put(Input.KEY_ADD, "ADD");
            put(Input.KEY_SUBTRACT, "SUBTRACT");
            put(Input.KEY_MULTIPLY, "MULTIPLY");
            put(Input.KEY_DIVIDE, "DIVIDE");
            put(Input.KEY_EQUALS, "EQUALS");
            put(Input.KEY_COMMA, "COMMA");
            put(Input.KEY_PERIOD, "PERIOD");
            put(Input.KEY_SEMICOLON, "SEMICOLON");
            put(Input.KEY_SLASH, "SLASH");
            put(Input.KEY_MINUS, "MINUS");
            put(Input.KEY_SPACE, "SPACE");
        }
    };

	// Taille de l'ecran 

	public static int width;
	public static int height;

	
	//Characters
	public static Integer MainPlayer=null;
	
	public static String Player1Classe;
	public static String Player2Classe;
	
	public static String Player1Name="";
		
	
	//Mobs --> Definisez tous les mobes ici 
	
	public static Mobs[] mobs = new Mobs[4];

	
	//Collision maps
	
	public static Collisions CollisionMapForet1;
	public static Collisions CollisionMapForet2;
	public static Collisions CollisionMapForet3;
	
	public static Collisions CollisionMapChateau1;
	//Pour verifier si une touche est actuelleemnt pressé
	public static InputHandler InputHandler;
	
	// Fonctions
	
	public static void InitializeGame() throws SlickException { //Fonction d'initialisation de project complet
		try {	
			InputHandler = new InputHandler(100);
			
			// Initialisation de P1
			
			switch (Player1Classe) {
			    case "Swordman":
			        P1 = new Swordsman(Player1Name, 1);
			        break;
			    case "Berserker":
			        P1 = new Berserker(Player1Name, 1, null);
			        break;
			    case "Healer":
			        P1 = new Healer(Player1Name, 1, null);
			        break;
			    case "Mage":
			        P1 = new Mage(Player1Name, 1, null);
			        break;

			}

			// Initialisation de P2
			
			switch (Player2Classe) {
			    case "Swordman":
			        P2 = new Swordsman("Swordsman", 1);
			        break;
			    case "Berserker":
			        P2 = new Berserker("Berserker", 1, null);
			        break;
			    case "Healer":
			        P2 = new Healer("Healer", 1, null);
			        break;
			    case "Mage":
			        P2 = new Mage("Mage", 1, null);
			        break;
			}

	    	P1.setBattlehitbox(new Rectangle(PlayerBattleDistance, height/3, 32, 48));
	    	P2.setBattlehitbox(new Rectangle(PlayerBattleDistance, height/3, 32, 48));
		    
	    	P1.Animation();
	    	
	    	//Initialisation des collisions des maps
	    	CollisionMapForet1=new Collisions();
	    	CollisionMapForet2=new Collisions();
	    	CollisionMapForet3=new Collisions();

	    	CollisionMapChateau1=new Collisions();

	    	// Creer les collisions ici
	    	CollisionsMapForet1();
	    	CollisionsMapForet2();

	    	
		} catch(Exception e){
			e.getMessage(); //Affiche le message d'erreur en cas ou l'initialisation du project mne marche pas correctement ducoup c'est une ligne importante en cas de debug
			System.out.println("Erreur dans le global sur la fonction initialize");
		}
	}

	public static void CollisionsMapForet1() {
    	//Collisions bas a gauche + gauche
    	CollisionMapForet1.addCollidable(new Rectangle(0, 0, 200, height)); 
    	CollisionMapForet1.addCollidable(new Rectangle(0, height-380, 1128, 380)); 
    	CollisionMapForet1.addCollidable(new Rectangle(200, height-440, 200, 440)); 
    	CollisionMapForet1.addCollidable(new Rectangle(1128, height-320, 66, 320)); 
    	CollisionMapForet1.addCollidable(new Rectangle(1194, height-126, 200, 126)); 
    	//Collision Foret du haut
    	CollisionMapForet1.addCollidable(new Rectangle(200, 0, 66, 320)); 
    	CollisionMapForet1.addCollidable(new Rectangle(266, 0, 66, 66)); 
    	CollisionMapForet1.addCollidable(new Rectangle(728, 0, 530, 130)); 
    	CollisionMapForet1.addCollidable(new Rectangle(1258, 0, 66, 66)); 
    	CollisionMapForet1.addCollidable(new Rectangle(794, 130, 400, 250)); 
    	CollisionMapForet1.addCollidable(new Rectangle(992, 380, 202, 66)); 
    	//Collision Taverne
    	CollisionMapForet1.addCollidable(new Rectangle(530, 190, 198, 190)); 
    	CollisionMapForet1.addCollidable(new Rectangle(332, 0, 396, 190)); 
    	//Collision Branche
    	CollisionMapForet1.addCollidable(new Rectangle(728, 450, 66, 60)); 
    	//Collision Haut a droite
    	CollisionMapForet1.addCollidable(new Rectangle(width-530,0,530,510)); 
    	CollisionMapForet1.addCollidable(new Rectangle(width-660,190,130,256)); 
    	CollisionMapForet1.addCollidable(new Rectangle(width-594,130,64,60)); 
    	CollisionMapForet1.addCollidable(new Rectangle(width-400,510,400,60)); 
    	CollisionMapForet1.addCollidable(new Rectangle(width-330,570,330,130)); 
    	CollisionMapForet1.addCollidable(new Rectangle(width-200,700,200,66)); 
    	//Collision panneau
    	CollisionMapForet1.addCollidable(new Rectangle(width-530,510,66,60)); 
	}
	
	public static void CollisionsMapForet2() {

	}
	
	public static void switchModeControles() { //Fonction  propre permettant de savoir si on est en dialoge ou en deplacmeent libre
		canMoovPlayer=!canMoovPlayer;
		canMoovDialogbox=!canMoovDialogbox;
	}
	
		//A voir avec les animations 
	public static void updatePlayerMovement(Input input, Collisions c) { //Fonction qui gere le deplacement du joueur P1
	    	if (P1 != null && canMoovPlayer) {
		        boolean isleft = input.isKeyDown(left);
		        boolean isright = input.isKeyDown(right);
		        boolean isup = input.isKeyDown(up);
		        boolean isdown = input.isKeyDown(down);

		        if (isleft) {
		        	InputHandler.keyPressed();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),-1,0)) {
		            	Global.P1.LeftSprite();
		                P1.moveLeft(1);
		            }
		        } else if (isright) {
		        	InputHandler.keyPressed();
		        	Global.P1.RightSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),1,0)) {
		                P1.moveRight(1);

		            }
		        } else if (isup) {
		        	InputHandler.keyPressed();
		        	Global.P1.UpSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,-1)) {
		                P1.moveUp(1);

		            }
		        } else if (isdown) {
		        	InputHandler.keyPressed();
		        	Global.P1.DownSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,1)) {
		                P1.moveDown(1);
		            }
		         
		        }
		        
		    }
	    	
	    }

	//Variables necessaire au lore ( genre pour definir les evenements et leurs ordonances) 
	//D'ailleur ces variables permetteront de sauvgarder la partie
	
	
	//Variables a sauvgarder ( rien ne doit etre instancié, il ne doit y avoir que de simples variables )
	
	public static int actualGameState;
	
	//Fonctions de sauvgarde
	public static void LoadGame(File file) {
		
	}
	
	public static void SaveGame(File file) {
		
	}
}

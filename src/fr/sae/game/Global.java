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

import java.util.HashMap;
import java.util.Map;


public class Global {
	
	public static Player P1 = null;
	public static Player P2 = null;
		
	public static float speed =0.3f; //Vitesse du Player
	
	//Coordonee de spawn du Player 1 ( le tout premier spawn
	public static int SpawnX =375;
	public static int SpawnY =280;

	//Local variable permettant de savoir qui a la main
	
	public static Integer id=null;
	
	//Variables definissant si on doit utiliser les touches de mouvements pour se deplacer dans les dialogbox ou pour deplacer le personnage
	
	public static boolean canMoovPlayer =true;
	public static boolean canMoovDialogbox =false;

	//Variable definissant la distance compare au bord gauche de l'ecran qu'auront les joueurs et les mobes en combat
	public static int PlayerBattleDistance =250;
	public static int MobsBattleDistance =0; //A modif

	//Variables global d'input
	
	public static int up =Input.KEY_UP;
	public static int down =Input.KEY_DOWN;
	public static int left =Input.KEY_LEFT;
	public static int right =Input.KEY_RIGHT;
	
	public static int pause =Input.KEY_ESCAPE;
	public static int interract =Input.KEY_SPACE;
	
	//public static  Map<String, Integer> dictionnaireInputs = new HashMap<String, Integer>() {{put("UP", Input.KEY_UP);put("DOWN", Input.KEY_DOWN);put("LEFT", Input.KEY_LEFT);put("RIGHT", Input.KEY_RIGHT);put("SPACE", Input.KEY_SPACE);}};

    public static Map<String, Integer> dictionnaireInputs = new HashMap<String, Integer>() {{
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

        
        put("EXCLAMATION_MARK", (int) '!');
        put("DOUBLE_QUOTE", (int) '"');
        put("HASH", (int) '#');
        put("DOLLAR", (int) '$');
        put("PERCENT", (int) '%');
        put("AMPERSAND", (int) '&');
        put("SINGLE_QUOTE", (int) '\'');
        put("LEFT_PARENTHESIS", (int) '(');
        put("RIGHT_PARENTHESIS", (int) ')');
        put("ASTERISK", (int) '*');
        put("PLUS", (int) '+');
        put("COLON", (int) ':');
        put("LESS_THAN", (int) '<');
        put("EQUAL", (int) '=');
        put("GREATER_THAN", (int) '>');
        put("QUESTION_MARK", (int) '?');
        put("AT", (int) '@');
        put("LEFT_BRACKET", (int) '[');
        put("BACKSLASH", (int) '\\');
        put("RIGHT_BRACKET", (int) ']');
        put("CARET", (int) '^');
        put("UNDERSCORE", (int) '_');
        put("LEFT_BRACE", (int) '{');
        put("VERTICAL_BAR", (int) '|');
        put("RIGHT_BRACE", (int) '}');
        put("TILDE", (int) '~');
    }};
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

	// Fonctions
	
	public static void InitializeGame() throws SlickException { //Fonction d'initialisation de project complet
		try {	
			
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
	    	P2.setBattlehitbox(new Rectangle(PlayerBattleDistance, (height/3)*2, 32, 48));
		    
	    	P1.Animation();
	    	
	    	//Initialisation des collisions des maps
	    	CollisionMapForet1=new Collisions();
	    	CollisionMapForet2=new Collisions();
	    	CollisionMapForet3=new Collisions();

	    	CollisionMapChateau1=new Collisions();

	    	// Creer les collisions ici
	    	CollisionsMapForet1();
	    	
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
    	CollisionMapForet1.addCollidable(new Rectangle(width-530,510,66,50)); 

	}
	public static void switchModeControles() { //Fonction  propre permettant de savoir si on est en dialoge ou en deplacmeent libre
		canMoovPlayer=!canMoovPlayer;
		canMoovDialogbox=!canMoovDialogbox;
	}
	
		//A voir avec les animations 
	public static void updatePlayerMovement(Input input, Collisions c) { //Fonction qui gere le deplacement du joueur P1
	    	if (P1 != null && canMoovPlayer) {
		        boolean left = input.isKeyDown(Input.KEY_LEFT);
		        boolean right = input.isKeyDown(Input.KEY_RIGHT);
		        boolean up = input.isKeyDown(Input.KEY_UP);
		        boolean down = input.isKeyDown(Input.KEY_DOWN);


		        if (left) {
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),-1,0)) {
		                P1.moveLeft(1);
		                
		            }
		        } else if (right) {
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),1,0)) {
		                P1.moveRight(1);
		            }
		        } else if (up) {
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,-1)) {
		                P1.moveUp(1);
		            }
		        } else if (down) {
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,1)) {
		                P1.moveDown(1);
		            }
		        }
		    }
	    }

}

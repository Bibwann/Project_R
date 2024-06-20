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
	
//---------------------------------------------------------------------------------------------------------------------
	//Temp pour la demo
	public static boolean tmpVarLaunchBattleScene=true;
//---------------------------------------------------------------------------------------------------------------------

	public static Player P1 = null;
	public static Player P2 = null;

	public static float speed =0.4f; //Vitesse du Player
	public static int actualId = 10;
	
	//Coordonee de spawn du Player 1 ( le tout premier spawn)
	public static int SpawnX =375;
	public static int SpawnY =280;
	
	//Variables definissant si on doit utiliser les touches de mouvements pour se deplacer dans les dialogbox ou pour deplacer le personnage
	
	public static boolean canMoovPlayer =true;
	public static boolean canMoovDialogbox =false;

	//Variable definissant la distance compare au bord gauche de l'ecran qu'auront les joueurs et les mobes en combat
	public static int PlayerBattleDistance =250;
	public static int MobsBattleDistance =1450;


	//Variables pour les invetaires	
	public static Map<String, Integer> dictionnaireInventory = new HashMap<String, Integer>() {{
		put("SWORD", 0);
		put("WAND", 0);
		put("BOOK", 0);
		put("TOMAHAWK", 0);
		put("POTION", 0);
	}};

	//Variables global d'input
	
	public static int up =Input.KEY_UP;
	public static int down =Input.KEY_DOWN;
	public static int left =Input.KEY_LEFT;
	public static int right =Input.KEY_RIGHT;
	
	public static int pause =Input.KEY_ESCAPE;
	public static int interract =Input.KEY_SPACE;
	
	//Hashmap permettant le changement de touches proprement


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

	public static String Player1Classe;
	public static String Player2Classe;
	
	public static String Player1Name="";
		
	
	//Mobs --> Definisez tous les mobes ici 
	
	public static Mobs[] mobs = new Mobs[4];

	//Special Thanks
	public static String egg1="";
	//Collision maps
	
	public static Collisions CollisionMapForet1;
	public static Collisions CollisionMapForet2;
	public static Collisions CollisionMapForet3;
	public static Collisions CollisionMapForet5;
	public static Collisions CollisionMapForet6;
	public static Collisions CollisionMapForet7;
	public static Collisions CollisionMapForet8;
	public static Collisions CollisionMapForet9;
	public static Collisions CollisionMapForet10;
	public static Collisions CollisionMapForet11;
	public static Collisions CollisionMapForet12;
	public static Collisions CollisionMapForet13;
	public static Collisions CollisionMapUnderground1;
	public static Collisions CollisionMapUnderground2;
	public static Collisions CollisionMapUnderground3;
	public static Collisions CollisionMapChateau1;
	
	
	public static Collisions CollisionMapBattlescene;
	//Pour verifier si une touche est actuelleemnt pressé
	public static InputHandler InputHandler;
	
	// Fonctions
	
	public static void InitializeGame() throws SlickException { //Fonction d'initialisation de project complet
		try {	
			InputHandler = new InputHandler(200);
			
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
	    	CollisionMapForet5=new Collisions();
	    	CollisionMapForet6=new Collisions();
	    	CollisionMapForet7=new Collisions();
	    	CollisionMapForet8=new Collisions();
	    	CollisionMapForet9=new Collisions();
	    	CollisionMapForet10=new Collisions();
	    	CollisionMapForet11=new Collisions();
	    	CollisionMapForet12=new Collisions();
	    	CollisionMapForet13=new Collisions();
	    	CollisionMapUnderground1=new Collisions();
	    	CollisionMapUnderground2=new Collisions();
	    	CollisionMapUnderground3=new Collisions();
	    	CollisionMapChateau1=new Collisions();
	    	
	    	CollisionMapBattlescene=new Collisions();
	    	
	    	// Creer les collisions ici
	    	CollisionsMapForet1();
	    	CollisionsMapForet2();
	    	CollisionsMapForet5();
	    	CollisionsMapForet6();
	    	CollisionsMapForet7();
	    	CollisionsMapForet8();
	    	CollisionsMapForet9();
	    	CollisionsMapForet10();
	    	CollisionsMapForet11();
	    	CollisionsMapForet12();
	    	CollisionsMapForet13();
	    	CollisionMapUnderground1();
	    	CollisionMapUnderground2();
	    	CollisionMapUnderground3();

	    	
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
    	CollisionMapForet1.addCollidable(new Rectangle(728, 0, 120, 130)); 
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
	
	
	public static void CollisionsMapForet6() {
		//HAUT GAUCHE
    	CollisionMapForet6.addCollidable(new Rectangle(0, 0, 1920, 130)); 
    	CollisionMapForet6.addCollidable(new Rectangle(0, 130, 330, 320)); 
    	CollisionMapForet6.addCollidable(new Rectangle(0, 450, 200, height-450));
    	//GAUCHE
    	CollisionMapForet6.addCollidable(new Rectangle(200, 570, 70, height-570)); 
    	CollisionMapForet6.addCollidable(new Rectangle(270, 630, 60, height-630));
    	CollisionMapForet6.addCollidable(new Rectangle(330, 570, 70, height-570));
    	//TONNEAU
    	CollisionMapForet6.addCollidable(new Rectangle(400, 830, 60, height-830));
    	//BAS GAUCHE
    	CollisionMapForet6.addCollidable(new Rectangle(460, 750, 270, height-750));
    	CollisionMapForet6.addCollidable(new Rectangle(730, 830, 60, height-830));
    	CollisionMapForet6.addCollidable(new Rectangle(790, 960, 70, height-960));
    	//DROITE
    	CollisionMapForet6.addCollidable(new Rectangle(1270, 130, width-1270, 60));
    	CollisionMapForet6.addCollidable(new Rectangle(1450, 190, width-1450, 60));
    	CollisionMapForet6.addCollidable(new Rectangle(1590, 250, width-1590, 70));
    	CollisionMapForet6.addCollidable(new Rectangle(1660, 320, 60, 60));
    	CollisionMapForet6.addCollidable(new Rectangle(1720, 380, width-1720, height-370));
    	CollisionMapForet6.addCollidable(new Rectangle(1660, 510, 60, height-510));
    	CollisionMapForet6.addCollidable(new Rectangle(1590, 630, 60, height-630));
    	CollisionMapForet6.addCollidable(new Rectangle(1530, 760, 60, 60));
    	CollisionMapForet6.addCollidable(new Rectangle(1390, 820, 140, height-820));
    	CollisionMapForet6.addCollidable(new Rectangle(1330, 950, 60, height-950));
    	//POT
    	CollisionMapForet6.addCollidable(new Rectangle(1595, 320, 60, 65));
    	CollisionMapForet6.addCollidable(new Rectangle(1330, 190, 60, 67));
    	//PUIT
    	CollisionMapForet6.addCollidable(new Rectangle(1527, 380, 60, 68));
    	//TOMBES
    	CollisionMapForet6.addCollidable(new Rectangle(270, 450, 60, 60)); 
    	CollisionMapForet6.addCollidable(new Rectangle(400, 130, 60, 130));
    	CollisionMapForet6.addCollidable(new Rectangle(400, 310, 60, 260));
    	CollisionMapForet6.addCollidable(new Rectangle(530, 130, 60, 440));
    	CollisionMapForet6.addCollidable(new Rectangle(670, 130, 60, 250));
    	CollisionMapForet6.addCollidable(new Rectangle(800, 130, 60, 440));
    	CollisionMapForet6.addCollidable(new Rectangle(930, 130, 120, 70));
    	CollisionMapForet6.addCollidable(new Rectangle(670, 440, 60, 130));
    	CollisionMapForet6.addCollidable(new Rectangle(930, 450, 120, 60));
    	CollisionMapForet6.addCollidable(new Rectangle(930, 380, 60, 70));
    	CollisionMapForet6.addCollidable(new Rectangle(930, 250, 120, 130));
    	//BARRIERES
    	CollisionMapForet6.addCollidable(new Rectangle(400, 630, 650, 70));
    	CollisionMapForet6.addCollidable(new Rectangle(1010, 570, 40, 60));
	}
	
	public static void CollisionsMapForet2() {

	}
	
	public static void CollisionsMapForet5() {
		CollisionMapForet5.addCollidable(new Rectangle(0, 0, 1920, 60));
		//(longitude, latitude, longueur, hauteur)
        CollisionMapForet5.addCollidable(new Rectangle(0, 0, 1920, 60));
        //Part de en haut à gauche et fait toute la ligne du haut 
        CollisionMapForet5.addCollidable(new Rectangle(0, 60, 1000, 60));
        //Part de en haut et toute le deuxième ligne et jusu'à la moitié de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(0, 120, 934, 60));
        //Part de en haut à gauche et va jusqu'à la moitié de l'écran avec un arbre en moins
        CollisionMapForet5.addCollidable(new Rectangle(0, 180, 934, 60));
        //Pars de la gauche et va juqu'à la moitié de
        CollisionMapForet5.addCollidable(new Rectangle(0, 240, 874, 60));
        CollisionMapForet5.addCollidable(new Rectangle(0, 300, 120, 60));
        CollisionMapForet5.addCollidable(new Rectangle(0, 360, 120, 60));
        CollisionMapForet5.addCollidable(new Rectangle(0, 420, 60, 60));
        CollisionMapForet5.addCollidable(new Rectangle(0, 480, 60, 70));
        CollisionMapForet5.addCollidable(new Rectangle(330, 420, 400, 80));
        CollisionMapForet5.addCollidable(new Rectangle(460, 500, 206, 60));
        CollisionMapForet5.addCollidable(new Rectangle(200, 300, 660, 120));
        CollisionMapForet5.addCollidable(new Rectangle(526, 560, 75, 60));
        CollisionMapForet5.addCollidable(new Rectangle(820, 450, 20, 60));
        //barrère gauche du haut au milieu de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(820, 510, 150, 60));
        //barrières du bas au milieu de l'écran en haut
        CollisionMapForet5.addCollidable(new Circle(830, 605, 30, 40));
        //rocher juste en dessous de la barrère coté gauche
        CollisionMapForet5.addCollidable(new Circle(1225, 605, 30, 40));
        //rocher au dessus de la barrière coté droit au milieu des autres rocher
        CollisionMapForet5.addCollidable(new Circle(1290, 540, 30, 40));
        //rocher à droite à coté de celui qui est au milieu un peu au dessus
        CollisionMapForet5.addCollidable(new Rectangle(1085, 760, 150, 65));
        //barrère du bas droite au milieu de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(1085, 640, 20, 120));
        //barrière droite et haute au milieu de l'écran
        CollisionMapForet5.addCollidable(new Circle(1125, 380, 60, 60));
        //arbre au milieu en haut à droite de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(1720, 448, 200, 62));
        //rectangle au milieu de l'écran qui prends 3 arbres à droite de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(1590, 510, 450, 570));
        //rectangle en bas à droite qui prend jusqu'au milieu de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(1460, 570, 130, 510));
        //rectangle en bas à droite collé au grand rectangle
        CollisionMapForet5.addCollidable(new Rectangle(1390, 640, 70, 120));
        CollisionMapForet5.addCollidable(new Rectangle(1325, 760, 135, 500));
        CollisionMapForet5.addCollidable(new Rectangle(1260, 830, 66, 66));
        CollisionMapForet5.addCollidable(new Rectangle(1190, 895, 135, 190));
        CollisionMapForet5.addCollidable(new Rectangle(1125, 955, 65, 125));
        //BRANCHE
        CollisionMapForet5.addCollidable(new Rectangle(120, 310, 80, 70));
	}
	
	public static void CollisionsMapForet7() {
		
	}
	
	public static void CollisionsMapForet8() {
	
	}
	
	public static void CollisionsMapForet9() {
		
	}
	
	public static void CollisionsMapForet10() {
		
	}
	
	public static void CollisionsMapForet11() {
		
	}
	
	public static void CollisionsMapForet12() {
		
	}
	
public static void CollisionMapUnderground1() {
		
	}
	
	public static void CollisionMapUnderground2() {
		
	}
	
	public static void CollisionMapUnderground3() {
		CollisionMapUnderground3.addCollidable(new Rectangle(0, 0, 395, 160)); //MUR HAUT
		CollisionMapUnderground3.addCollidable(new Rectangle(860, 0, 330, 160)); //MUR HAUT
		CollisionMapUnderground3.addCollidable(new Rectangle(1330, 0, width-1330, 160)); //MUR HAUT
		CollisionMapUnderground3.addCollidable(new Rectangle(925, 160, 70, height-220)); // MUR MILIEU
		CollisionMapUnderground3.addCollidable(new Rectangle(0, 1020, 395, 60)); // MUR BAS
		CollisionMapUnderground3.addCollidable(new Rectangle(465, 1020, width-465, 60)); // MUR BAS
		CollisionMapUnderground3.addCollidable(new Rectangle(395, 0, 465 , 130)); // GRILLE
		CollisionMapUnderground3.addCollidable(new Rectangle(530, 440, 60 , 60)); // TABLE
		CollisionMapUnderground3.addCollidable(new Rectangle(590, 450, 60 , 50)); // CHAISE
		CollisionMapUnderground3.addCollidable(new Rectangle(540, 510, 50 , 50)); // CHAISE
		CollisionMapUnderground3.addCollidable(new Rectangle(70, 840, 130 , 100)); // CAILLOU
		CollisionMapUnderground3.addCollidable(new Rectangle(800, 130, 60 , 60)); // SCEAU
	}
	
	public static void CollisionsMapForet13() {
		
	}
	
	
	public static void switchModeControles() { //Fonction  propre permettant de savoir si on est en dialoge ou en deplacmeent libre
		canMoovPlayer=!canMoovPlayer;
		canMoovDialogbox=!canMoovDialogbox;
	}
	
		//A voir avec les animations 
	public static void updatePlayerMovement(Input input, Collisions c, int delta) { //Fonction qui gere le deplacement du joueur P1
	    	if (P1 != null && canMoovPlayer) {
		        boolean isleft = input.isKeyDown(left);
		        boolean isright = input.isKeyDown(right);
		        boolean isup = input.isKeyDown(up);
		        boolean isdown = input.isKeyDown(down);

		        if (isleft) {
		        	InputHandler.keyPressed();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),-1,0)) {
		            	Global.P1.LeftSprite();
		                P1.moveLeft(1,delta);
		            }
		        } else if (isright) {
		        	InputHandler.keyPressed();
		        	Global.P1.RightSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),1,0)) {
		                P1.moveRight(1,delta);

		            }
		        } else if (isup) {
		        	InputHandler.keyPressed();
		        	Global.P1.UpSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,-1)) {
		                P1.moveUp(1,delta);

		            }
		        } else if (isdown) {
		        	InputHandler.keyPressed();
		        	Global.P1.DownSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,1)) {
		                P1.moveDown(1,delta);
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

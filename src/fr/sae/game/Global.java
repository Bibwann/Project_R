package fr.sae.game;


import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Berserker;
import fr.sae.game.caractere.Entity;
import fr.sae.game.caractere.Healer;
import fr.sae.game.caractere.Mage;
import fr.sae.game.caractere.Mobs;
import fr.sae.game.caractere.Player;
import fr.sae.game.caractere.Swordsman;
import fr.sae.mobes.Chaton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Global {
	
	//Rien a foutre
	public static boolean AfficherToutesLesCollisions=true;
	 
	//Objects de quetes
	 
	public static boolean CoeurVaillant=false;
	public static boolean LeveledUpForet5=false;

	
	//Combats uniques
	public static boolean Foret6Battle=true;
	public static boolean Foret5Battle=true;
	public static boolean Foret7Battle=true;
	public static boolean Underground3Battle=true;


	//Instances de player
	public static Player P1 = null;
	public static Player P2 = null;

	public static float speed =0.2f; //Vitesse du Player
	public static int actualId = 11;
	
	//Coordonee de spawn du Player 1 ( le tout premier spawn)
	public static int SpawnX =375;
	public static int SpawnY =280;
	
	//Variables definissant si on doit utiliser les touches de mouvements pour se deplacer dans les dialogbox ou pour deplacer le personnage
	
	public static boolean canMoovPlayer =true;
	public static boolean canMoovDialogbox =false;

	//Variable definissant la distance compare au bord gauche de l'ecran qu'auront les joueurs et les mobes en combat
	public static int PlayerBattleDistance =250;
	public static int MobsBattleDistance =1450;


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
	
	public static Mobs[] mobs = new Mobs[3];

	//Special Thanks
	public static String egg1="";
	
	//Collisions temporaires
	
	public static Collisions TmpCollisionMapForet5;

	//Collision maps
	
	public static Collisions CollisionMapForet1;
	public static Collisions CollisionMapForet2;
	public static Collisions CollisionMapForet3;
	public static Collisions CollisionMapForet4;
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
	
	public static Collisions CollisionsMapVide;

	
	
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
	    	
	    	Global.Collisions();

	    	
		} catch(Exception e){
			e.getMessage(); //Affiche le message d'erreur en cas ou l'initialisation du project mne marche pas correctement ducoup c'est une ligne importante en cas de debug
			System.out.println("Erreur dans le global sur la fonction initialize");
		}
	}
	

	private static void Collisions() {
		//Initialisation des collisions des maps
    	CollisionMapForet1=new Collisions();
    	CollisionMapForet2=new Collisions();
    	CollisionMapForet3=new Collisions();
    	CollisionMapForet4=new Collisions();
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
    	
    	CollisionsMapVide = new Collisions();

    	
    	// Creer les collisions ici
    	CollisionsMapForet1();
    	CollisionsMapForet2();
    	CollisionsMapForet3();
    	CollisionsMapForet4();
    	CollisionsMapForet5();
    	CollisionsMapForet6();
    	CollisionsMapForet7();
    	CollisionsMapForet8();
    	CollisionsMapForet9();
    	CollisionsMapForet10();
    	CollisionsMapForet11();
    	CollisionsMapForet12();
    	
    	CollisionMapUnderground1();
    	CollisionMapUnderground2();
    	CollisionMapUnderground3();
    	
    	CollisionsMapVide();


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
    	//MAGE
    	CollisionMapForet1.addCollidable(new Rectangle(596,505,66,30)); 

	}
	
	public static void CollisionsMapForet2() {
		//Haut
		CollisionMapForet2.addCollidable(new Rectangle(0, 0, 860, 50));
		CollisionMapForet2.addCollidable(new Rectangle(1060, 0, 1920-1060, 50));
		
		//Foret a gauche
		CollisionMapForet2.addCollidable(new Rectangle(0, 450, 130, 1080-450));
		CollisionMapForet2.addCollidable(new Rectangle(130, 570, 65, 1080-570));
		CollisionMapForet2.addCollidable(new Rectangle(195, 700, 65, 1080-700));
		CollisionMapForet2.addCollidable(new Rectangle(260, 760, 65, 175));
		
		//Arbres du bas
		CollisionMapForet2.addCollidable(new Circle(240, 1040, 90));

		CollisionMapForet2.addCollidable(new Circle(320, 1080, 60));
		CollisionMapForet2.addCollidable(new Circle(600, 1080, 60));
		CollisionMapForet2.addCollidable(new Circle(790, 1080, 60));
		CollisionMapForet2.addCollidable(new Circle(1130, 1080, 60));
		CollisionMapForet2.addCollidable(new Circle(1860, 1080, 60));
		CollisionMapForet2.addCollidable(new Circle(1860, 890, 60));


		CollisionMapForet2.addCollidable(new Circle(460, 1020, 60));
		CollisionMapForet2.addCollidable(new Circle(930, 1020, 60));
		CollisionMapForet2.addCollidable(new Circle(1330, 1020, 60));

		//Arbres solitaire au dessus a droite du groupe
		CollisionMapForet2.addCollidable(new Circle(1260, 510, 60));

		//Groupe d'arbres
		CollisionMapForet2.addCollidable(new Circle(990, 760, 60));
		CollisionMapForet2.addCollidable(new Circle(990, 630, 60));
		CollisionMapForet2.addCollidable(new Circle(990, 510, 60));
		
		CollisionMapForet2.addCollidable(new Circle(860, 510, 60));

		CollisionMapForet2.addCollidable(new Circle(730, 510, 60));
		CollisionMapForet2.addCollidable(new Circle(730, 630, 60));
		CollisionMapForet2.addCollidable(new Circle(730, 760, 60));
		

		//Panneau
		CollisionMapForet2.addCollidable(new Rectangle(1000, 140, 60, 40));

		//Branche
		CollisionMapForet2.addCollidable(new Rectangle(1395, 265, 60, 50));

		//Droite
		CollisionMapForet2.addCollidable(new Rectangle(1460, 50, 1920-1460, 60));
		CollisionMapForet2.addCollidable(new Rectangle(1790, 110, 1920-1790, 60));
		CollisionMapForet2.addCollidable(new Rectangle(1855, 170, 1920-1855, 1080-170));

		//Caisses
		CollisionMapForet2.addCollidable(new Rectangle(1790, 250, 1920-1790, 380));
		CollisionMapForet2.addCollidable(new Rectangle(1525, 255, 130, 380));

	}
	
	public static void CollisionsMapForet3() {
		
		//Arbres solitaires
		CollisionMapForet3.addCollidable(new Circle(730, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(860, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(990, 120, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1120, 320, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 320, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 320, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1455, 260, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1190, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 320, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1130, 0, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(790, 0, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(590, 130, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(590, 130, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(400, 0, 110, 50));
		CollisionMapForet3.addCollidable(new Circle(1260, 60, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(585, 0, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(260, 60, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(125, 0, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(60, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(60, 315, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(200, 315, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(400, 315, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(400, 190, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1790, 320, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1790, 130, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1850, 0, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1255, 890, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1060, 830, 60, 50));

		//Branche
		CollisionMapForet3.addCollidable(new Rectangle(1125, 640, 60, 50));

		//Ammat d'arbres a droite en bas
		CollisionMapForet3.addCollidable(new Circle(40, 615, 180, 50));
		CollisionMapForet3.addCollidable(new Circle(200, 640, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(190, 500, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(125, 440, 60, 50));

		//Arbres du bas
		CollisionMapForet3.addCollidable(new Circle(460, 1080, 110, 50));
		CollisionMapForet3.addCollidable(new Circle(660, 1080, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1190, 1080, 60, 50));
		CollisionMapForet3.addCollidable(new Rectangle(1210, 1080-125, 1920-1120, 125	));

		//Ammat de droite

		CollisionMapForet3.addCollidable(new Circle(1720, 700, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1850, 700, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1720, 570, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1720, 440, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1850, 440, 60, 50));
		
		//Ammat du centre
		
		CollisionMapForet3.addCollidable(new Circle(1450, 700, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1450, 570, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1450, 440, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 700, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 440, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1320, 570, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1250, 500, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1120, 500, 60, 50));
		
		CollisionMapForet3.addCollidable(new Circle(1055, 570, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(1055, 700, 60, 50));
		
		CollisionMapForet3.addCollidable(new Circle(930, 700, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(930, 440, 60, 50));

		CollisionMapForet3.addCollidable(new Circle(860, 630, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(790, 570, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(790, 450, 60, 50));
		
		CollisionMapForet3.addCollidable(new Circle(990, 380, 60, 50));

		//	Ammat du bas gauche
		
		CollisionMapForet3.addCollidable(new Circle(930, 1020, 110, 50));
		CollisionMapForet3.addCollidable(new Circle(800, 1080, 60, 50));

		//Ammat du bas a gauche
		
		CollisionMapForet3.addCollidable(new Circle(790, 830, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(730, 890, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(590, 890, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(320, 890, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(390, 830, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(530, 830, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(320, 890, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(260,950 , 60, 50));
		CollisionMapForet3.addCollidable(new Circle(130, 1020, 110, 50));
		CollisionMapForet3.addCollidable(new Circle(260, 820, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(260, 820, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(330, 760, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(390, 440, 60, 50));

		CollisionMapForet3.addCollidable(new Rectangle(340, 400+10, 310, 400-10));
		
		CollisionMapForet3.addCollidable(new Circle(590, 380, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(660, 690, 60, 50));
		CollisionMapForet3.addCollidable(new Circle(720, 760, 60, 50));

		CollisionMapForet3.addCollidable(new Circle(530, 450, 60, 50));

	}
	
	public static void CollisionsMapForet4() {

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
        
        //correction bug collision ( le truc moche tout seul)
        CollisionMapForet5.addCollidable(new Rectangle(820 , 430, 20, 20));

        //barrère gauche du haut au milieu de l'écran
        CollisionMapForet5.addCollidable(new Rectangle(820 , 510, 150, 60));
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
        //collisions Guts
        CollisionMapForet5.addCollidable(new Rectangle(960, 582, 40, 30));
        
        //Collisions temporaires
        
        CollisionMapForet5.addCollidable(new Rectangle(990, 580, 70, 70)); 

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
    	//BRANCHES
    	CollisionMapForet6.addCollidable(new Rectangle(995, 690, 60, 70));
    	
	}
	
	public static void CollisionsMapForet7() {
		
		// Ajout des collidables avec transformations
        CollisionMapForet7.addCollidable(new Circle(70, 180, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(130, 240, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(200, 310, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(330, 310, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(400, 240, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(460, 310, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(530, 380, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(470, 440, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(410, 510, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(400, 640, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(400, 640, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(360, 570, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(340, 700, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(230, 820, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(270, 760, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(270, 890, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(260, 360, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(270, 1000, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(320, 1060, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(600, 1060, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(460, 1060, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(720, 1060, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(790, 1000, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(860, 950, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(860, 820, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(860, 700, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(790, 630, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(720, 570, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(790, 500, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(790, 380, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(790, 250, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(860, 180, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(990, 180, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(990, 180, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(920, 230, 50, 50));

        CollisionMapForet7.addCollidable(new Circle(1060, 120, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(1120, 50, 50, 50));

        // Acces par le haut a droite
        CollisionMapForet7.addCollidable(
                new Rectangle(1385, 50, 60, 800)
                        .transform(Transform.createRotateTransform((float) Math.toRadians(45), 1415 , 75)));
        CollisionMapForet7.addCollidable(
                new Rectangle(1610, 200, 280, 470)
                        .transform(Transform.createRotateTransform((float) Math.toRadians(45), 1910 , 420)));
        CollisionMapForet7.addCollidable(
                new Rectangle(1360, -140, 220, 200)
                        .transform(Transform.createRotateTransform((float) Math.toRadians(45), 1400, 140)));

        CollisionMapForet7.addCollidable(new Circle(1520, 310, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(1390, 310, 50, 50));
        CollisionMapForet7.addCollidable(new Circle(1330, 380, 60, 50));

        CollisionMapForet7.addCollidable(new Circle(1060, 510, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1120, 570, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1200, 640, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1255, 570, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1320, 500, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1320, 630, 60, 50));

        // Acces pour en bas
        CollisionMapForet7.addCollidable(new Circle(1380, 690, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1450, 630, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1520, 570, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1520, 440, 60, 50));

        CollisionMapForet7.addCollidable(
                new Rectangle(1570, -800, 201, 450)
                        .transform(Transform.createRotateTransform((float) Math.toRadians(45), 200, 140)));

        CollisionMapForet7.addCollidable(new Circle(1785, 820, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1850, 760, 60, 50));

        CollisionMapForet7.addCollidable(new Circle(1055, 620, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1055, 760, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1120, 820, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1255, 820, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1320, 880, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1255, 820, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1390, 940, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1520, 950, 60, 50));

        CollisionMapForet7.addCollidable(new Circle(1580, 1020, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1720, 1020, 60, 50));
        CollisionMapForet7.addCollidable(new Circle(1845, 1020, 60, 50));

        
        //Branche
        CollisionMapForet7.addCollidable(new Rectangle(470, 520, 50, 50));
        
        //Coffre
        CollisionMapForet7.addCollidable(new Rectangle(405, 910, 38, 35));

	}
	
	public static void CollisionsMapForet8() {
	
	}
	
	public static void CollisionsMapForet9() {
		//Le haut
        CollisionMapForet9.addCollidable(new Rectangle(0, 0, 1920, 180));
		//Le bas
        CollisionMapForet9.addCollidable(new Rectangle(0, 1080-180, 1920, 180));
		//La droite
        CollisionMapForet9.addCollidable(new Rectangle(1920-180, 0, 180, 1080));
        
        //Tonneaux
        CollisionMapForet9.addCollidable(new Rectangle(670, 260, 450, 180));
        
        CollisionMapForet9.addCollidable(new Rectangle(670, 260+180, 50, 60));
        CollisionMapForet9.addCollidable(new Rectangle(670+450-50, 260+180, 50, 60));
        
        //Tipie
        CollisionMapForet9.addCollidable(new Rectangle(800, 260+180, 270, 120));

        //Bord gauche
        CollisionMapForet9.addCollidable(new Circle(-240, -60, 600, 50));
        CollisionMapForet9.addCollidable(new Circle(-260, 1080+100, 600, 50));

	}
	
	public static void CollisionsMapForet10() {
		//Haut
        CollisionMapForet10.addCollidable(new Rectangle(0, 0, 1920, 60));
        
        //Bas
        CollisionMapForet10.addCollidable(new Rectangle(0, 1080-130, 1920, 130));

        //Arbres du centre
        CollisionMapForet10.addCollidable(new Circle(460, 510, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(590, 630, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(720, 760, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(850, 820, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(980, 760, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1120, 630, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1260, 510, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1120, 380, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(980, 250, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(850, 190, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(720, 250, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(590, 380, 60, 50));

        //Branche
	    CollisionMapForet10.addCollidable(new Rectangle(930,515,60, 40)); 

	    //Epouventaille
	    CollisionMapForet10.addCollidable(new Rectangle(805,390,40, 40)); 
	    
	    //Foret gauche
		CollisionMapForet10.addCollidable(new Circle(65, 500, 110, 50));
		CollisionMapForet10.addCollidable(new Circle(65, 630, 110, 50));
		
		//Arbres droite
        CollisionMapForet10.addCollidable(new Circle(1790, 130, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1790, 260, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1790, 380, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1790, 820, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1790, 950, 60, 50));

        CollisionMapForet10.addCollidable(new Circle(1850, 450, 60, 50));
        CollisionMapForet10.addCollidable(new Circle(1850, 760, 60, 50));
        
        CollisionMapForet10.addCollidable(new Circle(1920, 690, 60, 50));




	}
	
	public static void CollisionsMapForet11() {
		
	}
	
	public static void CollisionsMapForet12() {
		//Chateau
	    CollisionMapForet12.addCollidable(new Rectangle(70, 0,1780, 380)); 
	    CollisionMapForet12.addCollidable(new Circle(130, 380,65, 30)); 
	    CollisionMapForet12.addCollidable(new Circle(660, 380,65, 30)); 
	    CollisionMapForet12.addCollidable(new Circle(1260, 380,65, 30)); 
	    CollisionMapForet12.addCollidable(new Circle(1790, 380,65, 30)); 
	    
	    //Tonneaux gauche 
	    CollisionMapForet12.addCollidable(new Rectangle(270,380,260, 130)); 
	    
	    //Tonneaux droite 
	    CollisionMapForet12.addCollidable(new Rectangle(1390,380,260, 130)); 
	    
	    //Arbre en bas a gauche
	    CollisionMapForet12.addCollidable(new Rectangle(0,1080-250,180, 250)); 

	    //Arbre en bas a droite
	    CollisionMapForet12.addCollidable(new Rectangle(1920-190,1080-190,190, 190)); 

	    //Panneau
	    CollisionMapForet12.addCollidable(new Rectangle(1065,990-40,60, 50)); 

	    //Branche
	    CollisionMapForet12.addCollidable(new Rectangle(1920-80,0,80, 60)); 

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
		CollisionMapUnderground3.addCollidable(new Rectangle(665, 895, 130 , 60)); // TONNEAU
		CollisionMapUnderground3.addCollidable(new Circle(556, 663, 30 , 40)); // rocher
	}
	
	public static void CollisionsMapVide() {
		
	}
	
	
	
	public static void switchModeControles() { //Fonction  propre permettant de savoir si on est en dialoge ou en deplacmeent libre
		canMoovPlayer=!canMoovPlayer;
		canMoovDialogbox=!canMoovDialogbox;
	}
	
		//A voir avec les animations 
	public static void updatePlayerMovement(Input input, Collisions c, int delta,StateBasedGame sbg) throws SlickException { //Fonction qui gere le deplacement du joueur P1
	    	if (P1 != null && canMoovPlayer) {
		        boolean isleft = input.isKeyDown(left);
		        boolean isright = input.isKeyDown(right);
		        boolean isup = input.isKeyDown(up);
		        boolean isdown = input.isKeyDown(down);
		        int chance = 100;
		        if (isleft) {
		        	InputHandler.keyPressed();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),-3,0)) {
		            	Global.P1.LeftSprite();
		                P1.moveLeft(1,delta);
		                
		                if(P1.getRandomBattle()) {
		                	if (getRandomNumber(1, chance)== 1) {
		                		launchRandomBattle(sbg);
		                	}
		                }
		                
		            }
		            
		        } else if (isright) {
		        	InputHandler.keyPressed();
		        	Global.P1.RightSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),3,0)) {
		                P1.moveRight(1,delta);
		                
		                if(P1.getRandomBattle()) {
		                	if (getRandomNumber(1, chance)== 1) {
		                		launchRandomBattle(sbg);
		                	}
		                }

		            }
		        } else if (isup) {
		        	InputHandler.keyPressed();
		        	Global.P1.UpSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,-3)) {
		                P1.moveUp(1,delta);
		                
		                if(P1.getRandomBattle()) {
		                	if (getRandomNumber(1, chance)== 1) {
		                		launchRandomBattle(sbg);
		                	}
		                }

		            }
		        } else if (isdown) {
		        	InputHandler.keyPressed();
		        	Global.P1.DownSprite();
		            if (!c.willCollideWithMap(Global.P1.getHitbox(),0,3)) {
		                P1.moveDown(1,delta);
		                
		                if(P1.getRandomBattle()) {
		                	if (getRandomNumber(1, chance)== 1) {
		                		launchRandomBattle(sbg);
		                	}
		                }
		            }
		        }
		        
		    }
	    	
	    }
	
	  public static void launchRandomBattle(StateBasedGame sbg) throws SlickException {
	        // Désactiver le mouvement du joueur
		  
	        Global.canMoovPlayer = false;

	        // Générer un nombre aléatoire de mobs entre 1 et 4
	        int numberOfMobs = getRandomNumber(1,2);

	        // Boucle pour initialiser chaque mob avec un niveau et un nom aléatoire
	        for (int i = 0; i < numberOfMobs; i++) {
	            String name = "chaton" + (i + 1);
	            int level = getRandomNumber(1,2);
	            Global.mobs[i] = new Chaton(name, level);
	        }

	        // Entrer dans l'état de combat (ID 100)
	        sbg.enterState(100);
	    }



	//Variables necessaire au lore ( genre pour definir les evenements et leurs ordonances) 
	//D'ailleur ces variables permetteront de sauvgarder la partie
	
	
	//Sur quelle save est on
	public static File actualfile;
	
	
	//Variables a sauvgarder ( rien ne doit etre instancié, il ne doit y avoir que de simples variables )
	public static int actualGameState;
	public static int X;
	public static int Y;

	public static int getRandomNumber(int min, int max) {
	     if (min > max) {
	         throw new IllegalArgumentException("min doit être inférieur ou égal à max");
	     }
	        
	     Random random = new Random();
	     return random.nextInt((max - min) + 1) + min;
	 }

	//Fonctions de sauvgarde
	public static void LoadGame() {
	    File file = new File(Global.actualfile.toString());

	    Global.Collisions();

	    InputHandler = new InputHandler(200);

	    int classep1Lvl = 1;
	    int classep2Lvl = 1;

	    try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(file);

	        // Normalize the XML structure
	        doc.getDocumentElement().normalize();

	        // Root element "Game"
	        Element rootElement = doc.getDocumentElement();

	        // Load MapID
	        NodeList mapIdList = rootElement.getElementsByTagName("MapID");
	        if (mapIdList.getLength() > 0) {
	            Element mapIdElement = (Element) mapIdList.item(0);
	            int mapId = Integer.parseInt(mapIdElement.getTextContent());
	            Global.actualId = mapId;
	        }

	        // Load classep1
	        NodeList classep1List = rootElement.getElementsByTagName("classep1");
	        if (classep1List.getLength() > 0) {
	            Element classep1Element = (Element) classep1List.item(0);
	            String classep1 = classep1Element.getTextContent();
	            Global.Player1Classe = classep1;
	        }

	        // Load classep2
	        NodeList classep2List = rootElement.getElementsByTagName("classep2");
	        if (classep2List.getLength() > 0) {
	            Element classep2Element = (Element) classep2List.item(0);
	            String classep2 = classep2Element.getTextContent();
	            Global.Player2Classe = classep2;
	        }

	        // Load level for Global.P1
	        NodeList classep1LvlList = rootElement.getElementsByTagName("classep1LVL");
	        if (classep1LvlList.getLength() > 0) {
	            Element classep1LvlElement = (Element) classep1LvlList.item(0);
	            classep1Lvl = Integer.parseInt(classep1LvlElement.getTextContent());
	        }

	        // Load level for Global.P2
	        NodeList classep2LvlList = rootElement.getElementsByTagName("classep2LVL");
	        if (classep2LvlList.getLength() > 0) {
	            Element classep2LvlElement = (Element) classep2LvlList.item(0);
	            classep2Lvl = Integer.parseInt(classep2LvlElement.getTextContent());
	        }

	        // Load Player1Name
	        NodeList player1NameList = rootElement.getElementsByTagName("Player1Name");
	        if (player1NameList.getLength() > 0) {
	            Element player1NameElement = (Element) player1NameList.item(0);
	            String player1Name = player1NameElement.getTextContent();
	            Global.Player1Name = player1Name;
	        }

	        // Initialize P1 if not already initialized
	        if (Global.P1 == null) {
	            switch (Global.Player1Classe) {
	                case "Swordman":
	                    P1 = new Swordsman(Global.Player1Name, classep1Lvl);
	                    break;
	                case "Berserker":
	                    P1 = new Berserker(Global.Player1Name, classep1Lvl, null);
	                    break;
	                case "Healer":
	                    P1 = new Healer(Global.Player1Name, classep1Lvl, null);
	                    break;
	                case "Mage":
	                    P1 = new Mage(Global.Player1Name, classep1Lvl, null);
	                    break;
	            }
	        }

	        // Load X coordinate
	        NodeList xList = rootElement.getElementsByTagName("x");
	        if (xList.getLength() > 0) {
	            Element xElement = (Element) xList.item(0);
	            int x = Integer.parseInt(xElement.getTextContent());
	            if (Global.P1 != null) {
	                Global.P1.getHitbox().setX(x);
	            }
	        }

	        // Load Y coordinate
	        NodeList yList = rootElement.getElementsByTagName("y");
	        if (yList.getLength() > 0) {
	            Element yElement = (Element) yList.item(0);
	            int y = Integer.parseInt(yElement.getTextContent());
	            if (Global.P1 != null) {
	                Global.P1.getHitbox().setY(y);
	            }
	        }

	        // Initialize P2 if not already initialized
	        if (Global.P2 == null) {
	            switch (Global.Player2Classe) {
	                case "Swordman":
	                    P2 = new Swordsman("Swordsman", classep2Lvl);
	                    break;
	                case "Berserker":
	                    P2 = new Berserker("Berserker", classep2Lvl, null);
	                    break;
	                case "Healer":
	                    P2 = new Healer("Healer", classep2Lvl, null);
	                    break;
	                case "Mage":
	                    P2 = new Mage("Mage", classep2Lvl, null);
	                    break;
	            }
	        }

	        // Load coeurVaillant
	        NodeList coeurVaillantList = rootElement.getElementsByTagName("coeurVaillant");
	        if (coeurVaillantList.getLength() > 0) {
	            Element coeurVaillantElement = (Element) coeurVaillantList.item(0);
	            boolean CoeurVaillant = Boolean.parseBoolean(coeurVaillantElement.getTextContent());
	            Global.CoeurVaillant = CoeurVaillant;
	        }

	        // Load LeveledUpForet5
	        NodeList leveledUpForet5List = rootElement.getElementsByTagName("LeveledUpForet5");
	        if (leveledUpForet5List.getLength() > 0) {
	            Element leveledUpForet5Element = (Element) leveledUpForet5List.item(0);
	            boolean LeveledUpForet5 = Boolean.parseBoolean(leveledUpForet5Element.getTextContent());
	            Global.LeveledUpForet5 = LeveledUpForet5;
	        }

	     // Load Foret6Battle
	        NodeList foret7BattleList = rootElement.getElementsByTagName("Foret7Battle");
	        if (foret7BattleList.getLength() > 0) {
	            Element foret7BattleElement = (Element) foret7BattleList.item(0);
	            boolean Foret7Battle = Boolean.parseBoolean(foret7BattleElement.getTextContent());
	            Global.Foret7Battle = Foret7Battle;
	        }
	        // Load Foret6Battle
	        NodeList foret6BattleList = rootElement.getElementsByTagName("Foret6Battle");
	        if (foret6BattleList.getLength() > 0) {
	            Element foret6BattleElement = (Element) foret6BattleList.item(0);
	            boolean Foret6Battle = Boolean.parseBoolean(foret6BattleElement.getTextContent());
	            Global.Foret6Battle = Foret6Battle;
	        }

	        // Load Foret5Battle
	        NodeList foret5BattleList = rootElement.getElementsByTagName("Foret5Battle");
	        if (foret5BattleList.getLength() > 0) {
	            Element foret5BattleElement = (Element) foret5BattleList.item(0);
	            boolean Foret5Battle = Boolean.parseBoolean(foret5BattleElement.getTextContent());
	            Global.Foret5Battle = Foret5Battle;
	        }

	        // Example of setting battle hitbox and initiating animation
	        if (Global.P1 != null) {
	            P1.setBattlehitbox(new Rectangle(PlayerBattleDistance, height / 3, 32, 48));
	            P1.Animation();
	        }
	        if (Global.P2 != null) {
	            P2.setBattlehitbox(new Rectangle(PlayerBattleDistance, height / 3, 32, 48));
	        }

	        if (Global.CoeurVaillant) {
	            Global.CollisionMapForet5.deletLastCollidable();
	        }

	        System.out.println("Game loaded successfully!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static void SaveGame() {
	    File file = new File(Global.actualfile.toString());
	    try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.newDocument();

	        // Root element
	        Element rootElement = doc.createElement("Game");
	        doc.appendChild(rootElement);

	        // Map ID element
	        Element mapIdElement = doc.createElement("MapID");
	        mapIdElement.appendChild(doc.createTextNode(Integer.toString(Global.actualId)));
	        rootElement.appendChild(mapIdElement);

	        // Save classep1
	        Element classep1Element = doc.createElement("classep1");
	        classep1Element.appendChild(doc.createTextNode(Global.Player1Classe));
	        rootElement.appendChild(classep1Element);

	        // Save classep2
	        Element classep2Element = doc.createElement("classep2");
	        classep2Element.appendChild(doc.createTextNode(Global.Player2Classe));
	        rootElement.appendChild(classep2Element);

	        // Save level for Global.P1
	        Element classep1Lvl = doc.createElement("classep1LVL");
	        classep1Lvl.appendChild(doc.createTextNode(Integer.toString(Global.P1.getLevel())));
	        rootElement.appendChild(classep1Lvl);

	        // Save level for Global.P2
	        Element classep2Lvl = doc.createElement("classep2LVL");
	        classep2Lvl.appendChild(doc.createTextNode(Integer.toString(Global.P2.getLevel())));
	        rootElement.appendChild(classep2Lvl);

	        // Save Player1Name
	        Element player1NameElement = doc.createElement("Player1Name");
	        player1NameElement.appendChild(doc.createTextNode(Global.Player1Name));
	        rootElement.appendChild(player1NameElement);

	        // Save X coordinate
	        Element xElement = doc.createElement("x");
	        xElement.appendChild(doc.createTextNode(Integer.toString((int) Global.P1.getHitbox().getX())));
	        rootElement.appendChild(xElement);

	        // Save Y coordinate
	        Element yElement = doc.createElement("y");
	        yElement.appendChild(doc.createTextNode(Integer.toString((int) Global.P1.getHitbox().getY())));
	        rootElement.appendChild(yElement);

	        // Save coeurVaillant
	        Element coeurVaillantElement = doc.createElement("coeurVaillant");
	        coeurVaillantElement.appendChild(doc.createTextNode(Boolean.toString(Global.CoeurVaillant)));
	        rootElement.appendChild(coeurVaillantElement);

	        // Save LeveledUpForet5
	        Element leveledUpForet5Element = doc.createElement("LeveledUpForet5");
	        leveledUpForet5Element.appendChild(doc.createTextNode(Boolean.toString(Global.LeveledUpForet5)));
	        rootElement.appendChild(leveledUpForet5Element);

	        // Save Foret7Battle
	        Element foret7BattleElement = doc.createElement("Foret7Battle");
	        foret7BattleElement.appendChild(doc.createTextNode(Boolean.toString(Global.Foret7Battle)));
	        rootElement.appendChild(foret7BattleElement);
	        
	        // Save Foret6Battle
	        Element foret6BattleElement = doc.createElement("Foret6Battle");
	        foret6BattleElement.appendChild(doc.createTextNode(Boolean.toString(Global.Foret6Battle)));
	        rootElement.appendChild(foret6BattleElement);

	        // Save Foret5Battle
	        Element foret5BattleElement = doc.createElement("Foret5Battle");
	        foret5BattleElement.appendChild(doc.createTextNode(Boolean.toString(Global.Foret5Battle)));
	        rootElement.appendChild(foret5BattleElement);

	        // Write the content into XML file, overwriting existing content
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(file);

	        transformer.transform(source, result);

	        System.out.println("Game saved!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
    public static String readXmlFile(File file) {
    	try {
	        // StringBuilder to accumulate the file content
	        StringBuilder contentBuilder = new StringBuilder();
	        
	        // Try-with-resources to ensure BufferedReader is closed automatically
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String currentLine;
	            
	            // Read the file line by line
	            while ((currentLine = br.readLine()) != null) {
	                // Append each line to the StringBuilder without adding a newline
	                contentBuilder.append(currentLine.trim());
	            }
	        }
	        
	        // Convert StringBuilder to String and return it
	        return contentBuilder.toString();
	    
	    }catch(IOException e) {
	    	e.getMessage();
	    }
        // Return "" if file is empty
    	return "";
		
    }

}

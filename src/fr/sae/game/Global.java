package fr.sae.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.sae.game.caractere.Berserker;
import fr.sae.game.caractere.Healer;
import fr.sae.game.caractere.Mage;
import fr.sae.game.caractere.Player;
import fr.sae.game.caractere.Swordsman;

import java.util.HashMap;
import java.util.Map;


public class Global {
	

	//Local variable permettant de savoir si oui ou non on est en cinematique ( pour desactiver les controles pendant celle ci )
	
	public static boolean inCinematique = false;
	
	//Local variable permettant de savoir qui a la main
	
	public static Integer id=null;
	
	//Variables global d'input
	
	public static int up =Input.KEY_UP;
	public static int down =Input.KEY_DOWN;
	public static int left =Input.KEY_LEFT;
	public static int right =Input.KEY_RIGHT;
	
	public static int interract =Input.KEY_SPACE;
	
	public static  Map<String, Integer> dictionnaire = new HashMap<String, Integer>() {{put("Up", 200);put("Down", 208);put("Left", 203);put("Right", 205);put("Space", 203);}};

	// Taille de l'ecran 
	
	public static int width;
	public static int height;
	
	
	//Characters
	public static Integer MainPlayer=null;
	
	public static String Player1Classe;
	public static String Player2Classe;
	
	public static String Player1Name="";
	
	//Mobs --> Definisez tous les mobes ici 
	
	
	//Collision maps
	
	public static Collisions Map1= new Collisions();
	public static Collisions Map2= new Collisions();

	
	
	// Fonctions
	
	public static void InitializeGame() throws SlickException { //Fonction d'initialisation de project complet
		try {	
			
			Player P1;
			Player P2;

			// Initialisation de P1
			
			switch (Player1Classe) {
			    case "Swordsman":
			        P1 = new Swordsman(Player1Name, 1, null);
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
			    case "Swordsman":
			        P2 = new Swordsman("Swordsman", 1, null);
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
			

		    
		} catch(Exception e){
			e.getMessage(); //Affiche le message d'erreur en cas ou l'initialisation du project mne marche pas correctement ducoup c'est une ligne importante en cas de debug
		}
}
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	}

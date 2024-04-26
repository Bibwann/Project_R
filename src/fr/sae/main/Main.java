package fr.sae.main;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.BattleScene;
import fr.sae.game.DialogueBox;
import fr.sae.game.EntityAnimations;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.game.cinematiques.IntroGame;
import fr.sae.game.maps.Chateau1;
import fr.sae.game.maps.Foret1;
import fr.sae.game.maps.Foret2;
import fr.sae.game.maps.Foret3;
import fr.sae.menus.ClassesSelect;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;

public class Main  {
    
    public static void main(String[] args) {
        try {

            AppGameContainer app = new AppGameContainer(new StateBasedGame("Whiskers Rebellion") {
            	
                @Override
                public void initStatesList(GameContainer gc) throws SlickException {
                	
                //Definition des layers un a un

                	//Menus
                    this.addState(new MainMenu(1));
                    this.addState(new SetCharacterName(2));
                    this.addState(new OptionMenu(3));
                    this.addState(new ClassesSelect(4));
                    
                    //Cinematiques
                    this.addState(new IntroGame(5));

                    //Maps -->Partie thomas 
                    this.addState(new Foret1(6));
                    this.addState(new Foret2(7));
                    this.addState(new Foret3(8));


                    
                  //Battle scene
                  this.addState(new BattleScene(100));

                }
            });
            
            app.setShowFPS(true); //Affichage des fps en haut a gauche --> ptetre mettre un option pour l'activer serai stylé
            
            app.setIcons(new String[] {"data/logo.png"});
            
            Global.width= app.getScreenWidth();
            Global.height= app.getScreenHeight();
            
            app.setDisplayMode(Global.width, Global.height, true);
            //app.setDisplayMode(800, 600, false);
            app.start(); 
            
        } catch (SlickException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
        	System.out.print(e.getMessage());
        }
        
    }

    
}

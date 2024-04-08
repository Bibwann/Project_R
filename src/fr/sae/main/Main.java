package fr.sae.main;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.DialogueBox;
import fr.sae.game.EntityAnimations;
import fr.sae.game.Global;
import fr.sae.game.Warp;
import fr.sae.game.cinematiques.IntroGame;
import fr.sae.game.maps.Foret;
import fr.sae.menus.ClassesSelect;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SetCharacterName;
import fr.sae.warp.Taverne;

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
                    
                    //Maps
                    this.addState(new Foret(6));

                  //Sous Maps
                    this.addState(new Taverne(7));
                }
            });
            
            app.setShowFPS(false); 
            app.setIcons(new String[] {"data/image.png"});
            
            Global.width= app.getScreenWidth();
            Global.height= app.getScreenHeight();
            
            app.setDisplayMode(Global.width, Global.height, true);
            //app.setDisplayMode(800, 600, false);
            app.start(); 
            
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    
}

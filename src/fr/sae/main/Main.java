package fr.sae.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.BattleScene;
import fr.sae.game.Global;
import fr.sae.game.cinematiques.IntroGame;
import fr.sae.game.maps.Chateau1;
import fr.sae.game.maps.Foret1;
import fr.sae.game.maps.Foret2;
import fr.sae.game.maps.Foret3;
import fr.sae.game.maps.Foret4;
import fr.sae.game.maps.Foret5;
import fr.sae.game.maps.Foret6;
import fr.sae.menus.ClassesSelect;
import fr.sae.menus.MainMenu;
import fr.sae.menus.OptionMenu;
import fr.sae.menus.SaveFileSelection;
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
                    this.addState(new SaveFileSelection(2));
                    this.addState(new OptionMenu(3));
                    this.addState(new SetCharacterName(4));
                    this.addState(new ClassesSelect(5));

                    
                    //Cinematiques
                    this.addState(new IntroGame(6));

                    //Maps -->Partie thomas 
                    
                    //De 10 a 50 c'est les mapes de la foret ( oui on a de la marge )
                    this.addState(new Foret1(11));
                    this.addState(new Foret2(12));
                    this.addState(new Foret3(13));
                    this.addState(new Foret4(14));
                    this.addState(new Foret5(15));
                    this.addState(new Foret6(16));
                    
                    //De 50 a 100 c'est les mapes du chateau ( oui on a de la marge )
                    this.addState(new Foret3(51));

                  //Battle scene
                  this.addState(new BattleScene(100));                  
                  

                }
            });
            
            app.setShowFPS(true); //Affichage des fps en haut a gauche --> ptetre mettre un option pour l'activer serai stylé
                        
            Global.width= app.getScreenWidth();
            Global.height= app.getScreenHeight();
            
            app.setDisplayMode(1920, 1080, true); // --> true = Pleine ecran / false = Fenetré 
            //app.setDisplayMode(Global.width, Global.height, true); ---> Pourrais generer des bug ( ecran auto adapatif )
            //app.setDisplayMode(800, 600, false);
            
            app.setIcons(new String[] {"data/ico.png"});
            app.start(); 
            
        } catch (SlickException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
        	System.out.print(e.getMessage());	
        }
        
    }

    
}

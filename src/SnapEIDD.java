
import controleur.Controller;
import vue.UIGraphic;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 * La classe contenant le main.
 * @author pierrecharbit
 */
public class SnapEIDD {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
            Controller co = new Controller();
            /*avec UITerminal*/
            //co.userinterface = new UITerminal(co);
            //co.userinterface.displayIntro();
            //co.userinterface.launchGame(co.game);
            //while(co.game.turn<4){
                //co.userinterface.launchTurn();    
            //}
            //co.endGame();
            
            
            /*avec UIGraphic*/
            co.userinterface = new UIGraphic(co);
            co.userinterface.displayMenu();
	}
	
}

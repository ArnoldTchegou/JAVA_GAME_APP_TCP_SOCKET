/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modele.AbstractGame;
import controleur.AbstractController;
import java.awt.Color;

/**
 * Une classe qui implémente la classe abstraite {@link AbstractUI} des composants graphiques issus de la bibliothéque Swing.
 * @author pierrecharbit
 */
public class UIGraphic  extends AbstractUI {
	static final Color MYDARKBLUE = new Color(51, 0, 102);	
	JFrame mainWindow;
	GamePanel gPanel;
	MainMenuPanel2 mPanel;

	public UIGraphic(AbstractController gc)  {
		this.controller=gc;
		mainWindow = new JFrame();
		mainWindow.setVisible(true);
		
	}

	/**
	 * ne fait rien pour l'instant
	 */
	@Override
	public void displayIntro() {
	}

	
	/**
	 * ne fait rien pour l'instant.
	 */
	@Override
	public void displayLoginMenu() {
	}

	
	/**
	 * affiche un panneau avec un bouton pour lancer la partie.
	 * Ce bouton appelle la methode {@link controleur.AbstractController#setupGame()} jusqu'à ce qu'elle retourne true puis {@link controleur.AbstractController#startGame()}
	 */
	@Override
	public void displayMenu() {
		mainWindow.getContentPane().removeAll();
		mPanel = new MainMenuPanel2(this);
		mainWindow.add(mPanel); //!!!!!!!
		mainWindow.pack();//!!!!
	}


	
	
	@Override
	public void launchGame(AbstractGame game){
		this.game=game;
		//on nettoie la fenetre principale de tout ce qu'elle pouvait contenir (comme le menu précédent)
		mainWindow.getContentPane().removeAll();
		//on crée le panneau de jeu	
		gPanel = new GamePanel(controller, game);
		//on l'affecte à la fenêtre principale
		mainWindow.add(gPanel);
		mainWindow.pack();//////!!!!!!!
	}

	
	/**
	 * ne fait rien pour cette implémentation de AbstractUI
	 */
	@Override
	public void launchTurn() {
		updateGameWindow(); //!!!	
	}

	
	
	/**
	 * se contente de metter a jour le panneau de jeu.
	 */
	@Override
	public void updateGameWindow() {
		gPanel.updateAll();
	}

	@Override
	public void endGame() {
		if(game.getWinner()==controller.getIndexPlayer()){
			JOptionPane.showMessageDialog(mainWindow, "VICTOIRE");
		}
                else if (game.getWinner() == -1) { 
			JOptionPane.showMessageDialog(mainWindow, "EGALITE");
		}
		else{
			JOptionPane.showMessageDialog(mainWindow, "PERDU");
		}
		displayMenu();
		
	}	
}

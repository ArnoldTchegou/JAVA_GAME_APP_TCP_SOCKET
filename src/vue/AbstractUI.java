/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import controleur.AbstractController;
import modele.AbstractGame;

/**
 * Une interface java pour décrire la partie VUE du programme.
 * Deux implémentations sont données : UITerminal qui fait se dérouler le jeu dans le terminal et UIGraphic une version fenêtre très basique (et assez laide) utilisant la bilitohéque Swing
 * 
 * @author pierrecharbit
 */
public abstract class AbstractUI {
	AbstractGame game;
	AbstractController controller;



	
	
	
	/**
	 * affiche une séquence d'intro.
	 * Non implementé ou presque dans ma version de GUIBasic.
	 * Se contente d'appeler la fonction displayIntro ci dessous
	 */
	public abstract void displayIntro();

	/**
	 * afiche un menu permettant de selectionner son profil.
	 * Non implementé encore dans UITerminal ou UIGraphic
	 * Se contente d'appeler la fonction displayMenu ci dessous
	 */
	public abstract void displayLoginMenu();

	/**
	 * affiche le menu principal pour lancer une partie ou choisir son deck.
	 * Dans ma classe GUIBasic, affiche simplement une fenetre avec un bouton pour lancer la méthode launchGame
	 */
	public abstract void displayMenu();
	
	/**
	 * Lance un tour de jeu.
	 * surtout utile dans le cas de l'UI Terminal, pour l'UIGraphic ca fait rien.
	 */
	public abstract void launchTurn();
	
	/**
	 * declenche une partie.
	 * @param g la référence de la partie en cours
	 */
	public abstract void launchGame(AbstractGame g);

	/**
	 * met à jour l'affichage en mode partie.
	 */
	public abstract void updateGameWindow();

	/**
	 * met à jour l'affichage pour finir la partie 
	 */
	public abstract void endGame();

	
	
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modele;

import java.util.LinkedList;

/**
 * Une interface minimale pour modéliser une partie de SNAP. Vous devrez écrire
 * une classe qui implémente toutes ces fonctions pour pouvoir utiliser
 * l'interface graphique fournie.
 *
 * @author pierrecharbit
 */
public abstract class AbstractGame {

        //Variable de points
        public int[] points;
	//Variable de total_score
        public int[] total_score;
        //Energie de départ du joueur courant
        public int turn;
        //pour notifier la fin de la partie
        public boolean fin_partie;
        //pour notifier de la désactivation du verou
        public boolean unlock_verou;
        //tableau pour mémoriser le nombre cartes sur les emplacemets
        public int[] size;

	/**
	 * recupère le nom d'un joueur par son indice 0 ou 1
	 *
	 * @param side
	 * @return le nom du joueur d'indice @param i
	 */
	public abstract String getName(int side);

	/**
	 * fixe le nom d'un joueur par son indice
	 *
	 * @param side l'indice du joueur
	 * @param name le nom du joueur
	 */
	public abstract void setName(int side, String name);

	/**
	 * retourne le deck d'un joueur.
	 *
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes dans le deck du joueur
 d'indice side
	 */
	public abstract LinkedList<AbstractCard> getDeck(int side);

	/**
	 * fixe le deck d'un joueur.
	 *
	 * @param side l'indice du joueur
	 * @param deck une LinkedList de AbstractCard pour les cartes du deck
	 */
	public abstract void setDeck(int side, LinkedList<AbstractCard> deck);

	/**
	 * recupère les cartes posées sur le plateau en fonction du coté
	 *
	 * @param side le coté du plateau (0 ou 1)
	 * @return une LinkedList de AbstractCard pour les cartes du plateau du cote donné
 par side
	 */
	public abstract LinkedList<AbstractCard> getBoard(int side);

	/**
	 * recupère les Cartes posées sur le plateau en fonction du coté et sur un
	 * emplacement précis (LEFT,MIDDLE or RIGHT).
	 *
	 * @param side le coté du plateau (0 ou 1)
	 * @param p l'emplacement (LEFT,MIDDLE ou RIGHT)
	 * @return une LinkedList de AbstractCard pour les cartes du plateau du cote donné
 par side et sur la position p
	 */
	public abstract LinkedList<AbstractCard> getBoard(int side, Position p);

	/**
	 * recupère la main d'un joueur
	 *
	 * @param side
	 * @return une LinkedList de AbstractCard pour les cartes de la main du joueur
 d'indice side
	 */
	public abstract LinkedList<AbstractCard> getHand(int side);

	/**
	 * un getter pour le score d'un joueur sur une position donnée
	 *
	 * @param side l'indice du joueur
	 * @param pos la position LEFT MIDDLE OU RIGHT
	 * @return le score du joueur sur cette position
	 */
        
	public abstract int getScore(int side, Position pos);

	/**
	 * un getter pour l'energie restante d'un joueur
	 *
	 * @param side l'indice du joueur
	 * @return lenergie du joueur d'indice side
	 */
	public abstract int getEnergy(int side);

	/**
	 * un getter pour le numero du tour actuel
	 *
	 * @return le numero du tour actuel (1 pour le premier tour)
	 */
	public abstract int getTurn();
        
	/**
	 * un getter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné.
	 *
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes préparées par ce joueur à
 ce tour de jeu
	 */
	public abstract LinkedList<AbstractCard> getPlay(int side);

	/**
	 * un getter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné sur un emplacement donné
	 *
	 * @param pos la position
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes préparées par ce joueur à
 ce tour de jeu pour la position pos
	 */
	public abstract LinkedList<AbstractCard> getPlay(int side, Position pos);

	/**
	 * un setter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné.
	 *
	 * @param side l'indice du joueur
	 * @param myPlay une LinkedList de AbstractCard pour les cartes préparées par ce
 joueur à ce tour de jeu
	 */
	public abstract void setPlay(int side, LinkedList<AbstractCard> myPlay);

	/**
	 * Essaie d'ajouter une carte en position LEFT MIDDLE ou RIGHT à la
	 * proposition courante de jeu du joueur donné par side. Si le coup est
	 * valide, alors la Card est ajoutée à la proposition courante de jeu La
	 * position de la Card est aussi changée pour valoir p
	 *
	 * @param side un indice de joueur
	 * @param card une AbstractCard
	 * @param p une position ou jouer la AbstractCard
	 * @return vrai si le coup etait valide false sinon
	 */
	public abstract boolean tryPlay(int side, AbstractCard card, Position p);

        
        ///NOUVELLES METHODES POUR poser une carte sur un emplacement
        public abstract void dropCard(int side, AbstractCard card, Position p);
        
        
	/**
	 * retourne un entier 0 ou 1 désignant le gagnant actuel.
	 *
	 * @return l'indice du joueur qui gagne actuellement.
	 */
	public abstract int getWinner();
        
        public abstract void redonne_energie(int side, int energie);
	///NOUVELLE FONCTION  QUE J'AVAIS OUBLIE!!! ////
	/**
	 * Demarre le tour.
	 * pioche des cartes pour chaque joueur
	 * redonne l'energie
	 */
	public abstract void startTurn() ;  //!!!!
        
        
        ///NOUVELLES METHODES POUR MARQUER L'AJOUT d'une carte à un emplacement
        
        public abstract void setMarker(int side, Position p, boolean bool);
        
        public abstract boolean getMarker(int side, Position p);
        
        ///NOUVELLES METHODES getters pour les cartes détruites
        
        public abstract LinkedList<AbstractCard> getDestroy(int side);       
        
        ///NOUVELLES METHODES POUR MARQUER les emplacements des cartes détruites
        
        public abstract void setMarker_Destroy(int side, Position p, boolean bool);
        
        public abstract boolean[] getMarker_Destroy();
        
        ///NOUVELLES METHODES POUR MARQUER les effets continus
        public abstract void setVerou(int side, Position p, boolean bool);
        
        public abstract boolean[] getVerou();
}

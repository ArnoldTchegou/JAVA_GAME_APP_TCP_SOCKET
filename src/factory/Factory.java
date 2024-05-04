/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.util.Collections;
import java.util.LinkedList;
import modele.AbstractCard;
import modele.BasicCard;
import modele.CardEffet;

/**
 * Une classe avec des méthode statiques pour générer automatiquement des decks de base afin de tester le jeu.
 * Elle est vide pour l'instant, je vous la mets pour vous suggérer d'avoir une telle classe pour tester votre jeu et lancer des parties.
 * 
 * @author pierrecharbit
 */
public final class Factory {

	private Factory(){};
//	/**
//	 * Une méthode générant un deck de 30 cartes de bases. 
//	 * A decommenter une fois que vous avez une implémentation  AbstractCard avec un constructeur qui va bien
//	 */
//	
	public static LinkedList<AbstractCard> deckBase() {
		LinkedList<AbstractCard> deckBase = new LinkedList<>();
		deckBase.add(new BasicCard(1, 1));
		deckBase.add(new BasicCard(1, 1));
                deckBase.add(new CardEffet(0, 1, 1));
                deckBase.add(new CardEffet(0, 1, 3));
		deckBase.add(new CardEffet(0, 3, 1));
                deckBase.add(new CardEffet(3, 3, 2));
                deckBase.add(new CardEffet(5, 3, 3));
		deckBase.add(new CardEffet(0, 2, 2));
                deckBase.add(new CardEffet(0, 2, 3));
		deckBase.add(new BasicCard(2, 2));
		deckBase.add(new BasicCard(3, 3));
		deckBase.add(new BasicCard(3, 3));		
		deckBase.add(new BasicCard(4, 4));
		deckBase.add(new BasicCard(4, 4));
                deckBase.add(new CardEffet(0, 4, 4));
		deckBase.add(new BasicCard(5, 5));
		deckBase.add(new BasicCard(5, 5));
		deckBase.add(new BasicCard(6, 6));
		Collections.shuffle(deckBase);
                return deckBase;
	}


	




}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;

import factory.Factory;
import java.io.IOException;
import java.util.LinkedList;
import modele.AbstractCard;
import modele.CardEffet;
import modele.Game;
import modele.Position;
import network.ExchangeConnector;
import vue.UIGraphic;
import vue.UITerminal;

/**
 *
 * @author HP
 */
public class Controller extends AbstractController {
		LinkedList<AbstractCard> mondeck;
		LinkedList<AbstractCard> deckautre;
	/**
	 * fait tout le nécessaire pour préparer une partie.
	 * Doit initialiser le connecteur et échanger les noms de joueur, et les deck avant de créer l'objet game
	 * @return true si la connexion a fonctionné et la partie crée, false si erreur de connexion.
	 */
        @Override
	public boolean setupGame(){
            this.connector = new ExchangeConnector();
            try{
                //initConnect sur l’objet ExchangeConnector
                this.connector.initConnect();
		//on recupère le side du joueur
		int side = this.getIndexPlayer();
                //échanger les noms de joueur
		String monNom = "Joueur"+side;
                String autreNom = this.connector.exchangeObj(monNom);
                //échanger les deck avant
		mondeck = Factory.deckBase(); 
                deckautre = this.connector.exchangeObj(mondeck);
                //créer l'objet game
                this.game = new Game();
		game.setName(side, monNom);
		game.setName(1 - side, autreNom);
		return true;
            }
            catch(IOException e){
                System.out.println("Connector : Connection échoué");
                return false; 
            }  
        }

	/**
	 * demarre la partie préparer avec {@link setupGame}.
	 * doit prevenir  game que la partie commence (distribution des Cards)
	 * doit prevenir ui que la partie a démarré pour qu'elle se mette à jour et qu'elle demarre le tour
	 */
        @Override
	public void startGame(){	
            //doit prevenir  game que la partie commence (distribution des Cards)
            this.game.setDeck(this.getIndexPlayer(), mondeck);
            this.game.setDeck(1 - this.getIndexPlayer(), deckautre);
            //doit prevenir ui que la partie a démarré pour qu'elle se mette à jour et qu'elle demarre le tour
            if(this.userinterface instanceof UITerminal){
                this.userinterface.updateGameWindow();
            }
        }

	/**
	 * finit un tour de jeu.
	 * La fonction qui gère la fin d'un tour.
	 * doit commuoiquer avec game pour ajouter les propositons de coups des joueurs au plateau
	 * et doit prevenir l'ui qu'elle doit se mettre à jour pour afficher les Cards, etcc
	 * A la fin, relance le tour suivant avec startTurn
	 */
        @Override
	public void endTurn(){
            //faire l’échange via le serveur pour récupérer le choix de l’autre.
            LinkedList<AbstractCard> ton_choix = this.connector.exchangeObj(this.game.getPlay(this.getIndexPlayer()));
            this.game.setPlay(1 - this.getIndexPlayer(), ton_choix);    
            //doit commuoiquer avec game pour ajouter les propositons de coups des joueurs au plateau
            for(int side=0; side<2; side++){
                for(Position p: Position.values()){
                    if(!this.game.getPlay(side, p).isEmpty()){
                        for(AbstractCard c: this.game.getPlay(side, p)){
                            if(c.getPosition()==p){
                                this.game.getBoard(side, p).add(c);
                                //marquer l'ajout d'une carte à cette emplacement
                                this.game.setMarker(side, p, true);
                            }
                        }
                    }                
                }
            }
            //Recuperer les cartes avec Effet
            LinkedList<AbstractCard> list_card = new LinkedList<>();
            for(int side=0; side<2; side++){
                for(AbstractCard c: this.game.getBoard(side)){
                    if(c instanceof CardEffet){
                           list_card.add(c);
                    }
                }
                //Appliquer les effets
                if(!list_card.isEmpty()){
                    for(AbstractCard c: list_card){
                        c.reveal(this.game, this.getSeed());
                    }
                    list_card.clear();
                }
                this.game.getBoard(side).clear();           
            }
            
            //pour les cartes détruites
            boolean[] tab = this.connector.exchangeObj(this.game.getMarker_Destroy());
            for(Position p: Position.values()){
                if(tab[this.getIndexPlayer() + 2*p.ordinal()]){
                    this.game.getDestroy(this.getIndexPlayer()).addAll(this.game.getBoard(this.getIndexPlayer(), p));
                    this.game.getBoard(this.getIndexPlayer(), p).clear();
                }
            }
            //pour les effets continues
            boolean[] tab_verou = this.connector.exchangeObj(this.game.getVerou());
            for(Position p: Position.values()){
                if(!this.game.unlock_verou){
                    this.game.setVerou(this.getIndexPlayer(), p, tab_verou[this.getIndexPlayer() + 2*p.ordinal()]);
                }
                if(this.game.unlock_verou&&tab_verou[this.getIndexPlayer() + 2*p.ordinal()]){
                    this.game.setVerou(this.getIndexPlayer(), p, !tab_verou[this.getIndexPlayer() + 2*p.ordinal()]);
                }
            }
            //et doit prevenir l'ui qu'elle doit se mettre à jour pour afficher les Cards, etcc
            //this.userinterface.updateGameWindow();
            //remetrre le tableau de booléen à sa valeur initiale
            for(int side=0; side<2; side++){
                for(Position p: Position.values()){
                    this.game.setMarker(side, p, false);
                    this.game.setMarker_Destroy(side, p, false);
                }
            }
            //mettre à jour size
            for(Position p: Position.values()){
                this.game.size[p.ordinal()] = this.game.getBoard(this.getIndexPlayer(), p).size();
            }
            //A la fin, relance le tour suivant avec startTurn
            this.game.startTurn();
            //et doit prevenir l'ui qu'elle doit se mettre à jour pour afficher les Cards, etcc
            if(this.userinterface instanceof UIGraphic){
                this.userinterface.updateGameWindow();
            }
            //met fin à la partie si le nombre de tour est supérieure à 6
            if(this.game.fin_partie){
                this.endGame();
            }
        }   
	
	
	/**
	 * finit la partie.
	 * Affiche avec l'ui les gagnants et les perdants.
	 */
        @Override
	public void endGame(){
            //Affiche avec l'ui les gagnants et les perdants.
            this.userinterface.endGame();
        }
}

	
package controleur;

import network.ExchangeConnector;
import modele.AbstractGame;
import vue.AbstractUI;

/**
 * La classe abstraite à dériver pour le moteur central du jeu.
 * Il possède trois champs de type Game,ExchangeConnector et UI.
 * @author pierrecharbit
 */
public abstract class AbstractController {
	/** La partie modèle du jeu. */
	public AbstractGame game;
	/** Le connecteur pour l'interaction avec l'autre joueur. */
	public ExchangeConnector connector;
	/** la partie vue et interface graphique. */
	public AbstractUI userinterface;
	
	

	
	/**
	 * retourne le numero de joueur qui a été assigné par le serveur lors de la connexion du ExchangeConnector.
	 * Il s'agit du champ side de l'objet Connector.
	 * @return l'entier 0 ou 1 correspondant à l'indice du joueur sur ce client.
	 */
	public int getIndexPlayer(){
		return connector.side;
	}
	
	/**
	 * retourne la seed qui a été assigné par le serveur lors de la connexion du ExchangeConnector.* 
         * @return l'entier seed.
	 */
	public long getSeed(){
		return connector.seed;
	}

	/**
	 * lance le client.
	 * pour l'instant,  créer l'interface graphique et lance le start menu coté VUE, vous pourrez l'etoffer ensuite
	 */
	public void start(){
		//userinterface = new UIGraphic(this); //!!!!!!!!
		this.startMainMenu();
	}

	/**
	 * lance le menu principal.
	 * pour l'instant, appelle juste la fonction displayMenu sur l'useinterface
	 */
	public void startMainMenu(){
		userinterface.displayMenu(); //!!!!
	}

		
	/**
	 * fait tout le nécessaire pour préparer une partie.
	 * Doit initialiser le connecteur et échanger les noms de joueur, et les deck avant de créer l'objet game
	 * @return true si la connexion a fonctionné et la partie crée, false si erreur de connexion.
	 */
	public abstract boolean setupGame();

	/**
	 * demarre la partie préparer avec {@link setupGame}.
	 * doit prevenir  game que la partie commence (distribution des Cards)
	 * doit prevenir ui que la partie a démarré pour qu'elle se mette à jour et qu'elle demarre le tour
	 */
	public abstract void startGame();

	/**
	 * finit un tour de jeu.
	 * La fonction qui gère la fin d'un tour.
	 * doit commuoiquer avec game pour ajouter les propositons de coups des joueurs au plateau
	 * et doit prevenir l'ui qu'elle doit se mettre à jour pour afficher les Cards, etcc
	 * A la fin, relance le tour suivant avec startTurn
	 */
	public abstract void endTurn();
	
	
	/**
	 * finit la partie.
	 * Affiche avec l'ui les gagnants et les perdants.
	 */
	public abstract void endGame();

	
}

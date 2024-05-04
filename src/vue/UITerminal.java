
package vue;

import controleur.AbstractController;
import java.util.LinkedList;
import java.util.Scanner;

import modele.AbstractCard;
import modele.AbstractGame;
import modele.Position;
import static modele.Position.LEFT;
import static modele.Position.MIDDLE;
import static modele.Position.RIGHT;

/**
 * Une classe qui implémente l'interface {@link AbstractUI} dans le terminal
 *
 * @author pierrecharbit
 */
public class UITerminal extends AbstractUI {

	Scanner sc = new Scanner(System.in);
        LinkedList<AbstractCard>[] l = new LinkedList[2];
        

	public UITerminal(AbstractController gc) {
		this.controller = gc;
                this.l[0] = new LinkedList<>();
                this.l[1] = new LinkedList<>();
	}

	@Override
	public void endGame() {
		if (game.getWinner() == controller.getIndexPlayer()) { //!!!!!!
			System.out.println("Vous avez gagné !!!");
		}
                else if (game.getWinner() == -1) { 
			System.out.println("Vous etets à égalité !!!");
		}
		else {
			System.out.println("LOOOOOOOOSER !!! ");
		}
		waitForInput();
	}

	@Override
	public void displayIntro() {
		System.out.println("------------------------------ BIENVENUE DANS LE SUPER JEU ----------------------------------");
		this.displayLoginMenu();
	}

	@Override
	public void displayLoginMenu() {
		controller.startMainMenu();
	}

	@Override
	public void displayMenu() {
		while (!controller.setupGame()) {
		}
		System.out.println("stargame");
		controller.startGame();
	}

	@Override
	public void launchGame(AbstractGame g) {
		this.game = g; //// !!!!!!!!!!
		int side = controller.getIndexPlayer();
		System.out.println("------  Nouvelle Partie    ----------");
		System.out.println("Adversaire : " + game.getName(1 - side));
		this.game.startTurn();//!!!!!!
		launchTurn();
	}

	/**
	 * reaffiche le plateau et lance la méthode de saisie du coup d'un joueur.
	 */
	@Override
	public void launchTurn() {
		clearScreen();
                System.out.println("------  Affichege du debut de votre partie    ----------");
		printBoard();
		getPlay();
		controller.endTurn();
	}

	/**
	 * reaffiche le plateau.
	 */
	@Override
	public void updateGameWindow() {
		clearScreen();
		printBoard();
	}

	private void getPlay() {
		int side_me = controller.getIndexPlayer();
		this.clearScreen();
                System.out.println("------  Votre partie commence    ----------");
		this.printBoard();
		boolean ok;
		while (true) {
			ok=false;
			System.out.println("\n\nVous avez " + game.getEnergy(side_me) + " energies");
			System.out.println("\n\nVoici votre main");
			LinkedList<AbstractCard> hand = game.getHand(side_me);
			for (int i = 0; i < hand.size(); i++) {
				System.out.println(i + ")" + hand.get(i).getDescription());
			}
                        int ch = choisir_carte(side_me);
			//!!!!
			if (ch == -1) {
				break;
			}
			if (ch >= 0 && ch < hand.size()) {
				this.clearScreen();
                                System.out.println("------  Affichage de votre Partie en cours    ----------");
				this.printBoard();
				System.out.println("Carte choisie : " + hand.get(ch).getDescription());
                                int sp = choisir_emplacement(side_me);

				Position p=null;
				switch (sp) {
					case 1:
						p=LEFT;
						break;
					case 2:
						p=MIDDLE;
						break;
					case 3:
						p=RIGHT;
						break;
					default:
						break;
				}
				ok = game.tryPlay(side_me, hand.get(ch), p);
			}
			this.clearScreen();
                        System.out.println("------  Affichage de votre essaie    ----------");
			this.printBoard();
			if (!ok) {
				System.out.println("Tentative Non Valide");
			}
			
			//System.out.print("\n Voulez vous vous arrêter là et valider votre coup?");
			String rep = quiter_tour();
			if (rep.toLowerCase().equals("oui")) {
				break;
			}

		}
	}

	private void waitForInput() {
		System.out.println("Appuyez sur n'importe quel touche puis entrée pour recommencer une partie");
		sc.next();
	}

	private void printBoard() {
		int side_me = controller.getIndexPlayer();
                if(game!=null){
		System.out.println("\t---------------------------BOARD-------------------------- ");
		for (int i = 0; i < 4; i++) {
			for (Position pos : Position.values()) {
                            if(game.getBoard(1 - side_me, pos)!=null){
                                    l[1-side_me].addAll(game.getBoard(1 - side_me, pos)); ////ERREUR !!!!!
                                    AbstractCard c;
                                    if (i < l[1-side_me].size()) {
                                            c = l[1-side_me].get(i);
                                            System.out.print("\t  /" + c.getValeur() + "/");
                                    }
                                    else {
                                            System.out.print("\t  //");
                                    }
                                    System.out.print("\t\t");
                                    l[1-side_me].clear();
                            }
			}
			System.out.println("");
		}

		System.out.println("\t---------------------------------------------------------- ");

		System.out.println("");
		/*affichage des score de lui sur les terrains*/
		for (Position pos : Position.values()) {
			System.out.print("\t  " + game.getScore(1-side_me, pos) + "\t\t"); //!!!!!!
		}
		System.out.println("");
		/*affichage du nom du terrain*/

		for (Position pos : Position.values()) {
			System.out.print("\t\t\t");

//			System.out.print("\t" +game.getNameEmplacement(pos) + "\t\t");
		}
		System.out.println("");
		/*affichage des score de moi sur les terrains*/
		for (Position pos : Position.values()) {
			System.out.print("\t  " + game.getScore(side_me, pos) + "\t\t"); //!!!!!

		}
		System.out.println("\n");
		System.out.println("\t---------------------------------------------------------- ");

		for (int i = 0; i < 4; i++) {
			for (Position pos : Position.values()) {
                                if(game.getBoard(side_me, pos)!=null){
                                        l[side_me].addAll(game.getBoard(side_me, pos)); ////ERREUR !!!!!
                                        AbstractCard c;
                                        if (i < l[side_me].size()) {
                                                c = l[side_me].get(i);
                                                System.out.print("\t  /" + c.getValeur() + "/");
                                        }
                                        else {
                                                System.out.print("\t  //");
                                        }
                                        System.out.print("\t\t");
                                        l[side_me].clear();
                                }
                            }
			System.out.println("");
		}
		System.out.println("\t---------------------------BOARD-------------------------- ");
           }
        }
	private void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
        //methode pour choisisr une carte dans la main
        public int choisir_carte(int side){
            int index = -2;
            while(index>=this.game.getHand(side).size()||index<0){
                System.out.println("Quelle carte voulez vous poser? (-1 pour passer votre tour) : ");
                try{
                    index = sc.nextInt();
                    if(index == -1){
                        System.out.println("Vous avez quiter ce tour sans choisir de carte");
                        break;
                    }                
                }
                catch (java.util.InputMismatchException e){
                    sc.nextLine();
                    index = -2;
                }
            }
            return index;
        } 
        //methode pour choisisr un emplacement
        public int choisir_emplacement(int side){
            int index = -1;
            while(index>3||index<1){
                System.out.println("Sur quel emplacement voulez vous la poser (1-2-3?)   :");
                try{
                    index = sc.nextInt();
                }
                catch (java.util.InputMismatchException e){
                    sc.nextLine();
                    index = -1;
                }
            }
            return index;
        }
        //methode pour choisisr un emplacement
        public String quiter_tour(){
            String rep = "";
            while(!rep.toLowerCase().equals("oui")&&!rep.toLowerCase().equals("non")){
                System.out.print("\n Voulez vous vous arrêter là et valider votre coup?");
                rep = sc.next();
            }
            return rep;
        }
}

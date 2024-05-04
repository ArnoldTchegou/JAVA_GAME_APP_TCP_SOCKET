/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.LinkedList;

/**
 *
 * @author HP
 */
public class Game extends AbstractGame{
        //nom du joueur courant
        private String nom;
        //Variable de energie
        public int[] energie;
        //deux listes: une pour chaque main de joueur
        LinkedList<AbstractCard>[] Hand;
        //deux listes: une pour chaque deck de chaque joueur
        LinkedList<AbstractCard>[] deck;
        //six listes: une de chaque coté des trois Board
        LinkedList<AbstractCard>[] Board ;
        //deux listes: une par joueur pour les cartes qui ont été détruites ou défaussées
        LinkedList<AbstractCard>[] detruites ;
        //deux listes: une pour chaque joueur pour les coups en préparation
        LinkedList<AbstractCard>[] coups ;
        //une linkedlist pour le plateau
        LinkedList<AbstractCard>[] plateau;
        //tableau de booléen permettant de savoir si une carte a été ajoutée à un emplacement du plateau
        public boolean[] marker;
        //tableau de booléen permettant de savoir sur quel emplacement l'adversaire à détruit des cartes
        public boolean[] marker_destroy;
        //tableau de booléen permettant de savoir à quel emplacement est posé un carte à effet continu
        public boolean[] verou;
        
        //constructeur
        public Game(){
            energie = new int[2];
            points = new int[2];
            total_score = new int[2];
            Hand = new LinkedList[2];
            deck = new LinkedList[2];
            Board = new LinkedList[6];
            detruites = new LinkedList[2];
            coups = new LinkedList[2];
            plateau = new LinkedList[2];
            marker = new boolean[6];
            marker_destroy = new boolean[6];
            verou = new boolean[6];
            this.fin_partie = false;
            this.size = new int[3];
            
            for(int i=0; i<2; i++){
                this.Hand[i] = new LinkedList<>();
                this.deck[i] = new LinkedList<>();
                this.detruites[i] = new LinkedList<>();
                this.coups[i] = new LinkedList<>();
            }
            for(int i=0; i<6; i++){
                this.Board[i] = new LinkedList<>();
            }
            for(int i=0; i<2; i++){
                plateau[i]  = new LinkedList<>();
            }
        }
        
        /**
	 * recupère le nom d'un joueur par son indice 0 ou 1
	 *
	 * @param side
	 * @return le nom du joueur d'indice @param i
	 */
        @Override
	public String getName(int side){
            return this.nom;
        }

	/**
	 * fixe le nom d'un joueur par son indice
	 *
	 * @param side l'indice du joueur
	 * @param name le nom du joueur
	 */
        @Override
	public void setName(int side, String name){
            this.nom = name;
        }

	/**
	 * retourne le deck d'un joueur.
	 *
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes dans le deck du joueur d'indice side
	 */
        @Override
	public LinkedList<AbstractCard> getDeck(int side){
            return this.deck[side];
        }

	/**
	 * fixe le deck d'un joueur.
	 *
	 * @param side l'indice du joueur
	 * @param deck une LinkedList de AbstractCard pour les cartes du deck
	 */
        @Override
	public void setDeck(int side, LinkedList<AbstractCard> deck){
            this.deck[side].addAll(deck);
        }

	/**
	 * recupère les cartes posées sur le plateau en fonction du coté
	 *
	 * @param side le coté du plateau (0 ou 1)
	 * @return une LinkedList de AbstractCard pour les cartes du plateau du cote donné par side
	 */
        @Override
	public LinkedList<AbstractCard> getBoard(int side){
            for(Position p: Position.values()){
                plateau[side].addAll(this.getBoard(side, p));
            }
            return this.plateau[side];
        }

	/**
	 * recupère les Cartes posées sur le plateau en fonction du coté et sur un
 Board précis (LEFT,MIDDLE or RIGHT).
	 *
	 * @param side le coté du plateau (0 ou 1)
	 * @param p l'Board (LEFT,MIDDLE ou RIGHT)
	 * @return une LinkedList de AbstractCard pour les cartes du plateau du cote donné par side et sur la position p
	 */
        @Override
	public LinkedList<AbstractCard> getBoard(int side, Position p){
            if(side==0||side==1){
                if (p == Position.LEFT)
                    return this.Board[side];
                if (p == Position.MIDDLE)
                    return this.Board[side+2];
                if (p == Position.RIGHT)
                    return this.Board[side+4];
                }
            return null;
        }

	/**
	 * recupère la main d'un joueur
	 *
	 * @param side
	 * @return une LinkedList de AbstractCard pour les cartes de la main du joueur d'indice side
	 */
        @Override
	public LinkedList<AbstractCard> getHand(int side){
            return this.Hand[side];
        }
        
	/**
	 * un getter pour le score d'un joueur sur une position donnée
	 *
	 * @param side l'indice du joueur
	 * @param pos la position LEFT MIDDLE OU RIGHT
	 * @return le score du joueur sur cette position
	 */
        @Override
	public int getScore(int side, Position pos){
            if(getBoard(side,  pos).isEmpty()){
                return 0;
            }
            int score = 0;
            for(AbstractCard e: getBoard(side,  pos)){
                    score += e.getValeur();
            }
            return score;
        }

	/**
	 * un getter pour l'tour restante d'un joueur
	 *
	 * @param side l'indice du joueur
	 * @return lenergie du joueur d'indice side
	 */
        @Override
	public int getEnergy(int side){
            return energie[side];
        }
	/**
	 * un getter pour le numero du tour actuel
	 *
	 * @return le numero du tour actuel (1 pour le premier tour)
	 */
        @Override
	public int getTurn(){
            return turn;
        }
        
	/**
	 * un getter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné.
	 *
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes préparées par ce joueur à
 ce tour de jeu
	 */
        @Override
	public LinkedList<AbstractCard> getPlay(int side){
            return this.coups[side];
        }

	/**
	 * un getter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné sur un Board donné
	 *
	 * @param pos la position
	 * @param side l'indice du joueur
	 * @return une LinkedList de AbstractCard pour les cartes préparées par ce joueur à
 ce tour de jeu pour la position pos
	 */
        @Override
	public LinkedList<AbstractCard> getPlay(int side, Position pos){
            LinkedList<AbstractCard> carteplay = new LinkedList<>();
            LinkedList<AbstractCard> play = getPlay(side);
            for(AbstractCard e : play){
                if(e.getPosition()==pos){
                    carteplay.add(e);
                }
            }
            return carteplay;
        }

	/**
	 * un setter pour la proposition de AbstractCard à jouer à ce tour pour un joueur
 donné.
	 *
	 * @param side l'indice du joueur
	 * @param myPlay une LinkedList de AbstractCard pour les cartes préparées par ce
 joueur à ce tour de jeu
	 */
        @Override
	public void setPlay(int side, LinkedList<AbstractCard> myPlay){
            this.coups[side].addAll(myPlay);
       }

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
        @Override
	public boolean tryPlay(int side, AbstractCard card, Position p){
            if(card.getNom().equals("Unlock")&&this.size[p.ordinal()]<4&&(card.getCout()<=this.energie[side])){
                //effet de Unlock--->Lors de sa révélation : Si le verou a été activé à cette position par Deathlock
                //cette carte désactive le verou
                if(this.verou[side+2*p.ordinal()]){
                    this.setVerou(side, p, false);
                    this.unlock_verou = true;
                    dropCard(side, card, p);
                    return true;
                }
                //effet de Unlock--->Lors de sa révélation : Si le verou n'a pas été activé à cette position par Deathlock
                //cette carte perd sa valeur 
                else if(!this.verou[side+2*p.ordinal()]){
                    card.setValeur(0);
                    dropCard(side, card, p);
                    return true;
                }
            }
            else if(this.size[p.ordinal()]<4&&(card.getCout()<=this.energie[side])&&!this.verou[side+2*p.ordinal()]){
                    dropCard(side, card, p);
                    return true;
            }
            return false;
        }

        ///NOUVELLES METHODES POUR poser une carte sur un emplacement
        @Override
        public void dropCard(int side, AbstractCard card, Position p){
            card.setPosition(p);
            card.setSide(side);
            energie[side] =  energie[side] - card.getCout();
            this.coups[side].add(card);
            this.getHand(side).remove(card);
            this.size[p.ordinal()] = this.size[p.ordinal()] + 1;
        } 
        
	/**
	 * retourne un entier 0 ou 1 désignant le gagnant actuel.
	 *
	 * @return l'indice du joueur qui gagne actuellement.
	 */
        @Override
	public int getWinner(){
            int J0, J1;
            for(Position p: Position.values()){
                J0 = getScore(0, p);
                J1 = getScore(1, p);
                if(J0<J1){
                    points[1] = points[1] + 1;
                }
                else if(J1<J0) {
                    points[0] = points[0] + 1;
                }
                total_score[0] = total_score[0] + J0;
                total_score[1] = total_score[1] + J1;
            }                
            if(points[0]==points[1]){
                points[0] = total_score[0];
                points[1] = total_score[1];
                if(points[0]>points[1]){
                    return 0;
                }
                else if(points[1]>points[0]){
                    return 1;
                }
            }
            else if(points[0]>points[1]){
                return 0;
            }
            else if(points[1]>points[0]){
                return 1;
            }
            return -1;
        }
        
        @Override
        public void redonne_energie(int side, int energie){
            this.energie[side] = energie;
        }
        
	///NOUVELLE FONCTION  QUE J'AVAIS OUBLIE!!! ////
	/**
	 * Demarre le tour.
	 * pioche des cartes pour chaque joueur
	 * redonne l'energie
	 */
        @Override
        public void startTurn(){
            //Demarre le tour.
            this.turn += 1;
            //pioche des cartes pour chaque joueur
            for(int side=0; side<2; side++){
                this.energie[side] +=1;
                this.coups[side].clear();
                if(!getDeck(side).isEmpty()){
                    if(this.getHand(side).size()<2){
                        for(int j=1; j<=3; j++){
                                this.getHand(side).add(getDeck(side).poll());
                        }           
                    }
                    else{
                            this.getHand(side).add(getDeck(side).poll());
                    }
                }
                //redonne l'energie
                if(getTurn()>6){
                    this.turn = 6;
                    this.fin_partie = true;
                }
                redonne_energie(side, getTurn());
            }
        }
        
        ///NOUVELLES METHODES POUR MARQUER L'AJOUT d'une carte à un emplacement
        @Override
        public void setMarker(int side, Position p, boolean bool){
            this.marker[side + 2*p.ordinal()] = bool;
        }
        
        @Override
        public boolean getMarker(int side, Position p){
            if(p==null){
                return false;
            }
            return this.marker[side + 2*p.ordinal()];
        }
        
        @Override
        public LinkedList<AbstractCard> getDestroy(int side){
            return this.detruites[side];
        }
        
        ///NOUVELLES METHODES POUR MARQUER les emplacements des cartes détruites
        
        @Override
        public void setMarker_Destroy(int side, Position p, boolean bool){
            this.marker[side + 2*p.ordinal()] = bool;
        }
        
        @Override
        public boolean[] getMarker_Destroy(){
            return this.marker_destroy;
        }
        
        ///NOUVELLES METHODES POUR MARQUER les effets continus
        
        @Override
        public void setVerou(int side, Position p, boolean bool){
            this.verou[side + 2*p.ordinal()] = bool;
        }
        
        @Override
        public boolean[] getVerou(){
            return this.verou;
        }
        
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modele;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Une classe abstraite pour modéliser les cartes de jeu.
 * @author pierrecharbit
 */
public abstract class AbstractCard implements Serializable{
        //le proprités de la carte
        public int cout;
        public int valeur;
        public int side;
        public Position p;
        public ImageIcon image;
	
	/**
	 * le nom de la carte
	 * @return 
	 */
	public abstract String getNom();
	
		/**
	 * la valeur de la carte.
	 * @return 
	 */
	public abstract int getValeur();
        
        public abstract void setValeur(int val);
	
	
	/**
	 * retourne le cout de pose d'une carte
	 * @return un entier 
	 */
	public abstract int getCout();

	/**
	 * le texte qui decrit les pouvoirs eventuels de la carte.
	 * @return 
	 */
	public abstract String getDescription();

	/**
	 * la position actuelle de la carte si elle est posée ou en préparation.
	 * @return une valeur du type enum {@link modele.Position} si la carte est posée ou en train d'etre posée, et null sinon
	 */
	public abstract Position getPosition();
        
        public abstract void setPosition(Position p);
        
        public int getPuissance(){
            return 0;
        }
	/**
	 * effet à appliquer lorsque la carte est révélée.
	 * @param g la partie en cours
         * @param seed
	 */
	public void effetRevele(AbstractGame g, long seed){
        
        }
	
	public abstract void setSide(int i);
        
        public abstract int getSide();
        
	public abstract void reveal(AbstractGame aThis, long seed);
        

}

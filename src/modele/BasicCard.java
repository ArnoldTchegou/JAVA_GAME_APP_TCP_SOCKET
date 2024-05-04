/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
/**
 *
 * @author HP
 */
public class BasicCard extends AbstractCard{

        
        //constructeur de la classe
        public BasicCard(int v, int c){
            this.valeur = v;
            this.cout = c;
        }
        
        /**
	 * le nom de la carte
	 *@return 
	 */
        
        @Override
	public String getNom(){
            String s ="";
            switch(this.cout){
                case 1:
                    s = "Angel";
                    break;
                case 2:
                    s = "Domino";
                    break;
                case 3:
                    s = "Cosmos";
                    break;
                case 4:
                    s = "Captain";
                    break;
                case 5:
                    s = "Klaw";
                    break;
                case 6:
                    s = "Iron-Man";
                    break;
            }
            return s;
        }
	
		/**
	 * la valeur de la carte.
	 * @return 
	 */
        @Override
	public int getValeur(){
            return this.valeur;
        }
	@Override
	public void setValeur(int val){
            this.valeur = val;
        }
        
        
	/**
	 * retourne le cout de pose d'une carte
	 * @return un entier 
	 */
        @Override
	public int getCout(){
            return this.cout;
        }

	/**
	 * le texte qui decrit les pouvoirs eventuels de la carte.
	 * @return 
	 */
        @Override
	public String getDescription(){
            return "Carte: "+this.getNom()+" de valeur: "+getValeur()+" et de cout: "+this.getCout();
        }

	/**
	 * la position actuelle de la carte si elle est posée ou en préparation.
	 * @return une valeur du type enum {@link modele.Position} si la carte est posée ou en train d'etre posée, et null sinon
	 */
        @Override
	public Position getPosition(){
            return this.p;
        }
         
        
        @Override
	public void setPosition(Position p){
            this.p = p;
        }
        
        @Override
        public void setSide(int i){
            this.side = i;
        }
       
        @Override
        public int getSide(){
            return this.side;
        }
        
        @Override
        public void reveal(AbstractGame aThis, long seed){
        
        }
}

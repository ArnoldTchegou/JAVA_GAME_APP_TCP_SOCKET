/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.util.Random;

/**
 *
 * @author administrateur
 */
public class CardEffet extends AbstractCard{
        private int puissance;
        public boolean reveal_effect;
        //constructeur de la classe
        public CardEffet(int v, int c, int p){
            this.valeur = v;
            this.cout = c;
            this.puissance = p;
            reveal_effect = false;
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
                    if(this.getPuissance()==1){
                        s = "The-Punisher";
                    }
                    else if(this.getPuissance()==3){
                        s = "Ant-Man";
                    }
                    break;
                case 2:
                    if(this.getPuissance()==2){
                        s = "Medusa";
                    }
                    else if(this.getPuissance()==3){
                        s = "Star-Lord";
                    }
                    break;
                case 3:
                    if(this.getPuissance()==1){
                        s = "Blade";
                    }
                    else if(this.getValeur()==3){
                        s = "Deathlock";
                    }
                    else if(this.getValeur()==5){
                        s = "Unlock";
                    }
                    break;
                case 4:
                    s = "Cable";
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
            String s ="";
            switch(this.cout){
                case 1:
                    if(this.getPuissance()==1){
                        s = this.getNom()+": Lors de sa révélation elle diminue de -1 la Puissance de chacune des cartes sur le meme emplacement du coté de l'adversaire";
                    }
                    else if(this.getPuissance()==3){
                        s = this.getNom()+": Lors de sa révélation  : Si 3 autres cartes sont posées au meme emplacement que cette carte Elle ajoute +3 Puissance à chacune des cartes";
                    }
                    break;
                case 2:
                    if(this.getPuissance()==2){
                        s = this.getNom()+": Lors de sa révélation : Si elle se trouve à l'emplacement du milieu Elle ajoute +2 Puissance à chacune des cartes qui se trouve à l'emplacement du milieu";
                    }
                    else if(this.getPuissance()==3){
                        s = this.getNom()+": Si l'adversaire a joué une carte au meme emplacement que cette carte à ce tour Elle ajoute +3 Puissance à elle meme";
                    }
                    break;
                case 3:
                switch (this.getPuissance()) {
                    case 1:
                        s = this.getNom()+": Lors de sa révélation : Détruit toutes les cartes de l'adversaire à cet emplacemet";
                        break;
                    case 2:
                        s = this.getNom()+": Tant que la carte est présente, il n’est plus possible de poser de carte sur cet emplacement";
                        break;
                    case 3:
                        s = this.getNom()+": Lors de sa révélation : Si le verou a été activé à cette position par Deathlock cette carte désactive le verou";
                        break;
                    default:
                        break;
                }
                    break;

                case 4:
                    s = this.getNom()+": pioche une carte dans le deck-détruit et la pose sur le meme emplacement";
            }
            return s;
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
        public int getPuissance(){
            return this.puissance;
        }
        
        /**
	 * effet à appliquer lorsque la carte est révélée.
	 * @param g la partie en cours
	*/
        @Override
	public void effetRevele(AbstractGame g, long seed){
            Random r = new Random();
            //effet de Ant-Man--->Lors de sa révélation  : Si 3 autres cartes sont posées au meme emplacement que cette carte
            //Elle ajoute +3 Puissance à chacune des cartes
            if(this.getNom().equals("Ant-Man")&&g.getBoard(this.getSide(), this.getPosition()).size()>3){
                for(AbstractCard c: g.getBoard(this.getSide(), this.getPosition())){
                    if(c!=this){
                            c.setValeur(c.getValeur()+this.getPuissance());
                    }
                }
            }
            //effet de Medusa--->Lors de sa révélation : Si elle se trouve à l'emplacement du milieu
            //Elle ajoute +2 Puissance à chacune des cartes qui se trouve à l'emplacement du milieu
            if(this.getNom().equals("Medusa")&&this.getPosition()==Position.MIDDLE){
                for(AbstractCard c: g.getBoard(this.getSide(), this.getPosition())){
                    if(c!=this){
                            c.setValeur(c.getValeur()+this.getPuissance());
                    }
                }
            }
            //effet de Star-Lord: Si l'adversaire a joué une carte au meme emplacement que cette carte à ce tour
            //Elle ajoute +3 Puissance à elle meme
            if(this.getNom().equals("Star-Lord")&&g.getMarker(1-this.getSide(), this.getPosition())){
                this.setValeur(this.getValeur()+this.getPuissance());
            }
            //effet de Punisher--->Lors de sa révélation :Elle diminue de -1 la Puissance de chacune des cartes  
            //sur le meme emplacement du coté de l'adversaire
            if(this.getNom().equals("The-Punisher")){
                for(AbstractCard c: g.getBoard(1-this.getSide(), this.getPosition())){
                    if(c!=this&&!c.getNom().equals("The-Punisher")&&c.getValeur()!=0){
                            c.setValeur(c.getValeur()-this.getPuissance());
                    }
                }
            }
            //effet de Blade--->Lors de sa révélation : Détruit toutes les cartes de l'adversaire à cet emplacemet
            if(this.getNom().equals("Blade")&&!g.getBoard(1-this.getSide(), this.getPosition()).isEmpty()){
                for(AbstractCard c: g.getBoard(1-this.getSide(), this.getPosition())){
                    if(c!=this){
                            g.getDestroy(1-this.getSide()).add(c);
                    }
                }
                g.getBoard(1-this.getSide(), this.getPosition()).clear();
                g.setMarker_Destroy(1-this.getSide(), this.getPosition(), true);
            }
            //effet de Cable--->Lors de sa révélation : pioche une carte au hasard dans le deck-détruit et la pose
            //sur le meme emplacement en se servant de la graine (seed)
            if(this.getNom().equals("Cable")&&!g.getDestroy(this.getSide()).isEmpty()){
                r.setSeed(seed);
                int alea = r.nextInt();
                if(alea<0){
                    alea = -1*alea;
                }
                AbstractCard c = g.getDestroy(this.getSide()).remove(alea%g.getDestroy(this.getSide()).size());
                if(g.tryPlay(this.getSide(), c, this.getPosition())){
                    c.setPosition(this.getPosition());
                    c.setValeur(c.getValeur()+this.getPuissance());
                    g.getBoard(this.getSide(), this.getPosition()).add(c);
                    g.size[this.getPosition().ordinal()] = g.size[this.getPosition().ordinal()] + 1;
                }
            }
            //effet continu de Deathlock--->"tant que la carte est présente, il n’est plus possible pour l'adversiare de poser de carte sur cet
            //emplacement"
            if(this.getNom().equals("Deathlock")){
                    g.setVerou(1-this.getSide(), this.getPosition(), true);
                    g.unlock_verou = false;
            }
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
            if(!this.reveal_effect){
                this.effetRevele(aThis, seed);
                this.reveal_effect = true;
            }     
        }
}

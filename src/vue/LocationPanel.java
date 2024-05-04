/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import modele.AbstractCard;
import static vue.GamePanel.CARDWIDTH;
import static vue.GamePanel.CARDHEIGHT;
import java.util.LinkedList;
import modele.Position;


/**
 * Un composant graphique pour afficher un emplacement de Jeu.
 * @author pierrecharbit
 */
class LocationPanel extends Box {

	Position pos;
	GamePanel mother;
	JPanel[] locs = new JPanel[2];
	Box centralBox;
	JLabel locName = new JLabel();

	public LocationPanel(GamePanel mother, Position pos) {
		super(BoxLayout.Y_AXIS);
		this.mother = mother;
		this.pos = pos;
		//this.setPreferredSize(new Dimension(CARDWIDTH+15,CARDHEIGHT+15));
		this.setBorder(new BevelBorder(BevelBorder.RAISED));

		for (int i = 0; i < 2; i++) {
			locs[i] = new JPanel();
			locs[i].setLayout(new GridLayout(2, 2, 5, 5));
			locs[i].setBorder(new BevelBorder(BevelBorder.RAISED));
			locs[i].setPreferredSize(new Dimension(CARDWIDTH+15,CARDHEIGHT+15));
		}
		centralBox = new Box(BoxLayout.Y_AXIS);
		centralBox.setPreferredSize((new Dimension(CARDWIDTH, CARDWIDTH)));
		centralBox.setBorder(new BevelBorder(BevelBorder.RAISED));		
		updateCentral();
		this.add(Box.createGlue());
		this.add(locs[1]);
		this.add(centralBox);
		this.add(locs[0]);
		this.add(Box.createGlue());
		

	}

	void setLocationName(String s) {
		locName.setText(s);
	}

	void setMe(LinkedList<AbstractCard> cardme) {
		locs[0].removeAll();
		for (AbstractCard card : cardme) {
			locs[0].add(new CardPanel2(mother,card));
		}
		for (int i = 0; i < 4 - cardme.size(); i++) {
			JPanel popo = new JPanel();
			popo.setPreferredSize(new Dimension(CARDWIDTH / 2, CARDHEIGHT / 2));
			locs[0].add(popo);
		}
	}

	void setOpp(LinkedList<AbstractCard> cardopp) {
		locs[1].removeAll();
		for (AbstractCard card : cardopp) {
			locs[1].add(new CardPanel2(mother,card));
		}
		for (int i = 0; i < 4 - cardopp.size(); i++) {
			JPanel popo = new JPanel();
			popo.setPreferredSize(new Dimension(CARDWIDTH / 2, CARDHEIGHT / 2));
			locs[1].add(popo);
		}
	}

	void updateCentral() {
		int side_me = mother.client.getIndexPlayer();
		int scme = mother.game.getScore(side_me, pos);
		int scopp= mother.game.getScore(1-side_me, pos);
		
		centralBox.removeAll();
		centralBox.add(new JLabel(""+scopp));
		centralBox.add(new JLabel(""));//centralBox.add(new JLabel(mother.game.getNameEmplacement(pos)));
		centralBox.add(new JLabel(""+scme));
		
	}

}

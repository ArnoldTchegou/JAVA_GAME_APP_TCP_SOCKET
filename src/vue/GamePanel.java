/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import modele.Position;
import modele.AbstractCard;
import modele.AbstractGame;
import controleur.AbstractController;

/**
 * Le composant graphique dérivé de JPanel qui contient l'affichage entier pour une partie.
 * @author pierrecharbit
 */
class GamePanel extends JPanel {

	final static int CARDWIDTH = 100;
	final static int CARDHEIGHT = 180;

	AbstractController client;
	AbstractGame game;
	
	//UIGraphic mother;
	//HandListener hlistener;
	Box handPanel;
	JPanel boardPanel;
	LocationPanel[] locationPanels;
	Box infoPanel;
	CardPanelZoom2 zoomCard;
	CardPanel2 selected;
	//JMenu menu;
	JLabel labelTour;
	JLabel labelEnergie;

        LinkedList<AbstractCard>[] l = new LinkedList[2];

	/**
	 * Creates new form GamePanel
	 *
	 * @param gc
	 * @param game
	 */
	public GamePanel(AbstractController gc, AbstractGame game) {
		this.client = gc;
		this.game = game;
		this.setLayout(new BorderLayout());

		this.labelEnergie = new JLabel();
		this.labelEnergie.setForeground(Color.WHITE);
		this.labelTour = new JLabel();
		this.labelTour.setForeground(Color.WHITE);

		this.handPanel = new Box(BoxLayout.LINE_AXIS);
		this.handPanel.setOpaque(false);
		this.handPanel.setPreferredSize(new Dimension(7 * (CARDWIDTH + 5), CARDHEIGHT + 5));

		this.boardPanel = new JPanel();
		//this.boardPanel.setPreferredSize(new Dimension(6*(CARDWIDTH/2+5), 5*(CARDHEIGHT+5)));
		this.boardPanel.setBorder(new LineBorder(Color.RED));
		this.boardPanel.setLayout(new GridLayout(1, 3));
		this.setOpaque(false);

		this.locationPanels = new LocationPanel[3];
		int i = 0;
		for (Position pos : Position.values()) {
			this.locationPanels[i] = new LocationPanel(this, pos);
			this.locationPanels[i].setOpaque(false);
			this.boardPanel.add(locationPanels[i]);
			this.locationPanels[i].setMe(new LinkedList<>());
			this.locationPanels[i].setOpp(new LinkedList<>());
			this.locationPanels[i].addMouseListener(new LocationListener(pos));
			i++;
		}

		this.infoPanel = new Box(BoxLayout.Y_AXIS);
		this.infoPanel.setOpaque(false);
		this.zoomCard = new CardPanelZoom2();
		this.zoomCard.displayText = true;
		this.infoPanel.add(Box.createGlue());
		this.infoPanel.add(this.zoomCard);
		this.infoPanel.add(Box.createGlue());
		this.infoPanel.add(labelEnergie);
		this.infoPanel.add(Box.createGlue());
		this.infoPanel.add(labelTour);
		this.infoPanel.add(Box.createGlue());

		Box cp = new Box(BoxLayout.X_AXIS);
		cp.add(boardPanel);
		cp.add(Box.createGlue());
		cp.add(infoPanel);

		Box sp = new Box(BoxLayout.X_AXIS);

		JButton jb = new JButton("Lancer le Tour");
		jb.addActionListener(e -> client.endTurn());
		sp.setOpaque(false);
		sp.add(handPanel);
		sp.add(jb);

		this.add(cp, BorderLayout.CENTER);
		this.add(sp, BorderLayout.SOUTH);

		this.setOpaque(true);
		this.setBackground(UIGraphic.MYDARKBLUE);
                
                this.l[0] = new LinkedList<>();
                this.l[1] = new LinkedList<>();
	}

	public void updateAll() {
		int side = client.getIndexPlayer();
		//LinkedList<AbstractCard> cardme, cardopp;
		int i = 0;
		for (Position pos : Position.values()) {
			LocationPanel lp = locationPanels[i];
			l[side].addAll(game.getBoard(side, pos));
			l[1-side].addAll(game.getBoard(1 - side, pos));
			lp.setMe(l[side]);
			lp.setOpp(l[1-side]);
			lp.setLocationName(""); //game.getNameEmplacement(pos));
			lp.updateCentral();
                        l[side].clear();
                        l[1-side].clear();
			i++;
		}
		this.labelEnergie.setText("Energie : " + game.getEnergy(side));
		this.labelTour.setText("Tour  : " + game.getTurn());
		updateHand(game.getHand(side));
	}

	private void updateHand(LinkedList<AbstractCard> hand) {
		handPanel.removeAll();
		int i;
		handPanel.add(Box.createGlue());
		for (i = 0; i < hand.size(); i++) {
			CardPanel2 cg = new CardPanel2(this, hand.get(i));
			handPanel.add(cg);
		}
		for (; i < 7; i++) {
			handPanel.add(new JLabel());
		}
		handPanel.add(Box.createGlue());
	}

	public void setSelected(CardPanel2 cp) {
		if (selected != null) {
			selected.setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		selected = cp;
		zoomCard.c = cp.c;
		zoomCard.repaint();
		cp.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.yellow));

	}

	class LocationListener extends MouseAdapter {

		Position position;

		LocationListener(Position pos) {
			this.position = pos;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (selected != null) {
				AbstractCard card = GamePanel.this.selected.c;
				if (GamePanel.this.handPanel.isAncestorOf(GamePanel.this.selected)) {
					if (game.tryPlay(client.getIndexPlayer(), card, position)) {
						GamePanel.this.updateAll();
					}
					else {
					}

				}
			}

		}

	}

}

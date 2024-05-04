/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import modele.AbstractCard;
import modele.BasicCard;
import modele.CardEffet;
import modele.Position;
import static vue.GamePanel.CARDHEIGHT;
import static vue.GamePanel.CARDWIDTH;



/**
 *
 * @author pierrecharbit
 */
public class CardPanel2 extends javax.swing.JPanel {

	GamePanel mother;
	AbstractCard c;
	boolean displayText = false;
        ImageIcon image;
        Image backgroundImage;

	public CardPanel2(GamePanel gp, AbstractCard c) {
		this.mother = gp;
		this.c = c;
		initComponents();
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (c != null) {
					mother.setSelected(CardPanel2.this);
				}
			}

		});
                
                if(c instanceof BasicCard){
                    switch(c.getCout()){
                    case 1:
                        image = new ImageIcon(getClass().getResource("Angel.png"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    case 2:
                        image = new ImageIcon(getClass().getResource("Domino.jpeg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    case 3:
                        image = new ImageIcon(getClass().getResource("Cosmos.jpg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    case 4:
                        image = new ImageIcon(getClass().getResource("Captain.jpg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    case 5:
                        image = new ImageIcon(getClass().getResource("Klaw.jpg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    case 6:
                        image = new ImageIcon(getClass().getResource("IronMan.jpg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        break;
                    }    
                }
                if(c instanceof CardEffet){
                    switch(c.getCout()){
                    case 1:
                        if(c.getPuissance()==1){
                            image = new ImageIcon(getClass().getResource("Punisher.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        else if(c.getPuissance()==3){
                            image = new ImageIcon(getClass().getResource("AntMan.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        break;
                    case 2:
                        if(c.getPuissance()==2){
                            image = new ImageIcon(getClass().getResource("Medusa.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        else if(c.getPuissance()==3){
                            image = new ImageIcon(getClass().getResource("StarLord.png"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        break;
                    case 3:
                        if(c.getPuissance()==1){
                            image = new ImageIcon(getClass().getResource("Blade.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        else if(c.getValeur()==3){
                            image = new ImageIcon(getClass().getResource("Deadlock.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        else if(c.getValeur()==5){
                            image = new ImageIcon(getClass().getResource("Unlock.jpg"));
                            backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                        }
                        break;
                    case 4:
                        image = new ImageIcon(getClass().getResource("Cable.jpg"));
                        backgroundImage = image.getImage().getScaledInstance(CARDWIDTH , CARDHEIGHT, WIDTH);
                    }
                }
                
	}

	public CardPanel2() {
		this.setBorder(null);
	}

	@Override
	public void repaint() {
		//super.paint();
		if (c != null) {
			super.repaint();
			Position pos = c.getPosition();
			if (pos!=null ) {
				Dimension dim = new Dimension(CARDWIDTH / 2, CARDHEIGHT / 2);
				this.setPreferredSize(dim);
				this.setMinimumSize(dim);
				this.setMaximumSize(dim);
				labelCout.setVisible(false);
			}
			else {
				Dimension dim = new Dimension(CARDWIDTH, CARDHEIGHT);
				this.setPreferredSize(dim);
				this.setMinimumSize(dim);
				this.setMaximumSize(dim);
			}
			
			if (c != null) {
				labelCout.setText("" + c.getCout());
				labelForce.setText("" + c.getValeur());
				labelNom.setText(c.getNom());

			}
			
		}
		else {
			Dimension dim = new Dimension(CARDWIDTH, CARDHEIGHT);
			this.setPreferredSize(dim);
			this.setMinimumSize(dim);
			this.setMaximumSize(dim);
		}
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  
        
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        if(backgroundImage !=null){
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }    

    private void initComponents() {

        labelCout = new javax.swing.JLabel();
        labelForce = new javax.swing.JLabel();
        labelNom = new javax.swing.JLabel();
        

        setPreferredSize(new java.awt.Dimension(60, 100));

        //labelCout.setBackground(new java.awt.Color(51, 51, 255));
        labelCout.setBackground(java.awt.Color.yellow);
        labelCout.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 14)); // NOI18N
        labelCout.setForeground(java.awt.Color.black);
        //labelCout.setForeground(new java.awt.Color(255, 255, 255));
        labelCout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCout.setOpaque(true);
        
        labelForce.setBackground(java.awt.Color.blue);
        labelForce.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 14)); // NOI18N
        labelForce.setForeground(java.awt.Color.black);
        //labelForce.setForeground(new java.awt.Color(255, 255, 255));
        labelForce.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelForce.setOpaque(true);
        
        labelNom.setFont(new java.awt.Font("Helvetica Neue", Font.BOLD, 12)); // NOI18N
        labelNom.setForeground(java.awt.Color.black);
        labelNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelCout, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(labelForce, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
            .addComponent(labelNom, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelCout, labelForce});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelForce, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(labelNom, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelCout, labelForce});

    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel labelCout;
    private javax.swing.JLabel labelForce;
    private javax.swing.JLabel labelNom;
    // End of variables declaration                   
}

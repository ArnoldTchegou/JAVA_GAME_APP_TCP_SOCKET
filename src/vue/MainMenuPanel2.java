/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

/**
 * La classe dérivée du JPanel pour le menu principal du jeu.
 * Pour l'instant affiche juste un bouton pour chercher et lancer une partie.
 * Le bouton appelle les fonctions setupGame puis startGame sur l'objet partie associé à la classe GUIBasic qui utilise ce panneau.
 * @author pierrecharbit
 */
class MainMenuPanel2 extends javax.swing.JPanel {
	UIGraphic mother;
	/**
	 * Creates new form MenuPanel
	 */
	public MainMenuPanel2(UIGraphic mother) {
		this.mother = mother;
		this.setBackground(UIGraphic.MYDARKBLUE);
		initComponents();
		
	}
                         
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        
        setBackground(UIGraphic.MYDARKBLUE);

        jButton1.setFont(new java.awt.Font("Phosphate", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("LANCER UNE PARTIE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
		while(!mother.controller.setupGame()){
		}
		
		mother.controller.startGame();
                mother.controller.userinterface.launchGame(mother.controller.game);
                mother.controller.userinterface.launchTurn();
    }                                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    // End of variables declaration                   
}

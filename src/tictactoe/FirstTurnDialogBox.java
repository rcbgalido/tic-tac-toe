package tictactoe;

import javax.swing.ButtonGroup;

/**
 *
 * @author rcbgalido
 */
public class FirstTurnDialogBox extends javax.swing.JFrame {

    private final Main main;
    private final ButtonGroup playerSymbolRadioGroup;
    private final ButtonGroup firstTurnRadioGroup;
    
    public FirstTurnDialogBox(Main main) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.main = main;
        
        playerSymbolRadioGroup = new ButtonGroup();
        playerSymbolRadioGroup.add(playersymbolXBTN);
        playerSymbolRadioGroup.add(playersymbolOBTN);
        
        firstTurnRadioGroup = new ButtonGroup();
        firstTurnRadioGroup.add(firstturnXBTN);
        firstTurnRadioGroup.add(firstturnOBTN);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        playersymbolXBTN = new javax.swing.JRadioButton();
        playersymbolOBTN = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        firstturnXBTN = new javax.swing.JRadioButton();
        firstturnOBTN = new javax.swing.JRadioButton();
        submitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(91, 125, 135));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel2.setText("Your Symbol");

        playersymbolXBTN.setBackground(new java.awt.Color(91, 125, 135));
        playersymbolXBTN.setFont(new java.awt.Font("Vrinda", 0, 11)); // NOI18N
        playersymbolXBTN.setSelected(true);
        playersymbolXBTN.setText("X");
        playersymbolXBTN.setFocusable(false);

        playersymbolOBTN.setBackground(new java.awt.Color(91, 125, 135));
        playersymbolOBTN.setFont(new java.awt.Font("Vrinda", 0, 11)); // NOI18N
        playersymbolOBTN.setText("O");
        playersymbolOBTN.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jLabel1.setText("First Turn");

        firstturnXBTN.setBackground(new java.awt.Color(91, 125, 135));
        firstturnXBTN.setFont(new java.awt.Font("Vrinda", 0, 11)); // NOI18N
        firstturnXBTN.setSelected(true);
        firstturnXBTN.setText("X");
        firstturnXBTN.setFocusable(false);

        firstturnOBTN.setBackground(new java.awt.Color(91, 125, 135));
        firstturnOBTN.setFont(new java.awt.Font("Vrinda", 0, 11)); // NOI18N
        firstturnOBTN.setText("O");
        firstturnOBTN.setFocusable(false);

        submitBtn.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        submitBtn.setText("Submit");
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(firstturnXBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstturnOBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(playersymbolXBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playersymbolOBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playersymbolXBTN)
                    .addComponent(playersymbolOBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstturnXBTN)
                    .addComponent(firstturnOBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitBtn)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (playersymbolXBTN.isSelected()) {
            main.setPlayerSymbol(Main.X_SYMBOL);
            main.setComputerSymbol(Main.O_SYMBOL);
        } else if (playersymbolOBTN.isSelected()) {
            main.setPlayerSymbol(Main.O_SYMBOL);
            main.setComputerSymbol(Main.X_SYMBOL);
        }

        if (firstturnXBTN.isSelected()) {
            main.setFirstTurnSymbol(Main.X_SYMBOL);
        } else if (firstturnOBTN.isSelected()) {
            main.setFirstTurnSymbol(Main.O_SYMBOL);
        }

        dispose();
        main.enableNewGameButton();
        main.startNewRound();
    }//GEN-LAST:event_submitBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton firstturnOBTN;
    private javax.swing.JRadioButton firstturnXBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton playersymbolOBTN;
    private javax.swing.JRadioButton playersymbolXBTN;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
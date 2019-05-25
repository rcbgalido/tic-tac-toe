package tictactoe;

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author rcbgalido
 */
public class Main extends javax.swing.JFrame {

    private boolean gridLock;
    protected final static String X_SYMBOL = "X";
    protected final static String O_SYMBOL = "O";
    
    private final static int PLAYER = 1;
    private final static int COMPUTER = 2;
    private final static int NUMBER_OF_TILES = 9;
    private final static int MILLISECONDS_IN_A_SECOND = 1000;
    
    private final static String RESOURCE_LOCATION = "resources/";
    private final static String TITLE_IMAGE_FILENAME = "01_title.png";
    private final static String BLANK_TILE_IMAGE_FILENAME = "02_tile_blank.jpg";
    private final static String X_TILE_IMAGE_FILENAME = "03_tile_x.jpg";
    private final static String O_TILE_IMAGE_FILENAME = "04_tile_o.jpg";

    private final String grid[];
    private final JButton gridButtons[];
    private final ImageIcon blankImage;
    
    private int xWins, oWins;
    private String playerSymbol, computerSymbol;
    private String firstTurnSymbol;
    private ImageIcon playerSymbolImage, computerSymbolImage;
    private ImageIcon titleImage;

    public Main() {
        initComponents();
        setLocationRelativeTo(null); // position frame in the center of the screen

        grid = new String[NUMBER_OF_TILES + 1];
        gridButtons = new JButton[]{
            null,
            oneBTN, twoBTN, threeBTN,
            fourBTN, fiveBTN, sixBTN,
            sevenBTN, eightBTN, nineBTN
        };
        blankImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + BLANK_TILE_IMAGE_FILENAME));

        resetGrid();
        lockGrid();
    }
    
    private void startNewGame() {
        lockGrid();
        resetScores();
        disableNewGameButton();
        disableNewRoundButton();
        showFirstTurnDialogBox();
    }
    
    protected void startNewRound() {
        showInformationMessage("First Turn: " + firstTurnSymbol, "New Game");
        resetGrid();
        setSymbols();

        if (isComputerFirstTurn()) {
            startComputerTurn();
        } else { // unlock grid so player can make first move
            unlockGrid();
        }
        
        disableNewRoundButton();
    }
    
    private void showFirstTurnDialogBox() {
        new FirstTurnDialogBox(this).show();
    }
    
    private void resetScores() {
        xWins = 0;
        oWins = 0;
        xTF.setText("0");
        oTF.setText("0");
    }
    
    protected void enableNewGameButton() {
        newgameBTN.setEnabled(true);
    }

    private void disableNewGameButton() {
        newgameBTN.setEnabled(false);
    }
    
    private void enableNewRoundButton() {
        newroundBTN.setEnabled(true);
    }

    private void disableNewRoundButton() {
        newroundBTN.setEnabled(false);
    }
    
    private void showInformationMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void resetGrid() {
        Arrays.fill(grid, "");

        for (int tileNumber = 1; tileNumber <= NUMBER_OF_TILES; tileNumber++) {
            gridButtons[tileNumber].setIcon(blankImage);
        }
    }
    
    private void setSymbols() {
        switch (playerSymbol) {
            case X_SYMBOL:
                playerSymbolImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + X_TILE_IMAGE_FILENAME));
                computerSymbolImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + O_TILE_IMAGE_FILENAME));
                break;
            case O_SYMBOL:
                playerSymbolImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + O_TILE_IMAGE_FILENAME));
                computerSymbolImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + X_TILE_IMAGE_FILENAME));
                break;
        }
    }
    
    private boolean isComputerFirstTurn() {
        return firstTurnSymbol.equals(computerSymbol);
    }
    
    private void startComputerTurn() {
        new computerTurnThread().start();
    }
    
    private void lockGrid() {
        gridLock = true;
    }

    protected void unlockGrid() {
        gridLock = false;
    }
    
    private boolean isGridLocked() {
        return gridLock;
    }
    
    private void onTileClick(int tileNumber) {
        if (isGridLocked() == false && grid[tileNumber].equals("")) {
            setTile(tileNumber, PLAYER);
            if (isWon(grid, playerSymbol)) {
                lockGrid();
                updateScore(playerSymbol);
                enableNewRoundButton();
                showInformationMessage(playerSymbol + " Wins!", "Message");
            } else if (isTied()) {
                lockGrid();
                enableNewRoundButton();
                showInformationMessage("It's a tie!", "Message");
            } else { // computer's turn (round not over)
                startComputerTurn();
            }
        }
    }
    
    private void setTile(int tileNumber, int moveBy) {
        if (moveBy == PLAYER) {
            gridButtons[tileNumber].setIcon(playerSymbolImage);
            grid[tileNumber] = playerSymbol;
        } else if (moveBy == COMPUTER) {
            gridButtons[tileNumber].setIcon(computerSymbolImage);
            grid[tileNumber] = computerSymbol;
        }
    }
    
    private boolean isWon(String grid[], String symbol) {
        String winSequences[] = {"123", "456", "789", "147", "258", "369", "159", "357"};
        int firstTileNumber, secondTileNumber, thirdTileNumber;
        for (String winSequence : winSequences) {
            firstTileNumber = Integer.parseInt("" + winSequence.charAt(0));
            secondTileNumber = Integer.parseInt("" + winSequence.charAt(1));
            thirdTileNumber = Integer.parseInt("" + winSequence.charAt(2));
            if (grid[firstTileNumber].equals(symbol) && grid[secondTileNumber].equals(symbol)
                    && grid[thirdTileNumber].equals(symbol)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isTied() {
        int filledTiles = 0;
        for (int tileNumber = 1; tileNumber <= NUMBER_OF_TILES; tileNumber++) {
            if (!grid[tileNumber].equals("")) {
                filledTiles++;
            }
        }
        return filledTiles == NUMBER_OF_TILES;
    }
    
    private void updateScore(String winSymbol) {
        switch (winSymbol) {
            case X_SYMBOL:
                xTF.setText("" + ++xWins);
                break;
            case O_SYMBOL:
                oTF.setText("" + ++oWins);
                break;
        }
    }
    
    protected void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    protected void setComputerSymbol(String computerSymbol) {
        this.computerSymbol = computerSymbol;
    }

    protected void setFirstTurnSymbol(String firstTurnSymbol) {
        this.firstTurnSymbol = firstTurnSymbol;
    }
    
    private void changeFirstTurnSymbol() { // from X to O or from O to X
        switch (firstTurnSymbol) {
            case X_SYMBOL:
                firstTurnSymbol = O_SYMBOL;
                break;
            case O_SYMBOL:
                firstTurnSymbol = X_SYMBOL;
                break;
        }
    }
    
    private void delay(long time) {
        try {
            sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class computerTurnThread extends Thread {

        @Override
        public void run() {
            lockGrid();
            
            delay(MILLISECONDS_IN_A_SECOND);
            
            Computer computer = new Computer(grid, computerSymbol, playerSymbol);
            setTile(computer.move(), COMPUTER);
            
            if (isWon(grid, computerSymbol)) {
                updateScore(computerSymbol);
                enableNewRoundButton();
                showInformationMessage(computerSymbol + " Wins!", "Message");
            } else if (isTied()) {
                enableNewRoundButton();
                showInformationMessage("It's a tie!", "Message");
            } else { // player's turn (round not over)
                unlockGrid();
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        oneBTN = new javax.swing.JButton();
        twoBTN = new javax.swing.JButton();
        threeBTN = new javax.swing.JButton();
        fourBTN = new javax.swing.JButton();
        fiveBTN = new javax.swing.JButton();
        sixBTN = new javax.swing.JButton();
        sevenBTN = new javax.swing.JButton();
        eightBTN = new javax.swing.JButton();
        nineBTN = new javax.swing.JButton();
        newgameBTN = new javax.swing.JButton();
        newroundBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        xTF = new javax.swing.JTextField();
        oTF = new javax.swing.JTextField();
        titleImage=new ImageIcon(getClass().getResource(RESOURCE_LOCATION + TITLE_IMAGE_FILENAME));
        jLabel3 = new javax.swing.JLabel(titleImage);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(91, 125, 135));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 60), 10));

        jPanel2.setBackground(new java.awt.Color(91, 125, 135));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(46, 50, 60), 5));
        jPanel2.setLayout(new java.awt.GridLayout(3, 3));

        oneBTN.setBackground(new java.awt.Color(46, 50, 60));
        oneBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        oneBTN.setFocusable(false);
        oneBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneBTNActionPerformed(evt);
            }
        });
        jPanel2.add(oneBTN);

        twoBTN.setBackground(new java.awt.Color(46, 50, 60));
        twoBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        twoBTN.setFocusable(false);
        twoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoBTNActionPerformed(evt);
            }
        });
        jPanel2.add(twoBTN);

        threeBTN.setBackground(new java.awt.Color(46, 50, 60));
        threeBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        threeBTN.setFocusable(false);
        threeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeBTNActionPerformed(evt);
            }
        });
        jPanel2.add(threeBTN);

        fourBTN.setBackground(new java.awt.Color(46, 50, 60));
        fourBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fourBTN.setFocusable(false);
        fourBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourBTNActionPerformed(evt);
            }
        });
        jPanel2.add(fourBTN);

        fiveBTN.setBackground(new java.awt.Color(46, 50, 60));
        fiveBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fiveBTN.setFocusable(false);
        fiveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveBTNActionPerformed(evt);
            }
        });
        jPanel2.add(fiveBTN);

        sixBTN.setBackground(new java.awt.Color(46, 50, 60));
        sixBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sixBTN.setFocusable(false);
        sixBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixBTNActionPerformed(evt);
            }
        });
        jPanel2.add(sixBTN);

        sevenBTN.setBackground(new java.awt.Color(46, 50, 60));
        sevenBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sevenBTN.setFocusable(false);
        sevenBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sevenBTNActionPerformed(evt);
            }
        });
        jPanel2.add(sevenBTN);

        eightBTN.setBackground(new java.awt.Color(46, 50, 60));
        eightBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        eightBTN.setFocusable(false);
        eightBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightBTNActionPerformed(evt);
            }
        });
        jPanel2.add(eightBTN);

        nineBTN.setBackground(new java.awt.Color(46, 50, 60));
        nineBTN.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nineBTN.setFocusable(false);
        nineBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineBTNActionPerformed(evt);
            }
        });
        jPanel2.add(nineBTN);

        newgameBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        newgameBTN.setText("New Game");
        newgameBTN.setFocusable(false);
        newgameBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newgameBTNActionPerformed(evt);
            }
        });

        newroundBTN.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        newroundBTN.setText("New Round");
        newroundBTN.setEnabled(false);
        newroundBTN.setFocusable(false);
        newroundBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newroundBTNActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 0, 20)); // NOI18N
        jLabel1.setText("X:");

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 0, 20)); // NOI18N
        jLabel2.setText("O:");

        xTF.setEditable(false);
        xTF.setBackground(new java.awt.Color(204, 204, 204));
        xTF.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        xTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        xTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        xTF.setFocusable(false);

        oTF.setEditable(false);
        oTF.setBackground(new java.awt.Color(204, 204, 204));
        oTF.setFont(new java.awt.Font("Arial Unicode MS", 0, 11)); // NOI18N
        oTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        oTF.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xTF))
                            .addComponent(newgameBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newroundBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oTF)))))
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newgameBTN)
                    .addComponent(newroundBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(xTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oTF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void oneBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneBTNActionPerformed
        onTileClick(1);
    }//GEN-LAST:event_oneBTNActionPerformed

    private void newgameBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newgameBTNActionPerformed
        startNewGame();
    }//GEN-LAST:event_newgameBTNActionPerformed

    private void twoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoBTNActionPerformed
        onTileClick(2);
    }//GEN-LAST:event_twoBTNActionPerformed

    private void threeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeBTNActionPerformed
        onTileClick(3);
    }//GEN-LAST:event_threeBTNActionPerformed

    private void fourBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourBTNActionPerformed
        onTileClick(4);
    }//GEN-LAST:event_fourBTNActionPerformed

    private void fiveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveBTNActionPerformed
        onTileClick(5);
    }//GEN-LAST:event_fiveBTNActionPerformed

    private void sixBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixBTNActionPerformed
        onTileClick(6);
    }//GEN-LAST:event_sixBTNActionPerformed

    private void sevenBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenBTNActionPerformed
        onTileClick(7);
    }//GEN-LAST:event_sevenBTNActionPerformed

    private void eightBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightBTNActionPerformed
        onTileClick(8);
    }//GEN-LAST:event_eightBTNActionPerformed

    private void nineBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineBTNActionPerformed
        onTileClick(9);
    }//GEN-LAST:event_nineBTNActionPerformed

    private void newroundBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newroundBTNActionPerformed
        changeFirstTurnSymbol();
        startNewRound();
    }//GEN-LAST:event_newroundBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eightBTN;
    private javax.swing.JButton fiveBTN;
    private javax.swing.JButton fourBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton newgameBTN;
    private javax.swing.JButton newroundBTN;
    private javax.swing.JButton nineBTN;
    private javax.swing.JTextField oTF;
    private javax.swing.JButton oneBTN;
    private javax.swing.JButton sevenBTN;
    private javax.swing.JButton sixBTN;
    private javax.swing.JButton threeBTN;
    private javax.swing.JButton twoBTN;
    private javax.swing.JTextField xTF;
    // End of variables declaration//GEN-END:variables
}

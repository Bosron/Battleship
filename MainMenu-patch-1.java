package battleship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;

public class MainMenu extends javax.swing.JFrame {

    private static Player p1 = new Player();
    private static Player p2 = new Player();
    private int shipStyle = 0;
    private static int currentPhase = -1;

    public static void setCurrentPhase(int currentPhase) {
        MainMenu.currentPhase = currentPhase;
    }

    public static int getCurrentPhase() {
        return currentPhase;
    }

    public static void setP1(Player p1) {
        MainMenu.p1 = p1;
    }

    public static void setP2(Player p2) {
        MainMenu.p2 = p2;
    }

    public MainMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtP2 = new javax.swing.JTextField();
        txtP1 = new javax.swing.JTextField();
        lblP1 = new javax.swing.JLabel();
        lblP2 = new javax.swing.JLabel();
        btnRules = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        lblNoName = new javax.swing.JLabel();
        boxStyle = new javax.swing.JComboBox<>();
        //lblStyle = new javax.swing.JLabel();
        lblNoStyle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblP1.setText("Player 1's name");

        lblP2.setText("Player 2's name");

        btnRules.setText("Show playing rules");
        btnRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRulesActionPerformed(evt);
            }
        });

        btnPlay.setText("START");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        boxStyle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Choose ship style", "Renaissance ships", "WW2 ships"}));
        boxStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxStyleActionPerformed(evt);
            }
        });

        //lblStyle.setText("Select the style of your ships.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnRules, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(200, 200, 200)
                                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(lblP1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(txtP2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                                                        .addComponent(lblNoName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(boxStyle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        //.addComponent(lblStyle, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(lblNoStyle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtP2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(lblNoName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblP1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                .addComponent(btnRules, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                //.addComponent(lblStyle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNoStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {
        run();
    }

    private void btnRulesActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "neshto si pravila", "Playing rules:", JOptionPane.PLAIN_MESSAGE);
    }

    private void boxStyleActionPerformed(java.awt.event.ActionEvent evt) {
        shipStyle = boxStyle.getSelectedIndex();
        lblNoStyle.setText("");
    }

    public static void main(String args[]) {
        new MainMenu().setVisible(true);
    }

    private void validateNameChars(String userName) {
        Pattern p = Pattern.compile("\\w*");
        Matcher m = p.matcher(userName);
        if (!m.matches()) {
            throw new InputMismatchException("InvalidChars");
        }
    }

    private void validateNameLength(String userName) {
        Pattern p = Pattern.compile(".{1,10}");
        Matcher m = p.matcher(userName);
        if (!m.matches()) {
            throw new InputMismatchException("InvalidLength");
        }
    }

    private void validateShipStyle(int shipStyle) {
        if (shipStyle == 0) {
            throw new InputMismatchException("InvalidStyle");
        }
    }

    private void setPlayer1Data() {
        try {
            validateNameChars(txtP1.getText());
            validateNameLength(txtP1.getText());
            validateShipStyle(shipStyle);
            p1.setName(txtP1.getText());
            p1.setShipStyle(shipStyle);
            this.dispose();
            currentPhase++;
            new MainMenu().setVisible(true);
        } catch (InputMismatchException e) {
            switch (e.getMessage()) {
                case "InvalidChars":
                    lblNoName.setText("Your name contains characters different from letters, numbers and underscore");
                    break;
                case "InvalidLength":
                    lblNoName.setText("Your name is too long, it must be between 1 and 10 including.");
                    break;
                case "InvalidStyle":
                    lblNoStyle.setText("Choose a ship style.");
                    break;
            }
        }
    }

    private void setPlayer2Data() {
        try {
            validateNameChars(txtP2.getText());
            validateNameLength(txtP2.getText());
            validateShipStyle(shipStyle);
            p2.setName(txtP2.getText());
            p2.setShipStyle(shipStyle);
            this.dispose();
            currentPhase++;
            run();
        } catch (InputMismatchException e) {
            switch (e.getMessage()) {
                case "InvalidChars":
                    lblNoName.setText("Your name contains characters different from letters, numbers and underscore");
                    break;
                case "InvalidLength":
                    lblNoName.setText("Your name has invalid length, it must be between 1 and 10 included.");
                    break;
                case "InvalidStyle":
                    lblNoStyle.setText("Choose a ship style.");
                    break;
            }
        }
    }

    public void run() {
        switch (currentPhase) {
            case -1:
                setPlayer1Data();
                break;
            case 0:
                setPlayer2Data();
                break;
            case 1:
                new BuildMenu(p1).setVisible(true);
                break;
            case 2:
                new BuildMenu(p2).setVisible(true);
                break;
            case 3:
                new StrikeMenu(p1, p2).setVisible(true);
                break;
            case 4:
                new StrikeMenu(p2, p1).setVisible(true);
                break;
            case 5:
                new EndScreen(p1, p2).setVisible(true);
                break;
            case 6:
                new EndScreen(p2, p1).setVisible(true);
                break;
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> boxStyle;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRules;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNoName;
    private javax.swing.JLabel lblNoStyle;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    //private javax.swing.JLabel lblStyle;
    private javax.swing.JTextField txtP1;
    private javax.swing.JTextField txtP2;
    // End of variables declaration
}

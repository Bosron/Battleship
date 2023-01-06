package battleship;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//authors: Damyan and borisks
public class CharacterCreator extends javax.swing.JFrame {

    //scene
    private JLabel PlayerNumber = new JLabel();
    private JLabel name = new JLabel();
    private JLabel shipStyleLabel = new JLabel();
    private JLabel displayShips = new JLabel();
    private JLabel done = new JLabel();
    private JLabel rules = new JLabel();
    private JLabel background = new JLabel();
    private JLabel admiral = new JLabel();
    private JLabel leftArrow = new JLabel();
    private JLabel rightArrow = new JLabel();
    private JLayeredPane layeredPane = new JLayeredPane();
    private JTextField lblName = new JTextField();
    private JComboBox<String> cmbShipStyle = new JComboBox();

    //admiral cycling
    private int cycle = 1;
    String commonAdmiralFileName = "src/images/Admiral.png";
    String newAdmiralFileName = commonAdmiralFileName.substring(0, 18) + "1" + commonAdmiralFileName.substring(18);

    //run
    private static Player p1 = new Player();
    private static Player p2 = new Player();
    private int shipStyle = 0;
    private static int currentPhase = -1;

    public CharacterCreator() {

        this.setBounds(100, 10, 914, 759);
        setLocationRelativeTo(null);
        layeredPane.setBounds(0, 0, 900, 720);

        //inicializirane
        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(new ImageIcon("src/images/CharacterCreatorBackground.png"));
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        background.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="PlayerNumber">
        if (currentPhase == - 1) {
            PlayerNumber.setIcon(new ImageIcon("src/images/Player1.png"));
        } else {
            PlayerNumber.setIcon(new ImageIcon("src/images/Player2.png"));
        }

        PlayerNumber.setBounds(225, 20, 424, 132);
        PlayerNumber.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="ShipStyle:">
        shipStyleLabel.setIcon(new ImageIcon("src/images/ShipStyle.png"));
        shipStyleLabel.setBounds(65, 249, 170, 38);
        shipStyleLabel.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="shipStyleComboBox">
        cmbShipStyle.addItem(" ");
        cmbShipStyle.addItem("Reinesance Ships");
        cmbShipStyle.addItem("WW2 Ships");
        cmbShipStyle.setBounds(245, 251, 200, 35);
        cmbShipStyle.setFont(new Font("Fira Sans", Font.BOLD, 20));
        cmbShipStyle.setOpaque(false);
        cmbShipStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String choice = (String) cb.getSelectedItem();
                if (choice.equals("Reinesance Ships")) {
                    displayShips.setIcon(new ImageIcon("src/images/RenShipShowcase.png"));
                } else if (choice.equals("WW2 Ships")) {
                    displayShips.setIcon(new ImageIcon("src/images/Ww2ShipShowcase.png"));
                } else {
                    displayShips.setIcon(new ImageIcon("src/images/BaseShipShowcase.png"));
                }

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Name:">
        name.setIcon(new ImageIcon("src/images/Name.png"));
        name.setBounds(65, 200, 111, 29);
        name.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="nameField"> 
        lblName.setBounds(186, 202, 259, 29);
        lblName.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rules">
        rules.setIcon(new ImageIcon("src/images/RulesButton.png"));
        rules.setBounds(25, 620, 188, 83);
        rules.setOpaque(false);
        rules.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "neshto si pravila", "Playing rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="done">
        done.setIcon(new ImageIcon("src/images/DoneButton.png"));
        done.setBounds(704, 620, 171, 83);
        done.setOpaque(false);
        done.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                run();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="displayShips">
        displayShips.setIcon(new ImageIcon("src/images/BaseShipShowcase.png"));
        displayShips.setBounds(65, 300, 398, 276);
        displayShips.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="admiral">
        admiral.setIcon(new ImageIcon("src/images/Admiral1.png"));
        admiral.setBounds(519, 164, 325, 412);
        admiral.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="leftArrow">
        leftArrow.setIcon(new ImageIcon("src/images/LeftArrow.png"));
        leftArrow.setBounds(550, 475, 40, 75);
        leftArrow.setOpaque(false);
        leftArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (cycle == 1) {
                    cycle = 7;
                } else {
                    cycle--;
                }
                admiralCycler(cycle);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rightArrow">
        rightArrow.setIcon(new ImageIcon("src/images/RightArrow.png"));
        rightArrow.setBounds(773, 475, 40, 75);
        rightArrow.setOpaque(false);
        rightArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (cycle == 7) {
                    cycle = 1;
                } else {
                    cycle++;
                }
                admiralCycler(cycle);
            }
        });
        // </editor-fold>

        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(lblName, Integer.valueOf(2));
        layeredPane.add(cmbShipStyle, Integer.valueOf(2));
        layeredPane.add(PlayerNumber, Integer.valueOf(2));
        layeredPane.add(name, Integer.valueOf(2));
        layeredPane.add(shipStyleLabel, Integer.valueOf(2));
        layeredPane.add(displayShips, Integer.valueOf(2));
        layeredPane.add(done, Integer.valueOf(2));
        layeredPane.add(rules, Integer.valueOf(2));
        layeredPane.add(admiral, Integer.valueOf(2));
        layeredPane.add(leftArrow, Integer.valueOf(3));
        layeredPane.add(rightArrow, Integer.valueOf(3));
        this.add(layeredPane);
        layeredPane.revalidate();
        this.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void setCurrentPhase(int currentPhase) {
        CharacterCreator.currentPhase = currentPhase;
    }

    public static int getCurrentPhase() {
        return currentPhase;
    }

    public static void setP1(Player p1) {
        CharacterCreator.p1 = p1;
    }

    public static void setP2(Player p2) {
        CharacterCreator.p2 = p2;
    }

    public static void main(String args[]) {
        new CharacterCreator().setVisible(true);
    }

    private void admiralCycler(int cycle) {
        newAdmiralFileName = commonAdmiralFileName.substring(0, 18) + Integer.toString(cycle) + commonAdmiralFileName.substring(18);
        admiral.setIcon(new ImageIcon(newAdmiralFileName));
        layeredPane.revalidate();
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
            validateNameChars(lblName.getText());
            validateNameLength(lblName.getText());
            validateShipStyle(shipStyle);
            p1.setName(lblName.getText());
            p1.setShipStyle(shipStyle);
            this.dispose();
            currentPhase++;
            new CharacterCreator().setVisible(true);
        } catch (InputMismatchException e) {
            switch (e.getMessage()) {
                case "InvalidChars":
                    JOptionPane.showMessageDialog(null,
                            "Your name contains characters different from letters, numbers and underscore",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "InvalidLength":
                    JOptionPane.showMessageDialog(null,
                            "Your name is too long, it must be between 1 and 10 including.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "InvalidStyle":
                    JOptionPane.showMessageDialog(null,
                            "Choose a ship style.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    private void setPlayer2Data() {
        try {
            validateNameChars(lblName.getText());
            validateNameLength(lblName.getText());
            validateShipStyle(shipStyle);
            p2.setName(lblName.getText());
            p2.setShipStyle(shipStyle);
            this.dispose();
            currentPhase++;
            run();
        } catch (InputMismatchException e) {
            switch (e.getMessage()) {
                case "InvalidChars":
                    JOptionPane.showMessageDialog(null,
                            "Your name contains characters different from letters, numbers and underscore",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "InvalidLength":
                    JOptionPane.showMessageDialog(null,
                            "Your name is too long, it must be between 1 and 10 including.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "InvalidStyle":
                    JOptionPane.showMessageDialog(null,
                            "Choose a ship style.",
                            "Error", JOptionPane.ERROR_MESSAGE);
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
}

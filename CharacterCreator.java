package battleship;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.InputMismatchException;
import java.util.Random;
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
    private JLabel txtPlayerNumber = new JLabel();
    private JLabel txtName = new JLabel();
    private JLabel shipStyleLabel = new JLabel();
    private JLabel displayShips = new JLabel();
    private JLabel doneButton = new JLabel();
    private JLabel rulesButton = new JLabel();
    private JLabel background = new JLabel();
    private JLabel admiral = new JLabel();
    private JLabel leftArrowButton = new JLabel();
    private JLabel rightArrowButton = new JLabel();
    private JLayeredPane layeredPane = new JLayeredPane();
    private JTextField txtfldName = new JTextField();
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
        Random rand = new Random();
        this.setBounds(0, 0, 914, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Battleship");
        layeredPane.setBounds(0, 0, 900, 720);

        //inicializirane

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(new ImageIcon("src/images/CharacterCreatorBackground.png"));
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        background.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtPlayerNumber">
        if (currentPhase == - 1) {
            txtPlayerNumber.setIcon(new ImageIcon("src/images/Player1.png"));
        } else {
            txtPlayerNumber.setIcon(new ImageIcon("src/images/Player2.png"));
        }

        txtPlayerNumber.setBounds(225, 20, 450, 132);
        txtPlayerNumber.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="ShipStyle:">
        shipStyleLabel.setIcon(new ImageIcon("src/images/ShipStyle.png"));
        shipStyleLabel.setBounds(65, 249, 170, 38);
        shipStyleLabel.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="shipStyleComboBox">
        cmbShipStyle.addItem("Choose ship style");
        cmbShipStyle.addItem("Renaissance Ships");
        cmbShipStyle.addItem("WW2 Ships");
        cmbShipStyle.setBounds(245, 251, 200, 35);
        cmbShipStyle.setFont(new Font("Fira Sans", Font.BOLD, 20));
        cmbShipStyle.setOpaque(true);
        cmbShipStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shipStyle = cmbShipStyle.getSelectedIndex();
                if (shipStyle == 1) {
                    displayShips.setIcon(new ImageIcon("src/images/RenShipShowcase.png"));
                } else if (shipStyle == 2) {
                    displayShips.setIcon(new ImageIcon("src/images/WW2ShipShowcase.png"));
                } else {
                    displayShips.setIcon(new ImageIcon("src/images/BaseShipShowcase.png"));
                }

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Name:">
        txtName.setIcon(new ImageIcon("src/images/Name.png"));
        txtName.setBounds(65, 200, 111, 29);
        txtName.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="nameField"> 
        txtfldName.setBounds(186, 202, 259, 29);
        txtfldName.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rulesButton">
        rulesButton.setIcon(new ImageIcon("src/images/RulesButton.png"));
        rulesButton.setBounds(25, 620, 188, 83);
        rulesButton.setOpaque(false);
        rulesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Правила:\nКому му трябват?\n"
                        + ":Пише той 42 минути след срока.", "Playing rules",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="doneButton">
        doneButton.setIcon(new ImageIcon("src/images/DoneButton.png"));
        doneButton.setBounds(704, 620, 171, 83);
        doneButton.setOpaque(false);
        doneButton.addMouseListener(new MouseAdapter() {
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
        int randomNumber = rand.nextInt(7) + 1;
        this.cycle = randomNumber;
        admiralCycler(randomNumber);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="leftArrowButton">
        leftArrowButton.setIcon(new ImageIcon("src/images/LeftArrow.png"));
        leftArrowButton.setBounds(550, 475, 40, 75);
        leftArrowButton.setOpaque(false);
        leftArrowButton.addMouseListener(new MouseAdapter() {
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

        // <editor-fold defaultstate="collapsed" desc="rightArrowButton">
        rightArrowButton.setIcon(new ImageIcon("src/images/RightArrow.png"));
        rightArrowButton.setBounds(773, 475, 40, 75);
        rightArrowButton.setOpaque(false);
        rightArrowButton.addMouseListener(new MouseAdapter() {
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

        this.add(layeredPane);
        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(txtfldName, Integer.valueOf(2));
        layeredPane.add(cmbShipStyle, Integer.valueOf(2));
        layeredPane.add(txtPlayerNumber, Integer.valueOf(2));
        layeredPane.add(txtName, Integer.valueOf(2));
        layeredPane.add(shipStyleLabel, Integer.valueOf(2));
        layeredPane.add(displayShips, Integer.valueOf(2));
        layeredPane.add(doneButton, Integer.valueOf(2));
        layeredPane.add(rulesButton, Integer.valueOf(2));
        layeredPane.add(admiral, Integer.valueOf(2));
        layeredPane.add(leftArrowButton, Integer.valueOf(3));
        layeredPane.add(rightArrowButton, Integer.valueOf(3));
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

    private void validateSameName(String userName){
        if(userName.equals(p1.getName())){
            throw new InputMismatchException("SameName");
        }
    }

    private void setPlayer1Data() {
        try {
            validateNameChars(txtfldName.getText());
            validateNameLength(txtfldName.getText());
            validateShipStyle(shipStyle);
            p1.setName(txtfldName.getText());
            p1.setShipStyle(shipStyle);
            p1.setCurrentAdmiralFileName(newAdmiralFileName);
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
                            "Your name must be between 1 and 10 characters long.",
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
            validateNameChars(txtfldName.getText());
            validateNameLength(txtfldName.getText());
            validateShipStyle(shipStyle);
            validateSameName(txtfldName.getText());
            p2.setName(txtfldName.getText());
            p2.setShipStyle(shipStyle);
            p2.setCurrentAdmiralFileName(newAdmiralFileName);
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
                            "Your name must be between 1 and 10 characters long.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "InvalidStyle":
                    JOptionPane.showMessageDialog(null,
                            "Choose a ship style.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "SameName":
                    JOptionPane.showMessageDialog(null,
                            "Your name should not be the same as the name of the other player.",
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
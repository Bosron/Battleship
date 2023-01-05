package battleship;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Damyan
 */
public class CharacterCreator extends javax.swing.JFrame {

    private JLabel PlayerNumber = new JLabel();
    private JLabel name = new JLabel();
    private JLabel shipStyle = new JLabel();
    private JLabel displayShips = new JLabel();
    private JLabel done = new JLabel();
    private JLabel rules = new JLabel();
    private JLabel background = new JLabel();
    private JLabel admiral = new JLabel();
    private JLabel leftArrow = new JLabel();
    private JLabel rightArrow = new JLabel();
    private JLayeredPane layeredPane = new JLayeredPane();
    private JTextField nameField = new JTextField();
    private JComboBox<String> shipStyleComboBox = new JComboBox();
    
    //for admiral cycling
    private int cycle = 1;
    String admiralFileName = "src/images/Admiral.png";
    String newAdmiralFileName = admiralFileName.substring(0, 18) + "1" + admiralFileName.substring(18);

    public CharacterCreator() {

        this.setBounds(100, 10, 900, 757);
        setLocationRelativeTo(null); 
        layeredPane.setBounds(0, 0, 900, 720);

        //inicializirane na vsichko statichno
        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(new ImageIcon("src/images/CharacterCreatorBackground.png"));
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        background.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="PlayerNumber">
        PlayerNumber.setIcon(new ImageIcon("src/images/Player1.png"));
        PlayerNumber.setBounds(225, 20, 424, 132);
        PlayerNumber.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="ShipStyle:">
        shipStyle.setIcon(new ImageIcon("src/images/ShipStyle.png"));
        shipStyle.setBounds(65, 249, 170, 38);
        shipStyle.setOpaque(false);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="shipStyleComboBox">
        shipStyleComboBox.addItem(" ");
        shipStyleComboBox.addItem("Reinesance Ships");
        shipStyleComboBox.addItem("WW2 Ships");
        shipStyleComboBox.setBounds(245, 251, 200, 35);
        shipStyleComboBox.setFont(new Font("Fira Sans", Font.BOLD, 20)); 
        shipStyleComboBox.setOpaque(false);
        shipStyleComboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String choice = (String)cb.getSelectedItem();
                if(choice.equals("Reinesance Ships"))
                {
                    displayShips.setIcon(new ImageIcon("src/images/RenShipShowcase.png"));
                }
                else if(choice.equals("WW2 Ships"))
                {
                    displayShips.setIcon(new ImageIcon("src/images/Ww2ShipShowcase.png"));
                }
                else
                {
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
        nameField.setBounds(186, 202, 259, 29);
        nameField.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rules">
        rules.setIcon(new ImageIcon("src/images/RulesButton.png"));
        rules.setBounds(25, 620, 188,83);
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
        done.setBounds(704, 620, 171,83);
        done.setOpaque(false);
        done.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

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
        layeredPane.add(nameField, Integer.valueOf(2));
        layeredPane.add(shipStyleComboBox, Integer.valueOf(2));
        layeredPane.add(PlayerNumber, Integer.valueOf(2));
        layeredPane.add(name, Integer.valueOf(2));
        layeredPane.add(shipStyle, Integer.valueOf(2));
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


    private void admiralCycler(int cycle) {
        newAdmiralFileName = admiralFileName.substring(0, 18) + Integer.toString(cycle) + admiralFileName.substring(18);
        admiral.setIcon(new ImageIcon(newAdmiralFileName));
        layeredPane.revalidate();
    }
}

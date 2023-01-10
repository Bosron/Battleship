package battleship;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MainMenu extends javax.swing.JFrame {

    private JLabel background = new JLabel();
    private JLabel txtTitle = new JLabel();
    private JLabel startButton = new JLabel();
    private JLabel rulesButton = new JLabel();
    private JLabel creditsButton = new JLabel();
    private JLabel optionsButton = new JLabel();
    private JLabel quitButton = new JLabel();
    private JLabel buttonBackdrop = new JLabel();
    private JLayeredPane layeredPane = new JLayeredPane();

    public MainMenu() {
        this.setBounds(0, 0, 914, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Battleship");
        layeredPane.setBounds(0, 0, 900, 720);

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(new ImageIcon("src/images/Background.png"));
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        background.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtTitle">
        txtTitle.setIcon(new ImageIcon("src/images/Title.png"));
        txtTitle.setBounds(47, 50, 806, 120);
        txtTitle.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="startButton">
        startButton.setIcon(new ImageIcon("src/images/start.png"));
        startButton.setBounds(350, 330, 200, 53);
        startButton.setOpaque(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                disposer();
                new CharacterCreator().setVisible(true);

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rulesButton">
        rulesButton.setIcon(new ImageIcon("src/images/rules.png"));
        rulesButton.setBounds(379, 403, 142, 50);
        rulesButton.setOpaque(false);
        rulesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "neshto si pravila", "Playing rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="creditsButton">
        creditsButton.setIcon(new ImageIcon("src/images/credits.png"));
        creditsButton.setBounds(360, 473, 180, 50);
        creditsButton.setOpaque(false);
        creditsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "credits", "Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="optionsButton">
        optionsButton.setIcon(new ImageIcon("src/images/options.png"));
        optionsButton.setBounds(362, 543, 175, 53);
        optionsButton.setOpaque(false);
        optionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Under Construction!", "///////////////////", JOptionPane.WARNING_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="quitButton">
        quitButton.setIcon(new ImageIcon("src/images/quit.png"));
        quitButton.setBounds(407, 614, 85, 44);
        quitButton.setOpaque(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                disposer();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="buttonBackdrop">
        buttonBackdrop.setIcon(new ImageIcon("src/images/ButtonBackdropFinished.png"));
        buttonBackdrop.setBounds(310, 300, 280, 384);
        buttonBackdrop.setOpaque(false);
        // </editor-fold>

        layeredPane.add(txtTitle, Integer.valueOf(2));
        layeredPane.add(startButton, Integer.valueOf(2));
        layeredPane.add(rulesButton, Integer.valueOf(2));
        layeredPane.add(creditsButton, Integer.valueOf(2));
        layeredPane.add(optionsButton, Integer.valueOf(2));
        layeredPane.add(quitButton, Integer.valueOf(2));
        layeredPane.add(buttonBackdrop, Integer.valueOf(2));
        layeredPane.add(background, Integer.valueOf(0));
        this.add(layeredPane);
        layeredPane.revalidate();
        this.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String args[]) {
        new MainMenu().setVisible(true);
    }

    public void disposer() {
        this.dispose();
    }
}

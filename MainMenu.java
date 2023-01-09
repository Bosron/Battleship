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
    private JLabel title = new JLabel();
    private JLabel start = new JLabel();
    private JLabel rules = new JLabel();
    private JLabel credits = new JLabel();
    private JLabel options = new JLabel();
    private JLabel quit = new JLabel();
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

        // <editor-fold defaultstate="collapsed" desc="title">
        title.setIcon(new ImageIcon("src/images/Title.png"));
        title.setBounds(47, 50, 806, 120);
        title.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="start">
        start.setIcon(new ImageIcon("src/images/start.png"));
        start.setBounds(350, 330, 200, 53);
        start.setOpaque(false);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                disposer();
                new CharacterCreator().setVisible(true);

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rules">
        rules.setIcon(new ImageIcon("src/images/rules.png"));
        rules.setBounds(379, 403, 142, 50);
        rules.setOpaque(false);
        rules.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "neshto si pravila", "Playing rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="credits">
        credits.setIcon(new ImageIcon("src/images/credits.png"));
        credits.setBounds(360, 473, 180, 50);
        credits.setOpaque(false);
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "credits", "Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="options">
        options.setIcon(new ImageIcon("src/images/options.png"));
        options.setBounds(362, 543, 175, 53);
        options.setOpaque(false);
        options.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Under Construction!", "///////////////////", JOptionPane.WARNING_MESSAGE);
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="quit">
        quit.setIcon(new ImageIcon("src/images/quit.png"));
        quit.setBounds(407, 614, 85, 44);
        quit.setOpaque(false);
        quit.addMouseListener(new MouseAdapter() {
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

        layeredPane.add(title, Integer.valueOf(2));
        layeredPane.add(start, Integer.valueOf(2));
        layeredPane.add(rules, Integer.valueOf(2));
        layeredPane.add(credits, Integer.valueOf(2));
        layeredPane.add(options, Integer.valueOf(2));
        layeredPane.add(quit, Integer.valueOf(2));
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

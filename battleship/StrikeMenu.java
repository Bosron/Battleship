package battleship;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

//authors: borisks & damyanlh

public class StrikeMenu extends javax.swing.JFrame {

    //scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private JLabel admiral = new JLabel();
    private JLabel txtName = new JLabel();

    //label array
    private DynamicLabelArray xLabels = new DynamicLabelArray(0);

    //striking
    private Player player = new Player();
    private Player opponent = new Player();
    private int strikes;
    private boolean strikePhase = true;
    private static int rounds = 1;

    //next phase
    private JLabel nextTurnButton = new JLabel(new ImageIcon("src/images/grayButton.png"));
    private JLabel txtStrikeCounter = new JLabel();
    private boolean loseCon = false;

    public StrikeMenu(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        strikes = player.getBiggestShip();
        txtStrikeCounter.setText(strikes + "");
        
        this.setBounds(0, 0, 1100, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Battleship");
        layeredPane.setBounds(0, 0, 1100, 720);

        //inicializirane

        // <editor-fold defaultstate="collapsed" desc="nextTurnButton">
        nextTurnButton.setBounds(750, 600, 100, 50);
        nextTurnButton.setOpaque(false);
        nextTurnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!strikePhase) {
                    checkForLoseCon();//proverka dali opponenta ima ostanali korabi
                    switchScene();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtStrikeCounter">
        txtStrikeCounter.setBounds(800, 10, 50, 50);
        txtStrikeCounter.setOpaque(false);
        txtStrikeCounter.setFont(new Font("Fira Sans", Font.BOLD, 40));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="admiral">
        admiral.setBounds(742, 75, 325, 412);
        admiral.setIcon(new ImageIcon(player.getCurrentAdmiralFileName()));
        admiral.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtName">
        txtName.setBounds(830, 500, 250, 29);
        txtName.setText(player.getName());
        txtName.setForeground(Color.WHITE);
        txtName.setOpaque(false);
        txtName.setFont(new Font("Fira Sans", Font.BOLD, 20));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(new ImageIcon("src/images/StrikeArea.png"));
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();

        layeredPane.add(admiral, Integer.valueOf(2));
        layeredPane.add(txtName, Integer.valueOf(2));
        layeredPane.add(nextTurnButton, Integer.valueOf(2));
        layeredPane.add(txtStrikeCounter, Integer.valueOf(2));
        layeredPane.add(background, Integer.valueOf(1));
        for (int i = 0; i < xLabels.length(); i++) {
            layeredPane.add(xLabels.elementGetter(i), Integer.valueOf(2));
        }
        markPastAttempts();
        this.addMouseListener(new MouseAdapterForCrossSpawning());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static int getRounds(){
        return rounds;
    }

    public static void setRounds(int rounds){
        StrikeMenu.rounds = rounds;
    }

    private void strikeGrid(int posX, int posY) {
        if (opponent.getGrid(posX, posY) == 3) {
            opponent.setGrid(2, posX, posY);
        } else if (opponent.getGrid(posX, posY) == 0) {
            opponent.setGrid(1, posX, posY);
        }

    }

    private void checkForSunkShips() {
        boolean isSunk = true;
        for (int i = 0; i < 20; i++) {
            Ship ship = opponent.getShip(i);
            for (int j = 0; j < ship.getShipLength(); j++) {
                if (ship.getShipOrientation() % 2 == 0) {
                    if (opponent.getGrid(ship.getPositionX() + j, ship.getPositionY()) == 3) {
                        isSunk = false;
                        j = 5;
                    }
                } else {
                    if (opponent.getGrid(ship.getPositionX(), ship.getPositionY() + j) == 3) {
                        isSunk = false;
                        j = 5;
                    }
                }
            }
            if(!ship.getIsSunk()){
                opponent.getShip(i).setIsSunk(isSunk);
            }
            isSunk = true;
        }
    }

    private boolean hasHit(int posX, int posY) {
        return !(opponent.getGrid(posX, posY) == 1 || opponent.getGrid(posX, posY) == 2);
    }

    private ImageIcon isShip(int x, int y) {
        if (opponent.getGrid(x, y) == 3 || opponent.getGrid(x, y) == 2) {
            return new ImageIcon("src/images/redX.png");
        } else if (opponent.getGrid(x, y) == 0 || opponent.getGrid(x, y) == 1) {
            return new ImageIcon("src/images/whiteX.png");
        } else {
            return new ImageIcon();
            }
    }

    private void switchScene() {
        if (CharacterCreator.getCurrentPhase() == 3) {
            CharacterCreator.setP2(opponent);
            if (loseCon) {
                CharacterCreator.setCurrentPhase(5);
            } else {
                CharacterCreator.setCurrentPhase(4);
            }
            this.dispose();
            new CharacterCreator().run();
        } else if (CharacterCreator.getCurrentPhase() == 4) {
            CharacterCreator.setP1(opponent);
            if (loseCon) {
                CharacterCreator.setCurrentPhase(6);
            } else {
                CharacterCreator.setCurrentPhase(3);
                rounds++;
            }
            this.dispose();
            new CharacterCreator().run();
        }
    }

    private void checkForLoseCon(){
        loseCon = true;
        for (int i = 0; i < 20; i++) {
            if (!opponent.getShip(i).getIsSunk()) {
                loseCon = false;
                i = 20;
            }
        }
    }

    private void markPastAttempts(){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if(opponent.getGrid(i, j) == 1 || opponent.getGrid(i, j) == 2){
                    xLabels.addLabel();
                    xLabels.spawnLabel(xLabels.length() - 1, i*60+5, j*60+5,50,50,isShip(i,j));
                    layeredPane.add(xLabels.elementGetter(xLabels.length() - 1), Integer.valueOf(2));
                    layeredPane.revalidate();
                }
            }
        }
    }

    class MouseAdapterForCrossSpawning extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (strikePhase && e.getX() - 7 < 720 && e.getY() - 29 < 720) {
                int posX = (e.getX() - 7) / 60;
                int posY = (e.getY() - 30) / 60;
                if (hasHit(posX, posY)) {
                    xLabels.addLabel();
                    xLabels.spawnLabel(xLabels.length() - 1, posX*60+5, posY*60+5,50,50,isShip(posX, posY));
                    layeredPane.add(xLabels.elementGetter(xLabels.length() - 1), Integer.valueOf(2));
                    layeredPane.revalidate();
                    strikeGrid(posX, posY);//promenia grida na playera
                    checkForSunkShips();//proverka dali celia korab e ucelen i go potopyava ako e, ako ne e go otpotopyava
                    strikes--;
                    txtStrikeCounter.setText(strikes + "");
                    if (strikes == 0) {
                        strikePhase = false;
                        nextTurnButton.setIcon(new ImageIcon("src/images/colorButton.png"));
                    }
                    checkForLoseCon();
                    if (loseCon) {
                        switchScene();
                    }
                }
            }

        }
    }
}
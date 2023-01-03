package battleship;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

//@authors: borisks & damyanlh

public class StrikeMenu extends javax.swing.JFrame {

    //scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private JLabel backdrop = new JLabel();
    private ImageIcon backDropIcon = new ImageIcon("src/images/backdrop.png");
    private ImageIcon backgroundIcon = new ImageIcon("src/images/StrikeArea.png");
    private ImageIcon redX = new ImageIcon("src/images/redX.png");
    private ImageIcon whiteX = new ImageIcon("src/images/whiteX.png");
    private ImageIcon grayButton = new ImageIcon("src/images/grayButton.png");
    private ImageIcon colorButton = new ImageIcon("src/images/colorButton.png");

    //label array
    private DynamicLabelArray xLabels = new DynamicLabelArray(0);

    //striking
    private Player player = new Player();
    private Player opponent = new Player();
    private int strikes;
    private boolean strikePhase = true;

    //next phase
    private JLabel nextTurn = new JLabel(grayButton);
    private JLabel strikeCounter = new JLabel();
    private boolean loseCon = false;

    public StrikeMenu(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        strikes = player.getBiggestShip();
        strikeCounter.setText(strikes + "");
        
        this.setBounds(100, 10, 900, 757);
        layeredPane.setBounds(0, 0, 900, 720);

        //inicializirane

        // <editor-fold defaultstate="collapsed" desc="nextTurn">
        nextTurn.setBounds(750, 600, 100, 50);
        nextTurn.setOpaque(false);
        nextTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!strikePhase) {
                    checkForLoseCon();//proverka dali opponenta ima ostanali korabi
                    switchScene();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="strikeCounter">
        strikeCounter.setBounds(800, 10, 50, 50);
        strikeCounter.setOpaque(false);
        strikeCounter.setFont(new Font("Fira Sans", Font.BOLD, 40));
        // </editor-fold>  

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="backdrop">
        backdrop.setIcon(backDropIcon);
        backdrop.setBounds(0, 0, 1980, 1080);
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();

        layeredPane.add(nextTurn, Integer.valueOf(2));
        layeredPane.add(strikeCounter, Integer.valueOf(2));
        layeredPane.add(background, Integer.valueOf(1));
        layeredPane.add(backdrop, Integer.valueOf(0));
        for (int i = 0; i < xLabels.length(); i++) {
            layeredPane.add(xLabels.elementGetter(i), Integer.valueOf(2));
        }
        markPastAttempts();//slaga hiksove kudeto igrachut veche e streylal perdishnite hodove
        this.addMouseListener(new MouseAdapterForCrossSpawning());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void strikeGrid(int posX, int posY) {
        if (opponent.getGrid(posX, posY) == 3) {
            opponent.setGrid(2, posX, posY);
        } else if (opponent.getGrid(posX, posY) == 0) {
            opponent.setGrid(1, posX, posY);
        }

    }

    private void checkForSunkShips() {//kakvoto i da se sluchva tuk e greshno -> pulen re-work
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
            return redX;
        } else if (opponent.getGrid(x, y) == 0 || opponent.getGrid(x, y) == 1) {
            return whiteX;
        }
        return new ImageIcon("src/images/bulba.png");
    }

    private void switchScene() {
        if (MainMenu.getCurrentPhase() == 3) {
            MainMenu.setP2(opponent);
            if (loseCon) {
                MainMenu.setCurrentPhase(5);
            } else {
                MainMenu.setCurrentPhase(4);
            }
            this.dispose();
            new MainMenu().run();
        } else if (MainMenu.getCurrentPhase() == 4) {
            MainMenu.setP1(opponent);
            if (loseCon) {
                MainMenu.setCurrentPhase(6);
            } else {
                MainMenu.setCurrentPhase(3);
            }
            this.dispose();
            new MainMenu().run();
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
                    xLabels.spawnLabel(xLabels.length() - 1, posX*60 +5, posY*60 + 5,50,50,isShip(posX,posY));
                    layeredPane.add(xLabels.elementGetter(xLabels.length() - 1), Integer.valueOf(2));
                    layeredPane.revalidate();
                    strikeGrid(posX, posY);//promenia grida na playera
                    checkForSunkShips();//proverka dali celia korab e ucelen i go potopyava ako e, ako ne e go otpotopyava
                    strikes--;
                    strikeCounter.setText(strikes + "");
                    if (strikes == 0) {
                        strikePhase = false;
                        nextTurn.setIcon(colorButton);
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

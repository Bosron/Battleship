package battleship;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

//@authors: borisks & damyanlh
public class StrikeMenu extends javax.swing.JFrame {

    //for scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private ImageIcon redX;
    private ImageIcon whiteX;
    private ImageIcon grayButton;
    private ImageIcon colorButton;

    //label array
    private DynamicLabelArray xLabels = new DynamicLabelArray(0);

    //for striking
    private Player player = new Player();
    private Player opponent = new Player();
    private int strikes;
    private boolean strikePhase = true;

    //for next phase
    private JLabel nextTurn = new JLabel(grayButton);
    private JLabel strikeCounter = new JLabel();
    private boolean loseCon = false;

    public StrikeMenu(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        strikes = player.getBiggestShip();
        strikeCounter.setText(strikes + "");
        //markPastAttempts();//slaga hiksove kudeto igrachut veche e streylal perdishnite hodove
        this.setBounds(100, 10, 900, 757);

        //inicializirane
        // <editor-fold defaultstate="collapsed" desc="nextTurn">
        nextTurn.setBounds(800, 600, 100, 50);
        nextTurn.setOpaque(false);
        nextTurn.setFont(new Font("Comic Sans", Font.BOLD, 10));
        nextTurn.setText("neshto");
        nextTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!strikePhase) {
                    //checkForLoseCon();//proverka dali opponenta ima ostanali korabi
                    switchScene();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="strikeCounter">
        strikeCounter.setBounds(800, 10, 50, 50);
        strikeCounter.setOpaque(false);
        strikeCounter.setFont(new Font("Comic Sans", Font.BOLD, 40));
        // </editor-fold>  

        // <editor-fold defaultstate="collapsed" desc="background">
        ImageIcon backgroundIcon = new ImageIcon("src/images/StrikeArea.png");
        layeredPane.setBounds(0, 0, 900, 720);
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();

        layeredPane.add(nextTurn, Integer.valueOf(5));
        layeredPane.add(strikeCounter, Integer.valueOf(5));
        layeredPane.add(background, Integer.valueOf(0));
        for (int i = 0; i < xLabels.length(); i++) {
            layeredPane.add(xLabels.elementGetter(i), Integer.valueOf(1));
        }
        this.addKeyListener(new MKeyListener());
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

    private void checkForSunkShips() {
        boolean isSunk = false;
        for (int i = 0; i < 20; i++) {
            Ship ship = opponent.getShip(i);
            for (int j = 0; j < ship.getShipLength(); j++) {
                if (ship.getShipOrientation() % 2 == 0) {
                    if (opponent.getGrid(ship.getPositionX() + j, ship.getPositionY()) == 2) {
                        isSunk = true;
                        j = 5;
                    }
                } else {
                    if (opponent.getGrid(ship.getPositionX(), ship.getPositionY() + j) == 2) {
                        isSunk = true;
                        j = 5;
                    }
                }
            }
            ship.setIsSunk(isSunk);
        }
    }

    private boolean hasHit(int posX, int posY) {
        return !(opponent.getGrid(posX, posY) == 1 || opponent.getGrid(posX, posY) == 2);
    }

    private ImageIcon isShip(int x, int y) {
        if (opponent.getGrid(x, y) == 3) {
            //return redX;//ne tryabva da go pokazva predi igrachut da e natisnal next turn
            return new ImageIcon("src/images/1-tile-ww2.png");
        } else if (opponent.getGrid(x, y) == 0) {
            //return whiteX;
            return new ImageIcon("src/images/5-tile-ww2.png");
        }
        return new ImageIcon("src/images/bulba.png");
    }

    private void switchScene() {
        if (MainMenu.getCurrentPhase() == 2) {
            MainMenu.setP2(opponent);
            if (loseCon) {
                MainMenu.setCurrentPhase(4);
            } else {
                MainMenu.setCurrentPhase(3);
            }
            this.dispose();
            new MainMenu().run();
        } else if (MainMenu.getCurrentPhase() == 3) {
            MainMenu.setP1(opponent);
            if (loseCon) {
                MainMenu.setCurrentPhase(4);
            } else {
                MainMenu.setCurrentPhase(2);
            }
            this.dispose();
            new MainMenu().run();
        }
    }

    class DynamicLabelArray {

        private JLabel[] labels = new JLabel[0];

        public DynamicLabelArray(int length) {
            labels = new JLabel[length];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = new JLabel();
                labels[i].setBounds(0, 0, 1, 1);
            }
        }

        public void setlabels(JLabel[] arr) {
            for (int i = 0; i < labels.length; i++) {
                this.labels[i] = arr[i];
            }
        }

        public void addLabel() {
            JLabel[] arr = new JLabel[labels.length + 1];
            for (int i = 0; i < labels.length; i++) {
                arr[i] = labels[i];
            }
            labels = new JLabel[arr.length];
            setlabels(arr);
        }

        public void removeLabel(int elementNumber) {
            if (elementNumber >= 0 && elementNumber < labels.length) {
                JLabel[] arr = new JLabel[labels.length - 1];
                for (int i = 0, y = 0; i < labels.length; i++, y++) {
                    if (i == elementNumber) {
                        labels[i].setVisible(false);
                        if (elementNumber == labels.length - 1) {
                        } else {
                            y--;
                        }
                    } else {
                        arr[y] = labels[i];
                    }
                }
                labels = new JLabel[arr.length];
                setlabels(arr);
            } else {
                System.out.println("Error: not able to remove element out of array length");
            }
        }

        public int length() {
            return labels.length;
        }

        public void spawnX(int numberInArray, int x, int y) {
            labels[numberInArray] = new JLabel();
            labels[numberInArray].setIcon(isShip(x, y));
            labels[numberInArray].setBounds(x * 60 + 5, y * 60 + 5, 50, 50);
            labels[numberInArray].setOpaque(false);
            layeredPane.add(labels[numberInArray], Integer.valueOf(2));
            layeredPane.revalidate();
        }

        public JLabel elementGetter(int numberInArray) {
            return labels[numberInArray];
        }
    }

    class MKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            char ch = event.getKeyChar();
            if (ch == 'z' || ch == 'Z') {
                if (strikes < player.getBiggestShip()) {
                    int posX = xLabels.elementGetter(xLabels.length() - 1).getX() / 60;
                    int posY = (xLabels.elementGetter(xLabels.length() - 1).getY() + 29) / 60;
                    if (opponent.getGrid(posX, posY) == 2) {
                        opponent.setGrid(3, posX, posY);
                    } else if (opponent.getGrid(posX, posY) == 1) {
                        opponent.setGrid(0, posX, posY);
                    }
                    xLabels.removeLabel(xLabels.length() - 1);
                    checkForSunkShips();
                    strikes++;
                    strikePhase = true;
                    strikeCounter.setText(strikes + "");
                    nextTurn.setIcon(grayButton);
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
                    xLabels.spawnX(xLabels.length() - 1, posX, posY);
                    strikeGrid(posX, posY);//promenia grida na playera
                    checkForSunkShips();//proverka dali celia korab e ucelen i go potopyava ako e, ako ne e go otpotopyava
                    strikes--;
                    strikeCounter.setText(strikes + "");
                    if (strikes == 0) {
                        strikePhase = false;
                        nextTurn.setIcon(colorButton);
                    }
                }
            }

        }
    }
}

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
    private JLabel name = new JLabel();
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
    private static int rounds = 1;

    //next phase
    private JLabel nextTurn = new JLabel(grayButton);
    private JLabel strikeCounter = new JLabel();
    private boolean loseCon = false;

    public StrikeMenu(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        strikes = player.getBiggestShip();
        strikeCounter.setText(strikes + "");
        
        this.setBounds(100, 10, 1130, 757);
        this.setResizable(false);
        layeredPane.setBounds(0, 0, 1130, 720);

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

        // <editor-fold defaultstate="collapsed" desc="admiral">
        admiral.setBounds(750, 75, 325, 412);
        admiral.setIcon(new ImageIcon(player.getCurrentAdmiralFileName()));
        admiral.setOpaque(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="name">
        name.setBounds(800, 500, 170, 29);
        name.setText(player.getName());
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setFont(new Font("Fira Sans", Font.BOLD, 20));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();

        layeredPane.add(admiral, Integer.valueOf(2));
        layeredPane.add(name, Integer.valueOf(2));
        layeredPane.add(nextTurn, Integer.valueOf(2));
        layeredPane.add(strikeCounter, Integer.valueOf(2));
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
            return redX;
        } else if (opponent.getGrid(x, y) == 0 || opponent.getGrid(x, y) == 1) {
            return whiteX;
        }
        return new ImageIcon("src/images/bulba.png");
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
                    xLabels.spawnX(xLabels.length() - 1, i, j);
                }
            }
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
                    checkForLoseCon();
                    if (loseCon) {
                        switchScene();
                    }
                }
            }

        }
    }
}

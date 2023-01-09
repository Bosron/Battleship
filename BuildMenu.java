package battleship;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

//@authors: borisks & damyanlh

public class BuildMenu extends javax.swing.JFrame {

    //grabing and rotation
    private int width, height;
    private int orientation;
    private Boolean canRotate = false;
    private BufferedImage auxiliaryImage;
    private Image image;
    private ImageIcon labelGhostIcon = new ImageIcon();

    //scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private JLabel name = new JLabel();
    private ImageIcon backgroundIcon = new ImageIcon("src/images/BuildArea.png");
    private ImageIcon oneTile;
    private ImageIcon twoTile;
    private ImageIcon threeTile;
    private ImageIcon fourTile;
    private ImageIcon fiveTile;
    private ImageIcon grayButton = new ImageIcon("src/images/grayButton.png");
    private ImageIcon colorButton = new ImageIcon("src/images/colorButton.png");

    //label arrays
    private DynamicLabelArray shipLabels = new DynamicLabelArray(0);
    private DynamicLabelArray staticShipLabels = new DynamicLabelArray(5);

    //adding ships
    private int metal = 20;
    private Player player = new Player();
    private boolean buildPhase = true;
    private int nomer = 0;
    private int length;

    //next phase
    private JLabel nextPhase = new JLabel();
    private JLabel metalCounter = new JLabel(metal + "");

    public BuildMenu(Player player) {
        this.player = player;

        this.setBounds(0, 0, 1294, 757);
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setTitle("Battleship");
        layeredPane.setBounds(0, 0, 1280, 720);

        //inicializirane
        this.shipStyleSetter();

        // <editor-fold defaultstate="collapsed" desc="1-tile ship">
        staticShipLabels.elementGetter(0).setIcon(oneTile);
        staticShipLabels.elementGetter(0).setBounds(800, 50, 48, 40);//tryabva da se naglasi spryamo pristanishteto
        staticShipLabels.elementGetter(0).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="2-tile ship">
        staticShipLabels.elementGetter(1).setIcon(twoTile);
        staticShipLabels.elementGetter(1).setBounds(800, 200, 80, 48);//tryabva da se naglasi spryamo pristanishteto
        staticShipLabels.elementGetter(1).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="3-tile ship">
        staticShipLabels.elementGetter(2).setIcon(threeTile);
        staticShipLabels.elementGetter(2).setBounds(800, 400, 160, 48);//tryabva da se naglasi spryamo pristanishteto
        staticShipLabels.elementGetter(2).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="4-tile ship">
        staticShipLabels.elementGetter(3).setIcon(fourTile);
        staticShipLabels.elementGetter(3).setBounds(900, 300, 200, 48);//tryabva da se naglasi spryamo pristanishteto
        staticShipLabels.elementGetter(3).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="5-tile ship">
        staticShipLabels.elementGetter(4).setIcon(fiveTile);
        staticShipLabels.elementGetter(4).setBounds(900, 100, 240, 48);//tryabva da se naglasi spryamo pristanishteto
        staticShipLabels.elementGetter(4).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="nextPhase">
        nextPhase.setBounds(1000, 10, 100, 50);
        nextPhase.setOpaque(false);
        nextPhase.setIcon(grayButton);
        nextPhase.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!buildPhase){
                    switchScene();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="metalCounter">
        metalCounter.setBounds(1150, 10, 50, 50);
        metalCounter.setOpaque(false);
        metalCounter.setFont(new Font("Fira Sans", Font.BOLD, 40));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="name">
        name.setBounds(730, 375, 170, 29);
        name.setText(player.getName());
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setFont(new Font("Fira Sans", Font.BOLD, 20));
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();
        this.addKeyListener(new MKeyListener());

        layeredPane.add(name, Integer.valueOf(2));
        layeredPane.add(metalCounter, Integer.valueOf(2));
        layeredPane.add(nextPhase, Integer.valueOf(2));
        layeredPane.add(background, Integer.valueOf(1));
        for (int i = 0; i < staticShipLabels.length(); i++) {
            layeredPane.add(staticShipLabels.elementGetter(i), Integer.valueOf(2));
        }
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void switchScene() {
        if (CharacterCreator.getCurrentPhase() == 1) {
            CharacterCreator.setP1(player);
            CharacterCreator.setCurrentPhase(2);
            this.dispose();
            new CharacterCreator().run();
        } else if (CharacterCreator.getCurrentPhase() == 2) {
            CharacterCreator.setP2(player);
            CharacterCreator.setCurrentPhase(3);
            this.dispose();
            new CharacterCreator().run();
        }
    }

    private void setCursorImage(Image image) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(image, new Point(15, 15), "img");
        this.setCursor(c);
    }

    private BufferedImage rotate(BufferedImage bimg, Double angle) {
        //varti potencialnia label koito shte slojim
        int temp = width;
        width = height;
        height = temp;
        //imagea koito slagame na labela
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin),
                newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww - w) / 2, (newh - h) / 2);
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }

    private BufferedImage ImageToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private void shipStyleSetter() {
        if (player.getShipStyle() == 1) {
            //zamenya s renesansovi korabi
            oneTile = new ImageIcon("src/images/1-tile-ren.png");
            twoTile = new ImageIcon("src/images/2-tile-ren.png");
            threeTile = new ImageIcon("src/images/3-tile-ren.png");
            fourTile = new ImageIcon("src/images/4-tile-ren.png");
            fiveTile = new ImageIcon("src/images/5-tile-ren.png");
        } else {
            //zamenya s ww2 korabi
            oneTile = new ImageIcon("src/images/1-tile-ww2.png");
            twoTile = new ImageIcon("src/images/2-tile-ww2.png");
            threeTile = new ImageIcon("src/images/3-tile-ww2.png");
            fourTile = new ImageIcon("src/images/4-tile-ww2.png");
            fiveTile = new ImageIcon("src/images/5-tile-ww2.png");
        }
    }

    private boolean placeCheck(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipLength = ship.getShipLength();
        int shipOrientation = ship.getShipOrientation();
        boolean isGridFree = true;
        //0 - iztok
        //1 - yug
        //2 - zapad
        //3 - sever
        if (shipOrientation % 2 == 0) {
            if (posX + shipLength - 1 < 12 && posX >= 0 && posY >= 0 && posY < 12) {
                for (int i = 0; i < shipLength; i++) {
                    if (player.getGrid(posX + i, posY) != 0) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            if (posY + shipLength - 1 < 12 && posY >= 0 && posX >= 0 && posX < 12) {
                for (int i = 0; i < shipLength; i++) {
                    if (player.getGrid(posX, posY + i) != 0) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return isGridFree;
    }

    private void buildShip(Player player, Ship ship) {
        metal -= ship.getShipLength();
        player.setShip(ship, nomer);
        nomer++;
        player.placeShipInGrid(ship);
        if (metal == 0) {
            buildPhase = false;
            nextPhase.setIcon(colorButton);
        }
    }

    private int determineX(Ship ship) {
        int posX = ship.getPositionX() * 60;
        int shipOrientation = ship.getShipOrientation();
        int shipLength = ship.getShipLength();
        if (shipOrientation % 2 == 0) {
            if (shipLength == 1) {
                posX += 6;
            } else if (shipLength == 2 || shipLength == 4) {
                posX += 20;
            } else if (shipLength == 3) {
                posX += 10;
            } else if (shipLength == 5) {
                posX += 30;
            }
        } else {
            if (shipLength == 1) {
                posX += 10;
            } else if (shipLength > 1) {
                posX += 6;
            }
        }
        return posX;
    }

    private int determineY(Ship ship) {
        int posY = ship.getPositionY() * 60;
        int shipOrientation = ship.getShipOrientation();
        int shipLength = ship.getShipLength();
        if (shipOrientation % 2 == 1) {
            if (shipLength == 1) {
                posY += 6;
            } else if (shipLength == 2 || shipLength == 4) {
                posY += 20;
            } else if (shipLength == 3) {
                posY += 10;
            } else if (shipLength == 5) {
                posY += 30;
            }
        } else {
            if (shipLength == 1) {
                posY += 10;
            } else if (shipLength > 1) {
                posY += 6;
            }
        }
        return posY;
    }

    class MKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {

            char ch = event.getKeyChar();

            if ((ch == 'r' || ch == 'R') && canRotate) {
                auxiliaryImage = rotate(ImageToBufferedImage(image), 90.0);
                image = (java.awt.Image) auxiliaryImage;
                setCursorImage(image);
                labelGhostIcon = new ImageIcon(image);
                if (orientation < 3) {
                    orientation++;
                } else {
                    orientation = 0;
                }
            }
            if (ch == 'z' || ch == 'Z') {
                if (nomer > 0) {
                    shipLabels.removeLabel(shipLabels.length() - 1);
                    layeredPane.revalidate();
                    player.clearShipInGrid(player.getShip(nomer - 1));
                    metal += player.getShip(nomer - 1).getShipLength();
                    nomer--;
                    buildPhase = true;
                    nextPhase.setIcon(grayButton);
                }
                metalCounter.setText(metal + "");
            }
        }
    }

    class MouseAdapterForShipSpawning extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < staticShipLabels.length(); i++) {
                if (e.getComponent().getX() == staticShipLabels.elementGetter(i).getX()
                        && e.getComponent().getY() == staticShipLabels.elementGetter(i).getY()) {
                    labelGhostIcon = (ImageIcon) staticShipLabels.elementGetter(i).getIcon();
                    width = staticShipLabels.elementGetter(i).getWidth();
                    height = staticShipLabels.elementGetter(i).getHeight();
                    length = i + 1;
                }
            }

            image = labelGhostIcon.getImage();
            setCursorImage(image);
            canRotate = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
            canRotate = false;
            if (buildPhase) {
                if (e.getX() + e.getComponent().getX() < 720 && e.getX() + e.getComponent().getX() > 0
                        && e.getY() + e.getComponent().getY() < 720 && e.getY() + e.getComponent().getY() > 0) {
                    int posX = (e.getX() + e.getComponent().getX()) / 60;
                    int posY = (e.getY() + e.getComponent().getY()) / 60;
                    ImageIcon shipImage = labelGhostIcon;
                    Ship ship = new Ship(length, orientation, shipImage, false, posX, posY);
                    if (placeCheck(player, ship) && (metal - length) >= 0) {
                        shipLabels.addLabel();
                        shipLabels.spawnLabel(shipLabels.length() - 1, determineX(ship), determineY(ship),width,height,labelGhostIcon);
                        layeredPane.add(shipLabels.elementGetter(shipLabels.length() - 1), Integer.valueOf(2));
                        layeredPane.revalidate();
                        buildShip(player, ship);
                        metalCounter.setText(metal + "");
                    }
                }
            }
            orientation = 0;
        }
    }
}

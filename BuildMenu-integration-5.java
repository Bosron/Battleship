package battleship;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

//@authors: borisks & damyanlh
public class BuildMenu extends javax.swing.JFrame implements MouseListener {

    //for grabing and rotation
    private int width, height;
    private int orientation;
    private Boolean canRotate = false;
    private BufferedImage auxiliaryImage;
    private Image image;
    private ImageIcon labelGhostIcon = new ImageIcon();

    //for scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private ImageIcon oneTile;
    private ImageIcon twoTile;
    private ImageIcon threeTile;
    private ImageIcon fourTile;
    private ImageIcon fiveTile;

    //label arrays
    private DynamicLabelArray shipLabels = new DynamicLabelArray(0);
    private DynamicLabelArray staticShipLabels = new DynamicLabelArray(5);

    //for adding ships
    private int metal = 20;
    private Player player = new Player();
    private boolean buildPhase = true;
    private int nomer = 0;
    private int length;

    public BuildMenu(Player player) {
        this.player = player;
        this.setBounds(100, 100, 1280, 720);
        //inicializirane na vsichko statichno
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

        layeredPane.setBounds(0, 0, 1280, 720);

        // <editor-fold defaultstate="collapsed" desc="background">
        ImageIcon backgroundIcon = new ImageIcon("src/images/BuildArea.png");
        //  Image backgroundImage = backgroundIcon.getImage();
        // Image modifiedBackgroundImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
        // backgroundIcon = new ImageIcon(modifiedBackgroundImage);
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();
        this.addKeyListener(new MKeyListener());

        layeredPane.add(background, Integer.valueOf(0));
        for (int i = 0; i < staticShipLabels.length(); i++) {
            layeredPane.add(staticShipLabels.elementGetter(i), Integer.valueOf(1));
        }

        this.setBounds(100, 100, 1280, 720);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
            //triabva da se zamenyat s renesansovite korabi
            oneTile = new ImageIcon("src/images/1-tile-ww2.png");
            twoTile = new ImageIcon("src/images/2-tile-ww2.png");
            threeTile = new ImageIcon("src/images/3-tile-ww2.png");
            fourTile = new ImageIcon("src/images/4-tile-ww2.png");
            fiveTile = new ImageIcon("src/images/5-tile-ww2.png");
        } else {
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
        }
    }
    
    private int determineX(Ship ship){
        int posX = ship.getPositionX()*60;
        int shipOrientation = ship.getShipOrientation();
        int shipLength = ship.getShipLength();
        if (shipOrientation%2 == 0) {
            if (shipLength == 1) {
                posX+=6;
            } else if (shipLength == 2 || shipLength == 4) {
                posX+=20;
            } else if (shipLength == 3) {
                posX+=10;
            } else if (shipLength == 5) {
                posX+= 30;
            }
        } else {
            if (shipLength == 1) {
                posX+=10;
            } else if (shipLength > 1) {
                posX+=6;
            }
        }
        return posX;
    }
    
    private int determineY(Ship ship){
        int posY = ship.getPositionY()*60;
        int shipOrientation = ship.getShipOrientation();
        int shipLength = ship.getShipLength();
        if (shipOrientation%2 == 1) {
            if (shipLength == 1) {
                posY+=6;
            } else if (shipLength == 2 || shipLength == 4) {
                posY+=20;
            } else if (shipLength == 3) {
                posY+=10;
            } else if (shipLength == 5) {
                posY+= 30;
            }
        } else {
            if (shipLength == 1) {
                posY+=10;
            } else if (shipLength > 1) {
                posY+=6;
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
                    metal+=player.getShip(nomer - 1).getShipLength();
                    nomer--;
                    buildPhase = true;
                }
                System.out.println(metal + " metal left");//tova tryabva da se sisplay-va na ekrana
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
                        shipLabels.spawnShip(shipLabels.length() - 1, determineX(ship), determineY(ship));
                        layeredPane.revalidate();
                        buildShip(player, ship);
                        System.out.println(metal + " metal left");//tova tryabva da se sisplay-va na ekrana
                    }
                }
            }
            orientation = 0;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Extra MouseAdapter(cannot deletes)">
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //</editor-fold>
    
    class DynamicLabelArray {

        private JLabel[] shipLabels = new JLabel[0];

        public DynamicLabelArray(int length) {
            shipLabels = new JLabel[length];
            for (int i = 0; i < shipLabels.length; i++) {
                shipLabels[i] = new JLabel();
                shipLabels[i].setBounds(0, 0, 1, 1);
            }
        }

        public void setshipLabels(JLabel[] arr) {
            for (int i = 0; i < shipLabels.length; i++) {
                this.shipLabels[i] = arr[i];
            }
        }

        public void addLabel() {
            JLabel[] arr = new JLabel[shipLabels.length + 1];
            for (int i = 0; i < shipLabels.length; i++) {
                arr[i] = shipLabels[i];

            }
            shipLabels = new JLabel[arr.length];
            setshipLabels(arr);
        }

        public void removeLabel(int elementNumber) {

            if (elementNumber >= 0 && elementNumber < shipLabels.length) {
                JLabel[] arr = new JLabel[shipLabels.length - 1];
                for (int i = 0, y = 0; i < shipLabels.length; i++, y++) {

                    if (i == elementNumber) {
                        shipLabels[i].setVisible(false);

                        if (elementNumber == shipLabels.length - 1) {

                        } else {
                            y--;
                        }
                    } else {
                        arr[y] = shipLabels[i];
                    }

                }
                shipLabels = new JLabel[arr.length];
                setshipLabels(arr);

            } else {
                System.out.println("Error: not able to remove element out of array length");
            }
        }

        public int length() {
            return shipLabels.length;
        }

        public void spawnShip(int numberInArray, int x, int y) {
            shipLabels[numberInArray] = new JLabel();
            shipLabels[numberInArray].setIcon((Icon) labelGhostIcon);
            shipLabels[numberInArray].setBounds(x, y, width, height);
            shipLabels[numberInArray].setOpaque(false);
            layeredPane.add(shipLabels[numberInArray], Integer.valueOf(2));
            layeredPane.revalidate();
        }

        public void boundsSetter(int numberInArray, int x, int y, int width, int height) {
            shipLabels[numberInArray].setBounds(x, y, width, height);
        }

        public void iconSetter(int numberInArray, Icon icon) {
            shipLabels[numberInArray].setIcon(icon);
        }

        public JLabel elementGetter(int numberInArray) {
            return shipLabels[numberInArray];
        }

    }

}

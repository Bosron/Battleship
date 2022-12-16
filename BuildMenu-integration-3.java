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

/**
 *
 * @author damyanlh
 */
public class BuildMenu extends javax.swing.JFrame implements MouseListener {

    //for grabing and rotation
    private int width, height;
    private int orientation;
    private javax.swing.JLabel jLabel1;
    private Boolean canRotate = false;
    private BufferedImage auxiliaryImage;
    private Image image;
    private ImageIcon labelGhostIcon = new ImageIcon();

    //for scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
   
    private ImageIcon oneTile = new ImageIcon("src/images/1-tile viking longship.png");
    private ImageIcon twoTile = new ImageIcon("src/images/2-tile Caravela .png");
    private ImageIcon threeTile = new ImageIcon("src/images/3-tile frigette.png");

    private DynamicLabelArray shipLabels = new DynamicLabelArray(0);
    private DynamicLabelArray staticShipLabels = new DynamicLabelArray(3);
    
    //for adding ships to the player grid
    private int metal = 5;
    private Player player = new Player();
    private boolean buildPhase = true;
    private int nomer = 0;
    private int length = 1;
    
    public BuildMenu(Player player) {
        this.player = player;
        this.setBounds(100, 100, 1280, 720);
        //inicializirane na vsichko statichno

        // <editor-fold defaultstate="collapsed" desc="1-tile ship">
        staticShipLabels.elementGetter(0).setIcon(oneTile);
        staticShipLabels.elementGetter(0).setBounds(800, 50, 48, 40);
        staticShipLabels.elementGetter(0).addMouseListener(new MouseAdapterForShipSpawning());
// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="2-tile ship">
        staticShipLabels.elementGetter(1).setIcon(twoTile);
        staticShipLabels.elementGetter(1).setBounds(800, 200, 80, 48);
        staticShipLabels.elementGetter(1).addMouseListener(new MouseAdapterForShipSpawning());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="3-tile ship">
        staticShipLabels.elementGetter(2).setIcon(threeTile);
        staticShipLabels.elementGetter(2).setBounds(800, 400, 140, 48);
        staticShipLabels.elementGetter(2).addMouseListener(new MouseAdapterForShipSpawning());
// </editor-fold>

        layeredPane.setBounds(0, 0, 1280, 720);

        // <editor-fold defaultstate="collapsed" desc="background">
        ImageIcon backgroundIcon = new ImageIcon("src/images/BuildArea2.png");
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

    @SuppressWarnings("unchecked")

    private void setCursorToImage(Image image) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        this.setCursor(c);
    }

    public BufferedImage rotate(BufferedImage bimg, Double angle) {
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
    
    public BufferedImage convertToBufferedImage(Image image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    class MKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {

            char ch = event.getKeyChar();

            if ((ch == 'r' || ch == 'R') && canRotate) {

                auxiliaryImage = rotate(convertToBufferedImage(image), 90.0);
                image = (java.awt.Image) auxiliaryImage;
                setCursorToImage(image);
                labelGhostIcon = new ImageIcon(image);

                if (orientation < 3) {
                    //orientation 0 e east, 1 south  , 2 west, 3 north primerno
                    orientation++;
                } else {
                    orientation = 0;
                }
                System.out.println(orientation);
            }
            if (ch == 'z' || ch == 'Z') {
                shipLabels.removeLabel(shipLabels.length() - 1);
                layeredPane.revalidate();

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
                    length = i+1;
                }
            }

            image = labelGhostIcon.getImage();
            setCursorToImage(image);
            canRotate = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
            //if over grid
            shipLabels.addLabel();
            shipLabels.spawnShip(shipLabels.length() - 1, e.getX() + e.getComponent().getX(), e.getY() + e.getComponent().getY());
            layeredPane.revalidate();
            canRotate = false;
            if (buildPhase) {
                if (e.getX() + e.getComponent().getX() < 720 && e.getX() + e.getComponent().getX() > 0
                        && e.getY() + e.getComponent().getY() < 720 && e.getY() + e.getComponent().getY() > 0) {
                    int posX = getGridCell(e.getX() + e.getComponent().getX());
                    int posY = getGridCell(e.getY() + e.getComponent().getY());
                    int length = 1;//tova da se promenia za korabite
                    ImageIcon shipImage = labelGhostIcon;
                    Ship ship = new Ship(length, orientation, shipImage, false, posX, posY);
                    if (placeCheck(player, ship)) {
                        buildShip(player, ship);
                        System.out.println(player.getShip(nomer - 1).getPositionX() + ";" + player.getShip(nomer - 1).getPositionY());
                    }
                }
            }
            orientation = 0;
        }
    }

    private boolean placeCheck(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        //int length = ship.getShipLength();
        boolean isGridFree = true;
        //0 - iztok
        //1 - yug
        //2 - zapad
        //3 - sever
        if (shipOrientation % 2 == 0) {
            if (posX + length - 1 < 12 && posX >= 0 && posY >= 0 && posY < 12) {
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX + i, posY) != 0) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            if (posY + length - 1 < 12 && posY >= 0 && posX >= 0 && posX < 12) {
                for (int i = 0; i < length; i++) {
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

    private void placeShipInGrid(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        if (shipOrientation % 2 == 0) {
            for (int i = 0; i < length; i++) {
                player.setGrid(3, posX + i, posY);
            }
        } else {
            for (int i = 0; i < length; i++) {
                player.setGrid(3, posX, posY + i);
            }
        }
    }

    private void buildShip(Player player, Ship ship) {
        if (metal - ship.getShipLength() >= 0) {
            if (placeCheck(player, ship)) {
                metal -= ship.getShipLength();
                player.setShip(ship, nomer);
                nomer++;
                placeShipInGrid(player, ship);
                if (metal <= 0) {
                    buildPhase = false;
                }
            }
        }
    }

    private int getGridCell(int pos) {
        int cell;
        cell = pos / 60;
        return cell;
    }

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
            shipLabels[numberInArray].setOpaque(true);
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

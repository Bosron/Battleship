/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package anothertest;

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
public class NewJframe extends javax.swing.JFrame implements MouseListener {

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
    private JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\Damyan\\Desktop\\Ships\\1-tile viking longship.png"));
    private JLabel label2 = new JLabel(new ImageIcon("C:\\Users\\Damyan\\Desktop\\Ships\\2-tile Caravela .png"));
    private JLabel label3 = new JLabel(new ImageIcon("C:\\Users\\Damyan\\Desktop\\Ships\\3-tile frigette.png"));

    /**
     * Creates new form NewJFrame
     */
    public NewJframe() {

        this.setBounds(100, 100, 1280, 720);
        //inicializirane na vsichko statichno
        // <editor-fold defaultstate="collapsed" desc="label1">
        label1.setBounds(800, 50, 200, 200);
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                labelGhostIcon = (ImageIcon) label1.getIcon();
                image = labelGhostIcon.getImage();
                setCursorToImage(image);
                canRotate = true;
                width = 48;
                height = 40;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
                //if over grid
                jLabel1.setIcon((Icon) labelGhostIcon);
                jLabel1.setBounds(e.getX() + e.getComponent().getX(), e.getY() + e.getComponent().getY(), width, height);
                jLabel1.setOpaque(true);
                //jLabel1.addMouseListener(new MouseEventForDeletion());
                layeredPane.add(jLabel1, Integer.valueOf(2));
                layeredPane.revalidate();
                canRotate = false;
                /*
                setShipLength(1);
                setShipOrientation(orientation);
                 */
                //
                orientation = 0;
            }
        });
// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="label2">
        label2.setBounds(800, 200, 200, 200);
        label2.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                labelGhostIcon = (ImageIcon) label2.getIcon();
                image = labelGhostIcon.getImage();
                setCursorToImage(image);
                canRotate = true;
                width = 80;
                height = 48;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
                //if over grid
                jLabel1.setIcon((Icon) labelGhostIcon);
                jLabel1.setText(" 1 tile");
                jLabel1.setBounds(e.getX() + e.getComponent().getX(), e.getY() + e.getComponent().getY(), width, height);
                jLabel1.setOpaque(true);
                layeredPane.add(jLabel1, Integer.valueOf(2));
                layeredPane.revalidate();
                canRotate = false;
                /*
                setShipLength(1);
                setShipOrientation(orientation);
                 */
                //
                 orientation = 0;
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="label3">
        label3.setBounds(800, 400, 200, 200);
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                labelGhostIcon = (ImageIcon) label3.getIcon();
                image = labelGhostIcon.getImage();
                setCursorToImage(image);
                canRotate = true;
                width = 140;
                height = 48;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
                //if over grid
                jLabel1.setIcon((Icon) labelGhostIcon);
                jLabel1.setText(" 1 tile");
                jLabel1.setBounds(e.getX() + e.getComponent().getX(), e.getY() + e.getComponent().getY(), width, height);
                jLabel1.setOpaque(true);
                layeredPane.add(jLabel1, Integer.valueOf(2));
                layeredPane.revalidate();
                canRotate = false;
                /*
                setShipLength(1);
                setShipOrientation(orientation);
                 */
                //
                orientation = 0;
            }

        });
        // </editor-fold>

        layeredPane.setBounds(0, 0, 1280, 720);

        // <editor-fold defaultstate="collapsed" desc="background">
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\Damyan\\Desktop\\Ships\\BuildArea2.png");
        Image backgroundImage = backgroundIcon.getImage();
        Image modifiedBackgroundImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(modifiedBackgroundImage);
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

        this.setLayeredPane(layeredPane);
        this.revalidate();
        this.addKeyListener(new MKeyListener());

        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(label1, Integer.valueOf(3));
        layeredPane.add(label2, Integer.valueOf(2));
        layeredPane.add(label3, Integer.valueOf(1));

        initComponents();
        this.setBounds(100, 100, 1280, 720);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 363, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 284, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJframe().setVisible(true);
            }
        });
    }

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

            if (ch == 'r' && canRotate) {

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

        }
    }
    
    class MouseEventForDeletion extends MouseAdapter
    {
         @Override
         public void mousePressed(MouseEvent e) {
                jLabel1.setVisible(false);
            }
    }

}

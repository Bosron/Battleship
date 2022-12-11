package battleship;

import java.awt.Image;
import javax.swing.ImageIcon;

public class BuildMenu extends javax.swing.JFrame {

    public BuildMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1304, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void run(Player player){
        //tova e tuk samo zashtoto player ne moje da e parametur na BuildMenu
        this.setVisible(true);
    }
    private int orientation = 0, metal = 5;
    
    private void KeyPressed(some_key_event e) {
        if (e.getChar() == 'r' || e.getChar() == 'R') {
            orientation++;
            if (orientation >= 4) {
                orientation = 0;
            }
            switchCursorImage(orientation);
        }
    }

    private void MouseReleased(some_mouse_event e) {
        int length = e.getShipLength();
        Image / ImageIcon ? shipImage = e.getShipImage();
        int posX = getGridCell(e.getX);
        int posY = getGridCell(e.getY);
        Ship ship = new Ship(length, orientation, "shipImage", false, posX, posY);
        if (placeCheck(player, ship)) {
            buildShip(player, ship);
        }
    }

    private boolean placeCheck(Player player, Ship ship) {//tova triabva da se obedini s rotateCheck v 1 method
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        return (player.getGrid(posX, posY) == 0);
    }

    private boolean rotateCheck(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        boolean isGridFree = false;

        switch (shipOrientation) {
            case 0:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX + i, posY) == 0) {
                        isGridFree = true;
                    }
                }
            case 1:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX - i, posY) == 0) {
                        isGridFree = true;
                    }
                }
            case 2:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX, posY - i) == 0) {
                        isGridFree = true;
                    }
                }
            case 3:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX, posY + i) == 0) {
                        isGridFree = true;
                    }
                }
        }
        return isGridFree;
    }

    private void placeShipInGrid(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        switch (shipOrientation) {
            case 0:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX + i, posY);
                }
            case 1:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX - i, posY);
                }
            case 2:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX, posY - i);
                }
            case 3:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX, posY + i);
                }
        }
    }

    public void buildShip(Player player, Ship ship) {
        int nomer = 0;
        if (metal > 0) {
            if (new BuildMenu().placeCheck(player, ship) && new BuildMenu().rotateCheck(player, ship)) {
                metal -= ship.getShipLength();
                player.setShip(ship, nomer);
                nomer++;
                new BuildMenu().placeShipInGrid(player, ship);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

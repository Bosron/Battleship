package battleship;

import java.awt.Image;
import javax.swing.ImageIcon;

public class BuildMenu extends javax.swing.JFrame {

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
    public BuildMenu(Player player) {
        initComponents();
        this.player = player;
    }
    private int orientation = 0, metal = 5;
    private Player player = new Player();
    private boolean buildPhase = true;

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
        if (buildPhase) {
            if (e.getX < 720 && e.getX > 0 && e.getY < 720 && e.getY > 0) {
                int posX = getGridCell(e.getX);
                int posY = getGridCell(e.getY);
                int length = e.getShipLength();
                ImageIcon shipImage = e.getShipImage();
                Ship ship = new Ship(length, orientation, shipImage, false, posX, posY);
                if (placeCheck(player, ship)) {
                    buildShip(player, ship);
                }
            }
        }
    }

    private boolean placeCheck(Player player, Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        boolean isGridFree = true;
        
        //0 - iztok
        //1 - zapad
        //2 - sever
        //4 - yug

        switch (shipOrientation) {
            case 0:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX + i, posY) != 0) {
                        isGridFree = false;
                    }
                }
            case 1:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX + i, posY) != 0) {
                        isGridFree = false;
                    }
                }
            case 2:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX, posY + i) != 0) {
                        isGridFree = false;
                    }
                }
            case 3:
                for (int i = 0; i < length; i++) {
                    if (player.getGrid(posX, posY + i) != 0) {
                        isGridFree = false;
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
                    player.setGrid(3, posX + i, posY);
                }
            case 2:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX, posY + i);
                }
            case 3:
                for (int i = 0; i < length; i++) {
                    player.setGrid(3, posX, posY + i);
                }
        }
    }

    private void buildShip(Player player, Ship ship) {
        int nomer = 0;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

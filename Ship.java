package battleship;

public class Ship {

    private int shipLength;
    private int shipOrientation;
    private String shipImage;
    private boolean isSunk;
    private int positionX, positionY;


    public int getShipLength() {
        return shipLength;
    }

    public int getShipOrientation() {
        return shipOrientation;
    }

    public String getShipImage() {
        return shipImage;
    }

    public boolean isIsSunk() {
        return isSunk;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public void setShipOrientation(int shipOrientation) {
        this.shipOrientation = shipOrientation;
    }

    public void setShipImage(String shipImage) {
        this.shipImage = shipImage;
    }

    public void setIsSunk(boolean isSunk) {
        this.isSunk = isSunk;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Ship(int shipLength, int shipOrientation, String shipImage, boolean isSunk, int positionX, int positionY) {
        this.shipLength = shipLength;
        this.shipOrientation = shipOrientation;
        this.shipImage = shipImage;
        this.isSunk = isSunk;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Ship() {
        this(0, 0, "", true, 0, 0);
    }

    @Override
    public String toString() {
        return "Ship{" + "shipLength=" + shipLength + ", shipOrientation=" + shipOrientation + ", shipImage=" + shipImage + ", isSunk=" + isSunk + ", positionX=" + positionX + ", positionY=" + positionY + '}';
    }
}
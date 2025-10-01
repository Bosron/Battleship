package battleship;

public class Player {

    private int[][] grid = new int[12][12];
    private String name;
    private int biggestShip;
    private Ship[] ships = new Ship[20];//size = metal
    private int shipStyle;
    private String currentAdmiralFileName;

    public void setName(String name) {
        this.name = name;
    }

    public void setShip(Ship ship, int index) {
        this.ships[index] = ship;
    }

    public void setGrid(int value, int index1, int index2) {
        this.grid[index1][index2] = value;
    }

    public void setBiggestShip(int biggestShip) {
        this.biggestShip = biggestShip;
    }

    public void setShipStyle(int shipStyle) {
        this.shipStyle = shipStyle;
    }

    public void setCurrentAdmiralFileName(String currentAdmiralFileName){
        this.currentAdmiralFileName = currentAdmiralFileName;
    }

    public String getCurrentAdmiralFileName(){
        return currentAdmiralFileName;
    }

    public String getName() {
        return name;
    }

    public Ship getShip(int index) {
        return ships[index];
    }

    public int getGrid(int index1, int index2) {
        return grid[index1][index2];
    }

    public int getBiggestShip() {
        this.findBiggestShip();
        return biggestShip;
    }

    public int getShipStyle() {
        return shipStyle;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this("");
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void findBiggestShip() {
        int biggestShipTemp = 0;
        for (int i = 0; i < 20; i++) {
            if(this.getShip(i).getShipLength() > biggestShipTemp && !(this.getShip(i).getIsSunk())){
                biggestShipTemp = this.getShip(i).getShipLength();
            }
        }
        this.setBiggestShip(biggestShipTemp);
    }
    
    public void placeShipInGrid(Ship ship) {
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        if (shipOrientation % 2 == 0) {
            for (int i = 0; i < length; i++) {
                this.setGrid(3, posX + i, posY);
            }
        } else {
            for (int i = 0; i < length; i++) {
                this.setGrid(3, posX, posY + i);
            }
        }
    }
    
    public void clearShipInGrid(Ship ship){
        int posX = ship.getPositionX();
        int posY = ship.getPositionY();
        int shipOrientation = ship.getShipOrientation();
        int length = ship.getShipLength();
        if (shipOrientation % 2 == 0) {
            for (int i = 0; i < length; i++) {
                this.setGrid(0, posX + i, posY);
            }
        } else {
            for (int i = 0; i < length; i++) {
                this.setGrid(0, posX, posY + i);
            }
        }
    }

    public Player (Player player){
        this(player.getName());
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", biggestShip=" + biggestShip + ", shipStyle=" + shipStyle + '}';
    }
}
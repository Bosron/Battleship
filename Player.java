package battleship;

public class Player {

    private int[][] grid = new int[12][12];
    private int[][] veiledGrid = new int[12][12];
    private String name;
    private int biggestShip;
    private Ship[] ships = new Ship[5];

    public void setName(String name) {
        this.name = name;
    }

    public void setShip(Ship ship, int index) {
        this.ships[index] = ship;
    }

    public void setGrid(int value, int index1, int index2) {
        this.grid[index1][index2] = value;
    }

    public void setVeiledGrid(int value, int index1, int index2) {
        this.veiledGrid[index1][index2] = value;
    }

    public void setBiggestShip(int biggestShip) {
        this.biggestShip = biggestShip;
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

    public int getVeiledGrid(int index1, int index2) {
        return veiledGrid[index1][index2];
    }

    public int getBiggestShip() {
        return biggestShip;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(Ship[] ships) {
        this.ships = ships;
    }

    public Player() {
        this("");
        for (int i = 0; i < 5; i++) {
            ships[i] = new Ship();
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                grid[i][j] = 0;
                veiledGrid[i][j] = 0;
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

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", biggestShip=" + biggestShip + '}';
    }
}

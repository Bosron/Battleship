package battleship;

public class Player {

    String[][] grid = new String[10][10];
    String[][] veiledGrid = new String[10][10];
    String name;
    int bigShip;

    public void setName(String name) {
        this.name = name;
    }

    public void setBigShip(int bigShip) {
        this.bigShip = bigShip;
    }

    public String getName() {
        return name;
    }

    public int getBigShip() {
        return bigShip;
    }

    public Player(String name, int bigShip) {
        this.name = name;
        this.bigShip = bigShip;
    }

    public Player() {
        this("", 0);
    }

    public Player(Player p){
        this(p.getName(), p.getBigShip());
    }
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", bigShip=" + bigShip + '}';
    }
}
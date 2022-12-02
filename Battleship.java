package battleship;

import java.util.Scanner;

public class Battleship {

    Boolean loseCon = false;

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();
        int runda = 1;
        System.out.println("Player 1, what is your name?");
        player1.name = input.next();
        System.out.println("Player 2, what is your name?");
        player2.name = input.next();
        building(player1);
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        building(player2);
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        for (int i = 0; i < 10; i++) {

            if (strike(player1, player2) == true) {
                System.out.printf("Pobeda za %s v %d runda \n", player1.name, runda);
                break;
            }
            for (int j = 0; j < 50; j++) {
                System.out.println("");
            }
            System.out.println("Pogledni nastrani.");
            Thread.sleep(5000);
            if (strike(player2, player1) == true) {
                System.out.printf("Pobeda za %s v %d runda \n", player2.name, runda);
                break;
            }
            for (int j = 0; j < 50; j++) {
                System.out.println("");
            }
            System.out.println("Pogledni nastrani.");
            Thread.sleep(5000);
            runda++;
        }
    }
    public static void building(Player player) {
        Scanner input = new Scanner(System.in);
        int posX, posY, metal = 5, price, izborInt;
        boolean izborBool;
        String[] shipType = {"Frigate", "Destroyer", "Cruiser", "Battleship", "Aircraft carrier"};
        String[] orient = {"East", "West", "North", "South"};
        boolean[] canRotate = {true, true, true, true};

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player.grid[i][j] = "\u301C";
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player.veiledGrid[i][j] = "\u301C";
            }
        }
        //izbirane na vid korab
        for (int i = 0, nomer = 1; metal != 0; i++, nomer++) {
            System.out.printf("Vavedete kade da e koraba vi - vsiako miasto koeto"
                    + " zaema toi shte izpolzva edin metal\nOstavashtia vi metal e %d\n", metal);
            System.out.printf("Kakav korab iskate?\n1. %s\n2. %s\n3. %s\n4. %s\n5. %s\n", shipType[0], shipType[1], shipType[2], shipType[3], shipType[4]);
            price = input.nextInt();
            while (price > metal) {
                price = input.nextInt();
            }
            if (player.bigShip < price) {
                player.bigShip = price;
            }
            //proverka kude e prazno
            System.out.printf("Izberete koordinati v grid 10 na 10 na vashia %s\n", shipType[price - 1]);
            posX = input.nextInt() - 1;
            posY = input.nextInt() - 1;
            while (posX > 9 || posY > 9 || posX < 0 || posY < 0 || player.grid[posX][posY] != "\u301C") {
                posX = input.nextInt() - 1;
                posY = input.nextInt() - 1;
            }
            for (int j = 0; j < price; j++) {
                if (posX + j >= 10) {
                    canRotate[0] = false;
                    orient[0] = "Occupied.";
                } else if (player.grid[posX + j][posY] != "\u301C") {
                    canRotate[0] = false;
                    orient[0] = "Occupied.";
                }
                if (posX - j < 0) {
                    canRotate[1] = false;
                    orient[1] = "Occupied.";
                } else if (player.grid[posX - j][posY] != "\u301C") {
                    canRotate[1] = false;
                    orient[1] = "Occupied.";
                }
                if (posY - j < 0) {
                    canRotate[2] = false;
                    orient[2] = "Occupied.";
                } else if (player.grid[posX][posY - j] != "\u301C") {
                    canRotate[2] = false;
                    orient[2] = "Occupied.";
                }
                if (posY + j >= 10) {
                    canRotate[3] = false;
                    orient[3] = "Occupied.";
                } else if (player.grid[posX][posY + j] != "\u301C") {
                    canRotate[3] = false;
                    orient[3] = "Occupied.";
                }
            }
            //izbor na posoka
            System.out.printf("Nakade iskate da gleda vashia %s?\n", shipType[price - 1]);
            System.out.printf(" 1. %s \n 2. %s \n 3. %s \n 4. %s \n", orient[0], orient[1], orient[2], orient[3]);
            izborInt = input.nextInt();
            izborBool = canRotate[izborInt - 1];
            while (izborBool == false) {
                izborInt = input.nextInt();
                izborBool = canRotate[izborInt - 1];
            }
            //stroene na korab
            for (int j = 0; j < price; j++) {
                if (izborInt == 1) {
                    if (j == price - 1) {
                        player.grid[posX + j][posY] = "\u25b6";
                    } else {
                        player.grid[posX + j][posY] = "\u25a0";
                    }

                } else if (izborInt == 2) {
                    if (j == price - 1) {
                        player.grid[posX - j][posY] = "\u25c0";
                    } else {
                        player.grid[posX - j][posY] = "\u25a0";
                    }
                } else if (izborInt == 3) {
                    if (j == price - 1) {
                        player.grid[posX][posY - j] = "\u25b2";
                    } else {
                        player.grid[posX][posY - j] = "\u25a0";
                    }
                } else if (izborInt == 4) {
                    if (j == price - 1) {
                        player.grid[posX][posY + j] = "\u25bc";
                    } else {
                        player.grid[posX][posY + j] = "\u25a0";
                    }
                }
            }
            //printirame boynoto pole
            System.out.println("'\'****************************************************************/");
            for (int h = 0; h < 11; h++) {
                System.out.printf("%d\t", h);
            }
            System.out.println("");
            for (int z = 0; z < 10; z++) {
                System.out.printf("%d\t", z + 1);
                for (int l = 0; l < 10; l++) {
                    System.out.printf("%s\t", player.grid[l][z]);
                }
                System.out.println("");
            }
            System.out.println("'\'****************************************************************/");
            metal = metal - price;
            //nulrane na masivite
            orient[0] = "East";
            orient[1] = "West";
            orient[2] = "North";
            orient[3] = "South";
            canRotate[0] = true;
            canRotate[1] = true;
            canRotate[2] = true;
            canRotate[3] = true;
        }
    }

    public static Boolean strike(Player player1, Player player2) {
        Scanner input = new Scanner(System.in);
        String symbol = "\u301C";
        int strikeX, strikeY, remains = 0;
        Boolean loseCon = false;
        System.out.printf("Tove e duskata na %s\n", player1.name);
        for (int h = 0; h < 11; h++) {
            System.out.printf("%d\t", h);
        }
        System.out.println("");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%d\t", j + 1);
            for (int u = 0; u < 10; u++) {
                System.out.printf("%s\t", player1.grid[u][j]);
            }
            System.out.println("");
        }
        System.out.printf("Tova e zamuglente duska na %s\n", player2.name);
        for (int h = 0; h < 11; h++) {
            System.out.printf("%d\t", h);
        }
        System.out.println("");
        for (int j = 0; j < 10; j++) {
            System.out.printf("%d\t", j + 1);
            for (int u = 0; u < 10; u++) {
                System.out.printf("%s\t", player2.veiledGrid[u][j]);
            }
            System.out.println("");
        }
        //Player1 strelia player2 biva potopen
        System.out.printf("Strelia %s \n", player1.name);
        for (int i = 0; i < player1.bigShip; i++) {
            //izbirane na cel
            System.out.printf("Molia vavedete koordinatite na air strika: \n");
            strikeX = input.nextInt() - 1;
            strikeY = input.nextInt() - 1;
            while (strikeX > 9 || strikeY > 9 || strikeX < 0 || strikeY < 0) {
                strikeX = input.nextInt() - 1;
                strikeY = input.nextInt() - 1;
            }
            //potopyavane
            if (player2.grid[strikeX][strikeY] == "\u25b6") {
                player2.grid[strikeX][strikeY] = "\u25b7";
                player2.veiledGrid[strikeX][strikeY] = "\u25b7";
            }
            if (player2.grid[strikeX][strikeY] == "\u25bc") {
                player2.grid[strikeX][strikeY] = "\u25bd";
                player2.veiledGrid[strikeX][strikeY] = "\u25bd";
            }
            if (player2.grid[strikeX][strikeY] == "\u25B2") {
                player2.grid[strikeX][strikeY] = "\u25B3";
                player2.veiledGrid[strikeX][strikeY] = "\u25B3";
            }
            if (player2.grid[strikeX][strikeY] == "\u25C0") {
                player2.grid[strikeX][strikeY] = "\u25C1";
                player2.veiledGrid[strikeX][strikeY] = "\u25C1";
            }
            if (player2.grid[strikeX][strikeY] == "\u25A0") {
                player2.grid[strikeX][strikeY] = "\u25A1";
                player2.veiledGrid[strikeX][strikeY] = "\u25A1";
            }
            if (player2.grid[strikeX][strikeY] == symbol) {
                player2.grid[strikeX][strikeY] = "\u2716";
                player2.veiledGrid[strikeX][strikeY] = "\u2716";
            }
            //printirane na boynoto pole
            System.out.printf("Tove e zamaglente duska na %s\n", player2.name);
            for (int h = 0; h < 11; h++) {
                System.out.printf("%d\t", h);
            }
            System.out.println("");
            for (int j = 0; j < 10; j++) {
                System.out.printf("%d\t", j + 1);
                for (int u = 0; u < 10; u++) {
                    if (player2.grid[u][j] == symbol || player2.grid[u][j] == "\u25b7" || player2.grid[u][j] == "\u25bd" || player2.grid[u][j] == "\u25B3" || player2.grid[u][j] == "\u25C1" || player2.grid[u][j] == "\u25A1" || player2.grid[u][j] == "\u2716") {
                        remains++;
                    }
                    System.out.printf("%s\t", player2.veiledGrid[u][j]);
                }
                System.out.println("");
            }
            if (remains == 100) {
                loseCon = true;
                break;
            } else {
                remains = 0;
            }
        }
        return loseCon;
    }
}
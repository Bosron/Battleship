package battleship;

import java.util.Scanner;

public class Battleship_Alpha {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String symbol = "\u301C", tester = "\u25C1";
        int n = 10, strikeX, strikeY, metal = 5, demand, runda = 0;
        Boolean loseCon = false;
        String[][] grid = new String[n][n];
        int posX, posY;
        String[] ship = {"Frigate", "Destroyer", "Cruiser", "Battleship", "Aircraft carrier"};
        String[] orient = {"East", "West", "North", "South"};
        boolean[] canRotate = {true, true, true, true};
        boolean izborBool;
        int izborInt;
        //nulirane na poleto
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = symbol;
            }
        }
        //izbirane na vid korab
        for (int i = 0, nomer = 1; metal != 0; i++, nomer++) {
            System.out.printf("Vavedete kade da e koraba vi - vsiako miasto koeto"
                    + " zaema toi shte izpolzva edin metal\nOstavashtia vi metal e %d\n", metal);
            System.out.printf("Kakav korab iskate?\n1. %s\n2. %s\n3. %s\n4. %s\n5. %s\n", ship[0], ship[1], ship[2], ship[3], ship[4]);
            demand = input.nextInt();
            while (demand > metal) {
                demand = input.nextInt();
            }
            //proverka kude e prazno
            System.out.printf("Izberete poziciata na vashia %s\n", ship[demand - 1]);
            posX = input.nextInt() - 1;
            posY = input.nextInt() - 1;
            while (posX > n - 1 || posY > n - 1 || posX < 0 || posY < 0 || grid[posX][posY] != symbol) {
                posX = input.nextInt() - 1;
                posY = input.nextInt() - 1;
            }
            for (int j = 0; j < demand; j++) {
                if (posX + j >= n) {
                    canRotate[0] = false;
                    orient[0] = "Occupied.";
                } else if (grid[posX + j][posY] != symbol) {
                    canRotate[0] = false;
                    orient[0] = "Occupied.";
                }
                if (posX - j < 0) {
                    canRotate[1] = false;
                    orient[1] = "Occupied.";
                } else if (grid[posX - j][posY] != symbol) {
                    canRotate[1] = false;
                    orient[1] = "Occupied.";
                }
                if (posY - j < 0) {
                    canRotate[2] = false;
                    orient[2] = "Occupied.";
                } else if (grid[posX][posY - j] != symbol) {
                    canRotate[2] = false;
                    orient[2] = "Occupied.";
                }
                if (posY + j >= n) {
                    canRotate[3] = false;
                    orient[3] = "Occupied.";
                } else if (grid[posX][posY + j] != symbol) {
                    canRotate[3] = false;
                    orient[3] = "Occupied.";
                }
            }
            //izbor na posoka
            System.out.printf("Nakade iskate da gleda vashia %s?\n", ship[demand - 1]);
            System.out.printf(" 1. %s \n 2. %s \n 3. %s \n 4. %s \n", orient[0], orient[1], orient[2], orient[3]);
            izborInt = input.nextInt();
            izborBool = canRotate[izborInt - 1];
            while (izborBool == false) {
                izborInt = input.nextInt();
                izborBool = canRotate[izborInt - 1];
            }
            //stroene na korab
            for (int j = 0; j < demand; j++) {
                if (izborInt == 1) {
                	if(j == demand - 1) {
                		grid[posX + j][posY] = "\u25b6";
                	} else {
                		grid[posX + j][posY] = "\u25a0";
                	}
                    
                } else if (izborInt == 2) {
                	if(j == demand - 1) {
                		grid[posX - j][posY] = "\u25c0";
                	} else {
                		grid[posX - j][posY] = "\u25a0";
                	}
                } else if (izborInt == 3) {
                	if(j == demand - 1) {
                		grid[posX][posY - j] = "\u25b2";
                	} else {
                		grid[posX][posY - j] = "\u25a0";
                	}
                } else if (izborInt == 4) {
                	if(j == demand - 1) {
                		grid[posX][posY + j] = "\u25bc";
                	} else {
                		grid[posX][posY + j] = "\u25a0";
                	}
                }
            }
            //reset na masivite
            orient[0] = "East";
            orient[1] = "West";
            orient[2] = "North";
            orient[3] = "South";
            canRotate[0] = true;
            canRotate[1] = true;
            canRotate[2] = true;
            canRotate[3] = true;
            //printirame boynoto pole
            System.out.println("'\'****************************************************************/");
            for(int h = 0; h < n + 1; h++) {
                System.out.printf("%d\t", h);
            }
            System.out.println("");
            for (int z = 0; z < n; z++) {
            	System.out.printf("%d\t", z + 1);
                for (int l = 0; l < n; l++) {
                    System.out.printf("%s\t", grid[l][z]);
                }
                System.out.println("");
            }
            System.out.println("'\'****************************************************************/");
            metal = metal - demand;
        }
        //tuk se strelya
        for (int y = 0, remains = 0; loseCon != true; y++, runda++) {
        	//izbirane na cel
            System.out.printf("Molia vavedete koordinatite na air strika: \n");
            strikeX = input.nextInt() - 1;
            strikeY = input.nextInt() - 1;
            while(strikeX > n - 1 || strikeY > n - 1 || strikeX < 0 || strikeY < 0) {
            	strikeX = input.nextInt() - 1;
                strikeY = input.nextInt() - 1;
            }
            //potopyavane
            if(grid[strikeX][strikeY] == "\u25b6") {
            	grid[strikeX][strikeY] = "\u25b7";
          	}
        	if(grid[strikeX][strikeY] == "\u25bc") {
             	grid[strikeX][strikeY] = "\u25bd";
           	}
            if(grid[strikeX][strikeY] == "\u25B2") {
            	grid[strikeX][strikeY] = "\u25B3";
          	}
        	if(grid[strikeX][strikeY] == "\u25C0") {
             	grid[strikeX][strikeY] = "\u25C1";
           	}
         	if(grid[strikeX][strikeY] == "\u25A0") {
              	grid[strikeX][strikeY] = "\u25A1";
          	}
          	if(grid[strikeX][strikeY] == symbol) {
              	grid[strikeX][strikeY] = "\u2716";
          	}
          	//printirane na boynoto pole
          	for(int h = 0; h < n + 1; h++) {
                System.out.printf("%d\t", h);
            }
            System.out.println("");
          	for (int j = 0; j < n; j++) {
          		System.out.printf("%d\t", j + 1);
                for (int i = 0; i < n; i++) {
                    if (grid[i][j] == symbol || grid[i][j] == "\u25b7" || grid[i][j] == "\u25bd" || grid[i][j] == "\u25B3" || grid[i][j] == "\u25C1" || grid[i][j] == "\u25A1" || grid[i][j] == "\u2716") {
                        remains++;
                    }
                    System.out.printf("%s\t", grid[i][j]);
                }
                System.out.println("");
            }
          	if (remains == n * n) {
                loseCon = true;
                break;
            } else {
                remains = 0;
            }
        }
        //Win!
        System.out.printf("Pobeda v %d runda \n", runda);
    }
}

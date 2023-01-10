package battleship;

 

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

 

//@authors: borisks & damyanlh
public class EndScreen extends javax.swing.JFrame {

 

    //scene
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel background = new JLabel();
    private JLabel backdrop = new JLabel();
    private ImageIcon backDropIcon = new ImageIcon("src/images/backdrop.png");
    private ImageIcon backgroundIcon = new ImageIcon("src/images/EndScreen.png");

 

    //players
    private Player winner = new Player();
    private Player loser = new Player();

 

    //labels
    private JLabel winnerName = new JLabel();
    private JLabel stats1 = new JLabel();
    private JLabel stats2 = new JLabel();
    private JLabel credits = new JLabel();
    private JLabel rematch = new JLabel();
    private JTextArea scoreboard = new JTextArea();

 

    private String scoreboardText = "";

 

    public EndScreen(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;

 

        this.setBounds(100, 10, 900, 757);
        layeredPane.setBounds(0, 0, 900, 720);

 

        //inicializirane
        // <editor-fold defaultstate="collapsed" desc="winnerName">
        winnerName.setBounds(250, 100, 500, 50);
        winnerName.setOpaque(false);
        winnerName.setFont(new Font("Fira Sans", Font.BOLD, 40));
        winnerName.setText(winner.getName() + " won!");
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="stats1">
        stats1.setBounds(300, 250, 400, 50);
        stats1.setOpaque(false);
        stats1.setFont(new Font("Fira Sans", Font.BOLD, 20));
        stats1.setText(winner.getName() + "'s accuracy: " + findAccuracy(loser));
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="stats2">
        stats2.setBounds(300, 350, 400, 50);
        stats2.setOpaque(false);
        stats2.setFont(new Font("Fira Sans", Font.BOLD, 20));
        stats2.setText(loser.getName() + "'s accuracy: " + findAccuracy(winner));
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="credits">
        credits.setBounds(300, 500, 100, 50);
        credits.setOpaque(false);
        credits.setFont(new Font("Fira Sans", Font.BOLD, 20));
        credits.setText("credits");
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="rematch">
        rematch.setBounds(300, 600, 100, 50);
        rematch.setOpaque(false);
        rematch.setIcon(new ImageIcon("src/images/colorButton.png"));
        rematch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startRematch();
            }
        });
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="background">
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="backdrop">
        background.setIcon(backDropIcon);
        background.setBounds(0, 0, 1980, 1080);
        // </editor-fold>

 

        // <editor-fold defaultstate="collapsed" desc="scoreboard">
        scoreboard.setBounds(100, 100, 300, 300);
        scoreboard.setEditable(false);
        scoreboard.setColumns(10);
        scoreboard.setOpaque(false);
        scoreboard.setFont(new Font("Fira Sans", Font.BOLD, 15));
        try {
            scoreboardDisplayer();
        } catch (FileNotFoundException ex) {
            System.out.println("exception");
            Logger.getLogger(EndScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        // </editor-fold>
        this.setLayeredPane(layeredPane);
        this.revalidate();

 

        layeredPane.add(scoreboard, Integer.valueOf(2));
        layeredPane.add(winnerName, Integer.valueOf(2));
        layeredPane.add(stats1, Integer.valueOf(2));
        layeredPane.add(stats2, Integer.valueOf(2));
        layeredPane.add(credits, Integer.valueOf(2));
        layeredPane.add(rematch, Integer.valueOf(2));
        layeredPane.add(background, Integer.valueOf(1));
        layeredPane.add(backdrop, Integer.valueOf(0));
        layeredPane.revalidate();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

 

    private void startRematch() {
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName(loser.getName());
        p2.setName(winner.getName());
        p1.setShipStyle(loser.getShipStyle());
        p2.setShipStyle(winner.getShipStyle());
        MainMenu.setP1(p1);
        MainMenu.setP2(p2);
        MainMenu.setCurrentPhase(1);
        this.dispose();
        new MainMenu().run();
    }

 

    private float findAccuracy(Player player) {
        float redNum = 0;
        float whiteNum = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (player.getGrid(i, j) == 1) {
                    whiteNum++;
                } else if (player.getGrid(i, j) == 2) {
                    redNum++;
                }
            }
        }
        return redNum / (whiteNum + redNum);
    }

 

    private void scoreboardDisplayer() throws FileNotFoundException {
        //scoreboard
        //prihvashtane na faila za scorevoard-a
        try {
            
            File file = new File("src/images/scoreboard.txt");
            boolean isCreated = file.createNewFile();
            //pisane na poslednia pobeditel v scoreboarda
            FileWriter fileWriter = new FileWriter(file.getPath(), true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            if (isCreated) { 
                System.out.println("New scoreboard was created");
                
            } else {
                out.write(winner.getName() + ":" + "5 rounds"+"\n");
                System.out.println("Scoreboard was found");
            }
            out.close();
            
            //vzimane na poslednite 10 pobeditelia ot scoreboard-a i slagane v saotvetnia label
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            int lines = 0;
            reader.mark(1000000);
            while (reader.readLine() != null) {
                lines++;
            } 
            System.out.println(lines);
            reader.reset();
            //BufferedReader reader2 = new BufferedReader(new FileReader(file.getPath()));
            for (int i = 0; i < lines; i++) {
                
                if(i > lines-6)
                {
                    System.out.println(i);
                    scoreboardText += reader.readLine();
                    scoreboardText +="\n";
                    System.out.println(scoreboardText);
                }
                else
                {
                    reader.readLine();
                }
                
            }
            reader.close();
            //reader.close();
            
        } catch (IOException e) {
            System.out.println("Exception Occured:");
            e.printStackTrace();
        }

 

        scoreboard.setText(scoreboardText);
        layeredPane.revalidate();
    }

 

    public static void main(String[] args) {
        Player p1 = new Player(), p2 = new Player();
        p1.setName("FirstPlayer");
        p2.setName("SecondPlayer");

 

        new EndScreen(p1, p2).setVisible(true);
    }
}



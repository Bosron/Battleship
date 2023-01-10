/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author damyanlh
 */
public class NewEndscreenFramework  extends javax.swing.JFrame{

    private JLabel backgorund = new JLabel();
    private JLabel namePlatePlayer1 = new JLabel();
    private JLabel namePlatePlayer2 = new JLabel();
    private JLabel accuracyPlatePlayer1 = new JLabel();
    private JLabel accuracyPlatePlayer2 = new JLabel();
    private JLabel txtAccuracy = new JLabel();
    private JLabel txtWinner = new JLabel();
    private JLabel txtLoser = new JLabel();
    private JLabel txtGameover = new JLabel();
    private JLabel loserFog = new JLabel();
    private JTextArea scoreboard = new JTextArea();
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel rematchButton = new JLabel();
    private JLabel quitButton = new JLabel();
    private String scoreboardText = "";

    //players
    private Player winner = new Player();
    private Player loser = new Player();

    public NewEndscreenFramework(Player winner, Player loser) {
        this.winner = winner;
        this.loser = loser;
        //inicializirane

        // <editor-fold defaultstate="collapsed" desc="backgorund">
        backgorund.setBounds(0, 0, 900, 720);
        backgorund.setOpaque(false);
        //backgorund.setIcon(new ImageIcon());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="namePlatePlayer1">
        namePlatePlayer1.setBounds(250, 100, 500, 50);
        namePlatePlayer1.setText(winner.getName());
        namePlatePlayer1.setOpaque(false);
        namePlatePlayer1.setFont(new Font("Fira Sans", Font.BOLD, 40));
        namePlatePlayer1.setText(winner.getName() + " won!");
        //namePlatePlayer1.setIcon(new ImageIcon());
        //backgorund.setIcon(new ImageIcon());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="namePlatePlayer2">
        namePlatePlayer2.setBounds(250, 100, 500, 50);
        namePlatePlayer2.setText(loser.getName());
        namePlatePlayer2.setOpaque(false);
        namePlatePlayer2.setFont(new Font("Fira Sans", Font.BOLD, 40));
        namePlatePlayer2.setText(loser.getName() + " won!");
        //namePlatePlayer2.setIcon(new ImageIcon());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="accuracyPlatePlayer1">
        accuracyPlatePlayer1.setBounds(250, 100, 500, 50);
        accuracyPlatePlayer1.setText("" + findAccuracy(loser));
        accuracyPlatePlayer1.setOpaque(false);
        accuracyPlatePlayer1.setFont(new Font("Fira Sans", Font.BOLD, 40));
        accuracyPlatePlayer1.setText(winner.getName() + " won!");
        //accuracyPlatePlayer1.setIcon(new ImageIcon());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="accuracyPlatePlayer2">
        accuracyPlatePlayer2.setBounds(250, 100, 500, 50);
        accuracyPlatePlayer2.setText("" + findAccuracy(winner));
        accuracyPlatePlayer2.setOpaque(false);
        accuracyPlatePlayer2.setFont(new Font("Fira Sans", Font.BOLD, 40));
        accuracyPlatePlayer2.setText(winner.getName() + " won!");
        //accuracyPlatePlayer2.setIcon(new ImageIcon());
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="rematchButton">
        rematchButton.setBounds(250, 100, 500, 50);
        rematchButton.setOpaque(false);
        //rematchButton.setIcon(new ImageIcon());
        rematchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startRematch();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="quitButton">
        quitButton.setBounds(250, 100, 500, 50);
        quitButton.setOpaque(false);
        //quitButton.setIcon(new ImageIcon());
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                disposer();
            }
        });
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="txtAccuracy">
        txtAccuracy.setBounds(250, 100, 500, 50);
        txtAccuracy.setOpaque(false);
        //txtAccuracy.setIcon(new ImageIcon());
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="txtWinner">
        txtWinner.setBounds(250, 100, 500, 50);
        txtWinner.setOpaque(false);
        //txtWinner.setIcon(new ImageIcon());
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="txtLoser">
        txtLoser.setBounds(250, 100, 500, 50);
        txtLoser.setOpaque(false);
        //txtLoser.setIcon(new ImageIcon());
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="txtGameover">
        txtGameover.setBounds(250, 100, 500, 50);
        txtGameover.setOpaque(false);
        //txtGameover.setIcon(new ImageIcon());
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="loserFog">
        loserFog.setBounds(250, 100, 500, 50);
        loserFog.setOpaque(false);
        //loserFog.setIcon(new ImageIcon());
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
            //Logger.getLogger(EndScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        // </editor-fold>
        
        this.setLayeredPane(layeredPane);
        this.revalidate();

        layeredPane.add(backgorund, Integer.valueOf(0));
        layeredPane.add(namePlatePlayer1, Integer.valueOf(1));
        layeredPane.add(namePlatePlayer2, Integer.valueOf(1));
        layeredPane.add(accuracyPlatePlayer1, Integer.valueOf(1));
        layeredPane.add(accuracyPlatePlayer2, Integer.valueOf(1));
        layeredPane.add(txtAccuracy, Integer.valueOf(2));
        layeredPane.add(txtWinner, Integer.valueOf(1));
        layeredPane.add(txtLoser, Integer.valueOf(0));
        layeredPane.add(txtGameover, Integer.valueOf(2));
        layeredPane.add(loserFog, Integer.valueOf(3));
        layeredPane.add(scoreboard, Integer.valueOf(2));
        layeredPane.add(rematchButton, Integer.valueOf(2));
        layeredPane.add(quitButton, Integer.valueOf(2));
        
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
        disposer();
        new MainMenu().run();
    }

    public void disposer() {
        this.dispose();
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
                System.out.println("Scoreboard was found");
            }
            out.write(winner.getName() + ":" + StrikeMenu.getRounds() + " rounds" + "\n");
            out.close();

 

            //vzimane na poslednite 10 pobeditelia ot scoreboard-a i slagane v saotvetnia label
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            int lines = 0;
            reader.mark(10000);
            while (reader.readLine() != null) {
                lines++;
            }
            System.out.println(lines);
            reader.reset();
            for (int i = 0; i < lines; i++) {
                if (i > lines - 6) {
                    System.out.println(i);
                    scoreboardText += reader.readLine();
                    scoreboardText += "\n";
                    System.out.println(scoreboardText);
                } else {
                    reader.readLine();
                }
            }
            reader.close();
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

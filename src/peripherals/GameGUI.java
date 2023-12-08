package peripherals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import engine.GameEngine;

/**
 * A Simple Graphical User Interface for the Six Equation Game.
 * 
 * @author Gopal Gurung
 *
 */
public class GameGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = -107785653906635L;

    private JLabel questArea;
    private JTextArea infoArea;
    private GameEngine myGame;
    private BufferedImage currentGame;

    // Timer variables
    private JLabel timerLabel;
    private Timer gameTimer;

    /**
     * Method that is called when a button has been pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int solution = Integer.parseInt(e.getActionCommand());
        boolean correct = myGame.checkSolution(solution);
        int score = myGame.getScore();
        if (correct) {
            System.out.println("Correct solution entered!");
            currentGame = myGame.nextGame();
            ImageIcon ii = new ImageIcon(currentGame);
            questArea.setIcon(ii);
            infoArea.setText("Congrats!  Score: " + score);
            resetGameTimer(); // Reset the timer for the new game
        } else {
            System.out.println("Wrong answer");
            infoArea.setText("Oops. Not correct!  Score: " + score);
        }
    }

    /**
     * Initializes the game.
     * 
     * @param player
     */
    private void initGame(String player) {
        setSize(690, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Solve the equation?");
        JPanel panel = new JPanel();

        myGame = new GameEngine(player);
        currentGame = myGame.nextGame();

        infoArea = new JTextArea(1, 40);
        infoArea.setEditable(false);
        infoArea.setText("What is the correct tomato equation?   Points: 0");

        JScrollPane infoPane = new JScrollPane(infoArea);
        panel.add(infoPane);

        ImageIcon ii = new ImageIcon(currentGame);
        questArea = new JLabel(ii);
        panel.add(questArea);
        questArea.setBackground(new java.awt.Color(0, 0, 0));
        questArea.setSize(330, 600);

        JScrollPane questPane = new JScrollPane(questArea);
        panel.add(questPane);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            panel.add(btn);
            btn.addActionListener(this);
        }

        // Add Timer Label to the bottom right corner
        timerLabel = new JLabel("Time remaining: 1:30");
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(timerLabel);

        getContentPane().add(panel);
        panel.repaint();

        // Initialize the timer
        gameTimer = new Timer(1000, new ActionListener() {
            int timeRemaining = 90; // Initial time in seconds

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    updateTimerLabel(timeRemaining);
                } else {
                    gameTimer.stop();
                    handleTimeOver();
                }
            }
        });
        gameTimer.start();
    }

    /**
     * Updates the timer label with the remaining time.
     * 
     * @param seconds Remaining time in seconds
     */
    private void updateTimerLabel(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        timerLabel.setText(String.format("Time remaining: %d:%02d", minutes, remainingSeconds));
    }

    /**
     * Restarts the game timer.
     */
    private void resetGameTimer() {
        gameTimer.restart();
    }

    /**
     * Handles actions when the time is over.
     */
    private void handleTimeOver() {
        JOptionPane.showMessageDialog(this, "Time's up! Game Over.");
        // Add logic to handle game over, e.g., reset or end the game
    }

    /**
     * Default player is null.
     */
    public GameGUI() {
        super();
        initGame(null);
        setVisible(true);
    }

    /**
     * Using this to start GUI, e.g., after login.
     * 
     * @param player
     */
    public GameGUI(String player) {
        super();
        initGame(player);
        setVisible(true);
    }

    // /**
    //  * Main entry point into the equation game. Can be used without login for testing.
    //  * 
    //  * @param args not used.
    //  */
    // public static void main(String[] args) {
    //     GameGUI myGUI = new GameGUI();
    //     myGUI.setVisible(true);
    // }
}

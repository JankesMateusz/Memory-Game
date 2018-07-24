import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


class GameWindow extends JFrame implements Runnable, WindowListener{

    private boolean gameRun;
    private int gridSize;

    private int seconds = 0;
    private int minutes = 0;
    private int secondsForRanking = 0;
    private int gridA;
    private int gridB;
    private int difficulty;

    private JTextField time;


    GameWindow(int difficulty, int gridA, int gridB, boolean gameRun){

        this.gameRun = gameRun;
        this.gridSize = gridA * gridB;
        this.gridA = gridA;
        this.gridB = gridB;
        this.difficulty = difficulty;

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(this);

        initGameBoard();
        initTimerPanel();

        pack();
        setVisible(true);
    }


    private void initGameBoard(){

        JPanel gameBoard = new JPanel();

        gameBoard.setLayout(new GridLayout(gridA, gridB));

        CardFactory factory = new CardFactory();
        ArrayList<Card> cards = factory.makeCards(gridSize);
        Collections.shuffle(cards);

        CardController controller = new CardController(this, gridSize);

        for(Card c : cards){
            c.setController(controller);
            controller.collectAllCards(c);
            gameBoard.add(c);
        }

        add(gameBoard);
    }


    private void initTimerPanel(){

        Thread timer = new Thread(this, "timer");

        JPanel timerPanel = new JPanel();

        time = new JTextField(minutes + ":0" + seconds);
        time.setPreferredSize(new Dimension(200, 80));
        time.setEditable(false);
        time.setFont(new Font("Arial", Font.BOLD, 24));
        time.setHorizontalAlignment(JTextField.CENTER);

        timerPanel.add(time, BorderLayout.CENTER);

        add(timerPanel);

        timer.start();
    }


    private void incrementTime(JTextField field){
        if(gameRun)
            seconds++;
            secondsForRanking++;
            if(seconds == 60){
                minutes++;
                seconds = 0;
            }
            if(seconds > 9)
                field.setText(minutes + ":" + seconds);
            else
                field.setText(minutes + ": 0" + seconds);
    }


    private String getTime(){
        if(seconds > 9)
            return (minutes + ":" + seconds);
        else
            return(minutes + ":0" + seconds);
    }


    public void setGameEnd() throws IOException{
        gameRun = false;
        new EnterScore(getTime(), secondsForRanking, gridSize, gridA, gridB, this, difficulty);
    }


    public void run(){
        while(gameRun){
            try{
                Thread.sleep(1000);
                incrementTime(time);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void windowClosed(WindowEvent e){
        gameRun = false;
        dispose();
    }







    public void windowDeiconified(WindowEvent e){

    }

    public void windowOpened(WindowEvent e){

    }

    public void windowClosing(WindowEvent e){

    }

    public void windowIconified(WindowEvent e){

    }

    public void windowDeactivated(WindowEvent e){

    }

    public void windowActivated(WindowEvent e ){

    }
}

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;


class CardController{

    private int cardsToMatch;
    private int cardsMatched = 0;
    private GameWindow game;

    private ArrayList<Card> flippedCards = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();


    CardController(GameWindow gameWindow, int cardsToMatch){
        this.game = gameWindow;
        this.cardsToMatch = (cardsToMatch / 2);

    }


    void getCards(Card card){
        flippedCards.add(card);
        card.setClickableOff();
        if(flippedCards.size() == 2){
            allCardsBlocked();
            if(flippedCards.get(0).getCardID() == flippedCards.get(1).getCardID()) {
                cardsMatched++;
                allCards.remove(flippedCards.get(0));
                allCards.remove(flippedCards.get(1));
                flippedCards.clear();
                try {
                    checkGameStatus();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                allCardsUnlocked();
            }
            else {
                Card card1 = flippedCards.get(0);
                Card card2 = flippedCards.get(1);

                card1.cardFlip();
                card2.cardFlip();
                Timer timer = new Timer(1000, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        card1.showImage();
                        card2.showImage();
                        allCardsUnlocked();
                    }
                });
                timer.setRepeats(false);
                timer.start();
                flippedCards.clear();
            }
        }
    }


    public void collectAllCards(Card c){
        allCards.add(c);
    }


    private void allCardsBlocked(){
        for(Card c : allCards)
            c.setClickableOff();
    }


    private void allCardsUnlocked(){
        for(Card c : allCards)
            c.setClickableOn();
    }


    private void checkGameStatus() throws IOException{
        if(cardsMatched == cardsToMatch)
            game.setGameEnd();
    }
}

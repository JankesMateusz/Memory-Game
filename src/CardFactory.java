import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class CardFactory {


    private ArrayList<MyImageIcon> images;
    private ImageIcon cardBack;


    public CardFactory(){

        cardBack = new ImageIcon("src/images/cardBack.png");

        MyImageIcon bull = new MyImageIcon("src/images/bull.png");
        MyImageIcon chicken = new MyImageIcon("src/images/chicken.png");
        MyImageIcon fox = new MyImageIcon("src/images/fox.png");
        MyImageIcon hedgehog = new MyImageIcon("src/images/hedgehog.png");
        MyImageIcon hippo = new MyImageIcon("src/images/hippo.png");
        MyImageIcon koala = new MyImageIcon("src/images/koala.png");
        MyImageIcon lemur = new MyImageIcon("src/images/lemur.png");
        MyImageIcon pig = new MyImageIcon("src/images/pig.png");
        MyImageIcon tiger = new MyImageIcon("src/images/tiger.png");
        MyImageIcon zebra = new MyImageIcon("src/images/zebra.png");

        images = new ArrayList<>();
        images.add(bull);
        images.add(chicken);
        images.add(fox);
        images.add(hedgehog);
        images.add(hippo);
        images.add(koala);
        images.add(lemur);
        images.add(pig);
        images.add(tiger);
        images.add(zebra);

    }


    public ArrayList<Card> makeCards(int amount){
        Collections.shuffle(images);
        ArrayList<Card> cards = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < amount / 2; j++){
                Card c = new Card(cardBack, images.get(j),images.get(j).getID());
                cards.add(c);
            }
        }
        return cards;
    }

}

import javax.swing.*;
import java.awt.*;

class Card extends JButton{


    private CardController controller;

    private ImageIcon image1;
    private MyImageIcon image2;

    private int cardID;
    private boolean cardUp;
    private boolean clickable;


    Card(ImageIcon image1, MyImageIcon image2, int cardID){

        this.image1 = image1;
        this.image2 = image2;
        this.cardID = cardID;

        clickable = true;
        cardUp = false;

        setPreferredSize(new Dimension(220, 220));
        setBackground(new Color(230, 230, 230));

        ImageIcon image = new ImageIcon("src/images/cardBack.png");
        Image tmp = image.getImage();
        ImageIcon icon = new ImageIcon(tmp.getScaledInstance(220, 220, Image.SCALE_SMOOTH));
        setIcon(icon);

        addActionListener(ActionEvent ->{
            if(clickable) {
                cardFlip();
                showImage();
                controller.getCards(this);
            }
        });
    }


    int getCardID(){return cardID;}


    private ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight){
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


    void showImage(){
        if(cardUp){
            setIcon(resizeIcon(image2, this.getWidth(), getHeight()));
        }
        else
            setIcon(resizeIcon(image1, this.getWidth(), getHeight()));
    }


    void cardFlip(){
        if(cardUp)
            cardUp = false;
        else
            cardUp = true;
    }


    void setController(CardController controller){
        this.controller = controller;
    }


    void setClickableOff(){
        this.clickable = false;
    }


    void setClickableOn(){
        this.clickable = true;
    }

}

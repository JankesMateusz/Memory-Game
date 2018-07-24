import javax.swing.*;

public class MyImageIcon extends ImageIcon {

    private static int counter;
    private int iconID = 0;

    MyImageIcon(String filename){
        super(filename);
        counter++;
        iconID += counter;
    }

    public int getID(){
        return iconID;
    }
}

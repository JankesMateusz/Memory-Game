import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


class Scoreboard extends JDialog{


    Scoreboard(){

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Scoreboard");

        JLabel rank = new JLabel("Ranking");

        File file = new File("src/scores.txt");
        ArrayList<String> list = new ArrayList<>();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));


            while((line = br.readLine()) != null)
                list.add(line);
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        MyListModel mlm = new MyListModel(list);
        JList<String> ranking = new JList<>(mlm);
        JScrollPane scroll = new JScrollPane(ranking);

        setPreferredSize(new Dimension(200, 300));

        add(rank);
        add(scroll);

        rank.setAlignmentX(CENTER_ALIGNMENT);

        pack();
        setVisible(true);
    }
}

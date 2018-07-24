import java.io.Serializable;

public class Result implements  Serializable {

    private String display;
    private int score;

    public Result(String display, int score){
        this.display = display;
        this.score = score;
    }

    public String toString(){
        return display;
    }

    public int getScore(){
        return score;
    }
}

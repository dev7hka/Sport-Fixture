import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Football extends Sport {

    private int draw_number;
    public Football(String name){

        super(name);
        this.draw_number = 0;
    }
    private void setDraw_number(){
        ++this.draw_number;
    }
    private int getdraw_number(){
        return this.draw_number;
    }
    public void calculate_point(String[] line){                 // takes the fixtures.txt line and analyzes the point and scores.
        this.setMatch_number();
        int score1 = Integer.parseInt(line[3].split(":")[0].trim());
        int score2 = Integer.parseInt(line[3].split(":")[1].trim());

        if (score1 > score2 && line[1].equals(this.getName())) {
            this.setGained_score(score1);
            this.setWon_number();
            this.setLost_score(score2);
            this.setTotal_point(3);
        }
        else if(score2 > score1 && line[2].equals(this.getName())) {
            this.setGained_score(score2);
            this.setWon_number();
            this.setLost_score(score1);
            this.setTotal_point(3);
        }
        else if(score1 == score2) {
            this.setGained_score(score1);
            this.setDraw_number();
            this.setLost_score(score1);
            this.setTotal_point(1);
        }
        else{
            if(line[1].equals(this.getName())) {
                this.setLost_score(score2);
                this.setGained_score(score1);
            }
            else {
                this.setLost_score(score1);
                this.setGained_score(score2);
            }
        }
    }
    public void getInfo(BufferedWriter bf) {
        try {
            bf.write(this.getName() + "\t" + this.getMatch_number() + "\t" + this.getWon_number() + "\t" + this.getdraw_number() + "\t" +
                    (this.getMatch_number() - this.getWon_number() - this.getdraw_number()) + "\t" + this.getGained_score() + ":" +
                    this.getLost_score() + "\t" + this.getTotal_point());
        } catch (IOException e) {
            System.out.println("File I/O Problem");
        }
    }

    public void get_fixture(LinkedHashMap<String, Football> football, BufferedWriter bf){          // uses the sorting class from ancestor and prints the sorted fixture

        ArrayList<Temp> temp_list = new ArrayList<>(football.size());
        for (String name : football.keySet()){
            temp_list.add(new Temp(name, football.get(name).getTotal_point(), football.get(name).getGained_score()-football.get(name).getLost_score()));
        }
        temp_list.sort(new sort_by_point());
        try {
            for (int i = 0; i < temp_list.size(); i++) {
                bf.write(i + 1 + ".\t");
                football.get(temp_list.get(i).name).getInfo(bf);
                if (i + 1 != temp_list.size())
                    bf.write("\n");
            }
        }
        catch (IOException e){
            System.out.println("File I/O Problem");
        }
    }
}

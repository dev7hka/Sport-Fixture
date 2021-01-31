import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Basketball extends Sport{

    public Basketball(String name){

        super(name);

    }
    public void calculate_point(String[] line){   // takes the fixtures.txt line and analyzes the point and scores.

        this.setMatch_number();
        int score1 = Integer.parseInt(line[3].split(":")[0].trim());
        int score2 = Integer.parseInt(line[3].split(":")[1].trim());

        if (score1 > score2 && line[1].equals(this.getName())) {
            this.setGained_score(score1);
            this.setWon_number();
            this.setLost_score(score2);
            this.setTotal_point(2);
        }
        else if(score2 > score1 && line[2].equals(this.getName())) {
            this.setGained_score(score2);
            this.setWon_number();
            this.setLost_score(score1);
            this.setTotal_point(2);
        }
        else {
            if (line[1].equals(this.getName())){
                this.setGained_score(score1);
                this.setLost_score(score2);
                this.setTotal_point(1);
            }
            else{
                this.setGained_score(score2);
                this.setLost_score(score1);
                this.setTotal_point(1);
            }
        }
    }
    public void get_fixture(LinkedHashMap<String, Basketball> basketball, BufferedWriter bf){      // uses the sorting class from ancestor and prints the sorted fixture

        ArrayList<Temp> temp_list = new ArrayList<>(basketball.size());
        for (String name : basketball.keySet()){
            temp_list.add(new Temp(name, basketball.get(name).getTotal_point(),basketball.get(name).getGained_score()-basketball.get(name).getLost_score()));
        }
        temp_list.sort(new sort_by_point());
        try {
            for (int i = 0; i < temp_list.size(); i++) {
                bf.write(i + 1 + ".\t");
                basketball.get(temp_list.get(i).name).getInfo(bf);
                if (i + 1 != temp_list.size())
                    bf.write("\n");
            }
        }
        catch (IOException e){
            System.out.println("File I/O Problem");
        }
    }
}

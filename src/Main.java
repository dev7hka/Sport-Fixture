import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {
            if (args.length != 4){
                System.out.println("Wrong input!!!");
                return;
            }
            LinkedHashMap<String, Football> football = new LinkedHashMap<>();                //  To
            LinkedHashMap<String, Basketball> basketball = new LinkedHashMap<>();            //  keep
            LinkedHashMap<String, Volleyball> volleyball = new LinkedHashMap<>();            //  teams

            File file = new File(args[0]);

            BufferedReader bfreader = new BufferedReader(new FileReader(file));
            BufferedWriter bfwriter ;

            String line;
            for (line = bfreader.readLine(); line != null; line = bfreader.readLine()){
                int i = 1;
                while (i != 3) {
                    String[] token = line.split("\t");

                    switch (token[0]) {
                        case "F": {
                            Football tmp = football.get(token[i]);
                            if (tmp == null) {
                                football.put(token[i], new Football(token[i]));
                                football.get(token[i]).calculate_point(token);
                            } else
                                football.get(token[i]).calculate_point(token);
                            break; }
                        case "B": {
                            Basketball tmp = basketball.get(token[i]);
                            if (tmp == null) {
                                basketball.put(token[i], new Basketball(token[i]));
                                basketball.get(token[i]).calculate_point(token);
                            } else
                                basketball.get(token[i]).calculate_point(token);
                            break; }
                        case "V": {
                            Volleyball tmp = volleyball.get(token[i]);
                            if (tmp == null) {
                                volleyball.put(token[i], new Volleyball(token[i]));
                                volleyball.get(token[i]).calculate_point(token);
                            } else {
                                volleyball.get(token[i]).calculate_point(token);
                            }
                            break; }
                    }
                    i++;
                }
            }
            file = new File(args[1]);
            bfwriter = new BufferedWriter(new FileWriter(file));
            Sport temp = new Football("temp");
            ((Football) temp).get_fixture(football, bfwriter);
            bfwriter.flush();
            file = new File(args[2]);
            bfwriter = new BufferedWriter(new FileWriter(file));
            temp = new Basketball("temp");
            ((Basketball) temp).get_fixture(basketball,bfwriter);
            bfwriter.flush();
            file = new File(args[3]);
            bfwriter = new BufferedWriter(new FileWriter(file));
            temp = new Volleyball("temp");
            ((Volleyball) temp).get_fixture(volleyball, bfwriter);
            bfreader.close();
            bfwriter.flush();bfwriter.close();
        }
        catch (IOException i){
            System.out.println("File I/O problem");
        }
    }
}

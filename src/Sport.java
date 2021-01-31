import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

abstract class Sport {

    private String name;
    private int match_number = 0;
    private int won_number= 0;
    private int gained_score = 0;
    private int lost_score = 0;
    private int total_point = 0;

    Sport(String name){
        this.name = name;
    }

    abstract void calculate_point(String[] line);
    public void getInfo(BufferedWriter bf) {
        try {
            bf.write(this.getName() + "\t" + this.getMatch_number() + "\t" + this.getWon_number() + "\t" +
                    (this.getMatch_number() - this.getWon_number()) + "\t" + this.getGained_score() + ":" +
                    this.getLost_score() + "\t" + this.getTotal_point());
        }
        catch (IOException e) {
            System.out.println("File I/O Problem");
        }
    }
    protected String getName() {
        return name; }
    protected int getMatch_number() {
        return match_number; }
    protected int getWon_number() {
        return won_number; }
    protected int getGained_score() {
        return gained_score; }
    protected int getLost_score() {
        return lost_score; }
    protected int getTotal_point() {
        return total_point; }
    protected void setMatch_number(){
        ++this.match_number; }
    protected void setWon_number() {
        ++this.won_number;    }
    protected  void setGained_score(int gained_score) {
        this.gained_score += gained_score ; }
    protected void setLost_score(int lost_score) {
        this.lost_score += lost_score ; }
    protected void setTotal_point(int total_point) {
        this.total_point += total_point ; }

    class Temp{    // a shell (temporary) class to provide the needed attributes

        protected String name;
        private int point;
        private int diff;

        Temp(String name, int point, int diff){
            this.name = name;
            this.point = point;
            this.diff = diff;
        }
    }
    class sort_by_point implements Comparator<Temp> {  // uses Temp class and compares two object's points

        public int compare(Temp a, Temp b) {  // If two object have same point (for football class) returns average score
            if (a.point == b.point){
                return b.diff - a.diff;
            }
            return b.point - a.point;
        }
    }
}



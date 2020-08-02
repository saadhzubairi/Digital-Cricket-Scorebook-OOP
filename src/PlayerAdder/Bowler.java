package PlayerAdder;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Bowler extends Batsman implements Serializable{
    
    public transient String bowl_hand;
    public transient String bowl_mod;
    public transient int wickets;
    public transient double average_economy;
    
    
    public Bowler(int id, String name, String department, String age, String format, String bat_hand, String bat_mod, boolean iskeeper, int average_runs, double average_stkrate,
                  String bowl_hand, String bowl_mod, int wickets, double average_economy) {
        super(id, name, department, age, format, bat_hand, bat_mod, iskeeper, average_runs, average_stkrate);
        this.bowl_hand          = bowl_hand;
        this.bowl_mod           = bowl_mod;
        this.wickets            = wickets;
        this.average_economy    = average_economy;
    }
    
    public String getBowl_hand() {
        return bowl_hand;
    }
    
    public void setBowl_hand(String bowl_hand) {
        this.bowl_hand = bowl_hand;
    }
    
    public String getBowl_mod() {
        return bowl_mod;
    }
    
    public void setBowl_mod(String bowl_mod) {
        this.bowl_mod = bowl_mod;
    }
    
    public int getWickets() {
        return wickets;
    }
    
    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
    
    public double getAverage_economy() {
        return average_economy;
    }
    
    public void setAverage_economy(double average_economy) {
        this.average_economy = average_economy;
    }
    
    @Override
    public String toString() {
        return "Bowler{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

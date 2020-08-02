package PlayerAdder;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Batsman extends Player implements Serializable{
    public String bat_hand;
    public String bat_mod;
    public boolean iskeeper;
    public int average_runs;
    public double average_stkrate;
    
    public Batsman(int id, String name, String department, String age, String format,
                   String bat_hand, String bat_mod, boolean iskeeper, int average_runs, double average_stkrate) {
        super(id, name, department, age, format);
        this.bat_hand           = bat_hand;
        this.bat_mod            = bat_mod ;
        this.iskeeper           = iskeeper;
        this.average_runs       = average_runs;
        this.average_stkrate    = average_stkrate;
    }
    
    public String getBat_hand() {
        return bat_hand;
    }
    public void setBat_hand(String bat_hand) {
        this.bat_hand = bat_hand;
    }
    public String getBat_mod() {
        return bat_mod;
    }
    public void setBat_mod(String bat_mod) {
        this.bat_mod = bat_mod;
    }
    public boolean isIskeeper() {
        return iskeeper;
    }
    public void setIskeeper(boolean iskeeper) {
        this.iskeeper = iskeeper;
    }
    public int getAverage_runs() {
        return average_runs;
    }
    public void setAverage_runs(int average_runs) {
        this.average_runs = average_runs;
    }
    public double getAverage_stkrate() {
        return average_stkrate;
    }
    public void setAverage_stkrate(double average_stkrate) {
        this.average_stkrate = average_stkrate;
    }
    
    @Override
    public String toString() {
        return "Batsman{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

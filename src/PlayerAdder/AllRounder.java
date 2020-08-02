package PlayerAdder;


import java.io.Serializable;

public class AllRounder extends Bowler implements Serializable {
    
    public AllRounder(int id, String name, String department, String age, String format, String bat_hand, String bat_mod, boolean iskeeper,
                      int average_runs, double average_stkrate,
                      String bowl_hand, String bowl_mod,
                      int wickets, double average_economy) {
        super(id, name, department, age, format, bat_hand, bat_mod, iskeeper, average_runs, average_stkrate, bowl_hand, bowl_mod, wickets, average_economy);
    }
    
    @Override
    public String toString() {
        return "AllRounder{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
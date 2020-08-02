package Match;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InningTwo extends InningOne{
    IntegerProperty inning_target;
    
    public InningTwo(BattingTeam inning_batteam, BowlingTeam inning_bowlteam) {
        super(inning_batteam, inning_bowlteam);
        inning_target = new SimpleIntegerProperty(this, "inning_target", 0);
    }
    
    public int getInning_target() {
        return inning_target.get();
    }
    
    public IntegerProperty inning_targetProperty() {
        return inning_target;
    }
    
    public void setInning_target(int inning_target) {
        this.inning_target.set(inning_target);
    }
}

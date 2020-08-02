package Match;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InningOne {
    public IntegerProperty inning_runs;
    public IntegerProperty inning_balls;
    public IntegerProperty inning_wickets;
    public BattingTeam inning_batteam;
    public BowlingTeam inning_bowlteam;
    public StringProperty inningovers;
    
    public InningOne(BattingTeam inning_batteam, BowlingTeam inning_bowlteam) {
        this.inning_runs = new SimpleIntegerProperty(this, "inning_runs",0);
        this.inning_balls = new SimpleIntegerProperty(this, "inning_balls",0);
        this.inning_wickets = new SimpleIntegerProperty(this, "inning_wickets",0);
        this.inning_batteam = inning_batteam;
        this.inning_bowlteam = inning_bowlteam;
        this.inningovers = new SimpleStringProperty(this, "inningovers","0.0");
    }
    
    public int getInning_runs() { return inning_runs.get(); }
    public IntegerProperty inning_runsProperty() { return inning_runs; }
    public void setInning_runs(int inning_runs) { this.inning_runs.set(inning_runs); }
    
    public int getInning_balls() { return inning_balls.get(); }
    public IntegerProperty inning_ballsProperty() { return inning_balls; }
    public void setInning_balls(int inning_balls) { this.inning_balls.set(inning_balls); }
    
    public int getInning_wickets() { return inning_wickets.get(); }
    public IntegerProperty inning_wicketsProperty() { return inning_wickets; }
    public void setInning_wickets(int inning_wickets) { this.inning_wickets.set(inning_wickets); }
    
    public BattingTeam getInning_batteam() { return inning_batteam; }
    public void setInning_batteam(BattingTeam inning_batteam) { this.inning_batteam = inning_batteam; }
    
    public BowlingTeam getInning_bowlteam() { return inning_bowlteam; }
    public void setInning_bowlteam(BowlingTeam inning_bowlteam) { this.inning_bowlteam = inning_bowlteam; }
    
    public String getInningovers() { return inningovers.get(); }
    public StringProperty inningoversProperty() { return inningovers; }
    public void setInningovers(String inningovers) { this.inningovers.set(inningovers); }
}

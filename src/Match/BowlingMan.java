package Match;

import PlayerAdder.Player;
import javafx.beans.property.*;

public class BowlingMan {
    public Player           bowlplayer;
    public IntegerProperty  id;
    public StringProperty   name;
    public IntegerProperty  balls;
    public IntegerProperty  runs;
    public IntegerProperty  wickets;
    public DoubleProperty   economy;
    public StringProperty   overs_String;
    
    public BowlingMan(Player bowlplayer){
        this.bowlplayer = bowlplayer;
        this.id             = new SimpleIntegerProperty (this, "id", bowlplayer.getId()       );
        this.name           = new SimpleStringProperty  (this, "name", bowlplayer.getName()   );
        this.balls          = new SimpleIntegerProperty (this, "balls", 0                   );
        this.runs           = new SimpleIntegerProperty (this, "runs",0                     );
        this.wickets        = new SimpleIntegerProperty (this, "wickets",0                  );
        this.economy        = new SimpleDoubleProperty  (this, "economy", 0.0               );
        this.overs_String   = new SimpleStringProperty  (this, "overs", "0.0"              );
    }
    
    public Player getBowlplayer() { return bowlplayer; }
    public void setBowlplayer(Player bowlplayer) { this.bowlplayer = bowlplayer; }
    
    public int getBalls() { return balls.get(); }
    public IntegerProperty ballsProperty() { return balls; }
    public void setBalls(int balls) { this.balls.set(balls); }
    
    public int getRuns() { return runs.get(); }
    public IntegerProperty runsProperty() { return runs; }
    public void setRuns(int runs) { this.runs.set(runs); }
    
    public int getWickets() { return wickets.get(); }
    public IntegerProperty wicketsProperty() { return wickets; }
    public void setWickets(int wickets) { this.wickets.set(wickets); }
    
    public double getEconomy() { return economy.get(); }
    public DoubleProperty economyProperty() { return economy; }
    public void setEconomy(double economy) { this.economy.set(economy); }
    
    public String getOvers_String() { return overs_String.get();}
    public StringProperty overs_StringProperty() { return overs_String; }
    public void setOvers_String(String overs_String) { this.overs_String.set(overs_String); }
    
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    public void setId(int id) { this.id.set(id); }
    
    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public void setName(String name) { this.name.set(name); }
}

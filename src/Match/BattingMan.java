package Match;

import PlayerAdder.Player;
import javafx.beans.property.*;

public class BattingMan {
    public Player batplayer;
    public IntegerProperty id;
    public StringProperty name;
    public IntegerProperty balls;
    public IntegerProperty runs;
    public IntegerProperty sixes;
    public IntegerProperty fours;
    public DoubleProperty strikerate;
    public StringProperty overs_String;
    public boolean out_or_not;
    
    public BattingMan(Player batplayer) {
        this.batplayer = batplayer;
        this.id   = new SimpleIntegerProperty(this, "id", batplayer.getId());
        this.name = new SimpleStringProperty(this, "name", batplayer.getName());
        this.balls = new SimpleIntegerProperty(this, "balls", 0);
        this.runs = new SimpleIntegerProperty(this, "runs",0);
        this.sixes = new SimpleIntegerProperty(this, "sixes",0);
        this.fours = new SimpleIntegerProperty(this, "fours",0);
        this.strikerate = new SimpleDoubleProperty(this, "sr",1.0);
        this.out_or_not = false;
        this.overs_String = new SimpleStringProperty(this, "overs", "0.0");
    }
    
    public BattingMan() { }
    
    public Player getBatplayer() { return batplayer; }
    public void setBatplayer(Player batplayer) { this.batplayer = batplayer; }
    
    public int getBalls() { return balls.get(); }
    public IntegerProperty ballsProperty() { return balls; }
    public void setBalls(int balls) { this.balls.set(balls); }
    
    public int getRuns() { return runs.get(); }
    public IntegerProperty runsProperty() { return runs; }
    public void setRuns(int runs) { this.runs.set(runs); }
    
    public int getSixes() { return sixes.get(); }
    public IntegerProperty sixesProperty() { return sixes; }
    public void setSixes(int sixes) { this.sixes.set(sixes); }
    
    public int getFours() { return fours.get(); }
    public IntegerProperty foursProperty() { return fours; }
    public void setFours(int fours) { this.fours.set(fours); }
    
    public double getStrikerate() { return strikerate.get(); }
    public DoubleProperty strikerateProperty() { return strikerate; }
    public void setStrikerate(double strikerate) { this.strikerate.set(strikerate); }
    
    public boolean isOut_or_not() { return out_or_not; }
    public void setOut_or_not(boolean out_or_not) { this.out_or_not = out_or_not; }
    
    public String getOvers_String() { return overs_String.get(); }
    public StringProperty overs_StringProperty() { return overs_String; }
    public void setOvers_String(String overs_String) { this.overs_String.set(overs_String); }
    
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    public void setId(int id) { this.id.set(id); }
    
    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public void setName(String name) { this.name.set(name); }
    
}

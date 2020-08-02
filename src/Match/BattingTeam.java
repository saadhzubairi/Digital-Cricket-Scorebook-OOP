package Match;

import TeamsView.Team;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BattingTeam {
    
    public Team BattingTeam;
    public ObservableList<BattingMan> battingMEN = FXCollections.observableArrayList();
    
    public BattingTeam(Team battingTeam, ObservableList<BattingMan> battingMEN) {
        BattingTeam = battingTeam;
        this.battingMEN = battingMEN;
    }
    
    public Team getBattingTeam() { return BattingTeam; }
    public void setBattingTeam(Team battingTeam) { BattingTeam = battingTeam; }
    
    public ObservableList<BattingMan> getBattingMEN() { return battingMEN; }
    public void setBattingMEN(ObservableList<BattingMan> battingMEN) { this.battingMEN = battingMEN; }
    
}

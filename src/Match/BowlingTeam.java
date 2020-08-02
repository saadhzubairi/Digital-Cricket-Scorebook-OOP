package Match;

import TeamsView.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BowlingTeam {
    
    public Team bowlingteam;
    public ObservableList<BowlingMan> bowlingMEN = FXCollections.observableArrayList();
    
    public BowlingTeam(Team bowlingteam, ObservableList<BowlingMan> bowlingMEN) {
        this.bowlingteam = bowlingteam;
        this.bowlingMEN = bowlingMEN;
    }
    
    public Team getBowlingteam() { return bowlingteam; }
    public void setBowlingteam(Team bowlingteam) { this.bowlingteam = bowlingteam; }
    
    public ObservableList<BowlingMan> getBowlingMEN() { return bowlingMEN; }
    public void setBowlingMEN(ObservableList<BowlingMan> bowlingMEN) { this.bowlingMEN = bowlingMEN; }
}

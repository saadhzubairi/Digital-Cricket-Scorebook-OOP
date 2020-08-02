package Match;

import TeamsView.Team;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Match {
    public Team team1;
    public Team team2;
    public StringProperty date;
    public InningOne inningOne;
    public InningTwo inningTwo;
    public StringProperty result;
    public IntegerProperty set_bowl_overs;
    public BattingMan striker;
    public BattingMan runner;
    public BowlingMan baller;
    
    public Match(Team team1, Team team2, InningOne inningOne, InningTwo inningTwo, int set_bowl_overs) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = new SimpleStringProperty(this, "date", "6/14/2020");
        this.inningOne = inningOne;
        this.inningTwo = inningTwo;
        this.result = new SimpleStringProperty(this, "result","");
        this.set_bowl_overs = new SimpleIntegerProperty(this, "set_bowl_overs", set_bowl_overs);
    }
    
    public Team getTeam1() { return team1; }
    public void setTeam1(Team team1) { this.team1 = team1; }
    
    public Team getTeam2() { return team2; }
    public void setTeam2(Team team2) { this.team2 = team2; }
    
    public String getDate() { return date.get(); }
    public StringProperty dateProperty() { return date; }
    public void setDate(String date) { this.date.set(date); }
    
    public InningOne getInningOne() { return inningOne; }
    public void setInningOne(InningOne inningOne) { this.inningOne = inningOne; }
    
    public InningTwo getInningTwo() { return inningTwo; }
    public void setInningTwo(InningTwo inningTwo) { this.inningTwo = inningTwo; }
    
    public String getResult() { return result.get(); }
    public StringProperty resultProperty() { return result; }
    public void setResult(String result) { this.result.set(result); }
    
    public int getSet_bowl_overs() {
        return set_bowl_overs.get();
    }
    public IntegerProperty set_bowl_oversProperty() {
        return set_bowl_overs;
    }
    public void setSet_bowl_overs(int set_bowl_overs) {
        this.set_bowl_overs.set(set_bowl_overs);
    }
    
    public BattingMan getStriker() {
        return striker;
    }
    public void setStriker(BattingMan striker) {
        this.striker = striker;
    }
    
    public BattingMan getRunner() {
        return runner;
    }
    public void setRunner(BattingMan runner) {
        this.runner = runner;
    }
    
    public BowlingMan getBaller() { return baller; }
    public void setBaller(BowlingMan baller) { this.baller = baller; }
    
    @Override
    public String toString() {
        return "Match{" + "team1=" + team1.getTeamname() + ", team2=" + team2.getTeamname() + '}';
    }
}
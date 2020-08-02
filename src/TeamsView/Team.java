package TeamsView;

import PlayerAdder.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable{
    public String id;
    public String teamname;
    public double wintolose;
    public int matches;
    public int wins;
    public int losses;
    public int draws;
    public Player captain;
    public int members;
    public transient ObservableList<Player> players = FXCollections.observableArrayList();
    public ArrayList<Player> saver = new ArrayList<Player>();
    public String capname;
    
    public Team(String id, String teamname, double wintolose, int matches,
                Player captain, int members, ObservableList<Player> team_players,
                int wins, int losses, int draws) {
        this.id         =id;
        this.teamname   = teamname;
        this.wintolose  = wintolose;
        this.matches    = matches;
        this.captain    = captain;
        this.members    = members;
        this.players    = team_players;
        this.capname    = captain.getName();
        this.wins       = wins;
        this.losses     = losses;
        this.draws      = draws;
    }
    
    public Team(String id, String teamname, ObservableList<Player> list, Player captain){
        this.id         = id;
        this.teamname   = teamname;
        this.wintolose  =  0.0;
        this.matches    =  0;
        this.captain    = captain;
        this.members    = list.size();
        this.players    = list;
        this.capname    = captain.getName();
        this.wins       = 0;
        this.losses     = 0;
        this.draws      = 0;
    }
    
    public Team(){
    
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTeamname() {
        return teamname;
    }
    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
    public double getWintolose() {
        return wintolose;
    }
    public void setWintolose(double wintolose) {
        this.wintolose = wintolose;
    }
    public int getMatches() {
        return matches;
    }
    public void setMatches(int matches) {
        this.matches = matches;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public int getDraws() {
        return draws;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public Player getCaptain() {
        return captain;
    }
    public void setCaptain(Player captain) {
        this.captain = captain;
    }
    public int getMembers() {
        return members;
    }
    public void setMembers(int members) {
        this.members = members;
    }
    public ObservableList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ObservableList<Player> players) {
        this.players = players;
    }
    public String getCapname() {
        return capname;
    }
    public void setCapname(String capname) {
        this.capname = capname;
    }
    public ArrayList<Player> getSaver() {return saver;}
    public void setSaver(ArrayList<Player> saver) {this.saver = saver;}
    
    @Override
    public String toString() {
        return "Team{" + "id='" + id + '\'' + ", teamname='" + teamname + '\'' + ", members=" + members + '}';
    }
}
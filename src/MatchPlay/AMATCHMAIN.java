package MatchPlay;

import Match.*;
import Match.Match;
import PlayerAdder.Batsman;
import PlayerAdder.Bowler;
import TeamsView.Team;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class AMATCHMAIN implements Initializable {
    
    @FXML private JFXButton chngbowler_btn;
    @FXML private JFXButton crossing_btn;
    @FXML private Label TEAM1_vs_TEAM2;
    @FXML private Label bottom_label;
    @FXML private Label battm_label, Match_Stat_label, Over_label;
    @FXML private Label inat1_label, batsman1_label, batsman1runs_label, sr1_label;
    @FXML private Label inat2_label, batsman2_label, batsman2runs_label, sr2_label;
    @FXML private Label bowler_label, bowler_overs_label, bowler_runs_label, bowler_economy_label;
    @FXML private JFXButton NEWBALL_bt, bowling_stats_bt, batting_stats_bt;
    @FXML private JFXButton start_nexti_bt;
    
    Timeline UpdateTracker;
    Timeline fiveSecondsWonder;
    
    public Match MATCH;
    
    public InningOne activeInning;
    
    
    public AMATCHMAIN(){}
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
        Timeline OverTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event ->
        {
            if(activeInning.getInning_balls()%6==0 && activeInning.getInning_balls() != 0){
                NEWBALL_bt.setDisable(true);
            }
        }
        ));
        OverTracker.setCycleCount(Timeline.INDEFINITE);
        OverTracker.play();
        
        NEWBALL_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/NewBall.fxml"));
                    Parent root1lala = (Parent) fxmlLoader.load();
                    NewBall newBall = fxmlLoader.getController();
                    newBall.in1inject(MATCH, activeInning, activeInning.inning_batteam);
                    Stage stage = new Stage();
                    stage.setTitle("New Ball");
                    stage.setScene(new Scene(root1lala));
                    stage.show();
                    if (activeInning.getInning_balls()%6 == 5){
                       OverTracker.play();
                    }
                    System.out.println(activeInning.getInning_balls());
                }
                catch (Exception e){
                    System.out.println("ERROR\n" + e);
                }
                updatelabs();
                
            }
        });
        crossing_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                crossing();
                updatelabs();
                bottom_label.setText("Crossing between the wickets!");
            }
        });
        chngbowler_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(activeInning.getInning_balls()%6 == 0){
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/selectBowler.fxml"));
                        Parent root1lala = (Parent) fxmlLoader.load();
                        selectBowler selectBowler = fxmlLoader.getController();
                        selectBowler.injector(MATCH, activeInning.inning_bowlteam);
                        Stage stage = new Stage();
                        stage.setTitle("New Ball");
                        stage.setScene(new Scene(root1lala));
                        stage.show();
                    }
                    catch (Exception e){
                        System.out.println("ERROR\n" + e);
                    }
                    updatelabs();
                    OverTracker.stop();
                    crossing();
                    NEWBALL_bt.setDisable(false);
                    
                }
                else{
                    bottom_label.setText("Cannot Change Bowler During Over");
                }
            }
        });
        bowling_stats_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MATCH.baller.setOvers_String(MATCH.baller.getBalls()/6 + "." + MATCH.baller.getBalls()%6);
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/BOWLSTAT.fxml"));
                    Parent root1lala = (Parent) fxmlLoader.load();
                    BOWLSTAT bowlstat = fxmlLoader.getController();
                    bowlstat.injector(activeInning);
                    Stage stage = new Stage();
                    stage.setTitle("Batting Statistics");
                    stage.setScene(new Scene(root1lala));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR\n" + e);
                }
                updatelabs();
            }
        });
        batting_stats_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/BATSTAT.fxml"));
                    Parent root1lala = (Parent) fxmlLoader.load();
                    BATSTAT batstat = fxmlLoader.getController();
                    batstat.injector(activeInning);
                    Stage stage = new Stage();
                    stage.setTitle("Batting Statistics");
                    stage.setScene(new Scene(root1lala));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR\n" + e);
                }
                updatelabs();
            }
        });
        
        UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            updatelabs();
            if(activeInning.getInning_balls() == MATCH.getSet_bowl_overs()*6
                   || activeInning.getInning_wickets() == 10 || MATCH.getInningTwo().getInning_runs()>MATCH.inningOne.getInning_runs())
            {
                chngbowler_btn.setDisable(true);
                crossing_btn.setDisable(true);
                NEWBALL_bt.setDisable(false);
                bowling_stats_bt.setDisable(true);
                batting_stats_bt.setDisable(true);
                start_nexti_bt.setDisable(false);
                bottom_label.setText("Innings Ended!");
            }
            
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
        
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            
            if(activeInning instanceof InningTwo){
                ((InningTwo) activeInning).setInning_target(MATCH.inningOne.getInning_runs() - MATCH.inningTwo.getInning_runs());
                bottom_label.setText("Inning 2, Need " + ((InningTwo) activeInning).getInning_target() + " to win from " + ((MATCH.getSet_bowl_overs()*6) - activeInning.getInning_balls()));
            }
            else{
                bottom_label.setText("Inning 1");
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        
        start_nexti_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                activeInning = MATCH.inningTwo;
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/selectOpeningPlayersIn2.fxml"));
                    Parent root1lala = (Parent) fxmlLoader.load();
                    selectOpeningPlayersIn2 sopin2 = fxmlLoader.getController();
                    sopin2.injector(MATCH, ((InningTwo)activeInning));
                    Stage stage = new Stage();
                    stage.setTitle("Select Opening Players for Inning two");
                    stage.setScene(new Scene(root1lala));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR\n" + e);
                }
                updatelabs();
                chngbowler_btn.setDisable(false);
                crossing_btn.setDisable(false);
                NEWBALL_bt.setDisable(false);
                bowling_stats_bt.setDisable(false);
                batting_stats_bt.setDisable(false);
                start_nexti_bt.setDisable(true);
                start_nexti_bt.setText("Finish Game!");
                start_nexti_bt.setOnAction(FinishGame);
            }
        });
        
    }
    EventHandler FinishGame = new EventHandler() {
        @Override
        public void handle(Event event) {
            fiveSecondsWonder.stop();
            UpdateTracker.stop();
            start_nexti_bt.setText("End Game!");
            bowling_stats_bt.setDisable(false);
            batting_stats_bt.setDisable(false);
            
            for (int i=0; i<MATCH.inningOne.getInning_batteam().getBattingMEN().size(); i++){
                ((Batsman)MATCH.inningOne.getInning_batteam().getBattingMEN().get(i).getBatplayer()).setAverage_runs(MATCH.inningOne.getInning_batteam().getBattingMEN().get(i).getRuns());
                ((Batsman)MATCH.inningOne.getInning_batteam().getBattingMEN().get(i).getBatplayer()).setAverage_stkrate(MATCH.inningOne.getInning_batteam().getBattingMEN().get(i).getStrikerate());
            }
            for (int i=0; i<MATCH.inningTwo.getInning_batteam().getBattingMEN().size(); i++){
                ((Batsman)MATCH.inningTwo.getInning_batteam().getBattingMEN().get(i).getBatplayer()).setAverage_runs(MATCH.inningTwo.getInning_batteam().getBattingMEN().get(i).getRuns());
                ((Batsman)MATCH.inningTwo.getInning_batteam().getBattingMEN().get(i).getBatplayer()).setAverage_stkrate(MATCH.inningTwo.getInning_batteam().getBattingMEN().get(i).getStrikerate());
            }
            for (int i = 0; i<MATCH.inningOne.getInning_bowlteam().getBowlingMEN().size(); i++) {
                ((Bowler) MATCH.inningOne.getInning_bowlteam().getBowlingMEN().get(i).getBowlplayer()).setWickets(MATCH.inningOne.getInning_bowlteam().getBowlingMEN().get(i).getWickets());
                ((Bowler) MATCH.inningOne.getInning_bowlteam().getBowlingMEN().get(i).getBowlplayer()).setAverage_economy(MATCH.inningOne.getInning_bowlteam().getBowlingMEN().get(i).getEconomy());
            }
            for (int i = 0; i<MATCH.inningTwo.getInning_bowlteam().getBowlingMEN().size(); i++) {
                ((Bowler) MATCH.inningTwo.getInning_bowlteam().getBowlingMEN().get(i).getBowlplayer()).setWickets(MATCH.inningTwo.getInning_bowlteam().getBowlingMEN().get(i).getWickets());
                ((Bowler) MATCH.inningTwo.getInning_bowlteam().getBowlingMEN().get(i).getBowlplayer()).setAverage_economy(MATCH.inningTwo.getInning_bowlteam().getBowlingMEN().get(i).getEconomy());
            }
            
            if(MATCH.inningOne.getInning_runs() > MATCH.inningTwo.getInning_runs()) {
                bottom_label.setText(MATCH.inningOne.getInning_batteam().getBattingTeam().getTeamname() + " Wins!");
                
                MATCH.inningOne.getInning_batteam().getBattingTeam().setWins(MATCH.inningOne.getInning_batteam().getBattingTeam().getWins()+1);
                MATCH.inningTwo.getInning_batteam().getBattingTeam().setLosses(MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses()+1);
    
                if (MATCH.inningOne.getInning_batteam().getBattingTeam().getLosses() != 0){
                    MATCH.inningOne.getInning_batteam().getBattingTeam().setWintolose(
                        MATCH.inningOne.getInning_batteam().getBattingTeam().getWins()/MATCH.inningOne.getInning_batteam().getBattingTeam().getLosses());
                }
                MATCH.inningTwo.getInning_batteam().getBattingTeam().setLosses(MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses()+1);
                MATCH.inningTwo.getInning_batteam().getBattingTeam().setWintolose(
                       MATCH.inningTwo.getInning_batteam().getBattingTeam().getWins()/MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses());
            }
            
            else{
                bottom_label.setText(MATCH.inningTwo.getInning_batteam().getBattingTeam().getTeamname() + " Wins!");
    
                MATCH.inningTwo.getInning_batteam().getBattingTeam().setWins(MATCH.inningTwo.getInning_batteam().getBattingTeam().getWins()+1);
                MATCH.inningOne.getInning_batteam().getBattingTeam().setLosses(MATCH.inningOne.getInning_batteam().getBattingTeam().getLosses()+1);
    
                if (MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses() != 0){
                    MATCH.inningTwo.getInning_batteam().getBattingTeam().setWintolose(
                           MATCH.inningOne.getInning_batteam().getBattingTeam().getWins()/MATCH.inningOne.getInning_batteam().getBattingTeam().getLosses());
                }
                MATCH.inningOne.getInning_batteam().getBattingTeam().setLosses(MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses()+1);
                MATCH.inningOne.getInning_batteam().getBattingTeam().setWintolose(
                       MATCH.inningTwo.getInning_batteam().getBattingTeam().getWins()/MATCH.inningTwo.getInning_batteam().getBattingTeam().getLosses());
            }
            
            
            start_nexti_bt.setText("SAVE & CLOSE");
            start_nexti_bt.setOnAction(closegame);
        }
    };
    
    EventHandler closegame = new EventHandler() {
        @Override
        public void handle(Event event) {
            Stage stage = (Stage) batting_stats_bt.getScene().getWindow();
            stage.close();
        }
    };
    
    public void updatelabs(){
        battm_label     .setText(this.activeInning.getInning_batteam().getBattingTeam().getTeamname());
        Match_Stat_label.setText(this.activeInning.getInning_runs() +"/"+ this.activeInning.getInning_wickets());
        Over_label      .setText((this.activeInning.getInning_balls()/6)+"."+(this.activeInning.getInning_balls()%6));
        
        //striker
        inat1_label.setText(MATCH.striker.getId()+"");
        batsman1_label.setText(MATCH.striker.getName());
        batsman1runs_label.setText(MATCH.striker.getRuns()+" (" + MATCH.striker.getBalls() + ")");
        double strs = 0;
        if(MATCH.striker.getBalls()!=6){
            strs = (MATCH.striker.getRuns()/((MATCH.striker.getBalls()/6)+1));
        }
        MATCH.striker.setStrikerate(strs);
        sr1_label.setText(MATCH.striker.getStrikerate()+"");
        
        //runner
        inat2_label.setText(MATCH.runner.getId()+"");
        batsman2_label.setText(MATCH.runner.getName());
        batsman2runs_label.setText(MATCH.runner.getRuns() + " (" + MATCH.runner.getBalls() + ")");
        double strr = 0;
        if(MATCH.striker.getBalls()!=0){
            strr = (MATCH.runner.getRuns()/((MATCH.runner.getBalls()/6)+1));
        }
        MATCH.runner.setStrikerate(strr);
        sr2_label.setText(""+MATCH.runner.getStrikerate());
        
        //Bowler
        bowler_label.setText(MATCH.baller.getName());
        bowler_overs_label.setText((MATCH.baller.getBalls()/6) + "." + (MATCH.baller.getBalls()%6));
        bowler_runs_label.setText(MATCH.baller.getRuns()+" - " + MATCH.baller.getWickets());
        bowler_economy_label.setText(MATCH.baller.getEconomy()+"");
        
        
    }
    public void crossing(){
        BattingMan temp = new BattingMan();
        temp = MATCH.striker;
        MATCH.striker = MATCH.runner;
        MATCH.runner = temp;
        updatelabs();
    }
    public void injector(Match match) {
        this.MATCH = match;
        this.activeInning = MATCH.inningOne;
        updatelabs();
        start_nexti_bt.setDisable(true);
        TEAM1_vs_TEAM2.setText(MATCH.team1.getTeamname() + " VS " + MATCH.team2.getTeamname());
    }
}
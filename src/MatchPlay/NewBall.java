package MatchPlay;

import Match.*;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewBall implements Initializable {
    
    public Match MATCH;
    public InningOne InjectedInning;
    public BattingTeam battingTeam;
    
    
    public JFXButton run_0,run_1, run_2, run_3,run_4,run_6;
    public JFXButton ball_wide, ball_no, ball_runout, ball_catch, ball_wicket;
    public JFXButton newball_ok, newball_cancel;
    public JFXButton e1,e2,e3,e4,e5,e6;
    
    @FXML private Label bottom_label;
    public int total_runs = 0;
    
    public NewBall(){}
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableEx();
        run_0.setOnAction(event -> {
            validballvalidrun(0); newball_cancel.setText("Close");
        });
        run_1.setOnAction(event -> {
            validballvalidrun(1); newball_cancel.setText("Close"); crossing();
        });
        run_2.setOnAction(event -> {
            validballvalidrun(2); newball_cancel.setText("Close");
        });
        run_3.setOnAction(event -> {
            validballvalidrun(3); newball_cancel.setText("Close"); crossing();
        });
        run_4.setOnAction(event -> {
            validballvalidrun(4); newball_cancel.setText("Close");
            
        });
        run_6.setOnAction(event -> {
            validballvalidrun(6); newball_cancel.setText("Close");
            
        });
        
        e1.setOnAction(event -> extrarun(1));
        e2.setOnAction(event -> extrarun(2));
        e3.setOnAction(event -> extrarun(3));
        e4.setOnAction(event -> extrarun(4));
        e5.setOnAction(event -> extrarun(5));
        e6.setOnAction(event -> extrarun(6));
        
        ball_wide.setOnAction   (event -> {
            invalidbowl(); newball_cancel.setText("Close");
        });
        ball_no.setOnAction     (event -> {
            invalidbowl();  newball_cancel.setText("Close");
        });
        ball_catch.setOnAction  (event -> {
            batsmanout();
            MATCH.baller.setBalls(MATCH.baller.getBalls()+1);
            MATCH.baller.setWickets(MATCH.baller.getWickets()+1);
            newball_cancel.setText("Close");
        });
        ball_runout.setOnAction (event -> {
            batsmanout();
            enableEx();
            newball_cancel.setText("Close");
        });
        ball_wicket.setOnAction (event -> {
            batsmanout();
            MATCH.baller.setBalls(MATCH.baller.getBalls()+1);
            MATCH.baller.setWickets(MATCH.baller.getWickets()+1);
            
            newball_cancel.setText("Close");
            
        });
        newball_ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeButtonAction();
            }
        });
        newball_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeButtonAction();
            }
        });
        print("" + total_runs);
    }
    
    public void in1inject(Match MATCH, InningOne inningOne, BattingTeam battingTeam){
        this.MATCH = MATCH;
        this.InjectedInning = inningOne;
        this.battingTeam = battingTeam;
        
        
    }
    @FXML
    private void closeButtonAction() { Stage stage = (Stage) newball_cancel.getScene().getWindow();stage.close(); }
    public void print(String x){ bottom_label.setText(x); }
    public void disablebowloptions(){
        ball_wide.setDisable(true);
        ball_no.setDisable(true);
        ball_runout.setDisable(true);
        ball_catch.setDisable(true);
        ball_wicket.setDisable(true);
    }
    public void disableruns(){
        run_0.setDisable(true);
        run_1.setDisable(true);
        run_2.setDisable(true);
        run_3.setDisable(true);
        run_4.setDisable(true);
        run_6.setDisable(true);
    }
    public void disableEx(){
        e1.setDisable(true);
        e2.setDisable(true);
        e3.setDisable(true);
        e4.setDisable(true);
        e5.setDisable(true);
        e6.setDisable(true);
    }
    public void enableEx(){
        e1.setDisable(false);
        e2.setDisable(false);
        e3.setDisable(false);
        e4.setDisable(false);
        e5.setDisable(false);
        e6.setDisable(false);
    }
    public void getOutPrompt(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/selectBatsman.fxml"));
        Parent root1lala = null;
        try {
            root1lala = (Parent) fxmlLoader.load();
        }catch (IOException e) {
            e.printStackTrace();
        }
        selectBatsman selectBatsman = fxmlLoader.getController();
        selectBatsman.inject(battingTeam, MATCH);
        Stage stage = new Stage();
        stage.setTitle("Dismiss Prompt");
        stage.setScene(new Scene(root1lala));
        stage.show();
    }
    
    public void crossing(){
        BattingMan temp = MATCH.striker;
        MATCH.striker = MATCH.runner;
        MATCH.runner = temp;
    }
    
    public void validballvalidrun(int x){
        total_runs+=x;
        print(total_runs + " runs on this ball");
        MATCH.striker.setRuns(MATCH.striker.getRuns()+x);
        MATCH.striker.setBalls(MATCH.striker.getBalls()+1);
        MATCH.baller.setRuns(MATCH.baller.getRuns()+x);
        MATCH.baller.setBalls(MATCH.baller.getBalls()+1);
        InjectedInning.setInning_balls(InjectedInning.getInning_balls() + 1);
        InjectedInning.setInning_runs(InjectedInning.getInning_runs() + total_runs);
        disablebowloptions();
        disableruns();
    }
    public void invalidbowl(){
         total_runs+=1;
        InjectedInning.setInning_runs(InjectedInning.getInning_runs() + total_runs);
         print(total_runs + " runs on this ball");
         MATCH.baller.setRuns(MATCH.baller.getRuns()+1);
         enableEx();
         disablebowloptions();
         disableruns();
         
    }
    public void batsmanout(){
        getOutPrompt();
        InjectedInning.setInning_wickets(InjectedInning.getInning_wickets()+1);
        InjectedInning.setInning_balls(InjectedInning.getInning_balls()+1);
        disablebowloptions();
        disableruns();
    }
    public void extrarun(int x){
        total_runs+=x;
        print(total_runs + " runs on this ball");
        MATCH.baller.setRuns(MATCH.baller.getRuns()+x);
        InjectedInning.setInning_runs(InjectedInning.getInning_runs() + total_runs);
    }
    
}

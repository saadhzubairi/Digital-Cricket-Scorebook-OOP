package MatchPlay;

import Match.BattingMan;
import Match.BattingTeam;
import Match.Match;
import PlayerAdder.Player;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.paint.MaterialHelper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class selectBatsman implements Initializable {
    
    public BattingTeam battingTeam;
    public Match MATCH;
    
    public TableView<BattingMan> bat1_table;
    public TableColumn<String, Player> PlayerID_col_bt1;
    public TableColumn<String, Player> Name_col_bt1;
    public JFXButton striker_bt;
    public JFXButton runner_bt;
    
    public selectBatsman() {}
    
    public void inject(BattingTeam battingTeam, Match MATCH) {
        this.battingTeam = battingTeam;
        this.MATCH = MATCH;
        //Batting2 Table
        bat1_table.setItems(this.battingTeam.getBattingMEN());
        PlayerID_col_bt1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col_bt1.setCellValueFactory(new PropertyValueFactory<>("name"));
        striker_bt.setText(MATCH.getStriker().getName());
        runner_bt.setText(MATCH.getRunner().getName());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            if(bat1_table.getSelectionModel().getSelectedItem() == MATCH.striker || bat1_table.getSelectionModel().getSelectedItem() == MATCH.runner
            || bat1_table.getSelectionModel().getSelectedItem() == null)
            {
                striker_bt.setDisable(true);
                runner_bt.setDisable(true);
            }
            else{
                striker_bt.setDisable(false);
                runner_bt.setDisable(false);
            }
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
        
        
        striker_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MATCH.getStriker().setOut_or_not(true);
                MATCH.setStriker(bat1_table.getSelectionModel().getSelectedItem());
                closeButtonAction();
            }
        });
        runner_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MATCH.getRunner().setOut_or_not(true);
                MATCH.setRunner(bat1_table.getSelectionModel().getSelectedItem());
                closeButtonAction();
            }
        });
    }
    
    public void closeButtonAction() {
        Stage stage = (Stage) bat1_table.getScene().getWindow();
        stage.close();
    }
}

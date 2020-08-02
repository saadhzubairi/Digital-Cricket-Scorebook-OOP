package MatchPlay;

import Match.Match;
import PlayerAdder.Player;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Match.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class selectOpeningPlayers implements Initializable {
    public Match MATCH;
    
    @FXML private Label Striker;
    @FXML private TableView<BattingMan> bat1_table;
    @FXML private TableColumn<String, Player> PlayerID_col_bt1, Name_col_bt1;
    
    @FXML private Label Runner;
    @FXML private TableView<BattingMan> bat2_table;
    @FXML private TableColumn<String, Player> PlayerID_col1_bt2, Name_col1_bt2;
    
    @FXML private TableView<BowlingMan> bowl_table;
    @FXML private TableColumn<String, Player> PlayerID_col2_bowl, Name_col2_bowl;
    
    @FXML private JFXButton OK_bt;
    @FXML private Label bottom_label;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        
            if(bat1_table.getSelectionModel().getSelectedItem() == null || bat2_table.getSelectionModel().getSelectedItem() == null || bowl_table.getSelectionModel().getSelectedItem() == null){
                OK_bt.setDisable(true);
                bottom_label.setText("Select Opening Batsmen and Bowlers");
            }
            else if(!(bat1_table.getSelectionModel().getSelectedItem() == bat2_table.getSelectionModel().getSelectedItem())) {
                OK_bt.setDisable(false);
                bottom_label.setText("Good to go! Press OK");
            }
            else if((bat1_table.getSelectionModel().getSelectedItem() == bat2_table.getSelectionModel().getSelectedItem())){
                OK_bt.setDisable(true);
                bottom_label.setText("Select Diffrent Batsmen Please");
            }
        
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
        
        OK_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MATCH.setStriker(bat1_table.getSelectionModel().getSelectedItem());
                MATCH.setRunner (bat2_table.getSelectionModel().getSelectedItem());
                MATCH.setBaller (bowl_table.getSelectionModel().getSelectedItem());
                if(MATCH.getStriker() == MATCH.getRunner()){
                    Striker .setText("Selection not appt");
                    Runner  .setText("Selection not appt");
                }
                else{
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/AMATCHMAIN.fxml"));
                        Parent root1lala = (Parent) fxmlLoader.load();
                        AMATCHMAIN amatchmain = fxmlLoader.getController();
                        amatchmain.injector(MATCH);
                        Stage stage = new Stage();
                        stage.setTitle(MATCH.team1.getTeamname() + " VS " + MATCH.team2.getTeamname());
                        stage.setScene(new Scene(root1lala));
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                    }
                    catch (Exception e){
                        bottom_label.setText("ERROR" + e);
                        e.printStackTrace();
                        System.out.println(e);
                    }
                    closeButtonAction();
                }
            }
        });
    }
    
    public void injector(Match MATCH) {
        this.MATCH = MATCH;
        
        //Batting1 Table
        bat1_table.setItems(this.MATCH.inningOne.getInning_batteam().getBattingMEN());
    
            PlayerID_col_bt1.setCellValueFactory(new PropertyValueFactory<>("id"));
            Name_col_bt1.setCellValueFactory(new PropertyValueFactory<>("name"));
            
        //Batting2 Table
        bat2_table.setItems(this.MATCH.inningOne.getInning_batteam().getBattingMEN());
        
            PlayerID_col1_bt2.setCellValueFactory(new PropertyValueFactory<>("id"));
            Name_col1_bt2.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        //Bowling Table
        bowl_table.setItems(this.MATCH.inningOne.getInning_bowlteam().getBowlingMEN());
        
            PlayerID_col2_bowl.setCellValueFactory(new PropertyValueFactory<>("id"));
            Name_col2_bowl.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) OK_bt.getScene().getWindow();
        stage.close();
    }
}

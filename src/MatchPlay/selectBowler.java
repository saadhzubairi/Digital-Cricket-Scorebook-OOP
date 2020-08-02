package MatchPlay;

import Match.*;
import PlayerAdder.Player;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.paint.MaterialHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class selectBowler implements Initializable {
    public TableView<BowlingMan> bowl_table;
    public TableColumn<String, Player> PlayerID_col_bowl;
    public TableColumn<String, Player> Name_col_bowl;
    public JFXButton OK_bt;
    public BowlingTeam bowlingTeam;
    public Label Bowler;
    
    public Match MATCH;
    
    public selectBowler(){}
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OK_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BowlingMan sel = bowl_table.getSelectionModel().getSelectedItem();
                if(sel == MATCH.baller){
                    Bowler.setText("Con. Overs N/A");
                }
                else{
                    MATCH.baller = sel;
                    closeButtonAction();
                }
            }
        });
    }
    
    public void injector(Match MATCH, BowlingTeam bowlingTeam){
        this.MATCH = MATCH;
        this.bowlingTeam = bowlingTeam;
        bowl_table.setItems(this.bowlingTeam.getBowlingMEN());
        PlayerID_col_bowl.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col_bowl.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    private void closeButtonAction() { Stage stage = (Stage) OK_bt.getScene().getWindow();stage.close(); }
}

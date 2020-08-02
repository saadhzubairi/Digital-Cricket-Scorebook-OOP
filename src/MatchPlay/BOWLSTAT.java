package MatchPlay;

import Match.BowlingMan;
import Match.InningOne;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BOWLSTAT implements Initializable {
    public InningOne inningin;
    @FXML private TableView<BowlingMan> bat1_table;
    @FXML private TableColumn<Object, Object> ID_col, Name_col, overs_col, runs_col, wickets_col, eco_column;
    @FXML private JFXButton closer;
    
    public void injector(InningOne in) {
        this.inningin = in;
        bat1_table.setItems(this.inningin.getInning_bowlteam().getBowlingMEN());
        ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        overs_col.setCellValueFactory(new PropertyValueFactory<>("overs"));
        runs_col.setCellValueFactory(new PropertyValueFactory<>("runs"));
        wickets_col.setCellValueFactory(new PropertyValueFactory<>("wickets"));
        eco_column.setCellValueFactory(new PropertyValueFactory<>("economy"));
    }
    
    @FXML private void closeButtonAction() {
        Stage stage = (Stage) closer.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closer.setOnAction(event -> closeButtonAction());
    }
}

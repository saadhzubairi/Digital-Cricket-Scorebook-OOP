package MatchPlay;

import Match.BattingMan;
import Match.InningOne;
import Match.InningTwo;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class BATSTAT implements Initializable {
    @FXML private TableView<BattingMan> bat1_table;
    @FXML private TableColumn<Object, Object> ID_col, Name_col, Rims_col, Balls_col, fours_col, sixes_col, sr_col;
    @FXML private JFXButton closer;
    
    public InningOne inningin;
    
    public void injector(InningOne in){
        this.inningin = in;
        bat1_table.setItems(inningin.getInning_batteam().getBattingMEN());
        ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        Rims_col.setCellValueFactory(new PropertyValueFactory<>("runs"));
        Balls_col.setCellValueFactory(new PropertyValueFactory<>("balls"));
        fours_col.setCellValueFactory(new PropertyValueFactory<>("fours"));
        sixes_col.setCellValueFactory(new PropertyValueFactory<>("sixes"));
        sr_col.setCellValueFactory(new PropertyValueFactory<>(""+"sr"));
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

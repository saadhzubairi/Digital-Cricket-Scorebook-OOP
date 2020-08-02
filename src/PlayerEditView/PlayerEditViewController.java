package PlayerEditView;

import PlayerAdder.AllRounder;
import PlayerAdder.Batsman;
import PlayerAdder.Bowler;
import PlayerAdder.Player;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import PlayerAdder.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerEditViewController implements Initializable {
    @FXML private JFXButton closer;
    @FXML private JFXTextField name_tf;
    @FXML private JFXComboBox<String> age_comb;
    @FXML private JFXComboBox<String> department_comb;
    
    @FXML private JFXButton AddBatsman_bt;
    @FXML private JFXComboBox<String> bat_bathand;
    @FXML private JFXComboBox<String> bat_batstyle;
    @FXML private JFXToggleButton isKeeper;
    
    @FXML private JFXButton AddBowler_bt;
    @FXML private JFXComboBox<String> bowl_bathand;
    @FXML private JFXComboBox<String> bowl_batstyle;
    @FXML private JFXComboBox<String> bowl_bowlhand;
    @FXML private JFXComboBox<String> bowl_bowlstyle;
    
    @FXML private JFXButton AddAllrounder_bt;
    @FXML private JFXComboBox<String> ar_bathand;
    @FXML private JFXComboBox<String> ar_batstyle;
    @FXML private JFXComboBox<String> ar_bowlhand;
    @FXML private JFXComboBox<String> ar_bowlstyle;
    
    @FXML private Label bottom_label;
    @FXML private Label runsavg_lab;
    @FXML private Label strrate_label;
    @FXML private Label wicks_label;
    @FXML private Label avgeconomy_label;
    
    @FXML private JFXButton f5;
    
    public Player PlayerEdit;
    public ObservableList<Player> players_list = FXCollections.observableArrayList();
    public int injectablei;
    
    ObservableList<String> departments = FXCollections.observableArrayList("BBA", "BSAF", "BSSLA", "BSECO", "BSEM","BSCS", "MS", "MBA", "PHD");
    ObservableList<String> ages = FXCollections.observableArrayList("15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49");
    ObservableList<String> hands = FXCollections.observableArrayList("RIGHT", "LEFT");
    ObservableList<String> battingstyles = FXCollections.observableArrayList("AGGRESSIVE", "MODERATE", "DEFENSIVE");
    ObservableList<String> bowlingstyles = FXCollections.observableArrayList("SEAM", "SPIN", "SWING");
    
    public void injector(Player PlayerEdit, int injectablei, ObservableList<Player> players_list){
        this.PlayerEdit = PlayerEdit;
        this.players_list = players_list;
        this.injectablei = injectablei;
        try {
            age_comb.       setItems(ages         );
            department_comb.setItems(departments  );
            bat_bathand.    setItems(hands        );
            bowl_bathand.   setItems(hands        );
            bowl_bowlhand.  setItems(hands        );
            ar_bathand.     setItems(hands        );
            ar_bowlhand.    setItems(hands        );
            bat_batstyle.   setItems(battingstyles);
            bowl_batstyle.  setItems(battingstyles);
            ar_batstyle.    setItems(battingstyles);
            bowl_bowlstyle. setItems(bowlingstyles);
            ar_bowlstyle.   setItems(bowlingstyles);
            name_tf.setText(PlayerEdit.getName());
            age_comb.setValue(PlayerEdit.getAge());
            department_comb.setValue(PlayerEdit.getDepartment());
            
            if(PlayerEdit.getFormat().equals("Batsman")){
                bat_bathand.setValue(((Batsman) PlayerEdit).getBat_hand());
                bat_batstyle.setValue(((Batsman) PlayerEdit).getBat_mod());
                runsavg_lab.setText(""+((Batsman) PlayerEdit).getAverage_runs());
                strrate_label.setText(""+((Batsman) PlayerEdit).getAverage_stkrate());
                wicks_label.setText("N/A");
                avgeconomy_label.setText("N/A");
                isKeeper.setFocusTraversable((((Batsman) PlayerEdit).iskeeper));
            }
            else if (PlayerEdit.getFormat().equals("Bowler")){
                bowl_bathand.setValue(((Bowler) PlayerEdit).getBat_hand());
                bowl_batstyle.setValue(((Bowler) PlayerEdit).getBat_mod());
                bowl_bowlhand.setValue(((Bowler) PlayerEdit).getBowl_hand());
                bowl_bowlstyle.setValue(((Bowler) PlayerEdit).getBowl_mod());
                runsavg_lab.setText(""+((Bowler) PlayerEdit).getAverage_runs());
                strrate_label.setText(""+((Bowler) PlayerEdit).getAverage_stkrate());
                wicks_label.setText(""+((Bowler) PlayerEdit).getWickets());
                avgeconomy_label.setText(""+((Bowler) PlayerEdit).getAverage_economy());
            }
            else{
                ar_bathand.setValue(((Bowler) PlayerEdit).getBat_hand());
                ar_batstyle.setValue(((Bowler) PlayerEdit).getBat_mod());
                ar_bowlhand.setValue(((Bowler) PlayerEdit).getBowl_hand());
                ar_bowlstyle.setValue(((Bowler) PlayerEdit).getBowl_mod());
                runsavg_lab.setText(""+((Bowler) PlayerEdit).getAverage_runs());
                strrate_label.setText(""+((Bowler) PlayerEdit).getAverage_stkrate());
                wicks_label.setText(""+((Bowler) PlayerEdit).getWickets());
                avgeconomy_label.setText(""+((Bowler) PlayerEdit).getAverage_economy());
            }
        }
        catch (Exception e){
            System.out.println("ERROR");
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddBatsman_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player = new Batsman(PlayerEdit.getId(), name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "Batsman", bat_bathand.getValue(),
                       bat_batstyle.getValue(), isKeeper.isFocused(), ((Batsman) PlayerEdit).getAverage_runs(), ((Batsman) PlayerEdit).getAverage_stkrate());
                players_list.remove(injectablei);
                players_list.add(injectablei, player);
                Stage stage = (Stage) closer.getScene().getWindow();
                stage.close();
            }
        });
        AddBowler_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player;
                if(PlayerEdit.getFormat().replaceAll("\\s","").equals("Batsman") ){
                    player = new Bowler(PlayerEdit.getId(), name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "Bowler",
                           bowl_bathand.getValue(), bowl_batstyle.getValue(), false, ((Batsman) PlayerEdit).getAverage_runs(), ((Batsman) PlayerEdit).getAverage_stkrate(),
                           bowl_bowlhand.getValue(), bowl_bowlstyle.getValue(), 0, 0);
                }
                else{
                    player = new Bowler(PlayerEdit.getId(), name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "Bowler",
                           bowl_bathand.getValue(), bowl_batstyle.getValue(), false, ((Batsman) PlayerEdit).getAverage_runs(), ((Batsman) PlayerEdit).getAverage_stkrate(),
                           bowl_bowlhand.getValue(), bowl_bowlstyle.getValue(), ((Bowler) PlayerEdit).getWickets(), ((Bowler) PlayerEdit).getAverage_economy());
                }
                players_list.remove(injectablei);
                players_list.add(injectablei, player);
                Stage stage = (Stage) closer.getScene().getWindow();
                stage.close();
            }
        });
        AddAllrounder_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player;
                if(PlayerEdit.getFormat().replaceAll("\\s","").equals("Batsman")){
                    player = new AllRounder(
                           PlayerEdit.getId(), name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "All Rounder",
                           ar_bathand.getValue(), ar_batstyle.getValue(), false, ((Batsman) PlayerEdit).getAverage_runs(), ((Batsman) PlayerEdit).getAverage_stkrate(),
                           ar_bowlhand.getValue(), ar_bowlstyle.getValue(), 0, 0.0);
                }
                else{
                    player = new AllRounder(
                           PlayerEdit.getId(), name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "All Rounder",
                           ar_bathand.getValue(), ar_batstyle.getValue(), false, ((Bowler) PlayerEdit).getAverage_runs(), ((Bowler) PlayerEdit).getAverage_stkrate(),
                           ar_bowlhand.getValue(), ar_bowlstyle.getValue(), ((Bowler) PlayerEdit).getWickets(), ((Bowler) PlayerEdit).getAverage_economy());
                }
                players_list.remove(injectablei);
                players_list.add(injectablei, player);
                Stage stage = (Stage) closer.getScene().getWindow();
                stage.close();
            }
            
        });
    }
    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closer.getScene().getWindow();
        stage.close();
    }
}

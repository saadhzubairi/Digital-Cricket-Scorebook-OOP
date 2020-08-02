package PlayerAdder;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerAdderController implements Initializable {
    
    @FXML private JFXButton canc1, canc2, canc3;
    @FXML private JFXTextField name_tf;
    @FXML private JFXComboBox<String> age_comb, department_comb;
    @FXML private JFXButton AddBatsman_bt;
    @FXML private JFXComboBox<String> bat_bathand, bat_batstyle;
    @FXML private JFXToggleButton isKeeper;
    @FXML private JFXButton AddBowler_bt;
    @FXML private JFXComboBox<String> bowl_bathand,bowl_batstyle,bowl_bowlhand,bowl_bowlstyle;
    @FXML private JFXButton AddAllrounder_bt;
    @FXML private JFXComboBox<String> ar_bathand, ar_batstyle, ar_bowlhand, ar_bowlstyle;
    
    @FXML private Label bottom_label;
    
    ObservableList<String> departments = FXCollections.observableArrayList("BBA", "BSAF", "BSSLA", "BSECO", "BSEM","BSCS", "MS", "MBA", "PHD");
    ObservableList<String> ages = FXCollections.observableArrayList("15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49");
    ObservableList<String> hands = FXCollections.observableArrayList("RIGHT", "LEFT");
    ObservableList<String> battingstyles = FXCollections.observableArrayList("AGGRESSIVE", "MODERATE", "DEFENSIVE");
    ObservableList<String> bowlingstyles = FXCollections.observableArrayList("SEAM", "SPIN", "SWING");
    
    //here's the injected list!
    public ObservableList<Player> playerlist = FXCollections.observableArrayList();
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    
    public void playerlistinjector(ObservableList<Player> playerlist, ArrayList<Integer> ID_Standard){
        this.playerlist = playerlist;
        this.ID_Standard = ID_Standard;
    }
    
    public PlayerAdderController() {    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        
        AddBatsman_bt.setOnAction(event -> {
            Player player = new Batsman(ID_Standard.size()+7337, name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "Batsman", bat_bathand.getValue(),
                   bat_batstyle.getValue(), isKeeper.isFocused(), 0, 0.0);
            playerlist.add(player);
            ID_Standard.add(1);
            clearfields();
        });
        AddBowler_bt.setOnAction(event -> {
            Player player = new Bowler(ID_Standard.size()+7337, name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "Bowler",
                   bowl_bathand.getValue(), bowl_batstyle.getValue(), false, 0, 0.0,
                   bowl_bowlhand.getValue(), bowl_bowlstyle.getValue(), 0, 0.0);
            playerlist.add(player);
            ID_Standard.add(1);
            clearfields();
        });
        AddAllrounder_bt.setOnAction(event -> {
            Player player = new Bowler(
                   ID_Standard.size()+7337, name_tf.getText(), department_comb.getValue(), age_comb.getValue(), "All Rounder",
                   ar_bathand.getValue(), ar_batstyle.getValue(), false, 0, 0.0,
                   ar_bowlhand.getValue(), ar_bowlstyle.getValue(), 0, 0.0);
            ID_Standard.add(1);
            playerlist.add(player);
            clearfields();
            
        });
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) canc1.getScene().getWindow();
        stage.close();
    }
    
    private void clearfields(){
        bottom_label    .setText("Player Added, Add more players or close");
        canc1           .setText("Go Back");
        canc2           .setText("Go Back");
        canc3           .setText("Go Back");
        AddAllrounder_bt.setText("Add More");
        AddBowler_bt    .setText("Add More");
        AddBatsman_bt   .setText("Add More");
        name_tf         .setText("");
        age_comb        .setValue("");
        department_comb .setValue("");
        bat_bathand     .setValue("");
        bat_batstyle    .setValue("");
        isKeeper        .setFocusTraversable(false);
        bowl_bathand    .setValue("");
        bowl_batstyle   .setValue("");
        bowl_bowlhand   .setValue("");
        bowl_bowlstyle  .setValue("");
        ar_bathand      .setValue("");
        ar_batstyle     .setValue("");
        ar_bowlhand     .setValue("");
        ar_bowlstyle    .setValue("");
    }
    
}

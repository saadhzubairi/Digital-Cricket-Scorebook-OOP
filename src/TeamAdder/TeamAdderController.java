package TeamAdder;

import PlayerAdder.Player;
import PlayerAdder.PlayerAdderController;
import PlayerEditView.PlayerEditViewController;
import TeamsView.Team;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TeamAdderController implements Initializable {
    
    public JFXTextField TeamName_tf;
    
    public TableView<Player> PlayersTable;
    public TableColumn<String, Player> PlayerID_col;
    public TableColumn<String, Player> Name_col;
    public TableColumn<String, Player> Age_col;
    public TableColumn<String, Player> Department_col;
    public TableColumn<String, Player> Format_col;
    
    public JFXButton EditViewPlayer_bt, AddPlayer_bt, DeletePlayer_bt;
    public JFXButton SaveTeam_bt, Cancel_bt;
    
    public ObservableList<Team> TEAM_LIST = FXCollections.observableArrayList();
    public ObservableList<Player> players_list = FXCollections.observableArrayList();
    public Label downlabel;
    public JFXButton setcap_bt;
    public Player Captain = new Player();
    
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    
    public void teamlistinjector(ObservableList<Team> teamlist, ArrayList<Integer> ID_Standard){
        this.TEAM_LIST      = teamlist;
        this.ID_Standard    = ID_Standard;
    }
    public TeamAdderController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        
            if(PlayersTable.getSelectionModel().getSelectedItem() == null){
                EditViewPlayer_bt.setDisable(true);
                DeletePlayer_bt.setDisable(true);
                setcap_bt.setDisable(true);
            }
            else {
                EditViewPlayer_bt.setDisable(false);
                DeletePlayer_bt.setDisable(false);
                setcap_bt.setDisable(false);
            }
        
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
        
        PlayersTable    .setItems(players_list);
        PlayerID_col    .setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col        .setCellValueFactory(new PropertyValueFactory<>("name"));
        Age_col         .setCellValueFactory(new PropertyValueFactory<>("department"));
        Department_col  .setCellValueFactory(new PropertyValueFactory<>("age"));
        Format_col      .setCellValueFactory(new PropertyValueFactory<>("format"));
        
        setcap_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Captain = PlayersTable.getSelectionModel().getSelectedItem();
                downlabel.setText("Captain Set as : " + Captain.getName());
            }
        });
        
        AddPlayer_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PlayerAdder/PlayerAdder.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    PlayerAdderController playerAdderController = fxmlLoader.getController();
                    playerAdderController.playerlistinjector(players_list, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Add Player");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }catch (Exception e) {
                    System.out.println("ERROR LOADING WINDOW\n" + e);
                }
            }
        });
        EditViewPlayer_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PlayerEditView/PlayerEditView.fxml"));
                    Parent root2 = (Parent) fxmlLoader.load();
                    Player injectableP = PlayersTable.getSelectionModel().getSelectedItem();
                    int injectablei = PlayersTable.getSelectionModel().getSelectedIndex();
                    ObservableList<Player> injectablel = players_list;
                    PlayerEditViewController playerEditView = fxmlLoader.getController();
                    playerEditView.injector(injectableP, injectablei, injectablel);
                    Stage stage = new Stage();
                    stage.setTitle("lala");
                    stage.setScene(new Scene(root2));
                    stage.show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        DeletePlayer_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayersTable.getItems().removeAll(PlayersTable.getSelectionModel().getSelectedItems());
            }
        });
        
        SaveTeam_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TEAM_LIST.add(new Team("" + (ID_Standard.size()+178), TeamName_tf.getText(), players_list, Captain));
                Cancel_bt.setText("Close");
                SaveTeam_bt.setDisable(true);
                setcap_bt.setDisable(true);
                downlabel.setText("Team Saved - Close window, or view players");
                ID_Standard.add(6);
                AddPlayer_bt.setDisable(true);
                DeletePlayer_bt.setDisable(true);
            }
        });
        Cancel_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) Cancel_bt.getScene().getWindow();
                stage.close();
            }
        });
        
    }
    
    public void closefunc(){
        Stage stage = (Stage) Cancel_bt.getScene().getWindow();
        stage.close();
    }
}

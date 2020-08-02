package TeamEditView;

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
import javafx.fxml.FXML;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamEditView implements Initializable {
    //Labels of stats
    @FXML private Label draws_count;
    @FXML private Label loss_count;
    @FXML private Label wins_count;
    @FXML private Label match_count;
    @FXML private Label wl_count;
    @FXML private Label downlabel;
    
    public JFXTextField TeamName_tf = new JFXTextField("");
    
    public TableView<Player> PlayersTable;
    public TableColumn<String, Player> PlayerID_col;
    public TableColumn<String, Player> Name_col;
    public TableColumn<String, Player> Age_col;
    public TableColumn<String, Player> Department_col;
    public TableColumn<String, Player> Format_col;
    
    public JFXButton EditViewPlayer_bt, AddPlayer_bt, DeletePlayer_bt;
    public JFXButton SaveTeam_bt, Cancel_bt;
    
    public ObservableList<Team> teamslist = FXCollections.observableArrayList();
    public ObservableList<Player> players_list = FXCollections.observableArrayList();
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    public JFXButton setcap_bt;
    @FXML public JFXButton f5;
    
    public Player Captain = new Player();
    public Team TeamtobeEdited = new Team();
    public int indexOfEditteam;
    
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
        
        
        f5.setDefaultButton(true);
        EditViewPlayer_bt.setDisable(true);
        AddPlayer_bt.setDisable(true);DeletePlayer_bt.setDisable(true);SaveTeam_bt.setDisable(true);
        Cancel_bt.setDisable(true);setcap_bt.setDisable(true);TeamName_tf.setDisable(true);
        TeamName_tf.setText("Please Refresh to show information");
        f5.setOnAction(event -> {
            TeamName_tf.setText(TeamtobeEdited.getTeamname());
            PlayersTable.setItems(players_list);
            PlayerID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            Name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            Age_col.setCellValueFactory(new PropertyValueFactory<>("department"));
            Department_col.setCellValueFactory(new PropertyValueFactory<>("age"));
            Format_col.setCellValueFactory(new PropertyValueFactory<>("format"));
            TeamName_tf.setDisable(false);
            EditViewPlayer_bt.setDisable(false);
            AddPlayer_bt.setDisable(false);
            DeletePlayer_bt.setDisable(false);
            SaveTeam_bt.setDisable(false);
            Cancel_bt.setDisable(false);
            setcap_bt.setDisable(false);
            draws_count.setText(""+TeamtobeEdited.getDraws());
            loss_count.setText(""+TeamtobeEdited.getLosses());
            wins_count.setText(""+TeamtobeEdited.getWins());
            match_count.setText(""+""+TeamtobeEdited.getMatches());
            wl_count.setText(""+TeamtobeEdited.getWintolose());
            downlabel.setText("Systems Refreshed, Info Updated!");
        });
        
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
                    System.out.println("ERROR LOADING WINDOW");
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
            public void handle(ActionEvent event)
            {
                teamslist.remove(indexOfEditteam);
                Team editedteam = new Team(TeamtobeEdited.getId(), TeamName_tf.getText(), TeamtobeEdited.getWintolose(),
                       TeamtobeEdited.getMatches(), Captain, players_list.size(), players_list,
                       TeamtobeEdited.getWins(), TeamtobeEdited.getLosses(), TeamtobeEdited.getDraws());
                teamslist.add(indexOfEditteam, editedteam);
                downlabel.setText("Team Saved!");
                Cancel_bt.setText("Close");
            }
        });
        Cancel_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) Cancel_bt.getScene().getWindow();
                stage.close();
            }
        });
    
        EditViewPlayer_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try{
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
                catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });
    }
    
    public void teamlistinjector(ObservableList<Team> teamslist, Team injectteam, int injectindex, ArrayList<Integer> ID_Standard) {
        this.teamslist = teamslist;
        this.TeamtobeEdited = injectteam;
        this.indexOfEditteam = injectindex;
        players_list = injectteam.players;
        Captain = injectteam.captain;
        this.ID_Standard = ID_Standard;
        TeamName_tf.setText(TeamtobeEdited.getTeamname());
        PlayersTable.setItems(players_list);
        PlayerID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        Age_col.setCellValueFactory(new PropertyValueFactory<>("department"));
        Department_col.setCellValueFactory(new PropertyValueFactory<>("age"));
        Format_col.setCellValueFactory(new PropertyValueFactory<>("format"));
        TeamName_tf.setDisable(false);
        EditViewPlayer_bt.setDisable(false);
        AddPlayer_bt.setDisable(false);
        DeletePlayer_bt.setDisable(false);
        SaveTeam_bt.setDisable(false);
        Cancel_bt.setDisable(false);
        setcap_bt.setDisable(false);
        draws_count.setText(""+TeamtobeEdited.getDraws());
        loss_count.setText(""+TeamtobeEdited.getLosses());
        wins_count.setText(""+TeamtobeEdited.getWins());
        match_count.setText(""+""+TeamtobeEdited.getMatches());
        wl_count.setText(""+TeamtobeEdited.getWintolose());
    }
}

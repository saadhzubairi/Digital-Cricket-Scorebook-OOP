package MatchStart;
import MatchToss.TossController;
import TeamEditView.TeamEditView;
import TeamsView.Team;
import TeamsView.TeamsViewController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class MatchStart implements Initializable {
    @FXML private Label bottom_label;
    @FXML private Label team1_label;
    @FXML private TableView<Team> tabla1;
    @FXML private TableColumn<Object, Object> id_col1, name_col1, cap_col1, mem_col1, winloss_col1;
    @FXML private JFXButton View_team1, set_team1;
    
    @FXML private Label team2_label;
    @FXML private TableView<Team> tabla2;
    @FXML private TableColumn<Object, Object> id_col2, name_col2, cap_col2, mem_col2, winloss_col2;
    @FXML private JFXButton View_team2, set_team2;
    
    @FXML private JFXComboBox<Integer> overs_comb;
    
    @FXML private JFXButton save_for_later, cancel, START;
    
    
    ObservableList<Integer> overs = FXCollections.observableArrayList(1, 3, 5, 6, 10, 15, 20, 50);
    
    
    /////                                      Injectables!
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public ObservableList<Team> teamslist = FXCollections.observableArrayList();
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    
    public void listinjector(ObservableList<Team> teamlist, ArrayList<Integer> ID_Standard){
        this.teamslist = teamlist; this.ID_Standard= ID_Standard;
        
        tabla1.setItems(teamslist);
        id_col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col1.setCellValueFactory(new PropertyValueFactory<>("teamname"));
        cap_col1.setCellValueFactory(new PropertyValueFactory<>("capname"));
        mem_col1.setCellValueFactory(new PropertyValueFactory<>("members"));
        winloss_col1.setCellValueFactory(new PropertyValueFactory<>("wintolose"));
    
        tabla2.setItems(teamslist);
        id_col2.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col2.setCellValueFactory(new PropertyValueFactory<>("teamname"));
        cap_col2.setCellValueFactory(new PropertyValueFactory<>("capname"));
        mem_col2.setCellValueFactory(new PropertyValueFactory<>("members"));
        winloss_col2.setCellValueFactory(new PropertyValueFactory<>("wintolose"));
    }
    
    public Team team1;
    public Team team2;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        overs_comb.setItems(overs);
    
        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        
            if(team1 == null || team2 == null || overs_comb.getValue() == null){
                save_for_later.setDisable(true);
                START.setDisable(true);
            }
            else {
                save_for_later.setDisable(false);
                START.setDisable(false);
            }
        
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
    
        Timeline UpdateTracker2 = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        
            if(tabla1.getSelectionModel().getSelectedItem()==null){
                set_team1.setDisable(true);
                View_team1.setDisable(true);
            }
            else {
                set_team1.setDisable(false);
                View_team1.setDisable(false);
            }
        
        }));
        UpdateTracker2.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker2.play();
    
        Timeline UpdateTracker3 = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        
            if(tabla2.getSelectionModel().getSelectedItem()==null){
                set_team2.setDisable(true);
                View_team2.setDisable(true);
            }
            else {
                set_team2.setDisable(false);
                View_team2.setDisable(false);
            }
        
        }));
        UpdateTracker3.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker3.play();
    
    
    
        View_team1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeamEditView/TeamEditView.fxml"));
                    Parent root1 = fxmlLoader.load();
                    TeamEditView teamEditView = fxmlLoader.getController();
                    Team injectteam = tabla1.getSelectionModel().getSelectedItem();
                    int injectindex = tabla1.getSelectionModel().getSelectedIndex();
                    teamEditView.teamlistinjector(teamslist, injectteam, injectindex, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Add Team");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    bottom_label.setText("No team selected!");
                }
            }
        });
        View_team2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeamEditView/TeamEditView.fxml"));
                    Parent root1 = fxmlLoader.load();
                    TeamEditView teamEditView = fxmlLoader.getController();
                    Team injectteam = tabla2.getSelectionModel().getSelectedItem();
                    int injectindex = tabla2.getSelectionModel().getSelectedIndex();
                    teamEditView.teamlistinjector(teamslist, injectteam, injectindex, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Add Team");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    bottom_label.setText("No team selected!");
                }
            }
        });
    
        set_team1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team sel = tabla1.getSelectionModel().getSelectedItem();
                    if(!(sel== team2)){
                        team1 = sel;
                        team1_label.setText(sel.getTeamname());
                        print(sel.getTeamname()+ " selected as team one");
                    }
                    else{
                        bottom_label.setText("Please select a different team");
                    }
            }
        });
        set_team2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Team sel = tabla2.getSelectionModel().getSelectedItem();
                    if(!(sel==team1)) {
                        team2 = sel;
                        team2_label.setText(sel.getTeamname());
                        print(sel.getTeamname()+ " selected as team two");
                    }
                    else{
                        bottom_label.setText("Please select a different team");
                    }
            }
        });
        
        START.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int ov = overs_comb.getValue();
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchToss/Toss.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    TossController tossController = fxmlLoader.getController();
                    tossController.listinjector(ID_Standard, team1, team2, ov);
                    Stage stage = new Stage();
                    stage.setTitle("Toss");
                    stage.setScene(new Scene(root1));
                    stage.show();
                    closeButtonAction();
                }
                catch (Exception e){
                    bottom_label.setText("Team Selection is empty or the selected team may not have any bowlers");
                    System.out.println(e);
                }
                
            }
        });
    
        save_for_later.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(overs_comb.getValue());
            }
        });
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    
    private void print(String msg){
        bottom_label.setText(msg);
    }
    
}
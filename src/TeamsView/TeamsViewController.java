package TeamsView;

import TeamAdder.TeamAdderController;
import TeamEditView.TeamEditView;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Time;
import java.util.*;

public class TeamsViewController implements Initializable {
    
    public JFXButton EditTeambt;
    public JFXButton AddNewTeambt;
    public JFXButton DelTeambt;
    public JFXButton closebt;
    
    public TableView<Team> tabla;
    public TableColumn<Object, Object> id_col, name_col, cap_col ,mem_col, winloss_col;
    
    public ObservableList<Team> teamslist = FXCollections.observableArrayList();
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    
    public void listinjector(ObservableList<Team> teamlist, ArrayList<Integer> ID_Standard)
    {
        this.teamslist = teamlist;
        this.ID_Standard= ID_Standard;
        tabla.setItems(teamslist);
        id_col.setCellValueFactory      (new PropertyValueFactory<>("id"        ));
        name_col.setCellValueFactory    (new PropertyValueFactory<>("teamname"  ));
        cap_col.setCellValueFactory     (new PropertyValueFactory<>("capname"   ));
        mem_col.setCellValueFactory     (new PropertyValueFactory<>("members"   ));
        winloss_col.setCellValueFactory (new PropertyValueFactory<>("wintolose" ));
    }
    
    public TeamsViewController() { }
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
        Timeline UpdateTracker = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            
            if(tabla.getSelectionModel().getSelectedItem() == null){
                EditTeambt.setDisable(true);
                DelTeambt.setDisable(true);
            }
            else {
                EditTeambt.setDisable(false);
                DelTeambt.setDisable(false);
            }
        
        }));
        UpdateTracker.setCycleCount(Timeline.INDEFINITE);
        UpdateTracker.play();
        
        EditTeambt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeamEditView/TeamEditView.fxml"));
                    Parent root1 = fxmlLoader.load();
                    TeamEditView teamEditView = fxmlLoader.getController();
                    Team injectteam = tabla.getSelectionModel().getSelectedItem();
                    int injectindex = tabla.getSelectionModel().getSelectedIndex();
                    teamEditView.teamlistinjector(teamslist, injectteam, injectindex, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Add Team");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR");
                }
            }
        });
        AddNewTeambt.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeamAdder/TeamAdder.fxml"));
                    Parent root1 = fxmlLoader.load();
                    TeamAdderController teamAdderController = fxmlLoader.getController();
                    teamAdderController.teamlistinjector(teamslist, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Add Team");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        DelTeambt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabla.getItems().removeAll(tabla.getSelectionModel().getSelectedItems());
            }
        });
        
    }
    
    @FXML private void closeButtonAction() { Stage stage = (Stage) closebt.getScene().getWindow();stage.close(); }
    @FXML private void Deletefromtab() { tabla.getItems().removeAll(tabla.getSelectionModel().getSelectedItems()); }
}

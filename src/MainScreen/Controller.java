package MainScreen;
import MatchStart.MatchStart;
import PlayerAdder.Player;
import TeamsView.Team;
import TeamsView.TeamsViewController;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    @FXML public JFXButton save_bt;
    @FXML private JFXButton Teamsbt, StartMatchbt;
    @FXML private Label bottom_label;
    
    public static ObservableList<Team>  TEAMLIST    = FXCollections.observableArrayList();
    public static ArrayList<Integer>    ID_Standard = new ArrayList<>();
    
    public Controller(){ }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        read();
    
    \
        Teamsbt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                print("");
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TeamsView/TeamsView.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    TeamsViewController teamsViewController = fxmlLoader.getController();
                    teamsViewController.listinjector(TEAMLIST, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Teams");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR LOADING WINDOW" +"\n"+ e);
                }
            }
        });
        StartMatchbt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               print("");
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchStart/MatchStart.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    MatchStart matchStart = fxmlLoader.getController();
                    matchStart.listinjector(TEAMLIST, ID_Standard);
                    Stage stage = new Stage();
                    stage.setTitle("Start Match");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    System.out.println("ERROR LOADING WINDOW" +"\n"+ e);
                }
                
            }
        });
    }
    public void print(String x){
        bottom_label.setText(x);
    }
    
    private static ObservableList<Team> load() {
        try {
            InputStream in = Files.newInputStream(Paths.get("Objectsavefile.ser"));
            ObjectInputStream ois = new ObjectInputStream(in);
            List<Team> list = (List<Team>) ois.readObject() ;
            
            return FXCollections.observableList(list);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }
    public void write(){
        for(int i = 0; i<TEAMLIST.size(); i++){
            TEAMLIST.get(i).saver.addAll(TEAMLIST.get(i).players);
        }
        try {
            ObservableList<Team> temp = TEAMLIST;
            FileOutputStream fos = new FileOutputStream("Objectsavefile.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Team>(temp));
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        print("SAVED");
    }
    public void read(){
        TEAMLIST = load();
        try{
            for(int i = 0; i<TEAMLIST.size(); i++){
                ObservableList<Player> xx = FXCollections.observableArrayList();
                xx.addAll(TEAMLIST.get(i).saver);
                TEAMLIST.get(i).setPlayers(xx);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        print("LOADED");
        
        int x = (TEAMLIST.get(TEAMLIST.size()-1).players.get(TEAMLIST.get(TEAMLIST.size()-1).players.size()-1).id)-7335;
        for (int i = 0 ; i<=x; i++){
            ID_Standard.add(5);
        }
    }
}



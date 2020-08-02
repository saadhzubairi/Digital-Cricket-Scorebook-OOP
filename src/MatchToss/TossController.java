package MatchToss;
import Match.*;
import MatchPlay.AMATCHMAIN;
import MatchPlay.selectOpeningPlayers;
import PlayerAdder.AllRounder;
import PlayerAdder.Batsman;
import PlayerAdder.Bowler;
import TeamsView.Team;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class TossController implements Initializable {
    @FXML private JFXButton t1head_bt, t1tail_bt, t1_bat_bt, t1_bowl_bt,t2head_bt,
           t2tail_bt, t2_bat_bt, t2_bowl_bt, FLIP_BT,CANCEL, BEGIN;
    @FXML private Label t1_label,t2_label,TOSS_RESULT_LABEL,bottom_label;
    @FXML private ImageView IMG_VIEW;
    
    public Team team1, team2;
    public ArrayList<Integer> ID_Standard = new ArrayList<>();
    public int overs;
    
    public Team heads_team, tails_team;
    public Team toss_winner, toss_loser;
    public Team bat_team, bowl_team;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        disableFlip();
        disableplaychoices();
        BEGIN.setDisable(true);
        t1head_bt.setOnAction(event -> {
            heads_team = team1;
            tails_team = team2;
            choicedis();
            enableFlip();
            bottom_label.setText(heads_team.getTeamname()+" chooses Heads");
        });
        t1tail_bt.setOnAction(event -> {
            heads_team = team2;
            tails_team = team1;
            choicedis();
            enableFlip();
            bottom_label.setText(tails_team.getTeamname()+" chooses Tails");
        });
        t2head_bt.setOnAction(event -> {
            heads_team = team2;
            tails_team = team1;
            choicedis();
            enableFlip();
            bottom_label.setText(heads_team.getTeamname()+" chooses Heads");
        });
        t2tail_bt.setOnAction(event -> {
            heads_team = team1;
            tails_team = team2;
            choicedis();
            enableFlip();
            bottom_label.setText(tails_team.getTeamname()+" chooses Tails");
        });
        FLIP_BT.setOnAction(event -> {
            try{
                double rand = Math.random();
                Image image;
                if(rand>0.5){
                    image = new Image("/coins/heads.png");
                    IMG_VIEW.setImage(image);
                    TOSS_RESULT_LABEL.setText("HEADS");
                    toss_winner = heads_team;
                    toss_loser  = tails_team;
                }
                else{
                    image = new Image("/coins/tails.png");
                    IMG_VIEW.setImage(image);
                    TOSS_RESULT_LABEL.setText("TAILS");
                    toss_winner = tails_team;
                    toss_loser  = heads_team;
                }
                bottom_label.setText(toss_winner.getTeamname()+ " has won the toss!");
                if (toss_winner == team1){
                    enableplaychoicest1();
                }
                else{
                    enableplaychoicest2();
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            FLIP_BT.setDisable(true);
        });
        t1_bat_bt.setOnAction(event -> {
            bat_team = team1;
            bowl_team = team2;
            disableplaychoices();
            bottom_label.setText(toss_winner.getTeamname()+" chooses to bat first");
            BEGIN.setDisable(false);
            
        });
        t1_bowl_bt.setOnAction(event -> {
            bat_team = team2;
            bowl_team = team1;
            disableplaychoices();
            bottom_label.setText(toss_winner.getTeamname()+" chooses to bowl first");
            BEGIN.setDisable(false);
        });
        t2_bat_bt.setOnAction(event -> {
            bat_team = team2;
            bowl_team = team1;
            disableplaychoices();
            bottom_label.setText(toss_winner.getTeamname()+" chooses to bat first");
            BEGIN.setDisable(false);
        });
        t2_bowl_bt.setOnAction(event -> {
            bat_team = team1;
            bowl_team = team2;
            disableplaychoices();
            bottom_label.setText(toss_winner.getTeamname()+" chooses to bowl first");
            BEGIN.setDisable(false);
        });
    
        BEGIN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ObservableList<BattingMan> batti1MEN = FXCollections.observableArrayList();
                    ObservableList<BowlingMan> bowli1MEN = FXCollections.observableArrayList();
                    ObservableList<BattingMan> batti2MEN = FXCollections.observableArrayList();
                    ObservableList<BowlingMan> bowli2MEN = FXCollections.observableArrayList();
                    
                    for (int i = 0; i < bat_team.players.size(); i++) {
                        batti1MEN.add(new BattingMan(bat_team.players.get(i)));
                    }
    
                    for (int i = 0; i < bowl_team.players.size(); i++) {
                        if (bowl_team.players.get(i) instanceof Bowler || bowl_team.players.get(i) instanceof AllRounder) {
                            bowli1MEN.add(new BowlingMan(bowl_team.players.get(i)));
                        }
                    }
    
                    for (int i = 0; i < bowl_team.players.size(); i++) {
                        batti2MEN.add(new BattingMan(bowl_team.players.get(i)));
                    }
    
                    for (int i = 0; i < bat_team.players.size(); i++) {
                        if (bat_team.players.get(i) instanceof Bowler || bat_team.players.get(i) instanceof AllRounder) {
                            bowli2MEN.add(new BowlingMan(bat_team.players.get(i)));
                        }
                    }
                    
                    BattingTeam battm1  = new BattingTeam   (bat_team, batti1MEN);
                    BowlingTeam bowltm1 = new BowlingTeam   (bowl_team, bowli1MEN);
                    BattingTeam battm2  = new BattingTeam   (bowl_team, batti2MEN);
                    BowlingTeam bowltm2 = new BowlingTeam   (bat_team, bowli2MEN);
                    
                    InningOne inningOne = new InningOne(battm1, bowltm1);
                    InningTwo inningTwo = new InningTwo(battm2, bowltm2);
                    
                    Match MATCH = new Match(team1, team2, inningOne, inningTwo, overs);
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MatchPlay/selectOpeningPlayers.fxml"));
                        Parent root1lala = (Parent) fxmlLoader.load();
                        selectOpeningPlayers selectOpeningPlayers = fxmlLoader.getController();
                        selectOpeningPlayers.injector(MATCH);
                        Stage stage = new Stage();
                        stage.setTitle(MATCH.team1.getTeamname() + " VS " + MATCH.team2.getTeamname());
                        stage.setScene(new Scene(root1lala));
                        stage.show();
                    }
                    catch (Exception e){
                        bottom_label.setText("ERROR\n" + e);
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
                closeButtonAction();
            }
        });
    }
    
    public void listinjector(ArrayList<Integer> ID_Standard, Team team1, Team team2, int overs){
        this.team1          = team1;
        this.team2          = team2;
        this.ID_Standard    = ID_Standard;
        this.overs = overs;
        t1_label.setText(team1.getTeamname());
        t2_label.setText(team2.getTeamname());
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) CANCEL.getScene().getWindow();
        stage.close();
    }
    private void disableplaychoices(){
        t1_bat_bt.setDisable(true);
        t1_bowl_bt.setDisable(true);
        t2_bat_bt.setDisable(true);
        t2_bowl_bt.setDisable(true);
    }
    private void enableplaychoicest1(){
        t1_bat_bt.setDisable(false);
        t1_bowl_bt.setDisable(false);
        t2_bat_bt.setDisable(true);
        t2_bowl_bt.setDisable(true);
    }
    private void enableplaychoicest2(){
        t1_bat_bt.setDisable(true);
        t1_bowl_bt.setDisable(true);
        t2_bat_bt.setDisable(false);
        t2_bowl_bt.setDisable(false);
    }
    private void disableFlip(){
        FLIP_BT.setDisable(true);
    }
    private void enableFlip(){
        FLIP_BT.setDisable(false);
    }
    private void choicedis(){
        t1head_bt.setDisable(true);
        t1tail_bt.setDisable(true);
        t2head_bt.setDisable(true);
        t2tail_bt.setDisable(true);
    }
}

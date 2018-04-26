package com.cw.ui.scenes;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import com.cw.exceptions.FighterException;
import com.cw.services.SessionServiceI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

import java.util.List;

@Component
public class BattleStage implements BasicStage {

    // Main stage and scene.
    Stage window;
    Scene scene;

    // Session services.
    @Autowired
    SessionServiceI sessionServiceI;

    // Acces token of current user.
    String accessToken;

    // Current user.
    User currentUser;

    @Autowired
    NavigationStage navigationStage;

    // Scenes layout.
    GridPane layout;

    // Scene elements.
    ComboBox setList;
    Button battleBtn;
    Button backBtn;

    // Scene size.
    private int h;
    private int w;

    public BattleStage(){

        // Setting default window size.
        h = 400;
        w = 400;

        setUpLayout();
    }

    @Override
    public void init(Stage stage) {

        window = stage;

        // Setting up layout elements.
        setList = new ComboBox<Set>();

        // Setting up back button.
        battleBtn = new Button("Start Battle");
        battleBtn.setOnAction(e -> startBattle());

        // Setting up back button.
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> window.setScene(navigationStage.getScene()));

        layout.add(setList, 0, 0);
        layout.add(battleBtn, 1, 0);
        layout.add(backBtn, 1, 2);

        scene = new Scene(layout, h, w);

    }

    @Override
    public Scene getScene() {
        return scene;
    }

    private void setUpLayout(){

        // Setting the layout.
        layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));
    }

    private void setStringConverter(ComboBox<Set> setList){
        setList.setConverter(new StringConverter<Set>() {

            @Override
            public String toString(Set set) {
                return set.getName();
            }

            @Override
            public Set fromString(String name) {
                return setList.getItems().stream().filter(ap ->
                        ap.getName().equals(name)).findFirst().orElse(null);
            }
        });
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public void setUserAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void updateUser(User user, String accessToken){
        setCurrentUser(user);
        setUserAccessToken(accessToken);

        List<Set> sets = currentUser.getSets();

        setStringConverter(setList);

        // Setting up lists of sets, that are availiable to user.
        for(Set set : user.getSets()){
            setList.getItems().add(set);
        }

        setList.getSelectionModel().select(0);
    }

    public void updateSetList(){

    }

    public void startBattle(){
        Set selectedSet = (Set) setList.getSelectionModel().getSelectedItem();
        try {
            int resultId = sessionServiceI.startFightAgainstUsers(selectedSet,
                    accessToken, "Duel");
            System.out.println(resultId);
        } catch (FighterException e) {
            e.printStackTrace();
        }
    }
}

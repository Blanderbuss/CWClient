package com.cw.ui.scenes;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
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
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> window.setScene(navigationStage.getScene()));

        layout.add(backBtn, 0, 0);

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

    private void setStringConverter(ComboBox<Set> set){
        set.setConverter(new StringConverter<Set>() {

            @Override
            public String toString(Set set) {
                return set.getName();
            }

            @Override
            public Set fromString(String name) {
                return set.getItems().stream().filter(ap ->
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
        
        setList.getItems().clear();

        for(Set set : sets){
            setList.getItems().add(set.getName());
        }
    }
}

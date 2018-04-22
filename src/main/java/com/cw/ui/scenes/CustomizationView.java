package com.cw.ui.scenes;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import com.cw.ui.support.BasicStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class CustomizationView implements BasicStage {

    Scene scene;

    // Declaring the layout and its parameters.
    VBox layout;
    int h;
    int w;

    // Current user.
    User currentUser;
    String accessToken;


    // Corresponding set.
    Set set;

    // Declaaring layout elements.
    TextArea codeArea;
    ComboBox<Artefact> headArtsList;

    public CustomizationView(Set set){
        this.set = set;

        layout = new VBox();

        codeArea = new TextArea();
        headArtsList = new ComboBox<Artefact>();

        // Setting the layout.
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30,30,30,30));
        layout.setSpacing(15);

        // Setting default window size.
        h = 300;
        w = 300;

        layout.getChildren().add(codeArea);
        layout.getChildren().add(headArtsList);
        layout.getChildren().add(new Button("Touch me please"));
        layout.setPrefWidth(Control.USE_COMPUTED_SIZE);
        scene = new Scene(layout, h, w);

    }

    @Override
    public void init(Stage stage) { }

    @Override
    public Scene getScene() {
        return scene;
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

        //List<Artefact> arts = currentUser.getUserArtefacts();
        codeArea.setText(set.getCode());
        codeArea.setPrefWidth(Control.USE_COMPUTED_SIZE);
        codeArea.setPrefHeight(Control.USE_COMPUTED_SIZE);

        headArtsList.setConverter(new StringConverter<Artefact>() {

            @Override
            public String toString(Artefact art) {
                return art.getName();
            }

            @Override
            public Artefact fromString(String name) {
                return headArtsList.getItems().stream().filter(ap ->
                        ap.getName().equals(name)).findFirst().orElse(null);
            }
        });


        for(Artefact art : set.getArtefacts()){
            headArtsList.getItems().add(art);
        }

    }
}

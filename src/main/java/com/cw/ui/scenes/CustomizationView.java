package com.cw.ui.scenes;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import com.cw.ui.support.BasicStage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    Label headArtLabel;
    ComboBox<Artefact> bodyArtsList;
    Label bodyArtLabel;
    ComboBox<Artefact> armsArtsList;
    Label armsArtLabel;
    ComboBox<Artefact> legsArtsList;
    Label legsArtLabel;
    Button saveButton;

    public CustomizationView(Set set){
        this.set = set;

        layout = new VBox();

        // Setting up layout elements.
        codeArea = new TextArea();
        headArtLabel = new Label("Head artefact:");
        headArtsList = new ComboBox<Artefact>();
        bodyArtLabel = new Label("Body artefact:");
        bodyArtsList = new ComboBox<Artefact>();
        armsArtLabel = new Label("Arms artefact:");
        armsArtsList = new ComboBox<Artefact>();
        legsArtLabel = new Label("Legs artefact:");
        legsArtsList = new ComboBox<Artefact>();

        // Seting up save changes button.
        saveButton = new Button("Save changes");
        saveButton.setOnAction(e -> saveChanges());

        // Setting the layout.
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30,30,30,30));
        layout.setSpacing(15);

        // Setting default window size.
        h = 500;
        w = 600;

        layout.getChildren().add(codeArea);
        layout.getChildren().add(headArtLabel);
        layout.getChildren().add(headArtsList);
        layout.getChildren().add(bodyArtLabel);
        layout.getChildren().add(bodyArtsList);
        layout.getChildren().add(armsArtLabel);
        layout.getChildren().add(armsArtsList);
        layout.getChildren().add(legsArtLabel);
        layout.getChildren().add(legsArtsList);
        layout.getChildren().add(saveButton);
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

        codeArea.setText(set.getCode());
        codeArea.setPrefWidth(Control.USE_COMPUTED_SIZE);
        codeArea.setPrefHeight(Control.USE_COMPUTED_SIZE);

        // Setting up string converter to store artifacts while displaying only their name.
        setStringConverter(headArtsList);
        setStringConverter(bodyArtsList);
        setStringConverter(armsArtsList);
        setStringConverter(legsArtsList);

        // Setting up lists of artefacts, that are availiable to user.
        for(Artefact art : user.getUserArtefacts()){
            setArtByType(art);
            setEquippedItem(art);
        }
    }

    private void setArtByType(Artefact art){
        if(art.getType().equals("head"))
            headArtsList.getItems().add(art);
        if(art.getType().equals("body"))
            bodyArtsList.getItems().add(art);
        if(art.getType().equals("arms"))
            armsArtsList.getItems().add(art);
        if(art.getType().equals("legs"))
            legsArtsList.getItems().add(art);
    }

    private void setEquippedItem(Artefact art){
        if(art.getType().equals("head") || set.getArtefacts().contains(art)){
            headArtsList.getSelectionModel().select(art);
        }
        if(art.getType().equals("body") || set.getArtefacts().contains(art)){
            bodyArtsList.getSelectionModel().select(art);
        }
        if(art.getType().equals("arms") || set.getArtefacts().contains(art)){
            armsArtsList.getSelectionModel().select(art);
        }
        if(art.getType().equals("legs") || set.getArtefacts().contains(art)){
            legsArtsList.getSelectionModel().select(art);
        }
    }

    private void setStringConverter(ComboBox<Artefact> art){
        art.setConverter(new StringConverter<Artefact>() {

            @Override
            public String toString(Artefact art) {
                return art.getName();
            }

            @Override
            public Artefact fromString(String name) {
                return art.getItems().stream().filter(ap ->
                        ap.getName().equals(name)).findFirst().orElse(null);
            }
        });
    }

    // Queries server to update set data in data base.
    private void saveChanges(){

    }
}

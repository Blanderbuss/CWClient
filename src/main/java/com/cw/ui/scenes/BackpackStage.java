package com.cw.ui.scenes;

import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import com.cw.ui.support.BasicStage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BackpackStage implements BasicStage{

    // Current user.
    User currentUser;
    String accessToken;

    // Main stage and scene.
    private Stage window;
    private Scene scene;

    // Scene size.
    private int h;
    private int w;

    // Link to navigation stage.
    @Autowired
    NavigationStage navigationStage;

    // Scenes layout.
    private GridPane layout;

    // Layout elements
    ListView artsList;
    TextArea artInfo;
    Button backBtn;

    public BackpackStage(){

        layout = new GridPane();

        // Setting up sets lost.
        artInfo = new TextArea();
        artInfo.setEditable(false);

        artsList = new ListView();
        artsList.setMinWidth(Control.USE_PREF_SIZE);
        artsList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                Artefact item = (Artefact) artsList.getSelectionModel().getSelectedItem();

                String stats = "HP: " + item.getHpBoost() + "\n" +
                        "Mana: " + item.getManaBoost() + "\n" +
                        "Stamina: " + item.getStaminaBoost() + "\n" +
                        "HP Regen: " + item.getHpRegenBoost() + "\n" +
                        "Mana Regen: " + item.getManaRegenBoost() + "\n" +
                        "Stamina Regen: " + item.getStaminaRegenBoost() + "\n" +
                        "Attack: " + item.getAttackBoost() + "\n" +
                        "Evasion: " + item.getEvasionBoost() + "\n" +
                        "Armor: " + item.getArmorBoost() + "\n" +
                        "Type: " + item.getType();

                artInfo.setText(stats);
            }

        });

        artsList.setCellFactory(lv -> {
            TextFieldListCell<Artefact> cell = new TextFieldListCell<Artefact>();
            cell.setConverter(new StringConverter<Artefact>() {

                @Override
                public String toString(Artefact art) {
                    return art.getName();
                }

                @Override
                public Artefact fromString(String string) {
                    Artefact art = cell.getItem();
                    art.setName(string);
                    return art;
                }
            });
            return cell;
        });

        h = 500;
        w = 400;

        // Setting the layout.
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));
    }

    @Override
    public void init(Stage stage) {

        // Primary stage initialization.
        window = stage;

        // Setting up back button.
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            artsList.getItems().clear();
            window.setScene(navigationStage.getScene());
        });

        layout.add(artsList, 0, 0);
        layout.add(artInfo, 1, 0);
        layout.add(backBtn, 0, 2);

        scene = new Scene(layout, h, w);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

//    public void updateName(String name){
//        nameLbl.setText(name);
//    }

    public void updateUser(User user, String accessToken){

        setCurrentUser(user);
        setAccessToken(accessToken);
        //updateName(user.getUsername());

        List<Artefact> arts = currentUser.getUserArtefacts();

        for(Artefact art : arts){
            artsList.getItems().add(art);
        }
    }



}

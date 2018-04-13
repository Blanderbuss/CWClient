package com.cw.ui.scenes;

import com.cw.models.entities.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

@Component
public class CustomizationStage implements BasicStage{

//    рівен
//            досві
//    мій нік
//    створити набір

    // Main stage and scene.
    Stage window;
    Scene scene;

    // Current user.
    User currentUser;

    @Autowired
    NavigationStage navigationStage;

    // Scenes layout.
    GridPane layout;

    // Scene elements.
    ListView setsList;
    TableColumn nameColumn;
    TableColumn levelColumn;
    TableColumn experienceColumn;
    Label levelLbl;
    Label experienceLbl;
    Label nameLbl;
    Button createSetBtn;
    Button backBtn;

    // Scene size.
    private int h;
    private int w;

    public CustomizationStage(){

        // Setting default window size.
        h = 400;
        w = 400;

        setsList = new ListView();
        levelLbl = new Label("");
        experienceLbl = new Label("");
        nameLbl = new Label("");

        nameColumn = new TableColumn("Username");
        levelColumn = new TableColumn("Level");
        experienceColumn = new TableColumn("Experience");

        setsList.getItems().addAll("AAA");

        setUpLayout();
    }

    @Override
    public void init(Stage stage) {

        window = stage;

        // Setting up back button.
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> window.setScene(navigationStage.getScene()));

        layout.add(setsList, 0, 0);
//        layout.add(levelLbl, 0, 1);
//        layout.add(experienceLbl, 0, 2);
        layout.add(backBtn, 0, 3);

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

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public void updateUser(User user){
        setCurrentUser(user);
        nameLbl.setText(user.getUsername());
        levelLbl.setText("Your level is " + user.getLvl());
        experienceLbl.setText("Your experience is " + user.getExperience());

        //updateName(user.getUsername());
    }
}

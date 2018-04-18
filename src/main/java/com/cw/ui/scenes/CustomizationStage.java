package com.cw.ui.scenes;

import com.cw.services.SessionServiceI;
import com.cw.entities.Set;
import com.cw.entities.User;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

import java.util.List;

@Component
public class CustomizationStage implements BasicStage{

//    рівен
//            досві
//    мій нік
//    створити набір

    // Session services.
    @Autowired
    SessionServiceI sessionServiceI;

    // Acces token of current user.
    String accessToken;

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
    ListView setList;
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

        // Setting up sets lost.
        setList = new ListView();

        setList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {

                String selectedSet = String.valueOf(setList.getSelectionModel().getSelectedItems());
                openCustomizationView(selectedSet);
                System.out.println(setList.getSelectionModel().getSelectedItems());
            }

        });
        //setsTable.setEditable(true);

        levelLbl = new Label("");
        experienceLbl = new Label("");
        nameLbl = new Label("");

        // Setting table colums.
//        setNameColumn = new TableColumn("Set");
//        setNameColumn.setResizable(false);
//        setNameColumn.prefWidthProperty().bind(setsTable.widthProperty().multiply(0.8));
//        setDeleteColumn = new TableColumn("Delete");
//        setDeleteColumn.setResizable(false);
//        setDeleteColumn.prefWidthProperty().bind(setsTable.widthProperty().multiply(0.2));

//        setsTable.getColumns().addAll(setNameColumn, setDeleteColumn);
//        setsTable.getItems().add(1);
//        setsTable.getItems().add(2);

        setUpLayout();
    }

    @Override
    public void init(Stage stage) {

        window = stage;

        // Setting up back button.
        backBtn = new Button("Back");
        backBtn.setOnAction(e -> window.setScene(navigationStage.getScene()));



        layout.add(setList, 0, 0);
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

    public void setUserAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void updateUser(User user, String accessToken){
        setCurrentUser(user);
        setUserAccessToken(accessToken);

        List<Set> sets = currentUser.getSets(); //sessionServiceI.getAllSetsOfMyUser(accessToken, currentUser);

        for(Set set : sets){
            setList.getItems().add(set.getName());
        }

        //updateName(user.getUsername());
    }

    public void openCustomizationView(String setName){
        Stage window = new Stage();
        window.setTitle(setName);
        window.setMinWidth(250);
        CustomizationView customizationView = null;

        List<Set> sets = currentUser.getSets(); //sessionServiceI.getAllSetsOfMyUser(accessToken, currentUser);

        for(Set set : sets){
            if(("[" + set.getName() + "]").equals(setName)){
                customizationView = new CustomizationView(set);
                break;
            }
        }

        if(customizationView == null){
            System.out.println("No set found");
            return;
        }

        window.setScene(customizationView.getScene());
        window.show();
    }
}

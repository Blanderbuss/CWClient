package com.cw.ui.scenes;

import com.cw.entities.User;
import com.cw.exceptions.IncorrectAccessTokenException;
import com.cw.services.SessionServiceI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

@Component
public class NavigationStage implements BasicStage {

    // Main stage and scene.
    Stage window;
    Scene scene;

    // Session services.
    @Autowired
    SessionServiceI sessionServiceI;

    // Authentication page reference.
    @Autowired
    AuthStage authStage;

    // Customization stage.
    @Autowired
    CustomizationStage customizationStage;

    // Battle stage.
    @Autowired
    BattleStage battleStage;

    // Backpack stage.
    @Autowired
    BackpackStage backpackStage;

    // Current user.
    User currentUser;

    // Current user access token.
    String accessToken;

    // Scene size.
    private int h;
    private int w;

    // Scenes layout.
    VBox layout;

    // Scene elements.
    Label nameLbl;
    Button cstmBtn;
    Button battleBtn;
    Button logoutBtn;
    Button backpackBtn;

    public NavigationStage(){

        layout = new VBox();

        // Setting the layout.
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30,30,30,30));
        layout.setSpacing(15);

        // Setting default window size.
        h = 300;
        w = 300;

        // Setting the layout.
//        layout.setAlignment(Pos.CENTER);
//        layout.setHgap(10);
//        layout.setVgap(10);
//        layout.setPadding(new Insets(25, 25, 25, 25));

    }

    @Override
    public void init(Stage stage) {

        // Primary stage initialization.
        window = stage;

        // Customization scene initialization.
        customizationStage.init(stage);

        // Battle stage initialization.
        battleStage.init(stage);

        // Backpack stage initialization.
        backpackStage.init(stage);

        // Setting the info button;
        cstmBtn = new Button("Customize");
        cstmBtn.setOnAction(e -> {
            customizationStage.updateUser(currentUser, accessToken);
            window.setScene(customizationStage.getScene());
        });
        cstmBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Setting the info button;
        battleBtn = new Button("Enter Battle");
        battleBtn.setOnAction(e -> {
            battleStage.updateUser(currentUser, accessToken);
            window.setScene(battleStage.getScene());
        });
        battleBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Setting the login button;
        logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            currentUser = null;
            try {
                sessionServiceI.logout(accessToken);
            } catch (IncorrectAccessTokenException e1) {
                e1.printStackTrace();
            }
            window.setScene(authStage.getScene());
        });
        logoutBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Setting the backpack button;
        backpackBtn = new Button("Backpack");
        backpackBtn.setOnAction(e -> {
            backpackStage.updateUser(currentUser, accessToken);
            window.setScene(backpackStage.getScene());
        });
        backpackBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        backpackBtn.setTooltip(
                new Tooltip("Button of Doom")
        );

//        ListView listView = new ListView();
//        listView.getItems().add(cstmBtn);
//        listView.getItems().add(infoBtn);
//        listView.getItems().add(logoutBtn);

        nameLbl = new Label("");

        layout.getChildren().add(nameLbl);
        layout.getChildren().add(cstmBtn);
        layout.getChildren().add(battleBtn);
        layout.getChildren().add(backpackBtn);
        layout.getChildren().add(logoutBtn);

        //layout.getChildren().add(listView);

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

    public void updateName(String name){
        nameLbl.setText(name);
    }

    public void updateUser(User user, String accessToken){

        setCurrentUser(user);
        setAccessToken(accessToken);
        updateName(user.getUsername());
    }
}

package com.cw.ui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    // Authentication page reference.
    @Autowired
    AuthStage authStage;

    // Statistics stage.
    @Autowired
    BattleStage battleStage;

    // Scene size.
    private int h;
    private int w;

    // Scenes layout.
    VBox layout;

    // Scene elements.
    Button cstmBtn;
    Button infoBtn;
    Button logoutBtn;

    public NavigationStage(){
        System.out.println("New auth stage created.");

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

        // Battle stage initialization.
        battleStage.init(stage);

        // Setting the info button;
        cstmBtn = new Button("Customize");
        cstmBtn.setOnAction(e -> window.setScene(authStage.getScene()));
        cstmBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Setting the info button;
        infoBtn = new Button("Stats");
        infoBtn.setOnAction(e -> window.setScene(authStage.getScene()));
        infoBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Setting the login button;
        logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> window.setScene(authStage.getScene()));
        logoutBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

//        ListView listView = new ListView();
//        listView.getItems().add(cstmBtn);
//        listView.getItems().add(infoBtn);
//        listView.getItems().add(logoutBtn);

        layout.getChildren().add(cstmBtn);
        layout.getChildren().add(infoBtn);
        layout.getChildren().add(logoutBtn);

        //layout.getChildren().add(listView);

        scene = new Scene(layout, h, w);

    }

    @Override
    public Scene getScene() {
        return scene;
    }
}

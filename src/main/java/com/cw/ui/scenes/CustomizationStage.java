package com.cw.ui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

@Component
public class CustomizationStage implements BasicStage{

    // Main stage and scene.
    Stage window;
    Scene scene;

    @Autowired
    NavigationStage navigationStage;

    // Scenes layout.
    GridPane layout;

    // Scene elements.
    Button backBtn;

    // Scene size.
    private int h;
    private int w;

    public CustomizationStage(){

        // Setting default window size.
        h = 400;
        w = 400;

        setUpLayout();
    }

    @Override
    public void init(Stage stage) {

        window = stage;

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
}

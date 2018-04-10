package com.cw.ui.scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import support.BasicStage;

@Component
public class BattleStage implements BasicStage {

    // Main stage and scene.
    Stage window;
    Scene scene;

    public BattleStage(){

    }

    @Override
    public void init(Stage stage) {

        // Primary stage initialization.
        window = stage;
    }

    @Override
    public Scene getScene() {
        return null;
    }
}

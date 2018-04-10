package com.cw.ui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import support.BasicStage;

/**
 * Created by denysmelnychenko on 3/18/18.
 */

@Component
public class AuthStage implements BasicStage {

    // Holds all needed stages.
    //StagesCollection stagesCollection;

    // Main stage and scene.
    Stage window;
    Scene scene;

    // Sign up stage.
    @Autowired
    SignUpStage signUpStage;

    // Navigation stage.
    @Autowired
    NavigationStage navigationStage;

    // Scene size.
    private int h;
    private int w;

    // Scenes layout.
    GridPane layout;

    // Scene elements.
    Button loginBtn;
    Button signUpBtn;
    TextField loginTxt;
    TextField passwdTxt;
    Label loginLbl;
    Label passwdLbl;

    public AuthStage(){//StagesCollection st){

        System.out.println("New auth stage created.");

        layout = new GridPane();

        // Setting default window size.
        h = 300;
        w = 300;

        // Setting other layout elements.
        loginTxt = new TextField();
        passwdTxt = new TextField();
        loginLbl = new Label("Login:");
        passwdLbl = new Label("Password:");

        // Setting the layout.
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));
    }

    @Override
    public void init(Stage stage){

        // Primary stage initialization.
        window = stage;

        // Sign up stage initialization and navigation.
        signUpStage.init(stage);
        navigationStage.init(stage);

        // Setting the login button;
        loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> handleLogin());

        // Setting the sign up button;
        signUpBtn = new Button("Sign Up");
        //signUpBtn.setOnAction(e -> window.setScene(sg.getScene()));
        //Scene sg = stagesCollection.getSignUpStage().getScene();
        signUpBtn.setOnAction(e -> window.setScene(signUpStage.getScene()));

        layout.add(loginLbl, 0, 1);
        layout.add(loginTxt, 1, 1);
        layout.add(passwdLbl, 0, 2);
        layout.add(passwdTxt, 1, 2);

        layout.add(loginBtn, 1, 3);
        layout.add(signUpBtn, 1, 5);

        scene = new Scene(layout, h, w);
    }

    // Returns authentication scene.
    @Override
    public Scene getScene() {
        return scene;
    }

    // TODO: handles whole log in process.
    private void handleLogin(){
        boolean valid = isValid(loginTxt.getText(), passwdTxt.getText());
        if(valid) window.setScene(navigationStage.getScene());
        System.out.println("checkind valid");
    }

    // TODO: checks if login and password are valid.
    private boolean isValid(String login, String passwd) {
        return true;
    }
}

package com.cw.ui.scenes;

import com.cw.exceptions.IncorrectAccessTokenException;
import com.cw.services.SessionServiceI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cw.ui.support.BasicStage;

/**
 * Created by denysmelnychenko on 3/18/18.
 */

@Component
public class SignUpStage implements BasicStage {

    // Session services.
    @Autowired
    SessionServiceI sessionServiceI;

    // Main stage and scene.
    Stage window;
    Scene scene;

    @Autowired
    AuthStage authStage;

    // Scene size.
    private int h;
    private int w;

    // Scenes layout.
    GridPane layout;

    // Scene elements.
    Button backBtn;
    Button signUpBtn;
    //TextField nameTxt;
    TextField emailTxt;
    TextField loginTxt;
    TextField passwdTxt;
    //Label nameLbl;
    Label emailLbl;
    Label loginLbl;
    Label passwdLbl;


    public SignUpStage(){

        // Setting default window size.
        h = 400;
        w = 400;

        // Initializing layout elements.
        //nameTxt = new TextField();
        loginTxt = new TextField();
        emailTxt = new TextField();
        passwdTxt = new PasswordField();

        //nameLbl = new Label("Name:");
        emailLbl = new Label("Email:");
        loginLbl = new Label("Login:");
        passwdLbl = new Label("Password:");

        setUpLayout();

    }

    @Override
    public void init(Stage stage){

        window = stage;

        // Setting go back to logging in scene button.
        backBtn = new Button("Go back");
        backBtn.setOnAction(e -> window.setScene(authStage.getScene()));

        // Setting sign up button.
        signUpBtn = new Button("Sign me up!");
        signUpBtn.setOnAction(e -> {
            boolean isValid = handleSignUp();
            System.out.println(isValid);
            if(isValid)
                window.setScene(authStage.getScene());
        });

        // Setting up the layout.
        //layout.add(nameLbl, 0, 0);
        //layout.add(nameTxt, 1, 0);
        layout.add(emailLbl, 0, 1);
        layout.add(emailTxt, 1, 1);
        layout.add(loginLbl, 0, 2);
        layout.add(loginTxt, 1, 2);
        layout.add(passwdLbl, 0, 3);
        layout.add(passwdTxt, 1, 3);
        layout.add(signUpBtn, 1, 4);
        layout.add(backBtn, 1, 5);

        scene = new Scene(layout, h, w);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    private boolean handleSignUp(){
        String username = loginTxt.getText();
        String email = emailTxt.getText();
        String pwd = passwdTxt.getText();
        if(notEmty()) {
            try {
                sessionServiceI.register(username, email, pwd);
            } catch (IncorrectAccessTokenException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private boolean notEmty(){
        if(!loginTxt.getText().equals("") &&
                !emailTxt.getText().equals("") &&
                !passwdTxt.getText().equals("")){

            return true;
        }
        System.out.println("empty!");
        return false;
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

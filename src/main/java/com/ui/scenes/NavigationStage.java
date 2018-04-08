package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import support.BasicStage;

public class NavigationStage implements BasicStage {

    // Main stage and scene.
    Stage window;
    Scene scene;

    // Authentication page reference.
    @Autowired
    AuthStage authStage;

    // Scene size.
    private int h;
    private int w;

    // Scenes layout.
    GridPane layout;

    // Scene elements.

    Button logoutBtn;

    public NavigationStage(){
        System.out.println("New auth stage created.");

        //this.stagesCollection = st;
        //this.window = st.getWindow();

        layout = new GridPane();

        // Setting default window size.
        h = 300;
        w = 300;

        // Setting other layout elements.
//        loginTxt = new TextField();
//        passwdTxt = new TextField();
//        loginLbl = new Label("Login:");
//        passwdLbl = new Label("Password:");

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

        // Setting the login button;
        logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> window.setScene(authStage.getScene()));

        layout.add(logoutBtn, 0, 1);

        scene = new Scene(layout, h, w);

    }

    @Override
    public Scene getScene() {
        return scene;
    }
}

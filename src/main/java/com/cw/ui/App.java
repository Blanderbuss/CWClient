package com.cw.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cw.ui.scenes.AuthStage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        //StagesCollection stagesCollection = new StagesCollection(primaryStage);
        //stagesCollection.setAuthStage(new AuthStage(stagesCollection));
        //stagesCollection.setSignUpStage(new SignUpStage(stagesCollection));
        //SignUpStage sg = new SignUpStage(primaryStage);
        //AuthStage authStage = new AuthStage(primaryStage);
        //AuthStage authStage = new AuthStage(primaryStage);

        //primaryStage.setScene(stagesCollection.getAuthStage().getScene());
        //primaryStage.show();

        ApplicationContext context = new ClassPathXmlApplicationContext("ui-beans.xml");
        AuthStage as = (AuthStage) context.getBean("authStage");
        as.init(primaryStage);
        primaryStage.setScene(as.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package com.cw.ui;

import com.cw.services.SessionServiceI;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.cw.ui.scenes.AuthStage;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
public class App extends Application {

    static final String adress = "localhost";

    @Bean
    RmiProxyFactoryBean battleService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://"+adress+":1099/SessionServiceI");
        rmiProxyFactory.setServiceInterface(SessionServiceI.class);
        return rmiProxyFactory;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class);
        AuthStage as = (AuthStage) applicationContext.getBean(AuthStage.class);
        as.init(primaryStage);
        primaryStage.setScene(as.getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {

        //CustomizationStage cs = new CustomizationStage();
        launch(args);
    }
}

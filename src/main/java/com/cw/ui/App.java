package com.cw.ui;

import com.cw.appif.ServerServiceIF;
import com.cw.models.db.services.SessionServiceI;
import com.cw.ui.scenes.CustomizationStage;
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
    /*
    @Bean
    RmiProxyFactoryBean battleService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://10.0.128.95:1099/ServerServiceIF");
        rmiProxyFactory.setServiceInterface(ServerServiceIF.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean userService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://10.0.128.95:1099/UserServiceI");
        rmiProxyFactory.setServiceInterface(UserServiceI.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean setService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://10.0.128.95:1099/SetServiceI");
        rmiProxyFactory.setServiceInterface(SetServiceI.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean artefactService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://10.0.128.95:1099/ArtefactServiceI");
        rmiProxyFactory.setServiceInterface(ArtefactServiceI.class);
        return rmiProxyFactory;
    }*/

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

        CustomizationStage cs = new CustomizationStage();
        launch(args);
    }
}

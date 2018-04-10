package com.cw.ui;

import com.cw.appif.ServerServiceIF;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.db.services.SetServiceI;
import com.cw.models.db.services.UserServiceI;
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

    @Bean
    RmiProxyFactoryBean battleService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ServerServiceIF");
        rmiProxyFactory.setServiceInterface(ServerServiceIF.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean userService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/UserServiceI");
        rmiProxyFactory.setServiceInterface(UserServiceI.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean setService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/SetServiceI");
        rmiProxyFactory.setServiceInterface(SetServiceI.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean artefactService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ArtefactServiceI");
        rmiProxyFactory.setServiceInterface(ArtefactServiceI.class);
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
        launch(args);
    }
}

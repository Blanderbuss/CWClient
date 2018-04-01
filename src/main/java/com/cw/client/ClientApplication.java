package com.cw.client;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.UserException;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.db.services.SetServiceI;
import com.cw.models.db.services.UserServiceI;
import com.cw.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
public class ClientApplication {

    private static User user;

    //TODO add methods for creating fighters

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

    private static ServerServiceIF battleService;
	private static UserServiceI userService;
	private static SetServiceI setService;
	private static ArtefactServiceI artefactService;

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ClientApplication.class, args);
        battleService = applicationContext.getBean(ServerServiceIF.class);
        userService = applicationContext.getBean(UserServiceI.class);
        setService = applicationContext.getBean(SetServiceI.class);
        artefactService = applicationContext.getBean(ArtefactServiceI.class);
        battleService.getResult(1, "kek");
        user = new User("Denis", "1234", "e@mail.co", 0, 0);
        //service.register(user);
        System.out.println("Reg succesfull");
        //service.auth(user);
    }
}

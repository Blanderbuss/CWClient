package com.cw.client;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.UserException;
import com.cw.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
public class ClientApplication {

    private static User user;

    //TODO add methods for creating fighters

	@Bean
    RmiProxyFactoryBean service() {
		RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
            rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ServerServiceIF");
		rmiProxyFactory.setServiceInterface(ServerServiceIF.class);
		return rmiProxyFactory;
	}

	public static void main(String[] args) {
	    try{
            ServerServiceIF service = SpringApplication.run(ClientApplication.class, args).
                    getBean(ServerServiceIF.class);
            user    = new User("Denis","1234","e@mail.co");
            service.register(user);
            System.out.println("Reg succesfull");
            service.auth(user);
            System.out.println("I`m authed");
            //TODO send fighter to server
        } catch (UserException e) {
            e.printStackTrace();
        }

    }
}

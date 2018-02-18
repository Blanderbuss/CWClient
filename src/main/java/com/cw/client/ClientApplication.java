package com.cw.client;

import com.cw.appif.ServerServiceIF;
import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.Fighters.Fighter1;
import com.cw.models.Fighters.Fighter2;
import com.cw.models.db.User;
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
            user    = new User("Denis","1234","e@mail.co",0,0);
            service.register(user);
            System.out.println("Reg succesfull");
            service.auth(user);
            System.out.println("I`m authed");
            Fighter1 benis = new Fighter1();
            benis.setName("The Benis");
            benis.setCurSpeed(10);
            benis.setCurHp(50);
            service.regFighter(benis);
            System.out.println("Fighter1 registered");
            Fighter2 denis = new Fighter2();
            denis.setName("The Denis");
            denis.setCurSpeed(10);
            denis.setCurHp(50);
            service.regFighter(denis);
            System.out.println("Fighter2 registered");
            //TODO send fighter to server
        } catch (UserException e) {
            e.printStackTrace();
        } catch (FighterException e) {
            e.printStackTrace();
        }

    }
}

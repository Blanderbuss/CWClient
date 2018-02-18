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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            Fighter1 benis = new Fighter1("The Benis",5);
            service.regFighter(benis);
            System.out.println("Fighter1 registered");
            Fighter2 denis = new Fighter2("The Denis",10);
            service.regFighter(denis);
            System.out.println("Fighter2 registered");
        } catch (UserException e) {
            e.printStackTrace();
        } catch (FighterException e) {
            e.printStackTrace();
        }

    }

    //For testing
    Fighter1 sendNewFighter() throws IOException {
        //Input block
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter fighter name");
        String name = br.readLine();
        System.out.println("Enter fighter lvl");
        int lvl = Integer.valueOf(br.readLine());
        //End of input block
        return new Fighter1(name, lvl);
    }
}

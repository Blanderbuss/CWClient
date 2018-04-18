package com.cw.client;

import com.cw.exceptions.UserNotFoundException;
import com.cw.services.SessionServiceI;
import com.cw.entities.Artefact;
import com.cw.entities.Set;
import com.cw.entities.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SessionServiceUserCredentialsTests.class)
public class SessionServiceUserCredentialsTests {
    @Autowired
    private ApplicationContext ctx;
    private SessionServiceI session;

    static final String address = "localhost";

    private static User user1, user2;
    // is meant to be added to userComplex;
    private static List<Artefact> artefacts;
    // is meant to be added to userComplex;
    private static List<Set> sets;

    @BeforeClass
    public static void initTests() {
        user1 = new User("qwertywerwr", "123456", "qwesadf@asd.cs", 10, 11);
        user2 = new User("asdfgh", "asdfgh", "qwe2@asd.cs", 10, 11);
    }

    @Before
    public void initSession() {
        if (session == null)
            session = ctx.getBean(SessionServiceI.class);
        session.register(user1.getUsername(), user1.getEmail(), user1.getPass());
        session.register(user2.getUsername(), user2.getEmail(), user2.getPass());
    }

    @After
    public void cleanUpSession() {
        String userToken = login(user1.getEmail(), user1.getPass());
        session.deleteMyUser(userToken);
        String userToken2 = login(user2.getEmail(), user2.getPass());
        session.deleteMyUser(userToken2);
    }

    @Bean
    RmiProxyFactoryBean sessionService() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://"+ address +":1099/SessionServiceI");
        rmiProxyFactory.setServiceInterface(SessionServiceI.class);
        return rmiProxyFactory;
    }

    @Test
    public void verifyContextLoaded() {
        assertNotNull(ctx);
        assertNotNull(session);
    }

    @Test
    public void verifySimpleLoginWithValidCredentials() {
        String token = login(user1.getEmail(), user1.getPass());
        assertTrue(session.isLoggedInByToken(token));
    }

    @Test
    public void verifyLoginWithInvalidCredentials() {
        String token = login("invalid_email", "pwd");
        assertFalse(session.isLoggedInByToken(token));
    }

    @Test
    public void verifyLoginWithAnotherActiveSessionLinkingToUser() {
        String token = login(user1.getEmail(), user1.getPass());
        assertTrue(session.isLoggedInByToken(token)); // new token is valid
        String newToken = login(user1.getEmail(), user1.getPass());
        assertFalse(session.isLoggedInByToken(token)); // old token is invalid
        assertTrue(session.isLoggedInByToken(newToken)); // new token is valid
        String newNewToken = login(user1.getEmail(), user1.getPass());
        assertFalse(session.isLoggedInByToken(token)); // old token is invalid
        assertFalse(session.isLoggedInByToken(newToken)); // old token is invalid
        assertTrue(session.isLoggedInByToken(newNewToken)); // new token is valid
        session.logout(newNewToken);
        assertFalse(session.isLoggedInByToken(newNewToken));
    }

    @Test
    public void verifyDeletionOfMyUser() {
        String token = login(user1.getEmail(), user1.getPass());
        assertTrue(session.isLoggedInByToken(token));
        assertTrue(session.deleteMyUser(token));
        assertFalse(session.isLoggedInByToken(token)); // session finishes on user deletion
    }

    @Test
    public void verifyDeletionWithInvalidToken() {
        String token = "qwerty";
        assertFalse(session.isLoggedInByToken(token));
        assertFalse(session.deleteMyUser(token));
    }

    @Test
    public void verifyInvalidTokens() {
        assertFalse(session.isLoggedInByToken(null));
        assertFalse(session.isLoggedInByToken(""));
        assertFalse(session.isLoggedInByToken("qwerty"));
    }

    @Test
    public void verifyRegistration() {
        User user3 = new User("lamenfviontbgf", "asdfgh", "lhkmjrgkdunvb@gdf.wcs", 10, 11);
        assertFalse(session.isUserRegistered(user3.getEmail()));
        assertTrue( session.register(user3.getUsername(), user3.getEmail(), user3.getPass()) );
        assertTrue(session.isUserRegistered(user3.getEmail()));
        String token = login(user3.getEmail(), user3.getPass());
        assertTrue(session.isLoggedInByToken(token));
        assertTrue(session.deleteMyUser(token));
    }

    private String login(String email, String pwd) {
        String result = "";
        try {
            result = session.login(email, pwd).val1;
        } catch (UserNotFoundException e) {
            //e.printStackTrace();
            //fail();
        }
        return result;
    }

}

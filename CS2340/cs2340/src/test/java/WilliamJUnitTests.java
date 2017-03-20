package test.java;

import main.java.controller.LoginScreenController;
import main.java.model.User;
import org.junit.Before;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static junit.framework.Assert.assertTrue;

/**
 * Created by williamcheng on 11/15/16.
 * Tests isValidLogin
 * private boolean isValidLogin(User currentUser, String username, String password, ArrayList<User> userList)
 *
 */
public class WilliamJUnitTests {
    private LoginScreenController c;

    @Before
    public void setUpController() {
        c = new LoginScreenController();
    }

    /**
     * checks if everything works
     */
    @Test
    public void test1() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u", "FALSE", "0");
        userList.add(u1);
        System.out.print(u1);
        System.out.println(c);
        assertTrue(c.isValidLogin("wilche", "pass", userList));
    }

    /**
     * checks password failure
     */
    @Test
    public void test2() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u", "FALSE", "0");
        userList.add(u1);
        assertTrue(!c.isValidLogin("wilche", "bleh", userList));
    }


    /**
     * checks arraylist with nothing in it.
     */
    @Test
    public void test3() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<>();
        assertTrue(!c.isValidLogin("wilche", "pass", userList));
    }

    /**
     * checks username failure.
     */
    @Test
    public void test4() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u", "FALSE", "0");
        userList.add(u1);
        assertTrue(!c.isValidLogin("pew pew", "pass", userList));
    }

}

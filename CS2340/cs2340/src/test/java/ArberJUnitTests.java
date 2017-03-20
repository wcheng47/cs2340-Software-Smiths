package test.java;

import main.java.controller.RegistrationScreenController;
import main.java.model.User;
import org.junit.Before;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Arber on 11/15/2016.
 * This class's purpose is to: Test that users' Types are properly set and
 * that their first and last names are valid
 */
public class ArberJUnitTests {
    private RegistrationScreenController controller;

    @Before
    public void setUpController() {
        controller = new RegistrationScreenController();
    }

    @Test
    public void testUserTypeSet() {
        User.Type expectedType = User.Type.USER;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "User", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test
    public void testWorkerTypeSet() {
        User.Type expectedType = User.Type.WORKER;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "Worker", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test
    public void testManagerTypeSet() {
        User.Type expectedType = User.Type.MANAGER;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "Manager", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test
    public void testAdminTypeSet() {
        User.Type expectedType = User.Type.ADMIN;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "Admin", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test
    public void testDefaultTypeWithNonEmptyStringSet() {
        User.Type expectedType = User.Type.USER;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "NoType", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test
    public void testDefaultTypeWithEmptyStringSet() {
        User.Type expectedType = User.Type.USER;
        User userTypeUser = new User("foo", "bar", "bam", "baz", "", "FALSE", "0");
        assertTrue(userTypeUser.getType().equals(expectedType));
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidFirstName() throws Exception {
        User invalidFirstNameValidLastName = new User("1234", "Last",
                "userName", "password", "Manager", "FALSE", "0");
        controller.checkValidName(invalidFirstNameValidLastName.getFirstName
                (), invalidFirstNameValidLastName.getLastName());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidLastName() throws Exception {
        User invalidFirstNameValidLastName = new User("First", "1234",
                "userName", "password", "Manager", "FALSE", "0");
        controller.checkValidName(invalidFirstNameValidLastName.getFirstName
                (), invalidFirstNameValidLastName.getLastName());
    }

}
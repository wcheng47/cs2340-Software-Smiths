package test.java;
import main.java.controller.CreateProfileScreenController;
import org.testng.annotations.Test;
import main.java.model.Profile;


import org.junit.Before;




/**
 * Created by Yash on 11/15/2016.
 * These are JUnit tests for the validity of a profile that is being created
 */
public class YashJUnitTests {
    private CreateProfileScreenController controller;

    @Before
    public void setUpController() {
        controller = new CreateProfileScreenController();
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidEmail() throws Exception {
        Profile profile = new Profile("ysinghgmail.com", "1234567890", "123 Boot Road", "Atlanta", "Georgia", "30332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidPhone() throws Exception {
        Profile profile = new Profile("ysingh@gmail.com", "1234567890", "123 Boot Road", "Atlanta", "Georgia", "30332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidStreet() throws Exception {
        Profile profile = new Profile("ysingh@gmail.com", "1234567890", "123 Boot Road!", "Atlanta", "Georgia", "30332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidCity() throws Exception {
        Profile profile = new Profile("ysingh@gmail.com", "1234567890", "123 Boot Road", "Atlanta9", "Georgia", "30332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidState() throws Exception {
        Profile profile = new Profile("ysingh@gmail.com", "1234567890", "123 Boot Road", "Atlanta", "Georgia!", "30332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

    @Test(expectedExceptions = Exception.class)
    public void testInvalidZip() throws Exception {
        Profile profile = new Profile("ysingh@gmail.com", "134567890", "123 Boot Road", "Atlanta", "Georgia", "3a332");
        controller.isValidProfile(profile.get_email(), profile.get_phoneNumber(), profile.get_streetAddress(),
                profile.get_city(), profile.get_state(), profile.get_zipcode());
    }

}

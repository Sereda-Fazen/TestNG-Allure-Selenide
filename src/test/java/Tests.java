import org.apache.tools.ant.taskdefs.Sleep;
import pages.Sign;
import config.Values;
import listeners.AllureOnFailListener;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import steps.Steps;

import static mail.CheckUnreadEmail.checkUnreadEmail;
import static mail.CheckUnreadEmail.cleaningMailbox;


@Listeners(AllureOnFailListener.class)
@Title("Test")
public class Tests extends BeforeTests {

    private Sign login;
    private Steps steps;


    @Step("Create new user, Step {0}, Status \"{1}\"")
    private String createUserStep() {
        String userEmail = Values.email + "+" + getRandomString(8) + Values.email_domain;
        System.out.println(userEmail);
        return userEmail;
    }

    private static String getRandomString(int l) {
        String tag = "";
        for (int i =0; i<l; i++) {
            tag = tag + (char) ((int) (Math.random() * 25 + 97));
        }
        return tag;
    }


//    @Test(priority = 2)
//    public void userLogin() {
//
//        login = new Sign();
//        login.openHomePage();
//        login.enterLogin(Values.enterLogin);
//        login.enterPass(Values.enterPassword);
//        login.clickButton();
//        login.dashboard("Dashboard");
//
//        steps = new Steps();
//        steps.screenShot("dashboard.png");
//
//    }

    @Test(priority = 1)
    public void checkRandEmail() throws InterruptedException {
        login = new Sign();
        login.openHomePage();
        login.forgotPassword("auto.testfloship1+fflwhyzv@gmail.com");
        String test = checkUnreadEmail(Values.host);
        login.openPageForNewPassword(test);
        Thread.sleep(3000);
        cleaningMailbox();

        steps = new Steps();
        steps.screenShot("password.png");

    }




}




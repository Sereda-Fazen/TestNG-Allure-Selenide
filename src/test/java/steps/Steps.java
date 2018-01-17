package steps;

import config.Values;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static listeners.ScreenShoter.makeScreenshot;

public class Steps {


    @Step("Screenshot")
    public void screenShot(String name) {
        try {
            makeScreenshot(name);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    Email
     */

    @Step("Create new user, Step {0}, Status \"{1}\"")
    public void createUserStep() {
        String userEmail = Values.email + "+" + getRandomString(8) + Values.email_domain;
        System.out.println(userEmail);
    }

    private static String getRandomString(int l) {
        String tag = "";
        for (int i =0; i<l; i++) {
            tag = tag + (char) ((int) (Math.random() * 25 + 97));
        }
        return tag;
    }















}


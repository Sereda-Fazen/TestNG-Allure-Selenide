package pages;

import config.Values;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class Sign {

    @Step("Open Home Page")
    public void openHomePage() {
        open(Values.host);
    }
    @Step("Enter login")
    public void enterLogin(String login) {
        $(By.xpath("//button[text()='Sign In']")).shouldHave(visible, text(Values.text));
        $("#validation-email").setValue(login);
    }
    @Step("Click Forgot Password")
    public void forgotPassword(String email){
        $(By.xpath("//a[text()='Forgot Password?']")).shouldHave(visible).click();
        $("h3.text-center").shouldHave(text("Password reset"));
        $("#id_email").setValue(email);
        $(By.xpath("//button[text()='Reset my password']")).should().click();
        $("h3").shouldHave(visible,text("Password reset sent"));
    }



    @Step("Enter password")
    public void enterPass(String pass){
        $("#validation-password").setValue(pass);

    }
    @Step("Click on the button - Sign in")
    public void clickButton(){
        $(By.xpath("//button[text()='Sign In']")).shouldHave(visible).click();
    }

    @Step("I should see - Dashboard")
    public void dashboard(String text){
        $("li > span").shouldHave(visible, text(text));
    }

    //email

    @Step("Create new user, Step {0}, Status \"{1}\"")
    private String createUserStep(int step, String status) {
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

    @Step("Enter new password")
    public void openPageForNewPassword(String link) {
        open(link);
    }





}


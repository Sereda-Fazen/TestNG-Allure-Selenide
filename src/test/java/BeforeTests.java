import config.Values;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BeforeTests {


    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        //Doesn't matter chrome or Chrome as this is case insensitive.
        System.setProperty("selenide.browser", "Chrome");
    }


    @AfterClass
    public void tearDown() {
        getWebDriver().quit();
    }

}

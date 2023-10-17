package StepDefinitions;

import Pages.MainPage;
import Pages._SpeechTextPage;
import Utils.Driver;
import Utils.MyUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class _Type_Stepdef {
    private _SpeechTextPage sp = new _SpeechTextPage();


    @Given("Go to {string}")
    public void goTo(String url) {
        Driver.getDriver().get(url);
    }

    @And("Get started the microphone")
    public void getStartedTheMicrophone() {
        MyUtils.clickFunction(sp.getElement("startStopButton"));
    }

    @Then("Lesson and display with Robot Class")
    public void lessonAndDisplayWithRobotClass() {
    }
}

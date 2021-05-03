package billableHour;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.TestBase;
import utils.TestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseCSV extends TestBase {



    @Parameters("dataEnv")
    public static void enterCsvFilePath(String dataEnv, String csvTestCase, String csv) throws InterruptedException, IOException, ParseException {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/" + dataEnv + "/filePath.json"));
        JSONObject envs = (JSONObject) config.get(csvTestCase);
        String filePath = (String) envs.get("filePath");

        File path = new File(System.getProperty("user.dir") + "/files/" + csv);
        getDriver().pushFile(filePath, path);

        TestUtils.testTitle("Enter file path: (" + filePath + ") to continue");
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Billable Hours']","Billable Hours");

        Thread.sleep(200);
        Actions action = new Actions(getDriver());
        action.sendKeys(filePath).perform();
        getDriver().findElement(By.xpath("//android.view.View[@index='3']")).click();
        Thread.sleep(300);
    }


    @Parameters("dataEnv")
    @Test
    public static void emptyCsvFileWithNoContent(String dataEnv) throws InterruptedException,IOException,ParseException {
        enterCsvFilePath(dataEnv,"EmptyCsvFile","EmptyIdAndProjects.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Empty CSV cannot be uploaded");
        getDriver().findElement(By.xpath("//android.widget.Button[@index='0']")).click();
        Thread.sleep(500);
    }

    @Parameters("dataEnv")
    @Test
    public static void csvFileWithHigherStartTime(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"higherStartTime","highStartTime - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with a higher start time than the end time cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void csvFileWithEmptyProjectName(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"emptyProjectName","SomeEmptyProject1 - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with an empty project name cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void csvFileWithEmptyBillableRate(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"emptyBillableRate","SomeEmptyBillrate1 - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with an empty Billable rate cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void csvFileWithEmptyEmployeeId(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"emptyEmployeeId","SomeEmptyEmployeeId1 - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with an empty Employee ID column cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void csvFileWithSameStartTimeAndEndTime(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"sameStartTimeAndEndTime","sameTime - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with invalid time format cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void csvFileWithEmptyBillableTime(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"emptyBillableTime","SomeEmptyFields1 - Sheet1.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV with invalid time format cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void randomCsvFileWithInvalidFormat(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"randomCsvFile","device-list.csv");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","CSV file that is not in billable hours format cannot be uploaded");
    }
    @Parameters("dataEnv")
    @Test
    public static void fileThatIsNotACsv(String dataEnv) throws InterruptedException,IOException,ParseException {
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).click();
        getDriver().findElement(By.xpath("//android.widget.EditText[@index='2']")).clear();
        enterCsvFilePath(dataEnv,"pdfFilePath","device-list.pdf");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","File not a CSV cannot be uploaded");
    }

}

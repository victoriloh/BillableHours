package billableHour;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.AssertBase;
import utils.TestBase;
import utils.TestUtils;

import static billableHour.ParseCSV.enterCsvFilePath;

public class SameIdDifferentCompany extends TestBase {
    @Parameters("dataEnv")
    @Test
    public static void parseCsvForSameEmployeeIdDifferentCompany(String dataEnv) throws Exception {
        enterCsvFilePath(dataEnv,"sameEmployeeIdDifferentCompany","SameIdDifferentProjects11 - Sheet1.csv");
        TestUtils.testTitle("Parse result for a CSV with different Employee's Id and Different Company Name");
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Parse Results");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]","CSV File has been parsed successfully, see summary below");
        TestUtils.testTitle("Verify that user is able to navigate back to screen 1 using the back arrow button");
        getDriver().findElement(By.xpath("//android.widget.Button[@index='0']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Billable Hours']","Billable Hours");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='1']","Enter the file path below");
        TestUtils.elementIsPresent(By.xpath("//android.view.View[@index='3']"),"Continue Button");
        Thread.sleep(1000);
        TestUtils.testTitle("Navigate back unto the Parse Result page");
        getDriver().findElement(By.xpath("//android.view.View[@index='3']")).click();
        Thread.sleep(700);
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Parse Results");
        TestUtils.testTitle("Verify the header of the Parse result table info");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]","S.N");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]","Company");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]","Invoice Value");
        AssertBase.sameEmployeeIdDifferentProject();
    }
    @Parameters("dataEnv")
    @Test
    public static void getCompanyInfoForSN1(String dataEnv) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        TestUtils.testTitle("Verify that clicking on the company name navigates user to the company info page");
        getDriver().findElement(By.xpath("//android.view.View[@index='5']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","Loyal");
        TestUtils.testTitle("Verify that user is able to navigate back to the parse result page");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View", "Parse Results");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]","CSV File has been parsed successfully, see summary below");
        TestUtils.testTitle("Navigate back to the Company info page for Loyal");
        getDriver().findElement(By.xpath("//android.view.View[@index='5']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","Loyal");
        TestUtils.testTitle("Verify the header of the Company Info table");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='3']","Employee ID");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='4']","Number of Hours");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='5']","Unit Price");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='6']","Cost");
        TestUtils.testTitle("Verify the cost of each employee is in Naira and the Total is also in Naira");
        testInfo.get().info("Cost for Employee1");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='10']","N300.00");
        testInfo.get().info("Total Cost for Employees");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='12']","N300.00");
        TestUtils.testTitle("Verify the  total cost for the company for the employees");
        AssertBase.assertCostForDifferentEmployeeDifferentCompany();
    }

    @Parameters("dataEnv")
    @Test
    public static void getCompanyInfoForSN2(String dataEnv) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        TestUtils.testTitle("Navigate Back to the Parse Result page to check the second Company Info");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Parse Results");
        TestUtils.testTitle("Verify that clicking on the company name navigates user to the company info page");
        getDriver().findElement(By.xpath("//android.view.View[@index='8']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","BundleBlow");
        TestUtils.testTitle("Verify that user is able to navigate back to the parse result page");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View", "Parse Results");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]","CSV File has been parsed successfully, see summary below");
        TestUtils.testTitle("Navigate back to the Company info page for BundleBlow");
        getDriver().findElement(By.xpath("//android.view.View[@index='8']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","BundleBlow");
        TestUtils.testTitle("Verify the header of the Company Info table");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='3']","Employee ID");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='4']","Number of Hours");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='5']","Unit Price");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='6']","Cost");
        TestUtils.testTitle("Verify the cost of each employee is in Naira and the Total is also in Naira");
        testInfo.get().info("Cost for Employee1");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='10']","N6750.00");
        testInfo.get().info("Total Cost for Employees");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='12']","N6750.00");
        TestUtils.testTitle("Verify the  total cost for the company for the employees");
        AssertBase.assertCostForDifferentEmployeeDifferentCompany();
    }

    @Parameters("dataEnv")
    @Test
    public static void getCompanyInfoForSN3(String dataEnv) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        TestUtils.testTitle("Navigate Back to the Parse Result page to check the second Company Info");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Parse Results");
        TestUtils.testTitle("Verify that clicking on the company name navigates user to the company info page");
        getDriver().findElement(By.xpath("//android.view.View[@index='11']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","ETCBundle");
        TestUtils.testTitle("Verify that user is able to navigate back to the parse result page");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View", "Parse Results");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]","CSV File has been parsed successfully, see summary below");
        TestUtils.testTitle("Navigate back to the Company info page for ETCBundle");
        getDriver().findElement(By.xpath("//android.view.View[@index='11']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","ETCBundle");
        TestUtils.testTitle("Verify the header of the Company Info table");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='3']","Employee ID");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='4']","Number of Hours");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='5']","Unit Price");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='6']","Cost");
        TestUtils.testTitle("Verify the cost of each employee is in Naira and the Total is also in Naira");
        testInfo.get().info("Cost for Employee1");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='10']","N2360.00");
        testInfo.get().info("Total Cost for Employees");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='12']","N2360.00");
        TestUtils.testTitle("Verify the  total cost for the company for the employees");
        AssertBase.assertCostForDifferentEmployeeDifferentCompany();
    }
    @Parameters("dataEnv")
    @Test
    public static void getCompanyInfoForSN4(String dataEnv) throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        TestUtils.testTitle("Navigate Back to the Parse Result page to check the second Company Info");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","//android.view.View[@text='Parse Results']", "Parse Results");
        TestUtils.testTitle("Verify that clicking on the company name navigates user to the company info page");
        getDriver().findElement(By.xpath("//android.view.View[@index='14']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","Whatsapp");
        TestUtils.testTitle("Verify that user is able to navigate back to the parse result page");
        getDriver().findElement(By.xpath("//android.widget.ImageView[@index='1']")).click();
        Thread.sleep(500);
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View", "Parse Results");
        TestUtils.assertSearchText("XPATH","/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]","CSV File has been parsed successfully, see summary below");
        TestUtils.testTitle("Navigate back to the Company info page for Whatsapp");
        getDriver().findElement(By.xpath("//android.view.View[@index='14']")).click();
        Thread.sleep(1000);
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='2']","Whatsapp");
        TestUtils.testTitle("Verify the header of the Company Info table");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='3']","Employee ID");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='4']","Number of Hours");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='5']","Unit Price");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='6']","Cost");
        TestUtils.testTitle("Verify the cost of each employee is in Naira and the Total is also in Naira");
        testInfo.get().info("Cost for Employee1");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='10']","N10080.00");
        testInfo.get().info("Total Cost for Employees");
        TestUtils.assertSearchText("XPATH","//android.view.View[@index='12']","N10080.00");
        TestUtils.testTitle("Verify the  total cost for the company for the employees");
        AssertBase.assertCostForDifferentEmployeeDifferentCompany();
    }
}

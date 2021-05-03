package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class AssertBase extends TestBase {
    private static StringBuffer verificationErrors = new StringBuffer();

    public static void assertCostForEmployee() throws Exception{
        String employeeId = getDriver().findElement(By.xpath("//android.view.View[@index='7']")).getText();
        String NumberOfHours = getDriver().findElement(By.xpath("//android.view.View[@index='8']")).getText();
        String UnitPrice = getDriver().findElement(By.xpath("//android.view.View[@index='9']")).getText();
        String cost = getDriver().findElement(By.xpath("//android.view.View[@index='10']")).getText();
        String employeeId1 = getDriver().findElement(By.xpath("//android.view.View[@index='11']")).getText();
        String NumberOfHours1 = getDriver().findElement(By.xpath("//android.view.View[@index='12']")).getText();
        String UnitPrice1 = getDriver().findElement(By.xpath("//android.view.View[@index='13']")).getText();
        String cost1 = getDriver().findElement(By.xpath("//android.view.View[@index='14']")).getText();
        String TotalAmount = getDriver().findElement(By.xpath("//android.view.View[@index='16']")).getText();
        int actualNumberOfHours = TestUtils.convertToInt(NumberOfHours)/10;
        int actualUnitPrice = TestUtils.convertToInt(UnitPrice)/10;
        int actualCost = TestUtils.convertToInt(cost)/10;
        int actualTotalAmount = TestUtils.convertToInt(TotalAmount)/10;

        int actualNumberOfHours1 = TestUtils.convertToInt(NumberOfHours1)/10;
        int actualUnitPrice1 = TestUtils.convertToInt(UnitPrice1)/10;
        int actualCost1 = TestUtils.convertToInt(cost1)/10;

        int expectedCost = actualNumberOfHours * actualUnitPrice ;
        int expectedCost1 = actualNumberOfHours1 * actualUnitPrice1;
        int expectedTotalAmount = actualCost + actualCost1;
        try {
            Assert.assertEquals(actualCost,expectedCost);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId + ") ("+ expectedCost+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours + ") * Unit Price (" +actualUnitPrice+") .");
            Assert.assertEquals(actualCost1,expectedCost1);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId1 + ") ("+ expectedCost1+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours1 + ") * Unit Price (" +actualUnitPrice1+") .");
            Assert.assertEquals(actualTotalAmount,expectedTotalAmount);
            testInfo.get().log(Status.INFO,"Total Cost for Employee ("+ employeeId1 + ")  ("+ expectedCost1+ ") is equal to the Cost for employee 1 ("
                    + actualCost + ") + Cost for employee (" +actualUnitPrice1+") .");
        }catch (Error e){
            verificationErrors.append(e.toString());
            String verificationErrorString = verificationErrors.toString();
            testInfo.get().error("Summation not equal");
            testInfo.get().error(verificationErrorString);
        }
    }
	
	public static void sameEmployeeIdSameProject() throws Exception {

			String assertDetails = "Asserting Parse result of company details and Invoice Value";
			Markup ad = MarkupHelper.createLabel(assertDetails, ExtentColor.BLUE);
			testInfo.get().info(ad);
			TestUtils.testTitle("Asserting Parse result of company details and Invoice Value");
			String serialNumber = getDriver().findElement(By.xpath("//android.view.View[@index='4']")).getText();
			String company = getDriver().findElement(By.xpath("//android.view.View[@index='5']")).getText();
			String invoiceId = getDriver().findElement(By.xpath("//android.view.View[@index='6']")).getText();
			String serialNumber2 = getDriver().findElement(By.xpath("//android.view.View[@index='7']")).getText();
            String company2 = getDriver().findElement(By.xpath("//android.view.View[@index='8']")).getText();
            String invoiceId2 = getDriver().findElement(By.xpath("//android.view.View[@index='9']")).getText();

			String NA = "";

			String[] toList = { "S.N : " + serialNumber, "Company: " + company, "Invoice Value: " + invoiceId,
					"S.N - 2: " + serialNumber2, "Company - 2: " + company2, "Invoice Value - 2: " + invoiceId2 };

			for (String field : toList) {
				String name = "";
				String val = NA;
				try {
					String[] fields = field.split(":");
					name = fields[0];
					val = fields[1];
					Assert.assertNotEquals(val, NA);
					testInfo.get().log(Status.INFO, "<b>" +name + " : </b>" + val);
				} catch (Error e) {
					testInfo.get().error("<b>" + name + " : </b>" + val);
				}
			}
		}

    public static void differentEmployeeIdSameProject() throws Exception {

        String assertDetails = "Asserting Parse result of company details and Invoice Value";
        Markup ad = MarkupHelper.createLabel(assertDetails, ExtentColor.BLUE);
        testInfo.get().info(ad);
        TestUtils.testTitle("Asserting Parse result of company details and Invoice Value");
        String serialNumber = getDriver().findElement(By.xpath("//android.view.View[@index='4']")).getText();
        String company = getDriver().findElement(By.xpath("//android.view.View[@index='5']")).getText();
        String invoiceId = getDriver().findElement(By.xpath("//android.view.View[@index='6']")).getText();

        String NA = "";

        String[] toList = { "S.N : " + serialNumber, "Company: " + company, "Invoice Value: " + invoiceId };

        for (String field : toList) {
            String name = "";
            String val = NA;
            try {
                String[] fields = field.split(":");
                name = fields[0];
                val = fields[1];
                Assert.assertNotEquals(val, NA);
                testInfo.get().log(Status.INFO, "<b>" +name + " : </b>" + val);
            } catch (Error e) {
                testInfo.get().error("<b>" + name + " : </b>" + val);
            }
        }
    }
    public static void assertCostForDifferentEmployeeIdSameCompany() throws Exception{
        String employeeId = getDriver().findElement(By.xpath("//android.view.View[@index='7']")).getText();
        String NumberOfHours = getDriver().findElement(By.xpath("//android.view.View[@index='8']")).getText();
        String UnitPrice = getDriver().findElement(By.xpath("//android.view.View[@index='9']")).getText();
        String cost = getDriver().findElement(By.xpath("//android.view.View[@index='10']")).getText();
        String employeeId1 = getDriver().findElement(By.xpath("//android.view.View[@index='11']")).getText();
        String NumberOfHours1 = getDriver().findElement(By.xpath("//android.view.View[@index='12']")).getText();
        String UnitPrice1 = getDriver().findElement(By.xpath("//android.view.View[@index='13']")).getText();
        String cost1 = getDriver().findElement(By.xpath("//android.view.View[@index='14']")).getText();
        String employeeId2 = getDriver().findElement(By.xpath("//android.view.View[@index='15']")).getText();
        String NumberOfHours2 = getDriver().findElement(By.xpath("//android.view.View[@index='16']")).getText();
        String UnitPrice2 = getDriver().findElement(By.xpath("//android.view.View[@index='17']")).getText();
        String cost2 = getDriver().findElement(By.xpath("//android.view.View[@index='18']")).getText();
        String TotalAmount = getDriver().findElement(By.xpath("//android.view.View[@index='20']")).getText();

        int actualNumberOfHours = TestUtils.convertToInt(NumberOfHours)/10;
        int actualUnitPrice = TestUtils.convertToInt(UnitPrice)/10;
        int actualCost = TestUtils.convertToInt(cost)/10;

        int actualTotalAmount = TestUtils.convertToInt(TotalAmount)/10;


        int actualNumberOfHours1 = TestUtils.convertToInt(NumberOfHours1)/10;
        int actualUnitPrice1 = TestUtils.convertToInt(UnitPrice1)/10;
        int actualCost1 = TestUtils.convertToInt(cost1)/10;


        int actualNumberOfHours2 = TestUtils.convertToInt(NumberOfHours2)/10;
        int actualUnitPrice2 = TestUtils.convertToInt(UnitPrice2)/10;
        int actualCost2 = TestUtils.convertToInt(cost2)/10;


        int expectedCost = actualNumberOfHours * actualUnitPrice ;
        int expectedCost1 = actualNumberOfHours1 * actualUnitPrice1;
        int expectedCost2 = actualNumberOfHours2 * actualUnitPrice2;
        int expectedTotalAmount = expectedCost + expectedCost1 + expectedCost2;
        try {
            Assert.assertEquals(actualCost,expectedCost);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId + ") ("+ expectedCost+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours + ") * Unit Price (" +actualUnitPrice+") .");
            Assert.assertEquals(actualCost1,expectedCost1);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId1 + ") ("+ expectedCost1+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours1 + ") * Unit Price (" +actualUnitPrice1+") .");
            Assert.assertEquals(actualCost2,expectedCost2);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId2 + ") ("+ expectedCost2+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours2 + ") * Unit Price (" +actualUnitPrice2+") .");
            Assert.assertEquals(actualTotalAmount,expectedTotalAmount);
            testInfo.get().log(Status.INFO,"Total Cost for Employee ("+ employeeId1 + ") ("+ expectedCost+ ") ,for Employee("+ employeeId1 + ") ("+ expectedCost1+ ")and for Employee ("+ employeeId2 + ") ("+ expectedCost1+ ") is equal to the  sum of the three cost = "+expectedTotalAmount+".");
        }catch (Error e){
            verificationErrors.append(e.toString());
            String verificationErrorString = verificationErrors.toString();
            testInfo.get().error("Summation not equal");
            testInfo.get().error(verificationErrorString);
        }
    }

    public static void assertCostForDifferentEmployeeDifferentCompany() throws Exception{
        String employeeId = getDriver().findElement(By.xpath("//android.view.View[@index='7']")).getText();
        String NumberOfHours = getDriver().findElement(By.xpath("//android.view.View[@index='8']")).getText();
        String UnitPrice = getDriver().findElement(By.xpath("//android.view.View[@index='9']")).getText();
        String cost = getDriver().findElement(By.xpath("//android.view.View[@index='10']")).getText();
        String TotalAmount = getDriver().findElement(By.xpath("//android.view.View[@index='12']")).getText();
        int actualNumberOfHours = TestUtils.convertToInt(NumberOfHours)/10;
        int actualUnitPrice = TestUtils.convertToInt(UnitPrice)/10;
        int actualCost = TestUtils.convertToInt(cost)/10;
        int actualTotalAmount = TestUtils.convertToInt(TotalAmount)/10;

        int expectedCost = actualNumberOfHours * actualUnitPrice ;
        int expectedTotalAmount = expectedCost;
        try {
            Assert.assertEquals(actualCost,expectedCost);
            testInfo.get().log(Status.INFO,"Cost for Employee ("+ employeeId + ") ("+ expectedCost+ ") is equal to the Number of Hours ("
                    + actualNumberOfHours + ") * Unit Price (" +actualUnitPrice+").");
            Assert.assertEquals(actualTotalAmount,expectedTotalAmount);
            testInfo.get().log(Status.INFO,"Total Cost for Employee ("+ employeeId + ") ("+ expectedCost+ ") is equal to the Cost for employee 1 ("
                    + expectedCost + ").");
        }catch (Error e){
            verificationErrors.append(e.toString());
            String verificationErrorString = verificationErrors.toString();
            testInfo.get().error("Summation not equal");
            testInfo.get().error(verificationErrorString);
        }
    }

    public static void sameEmployeeIdDifferentProject() throws Exception {

        String assertDetails = "Asserting Parse result of company details and Invoice Value";
        Markup ad = MarkupHelper.createLabel(assertDetails, ExtentColor.BLUE);
        testInfo.get().info(ad);
        TestUtils.testTitle("Asserting Parse result of company details and Invoice Value");
        String serialNumber = getDriver().findElement(By.xpath("//android.view.View[@index='4']")).getText();
        String company = getDriver().findElement(By.xpath("//android.view.View[@index='5']")).getText();
        String invoiceId = getDriver().findElement(By.xpath("//android.view.View[@index='6']")).getText();
        String serialNumber2 = getDriver().findElement(By.xpath("//android.view.View[@index='7']")).getText();
        String company2 = getDriver().findElement(By.xpath("//android.view.View[@index='8']")).getText();
        String invoiceId2 = getDriver().findElement(By.xpath("//android.view.View[@index='9']")).getText();
        String serialNumber3 = getDriver().findElement(By.xpath("//android.view.View[@index='10']")).getText();
        String company3 = getDriver().findElement(By.xpath("//android.view.View[@index='11']")).getText();
        String invoiceId3 = getDriver().findElement(By.xpath("//android.view.View[@index='12']")).getText();
        String serialNumber4 = getDriver().findElement(By.xpath("//android.view.View[@index='13']")).getText();
        String company4 = getDriver().findElement(By.xpath("//android.view.View[@index='14']")).getText();
        String invoiceId4 = getDriver().findElement(By.xpath("//android.view.View[@index='15']")).getText();

        String NA = "";

        String[] toList = { "S.N : " + serialNumber, "Company: " + company, "Invoice Value: " + invoiceId,
                "S.N - 2: " + serialNumber2, "Company - 2: " + company2, "Invoice Value - 2: " + invoiceId2,
                "S.N- 3 : " + serialNumber3, "Company - 3: " + company3, "Invoice Value - 3: " + invoiceId3,
                "S.N - 4: " + serialNumber4, "Company - 4: " + company4, "Invoice Value - 4: " + invoiceId4
        };

        for (String field : toList) {
            String name = "";
            String val = NA;
            try {
                String[] fields = field.split(":");
                name = fields[0];
                val = fields[1];
                Assert.assertNotEquals(val, NA);
                testInfo.get().log(Status.INFO, "<b>" +name + " : </b>" + val);
            } catch (Error e) {
                testInfo.get().error("<b>" + name + " : </b>" + val);
            }
        }
    }
}

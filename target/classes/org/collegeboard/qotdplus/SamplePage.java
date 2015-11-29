package org.collegeboard.qotdplus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.Dimension;
import java.util.NoSuchElementException;

/*
 * @author bnimmagadda
 */

public class SamplePage extends Page {
	//private String env;
	private WebDriver pageDriver;
    AndroidDriver driver;
    Dimension size;
	private java.util.Properties pageprops;



	public SamplePage(WebDriver driver, java.util.Properties props){
		//this.env = env;
		this.pageDriver = driver;
		this.pageprops = props;
	}

	
	public void navigateToMenu() throws Exception{
		//MintLogger.log(Level.INFO, "navigateToAllAccounts");
        WebDriverWait wait = new WebDriverWait(pageDriver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("MENU"))));
        pageDriver.findElement(By.xpath(pageprops.getProperty("MENU"))).click();

//        WebDriverWait wait = new WebDriverWait(pageDriver,120);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageprops.getProperty("MENU"))));
//		FindElementByTagNameAndClick(pageDriver,pageprops.getProperty("MENU"));

	}

    public void ClickToSignin() throws Exception{
        //MintLogger.log(Level.INFO, "navigateToAllAccounts");
        WebDriverWait wait = new WebDriverWait(pageDriver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("SIGN_IN"))));
        pageDriver.findElement(By.xpath(pageprops.getProperty("SIGN_IN"))).click();
//pageDriver.findElement(By.name(pageprops.getProperty("MENU"))).click();

//        WebDriverWait wait = new WebDriverWait(pageDriver,120);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageprops.getProperty("MENU"))));
//		FindElementByTagNameAndClick(pageDriver,pageprops.getProperty("MENU"));

    }



    public void Signin() throws Exception{
        //MintLogger.log(Level.INFO, "navigateToAllAccounts");
        WebDriverWait wait = new WebDriverWait(pageDriver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("USERNAME"))));
        pageDriver.findElement(By.xpath(pageprops.getProperty("USERNAME"))).sendKeys("demouser8");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("PASSWORD"))));
        pageDriver.findElement(By.xpath(pageprops.getProperty("PASSWORD"))).sendKeys("password1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("SIGNINBUTTON"))));
        pageDriver.findElement(By.xpath(pageprops.getProperty("SIGNINBUTTON"))).click();
//pageDriver.findElement(By.name(pageprops.getProperty("MENU"))).click();

//        WebDriverWait wait = new WebDriverWait(pageDriver,120);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageprops.getProperty("MENU"))));
//		FindElementByTagNameAndClick(pageDriver,pageprops.getProperty("MENU"));

    }



    public void swipingHorizontal() throws InterruptedException {
        //Get the size of screen.
        size = driver.manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.
        int startx = (int) (size.width * 0.70);
        //Find endx point which is at left side of screen.
        int endx = (int) (size.width * 0.30);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

        //Horizontal scroll toward right by 50 pixels :
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(50,0)", "");
        //Swipe from Right to Left.

//        driver.swipe(startx, starty, endx, starty, 3000);
//        Thread.sleep(2000);

        //Swipe from Left to Right.
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("android.widget.LinearLayout[1]/")));
        driver.swipe(endx, starty, startx, starty, 3000);
        Thread.sleep(2000);
//        WebDriverWait wait = new WebDriverWait(driver, 300);
//        wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.RelativeLayout")));

    }
	
	
//	public void navigateToCD() throws Exception{
//		   FindElementByTagNameAndClick(pageDriver, pageprops.getProperty("ALL_ACCOUNTS_CD"));
//	}
//
//	public void navigateToChecking() throws Exception{
//		WebDriverWait wait = new WebDriverWait(pageDriver, 120);
//		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_CHECKING"))));
//		   pageDriver.findElement(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_CHECKING"))).click();
//	}
//
//	public void navigateToSavings() throws Exception{
//		WebDriverWait wait = new WebDriverWait(pageDriver, 120);
//		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_SAVINGS"))));
//		   pageDriver.findElement(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_SAVINGS"))).click();
//	}
//
//	public void navigateToVisa() throws Exception{
//		WebDriverWait wait = new WebDriverWait(pageDriver, 120);
//		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_VISA"))));
//		   pageDriver.findElement(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_VISA"))).click();
//	}
//
//	public void navigateToRothIRA() throws Exception{
//		WebDriverWait wait = new WebDriverWait(pageDriver, 120);
//		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_ROTH_IRA"))));
//		   pageDriver.findElement(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_ROTH_IRA"))).click();
//	}
//
//	public boolean validateAllAccountsPageSections() {
//		WebDriverWait wait = new WebDriverWait(pageDriver, 120);
////		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pageprops.getProperty("ALL_ACCOUNTS_CD"))));
//		return (pageDriver.getPageSource().toString().contains("Investment")
//				&& pageDriver.getPageSource().toString().contains("Cash") && pageDriver.getPageSource().toString().contains("Credit Cards"));
//	}
//
//	public void navigateBackToOverviewPage(){
//		WebDriverWait wait =  new WebDriverWait(pageDriver, 120);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(pageprops.getProperty("NAVIGATE_HOME"))));
//		pageDriver.findElement(By.name( pageprops.getProperty("NAVIGATE_HOME"))).click();
//	}
	
	
	private boolean existsElement(String name) {
	    try {
	        pageDriver.findElement(By.name(name));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
}

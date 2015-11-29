package org.collegeboard.qotdplus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class Page {
	Logger CollegeLogger = Logger.getLogger("MyLogger");

	public void FindElementByTagNameAndClick(WebDriver pageDriver,String classType, String searchType,
			String name) {
		List<WebElement> listOfInputs = pageDriver.findElements(By
				.className(classType));
		for (int i = 0; i < listOfInputs.size(); i++) {
			if (listOfInputs.get(i).getAttribute(searchType).equals(name)){
				listOfInputs.get(i).click();
				return;
			}

		}

	}
	
	public void FindElementByTagNameAndClick(WebDriver pageDriver,String val) {
		StringTokenizer st = new StringTokenizer(val,",");
		System.out.println("test");
		   String[] splitArray = val.split(",");
		List<WebElement> listOfInputs = pageDriver.findElements(By
				.className(splitArray[0]));
		for (int i = 0; i < listOfInputs.size(); i++) {
			if (listOfInputs.get(i).getAttribute(splitArray[1]).equals(splitArray[2])){
				listOfInputs.get(i).click();
				return;
			}

		}
		

	}

    public void FindElementByXpathAndClick(WebDriver pageDriver,String val) {
        StringTokenizer st = new StringTokenizer(val, ",");
        System.out.println("testXpath");
        String[] splitArray = val.split(",");
        List<WebElement> listOfInputs = pageDriver.findElements(By
                .xpath(splitArray[0]));
        for (int i = 0; i < listOfInputs.size(); i++) {
            if (listOfInputs.get(i).getAttribute(splitArray[1]).equals(splitArray[2])) {
                listOfInputs.get(i).click();
                return;
            }

        }
    }



	
	public void FindElementByTagNameAndSendKeys(WebDriver pageDriver,String propertyName,String value) {
		StringTokenizer st = new StringTokenizer(propertyName,",");
		   String[] splitArray = propertyName.split(",");
		List<WebElement> listOfInputs = pageDriver.findElements(By
				.className(splitArray[0]));
		for (int i = 0; i < listOfInputs.size(); i++) {
			if (listOfInputs.get(i).getAttribute(splitArray[1]).equals(splitArray[2])){
				listOfInputs.get(i).click();
				listOfInputs.get(i).sendKeys(value);
				return;
			}

		}
		

	}
	
	public void findElelemntByIndexAndClick(WebDriver driver,String classType, int index) {
		/*
		Looping thru elements is not working because there are more than1 image and
		 images dont have names to them.So forced to use index
		*/
		CollegeLogger.log(Level.INFO,"findElelemntByIndexAndClick");
		List<WebElement> listOfInputs = driver.findElements(By
				.className(classType));
				listOfInputs.get(index).click();
	}
	
	public void findElelemntByIndexAndClick(WebDriver driver,String propertyName) {
		/*
		Looping thru elements is not working because there are more than1 image and
		 images dont have names to them.So forced to use index
		*/
		StringTokenizer st = new StringTokenizer(propertyName,",");
		   String[] splitArray = propertyName.split(",");
		   int index = Integer.parseInt(splitArray[1]);
		CollegeLogger.log(Level.INFO,"findElelemntByIndexAndClick");
		List<WebElement> listOfInputs = driver.findElements(By
				.className(splitArray[0]));
				listOfInputs.get(index).click();
	}

}

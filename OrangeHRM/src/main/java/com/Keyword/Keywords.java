package com.Keyword;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.Utility.ReadProertiesData;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keywords {
	public WebDriver driver;
	public WebElement element;
	public Select select;
	public Actions action;
	public List<WebElement> elements;
	public ExtentTest logger;
	public ExtentReports report;
	public ExtentHtmlReporter reporter;
	
	public ReadProertiesData readPropertiesData=new ReadProertiesData();
	
Logger log=Logger.getLogger(Keywords.class);


@BeforeClass
public void startInstance(){
	
	reporter=new ExtentHtmlReporter(new File("./Reports/TestcaseExtentReport_"+ timestamp()+".html"));
	report=new ExtentReports();
	report.attachReporter(reporter);
	
	
	String driverName= readPropertiesData.getGlobalRepoData("browserName");
	
	log.info("Tased this test case"+driverName);
	if(driverName.equalsIgnoreCase("Chrome")){
		System.setProperty("webdriver.chrome.driver", readPropertiesData.getGlobalRepoData("ChromeDriverPath"));
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	log.info("Chrome Driver Launch Sucessfully");
	}

	else if(driverName.equalsIgnoreCase("FireFox")){
		System.setProperty("webdriver.FirefoxDriver.driver", readPropertiesData.getGlobalRepoData("FirefoxDriverPath"));
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		log.info("FireFox Driver Launch Sucessfully");
	}
	else if(driverName.equalsIgnoreCase("IeDriver")){
		System.setProperty("webdriver.FirefoxDriver.driver", readPropertiesData.getGlobalRepoData("IeDriverPath"));
		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();
		log.info("Ie Driver Launch Sucessfully");
	}	
}

public void startReportTest(String testName){
	logger=report.createTest(testName);
	
}
public void addloggerMassage(String infoMassage){
	logger.info(infoMassage);
}
public void getUrl(){
		driver.get(readPropertiesData.getGlobalRepoData("url"));
	log.info("Sucessfully open the window with " + readPropertiesData.getGlobalRepoData("url")+"URL");
logger.info("Sucessfully open the window with " + readPropertiesData.getGlobalRepoData("url")+"URL");
}

	public WebElement findElement(String object){
		String locatorType=readPropertiesData.getObjectRepoData(object).split(":")[0];

		try{
		switch (locatorType){
		case "id":
			element=driver.findElement(By.id(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
		case "className":
			element=driver.findElement(By.className(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
		case "name":
			element=driver.findElement(By.name(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
			
		case "xpath":
			element=driver.findElement(By.xpath(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
		case "tagName":
			element=driver.findElement(By.tagName(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
		case "cssSelector":
			element=driver.findElement(By.cssSelector(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
		case "partialLinkText":
			element=driver.findElement(By.partialLinkText(readPropertiesData.getObjectRepoData(object).split(":")[1]));
			break;
			
			default:
				System.out.println("Locator type is not valid type");
				break;
		}
		logger.log(Status.PASS,"Locator found sucessfully");
		}
		
		catch(WebDriverException e){
		 log.error(e.getMessage());	
		 logger.error(e.getMessage());
		 logger.log(Status.FAIL,"Locator Not found sucessfully");
		 
		}
	 return element;
		
	}
	
	
		
	
	public String timestamp(){
		DateTimeFormatter dft=DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss") ;
		LocalDateTime now=LocalDateTime.now();
	
		return now.format(dft);
		
	}
	
	public void sendkeys(String object,String value){
		findElement(object).sendKeys(value);
		log.info("values send sucessfully");
		logger.info("values send sucessfully");
	}
	
	public void clickonWebElement(String object){
		findElement(object).click();
		log.info("Click on WebElement correctly");
		logger.info("Click on WebElement correctly");
	}
	
	
	public void clickAndHold(String object){
		action = new Actions(driver);
		action.clickAndHold().perform();	
	}
	public void actionclassClickOnElement(String object){
		action = new Actions(driver);
		action.click(findElement(object)).perform();
		//action.moveToElement(findElement(object)).perform();
	}
	
public void selectDropDownByIndex(String object,int number){
	
	element=findElement(object);
	select=new Select(element);

	select.selectByIndex(number);
	}
public void selectDropDownByByValue(String object){
	
	element=findElement(object);
	select=new Select(element);

	select.selectByValue("object");

}
public void selectDropDownByByValueVisibeText(String object ){
	
	element=findElement(object);
	select=new Select(element);

	select.selectByVisibleText("object");

}
public void CheckBoxValue(String object,String... value){
	List <WebElement>elements= driver.findElements(By.xpath(object));
	
	String []array=value;
	for(String Checkboxvalue : array){
		for(WebElement element: elements){
			if(element.getAttribute("value").equals(Checkboxvalue)){
				element.click();
			}
		}
	}
	
}

public void ScreenShotbyAshot(String path){
	Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	try {
		ImageIO.write(screenshot.getImage(), "png", new File(path));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}

public void ScreenShotByTakeScreenShot(String path){
	TakesScreenshot screenshot=(TakesScreenshot) driver;

	File src=screenshot.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(src, new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void ImplicitWait(){
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
}
@AfterClass
public void closeInstance(){
	driver.quit();
	report.flush();
}
public void assertEquealsString(String actual,String expected){
	if(actual.equals(expected)){
		log.info(actual+"and"+ expected+"are equels");
        logger.log(Status.PASS, "Actual and Expected Url are equeal");		
	}
	else{
		log.info(actual+"and"+ expected+"are not equels");
        logger.log(Status.FAIL, "Actual and Expected Url are not equeal");		
	}
}
public String currentUrl(){
log.info("current Url is");
logger.info("current Url is " + driver.getCurrentUrl());
	return driver.getCurrentUrl();
}


public void assertEquealsURL(String text){
	if(currentUrl().equals(text)){
		log.info("Both url are equals");
		logger.log(Status.PASS, "Both Url are Equeals");
	}
	else{
		log.info("Both url are not equals");
		logger.log(Status.FAIL, "Both Url are not Equeals");
	}
}

public String getWebElementText(String object){
	String actualText=findElement(object).getText();
		log.info("Actual Text is " + findElement(object).getText());
		logger.info("Actual Text is "+ findElement(object).getText());
		return actualText;
		
	
}
public void assertEquealsWebElementText(String object,String text){
	if(getWebElementText(object).equals(text)){
		log.info("Both Text are equal");
		logger.log(Status.PASS,"Both Text are equal" );
	}
	else{
		log.info("Both Text are not equal");
		logger.log(Status.FAIL,"Both Text are not equal" );
	}
}
public boolean isEnable(String object){
	boolean result=findElement(object).isEnabled();
	if(result==true){
		log.info("WebElement is Enabled");
	 logger.info("WebElement is Enabled");
	 
	}
	
	
	return result;
	
}

public boolean isPresent(String object){
	boolean result=findElement(object).isDisplayed();
	if(result==true){
		log.info("WebElement is Present");
	 logger.info("WebElement is Present");
	 
	}
	
	
	return result;
}

}
package deu_calendar;
  
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.security.cert.X509Certificate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class Crawling_C {
	public static void main(String[] args) {

	}
	
	public static void DoorCrawling(String doorId, String doorPw) throws Exception{
		// WebDriver ��� ����(�� ���ϰ�� �����ʼ�)
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\atrix\\Desktop\\Git\\DEU-Calendar\\chromedriver_win32\\chromedriver.exe");

	    // ũ�� ȭ�� �� �۾����� Ȯ���� ���� �޼ҵ�(������ �������, ���� �����Ͽ� ũ��â �ȶ�� �� ����)
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    
	    // WebDriver �ν��Ͻ� ����
	    WebDriver driver = new ChromeDriver();

	    try {
	        // �α��� ������ URL
	        String loginUrl = "https://door.deu.ac.kr/sso/login.aspx";

	        // WebDriver�� ���� �α��� �������� �̵�
	        driver.get(loginUrl);
	        try {Thread.sleep(1000);} catch (InterruptedException e) {}		//  1�� ���
	        
	        // �α��ο� �ʿ��� ��Ҹ� ã�Ƽ� ���� �Է�
	        WebElement usernameInput = driver.findElement(By.id("logId"));
	        WebElement passwordInput = driver.findElement(By.id("logPw"));

	        usernameInput.sendKeys(doorId);
	        passwordInput.sendKeys(doorPw);
	        
	        // �α��� ��ư Ŭ��
	        WebElement loginButton = driver.findElement(By.cssSelector("body > form > div:nth-child(5) > div:nth-child(5) > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > a"));
	        loginButton.click();

	        try {Thread.sleep(1000);} catch (InterruptedException e) {}		// 1�� ���
	        
	        // �α��� ���� ������ URL
	        String loggedInUrl = "http://door.deu.ac.kr/MyPage";

	        // �α��� ���� �������� �̵�
	        driver.get(loggedInUrl);
	        try {Thread.sleep(1000);} catch (InterruptedException e) {} 	// 1�� ���

	        // �α��� �� �������� HTML �Ľ� �Ǵ� �ʿ��� �۾� ����(���� �ʿ�)
	        String fixedCode = "CHGB001";	// ���� ������ ��(�̰� ���� ���ΰ��� ��ȸ)
	        List<WebElement> titles = driver.findElements(By.xpath("//a[contains(@href, 'javascript:goRoom') and contains(@href, '" + fixedCode + "')]"));
	        for(int i=0; i<titles.size(); i++) {
	        	WebElement el = titles.get(i);

	        	JavascriptExecutor executor = (JavascriptExecutor) driver;	// ���� ������ �̵��� �ڹٽ�ũ��Ʈ�� �̿�
	        	executor.executeScript("arguments[0].click();", el);	// ���� ������ �̵��� �ڹٽ�ũ��Ʈ�� �̿�
	        	try {Thread.sleep(500);} catch (InterruptedException e) {}		// 1�� ���
	        	
	        	driver.findElement(By.xpath("//*[@id=\"lnbContent\"]/div/div[3]/ul/li/ul/li[3]/a/span")).click();	// ���������� �̵�
	        	
	        	WebElement subs = driver.findElement(By.className("tbl_type"));		// �������������� ������ �������
	        	System.out.println(subs.getText());		// ����� ������ ���� ���
	        	
	        	// ���ǽ� �������� �ٽ� �̵�(2�� �ʼ�)
	        	driver.navigate().back();	// �ڷΰ��� �޼ҵ�
	        	driver.navigate().back();
	    
	        }
	 
	    } finally {
	        // WebDriver ����
	        driver.quit();
	    }
	}


}
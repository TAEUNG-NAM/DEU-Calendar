package deu_calendar;
  
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.security.cert.X509Certificate;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Crawling_C {
	public static void main(String[] args) {

	}
	
	public static boolean DoorCrawling(String doorId, String doorPw) throws Exception{
		// WebDriver 경로 설정(뒤 파일경로 수정필수)
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\byj06\\onedrive - bombom\\OneDrive - 동의대학교\\학교\\컴퓨터공학과\\4학년 2학기\\소프트웨어 공학\\클론5\\DEU-Calendar\\chromedriver_win32\\chromedriver.exe");

	    // 크롬 화면 띄어서 작업순서 확인을 위한 메소드(아직은 적용안함, 추후 적용하여 크롬창 안띄게 할 예정)
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("--enable-automation", "--remote-debugging-port=9222");
	    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
	    
	    // WebDriver 인스턴스 생성
	    WebDriver driver = new ChromeDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));		// 명시적대기를 위함(셀레니움이랑 웹드라이버랑 호환문제로 이렇게만 사용가능!!)

	    try {
	        // 로그인 페이지 URL
	        String loginUrl = "https://door.deu.ac.kr/sso/login.aspx";

	        // WebDriver를 통해 로그인 페이지로 이동
	        driver.get(loginUrl);
	        
	        // 로그인에 필요한 요소를 찾아서 값을 입력
	        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logId")));
	        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logPw")));

	        usernameInput.sendKeys(doorId);
	        passwordInput.sendKeys(doorPw);
	        
	        // 로그인 버튼 클릭
	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > form > div:nth-child(5) > div:nth-child(5) > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > a")));
	        loginButton.click();
	        
	        // 로그인 후의 페이지 URL
	        String loggedInUrl = "http://door.deu.ac.kr/MyPage";
	        // 로그인 후의 페이지로 이동
	        driver.get(loggedInUrl);
	        wait.until(ExpectedConditions.urlToBe(loggedInUrl));
	        

	        // 로그인 후 페이지의 HTML 파싱 또는 필요한 작업 수행(수정 필요)
	        String fixedCode = "CHGB001";	// 과목에 고정된 값(이걸 통해 개인과목 조회)
	        List<WebElement> titles = driver.findElements(By.xpath("//a[contains(@href, 'javascript:goRoom') and contains(@href, '" + fixedCode + "')]"));
	        for(int i=0; i<titles.size(); i++) {
	        	WebElement title = titles.get(i);

	        	JavascriptExecutor executor = (JavascriptExecutor) driver;	// 과목별 페이지 이동은 자바스크립트를 이용
	        	executor.executeScript("arguments[0].click();", title);	// 과목별 페이지 이동은 자바스크립트를 이용
	        	
	        	
	        	System.out.println("테스트");
	        	WebElement subPage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#lnbContent > div > div:nth-child(3) > ul > li > ul > li:nth-child(3) > a"))); // 과제페이지 이동
	        	subPage.click();
	        	System.out.println("테스트2");
	        	
	        	WebElement subs = driver.findElement(By.className("tbl_type"));		// 과제페이지에서 과제란 요소저장
	        	System.out.println(subs.getText());		// 저장된 과제란 전부 출력
	        	
	        	// 강의실 페이지로 다시 이동(2번 필수)
	        	driver.navigate().back();	// 뒤로가기 메소드
	        	driver.navigate().back();
	        	
	        }
	 
	    } catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("로그인 실패!");
	    	JOptionPane.showMessageDialog(null, "로그인 실패!","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	    	
	    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = "C:\\Users\\atrix\\Desktop\\failure_screenshot.png";  // 실패 시 스크린샷 저장 경로
	        Files.copy(screenshotFile.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

	    	return false;
		}finally {
	        // WebDriver 종료
//	        driver.quit();
	    }
	    return true;
	}

}
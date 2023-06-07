package deu_calendar;
  
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		// WebDriver ��� ����(�� ���ϰ�� �����ʼ�)
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\atrix\\Desktop\\Git\\DEU-Calendar\\chromedriver_win32\\chromedriver.exe");

	    // ũ�� ȭ�� �� �۾����� Ȯ���� ���� �޼ҵ�(������ �������, ���� �����Ͽ� ũ��â �ȶ�� �� ����)
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("--enable-automation", "--remote-debugging-port=9222");
	    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
	    
	    // WebDriver �ν��Ͻ� ����
	    WebDriver driver = new ChromeDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));		// �������⸦ ����(�����Ͽ��̶� ������̹��� ȣȯ������ �̷��Ը� ��밡��!!)

	    try {
	        // �α��� ������ URL
	        String loginUrl = "https://door.deu.ac.kr/sso/login.aspx";

	        // WebDriver�� ���� �α��� �������� �̵�
	        driver.get(loginUrl);
	        
	        // �α��ο� �ʿ��� ��Ҹ� ã�Ƽ� ���� �Է�
	        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logId")));
	        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logPw")));

	        usernameInput.sendKeys(doorId);
	        passwordInput.sendKeys(doorPw);
	        
	        // �α��� ��ư Ŭ��
	        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > form > div:nth-child(5) > div:nth-child(5) > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > a")));
	        loginButton.click();
	        
	        // �α��� ���� ������ URL
	        String loggedInUrl = "http://door.deu.ac.kr/MyPage";
	        // �α��� ���� �������� �̵�
	        driver.get(loggedInUrl);
	        wait.until(ExpectedConditions.urlToBe(loggedInUrl));
	        

	        // �α��� �� �������� HTML �Ľ� �Ǵ� �ʿ��� �۾� ����(���� �ʿ�)
	        String fixedCode = "CHGB001";	// ���� ������ ��(�̰� ���� ���ΰ��� ��ȸ)
	        List<WebElement> titles = driver.findElements(By.xpath("//a[contains(@href, 'javascript:goRoom') and contains(@href, '" + fixedCode + "')]"));
	        for(int i=0; i<titles.size(); i++) {
	        	
	        	WebElement title = titles.get(i);

	        	JavascriptExecutor executor = (JavascriptExecutor) driver;	// ���� ������ �̵��� �ڹٽ�ũ��Ʈ�� �̿�
	        	executor.executeScript("arguments[0].click();", title);	// ���� ������ �̵��� �ڹٽ�ũ��Ʈ�� �̿�
	        	
	        	
	        	//System.out.println("�׽�Ʈ");
	        	WebElement subPage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#lnbContent > div > div:nth-child(3) > ul > li > ul > li:nth-child(3) > a"))); // ���������� �̵�
	        	subPage.click();
	        	//System.out.println("�׽�Ʈ2");
	        	
	        	//WebElement subs = driver.findElement(By.className("tbl_type"));		// �������������� ������ �������
	        	//System.out.println(subs.getText());		// ����� ������ ���� ���
	        	
	        	// �����
	            WebElement selectedOption = driver.findElement(By.cssSelector("select.selectSt_0 option[selected]"));

	            // ����� �ؽ�Ʈ ��������
	            String selectedText = selectedOption.getText(); //�����

	            // ��� ���
	            System.out.println(selectedText);
	            
	         // ���̺� ã��
	            WebElement table = driver.findElement(By.className("tbl_type"));

	            // ��ϵ� ������ ������ Ȯ��
	            WebElement noAssignmentElement = table.findElement(By.tagName("td"));
	            String noAssignmentText = noAssignmentElement.getText();

	            // ��ϵ� ������ ���� ��� ���� �������� �̵�
	            if (noAssignmentText.equals("��ϵ� ������ �����ϴ�.")) {
	                System.out.println("��ϵ� ������ �����ϴ�.");
	                
	                
	             // ���ǽ� �������� �ٽ� �̵�(2�� �ʼ�)
		        	driver.navigate().back();	// �ڷΰ��� �޼ҵ�
		        	driver.navigate().back();
	            }
	            else {

	            // ������ �ִ� ��� �� ��ȸ
	            List<WebElement> rows = table.findElements(By.tagName("tr"));
	            for (int k = 1; k < rows.size(); k++) {
	                WebElement row = rows.get(k);

	                // �������� ��������
	                WebElement titleElement = row.findElement(By.cssSelector("td:nth-child(3) > a"));
	                String title2 = titleElement.getText();

	                // ����Ⱓ ��������
	                WebElement periodElement = row.findElement(By.cssSelector("td:nth-child(4)"));
	                String period = periodElement.getText();
	                
	                String[] parts = period.split(" ");
	                String lastPart = parts[parts.length - 2];
	                System.out.println(lastPart);
	                
	                
	                String[] parts2 = lastPart.split("~");
	                String datePart = parts2[1].trim();
	                
	                String[] dateParts = datePart.split("-");
	                String year = dateParts[0];
	                String month = dateParts[1];
	                String day = dateParts[2];
	                
	                int month2 = Integer.parseInt(month);
	                String replaced = String.valueOf(month2);
	                
	                int day2 = Integer.parseInt(day);
	                String replaced2 = String.valueOf(day2);
	                
	                String result = "20"+year+replaced+replaced2;
	
     
	                
	                //�̷��� �ص� �ǳ�
	                
	                String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
	                String username = "c##java";
	                String password = "java123";

	                String subTitle = "New_Sub_Title";
	                String taskTitle = "New_Task_Title";
	     

	                Connection connection = null;
	                try {
	                    connection = DriverManager.getConnection(url, username, password);
	                    System.out.println("DB ���� ����");

	                    String sql = "MERGE INTO SUBJECT s " +
	                            "USING (SELECT ? AS STUDENT_ID, ? AS SUB_TITLE, ? AS TASK_TITLE, ? AS SUB_DATE FROM DUAL) new_data " +
	                            "ON (s.STUDENT_ID = new_data.STUDENT_ID AND s.SUB_TITLE = new_data.SUB_TITLE AND s.TASK_TITLE = new_data.TASK_TITLE AND s.SUB_DATE = new_data.SUB_DATE) " +
	                            "WHEN NOT MATCHED THEN " +
	                            "    INSERT (STUDENT_ID, SUB_TITLE, TASK_TITLE, SUB_DATE) VALUES (new_data.STUDENT_ID, new_data.SUB_TITLE, new_data.TASK_TITLE, new_data.SUB_DATE)";


	                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                    	statement.setInt(1, 20215030);
	                        statement.setString(2, selectedText);
	                        statement.setString(3, title2);
	                        statement.setInt(4, Integer.parseInt(result));

	                        int rowsAffected = statement.executeUpdate();
	         
	                        System.out.println("Rows affected: " + rowsAffected);
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } finally {
	                    if (connection != null) {
	                        try {
	                            connection.close();
	                            System.out.println("DB ���� ����");
	                        } catch (SQLException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	                

	                // ũ�Ѹ��� �����͸� DB�� �����ϴ� �ڵ� �ۼ�
	                // ���÷� ��������� ����Ⱓ�� "assignments" ���̺� �����ϴ� �ڵ� �ۼ�
	                // ���� ���� �ڵ�� ����

	                // DB ���� ���� �� ������ ���� �ڵ� ����
	                
	                System.out.println(title2); //������
	               
	            }
	            
	             
	        	
	        	// ���ǽ� �������� �ٽ� �̵�(2�� �ʼ�)
	        	driver.navigate().back();	// �ڷΰ��� �޼ҵ�
	        	driver.navigate().back();
	            }
	        }
	 
	    } catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("�α��� ����!");
	    	JOptionPane.showMessageDialog(null, "�α��� ����!","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	    	
	    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = "C:\\Users\\atrix\\Desktop\\failure_screenshot.png";  // ���� �� ��ũ���� ���� ���
	        Files.copy(screenshotFile.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

	    	return false;
		}finally {
	        // WebDriver ����
//	        driver.quit();
	    }
	    return true;
	}

}
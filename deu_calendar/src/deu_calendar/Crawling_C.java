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
	public static String student_id;
	public static void main(String[] args) {

	}
	
	public static boolean DoorCrawling(String doorId, String doorPw) throws Exception{
		student_id=doorId;
		// WebDriver 경로 설정(뒤 파일경로 수정필수)
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\atrix\\Desktop\\Git\\DEU-Calendar\\chromedriver_win32\\chromedriver.exe");

	    // 크롬 화면 띄어서 작업순서 확인을 위한 메소드(아직은 적용안함, 추후 적용하여 크롬창 안띄게 할 예정)
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");
	    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
	    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
		options.addArguments("lang=ko_KR");
	    
	    
	    // WebDriver 인스턴스 생성
	    WebDriver driver = new ChromeDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));		// 명시적대기를 위함(셀레니움이랑 웹드라이버랑 호환문제로 이렇게만 사용가능!!)

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
	        

	        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id=\"gnbContent\"]/div/div[1]/a[1]"))));	// 사용자이름 요소설정
            String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element);	// 사용자 이름 자바스크립트로 변수에 저장

            System.out.println(text);
            System.out.println(doorId);
            
          
            
            //����� ����
            String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
            String username = "c##java";
            String password = "java123";


            // JDBC ���� ��ü ����
            Connection connection = null;
            PreparedStatement statement = null;


                try {
                    // Oracle �����ͺ��̽��� ����
                    connection = DriverManager.getConnection(url, username, password);

                    // MERGE �� �ۼ�
                    String sql = "MERGE INTO USER_INFO ui USING (SELECT ? AS STUDENT_ID, ? AS STUDENT_NAME FROM DUAL) d " +
                            "ON (ui.STUDENT_ID = d.STUDENT_ID) " +
                            "WHEN MATCHED THEN UPDATE SET ui.STUDENT_NAME = d.STUDENT_NAME " +
                            "WHEN NOT MATCHED THEN INSERT (STUDENT_ID, STUDENT_NAME) VALUES (d.STUDENT_ID, d.STUDENT_NAME)";

                    // PreparedStatement ��ü ����
                    statement = connection.prepareStatement(sql);

                    // �Ķ���� ����
                    statement.setInt(1, Integer.parseInt(doorId));
                    statement.setString(2, text);

                    // MERGE �� ����
                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected.");

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // JDBC ��ü �ݱ�
                    try {
                        if (statement != null)
                            statement.close();
                        if (connection != null)
                            connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
	        
	        // 로그인 후 페이지의 HTML 파싱 또는 필요한 작업 수행(수정 필요)
	        String fixedCode = "CHGB001";	// 과목에 고정된 값(이걸 통해 개인과목 조회)
	        List<WebElement> titles = driver.findElements(By.xpath("//a[contains(@href, 'javascript:goRoom') and contains(@href, '" + fixedCode + "')]"));
	        for(int i=0; i<titles.size(); i++) {
	        	
	        	WebElement title = titles.get(i);

	        	JavascriptExecutor executor = (JavascriptExecutor) driver;	// 과목별 페이지 이동은 자바스크립트를 이용
	        	executor.executeScript("arguments[0].click();", title);	// 과목별 페이지 이동은 자바스크립트를 이용
	        	
	        	

//	        	WebElement subPage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#lnbContent > div > div:nth-child(3) > ul > li > ul > li:nth-child(3) > a"))); // 과제페이지 이동
//	        	subPage.click();

	        	WebElement subPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@onclick, '/LMS/LectureRoom/CourseHomeworkStudentList/')]")));		// 과제페이지 요소설정
	        	executor.executeScript("arguments[0].click();", subPage);	// 과제페이지 이동
	        	
	        	//WebElement subs = driver.findElement(By.className("tbl_type"));		// 과제페이지에서 과제란 요소저장
	        	//System.out.println(subs.getText());		// 저장된 과제란 전부 출력
	        	
	        	// 과목명
	            WebElement selectedOption = driver.findElement(By.cssSelector("select.selectSt_0 option[selected]"));

	            // 과목명 텍스트 가져오기
	            String selectedText = selectedOption.getText(); //과목명

	            // 결과 출력
	            System.out.println(selectedText);
	            
	         // 테이블 찾기
	            WebElement table = driver.findElement(By.className("tbl_type"));

	            // 등록된 과제가 없는지 확인
	            WebElement noAssignmentElement = table.findElement(By.tagName("td"));
	            String noAssignmentText = noAssignmentElement.getText();

	            // 등록된 과제가 없는 경우 다음 페이지로 이동
	            if (noAssignmentText.equals("등록된 과제가 없습니다.")) {
	                System.out.println("등록된 과제가 없습니다.");
	                
	                
	             // 강의실 페이지로 다시 이동(2번 필수)
		        	driver.navigate().back();	// 뒤로가기 메소드
		        	driver.navigate().back();
	            }
	            else {
	            	
	            System.out.println("테스트");
	            // 과제가 있는 경우 행 순회
	            List<WebElement> rows = table.findElements(By.tagName("tr"));
	            for (int k = 1; k < rows.size(); k++) {
	                WebElement row = rows.get(k);

	                // 과제제목 가져오기
	                WebElement titleElement = row.findElement(By.cssSelector("td:nth-child(3) > a"));
	                String title2 = titleElement.getText();

	                // 제출기간 가져오기
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
	
     
	                
	                //이렇게 해도 되나
	                
	              
	                String subTitle = "New_Sub_Title";
	                String taskTitle = "New_Task_Title";
	     

	                try {
	                    connection = DriverManager.getConnection(url, username, password);
	                    System.out.println("DB 연결 성공");

	                    String sql = "MERGE INTO SUBJECT s " +
	                            "USING (SELECT ? AS STUDENT_ID, ? AS SUB_TITLE, ? AS TASK_TITLE, ? AS SUB_DATE FROM DUAL) new_data " +
	                            "ON (s.STUDENT_ID = new_data.STUDENT_ID AND s.SUB_TITLE = new_data.SUB_TITLE AND s.TASK_TITLE = new_data.TASK_TITLE AND s.SUB_DATE = new_data.SUB_DATE) " +
	                            "WHEN NOT MATCHED THEN " +
	                            "    INSERT (STUDENT_ID, SUB_TITLE, TASK_TITLE, SUB_DATE) VALUES (new_data.STUDENT_ID, new_data.SUB_TITLE, new_data.TASK_TITLE, new_data.SUB_DATE)";


	                    try (PreparedStatement statement2 = connection.prepareStatement(sql)) {
	                    	statement2.setInt(1, Integer.parseInt(student_id));
	                        statement2.setString(2, selectedText);
	                        statement2.setString(3, title2);
	                        statement2.setInt(4, Integer.parseInt(result));

	                        int rowsAffected = statement2.executeUpdate();
	         
	                        System.out.println("Rows affected: " + rowsAffected);
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                } finally {
	                    if (connection != null) {
	                        try {
	                            connection.close();
	                            System.out.println("DB 연결 종료");
	                        } catch (SQLException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	                

	                // 크롤링한 데이터를 DB에 저장하는 코드 작성
	                // 예시로 과제제목과 제출기간을 "assignments" 테이블에 삽입하는 코드 작성
	                // 이전 예시 코드와 동일

	                // DB 연결 설정 및 데이터 삽입 코드 생략
	                
	                System.out.println(title2); //과제명
	               
	            }
	            
	             
	        	
	        	// 강의실 페이지로 다시 이동(2번 필수)
	        	driver.navigate().back();	// 뒤로가기 메소드
	        	driver.navigate().back();
	            }
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
	        driver.quit();
	    }
	    return true;
	}

}
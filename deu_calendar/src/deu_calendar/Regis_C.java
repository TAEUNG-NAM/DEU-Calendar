package deu_calendar;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Regis_C {
	
	private String planName; // 일정 제목
	private String memoTextArea; // 일정 메모
	private String Repetiton; // 반복 값
	
	
	// 일정 제목 가져오는 메소드
	public String birngTitle() { 
		planName = Regis_B.textField.getText();
		
		return planName;
	}
	
	// 일정 메모 가져오는 메소드
	public String bringMemo() {
		memoTextArea = Regis_B.textArea.getText();
		
		return memoTextArea;	
	}
	
	// 반복 값 가져오는 메소드
	public String bringRepeat() {
		Repetiton = Regis_B.Repetiton.getText();
		
		return Repetiton;
	}
	
	
	// 일정 등록 기능 메소드
	public void regist(String year, String month, String select_day) {
		Connection con = null;
		String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
	    String id = "c##java";
	    String password = "java123";
	    
	    try {
	    	
            con = DriverManager.getConnection(url, id, password);
            System.out.println("DB 연결 성공");

            // 트랜잭션 시작
            con.setAutoCommit(false);         

            try {
       
        		
                // "plan" 테이블에 데이터 삽입
            	if(bringRepeat()=="반복 없음") {
                String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //임의로 db 집어넣은 값 학번
                insertPlanStatement.setString(2, year+month+select_day); //날짜 임의 값
                insertPlanStatement.setString(3, birngTitle()); //일정 명
                insertPlanStatement.setString(4, bringMemo()); //메모
                insertPlanStatement.setString(5, bringRepeat()); //반복값
                insertPlanStatement.executeUpdate();
            	}
            	
            	else if(bringRepeat()=="매월 같은 일") {
            		for(int i=Integer.parseInt(year); i<Integer.parseInt(year)+20; i++) { //년 증가
            			for (int j=Integer.parseInt(month); j<13;j++) { //월 증가
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //임의로 db 집어넣은 값 학번
                         insertPlanStatement.setString(2, String.valueOf(i)+String.valueOf(j)+select_day); //날짜 임의 값
                         insertPlanStatement.setString(3, birngTitle()); //일정 명
                         insertPlanStatement.setString(4, bringMemo()); //메모
                         insertPlanStatement.setString(5, bringRepeat()); //반복값
                         insertPlanStatement.executeUpdate();
            			}
            		}
            	}
            	
            	else if(bringRepeat()=="매일") {	
	        		for (int j=Integer.parseInt(select_day);Integer.parseInt(year+month)+j<=Integer.parseInt(year)*12*365;j=j+1) {
	        			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
	                     PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
	                     insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //임의로 db 집어넣은 값 학번
	                     insertPlanStatement.setString(2, String.valueOf(Integer.parseInt(year+month)+j)); //날짜 임의 값
	                     insertPlanStatement.setString(3, birngTitle()); //일정 명
	                     insertPlanStatement.setString(4, bringMemo()); //메모
	                     insertPlanStatement.setString(5, bringRepeat()); //반복값
	                     insertPlanStatement.executeUpdate();
	        		}}
            	
            	
            	else if(bringRepeat()=="매년 같은 월일") {
            		for(int i=Integer.parseInt(year); i<Integer.parseInt(year)+20; i++) { //년 증가
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //임의로 db 집어넣은 값 학번
                         insertPlanStatement.setString(2, String.valueOf(i)+String.valueOf(month)+select_day); //날짜 임의 값
                         insertPlanStatement.setString(3, birngTitle()); //일정 명
                         insertPlanStatement.setString(4, bringMemo()); //메모
                         insertPlanStatement.setString(5, bringRepeat()); //반복값
                         insertPlanStatement.executeUpdate();
            		}
            	}
            	
            	else if(bringRepeat()=="매주 같은 요일") {
            		for (int j=Integer.parseInt(select_day);Integer.parseInt(year+month)+j<=Integer.parseInt(year)*12*365;j=j+7) {
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //임의로 db 집어넣은 값 학번
                         insertPlanStatement.setString(2, String.valueOf(Integer.parseInt(year+month)+j)); //날짜 임의 값
                         insertPlanStatement.setString(3, birngTitle()); //일정 명
                         insertPlanStatement.setString(4, bringMemo()); //메모
                         insertPlanStatement.setString(5, bringRepeat()); //반복값
                         insertPlanStatement.executeUpdate();
            		}
            	}
                // 트랜잭션 커밋
                con.commit();

                System.out.println("일정 데이터 삽입이 성공적으로 완료되었습니다.");
            } catch (SQLException e) {
                // 트랜잭션 롤백
                con.rollback();

                System.out.println("일정 데이터 삽입 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection Fail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                System.out.println("DB 연결 종료");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	// 경고 메시지 메소드
	public void showErrorMsg() {
		JOptionPane.showMessageDialog(null, "일정 이름을 입력하지 않았습니다.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	}

}

package deu_calendar;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registration {
	
	private String planName; // 일정 제목
	private String memoTextArea; // 일정 메모
	private int Repetiton; // 반복 값
	private String dayValue; // 날짜 값
	
	// 일정 제목 가져오는 메소드
	public String birngTitle(String planName) { 
		planName = entry.textField.getText();
		
		return planName;
	}
	
	// 일정 메모 가져오는 메소드
	public String bringMemo(String memoText) {
		memoTextArea = entry.textArea.getText();
		
		return memoTextArea;	
	}
	
	// 반복 값 가져오는 메소드
	public int bringRepeat() {
		
		return Repetiton;
	}
	
	// 날짜 값 받아오는 메소드
	public String bringDayValue() {
		
		return dayValue;
	}
	
	// 일정 등록 기능 메소드
	public void regist() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
	    String id = "c##java";
	    String password = "java123";
	    
	    try {
	    	
            con = DriverManager.getConnection(url, id, password);
<<<<<<< HEAD
            System.out.println("DB 연결 성공");

            // 트랜잭션 시작
            con.setAutoCommit(false);         

            try {
                // "SUBJECT" 테이블에 데이터 삽입
                String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                insertPlanStatement.setInt(1, 20215030); //임의로 db 집어넣은 값 학번
                insertPlanStatement.setInt(2, 20230201); //날짜 임의 값
                insertPlanStatement.setString(3, birngTitle("")); //일정 명
                insertPlanStatement.setString(4, bringMemo("")); //메모
                insertPlanStatement.setInt(5, 22); //반복값
                insertPlanStatement.executeUpdate();

                // 트랜잭션 커밋
                con.commit();

                System.out.println("일정 데이터 삽입이 성공적으로 완료되었습니다.");
            } catch (SQLException e) {
                // 트랜잭션 롤백
                con.rollback();

                System.out.println("일정 데이터 삽입 중 오류가 발생했습니다.");
=======
            System.out.println("DB ���� ����");

            // Ʈ����� ����
            con.setAutoCommit(false);         

            try {
                // "SUBJECT" ���̺� ������ ����
                String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                insertPlanStatement.setInt(1, 20215030); //���Ƿ� db ������� �� �й�
                insertPlanStatement.setInt(2, 20230201); //��¥ ���� ��
                insertPlanStatement.setString(3, birngTitle("")); //���� ��
                insertPlanStatement.setString(4, bringMemo("")); //�޸�
                insertPlanStatement.setInt(5, 22); //�ݺ���
                insertPlanStatement.executeUpdate();

                // Ʈ����� Ŀ��
                con.commit();

                System.out.println("���� ������ ������ ���������� �Ϸ�Ǿ����ϴ�.");
            } catch (SQLException e) {
                // Ʈ����� �ѹ�
                con.rollback();

                System.out.println("���� ������ ���� �� ������ �߻��߽��ϴ�.");
>>>>>>> upstream/main
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection Fail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
<<<<<<< HEAD
                System.out.println("DB 연결 종료");
=======
                System.out.println("DB ���� ����");
>>>>>>> upstream/main
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
		
=======
>>>>>>> upstream/main
	}
	
	// 경고 메시지 메소드
	public void showErrorMsg() {
		
	}

}

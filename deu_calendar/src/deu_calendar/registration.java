package deu_calendar;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registration {
	
	private String planName; // ���� ����
	private String memoTextArea; // ���� �޸�
	private String Repetiton; // �ݺ� ��
	private String dayValue; // ��¥ ��
	
	// ���� ���� �������� �޼ҵ�
	public String birngTitle() { 
		planName = entry.textField.getText();
		
		return planName;
	}
	
	// ���� �޸� �������� �޼ҵ�
	public String bringMemo() {
		memoTextArea = entry.textArea.getText();
		
		return memoTextArea;	
	}
	
	// �ݺ� �� �������� �޼ҵ�
	public String bringRepeat() {
		Repetiton = entry.Repetiton.getText();
		
		return Repetiton;
	}
	
	// ��¥ �� �޾ƿ��� �޼ҵ�
	public String bringDayValue() {
		
		return dayValue;
	}
	
	// ���� ��� ��� �޼ҵ�
	public void regist() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
	    String id = "c##java";
	    String password = "java123";
	    
	    try {
	    	
            con = DriverManager.getConnection(url, id, password);
            System.out.println("DB ���� ����");

            // Ʈ����� ����
            con.setAutoCommit(false);         

            try {
                // "SUBJECT" ���̺� ������ ����
                String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                insertPlanStatement.setInt(1, 20215030); //���Ƿ� db ������� �� �й�
                insertPlanStatement.setInt(2, 20230201); //��¥ ���� ��
                insertPlanStatement.setString(3, birngTitle()); //���� ��
                insertPlanStatement.setString(4, bringMemo()); //�޸�
                insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
                insertPlanStatement.executeUpdate();

                // Ʈ����� Ŀ��
                con.commit();

                System.out.println("���� ������ ������ ���������� �Ϸ�Ǿ����ϴ�.");
            } catch (SQLException e) {
                // Ʈ����� �ѹ�
                con.rollback();

                System.out.println("���� ������ ���� �� ������ �߻��߽��ϴ�.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection Fail: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                System.out.println("DB ���� ����");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	// ��� �޽��� �޼ҵ�
	public void showErrorMsg() {
		
	}

}

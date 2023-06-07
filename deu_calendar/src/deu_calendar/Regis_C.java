package deu_calendar;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Regis_C {
	
	private String planName; // ���� ����
	private String memoTextArea; // ���� �޸�
	private String Repetiton; // �ݺ� ��
	
	
	// ���� ���� �������� �޼ҵ�
	public String birngTitle() { 
		planName = Regis_B.textField.getText();
		
		return planName;
	}
	
	// ���� �޸� �������� �޼ҵ�
	public String bringMemo() {
		memoTextArea = Regis_B.textArea.getText();
		
		return memoTextArea;	
	}
	
	// �ݺ� �� �������� �޼ҵ�
	public String bringRepeat() {
		Repetiton = Regis_B.Repetiton.getText();
		
		return Repetiton;
	}
	
	
	// ���� ��� ��� �޼ҵ�
	public void regist(String year, String month, String select_day) {
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
       
        		
                // "plan" ���̺� ������ ����
            	if(bringRepeat()=="�ݺ� ����") {
                String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //���Ƿ� db ������� �� �й�
                insertPlanStatement.setString(2, year+month+select_day); //��¥ ���� ��
                insertPlanStatement.setString(3, birngTitle()); //���� ��
                insertPlanStatement.setString(4, bringMemo()); //�޸�
                insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
                insertPlanStatement.executeUpdate();
            	}
            	
            	else if(bringRepeat()=="�ſ� ���� ��") {
            		for(int i=Integer.parseInt(year); i<Integer.parseInt(year)+20; i++) { //�� ����
            			for (int j=Integer.parseInt(month); j<13;j++) { //�� ����
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //���Ƿ� db ������� �� �й�
                         insertPlanStatement.setString(2, String.valueOf(i)+String.valueOf(j)+select_day); //��¥ ���� ��
                         insertPlanStatement.setString(3, birngTitle()); //���� ��
                         insertPlanStatement.setString(4, bringMemo()); //�޸�
                         insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
                         insertPlanStatement.executeUpdate();
            			}
            		}
            	}
            	
            	else if(bringRepeat()=="����") {	
	        		for (int j=Integer.parseInt(select_day);Integer.parseInt(year+month)+j<=Integer.parseInt(year)*12*365;j=j+1) {
	        			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
	                     PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
	                     insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //���Ƿ� db ������� �� �й�
	                     insertPlanStatement.setString(2, String.valueOf(Integer.parseInt(year+month)+j)); //��¥ ���� ��
	                     insertPlanStatement.setString(3, birngTitle()); //���� ��
	                     insertPlanStatement.setString(4, bringMemo()); //�޸�
	                     insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
	                     insertPlanStatement.executeUpdate();
	        		}}
            	
            	
            	else if(bringRepeat()=="�ų� ���� ����") {
            		for(int i=Integer.parseInt(year); i<Integer.parseInt(year)+20; i++) { //�� ����
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //���Ƿ� db ������� �� �й�
                         insertPlanStatement.setString(2, String.valueOf(i)+String.valueOf(month)+select_day); //��¥ ���� ��
                         insertPlanStatement.setString(3, birngTitle()); //���� ��
                         insertPlanStatement.setString(4, bringMemo()); //�޸�
                         insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
                         insertPlanStatement.executeUpdate();
            		}
            	}
            	
            	else if(bringRepeat()=="���� ���� ����") {
            		for (int j=Integer.parseInt(select_day);Integer.parseInt(year+month)+j<=Integer.parseInt(year)*12*365;j=j+7) {
            			 String insertPlantSql = "INSERT INTO PLAN (STUDENT_ID, REGIST_DATE, PLAN_TITLE, MEMO, REPEATVALUE) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement insertPlanStatement = con.prepareStatement(insertPlantSql);
                         insertPlanStatement.setInt(1, Integer.parseInt(Crawling_C.student_id)); //���Ƿ� db ������� �� �й�
                         insertPlanStatement.setString(2, String.valueOf(Integer.parseInt(year+month)+j)); //��¥ ���� ��
                         insertPlanStatement.setString(3, birngTitle()); //���� ��
                         insertPlanStatement.setString(4, bringMemo()); //�޸�
                         insertPlanStatement.setString(5, bringRepeat()); //�ݺ���
                         insertPlanStatement.executeUpdate();
            		}
            	}
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
		JOptionPane.showMessageDialog(null, "���� �̸��� �Է����� �ʾҽ��ϴ�.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	}

}

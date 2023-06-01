package deu_calendar;

import java.util.ArrayList;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class mainScreen_Control {
    public ArrayList<String> ConnectDB(int year, int month, int day) { //db ����
    	Connection con = null;
        String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
        String id = "c##java";
        String password = "java123";
        
        ArrayList<String> text = new ArrayList();
        
        try { //����̹� ����
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            System.out.println("����̹� ���� ����");
        } catch (ClassNotFoundException e) {
            System.out.println("No Driver.");
        }
        
        try {
            con = DriverManager.getConnection(url, id, password);
            System.out.println("DB ���� ����");
         	String query = "select PLAN_TITLE, MEMO from PLAN Where REGIST_DATE="+year+month+day; //���߿� where������ ������ �й��� �ش��ϴ� �͸� ����ϵ��� �ٲ����.
         	System.out.println(query);
         	try { 
         		Statement stmt = con.createStatement(); 
         		ResultSet rs = stmt.executeQuery(query);
         		while (rs.next()) {
         			text.add("- " + rs.getString("PLAN_TITLE")); // �������� ���� ���� �� �����´�. ������� �޸�
         		}
         		stmt.close(); 
         		rs.close();
         		} catch (SQLException e) { e.printStackTrace(); 
         		} finally { 
         			con.close(); 
         			}
         	
   
         	con = DriverManager.getConnection(url, id, password);
            System.out.println("DB ���� ����");
          	String query1 = "select SUB_TITLE from SUBJECT Where SUB_DATE="+year+month+day; //���߿� where������ ������ �й��� �ش��ϴ� �͸� ����ϵ��� �ٲ����.
          	System.out.println(query1);
          	try { 
         		Statement stmt = con.createStatement(); 
         		ResultSet rs = stmt.executeQuery(query1);
         		while (rs.next()) {
         			text.add("�� "+rs.getString("SUB_TITLE")); // �������� ���� ���� �� �����´�. ������� �޸�
         		}
         		stmt.close(); 
         		rs.close();
         		} catch (SQLException e) { e.printStackTrace(); 
         		} finally { 
         			con.close(); 
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
       
        return text;
     }
    
    	
}


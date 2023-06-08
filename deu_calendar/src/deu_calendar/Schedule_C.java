package deu_calendar;

import java.util.ArrayList;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class Schedule_C {
	public ArrayList<ArrayList<ArrayList<String>>> ConnectDB(String studentID) {
	    Connection con = null;
	    String url = "jdbc:oracle:thin:@dict.asuscomm.com:3100:system";
	    String id = "c##java";
	    String password = "java123";

	    ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<>();

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        System.out.println("드라이버 적재 성공");
	    } catch (ClassNotFoundException e) {
	        System.out.println("No Driver.");
	    }

	    try {
	        con = DriverManager.getConnection(url, id, password);
	        System.out.println("DB 연결 성공");

	        String planQuery = "SELECT * FROM PLAN WHERE STUDENT_ID = ?";
	        System.out.println(planQuery);
	        try (PreparedStatement stmt = con.prepareStatement(planQuery)) {
	        	stmt.setInt(1, Integer.parseInt(studentID));
	            ResultSet rs = stmt.executeQuery();

	            ArrayList<ArrayList<String>> planList = new ArrayList<>();
	            while (rs.next()) {
	                ArrayList<String> planInfo = new ArrayList<>();
	                planInfo.add(rs.getString("PLAN_TITLE"));
	                planInfo.add(rs.getString("MEMO"));
	                planInfo.add(String.valueOf(rs.getInt("REGIST_DATE")));
	                planInfo.add(rs.getString("REPEATVALUE"));

	                planList.add(planInfo);
	            }
	            result.add(planList);

	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        String subjectQuery = "SELECT * FROM SUBJECT WHERE STUDENT_ID = ?";
	        
	        try (PreparedStatement stmt = con.prepareStatement(subjectQuery)) {
	        	stmt.setInt(1, Integer.parseInt(studentID));
	            ResultSet rs = stmt.executeQuery();

	            ArrayList<ArrayList<String>> subjectList = new ArrayList<>();
	            while (rs.next()) {
	                ArrayList<String> subjectInfo = new ArrayList<>();
	                subjectInfo.add(rs.getString("SUB_TITLE"));
	                subjectInfo.add(rs.getString("TASK_TITLE"));
	                subjectInfo.add(String.valueOf(rs.getInt("SUB_DATE")));
	                
	                subjectList.add(subjectInfo);
	            }
	            result.add(subjectList);

	            rs.close();
	        } catch (SQLException e) {
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
	    
	    System.out.println(result);

	    return result;
	}
    
    	
}



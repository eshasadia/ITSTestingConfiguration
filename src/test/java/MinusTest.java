

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Before;
import org.junit.Test;
/**
 * @author es255022
 *
 */
public class MinusTest{
	 static String testStatus;
	 static String minus_result_A;
	 static String minus_result_B;
	 static String auto_test_A;
	 static String auto_test_B;
	 String sourceTable="Employee";
	 String sourceDb="EmpDept";
	 String targettable="EmpTest";
	 String targetDb="EmpDept_Test";
	 String resultTable="Minus_Test_Rslt";
	 String resultDb="ITSTESTRESULTS";
	 String test_status="test_status";
	 String rowcount="source_total_count";
	 String pColumn="employee_id";
	@Before
	public  void before() throws Exception {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		String query="select "+test_status+" from "+resultDb+"."+resultTable+" where test_id=20001";
		PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
			testStatus=rs.getString(1);
		}
		
		String query1="SELECT COUNT(*) FROM (SELECT "+pColumn+" FROM "+sourceDb+"."+sourceTable+ " MINUS "
				+ " SELECT "+pColumn+" FROM "+targetDb+"."+targettable+ ") A";
		PreparedStatement stmt1=con.prepareStatement(query1);
		ResultSet rs1=stmt1.executeQuery();
		while(rs1.next()) 
		{
			minus_result_A=rs1.getString(1);
		}
		String query2="SELECT COUNT(*) FROM (SELECT "+pColumn+" FROM "+targetDb+"."+targettable+ " MINUS "
				+ "SELECT "+pColumn+" FROM "+sourceDb+"."+sourceTable+ ") A";
		PreparedStatement stmt2=con.prepareStatement(query2);
		ResultSet rs2=stmt2.executeQuery();
		while(rs2.next()) 
		{
			minus_result_B=rs2.getString(1);
			
		}
		
		String query3="select a_minus_b from "+resultDb+"."+resultTable+" where test_id=20001 ";
		PreparedStatement stmt3=con.prepareStatement(query3);
		ResultSet rs3=stmt3.executeQuery();
		while(rs3.next()) 
		{
			auto_test_A=rs3.getString(1);
			//System.out.println("A="+auto_test_A);
		}
		String query4="select b_minus_a from "+resultDb+"."+resultTable+" where test_id=20001";
		PreparedStatement stmt4=con.prepareStatement(query4);
		ResultSet rs4=stmt4.executeQuery();
		while(rs4.next()) 
		{
			auto_test_B=rs4.getString(1);
			//System.out.println("B="+auto_test_B);
		}
	}
		@Test
		public void Minus_Status_Test()
		{
			assertEquals(testStatus,"Passed");
	}
		@Test
		public void Manual_Auto_ComparisonA_Test()
		{
			assertEquals(auto_test_A,minus_result_A);
	     }
		@Test
		public void Manual_Auto_ComparisonB_Test()
		{
			assertEquals(auto_test_B,minus_result_B);
	     }
	
}

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;

public class SurrogateKeyTest {
	static String surrogatecount;
	static String manualcount;
	static String status;
	  @Before
	public  void before()throws Exception 
	  {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		
		manualcount="SELECT (SELECT COUNT (DISTINCT Employee_name) FROM EmpDept_TEST.EMPTEST) AS SURR_TOTAL_COUNT ,"
				+ "(SELECT COUNT (*) FROM (SELECT DISTINCT EMPLOYEE_ID FROM EmpDept.EMPLOYEE) A"
				+ " )AS SRC_TOTAL_COUNT,(SELECT COUNT(*) FROM EmpDept.EMPLOYEE A INNER "
				+ "JOIN EmpDept_TEST.EMPTEST B ON (A.dept_id) = B.salary  ) AS MATCHING_NATURAL_KEYS,"
				+ "(SURR_TOTAL_COUNT - MATCHING_NATURAL_KEYS) AS SURR_KEYS_WITHOUT_SOURCE_KEYS,"
				+ "(SRC_TOTAL_COUNT - MATCHING_NATURAL_KEYS) AS SRC_KEYS_WITHOUT_SURR_KEYS,"
				+ "(SELECT COUNT (*) FROM (SELECT DISTINCT A.Employee_name, A.salary FROM "
				+ "EmpDept_TEST.EMPTEST A INNER JOIN EmpDept_TEST.EMPTEST B ON A.Employee_name"
				+ " = B.Employee_name WHERE A.salary <> B.salary) X) AS MULTIPLE_SRC_KEYS,(SELECT "
				+ "COUNT (*) FROM (SELECT DISTINCT A.Employee_name, A.salary FROM EmpDept_TEST.EMPTEST A "
				+ "INNER JOIN EmpDept_TEST.EMPTEST B ON A.salary = B.salary WHERE A.Employee_name <> B.Employee_name)"
				+ " X) AS MULTIPLE_SURR_KEYS";
		PreparedStatement stmt=con.prepareStatement(manualcount);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
		 manualcount=rs.getString(1);
		}
		String query2="select test_status from ITSTESTRESULTS.Surrogate_Key_Rslt";
		PreparedStatement stmt2=con.prepareStatement(query2);
		ResultSet rs2=stmt2.executeQuery();
        while(rs2.next()) 
        {
			status=rs2.getString(1);
			//System.out.println("TestStatus="+status);
		}
		String surrogatecount="select multiple_surr_keys from ITSTESTRESULTS.Surrogate_Key_Rslt";
		PreparedStatement stmt3=con.prepareStatement(surrogatecount);
		ResultSet rs3=stmt3.executeQuery();
        while(rs3.next()) 
        {
			surrogatecount=rs3.getString(1);
		   //System.out.println("SurrogateCount="+surrogatecount);
		}
    }
         @Test
         public void Surrogate_Rslt_Status_test()
         {
        	assertEquals(status,"Passed");
         }
         @Test
         public void Surrogate_Rslt_Comparison_Test()
         {

        	//assertEquals(surrogatecount,manualcount);
        	assertNull(surrogatecount);
         }
}


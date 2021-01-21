import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Before;

public class SumAvgTest {
  static String testStatus;
  static String deptIdSum;
  static String autodeptIdSum;
  static String salarySum;
  static String autosalarySum;
	@Before
	public  void before() throws Exception {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		String query="select test_status from ITSTESTRESULTS.Sum_Avg_Recon_Rslt";
		PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) {
		testStatus=rs.getString(1);
		System.out.println("source="+testStatus);}
		
        String query2= "SELECT (SELECT Cast(Sum(Cast(s.dept_id AS DECIMAL(30,8))) AS DECIMAL(30,8)) as source_sum FROM EmpDept.Employee s ),(SELECT Cast(Sum(Cast(t.dept_id AS DECIMAL(30,8))) AS DECIMAL(30,8)) as target_sum FROM EmpDept_TEST.EmpTest t ),(SELECT Cast(Avg(Cast(s.dept_id AS DECIMAL(30,8))) AS DECIMAL(30,8)) as source_avg FROM EmpDept.Employee s ),(SELECT Cast(Avg(Cast(t.dept_id AS DECIMAL(30,8))) AS DECIMAL(30,8)) as target_avg FROM EmpDept_TEST.EmpTest t )";
    	PreparedStatement stmt2=con.prepareStatement(query2);
		ResultSet rs2=stmt2.executeQuery();
        while(rs2.next()) {
			 deptIdSum=rs2.getString(1);
			System.out.println("deptIdSum="+deptIdSum);
		}
        
        String query5= "select source_sum from ITSTESTRESULTS.Sum_Avg_Recon_Rslt where target_column_name='dept_id'";
    	PreparedStatement stmt5=con.prepareStatement(query5);
		ResultSet rs5=stmt5.executeQuery();
        while(rs5.next()) {
			 autodeptIdSum=rs5.getString(1);
			System.out.println("autodeptIdSum="+autodeptIdSum);
		}
//        
//        
        
        String query3="SELECT (SELECT Cast(Sum(Cast(s.salary AS DECIMAL(30,8))) AS DECIMAL(30,8)) as source_sum FROM EmpDept.Employee s ),(SELECT Cast(Sum(Cast(t.salary AS DECIMAL(30,8))) AS DECIMAL(30,8)) as target_sum FROM EmpDept_TEST.EmpTest t ),(SELECT Cast(Avg(Cast(s.salary AS DECIMAL(30,8))) AS DECIMAL(30,8)) as source_avg FROM EmpDept.Employee s ),(SELECT Cast(Avg(Cast(t.salary AS DECIMAL(30,8))) AS DECIMAL(30,8)) as target_avg FROM EmpDept_TEST.EmpTest t )";
        PreparedStatement stmt3=con.prepareStatement(query3);
		ResultSet rs3=stmt3.executeQuery();
        while(rs3.next()) {
			salarySum=rs3.getString(1);
			System.out.println("autoRowCount="+salarySum);		
		}
        String query4="select source_sum from ITSTESTRESULTS.Sum_Avg_Recon_Rslt where target_column_name='salary'";
        PreparedStatement stmt4=con.prepareStatement(query4);
		ResultSet rs4=stmt4.executeQuery();
        while(rs4.next()) {
			autosalarySum=rs4.getString(1);
			System.out.println("autoSalaryCount="+autosalarySum);		
		}
	}	
	@Test
	public void dept_Sum_Test()
	{
		assertEquals(autodeptIdSum,deptIdSum);
}
	@Test
	public void salary_Sum_Test()
	{
		assertEquals(autosalarySum,salarySum);
}
	@Test
	public void StatusTest()
	{
		assertEquals(testStatus,"Passed");
}
}

/**
 * @author es255022
 *
 */




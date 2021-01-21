
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Before;
import org.junit.Test;

public class NullCountTest {
	 static String testStatus;
	 static String null_count;
	 static String auto_count;
	 String sourceTable="Employee";
	 String sourceDb="EmpDept";
	 String targettable="EmpTest";
	 String targetDb="EmpDept_Test";
	 String resultTable="Null_Value_Count_Rslt";
	 String resultDb="ITSTESTRESULTS";
	 String test_status="test_status";
	 String rowcount="source_total_count";
	 String pColumn="employee_id";
	 String s_rslt="source_null_count";
	 String t_rslt="target_null_count";
	@Before
	public  void before() throws Exception {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		String query="select test_status from "+resultDb+"."+resultTable;
		PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
			testStatus=rs.getString(1);
		}
		String query1="SELECT ( SELECT COUNT(*) x FROM "+sourceDb+"."+sourceTable+" WHERE "+pColumn+" IS NULL ) as source_null_count, (SELECT COUNT(*) x FROM "+targetDb+"."+targettable+" WHERE "+pColumn+" IS NULL) as target_null_count";
		PreparedStatement stmt1=con.prepareStatement(query1);
		ResultSet rs1=stmt1.executeQuery();
		while(rs1.next()) 
		{
			null_count=rs1.getString(1);
		}
		String query3="select source_null_count from "+resultDb+"."+resultTable+" where "+s_rslt+"="+t_rslt;
		PreparedStatement stmt3=con.prepareStatement(query3);
		ResultSet rs3=stmt3.executeQuery();
		while(rs3.next()) 
		{
			auto_count=rs3.getString(1);
		}
	}
		@Test
		public void Null_Count_Test()
		{
			assertEquals(null_count,auto_count);
	}
		@Test
		public void comparisonTest()
		{
			assertEquals("Passed",testStatus);
	}
	
		

}
		

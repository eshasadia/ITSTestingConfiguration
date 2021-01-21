
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Before;
import org.junit.Test;

public class HistoryTest {
	
	static String overlap_test_status;
	static String gap_test_status;
	static String reverse_test_status;
	static String history_type_count;
	@Before
	public  void before() throws Exception {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		
		String query="select test_status from ITSTESTRESULTS.Hh_Rslt where history_case='Overlap'";
		PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
			overlap_test_status=rs.getString(1);
			//System.out.println("OverlapTestStatus="+overlap_test_status);
		}
		String query1="select test_status from ITSTESTRESULTS.Hh_Rslt where history_case='Reverse'";
		PreparedStatement stmt1=con.prepareStatement(query1);
		ResultSet rs1=stmt1.executeQuery();
		while(rs1.next()) 
		{
			reverse_test_status=rs1.getString(1);
			//System.out.println("ReverseTestStatus="+reverse_test_status);
		}
		String query3="select test_status from ITSTESTRESULTS.Hh_Rslt where history_case='Gap'";
		PreparedStatement stmt3=con.prepareStatement(query3);
		ResultSet rs3=stmt3.executeQuery();
		while(rs3.next()) 
		{
			gap_test_status=rs3.getString(1);
			//System.out.println("GapTestStatus="+gap_test_status);
		}
		String query4="select history_type_count from ITSTESTRESULTS.Hh_Rslt";
		PreparedStatement stmt4=con.prepareStatement(query4);
		ResultSet rs4=stmt4.executeQuery();
		while(rs4.next()) 
		{
			history_type_count=rs4.getString(1);
			//System.out.println("GapTestStatus="+history_type_count);
		}
	}
		@Test
		public void History_TypeCountComparison_Test()
		{
			assertEquals(history_type_count,"0");
	}
		@Test
		public void History_Overlap_Rslt_Test()
		{
			assertEquals(overlap_test_status,"Passed");
	}
		@Test
		public void History_Gap_Rslt_Test()
		{
			assertEquals(gap_test_status,"Passed");
	}
		@Test
		public void History_Reverse_Rslt_Test()
		{
			assertEquals(reverse_test_status,"Passed");
	}
	
	
}
//	HistoryTestAutomator h=new HistoryTestAutomator(false);
//	//h.startTime=12;
////	@BeforeClass
////	public static void setUpBeforeClass() throws Exception {
////		
////		
////		
////		
////	}
////
////	@AfterClass
////	public static void tearDownAfterClass() throws Exception {
////	}
////
////	@Before
////	public void setUp() throws Exception {
////	}
////
////	@After
////	public void tearDown() throws Exception {
////	}
//
//	@Test
//	public void test() throws ClassNotFoundException, SQLException {
//		
//	      //  assertArrayEquals(expectedOutput, methodOutput);
//			Class.forName("com.teradata.jdbc.TeraDriver");
//			Connection con=DriverManager.getConnection(  
//					"jdbc:teradata://192.168.0.139,database=EmpDept,USER=dbc,PASSWORD=dbc,tmode=ANSI,charset=UTF8/");
//			assertNotNull(h.getDatetsFormats(con, "EMPLOYEE","2018-09-01 09:01:15", "2020-09-01 09:01:15"));
//			//h.getHistoryCaseQuery("full", "EMPLOYEE", final HashMap<String, String[]> tblToHistoryCount)
//		}}
////	}
////
////}




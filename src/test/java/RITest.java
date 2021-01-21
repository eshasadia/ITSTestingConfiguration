import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Before;

public class RITest {
	static String manualcount;
	static String violationcount;
	static String status;
	String sourceTable="Employee";
	String sourceDb="EmpDept";
	String targettable="Dept";
	String targetDb="EmpDept_Test";
	String resultTable="Ri_Rslt";
	String resultDb="ITSTESTRESULTS";
	String test_status="test_status";
	String rowcount="source_total_count";
	String  tableId="Dept_id";
	  @Before
	public  void before()throws Exception 
	  {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/"
				+ "database=ITSTESTRESULTS,USER=dbc,"
				+ "PASSWORD=dbc,tmode=ANSI");
		
		String query2="select "+test_status+" from "+resultDb+"."+resultTable;
		PreparedStatement stmt2=con.prepareStatement(query2);
		ResultSet rs2=stmt2.executeQuery();
        while(rs2.next()) 
        {
			status=rs2.getString(1);
			
		}
       
	String query="SELECT count (*) RI_Violation_Count FROM ( Select A."+tableId+" from "+sourceDb+"."+sourceTable
			+ " A LEFT OUTER JOIN "+sourceDb+"."+targettable+" B ON A."+tableId+"= B."+tableId+" WHERE B."+tableId+" IS NULL) X";
	PreparedStatement stmt=con.prepareStatement(query);
	ResultSet rs=stmt.executeQuery();
	while(rs.next()) 
		{
		 manualcount=rs.getString(1);
		}
    String query3="select no_of_violations from "+resultDb+"."+resultTable;
    PreparedStatement stmt3=con.prepareStatement(query3);
    ResultSet rs3=stmt3.executeQuery();
    while(rs3.next()) 
        {
			violationcount=rs3.getString(1);	
		}
    }
         @Test
         public void Ri_Rslt_Comparison_Test()
         {

        	assertEquals(violationcount,manualcount);
         }
         
         @Test
         public void Ri_Rslt_Status_test()
         {
        	assertEquals(status,"Passed");
         }
	}
		


//System.out.println("ViolationCount="+violationcount);
//System.out.println("TestStatus="+status);
//		Connection con1=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=EmpDept_Test,USER=dbc,PASSWORD=dbc,tmode=ANSI");

//		
//		Connection con2=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		
	
//    
       // query = "SELECT count (*) RI_Violation_Count FROM ( Select A." + JobTypeParser.getRiVerification().get(i).getFkColumns().replace(",", ",A.") + " from " 
				//							+ JobTypeParser.getRiVerification().get(i).getChildDbName() + "." + JobTypeParser.getRiVerification().get(i).getChildTableName() 
				//							+ " A LEFT OUTER JOIN " + JobTypeParser.getRiVerification().get(i).getParentDbName() + "." 
				//							+ JobTypeParser.getRiVerification().get(i).getParentTableName() + " B ON COALESCE(A." 
				//							+ JobTypeParser.getRiVerification().get(i).getFkColumns().replace(",", ",A.") + ", \'\') = "
				//									+ "COALESCE(B." + JobTypeParser.getRiVerification().get(i).getPkColumns().replace(",", ",B.") + ", \'\') WHERE B." 
				//							+ JobTypeParser.getRiVerification().get(i).getPkColumns().replace(",", " IS NULL or B.") + " IS NULL GROUP BY A." + 
				//									JobTypeParser.getRiVerification().get(i).getFkColumns().replace(",", ",A.") + ") X;";
	
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;

///**
// * @author es255022
// *
// */


//
//	/**
//	 * @throws java.lang.Exception
//	 */,
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}//sample database test table k 2 tables k ri test 
//	//random rows 
//	//pass or fail
//	
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//    public static void test() throws Exception {
//		assertNull(RITestAutomator.call());
//	}
//
//}

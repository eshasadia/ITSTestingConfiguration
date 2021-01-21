
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.junit.Test;


import org.junit.Before;
import static org.junit.Assert.*;
 

public class DistinctCountTest{
	static String status;
	static String empIdCount;
    static String empSalaryCount;
	static String empNameCount;
	static String empDeptIdCount;
	static String positionCount;
	static String autoempIdCount;
    static String autoempSalaryCount;
	static String autoempNameCount;
	static String autoempDeptIdCount;
	static String autopositionCount;
	  @Before
	public  void before()throws Exception 
	  {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		
		String queryEmpId="SELECT (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT employee_id FROM EmpDept.Employee  GROUP BY employee_id) A) as source_distinct_count, (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT employee_id FROM EmpDept_TEST.EmpTest  GROUP BY employee_id) B) as target_distinct_count;";
		PreparedStatement stmt=con.prepareStatement(queryEmpId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
			empIdCount=rs.getString(1);
			//System.out.println("empIdCount="+empIdCount);
		}
		String querySalaryCount="SELECT (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT salary FROM EmpDept.Employee  GROUP BY salary) A) as source_distinct_count, (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT salary FROM EmpDept_TEST.EmpTest  GROUP BY salary) B) as target_distinct_count;";
		PreparedStatement stmt1=con.prepareStatement(querySalaryCount);
		ResultSet rs1=stmt1.executeQuery();
		while(rs1.next()) 
		{
			empSalaryCount=rs1.getString(1);
			//System.out.println("empSalaryCount="+empSalaryCount);
		}
		String queryEmployeeName="SELECT (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT employee_name FROM EmpDept.Employee  GROUP BY employee_name) A) as source_distinct_count, (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT employee_name FROM EmpDept_TEST.EmpTest  GROUP BY employee_name) B) as target_distinct_count;";
		PreparedStatement stmt2=con.prepareStatement(queryEmployeeName);
		ResultSet rs2=stmt2.executeQuery();
		while(rs2.next()) 
		{
			empNameCount=rs2.getString(1);
			//System.out.println("empNameCount="+empNameCount);
		}
		String queryDeptId="SELECT (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT dept_id FROM EmpDept.Employee  GROUP BY dept_id) A) as source_distinct_count, (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT dept_id FROM EmpDept_TEST.EmpTest  GROUP BY dept_id) B) as target_distinct_count;";
		PreparedStatement stmt3=con.prepareStatement(queryDeptId);
		ResultSet rs3=stmt3.executeQuery();
		while(rs3.next()) 
		{
			empDeptIdCount=rs3.getString(1);
			//System.out.println("empDeptIdCount="+empDeptIdCount);
		}
		String queryPosition="SELECT (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT position_ FROM EmpDept.Employee  GROUP BY position_) A) as source_distinct_count, (SELECT CAST(COUNT(*) AS BIGINT) x FROM (SELECT position_ FROM EmpDept_TEST.EmpTest  GROUP BY position_) B) as target_distinct_count;";
		PreparedStatement stmt4=con.prepareStatement(queryPosition);
		ResultSet rs4=stmt4.executeQuery();
		while(rs4.next()) 
		{
			positionCount=rs4.getString(1);
			//System.out.println("positionCount="+positionCount);
		}
		String query2="select test_status from ITSTESTRESULTS.Distinct_Value_Count_Rslt";
		PreparedStatement stmt5=con.prepareStatement(query2);
		ResultSet rs5=stmt5.executeQuery();
        while(rs5.next()) 
        {
			status=rs5.getString(1);
			//System.out.println("TestStatus="+status);
		}
        
		String query3="select source_distinct_count from ITSTESTRESULTS.Distinct_Value_Count_Rslt where source_column_name='employee_id';";
		PreparedStatement stmt6=con.prepareStatement(query3);
		ResultSet rs6=stmt6.executeQuery();
        while(rs6.next()) 
        {
        	autoempIdCount=rs6.getString(1);
			//System.out.println("autoempIdCount="+autoempIdCount);
		}
        String query4="select source_distinct_count from ITSTESTRESULTS.Distinct_Value_Count_Rslt where source_column_name='employee_name';";
		PreparedStatement stmt7=con.prepareStatement(query4);
		ResultSet rs7=stmt7.executeQuery();
        while(rs7.next()) 
        {
        	autoempNameCount=rs7.getString(1);
			//System.out.println("autoempNameCount="+autoempNameCount);
		}
        String query5="select source_distinct_count from ITSTESTRESULTS.Distinct_Value_Count_Rslt where source_column_name='salary';";
     		PreparedStatement stmt8=con.prepareStatement(query5);
     		ResultSet rs8=stmt8.executeQuery();
             while(rs8.next()) 
             {
             	autoempSalaryCount=rs8.getString(1);
     			//System.out.println("autoempSalaryCount="+autoempSalaryCount);
     		}
         String query6="select source_distinct_count from ITSTESTRESULTS.Distinct_Value_Count_Rslt where source_column_name='position_';";
     		PreparedStatement stmt9=con.prepareStatement(query6);
     		ResultSet rs9=stmt9.executeQuery();
             while(rs9.next()) 
             {
             	autopositionCount=rs9.getString(1);
     		  //System.out.println("autopositionCount="+autopositionCount);
     		}
             
      String query7="select target_distinct_count from ITSTESTRESULTS.Distinct_Value_Count_Rslt"
		+ " where source_column_name='dept_id';";
     		PreparedStatement stmt10=con.prepareStatement(query7);
     		ResultSet rs10=stmt10.executeQuery();
             while(rs10.next()) 
             {
             	autoempDeptIdCount=rs10.getString(1);
     		     //System.out.println("autoempDeptCount="+autoempDeptIdCount);
     		  }
         }
         @Test
         public void Distinct_Rslt_Status_test()
         {
        	assertEquals(status,"Passed");
         }
		@Test
         public void Distinct_EmpId_Comparison_Test()
         {       
        	assertEquals(autoempIdCount.trim(),empIdCount);
         }
         @Test
         public void Distinct_EmpName_Comparison_Test()
         {
            assertEquals(empNameCount,autoempNameCount.trim());
         }
         @Test
         public void Distinct_DeptId_Comparison_Test()
         {
        	assertEquals(empDeptIdCount,autoempDeptIdCount.trim());
         }
         @Test
         public void Distinct_Salary_Comparison_Test()
         {
        	assertEquals(empSalaryCount,autoempSalaryCount.trim());
         }
         @Test
         public void Distinct_Position_Comparison_Test()
         {
           assertEquals(positionCount,autopositionCount.trim());
         }
}











//package com.td.tafd.modules.di;
//
//import static org.junit.Assert.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;



//	static ArrayList<String> colsList=new ArrayList<String>();
//	static HashMap<String, Integer> mapDistinctCount= new HashMap<String, Integer>();
//	static ArrayList<String> rcolsList=new ArrayList<String>();
//	DistinctCountTestAutomator d=new DistinctCountTestAutomator("ITSTESTRESULTS", true, true);
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
// 
//		//d.getDistinctValueCount(String obj_name, ArrayList<String> cols, String whereClause, HashMap<String, Integer> mapDistinctCount, int sourceType)	
//		colsList.add("EMPLOYEE_ID");
//		colsList.add("EMPLOYEE_NAME");
//		colsList.add("DEPT_ID");
//		colsList.add("POSITION_");
//		colsList.add("SALARY");	
//		mapDistinctCount.put("EMPLOYEE_ID",3);
//		mapDistinctCount.put( "EMPLOYEE_NAME",4);
//		mapDistinctCount.put("DEPT_ID",1);
//		mapDistinctCount.put("POSITION_",2);
//		mapDistinctCount.put("SALARY",1);
//		DistinctCountTestAutomator.getDistinctValueCount("EMPLOYEE", colsList, "WHERE", mapDistinctCount, 1);
//		 //Compares distinct values for all tables listed in input parameter file 
//	}
//	
//	
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
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
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.DistinctCountTestAutomator#getResultTableName()}.
//	 */
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.DistinctCountTestAutomator#DistinctCountTestAutomator(java.lang.String, boolean, boolean)}.
//	 */
//	@Test
//	public final void testDistinctCountTestAutomator() 
//	{
//		DistinctCountTestAutomator d=new DistinctCountTestAutomator("ITSTESTRESULTS", true, true);
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.DistinctCountTestAutomator#getDistinctValueCount(java.lang.String, java.util.ArrayList, java.lang.String, java.util.HashMap, int)}.
//	 */
//	@Test
//	public final void testGetDistinctValueCount() {
//		
//		DistinctCountTestAutomator.getDistinctValueCount("EMPLOYEE", colsList, "",mapDistinctCount, 1);
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.DistinctCountTestAutomator#equateColumns(java.util.ArrayList, java.util.ArrayList)}.
//	 */
//	@Test
//	public final void testEquateColumns() {
//		rcolsList= d.equateColumns(colsList, colsList); // TODO
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.DistinctCountTestAutomator#call()}.
//	 */
//	@Test
//	public final void testCall() {
//		d.call() ;// TODO
//	}
//
//}

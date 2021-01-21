

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;


public class MinMaxTest{
	static String status;
	static String empIdMax;
    static String empSalaryMax;
	static String empNameMax;
	static String empDeptIdMax;
	static String positionMax;
	static String autoempIdMax;
    static String autoempSalaryMax;
	static String autoempNameMax;
	static String autoempDeptMax;
	static String autopositionMax;
	  @Before
	public  void before()throws Exception 
	  {
		Class.forName("com.teradata.jdbc.TeraDriver");
		Connection con=DriverManager.getConnection("jdbc:teradata://192.168.43.119/database=ITSTESTRESULTS,USER=dbc,PASSWORD=dbc,tmode=ANSI");
		
		String query1="SELECT (SELECT max(s.employee_id) as source_max FROM EmpDept.Employee s ),(SELECT max(t.employee_id) as target_max FROM EmpDept_TEST.EmpTest t ),(SELECT min(s.employee_id) as source_min FROM EmpDept.Employee s ),(SELECT min(t.employee_id) as target_min FROM EmpDept_TEST.EmpTest t )";
		PreparedStatement stmt=con.prepareStatement(query1);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) 
		{
			empIdMax=rs.getString(1);
			//System.out.println("empIdMax="+empIdMax);
		}
		String querySalaryMax="SELECT (SELECT max(s.salary) as source_max FROM EmpDept.Employee s ),(SELECT max(t.salary) as target_max FROM EmpDept_TEST.EmpTest t ),(SELECT min(s.salary) as source_min FROM EmpDept.Employee s ),(SELECT min(t.salary) as target_min FROM EmpDept_TEST.EmpTest t )";
		PreparedStatement stmt1=con.prepareStatement(querySalaryMax);
		ResultSet rs1=stmt1.executeQuery();
		while(rs1.next()) 
		{
			empSalaryMax=rs1.getString(1);
			//System.out.println("empSalaryCount="+empSalaryMax);
		}
		String queryEmployeeName="SELECT (SELECT max(s.employee_name) as source_max FROM EmpDept.Employee s ),(SELECT max(t.employee_name) as target_max FROM EmpDept_TEST.EmpTest t ),(SELECT min(s.employee_name) as source_min FROM EmpDept.Employee s ),(SELECT min(t.employee_name) as target_min FROM EmpDept_TEST.EmpTest t )";
		PreparedStatement stmt2=con.prepareStatement(queryEmployeeName);
		ResultSet rs2=stmt2.executeQuery();
		while(rs2.next()) 
		{
			empNameMax=rs2.getString(1);
			//System.out.println("empNameMax="+empNameMax);
		}
		String queryDeptMax="SELECT (SELECT max(s.dept_id) as source_max FROM EmpDept.Employee s ),(SELECT max(t.dept_id) as target_max FROM EmpDept_TEST.EmpTest t ),(SELECT min(s.dept_id) as source_min FROM EmpDept.Employee s ),(SELECT min(t.dept_id) as target_min FROM EmpDept_TEST.EmpTest t )";
		PreparedStatement stmt3=con.prepareStatement(queryDeptMax);
		ResultSet rs3=stmt3.executeQuery();
		while(rs3.next()) 
		{
			empDeptIdMax=rs3.getString(1);
			//System.out.println("empDeptIdMax="+empDeptIdMax);
		}
		String queryPosition="SELECT (SELECT max(s.position_) as source_max FROM EmpDept.Employee s ),(SELECT max(t.position_) as target_max FROM EmpDept_TEST.EmpTest t ),(SELECT min(s.position_) as source_min FROM EmpDept.Employee s ),(SELECT min(t.position_) as target_min FROM EmpDept_TEST.EmpTest t )";
		PreparedStatement stmt4=con.prepareStatement(queryPosition);
		ResultSet rs4=stmt4.executeQuery();
		while(rs4.next()) 
		{
			positionMax=rs4.getString(1);
			//System.out.println("positionMax="+positionMax);
		}
		String query2="select test_status from ITSTESTRESULTS.Min_Max_Recon_Rslt";
		PreparedStatement stmt5=con.prepareStatement(query2);
		ResultSet rs5=stmt5.executeQuery();
        while(rs5.next()) 
        {
			status=rs5.getString(1);
			//System.out.println("TestStatus="+status);
		}
      
		String query3="select source_max from ITSTESTRESULTS.Min_Max_Recon_Rslt where source_column_name='employee_id';";
		PreparedStatement stmt6=con.prepareStatement(query3);
		ResultSet rs6=stmt6.executeQuery();
      while(rs6.next()) 
      {
      	autoempIdMax=rs6.getString(1);
			//System.out.println("autoempIdCount="+autoempIdMax);
		}
      String query4="select source_max from ITSTESTRESULTS.Min_Max_Recon_Rslt where source_column_name='employee_name';";
		PreparedStatement stmt7=con.prepareStatement(query4);
		ResultSet rs7=stmt7.executeQuery();
      while(rs7.next()) 
      {
      	autoempNameMax=rs7.getString(1);
			//System.out.println("autoempNameMax="+autoempNameMax);
		}
      String query5="select source_max from ITSTESTRESULTS.Min_Max_Recon_Rslt where source_column_name='salary';";
   		PreparedStatement stmt8=con.prepareStatement(query5);
   		ResultSet rs8=stmt8.executeQuery();
           while(rs8.next()) 
           {
           	autoempSalaryMax=rs8.getString(1);
   			//System.out.println("autoempSalaryMax="+autoempSalaryMax);
   		}
       String query6="select source_max  from ITSTESTRESULTS.Min_Max_Recon_Rslt where source_column_name='position_';";
   		PreparedStatement stmt9=con.prepareStatement(query6);
   		ResultSet rs9=stmt9.executeQuery();
           while(rs9.next()) 
           {
           	autopositionMax=rs9.getString(1);
   		  //System.out.println("autopositionMax="+autopositionMax);
   		}
        String query7="select source_max  from ITSTESTRESULTS.Min_Max_Recon_Rslt where source_column_name='dept_id';";
   		PreparedStatement stmt10=con.prepareStatement(query7);
   		ResultSet rs10=stmt10.executeQuery();
           while(rs10.next()) 
           {
           	autoempDeptMax=rs10.getString(1);
   		    // System.out.println("autoempDeptMax="+autoempDeptMax);
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
      	assertEquals(autoempIdMax.trim(),empIdMax);
       }
       @Test
       public void Distinct_EmpName_Comparison_Test()
       {
          assertEquals(empNameMax,autoempNameMax.trim());
       }
       @Test
       public void Distinct_DeptId_Comparison_Test()
       {
      	assertEquals(empDeptIdMax,autoempDeptMax.trim());
       }
       @Test
       public void Distinct_Salary_Comparison_Test()
       {
      	assertEquals(empSalaryMax,autoempSalaryMax.trim());
       }
       @Test
       public void Distinct_Position_Comparison_Test()
       {
         assertEquals(positionMax,autopositionMax.trim());
       }
}
//	static ArrayList<String> colsList=new ArrayList<String>();
//	
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.MinMaxTestAutomator#MinMaxTestAutomator(java.lang.String, boolean, boolean)}.
//	 */
//	@Test
//	public final void testMinMaxTestAutomator() {
//		MinMaxTestAutomator m=new MinMaxTestAutomator("ITSTESTRESULTS", true, true);
//		colsList.add("EMPLOYEE_ID");
//		colsList.add("EMPLOYEE_NAME");
//		colsList.add("DEPT_ID");
//		colsList.add("POSITION_");
//		colsList.add("SALARY");
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.MinMaxTestAutomator#getMinMax(java.lang.String, int, java.util.ArrayList, java.lang.String, java.util.HashMap, int)}.
//	 */
//	@Test
//	public final void testGetMinMax() {
//		//MinMaxTestAutomator.getMinMax("EMPLOYEE", 1, colsList,"WHERE", HashMap<String, String[]> mapMinMax, 1)
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.MinMaxTestAutomator#equateColumns(java.util.ArrayList, java.util.ArrayList)}.
//	 */
//	@Test
//	public final void testEquateColumns() {
//		MinMaxTestAutomator.equateColumns(colsList, colsList);
//	}
//
//	/**
//	 * Test method for {@link com.td.tafd.modules.di.MinMaxTestAutomator#call()}.
//	 */
//	@Test
//	public final void testCall() throws Exception  {
//		//MinMaxTestAutomator.call(); // TODO
//	}
//
//}







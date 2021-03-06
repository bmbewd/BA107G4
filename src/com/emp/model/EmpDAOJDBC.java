package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comrf.model.ComRfVO;

public class EmpDAOJDBC implements EmpDAOInterface{
static final String INSERT="INSERT INTO EMPLOYEE (EMP_NO,EMP_NM,EMP_PIC,EMP_PSW,EMP_STS) VALUES('EMP'||(LPAD(TO_CHAR(EMP_SEQ.NEXTVAL),3,'0')),?,?,?,?)";
static final String DELETE="DELETE FROM EMPLOYEE WHERE EMP_NO=?";
static final String SELECT="SELECT * FROM EMPLOYEE WHERE EMP_NO=?";
static final String SELECTALL="SELECT * FROM EMPLOYEE";
static final String UPDATE="UPDATE EMPLOYEE SET EMP_NM=?,EMP_PIC=?,EMP_PSW,EMP_STS=? WHERE EMP_NO=?";
	@Override
	public EmpVO insert(EmpVO empvo) {
		Connection c=null;	 
			    try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
					 c.setAutoCommit(false);
					 String[] generateKey=new String[]{"EMP_NO"};
					 PreparedStatement prst=c.prepareStatement(this.INSERT,generateKey);
					 prst.setString(1, empvo.getEmp_nm());
					 prst.setBytes(2, empvo.getEmp_pic());
	                 prst.setString(3, empvo.getEmp_psw());
	                 prst.setString(4, empvo.getEmp_sts());
	                 prst.executeUpdate(); 
	                 ResultSet rs=prst.getGeneratedKeys();
					c.setAutoCommit(true);
					c.commit();
					 
	                 while(rs.next()){
	                	 empvo.setEmp_no(rs.getString(1));
	                 }
			    } catch (ClassNotFoundException | SQLException e) {
					if(c!=null){
						try {
							c.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					e.printStackTrace();
				}finally{
					if(c!=null){
						try {
							c.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			   	return empvo;
		
	}

	@Override
	public void update(EmpVO empvo) {
		Connection c=null;
		 
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			 c.setAutoCommit(false);
			 PreparedStatement prst=c.prepareStatement(this.UPDATE);
			 prst.setString(1, empvo.getEmp_nm());
			 prst.setBytes(2, empvo.getEmp_pic());
			 prst.setString(3, empvo.getEmp_psw());
			 prst.setString(4, empvo.getEmp_sts());
			 prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
	    } catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(String empno) {
		Connection c=null;	 
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			 c.setAutoCommit(false);
			 PreparedStatement prst=c.prepareStatement(this.DELETE);
			 prst.setString(1, empno);
			 prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
	    } catch (ClassNotFoundException | SQLException e) {
	    	if(c!=null){
	    		try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
			
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


		
	}

	@Override
	public boolean selectByPrimaryKey(String empno,String emp_psw) {
		EmpVO empvo=new EmpVO();
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");		 
			 PreparedStatement prst=c.prepareStatement(this.SELECT);
			 
			 prst.setString(1, empno);
			 prst.setString(2, emp_psw);
			 ResultSet rs=prst.executeQuery();
			 if(rs.next()){
				return true;
			 }else{
				 return false;
			 }
	    } catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


		return false;
	}

	@Override
	public List<EmpVO> selectAll() {
		Connection c=null;	 
		List<EmpVO> list=new ArrayList<EmpVO>();
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");			 
			 PreparedStatement prst=c.prepareStatement(this.SELECTALL);
			 ResultSet rs=prst.executeQuery();
			 while(rs.next()){
				 EmpVO empvo=new EmpVO();
				 empvo.setEmp_no(rs.getString(1));
				 empvo.setEmp_nm(rs.getString(2));
				 empvo.setEmp_pic(rs.getBytes(3));
				 empvo.setEmp_psw(rs.getString(4));
				 empvo.setEmp_sts(rs.getString(5));
			     list.add(empvo);
			 }
			 
		
	    } catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

          return list;
	}



	@Override
	public EmpVO selectEmp(String emp_no) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

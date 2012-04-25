package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Created on 2005-9-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author 09003112小组
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserMgr 
{
	private  int row=0;
	//主方法供调试时用
	/*public static  void main(String args[])
	{
		UserMgr UM=new UserMgr();
		
		//int h=UM.getRow();
		//System.out.println(h);
		//UM.updateInfor_Price("star warIII",1000000);
		//UM.updateInfor_M("Tom",200);
		//UM.insertInfor("GOD",378612);
		//UM.updateInfor_EndT(1000,"GOD");
		//UM.deleteInfor("Tim");
		//UM.selectInfor_PS("Tim");
	   //UM.showInfor_Name(10);
		//if(UM.userExist("sun"))
			//System.out.println(" jhafa");
		//UM.showUserOnLine_Name(10);
		//UM.selectInfor_OL("aaaa");
		//UM.updateInfor_PS(1000,"Nicholas");
	}
	*/
	
	public int getRow()
	{
		try
		  {//加载驱动程序，此处为JDBC-ODBC桥
		      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		      String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		      Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
	 	      String sql="select * from User";
	 	      ResultSet rs=stmt.executeQuery(sql);
			   while(rs.next())
			   	row++;
			  }
	        catch(ClassNotFoundException e)
			   {
		   	System.out.println("error:ClassNotFound"+e);
		       }
		    catch(SQLException e)
		       {
		    	System.out.println("error:getRow"+ e);
		      } 
		    int aa=row;
		    row=0;
		   return aa;
	 }
	
	
	
	public boolean userExist(String name) //判断用户名是否存在
	{
		boolean userExist=false;
		  try 
		     {//加载驱动程序，此处为JDBC-ODBC桥
		      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		      String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		      Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
	 	      String sql="select * from User";
	 	      ResultSet rs=stmt.executeQuery(sql);
			   while(rs.next())
			   if(rs.getString("Name").equals(name))
			    {
			  	userExist=true;
			  	
			    break;
			  	}
			 }
	        catch(ClassNotFoundException e)
			   {
		   	System.out.println("error:ClassNotFound"+e);
		       }
		    catch(SQLException e)
		       {
		    	System.out.println("error:Judge userName Exist"+ e);
		      } 
		    return userExist;
	 }
	
	public String[] showInfor_Name(int row) //显示所有数据库记录（用户名）
    { 
	   String[]  userName=new String[row];
 	  try 
	  {//加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	       String sql="SELECT * FROM User";
          ResultSet rs=stmt.executeQuery(sql);
       if(rs!=null) 
        {   int i=0;
        while(rs.next())
          {
          userName[i]=rs.getString(1);
          //System.out.println("姓名："+userName[i]);
          i++; }
          rs.close();
          stmt.close();
          con.close();
        }}
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error:ClassNotFound"+e);
       }
    catch(SQLException e)
       {
    	System.out.println("error:showInfor_Name"+ e);
      } 
   return userName ;
   }
	
	
	public long[] showInfor_Money(int row)    //显示所有数据库记录(金钱)
    { 
	   long  userMoney[]=new long [row];
 	  try 
	  {//加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	       String sql="SELECT * FROM User";
          ResultSet rs=stmt.executeQuery(sql);
       if(rs!=null) 
        {   int i=0;
        while(rs.next())
          {
          userMoney[i]=rs.getLong(3);
          //System.out.println("姓名："+userMoney[i]);
          i++; }
          rs.close();
          stmt.close();
          con.close();
        }
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error:ClassNotFound"+e);
       }
    catch(SQLException e)
       {
    	System.out.println("error:showInfor_Money"+ e);
      } 
     return userMoney ;
   }
	
	
	
	public String[] showUserOnLine_Name(int row) //显示在线用户信息（用户名）
    { 
		 String[]  userName=new String[row];
 	  try 
	  {//加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM User WHERE onLine=1";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
          {   
          	int i=0;
          while(rs.next())
            {
            userName[i]=rs.getString(1);
            //System.out.println("姓名："+userName[i]);
            i++; 
            }
          for(;i<row;i++)
          	userName[i]="administrator";
            rs.close();
            stmt.close();
            con.close();
          }
         }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error:ClassNotFound"+e);
       }
    catch(SQLException e)
       {
    	System.out.println("error:showUserOnline_Name"+ e);
      } 
    return userName;
   }
	
	
	public long[] showUserOnLine_Money(int length)//显示在线用户信息(金钱)
    { 
		 long[]  userMoney=new long[length];
 	  try 
	  {//加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM User WHERE onLine=1";
          ResultSet rs=stmt.executeQuery(sql);
          while(rs.next())
          {
          String name=rs.getString(1);
          long money=rs.getLong(3);
          //System.out.println("姓名："+name+" 金钱：  "+money);
          }
          rs.close();
          stmt.close();
          con.close();
          }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error:ClassNotFound"+e);
       }
    catch(SQLException e)
       {
    	System.out.println("error:showUserOnline_Money"+ e);
      }
    return userMoney;
   }
	
	 public void insertInfor(String name,long password)//添加用户记录
	    {
	  	 int i=-1;
	  	 try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
		      String sql2="INSERT INTO User"+" VALUES('"+name+"',"+password+","+10000+","+0+","+0+","+0+")";
		      i=stmt.executeUpdate(sql2);
		     
		      stmt.close();
		      con.close();
		     
		  }
	  	  catch(ClassNotFoundException e)
		   {
	 	   System.out.println("error:ClassNotFound"+e);
	 	    }
	  	    catch(SQLException e)
	       {
	    	System.out.println("error:insertInfor"+ e);
	    	
	       } 
	  	    
	      //System.out.println("数据添加到第"+i+"行");
	  	}
	  
	 
	 public void deleteInfor(String name)         //删除用户记录
	  {
	  	int i=-1;
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
		      String sql="DELETE FROM User WHERE Name= '"+name+"'";
		      i=stmt.executeUpdate(sql);
		      stmt.close();
		      con.close();
		   }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:deleteInfor"+ e);
	        } 
		    //System.out.println("deleteInfor,done!!");
	    }
	 
	 
	 
	 public void updateInfor_PS(long newPassword, String name)//修改密码
	  {
	  	int i=-1;
	  
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   String sql1="UPDATE User SET Password= "+newPassword+" WHERE Name= '"+name+"'";
			      i=stmt.executeUpdate(sql1);
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:updateInfor_Password"+ e);
	        } 
		    //System.out.println("updateInfor_Password,done!!" );
	     }
	 
	 public void updateInfor_Quit( String name)  //下线
	  {
	  	int i=-1;  
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   String sql2="UPDATE User SET onLine= 0 WHERE Name= '"+name+"'";
			   i=stmt.executeUpdate(sql2);
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:updateInfor_Quit"+ e);
	        } 
		    //System.out.println("updateInfor_Quit,done!!" );
	     }
	 
	 
	 public void updateInfor_Load( String name)  //上线
	  {
	  	int i=-1;
	  
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   String sql2="UPDATE User SET onLine= 1 WHERE Name= '"+name+"'";
			   i=stmt.executeUpdate(sql2);
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:updateInfor_Load"+ e);
	        } 
		    //System.out.println("updateInfor_Load,done!!" );
	     }
	 
	
	 
	 public void updateInfor_M(String name,long aMoney)//修改金钱
	  {
	  	int i=-1;
	  	long money=selectInfor_M(name)+aMoney;
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
		      //System.out.println(money);
		      String sql="UPDATE User SET moneyn= "+money+" WHERE Name= '"+name+"'";
		      i=stmt.executeUpdate(sql);
		      
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:update_Money_ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:update_Money"+ e);
	        } 
		    //System.out.println("金钱修改成功");
		    
	     }
	 
	 
	 
	 public int checkUser(String name,long password)//核对密码
	 {
	 	if(selectInfor_PS(name)==0)return 0;
	 	else if(selectInfor_PS(name)==password)
	 	return 1;
	 	else return -1;
	 	}
	 
	 
	 
	 
	 public long selectInfor_PS(String name)//查找某个用户的信息（密码）（没有找到则返回0）
	 {
	 	
	 	long password=0;
	 	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   
			   String sql="SELECT * FROM User WHERE Name= '"+name+"'";
			   ResultSet rs =stmt.executeQuery(sql); 
			   if(rs.next())
			   {password=rs.getLong("Password");}
			    stmt.close();
		        con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound_GetInfor_Password"+e);
		    }
		    catch(SQLException e)
	      {
		    System.out.println("error:GetInfor_Password"+ e);
	      } 
		    return password;
	   }
	 
	 
	 public int selectInfor_OL(String name)//查找某个用户的信息（在线状态）（没有找到则返回0）
	 {
	 	
	 	int onLine=0;
	 	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   
			   String sql="SELECT * FROM User WHERE Name= '"+name+"'";
			   ResultSet rs =stmt.executeQuery(sql); 
			   if(rs.next())
			   {onLine=rs.getInt(4);}
			    stmt.close();
		        con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound_GetInfor_Password"+e);
		    }
		    catch(SQLException e)
	      {
		    System.out.println("error:GetInfor_Password"+ e);
	      } 
		    //System.out.println(onLine);
		    return onLine;
	   }
	 public long selectInfor_StartT(String name)//查找某个用户的信息（在线状态）（没有找到则返回0）
	 {
	 	
	 	long  startTime=0;
	 	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   
			   String sql="SELECT * FROM User WHERE Name= '"+name+"'";
			   ResultSet rs =stmt.executeQuery(sql); 
			   if(rs.next())
			   {startTime=rs.getLong(5);}
			    stmt.close();
		        con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound_GetInfor_Password"+e);
		    }
		    catch(SQLException e)
	      {
		    System.out.println("error:GetInfor_Password"+ e);
	      } 
		    //System.out.println(onLine);
		    return startTime;
	   }
	 
	 
	 public void updateInfor_StartT(long startTime, String name)/*修改下线时间(可调用getTim()函数
                                                                        修改下线时间用来计算在线时长)*/  
	 	{
	  	int i=-1;
	  
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   String sql1="UPDATE User SET startTime= "+startTime+" WHERE Name= '"+name+"'";
			      i=stmt.executeUpdate(sql1);
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound_Update_Infor_StartTime"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:Update_Infor_StartTime"+ e);
	        } 
		    //System.out.println("Update_Infor_StartTime成功" );
	   }
	 
	 public long selectInfor_M(String name)//查找某个用户的信息（金钱数）
	 {
	 	
	 	long money=0;
	 	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   
			   String sql="SELECT * FROM User WHERE Name= '"+name+"'";
			   ResultSet rs =stmt.executeQuery(sql); 
			   if(rs.next())
			   {money=rs.getLong("moneyn");}
			    stmt.close();
		        con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	      {
		    System.out.println("error:select_Money"+ e);
	      } 
		    return money;
	   }
	 
	 
	 public void updateInfor_EndT(long endTime, String name)/*修改下线时间(可调用getTim()函数
	                                                                 修改下线时间用来计算在线时长)*/
	  {
	  	int i=-1;
	  
	  	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   String sql1="UPDATE User SET endTime= "+endTime+" WHERE Name= '"+name+"'";
			      i=stmt.executeUpdate(sql1);
		      stmt.close();
		      con.close();
		  }
		  catch(ClassNotFoundException e)
		   {
		   System.out.println("error:ClassNotFound"+e);
		    }
		    catch(SQLException e)
	        {
	  	    System.out.println("error:UPDATE_Infor_endTime"+ e);
	        } 
		   //System.out.println("UPDATE_Infor_endTime,done!!" );
	     }
}

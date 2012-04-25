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
public class MyFileMgr 
{
	private int row=0;
	//主方法供调试时用
	/*public static  void main(String args[])
	{
		MyFileMgr MFM=new MyFileMgr();
		//MFM.selectInfor_fileName("sun",7);
		//MFM.fileNameExsit("甜蜜蜜",7);
		//JC.updateInfor_Price("star warIII",1000000);
		//JC.updateInfor_M("sun",20000);
		//MFM.insertInfor("sda","Nicholas",120,541336);
		//MFM.updateInfor_TOD("star warI");
		// MFM.showInfor_Length(8);
		//MFM.deleteInfor("star warI");
		//JC.selectInfor_user("sun");
		MFM.showInfor_Price("甜蜜蜜");
		
		}*/
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
	 	      String sql="select * from File";
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
		    	System.out.println("error:MyFileMgr_getRow"+ e);
		      } 
		   //System.out.println("MyFileMgr_getRow,done");
		   int aa=row;
		   row=0;
		   return aa;
	 }
	
	
	public String[] showInfor_Name(int length)//显示文件数据库记录（文件名）
    { 
		String fileName[]=new String[length];
 	  try 
	  {  //加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM File";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
         {   int i=0;
          while(rs.next())
            {
           fileName[i]=rs.getString(1);
            
            //System.out.println(fileName[i]);
            i++;
            }
            rs.close();
            stmt.close();
            con.close();
         }
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("ClassNotFound"+e);
      }
    catch(SQLException e)
    {
    	System.out.println("MyFileMgr_showInfor_Name"+ e);	
     } 
    return fileName;
   }
	
	 
 public String [] selectInfor_file_uploader(String uploader ,int row)//查找某个用户上传的所有文件
	 {
	 	String [] fileName=new String [row];           
	 	try 
		  {//加载驱动程序，此处为JDBC-ODBC桥
		     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		     //指定连接的ODBC数据源为："management"
		     String source="jdbc:odbc:management";
		       //通过驱动程序管理器建立与数据源的连接
		     Connection con=DriverManager.getConnection(source);
		       //创建执行查询的Statement对象
		      Statement stmt=con.createStatement();
			   
			   String sql="SELECT * FROM File WHERE Uploader= '"+uploader+"'";
			   ResultSet rs =stmt.executeQuery(sql); 
			   if(rs!=null) 
		          {   
		          	int i=0;
		          while(rs.next())
		            {
		            fileName[i]=rs.getString(1);
		            
		            i++; 
		            }
		          for(;i<row;i++)
		          	fileName[i]="wokaofantsy";
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
		    System.out.println("error:selectInfor_file_uploader"+ e);
	      } 
		    //for(int i=0;i<row;i++)
		    //{System.out.println("文件名："+fileName[i]);}
		    return fileName;
	   }
 
 
 
 public boolean fileNameExsit(String fileName,int l)// 判断上传的文件是否重名
 {
 	
 	 String[] fName=new String[l];
 	  fName=showInfor_Name(l);
 	 
 	  for(int i=0;i<l;i++)
 	  {
 	  	if(fName[i].equals(fileName))
 	  		{//System.out.println(fName[i]);
 	  		return true;
 	  	    //}
 	  	     }
 	  }
	    return false;
  }

	
	
	public long showInfor_Price(String fileName)//显示某文件价格//done
    { 
		long filePrice=0;
 	  try 
	  {  //加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM File WHERE FileName= '"+fileName+"'";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
         {   
          while(rs.next())
            {
             filePrice=rs.getLong(4);
            }
            rs.close();
            stmt.close();
            con.close();
         }
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error1"+e);
      }
    catch(SQLException e)
    {
    	System.out.println("error2"+ e);	
     } 
    //System.out.println(filePrice);
    return filePrice;
   }
	
	
	public String showInfor_Uploader(String fileName)//显示某文件上传者 //done
	{
		String uploader=null;
 	  try 
	  {  //加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM File WHERE FileName= '"+fileName+"'";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
         {  
          while(rs.next())
            {uploader=rs.getString(3); }
            rs.close();
            stmt.close();
            con.close();
         }
          //System.out.println(uploader);
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error1"+e);
      }
    catch(SQLException e)
    {
    	System.out.println("error2"+ e);
    	
     } 
    return uploader;
   }
	 
	public long showInfor_TOD(String fileName)//显示某文件下载次数/done
	{
		long TOD=0;
 	  try 
	  {  //加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM File WHERE FileName= '"+fileName+"'";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
         {  
          while(rs.next())
            {TOD=rs.getLong(2); }
            rs.close();
            stmt.close();
            con.close();
         }
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error1"+e);
      }
    catch(SQLException e)
    {
    	System.out.println("error2"+ e);	
     } 
    //System.out.println(TOD);
    return TOD;
   }
	
	
	
	public long[] showInfor_Length(int row)//显示文件数据库记录（大小）
    { 
		long fileLength[]=new long[row];
 	  try 
	  {  //加载驱动程序，此处为JDBC-ODBC桥
	      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	     //指定连接的ODBC数据源为："management"
	      String source="jdbc:odbc:management";
	       //通过驱动程序管理器建立与数据源的连接
	      Connection con=DriverManager.getConnection(source);
	       //创建执行查询的Statement对象
	      Statement stmt=con.createStatement();
 	      String sql="SELECT * FROM File";
          ResultSet rs=stmt.executeQuery(sql);
          if(rs!=null) 
         {   int i=0;
          while(rs.next())
            {
           fileLength[i]=rs.getLong(5);
             
            //System.out.println(fileLength[i]);
            i++;
            }
            rs.close();
            stmt.close();
            con.close();
         }
       }
 	catch(ClassNotFoundException e)
	   {
   	System.out.println("error1"+e);
      }
    catch(SQLException e)
    {
    	System.out.println("error2"+ e);	
     } 
    return fileLength;
   }
	
	
	
   public void insertInfor(String fileName,String uploader ,long price,long length)//添加文件记录
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
			      String sql="INSERT INTO File "+" VALUES('"+fileName+"',"+0+",'"+uploader+"',"+price+","+length+")";
			      i=stmt.executeUpdate(sql);
			      stmt.close();
			      con.close();
			     
			  }
		  	  catch(ClassNotFoundException e)
			   {
		 	   System.out.println("error1"+e);
		 	    }
		  	    catch(SQLException e)
		       {
		    	System.out.println("error2"+ e);
		    	
		       } 
		  	    
		      //System.out.println("数据添加到第"+i+"行");
		  	}
           
           
		  
		 public void deleteInfor(String fileName)//删除文件记录
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
			      String sql="DELETE FROM File WHERE FileName= '"+fileName+"'";
			      i=stmt.executeUpdate(sql);
			      stmt.close();
			      con.close();
			   }
			  catch(ClassNotFoundException e)
			   {
			   System.out.println("error1"+e);
			    }
			    catch(SQLException e)
		        {
		  	    System.out.println("error2"+ e);
		        } 
			    System.out.println(i+"行数据已经被删除");
		    }
		 
		 
		 public void updateInfor_TOD(String fileName)//下载次数加1
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
			      String sql1="SELECT * FROM File WHERE FileName= '"+fileName+"'";
				   ResultSet rs =stmt.executeQuery(sql1);
				   long tod=0;
				   while(rs.next())
				  {tod=rs.getLong(2)+1; };
				   String sql2="UPDATE File SET TimesOfDload= "+tod+" WHERE FileName= '"+fileName+"'";
				      i=stmt.executeUpdate(sql2);
			      stmt.close();
			      con.close();
			  }
			  catch(ClassNotFoundException e)
			   {
			   System.out.println("error1"+e);
			    }
			    catch(SQLException e)
		        {
		  	    System.out.println("error2"+ e);
		        } 
			    //System.out.println(i+"行信息修改成功" );
		     }
		 
		 
		 
		 public void updateInfor_Price(String fileName,long newPrice)//修改文件价格
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
			      String sql1="SELECT * FROM File WHERE FileName= '"+fileName+"'";
				   ResultSet rs =stmt.executeQuery(sql1);
				   long tod=0;
				   while(rs.next())
				  {tod=rs.getLong(2)+1; };
				   String sql2="UPDATE File SET Price= "+newPrice+" WHERE FileName= '"+fileName+"'";
				      i=stmt.executeUpdate(sql2);
			      stmt.close();
			      con.close();
			  }
			  catch(ClassNotFoundException e)
			   {
			   System.out.println("error1"+e);
			    }
			    catch(SQLException e)
		        {
		  	    System.out.println("UPDATE File Price"+ e);
		        } 
			    //System.out.println(i+"行信息修改成功" );
		     }}
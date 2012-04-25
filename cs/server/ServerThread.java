/*
 * Created on 2005-9-8
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
package server;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread 
{
	UserMgr UM=new UserMgr();
	MyFileMgr MFM=new MyFileMgr();
	public static final String str=new String(" ");
	public static final String LOAD="LOAD";                            //登录
	public static final String REGISTER="REGISTER";                    //注册
	public static final String ALLONLINE="ALLONLINE";                  //查询所有在线用户名
	public static final String ALLFILE="ALLFILE";                      //查询所有文件名
	public static final String FINDFILE="FINDFILE";                    //查找某个文件
	public static final String FINDOFONE="FINDOFONE";                  //查找某个人的所有文件
	public static final String UPLOAD="UPLOAD";                        //上传
	public static final String DOWNLOAD="DOWNLOAD";                    //下载
	public static final String REUSERLIST="REUSERLIST";                //刷新用户列表
	public static final String SAVEMONEY="SAVEMONEY";                      //充值
	public static final String QUIT="QUIT";                             //退出
	public static final String CHANGEPASSWORD="CHANGEPASSWORD";      //修改密码
	public static final String REFRESHUSERINFOR="REFRESHUSERINFOR";//刷新当前用户信息
	
	     private Socket socket=null;
	     public ServerThread(Socket socket)
	     {
	     	super("ServerThread");
	     	this.socket=socket;
	     	}
	     
///////////////////////////////////run()////////////////////////////////////////////	     
	     
	     public void run()
	     {
	     	DataInputStream in=null;
	     	DataOutputStream out=null;
	     	try{
	     	in=new DataInputStream(socket.getInputStream());
	     	out=new DataOutputStream(socket.getOutputStream());
	     	
	     	while(true)
	     	{
	     		String order=in.readUTF();
	     		in.readChar();
	     		if(order.equals(LOAD))                           //登录
	     			{
	     			String name=in.readUTF();
	     			in.readChar();
	     			long password=in.readLong();
	     			int onLine=0;
	     			onLine=UM.selectInfor_OL(name);
	     			if(onLine==1)
	     			{
	     				out.writeUTF("用户已登陆，请稍后登陆");   //用户已登陆，请稍后登陆
	     			    out.flush();
	     			    }

	     			else if(UM.checkUser(name,password)==0)//在数据库中查找看有没有这个名字，
	     				{
	     				out.writeUTF("用户名不存在,请注册");   //有看密码等不等，没有返回让其注册
	     			    out.flush();
	     			    }
	     			else if(UM.checkUser(name,password)==-1)
	     				{
	     				out.writeUTF("密码错误");
	     				out.flush();
	     				}
	     			else 
	     				{	
                            out.writeUTF("登录成功");
	     				    out.flush();
	     				    UM.updateInfor_Load(name);
	     				   double startTime=System.currentTimeMillis(); //取得当前时间
	     				   long a=(long)startTime/1000;
	     				   UM.updateInfor_StartT(a,name);
	     				}
	     			}
	     		
	     		
	     		else if(order.equals(REGISTER)) //注册
	     		    {
	     			String uname=null;
	     			uname=in.readUTF();
	     			//System.out.println("sdsa");
	     			in.readChar();
	     			long password=in.readLong();
	     			in.readChar();
	     			if(UM.userExist(uname))
	     				{out.writeUTF("重名,请重新输入");}
	     			else
	     			    {
	     				    UM.insertInfor(uname,password);
	     				    out.writeUTF("恭喜,注册成功!");
	     			    }
	     		    }
	     		
	     		
	     		else if(order.equals(ALLONLINE)) 
	     			{
	     		      int r=UM.getRow();
	     		      String []onLineUser=new String[r];
	     			  onLineUser=UM.showUserOnLine_Name(r);//查询所有在线用户名
                      synchronized(str)
	     			  {
	     			  out.writeInt(r);
	     			  out.writeChar('\t');
	     			  out.flush();
	     			  for(int i=0;i<r;i++)
	     			  {
	     			     if(onLineUser[i]!=null)
	     			         out.writeUTF(onLineUser[i]);
	     			     out.writeChar('\t');
	     			     out.flush();
	     			  }
	     			  }
	     			}
	     		
	     		
	     		else if(order.equals(ALLFILE))                      //查询所有文件名
	     			{
	     			int r=MFM.getRow();
   		            String []allFile=new String[r];
   			        allFile=MFM.showInfor_Name(r);
   			        synchronized(str)
				    {
				    out.writeInt(r);
				    out.writeChar('\t');
				    out.flush();
				    for(int i=0;i<r;i++)
				      {
				       if(allFile[i]!=null)
				         out.writeUTF(allFile[i]);
				       out.writeChar('\t');
				       out.flush();
				      }
				    }
                    }
	     		
	     		
	     		else if(order.equals(FINDFILE))                    //查找某个文件
	     		    {
	     			   String fileName=new String();
	     			   fileName=in.readUTF();
	     			   int r=MFM.getRow();
	     			   if(MFM.fileNameExsit(fileName,r))
	     			   	  out.writeUTF("文件已存在");
	     			   else
	     			   	  out.writeUTF("文件未存在");
	     		    }
	     		else if(order.equals(FINDOFONE))                    //查找某个人的所有文件
	     			{
	     			String s=in.readUTF();
	     			int r=MFM.getRow();
   		            String []fileOfOne=new String[r];
   			        fileOfOne=MFM.selectInfor_file_uploader(s,r);
   			        synchronized(str)
				    {
				    out.writeInt(r);
				    out.writeChar('\t');
				    out.flush();
				    for(int i=0;i<r;i++)
				      {
				       if(fileOfOne[i]!=null)
				       out.writeUTF(fileOfOne[i]);
				       out.writeChar('\t');
				       out.flush();
				      }
				    }
                    }
	     		
	     		else if(order.equals(UPLOAD))     //上传文件             
	     		    {
     			    String fn=in.readUTF();    //取得文件名
     			    String f="E:/share/"+fn;
     			    int row=MFM.getRow();
     			    
     			    in.readChar();
     			    if(!MFM.fileNameExsit(fn,row))
     			   {
     			    	out.writeUTF("不存在");
     			    	long length=0;
     			    length=in.readLong();
     			    String uploader=null;
     			    uploader=in.readUTF();
     			    try
				    {
 			         FileOutputStream fis=new FileOutputStream(f);
 			          //只能上传在服务器E盘中share文件夹中
 			        for(int i=0;i<length;i++)
 				       fis.write(in.read());
 			         
 			         }catch(IOException e){}
     			     MFM.insertInfor(fn,uploader,0,length); 
     			     UM.updateInfor_M(uploader,10);
     			     out.writeUTF("文件"+fn+"上传成功");
     			   }
     			    else
     			    	out.writeUTF("您上传的文件"+fn+"已存在，请重新选择文件上传！");
	     		   }
	     		
	     		
	     		else if(order.equals(DOWNLOAD))                  //下载        
	     			{
	     			    int c;
	     				String fn=in.readUTF();//文件名 
	     				in.readChar();
	     				String Dloader=in.readUTF();//下载者姓名
	     				
	     				String f="E:/share/"+fn;
	     				File file=new File(f);
	     				synchronized(fn)
						{
	     			     try
						   {
	     			         FileInputStream fis=new FileInputStream(f);
	     			         long length=file.length();
	     			         out.writeLong(length);
	     			         for(int i=0;i<length;i++)
	     				        out.write(fis.read());
	     			       }catch(IOException e){}
	     		         }
	     				  String Uloader=MFM.showInfor_Uploader(fn);
	     				  System.out.println(Uloader);
	     				  long addMoney=MFM.showInfor_Price(fn);
	     				  UM.updateInfor_M(Uloader,addMoney);
	     				  UM.updateInfor_M(Dloader,(0-addMoney));
	     				  MFM.updateInfor_TOD(fn);
	     				  long newTOD=MFM.showInfor_TOD(fn);
	     				  if(newTOD<50)
	     				  { 
	     				  	int a=(int) newTOD/10;
	     				   long newPrice=a*10;
	     				   MFM.updateInfor_Price(fn,newPrice);
	     				  }
	     				  else MFM.updateInfor_Price(fn,50);
	     			   }

	     		
	     		
	     		else if(order.equals(REUSERLIST))               // 刷新用户列表
	     		{
	     			int r=UM.getRow();
	     		      String []onLineUser=new String[r];
	     			  onLineUser=UM.showUserOnLine_Name(r);//查询所有在线用户名
                  synchronized(str)
	     			  {
	     			  out.writeInt(r);
	     			  out.writeChar('\t');
	     			  out.flush();
	     			  for(int i=0;i<r;i++)
	     			  {
	     			     if(onLineUser[i]!=null)
	     			         out.writeUTF(onLineUser[i]);
	     			     out.writeChar('\t');
	     			     out.flush();
	     			  }
	     			  }
	     			 }
	     		
	     		
	     		
	     		else if(order.equals(QUIT))  //退出
	     		{
	     		     String userName=in.readUTF();
	     		     in.readChar();
	     		     UM.updateInfor_Quit(userName); //数据库中该用户在线状态为0
	     		     double endTime=System.currentTimeMillis();//取得当前时间
	     		     long b=(long)endTime/1000;
	     		     long a=(long)UM.selectInfor_StartT(userName);
	     		     long addMoney=b-a;
	     		     UM.updateInfor_M(userName,addMoney);
	     		     //计算该用户在线时间,并转化为金钱加入到数据库中
	     		     in.close();
	     		     out.close();
	     		    socket.close();
	     		}
	     		
	     		
	     		
	     		else if(order.equals(CHANGEPASSWORD))//修改密码
	     		{
	     			long newPassword=0;
	     			newPassword=in.readLong();
	     			String name=null;
	     			in.readChar();
	     			name=in.readUTF();
	     			//System.out.println(name);
	     			//System.out.println(newPassword);
	     			UM.updateInfor_PS(newPassword,name);
	     			//在数据库中修改该用户的新密码
	     		}
	     		
	     		
	     		else if(order.equals(SAVEMONEY))    //充值
	     		{
	     		    long money=0;
	     		    String name=null;
	     		    name=in.readUTF();
	     		    money=in.readLong();
	     		    money=money*10;
	     		    in.readChar();
	     		    UM.updateInfor_M(name,money);
	     		    out.writeUTF("你已经成功充值");
	     		    out.writeLong(money);
					
	     		}
	     		
	     		
	     		
	     	else if(order.equals(REFRESHUSERINFOR))
	     	{
	     		try 
				{   long newMoney=0;
				    String name=null;
				    
				    name=in.readUTF();
				    newMoney=UM.selectInfor_M(name);
				    in.readChar();
				    out.writeLong(newMoney);
	     		}catch(IOException w){System.out.println("刷新当前用户信息出错");}
	     	 }
	     		}
	     	}catch(IOException e){}
	    }
	}

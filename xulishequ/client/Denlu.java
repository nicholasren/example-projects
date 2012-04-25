/*
 * Created on 2005-9-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package client;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * @author 09003112小组
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Denlu extends Frame {
	public static final String LOAD="LOAD";                            //登录
	public static final String REGISTER="REGISTER";                    //注册
	public static final String ALLONLINE="ALLONLINE";                  //查询所有在线用户名
	public static final String ALLFILE="ALLFILE";                      //查询所有文件名
	public static final String FINDFILE="FINDFILE";                    //查找某个文件
	public static final String FINDOFONE="FINDOFONE";                  //查找某个人的所有文件
	public static final String UPLOAD="UPLOAD";                        //上传
	public static final String DOWNLOAD="DOWNLOAD";                    //下载
	public static final String TIME="TIME";                            //查询时间，供转帐用
	public static final String REFILELIST="REFILELIST";    //刷新文件列表
	public static final String REUSERLIST="REUSERLIST";    //刷新用户列表
	public static final String SAVEMONEY="SAVEMONEY";      //充值
	public static final String REFRESHUSERINFOR="REFRESHUSERINFOR";//刷新当前用户信息
	
	static Frame denl=new Frame("FSC文件共享社区登录界面");
	static TextField txf1=new TextField("aaaa");//用户名输入
	static TextField txf2=new TextField("123");//密码输入
	static TextField txf3=new TextField("127.0.0.1");//主机IP输入
	
	static Label lab1=new Label("欢迎用户登陆我们的虚拟社区");
	static Label lab2=new Label("用户名:");
	static Label lab3=new Label("密码：");
	static Label lab4=new Label("连接IP:");
	static Label lab5=new Label("端口：");
	static Label lab6=new Label("3220");
	
	static Button btn3=new Button ("登陆");
	static Button btn2=new Button ("注册");
	static Button btn1=new Button ("连接");
	

    static boolean success=false;
    static String userName=null;
	static long userMoney=0;
	static Socket clientSocket=null;            //客户端的Socket
	
	static DataInputStream in=null;   //数据输入流
 	static DataOutputStream out=null; //数据输出流
 
 ////////////////////////////Denlu的主要方法//////////////////////////////////////////////	
	void run()
	{	
	denl.setBounds(350,200,320,250);
	denl.setLayout(null);
	denl.setBackground(Color.BLUE);
	lab1.setBounds(70,40,180,30);
	lab2.setBounds(50,120,60,20);
	lab3.setBounds(50,160,60,20);
	lab4.setBounds(20,90,40,20);
	lab5.setBounds(200,90,40,20);
	lab6.setBounds(240,90,50,20);
	txf1.setBounds(130,120,120,20);
	txf2.setBounds(130,160,120,20);
	txf3.setBounds(70,90,100,20);
	txf2.setEchoChar('*');
	btn1.setBounds(40,200,70,20);
	btn2.setBounds(220,200,70,20);
	btn3.setBounds(130,200,70,20);
	
	btn1.addActionListener(new ActLis());
	btn2.addActionListener(new ActLis());
	btn3.addActionListener(new ActLis());
	
	btn1.setBackground(Color.GREEN);
	btn2.setBackground(Color.GREEN);
	btn3.setBackground(Color.GREEN);
	
	denl.add(txf1);
	denl.add(txf2);
	denl.add(txf3);
	denl.add(lab1);
	denl.add(lab2);
	denl.add(lab3);
	denl.add(lab4);
	denl.add(lab5);
	denl.add(lab6);
	denl.add(btn1);
	denl.add(btn2);
	denl.add(btn3);
	
	denl.addWindowListener
	(new WindowAdapter()
			{public void windowClosing
		(WindowEvent e)
			{denl.dispose();;}});
	denl.setVisible(true);
	
	}
	
	
class ActLis implements ActionListener{      //事件侦听类
	

	public void actionPerformed(ActionEvent e)
	{
		Button btn=(Button)e.getSource();
		
////////////////////////////////连接/////////////////////////////////////////
		
		if(btn==btn1)
		{
				try
				{
					clientSocket=new Socket (txf3.getText(),3220);
					in=new DataInputStream(clientSocket.getInputStream());
					out=new DataOutputStream(clientSocket.getOutputStream());
					success=true;
				}
				catch(IOException i)
					{
					xiaoxi xx=new xiaoxi("连接失败！");
					success=false;
					}
				if(success)
					{xiaoxi aa=new xiaoxi("连接成功！");}
		}
		
/////////////////////////////////////登陆//////////////////////////////////		
		
		if(btn==btn3)
		{
			if(!success)
			   {xiaoxi aa=new xiaoxi("请先连接到服务器");}
			else
				{
				userName=txf1.getText();
				long password=0;
				try
				{
			    	password=Integer.parseInt(txf2.getText());
			    }catch(NumberFormatException r)
					{
						xiaoxi aa=new xiaoxi("密码请填入数字,并小于九位",200,200);
					}
			    try
				{
			    	out.writeUTF(LOAD);           //登陆 
			    	out.writeChar('\t');          //以'\t'为分界符
			    	out.writeUTF(userName);       //userName被赋值
			    	out.writeChar('\t');
			    	out.writeLong(password);
			    	String checkRs=in.readUTF();
			    	if(checkRs.equals("用户已登陆，请稍后登陆"))
			    	{
			    		   xiaoxi aa=new xiaoxi("用户已登陆，请稍后登陆");
			    		}
			    	else if(checkRs.equals("用户名不存在,请注册"))
			    		{
			    		   xiaoxi aa=new xiaoxi("用户名不存在,请注册");
			    		}
			    	else if(checkRs.equals("密码错误"))
			    	    {
			    		   xiaoxi aa=new xiaoxi("密码错误");
			    	    }
			    	else if(checkRs.equals("登录成功"))
			    	    {
			    		   
			    		   Zhuyemian zhuye=new Zhuyemian();
						   zhuye.run();
						   denl.setVisible(false);
			    	    }
		     	}catch(IOException a){}
				}
		}
		
////////////////////////////////////注册/////////////////////////////////////		
		
		if(btn==btn2)
		{
			Zhuchelei zhuche=new Zhuchelei();
			zhuche.run();
			denl.setVisible(false);
		}

	}
}
}

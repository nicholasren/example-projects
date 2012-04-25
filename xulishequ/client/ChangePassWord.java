 /*
  * Created on 2005-9-8
  *
  * TODO To change the template for this generated file go to
  * Window - Preferences - Java - Code Style - Code Templates
  */
 package client;
 import java.awt.*;
 import java.awt.event.*;
 /**
  * @author 09003112小组已改
  *
  * TODO To change the template for this generated type comment go to
  * Window - Preferences - Java - Code Style - Code Templates
  */
 public class ChangePassWord extends Frame {
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
 	public static final String CHANGEPASSWORD="CHANGEPASSWORD";      //修改密码
 	
 	 static Frame zhuche=new Frame("修改密码");
 	 static TextField txf2=new TextField();//新密码输入
 	 static TextField txf3=new TextField();//新密码重输入
 	 static Label lab2=new Label("你的新密码：");
 	 static Label lab3=new Label("确认新密码:");
 	 static Button btn1=new Button ("确定");
 	 static Button btn2=new Button ("返回");
 	 
 	 
 	public void run()
 	{
 		zhuche.setBounds(300,300,250,220);
 		zhuche.setLayout(null);
 		zhuche.setBackground(Color.BLUE);
 		txf2.setBounds(100,70,120,20);//新密码
 		txf3.setBounds(100,100,120,20);//确认新密码
 		lab2.setBounds(20,70,60,20);
 		lab3.setBounds(20,100,70,20);
 		btn1.setBounds(50,140,50,30);
 		btn2.setBounds(150,140,50,30);
 		
 		btn1.addActionListener(new ActLisz());
 		btn2.addActionListener(new ActLisz());
 		
 		btn1.setBackground(Color.GREEN);
 		btn2.setBackground(Color.GREEN);
 		
 		txf2.setEchoChar('*');
 		txf3.setEchoChar('*');
 		
 		zhuche.add(txf2);
 		zhuche.add(txf3);
 		zhuche.add(lab2);
 		zhuche.add(lab3);
 		zhuche.add(btn1);
 		zhuche.add(btn2);
 		
 		zhuche.addWindowListener
 		(new WindowAdapter()
 				{public void windowClosing
 			(WindowEvent e)
 				{zhuche.dispose();}});
 		zhuche.setVisible(true);
 		}
 	
 	class ActLisz implements ActionListener{
 	public void actionPerformed(ActionEvent a)
 	{
 	Button  btn=(Button)a.getSource();
 	if(btn==btn1)//确认
 	{
 		//返回主界面向服务器传送信息，并取得服务器返回的信息
 		if(!txf2.getText().equals(txf3.getText()))
 		{
 			xiaoxi z=new xiaoxi("两次密码输入不同！！请重新输入");
 		}
 		else
 		{
 			boolean zhuce=true;
 			long newPassword=0L;
 			try
 			{
 				newPassword=Integer.parseInt(txf2.getText());//取得新密码
 		    }catch(NumberFormatException r)
 			{
 		    	xiaoxi s=new xiaoxi("请输入九位以内的数字");
 		    	zhuce=false;
 			}
 		    if(zhuce)
 		    {
 		    try
 			{
 		    	Denlu.out.writeUTF(CHANGEPASSWORD);
 		    	Denlu.out.writeChar('\t');

 		    	Denlu.out.writeLong(newPassword);
 		    	Denlu.out.writeChar('\t');
 		    	Denlu.out.writeUTF(Denlu.userName);
	    		xiaoxi d=new xiaoxi("修改密码成功");
 		    }catch(Exception s){}
 		    }
 		  }
 		}
 	if(btn==btn2)//返回
 	{
 		zhuche.dispose();
 		Zhuyemian.zhuyemian.setVisible(true);
 		
 	}
 	}
 	}
 	}



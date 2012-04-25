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
 * @author 09003112小组
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Zhuchelei extends Frame 
{
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
	
	 static Frame zhuche=new Frame("欢迎注册");
	 static TextField txf1=new TextField();//用户名输入
	 static TextField txf2=new TextField();//密码输入
	 static TextField txf3=new TextField();//密码重输入
	 static Label lab1=new Label("用户名：");
	 static Label lab2=new Label("密码：");
	 static Label lab3=new Label("重输入密码:");
	 static Button btn1=new Button ("注册");
	 static Button btn2=new Button ("返回");
	 
	public void run(){
		zhuche.setBounds(300,300,250,220);
		zhuche.setLayout(null);
		zhuche.setBackground(Color.BLUE);
		txf1.setBounds(100,40,120,20);
		txf2.setBounds(100,70,120,20);
		txf3.setBounds(100,100,120,20);
		lab1.setBounds(20,40,60,20);
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
		
		zhuche.add(txf1);
		zhuche.add(txf2);
		zhuche.add(txf3);
		zhuche.add(lab1);
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
	
/////////////////////////////点击注册按钮///////////////////////////////////////////////////	
	if(btn==btn1)
	{		
		boolean numberFormat=true;
		if(Denlu.success)
		{
			String registerName=txf1.getText();//取得注册用户名
		if(!txf2.getText().equals(txf3.getText()))
		{
			xiaoxi z=new xiaoxi("两次密码输入不同！！请重新输入");
			
		}
		else
		{
			long registerPassword=0L;
			try
			{
				registerPassword=Integer.parseInt(txf2.getText());//取得注册密码
		    }catch(NumberFormatException r)
			{
		    	xiaoxi s=new xiaoxi("请输入九位以内的数字",200,200);
		    	numberFormat=false;
			}
		    try
			{
		    	if(numberFormat)
		    	{Denlu.out.writeUTF(REGISTER);
		    	Denlu.out.writeChar('\t');
		    	Denlu.out.writeUTF(registerName);
		    	Denlu.out.writeChar('\t');
		    	Denlu.out.writeLong(registerPassword);
		    	Denlu.out.writeChar('\t');
		    	String g=Denlu.in.readUTF();
		    	synchronized( " ")
			    {
		    	if(g.equals("重名,请重新输入"))
		    		{
		    		xiaoxi d=new xiaoxi("重名,请重新输入");
		    		}
		    	else
		    	    {
		    		xiaoxi f=new xiaoxi("恭喜,注册成功! 用户名为"+registerName+"请常来玩啊!");
		    	    }
			    }
		    	}
		    }catch(Exception s){}
		}
		}
		else
		{
			xiaoxi xx=new xiaoxi("请先连接服务器!");	
		}
		}
	
////////////////////////////////返回登陆界面////////////////////////////////////////////	
	if(btn==btn2){
		zhuche.dispose();
		Denlu.denl.setVisible(true);
		
	}
	}
	}
}


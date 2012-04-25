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
 /**
  * @author 09003112小组
  *
  * TODO To change the template for this generated type comment go to
  * Window - Preferences - Java - Code Style - Code Templates
  */

public class Zhuyemian {
	public static final String LOAD="LOAD";                //登录
	public static final String REGISTER="REGISTER";        //注册
	public static final String ALLONLINE="ALLONLINE";      //查询所有在线用户名
	public static final String ALLFILE="ALLFILE";          //查询所有文件名
	public static final String FINDFILE="FINDFILE";        //查找某个文件
	public static final String FINDOFONE="FINDOFONE";      //查找某个人的所有文件
	public static final String UPLOAD="UPLOAD";            //上传
	public static final String DOWNLOAD="DOWNLOAD";        //下载
	public static final String TIME="TIME";                //查询时间，供转帐用
	public static final String REFILELIST="REFILELIST";    //刷新文件列表
	public static final String REUSERLIST="REUSERLIST";    //刷新用户列表
	public static final String SAVEMONEY="SAVEMONEY";      //充值
	public static final String REFRESHUSERINFOR= "REFRESHUSERINFOR";  //刷新当前用户信息
	static Frame zhuyemian=new Frame("FSC虚拟社区");
	static List list1=new List();
	static List list2=new List();
	static Scrollbar scr=new Scrollbar();
	static Button btn1=new Button ("修改密码");
	static Button btn2=new Button ("上传文件");
	static Button btn3=new Button ("下载文件");
	static Button btn4=new Button ("退出");
	static Button btn5=new Button ("用户充值");
	static Button btn6=new Button ("按用户名查找");
	static Button btn7=new Button ("按文件名查找");
	static Button btn8=new Button ("显示在线用户");
	static Button btn9=new Button ("显示文件列表");
	static Button btn10=new Button ("刷新用户信息");
	static TextField txf6=new TextField();
	static TextField txf8=new TextField();
	static Label lab1=new Label("用户名:");
	static Label lab2=new Label("金币:");
	static Label lab6=new Label("用户:");
	static Label lab8=new Label("文件列表");
	static Label lab9=new Label("在线用户列表");
	static Label lab10=new Label("文件");
	static Label lab11=new Label(Denlu.userName);//用户名
	static Label lab12=new Label(""+Denlu.userMoney+"");//金钱数
	
	
////////////////////////////////主要方法///////////////////////////////////////	
	
	public void run()
	{
		zhuyemian.setBounds(0,100,450,700);
		zhuyemian.setLayout(null);
		zhuyemian.setBackground(Color.BLUE);
		list1.setBounds(20,80,150,250);
		list2.setBounds(20,400,150,250);
		lab1.setBounds(250,50,60,25);
		lab2.setBounds(250,90,60,20);
		btn5.setBounds(250,180,80,20);
		btn1.setBounds(250,640,60,25);
		lab6.setBounds(200,560,40,20);
		lab8.setBounds(60,50,70,20);
		lab9.setBounds(60,370,80,20);
		lab10.setBounds(200,600,30,20);
		lab11.setBounds(330,50,70,20);
		lab12.setBounds(330,90,70,20);
		btn2.setBounds(350,300,70,20);
		btn3.setBounds(250,300,70,20);
		btn4.setBounds(320,640,60,25);
		btn6.setBounds(320,560,120,20);
		btn7.setBounds(320,600,120,20);
		btn8.setBounds(250,220,170,20);
		btn9.setBounds(250,260,170,20);
		btn10.setBounds(340,180,85,20);
		txf6.setBounds(240,560,70,20);
		txf8.setBounds(240,600,70,20);
		btn1.setBackground(Color.GREEN);
		btn2.setBackground(Color.GREEN);
		btn3.setBackground(Color.GREEN);
		btn4.setBackground(Color.GREEN);
		btn5.setBackground(Color.GREEN);
		btn6.setBackground(Color.GREEN);
		btn7.setBackground(Color.GREEN);
		btn8.setBackground(Color.GREEN);
		btn9.setBackground(Color.GREEN);
		btn10.setBackground(Color.GREEN);

		btn1.addActionListener(new ActLisz());
		btn2.addActionListener(new ActLisz());
		btn3.addActionListener(new ActLisz());
		btn4.addActionListener(new ActLisz());
		btn5.addActionListener(new ActLisz());
		btn6.addActionListener(new ActLisz());
		btn7.addActionListener(new ActLisz());
		btn8.addActionListener(new ActLisz());
		btn9.addActionListener(new ActLisz());
		btn10.addActionListener(new ActLisz());

		

	    zhuyemian.add(list1);
	    zhuyemian.add(list2);
	    zhuyemian.add(btn5);
	    zhuyemian.add(lab1);
	    zhuyemian.add(lab2);
	    zhuyemian.add(lab6);
	    zhuyemian.add(lab8);
	    zhuyemian.add(lab9);
	    zhuyemian.add(lab10);
	    zhuyemian.add(lab11);
	    zhuyemian.add(lab12);
	    zhuyemian.add(txf6);
	    zhuyemian.add(txf8);
	    zhuyemian.add(btn1);
	    zhuyemian.add(btn2);
	    zhuyemian.add(btn3);
	    zhuyemian.add(btn4);
	    zhuyemian.add(btn6);
	    zhuyemian.add(btn7);
	    zhuyemian.add(btn8);
	    zhuyemian.add(btn9);
	    zhuyemian.add(btn10);
	    zhuyemian.addWindowListener
		(new WindowAdapter()
				{public void windowClosing
			(WindowEvent e)
				{try
					{
					Denlu.out.writeUTF("QUIT");
					Denlu.out.writeChar('\t');
					Denlu.out.writeUTF(Denlu.userName);
					Denlu.out.writeChar('\t');
					zhuyemian.dispose();
					Denlu.userName=null;
					Denlu.userMoney=0;
					//Denlu.denl.setVisible(true);
					Denlu.in.close();
					Denlu.out.close();
					Denlu.clientSocket.close();
			    }catch(IOException yy){}}});
	    zhuyemian.setVisible(true);
	}
	
	////////////////////////////////////////////////
	class ActLisz implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			Button  btn=(Button)a.getSource();
			
/////////////////////////////改变密码/////////////////////////////////////			
			if(btn==btn1)           
			{
				ChangePassWord changepassword=new ChangePassWord();
				changepassword.run();
				zhuyemian.setVisible(false);
		    }
////////////////////////上传文件//////////////////////////	
			
			if(btn==btn2)                      
			{ 
				Frame f=new Frame("upload")	;
				f.setBounds(200,200,200,200);
				f.setBackground(Color.BLUE);
				FileDialog file1=new FileDialog(f,"打开",FileDialog.LOAD);
				file1.setVisible(true);
				String fileName=file1.getFile();
				if(fileName!=null)
				{
				String upPath=file1.getDirectory();
	              try
				  {
					Denlu.out.writeUTF("UPLOAD");
					Denlu.out.writeChar('\t');
					int c;
			     	
			     	File file=new File(upPath+fileName);
			     	if(!file.exists())
			     		{xiaoxi qq=new xiaoxi("文件不存在");}
			     	else
			     	  {
			     		long length=0;
			     	    length=file.length();
			     	    Denlu.out.writeUTF(file.getName());
			     	    Denlu.out.writeChar('\t');
			     	    String s=Denlu.in.readUTF();
			     	    if(s.equals("不存在"))
			     	    
			     	    {
			     	    	Denlu.out.writeLong(length);
			     	    String uploader=Denlu.userName;
			     	    Denlu.out.writeUTF(uploader);
			     	    Denlu.out.flush();
			     	   xiaoxi xx=new xiaoxi("正在上传文件");
			     	     try
					       {int l=0;
			     	          FileInputStream fis=new FileInputStream(upPath+fileName);
			     	         
			     	         
			     	          for(int i=0;i<length;i++)
					          {
			     		       Denlu.out.write(fis.read());
			     		      if(i==(length/100)*l)
			     		         { 
						          xx.lab.setText("已上传文件"+l+"%");l++;
						         }
			                   }
					        }catch(IOException w){};
					        xx.lab.setText(Denlu.in.readUTF());
			     	    }
			     	    else if(s.equals("您上传的文件"+file.getName()+"已存在，请重新选择文件上传！"))
			     	         {
			     	        	xiaoxi dd=new xiaoxi("您上传的文件"+file.getName()+"已存在，请重新选择文件上传！");
			     	          }
			     	     }
				    }catch(IOException w){}
				}
				file1.dispose();
			}
			     	       
			     	   
	///////////////////////下载文件//////////////////////////////		     	
			
			
			if(btn==btn3)
			{
				Frame f1=new Frame("download")	;
				f1.setBounds(200,200,200,200);
				f1.setBackground(Color.BLUE);
				FileDialog file2=new FileDialog(f1,"保存",FileDialog.SAVE);
				file2.setFile(list1.getSelectedItem());
				file2.setVisible(true);
				String aa=file2.getFile();
				if(aa!=null)
				{
				//System.out.println(file2.getDirectory()+file2.getFile());
				try{
					Denlu.out.writeUTF("DOWNLOAD");	
		     	    Denlu.out.writeChar('\t');
		     	    Denlu.out.flush();
		            String fn=file2.getDirectory()+file2.getFile();     //指定下载文件夹，到时由用户来指定
		     	    Denlu.out.writeUTF(file2.getFile()); //将从文件列表中得到
		            Denlu.out.writeChar('\t'); 
		            Denlu.out.writeUTF(Denlu.userName);
		     	    Denlu.out.flush();
		     	    synchronized(fn)
				    {
				        try
				        {
				           FileOutputStream fos=new FileOutputStream(fn);
				          xiaoxi xx=new xiaoxi("正在下载文件");                           //只能下载服务器D盘中share文件夹中的内容
				           long length=Denlu.in.readLong();
				          int l=0;
				           for(int i=0;i<length;i++)
				           {	fos.write(Denlu.in.read());
				           if(i==(length/100)*l)
				             {
				               xx.lab.setText("已下载文件"+l+"%");l++;
				              }
				           }
				         xx.lab.setText("成功下载文件"+file2.getFile()); 
				          }catch(IOException e){}
				      }
			        }catch(IOException e){}
				}
				file2.dispose();
				}
			
			
///////////////////////////退出////////////////////////////////////////////			
			
			if(btn==btn4)             //退出
			{                         //userName是登陆后的用户名,退出时界面关闭,数据库中
				                      //在线状态为0,并计算钱数添加到数据库中
				try
				{
					Denlu.out.writeUTF("QUIT");
					Denlu.out.writeChar('\t');
					Denlu.out.writeUTF(Denlu.userName);
					Denlu.out.writeChar('\t');
					zhuyemian.dispose();
					Denlu.userName=null;
					Denlu.userMoney=0;
					Denlu.in.close();
					Denlu.out.close();
					Denlu.clientSocket.close();
					
			    }catch(IOException e){}
			}
			
//////////////////////////////银行转帐////////////////////////////////////////
			
			if(btn==btn5)                                   //银行转帐
			{
				SaveMoney saveMoney=new SaveMoney();
				 saveMoney.run();
				zhuyemian.setVisible(false);
			}
			
			
			if(btn==btn6)
			{
			try{
				Denlu.out.writeUTF(FINDOFONE);     //查找某个人上传的所有文件
				Denlu.out.writeChar('\t');
				Denlu.out.writeUTF(txf6.getText());//传送某个用户名过去以供查询
				Denlu.out.flush();
		     	int r=Denlu.in.readInt();
		     	Denlu.in.readChar();
		     	
		     	String []fileOfOne=new String[r];
		     	
		     	synchronized("  ")
				  {
		          for(int i=0;i<r;i++)
				    {
		     		   fileOfOne[i]=Denlu.in.readUTF();
		     		   Denlu.in.readChar();
				       //if(fileOfOne[i]!=null)
				          //System.out.println(fileOfOne[i]);
				    }
				  }
		     	 list1.removeAll();
		 	    	for(int i=0;i<r;i++)
		 	    	{
		 	    		
		 	    		if(fileOfOne[i]!=null&&!fileOfOne[i].equals("wokaofantsy"))
		 	    			list1.add(fileOfOne[i]);
		 	    	}
		          }catch(IOException w){System.out.println("按用户名查找错误");}
			   }

///////////////////////////////////////查找某个特定文件//////////////////////////////////			
			
			if(btn==btn7)                        //查找文件是否存在
			{
				try{
					Denlu.out.writeUTF(FINDFILE);  // 找某个特定的文件，判断其是否存在 
					Denlu.out.writeChar('\t');
					String s=txf8.getText();
					Denlu.out.writeUTF(s);
					Denlu.out.flush();
					String q=Denlu.in.readUTF();
					if(q.equals("文件未存在"))
					{xiaoxi ss=new xiaoxi("文件未存在");}
					else if(q.equals("文件已存在"))
					{xiaoxi  ss=new xiaoxi("文件已存在");
					list1.removeAll();
					list1.add(s);
					}
				}catch(IOException e){System.out.println("查找文件名失败");}
				
			}
			
			
////////////////////////刷新在线用户列表///////////////////////////////////////////			
			if(btn==btn8)                             //刷新在线用户列表
			{
				try
				{
					Denlu.out.writeUTF(REUSERLIST);  
					Denlu.out.writeChar('\t');
					Denlu.out.flush();
			     	int r=Denlu.in.readInt();
			     	Denlu.in.readChar();
			     	//System.out.println(r);
			     	String []onLineUser=new String[r];
			     	
			     	synchronized("  ")
					  {
			          for(int i=0;i<r;i++)
					  {
			     		 onLineUser[i]=Denlu.in.readUTF();
			     		 Denlu.in.readChar();
					      //if(onLineUser[i]!=null)
					      //System.out.println(onLineUser[i]);
					  }
					  }//已从服务端得到String数组onLineUser和数组长度r
			     	list2.removeAll();
		 	    	for(int i=0;i<r;i++)
		 	    	{
		 	    		if(onLineUser[i]!=null&&!onLineUser[i].equals("administrator"))
		 	    			list2.add(onLineUser[i]);
		 	    	}
					
				}
					catch(IOException i){System.out.println("刷新用户列表失败");}
			}
			
			
////////////////////////////刷新文件列表///////////////////////////////////////////////			
			
			if(btn==btn9)                            //刷新文件列表
			{
				try
				{
					Denlu.out.writeUTF(ALLFILE);  //返回所有文件名
					Denlu.out.writeChar('\t');
					Denlu.out.flush();
			     	int r=Denlu.in.readInt();
			     	Denlu.in.readChar();
			     	String []allFile=new String[r];
			     	
			     	synchronized(" ")
					  {
			          for(int i=0;i<r;i++)
					  {
			     		 allFile[i]=Denlu.in.readUTF();
			     		Denlu.in.readChar();
					      //if(allFile[i]!=null)
					      //System.out.println(allFile[i]);
					  }
			          list1.removeAll();
			 	    	for(int i=0;i<r;i++)
			 	    	{
			 	    		
			 	    		if(allFile[i]!=null)
			 	    			list1.add(allFile[i]);
			 	    	}
					  }
				}catch(IOException e){System.out.println("刷新文件列表失败");}					
			}
			
///////////////////////////刷新用户信息/////////////////////////////////////////////////			
			
			if(btn==btn10)                         //刷新用户信息
			{
				try
			    {   long newMoney=0;
					Denlu.out.writeUTF(REFRESHUSERINFOR);
					Denlu.out.writeChar('\t');
					Denlu.out.writeUTF(Denlu.userName);
					Denlu.out.writeChar('\t');
					newMoney=Denlu.in.readLong();
					//System.out.println(newMoney);
					Zhuyemian.lab12.setText(""+newMoney+"");
				}catch(IOException  t)
				{System.out.println("zhuyenian shauxin IOException");}
			}
	 }
	}
}

			
			

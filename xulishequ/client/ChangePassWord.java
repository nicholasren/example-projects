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
  * @author 09003112С���Ѹ�
  *
  * TODO To change the template for this generated type comment go to
  * Window - Preferences - Java - Code Style - Code Templates
  */
 public class ChangePassWord extends Frame {
 	public static final String LOAD="LOAD";                            //��¼
 	public static final String REGISTER="REGISTER";                    //ע��
 	public static final String ALLONLINE="ALLONLINE";                  //��ѯ���������û���
 	public static final String ALLFILE="ALLFILE";                      //��ѯ�����ļ���
 	public static final String FINDFILE="FINDFILE";                    //����ĳ���ļ�
 	public static final String FINDOFONE="FINDOFONE";                  //����ĳ���˵������ļ�
 	public static final String UPLOAD="UPLOAD";                        //�ϴ�
 	public static final String DOWNLOAD="DOWNLOAD";                    //����
 	public static final String TIME="TIME";                            //��ѯʱ�䣬��ת����
 	public static final String REFILELIST="REFILELIST";    //ˢ���ļ��б�
 	public static final String REUSERLIST="REUSERLIST";    //ˢ���û��б�
 	public static final String SAVEMONEY="SAVEMONEY";      //��ֵ
 	public static final String CHANGEPASSWORD="CHANGEPASSWORD";      //�޸�����
 	
 	 static Frame zhuche=new Frame("�޸�����");
 	 static TextField txf2=new TextField();//����������
 	 static TextField txf3=new TextField();//������������
 	 static Label lab2=new Label("��������룺");
 	 static Label lab3=new Label("ȷ��������:");
 	 static Button btn1=new Button ("ȷ��");
 	 static Button btn2=new Button ("����");
 	 
 	 
 	public void run()
 	{
 		zhuche.setBounds(300,300,250,220);
 		zhuche.setLayout(null);
 		zhuche.setBackground(Color.BLUE);
 		txf2.setBounds(100,70,120,20);//������
 		txf3.setBounds(100,100,120,20);//ȷ��������
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
 	if(btn==btn1)//ȷ��
 	{
 		//�����������������������Ϣ����ȡ�÷��������ص���Ϣ
 		if(!txf2.getText().equals(txf3.getText()))
 		{
 			xiaoxi z=new xiaoxi("�����������벻ͬ��������������");
 		}
 		else
 		{
 			boolean zhuce=true;
 			long newPassword=0L;
 			try
 			{
 				newPassword=Integer.parseInt(txf2.getText());//ȡ��������
 		    }catch(NumberFormatException r)
 			{
 		    	xiaoxi s=new xiaoxi("�������λ���ڵ�����");
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
	    		xiaoxi d=new xiaoxi("�޸�����ɹ�");
 		    }catch(Exception s){}
 		    }
 		  }
 		}
 	if(btn==btn2)//����
 	{
 		zhuche.dispose();
 		Zhuyemian.zhuyemian.setVisible(true);
 		
 	}
 	}
 	}
 	}



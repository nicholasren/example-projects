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
 * @author 09003112С��
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Denlu extends Frame {
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
	public static final String REFRESHUSERINFOR="REFRESHUSERINFOR";//ˢ�µ�ǰ�û���Ϣ
	
	static Frame denl=new Frame("FSC�ļ�����������¼����");
	static TextField txf1=new TextField("aaaa");//�û�������
	static TextField txf2=new TextField("123");//��������
	static TextField txf3=new TextField("127.0.0.1");//����IP����
	
	static Label lab1=new Label("��ӭ�û���½���ǵ���������");
	static Label lab2=new Label("�û���:");
	static Label lab3=new Label("���룺");
	static Label lab4=new Label("����IP:");
	static Label lab5=new Label("�˿ڣ�");
	static Label lab6=new Label("3220");
	
	static Button btn3=new Button ("��½");
	static Button btn2=new Button ("ע��");
	static Button btn1=new Button ("����");
	

    static boolean success=false;
    static String userName=null;
	static long userMoney=0;
	static Socket clientSocket=null;            //�ͻ��˵�Socket
	
	static DataInputStream in=null;   //����������
 	static DataOutputStream out=null; //���������
 
 ////////////////////////////Denlu����Ҫ����//////////////////////////////////////////////	
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
	
	
class ActLis implements ActionListener{      //�¼�������
	

	public void actionPerformed(ActionEvent e)
	{
		Button btn=(Button)e.getSource();
		
////////////////////////////////����/////////////////////////////////////////
		
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
					xiaoxi xx=new xiaoxi("����ʧ�ܣ�");
					success=false;
					}
				if(success)
					{xiaoxi aa=new xiaoxi("���ӳɹ���");}
		}
		
/////////////////////////////////////��½//////////////////////////////////		
		
		if(btn==btn3)
		{
			if(!success)
			   {xiaoxi aa=new xiaoxi("�������ӵ�������");}
			else
				{
				userName=txf1.getText();
				long password=0;
				try
				{
			    	password=Integer.parseInt(txf2.getText());
			    }catch(NumberFormatException r)
					{
						xiaoxi aa=new xiaoxi("��������������,��С�ھ�λ",200,200);
					}
			    try
				{
			    	out.writeUTF(LOAD);           //��½ 
			    	out.writeChar('\t');          //��'\t'Ϊ�ֽ��
			    	out.writeUTF(userName);       //userName����ֵ
			    	out.writeChar('\t');
			    	out.writeLong(password);
			    	String checkRs=in.readUTF();
			    	if(checkRs.equals("�û��ѵ�½�����Ժ��½"))
			    	{
			    		   xiaoxi aa=new xiaoxi("�û��ѵ�½�����Ժ��½");
			    		}
			    	else if(checkRs.equals("�û���������,��ע��"))
			    		{
			    		   xiaoxi aa=new xiaoxi("�û���������,��ע��");
			    		}
			    	else if(checkRs.equals("�������"))
			    	    {
			    		   xiaoxi aa=new xiaoxi("�������");
			    	    }
			    	else if(checkRs.equals("��¼�ɹ�"))
			    	    {
			    		   
			    		   Zhuyemian zhuye=new Zhuyemian();
						   zhuye.run();
						   denl.setVisible(false);
			    	    }
		     	}catch(IOException a){}
				}
		}
		
////////////////////////////////////ע��/////////////////////////////////////		
		
		if(btn==btn2)
		{
			Zhuchelei zhuche=new Zhuchelei();
			zhuche.run();
			denl.setVisible(false);
		}

	}
}
}

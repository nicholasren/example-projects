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
 * @author 09003112С��
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Zhuchelei extends Frame 
{
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
	
	 static Frame zhuche=new Frame("��ӭע��");
	 static TextField txf1=new TextField();//�û�������
	 static TextField txf2=new TextField();//��������
	 static TextField txf3=new TextField();//����������
	 static Label lab1=new Label("�û�����");
	 static Label lab2=new Label("���룺");
	 static Label lab3=new Label("����������:");
	 static Button btn1=new Button ("ע��");
	 static Button btn2=new Button ("����");
	 
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
	
/////////////////////////////���ע�ᰴť///////////////////////////////////////////////////	
	if(btn==btn1)
	{		
		boolean numberFormat=true;
		if(Denlu.success)
		{
			String registerName=txf1.getText();//ȡ��ע���û���
		if(!txf2.getText().equals(txf3.getText()))
		{
			xiaoxi z=new xiaoxi("�����������벻ͬ��������������");
			
		}
		else
		{
			long registerPassword=0L;
			try
			{
				registerPassword=Integer.parseInt(txf2.getText());//ȡ��ע������
		    }catch(NumberFormatException r)
			{
		    	xiaoxi s=new xiaoxi("�������λ���ڵ�����",200,200);
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
		    	if(g.equals("����,����������"))
		    		{
		    		xiaoxi d=new xiaoxi("����,����������");
		    		}
		    	else
		    	    {
		    		xiaoxi f=new xiaoxi("��ϲ,ע��ɹ�! �û���Ϊ"+registerName+"�볣���氡!");
		    	    }
			    }
		    	}
		    }catch(Exception s){}
		}
		}
		else
		{
			xiaoxi xx=new xiaoxi("�������ӷ�����!");	
		}
		}
	
////////////////////////////////���ص�½����////////////////////////////////////////////	
	if(btn==btn2){
		zhuche.dispose();
		Denlu.denl.setVisible(true);
		
	}
	}
	}
}

